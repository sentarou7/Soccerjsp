package to.msn.wings.Soccerjsp;


import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import to.msn.wings.Soccerjsp.selectSoccerDAO.SelectCountriesDAO;
import to.msn.wings.Soccerjsp.selectSoccerDAO.SelectGoalsDAO;
import to.msn.wings.Soccerjsp.selectSoccerDAO.SelectPairingsDAO;
import to.msn.wings.Soccerjsp.selectSoccerDAO.SelectPlayersDAO;

@WebServlet("/SoccerSelectServlet")
public class SoccerSelectServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if(request.getParameter("player") != null) {

			SelectPlayersDAO dao = new SelectPlayersDAO();
     
		    List<Players> list = dao.selectPlayers(
				                 request.getParameter("id"),
				                 request.getParameter("country_id"),
				                 request.getParameter("uniform_num"),
				                 request.getParameter("position"),
				                 request.getParameter("name"),
				                 request.getParameter("club"),
				                 request.getParameter("birth"),
				                 request.getParameter("height"),
				                 request.getParameter("weight")
				                 );

            List<Players> List_list = new ListBoxDAO().Player_List();
	    	
	    	request.setAttribute("list",list);
	    	request.setAttribute("Player_list", List_list);

		    this.getServletContext().getRequestDispatcher("/Soccer_form_Players.jsp").forward(request, response);
	    }
		
	    if(request.getParameter("countries") != null) {
	    	SelectCountriesDAO dao = new SelectCountriesDAO();
	    	
	    	List<Countries> list = dao.selectCountries(
	    			               request.getParameter("listId"),
	                               request.getParameter("listName"),
	                               request.getParameter("listRanking"),
	                               request.getParameter("listGroup_name")
	                               );
	    	
	        List<Countries> List_list = new ListBoxDAO().Country_list();
	    	
	    	request.setAttribute("list",list);
	    	request.setAttribute("Country_list", List_list);
	    	
	    	this.getServletContext().getRequestDispatcher("/Soccer_form_Countries.jsp").forward(request, response);
	    }
		
	    if(request.getParameter("goals") != null) {
	    	SelectGoalsDAO dao = new SelectGoalsDAO();
	    	
	    	List<Goals> list = dao.selectGoals(
	    			               request.getParameter("id"),
	                               request.getParameter("pairing_id"),
	                               request.getParameter("player_id"),
	                               request.getParameter("goal_time")
	                               );
	    	
	    	 List<Goals> List_list = new ListBoxDAO().Goal_list();
		    	
		    	request.setAttribute("list",list);
		    	request.setAttribute("Goal_list", List_list);
		    	
	    	
	    	
	    	this.getServletContext().getRequestDispatcher("/Soccer_form_Goals.jsp").forward(request, response);
	    }
	    
	    if(request.getParameter("pairings") != null) {
	    	SelectPairingsDAO dao = new SelectPairingsDAO();
	    	
	    	List<Pairings> list = dao.selectPairings(
	    			               request.getParameter("id"),
	                               request.getParameter("kickoff"),
	                               request.getParameter("my_country_id"),
	                               request.getParameter("enemy_country_id")
	                               );
	    	
	    	 List<Pairings> List_list = new ListBoxDAO().Pairing_list();
		    	
		    	request.setAttribute("list",list);
		    	request.setAttribute("Pairing_list", List_list);
		    	
	    	
	    	this.getServletContext().getRequestDispatcher("/Soccer_form_Pairings.jsp").forward(request, response);
	    }
	}
}
