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
@WebServlet("/Soccer_Goals_Error")
public class Soccer_Goals_Error extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pairing_id = request.getParameter("pairing_id");
		String player_id = request.getParameter("player_id");
		String goal_time = request.getParameter("goal_time");
		
		request.setAttribute("id", id);
		request.setAttribute("pairing_id", pairing_id);
		request.setAttribute("player_id",player_id);
		request.setAttribute("goal_time", goal_time);
		
		CheckUtil check = new CheckUtil();
		/*// 必須検証
		check.requiredCheck(id, "必須検証:ID");
		check.requiredCheck(pairing_id, "必須検証:試合ID");
		check.requiredCheck(player_id, "必須検証:プレイヤーID");
		
		// 文字列長検証（max文字以内であるか）
		check.lengthCheck(id, 3, "文字列長検証:氏名");
		//check.lengthCheck(group_name, 1, "文字列長検証:グループの区分");
		
		// 重複検証
		//check.duplicateCheck(, "select name from countries where name = ?", "重複検証:氏名");
		check.duplicateCheck(id,"select id from goals where id = ?", "重複検証:ID");
		

		// 数値型検証
		check.numberTypeCheck(id, "数値型検証:ID");
		check.numberTypeCheck(pairing_id, "数値型検証:試合ID");
		
		// 数値範囲検証 必要性?
		//check.rangeCheck(ranking, 100, 1, "数値範囲検証:ランキング");

		// 日付型検証
		//check.dateTypeCheck(group_name, "日付型検証:生年月日");
		
		// 正規表現検証
		//check.regExCheck(mail, "^[a-zA-Z0-9_+-]+(.[a-zA-Z0-9_+-]+)*@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,}$", "正規表現検証:メールアドレス");
		check.regExCheck(goal_time, "[前後]半[0-9]{1,2}分","正規表現検証:ゴールタイム");
		
		
		//アルファベットかどうかの判別
		//check.regExCheck(group_name, "[a-zA-Z]","アルファベットの検証 :グループの区分");
*/
		
		// エラーの有無を確認
		if (check.hasErrors()) {
			// エラーメッセージを取得
			List<String> err = check.getError();
			request.setAttribute("errMsg", err);

			RequestDispatcher rd = request.getRequestDispatcher("/SoccerListServlet");
			rd.forward(request, response);
			
		} else {
			
			request.setAttribute("goals","goals" );
			RequestDispatcher rd = request.getRequestDispatcher("/SoccerInsertServlet");
			rd.forward(request, response);
		}

	}

}
