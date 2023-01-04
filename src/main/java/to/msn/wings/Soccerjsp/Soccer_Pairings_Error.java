package to.msn.wings.Soccerjsp;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Create
 */
@WebServlet("/Soccer_Pairings_Error")
public class Soccer_Pairings_Error extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String kickoff = request.getParameter("kickoff");
		String my_country_id = request.getParameter("my_country_id");
		String enemy_country_id = request.getParameter("enemy_country_id");
		
		request.setAttribute("id", id);
		request.setAttribute("kickoff", kickoff);
		request.setAttribute("my_country_id",my_country_id);
		request.setAttribute("enemy_country_id", enemy_country_id);
		
		CheckUtil check = new CheckUtil();
		// 必須検証
		//check.requiredCheck(id, "必須検証:ID");
		//check.requiredCheck(my_country_id, "必須検証:自国ID");
		//check.requiredCheck(enemy_country_id, "必須検証:敵国ID");
		
		// 文字列長検証（max文字以内であるか）
		//check.lengthCheck(my_country_id, 3, "文字列長検証:自国ID");
		//check.lengthCheck(enemy_country_id, 3, "文字列長検証:敵国ID");
		//check.lengthCheck(group_name, 1, "文字列長検証:グループの区分");
		
		// 重複検証
		//check.duplicateCheck(, "select name from countries where name = ?", "重複検証:氏名");
		//check.duplicateCheck(id,"select id from pairings where id = ?", "重複検証:ID");
		check.duplicateChecked(my_country_id,enemy_country_id, "重複検証:自国と敵国のIDが同じです。");
		

		// 数値型検証
		//check.numberTypeCheck(id, "数値型検証:ID");
		//check.numberTypeCheck(my_country_id, "数値型検証:自国ID");
		//check.numberTypeCheck(enemy_country_id, "数値型検証:敵国ID");
		
		// 数値範囲検証 必要性?
		//check.rangeCheck(ranking, 100, 1, "数値範囲検証:ランキング");

		// 日付型検証
		//check.dateTypeCheck(kickoff, "日付型検証:試合開始時間");
		
		// 正規表現検証
		//check.regExCheck(mail, "^[a-zA-Z0-9_+-]+(.[a-zA-Z0-9_+-]+)*@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,}$", "正規表現検証:メールアドレス");
		check.regExCheck(kickoff,"20[12][0-9][-]([0][0-9]|[1][0-2])[-]([0][1-9]|[12][0-9]|[3][01])\s([01][0-9]|[2][0-4])[:][0-5][0-9][:][0-5][0-9]","正規表現検証:試合開始時間");
		
		// エラーの有無を確認
		if (check.hasErrors()) {
			// エラーメッセージを取得
			List<String> err = check.getError();
			request.setAttribute("errMsg", err);

			RequestDispatcher rd = request.getRequestDispatcher("/SoccerListServlet");
			rd.forward(request, response);
			
		} else {
			
			request.setAttribute("pairings","pairings" );
			RequestDispatcher rd = request.getRequestDispatcher("/SoccerInsertServlet");
			rd.forward(request, response);
		}

	}

}
