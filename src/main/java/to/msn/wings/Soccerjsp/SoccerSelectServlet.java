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
		
		if(request.getParameter("players") != null) {

			SelectPlayersDAO dao = new SelectPlayersDAO();
			
			//textboxに入力した値を残すための変数(jspから受け取ってjspに送る)
			String name = request.getParameter("name");
			String height = request.getParameter("height");
			String birth = request.getParameter("birth");
			String uniform_num = request.getParameter("uniform_num");
			String position = request.getParameter("position");
			String club = request.getParameter("club");
			String weight = request.getParameter("weight");
			
			request.setAttribute("name",name);
			request.setAttribute("height",height);
			request.setAttribute("birth",birth);
			request.setAttribute("uniform_num",uniform_num);
			request.setAttribute("position",position);
			request.setAttribute("club",club);
			request.setAttribute("weight",weight); 
     
		    List<Players> list = dao.selectPlayers(
				                 request.getParameter("list_country_id"),
				                 request.getParameter("country_id"),
				                 uniform_num,
				                 position,
				                 name,
				                 club,
				                 birth,
				                 height,
				                 weight
				                 );
	    	
	    	request.setAttribute("list",list);
	    	
	    	List<Integer> Id = PlayersDAO.Players_Country_id();
	            
	        request.setAttribute("Id",Id);
	            
	        List<String> Lname = PlayersDAO.Players_Country_Name();
	            
	        request.setAttribute("Name",Lname);
	        
	        
	       // request.setAttribute("dat",request.getAttribute("dat"));
	    	
	        
            //System.out.println("ID:" + request.getParameter("country_id"));
            //System.out.println("NM:" + request.getParameter("country_name"));
            
		    this.getServletContext().getRequestDispatcher("/Soccer_form_Players.jsp").forward(request, response);
	    }
		
	    if(request.getParameter("countries") != null) {
	    	
	    	String get = request.getParameter("actType");
	    	request.setAttribute("actType", get);
	    	
	    	SelectCountriesDAO dao = new SelectCountriesDAO();
	    	
	    	String name = request.getParameter("name");
	    	String ranking = request.getParameter("ranking");
	    	String group_name = request.getParameter("group_name");
	    
	    	request.setAttribute("name", name);
	    	request.setAttribute("ranking",ranking);
	    	request.setAttribute("group_name", group_name);
	    	
	    	List<Countries> list = dao.selectCountries(
	    			               request.getParameter("id"),
	                               name,
	                               ranking,
	                               group_name
	                               );
	    	
	    	
	        List<Countries> List_list = new ListBoxDAO().Country_list();
	    	
	    	request.setAttribute("list",list);
	    	request.setAttribute("Country_list", List_list);
	    	
            if(get != null) {
	    		
	    	    this.doGet(request,response);
	    	
	    	}else {
	    	
	    	request.setAttribute("get",get);
	    	
	    	this.getServletContext().getRequestDispatcher("/Soccer_form_Countries.jsp").forward(request, response);
	    	}
	    }
		
	    if(request.getParameter("goals") != null) {
	    	SelectGoalsDAO dao = new SelectGoalsDAO();
	    	
	    	String pairing_id = request.getParameter("pairing_id");
	    	String player_id = request.getParameter("player_id");
	    	String goal_time = request.getParameter("goal_time");
	    
	    	request.setAttribute("pairing_id", pairing_id);
	    	request.setAttribute("player_id",player_id);
	    	request.setAttribute("goal_time", goal_time);
	    	
	    	List<Goals> list = dao.selectGoals(
	    			               request.getParameter("id"),
	                               pairing_id,
	                               player_id,
	                               goal_time
	                               );
	    	
	    	request.setAttribute("list",list);
	    	
	    	 List<Goals> List_list = new ListBoxDAO().Goal_list();
		     
	    	 request.setAttribute("Goal_list", List_list);

	         List<Integer> Pairing_Id = GoalsDAO.Goals_pairings_id();
	             
	         request.setAttribute("Pairing_Id",Pairing_Id);
	             
	         List<Integer> Player_Id = GoalsDAO.Goals_players_id();
	             
	         request.setAttribute("Player_Id", Player_Id);
	             
	         List<String> Player_Name = GoalsDAO.Goals_players_name();
	             
	         request.setAttribute("Player_Name", Player_Name);
	    	
	    	
	    	this.getServletContext().getRequestDispatcher("/Soccer_form_Goals.jsp").forward(request, response);
	    }
	    
	    if(request.getParameter("pairings") != null) {
	    	SelectPairingsDAO dao = new SelectPairingsDAO();
	    	
	    	String id =request.getParameter("id");
	    	String kickoff = request.getParameter("kickoff");
	    	String my_country_id = request.getParameter("my_country_id");
	    	String enemy_country_id = request.getParameter("enemy_country_id");
	    	String my_country_name = request.getParameter("My_country_name");
	    	String enemy_country_name =request.getParameter("Enemy_country_name");
	    	
	    	request.setAttribute("kickoff",kickoff);
	    	request.setAttribute("my_country_id",my_country_id);
	    	request.setAttribute("enemy_country_id",enemy_country_id);
	    	request.setAttribute("my_country_name",my_country_name);
	    	request.setAttribute("enemy_country_name",enemy_country_name);
	    	
	    	
	    	List<Pairings> list = dao.selectPairings(
	    			               id,
	                               kickoff,
	                               my_country_id,
	                               enemy_country_id
	                               );
	    	request.setAttribute("list",list);
	    	
	        List<Pairings> List_list = new ListBoxDAO().Pairing_list();
		    	
	        request.setAttribute("Pairing_list", List_list);
	        
	        List<Integer> Id = PlayersDAO.Players_Country_id();
            
            request.setAttribute("Id", Id);
            
            List<String> name = PlayersDAO.Players_Country_Name();
            
            request.setAttribute("Name",name);
            
            String get = request.getParameter("actType");
	    	request.setAttribute("actType", get);
	    	
	    	this.getServletContext().getRequestDispatcher("/Soccer_form_Pairings.jsp").forward(request, response);
	    }
	}
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        request.setCharacterEncoding("UTF-8");
	        
	        //request.setAttribute("get", request.getAttribute("get"));
	        String get = request.getParameter("actType");
	    	request.setAttribute("actType", get);
	        
	        this.getServletContext().getRequestDispatcher("/Soccer_form_Countries.jsp").forward(request, response);
	    }
	}
