package to.msn.wings.Soccerjsp;


import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SoccerUpdateServlet")
public class SoccerUpdateServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if(request.getParameter("player") != null) {
            
			UpdatePlayersDAO dao = new UpdatePlayersDAO();
      
		    List<Players> list = dao.UpdatePlayers(
				                 request.getParameter("id"),
				                 request.getParameter("country_id"),
				                 request.getParameter("uniform_num"),
				                 request.getParameter("position"),
				                 request.getParameter("name"),
				                 request.getParameter("club"),
				                 request.getParameter("birth"),
				                 request.getParameter("height"),
				                 request.getParameter("weight"));

		    request.setAttribute("list", list);
		    request.setAttribute("result", "OK");
		    this.getServletContext().getRequestDispatcher("/Soccer_form_Update_Players.jsp").forward(request, response);
	    }
		
		if(request.getParameter("country") != null) {
			UpdateCountriesDAO dao = new UpdateCountriesDAO();
	    	
	    	List<Countries> list = dao.UpdateCountries(
	    			               request.getParameter("id"),
	                               request.getParameter("name"),
	                               request.getParameter("ranking"),
	                               request.getParameter("group_name")
	                               );
	    	
	    	request.setAttribute("list", list);
	    	
	    	this.getServletContext().getRequestDispatcher("/Soccer_form_Update_Countries.jsp").forward(request, response);
	    }
		
		if(request.getParameter("goal") != null) {
			UpdateGoalsDAO dao = new UpdateGoalsDAO();
	    	
	    	List<Goals> list = dao.UpdateGoals(
	    			               request.getParameter("id"),
	                               request.getParameter("pairing_id"),
	                               request.getParameter("player_id"),
	                               request.getParameter("goal_time")
	                               );
	    	
	    	request.setAttribute("list", list);
	    	
	    	this.getServletContext().getRequestDispatcher("/Soccer_form_Update_Goals.jsp").forward(request, response);
	    }
		
		if(request.getParameter("pairings") != null) {
			UpdatePairingsDAO dao = new UpdatePairingsDAO();
	    	
	    	List<Pairings> list = dao.UpdatePairings(
	    			               request.getParameter("id"),
	                               request.getParameter("kickoff"),
	                               request.getParameter("my_country_id"),
	                               request.getParameter("enemy_country_id")
	                               );
	    	
	    	request.setAttribute("list", list);
	    	
	    	this.getServletContext().getRequestDispatcher("/Soccer_form_Update_Pairings.jsp").forward(request, response);
	    }
	}
}
