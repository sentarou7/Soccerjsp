package to.msn.wings.Soccerjsp;


import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SoccerListServlet")
public class SoccerListServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ListBoxDAO dao = new ListBoxDAO();
		
		if(request.getParameter("players") != null) {
			
            List<Players> list = dao.Player_List();
            
            request.setAttribute("Player_list", list);
     
		    this.getServletContext().getRequestDispatcher("/Soccer_form_Players.jsp").forward(request, response);
	    }
		
		if(request.getParameter("countries") != null) {
			
	    	 List<Countries> list=dao.Country_list();
	    	 
	    	request.setAttribute("Country_list", list);
	    	
	    	if(request.getParameter("errMsg") != null) {
	    		request.setAttribute("errMsg", request.getParameter("errMsg"));
	    	}
	    	
	    	this.getServletContext().getRequestDispatcher("/Soccer_form_Countries.jsp").forward(request, response);
	    }
		
		if(request.getParameter("goals") != null) {
			
             List<Goals> list = dao.Goal_list();
	    	
             request.setAttribute("Goal_list",list);
	    	
	    	this.getServletContext().getRequestDispatcher("/Soccer_form_Goals.jsp").forward(request, response);
	    }
		
		if(request.getParameter("pairings") != null) {

			 List<Pairings> list = dao.Pairing_list();
		    	
             request.setAttribute("Pairing_list",list);
	    	
           	this.getServletContext().getRequestDispatcher("/Soccer_form_Pairings.jsp").forward(request, response);
	    }
	}
}
