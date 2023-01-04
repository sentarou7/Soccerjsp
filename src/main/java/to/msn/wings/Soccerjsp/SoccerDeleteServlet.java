package to.msn.wings.Soccerjsp;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SoccerDeleteServlet")
public class SoccerDeleteServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		DeleteDAO dao = new DeleteDAO();
		
		if(request.getParameter("player") != null) {
      
		    dao.DeleteData(request.getParameter("id"),0);

		    request.setAttribute("result", "OK");
		    this.getServletContext().getRequestDispatcher("/Soccer_form_Update_Players.jsp").forward(request, response);
	    }
		
		if(request.getParameter("country") != null) {
	    	
	    	dao.DeleteData(request.getParameter("id"),1);
	    	
	    	request.setAttribute("result","OK");
	    	this.getServletContext().getRequestDispatcher("/Soccer_form_Update_Countries.jsp").forward(request, response);
	    }
		
		if(request.getParameter("goal") != null) {
            dao.DeleteData(request.getParameter("id"),2);
	    	
	    	request.setAttribute("result","OK");
	    	
	    	this.getServletContext().getRequestDispatcher("/Soccer_form_Update_Goals.jsp").forward(request, response);
	    }
		
		if(request.getParameter("pairings") != null) {
            dao.DeleteData(request.getParameter("id"),3);
	    	
	    	request.setAttribute("result","OK");
	    	
	    	this.getServletContext().getRequestDispatcher("/Soccer_form_Update_Pairings.jsp").forward(request, response);
	    }
	}
}
