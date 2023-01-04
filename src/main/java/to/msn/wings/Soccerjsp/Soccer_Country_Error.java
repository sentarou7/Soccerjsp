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
@WebServlet("/Soccer_Country_Error")
public class Soccer_Country_Error extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String ranking = request.getParameter("ranking");
		String group_name = request.getParameter("group_name");
		
		request.setAttribute("id", id);
		request.setAttribute("name", name);
		request.setAttribute("ranking",ranking);
		request.setAttribute("group_name", group_name);
		
		CheckUtil check = new CheckUtil();
		// 必須検証
		//check.requiredCheck(id, "必須検証:ID");
		check.requiredCheck(name, "必須検証:国名");
		
		// 文字列長検証（max文字以内であるか）
		check.lengthCheck(name, 50, "文字列長検証:氏名");
		check.lengthCheck(group_name, 1, "文字列長検証:グループの区分");
		
		// 重複検証
		//check.duplicateCheck(name, "select name from countries where name = ?", "重複検証:氏名");
		//check.duplicateCheck(id,"select id from countries where id = ?", "重複検証:ID");
		

		// 数値型検証
		//check.numberTypeCheck(id, "数値型検証:ID");
		check.numberTypeCheck(ranking, "数値型検証:ランキング");

		// 日付型検証
		//check.dateTypeCheck(group_name, "日付型検証:生年月日");
		
		// 正規表現検証
		//check.regExCheck(mail, "^[a-zA-Z0-9_+-]+(.[a-zA-Z0-9_+-]+)*@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,}$", "正規表現検証:メールアドレス");
		
		//アルファベットかどうかの判別
		check.regExCheck(group_name, "[a-zA-Z]","アルファベットの検証 :グループの区分");

		
		// エラーの有無を確認
		if (check.hasErrors()) {
			// エラーメッセージを取得
			List<String> err = check.getError();
			request.setAttribute("errMsg", err);

			RequestDispatcher rd = request.getRequestDispatcher("/SoccerListServlet");
			rd.forward(request, response);
			
		} else {
			
			request.setAttribute("countries","countries" );
			RequestDispatcher rd = request.getRequestDispatcher("/SoccerInsertServlet");
			rd.forward(request, response);
		}

	}

}
