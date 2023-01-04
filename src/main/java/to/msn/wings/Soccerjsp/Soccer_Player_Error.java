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
@WebServlet("/Soccer_Player_Error")
public class Soccer_Player_Error extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String country_id = request.getParameter("country_id");
		String uniform_num = request.getParameter("uniform_num");
		String position = request.getParameter("position");
		String name = request.getParameter("name");
		String club = request.getParameter("club");
		String birth = request.getParameter("birth");
		String height = request.getParameter("height");
		String weight = request.getParameter("weight");
		
		request.setAttribute("id", id);
		request.setAttribute("country_id",country_id );
		request.setAttribute("uniform_num",uniform_num);
		request.setAttribute("position", position);
		request.setAttribute("name",name);
		request.setAttribute("club",club);
		request.setAttribute("birth",birth);
		request.setAttribute("height",height);
		request.setAttribute("weight",weight);
		
		CheckUtil check = new CheckUtil();
		// 必須検証
		check.requiredCheck(name, "必須検証:氏名");
		//check.requiredCheck(id, "必須検証:ID");
		
		// 文字列長検証（max文字以内であるか）
		check.lengthCheck(name, 50, "文字列長検証:氏名");
		check.lengthCheck(position, 2, "文字列長検証:ポジション");
		check.lengthCheck(club, 50, "文字列長検証:クラブ");
		
		// 重複検証
		//check.duplicateCheck(name, "select name from players where name = ?", "重複検証:氏名");
		//check.duplicateCheck(id,"select id from players where id = ?", "重複検証:ID");
		

		// 数値型検証
		//check.numberTypeCheck(id, "数値型検証:ID");
		//check.numberTypeCheck(country_id, "数値型検証:国ID");
		check.numberTypeCheck(uniform_num, "数値型検証:背番号");
		check.numberTypeCheck(height, "数値型検証:身長");
		check.numberTypeCheck(weight, "数値型検証:体重");
		
		//文字型検証
		//check.StringTypeCheck(club,"文字検証:クラブ");
		
		// 数値範囲検証 必要性?
		//check.rangeCheck(ranking, 100, 1, "数値範囲検証:ランキング");

		// 日付型検証
		check.dateTypeCheck(birth, "日付型検証:生年月日");
		
		// 正規表現検証
		//check.regExCheck(mail, "^[a-zA-Z0-9_+-]+(.[a-zA-Z0-9_+-]+)*@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,}$", "正規表現検証:メールアドレス");
		
		//アルファベットかどうかの判別
		//check.regExCheck(position, "[FW] | [GK] | [DF] | [MF]","アルファベットの検証 :ポジション");

		
		// エラーの有無を確認
		if (check.hasErrors()) {
			// エラーメッセージを取得
			List<String> err = check.getError();
			request.setAttribute("errMsg", err);

			RequestDispatcher rd = request.getRequestDispatcher("/SoccerListServlet");
			rd.forward(request, response);
			
		} else {
			
			request.setAttribute("players","players" );
			
			RequestDispatcher rd = request.getRequestDispatcher("/SoccerInsertServlet");
			rd.forward(request, response);
		}

	}

}
