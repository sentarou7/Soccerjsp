package to.msn.wings.Soccerjsp;


import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SoccerInsertServlet")
public class SoccerInsertServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if(request.getParameter("players") != null) {

		    InsertPlayersDAO dao = new InsertPlayersDAO();
     
		    List<Players> list = dao.InsertPlayers(
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
		    
            List<Integer> Id = PlayersDAO.Players_Country_id();
            
            request.setAttribute("Id",Id);
            
            List<String> name = PlayersDAO.Players_Country_Name();
            
            request.setAttribute("Name",name);
            
            request.setAttribute("result", "ok");
		    

		    this.getServletContext().getRequestDispatcher("/Soccer_form_Players.jsp").forward(request, response);
	    }
		
	    if(request.getParameter("countries") != null) {
	    	InsertCountriesDAO dao = new InsertCountriesDAO();
	    	
	    	List<Countries> list = dao.InsertCountries(
	    			               request.getParameter("id"),
	                               request.getParameter("name"),
	                               request.getParameter("ranking"),
	                               request.getParameter("group_name")
	                               );
	    	
	    	 List<Countries> lists=  new ListBoxDAO().Country_list();
	    	 
		    request.setAttribute("Country_list", lists);
		    
	    	request.setAttribute("list", list);
	    	
	    	request.setAttribute("result", "ok");
	    	
	    	this.getServletContext().getRequestDispatcher("/Soccer_form_Countries.jsp").forward(request, response);
	    }
	
	    if(request.getParameter("goals") != null) {
	    	InsertGoalsDAO dao = new InsertGoalsDAO();
	    	
	    	List<Goals> list = dao.InsertGoals(
	    			               request.getParameter("id"),
	                               request.getParameter("pairing_id"),
	                               request.getParameter("player_id"),
	                               request.getParameter("goal_time")
	                               );
	    	
	    	request.setAttribute("list", list);
	    	
	    	List<Goals> lists=  new ListBoxDAO().Goal_list();
	    	 
		    request.setAttribute("Goal_list", lists);
	    	
		    List<Integer> Pairing_Id = GoalsDAO.Goals_pairings_id();
            
            request.setAttribute("Pairing_Id",Pairing_Id);
            
            List<Integer> Player_Id = GoalsDAO.Goals_players_id();
            
            request.setAttribute("Player_Id", Player_Id);
            
            List<String> Player_Name = GoalsDAO.Goals_players_name();
            
            request.setAttribute("Player_Name", Player_Name);
            
	    	request.setAttribute("result", "ok");
	    	
	    	this.getServletContext().getRequestDispatcher("/Soccer_form_Goals.jsp").forward(request, response);
	    }
	
	    if(request.getParameter("pairings") != null) {
	    	InsertPairingsDAO dao = new InsertPairingsDAO();
	    	
	    	List<Pairings> list = dao.InsertPairings(
	    			               request.getParameter("id"),
	                               request.getParameter("kickoff"),
	                               request.getParameter("my_country_id"),
	                               request.getParameter("enemy_country_id")
	                               );
	    	
	    	request.setAttribute("list", list);
	    	
	    	List<Pairings> lists=  new ListBoxDAO().Pairing_list();
	    	 
		    request.setAttribute("Pairing_list", lists);
		    
		    List<Integer> Id = PlayersDAO.Players_Country_id();
            
            request.setAttribute("Id",Id);
            
            List<String> name = PlayersDAO.Players_Country_Name();
            
            request.setAttribute("Name",name);
	    	
	    	request.setAttribute("result", "ok");
	    	
	    	this.getServletContext().getRequestDispatcher("/Soccer_form_Pairings.jsp").forward(request, response);
	    }
	    
	}
}
