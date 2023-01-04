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
	ListBoxDAO dao = new ListBoxDAO();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//ListBoxDAO dao = new ListBoxDAO();
		
		if(request.getParameter("players") != null) {
			            
            List<Integer> Id = PlayersDAO.Players_Country_id();
            
            request.setAttribute("Id",Id);
            
            List<String> name = PlayersDAO.Players_Country_Name();
            
            request.setAttribute("Name",name);
            
            if(request.getParameter("errMsg") != null) {
	    		request.setAttribute("errMsg", request.getParameter("errMsg"));
	    	}
            
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
             
             List<Integer> Pairing_Id = GoalsDAO.Goals_pairings_id();
             
             request.setAttribute("Pairing_Id",Pairing_Id);
             
             List<Integer> Player_Id = GoalsDAO.Goals_players_id();
             
             request.setAttribute("Player_Id", Player_Id);
             
             List<String> Player_Name = GoalsDAO.Goals_players_name();
             
             request.setAttribute("Player_Name", Player_Name);
	    	
	    	this.getServletContext().getRequestDispatcher("/Soccer_form_Goals.jsp").forward(request, response);
	    }
		
		if(request.getParameter("pairings") != null) {

			 List<Pairings> list = dao.Pairing_list();
		    	
             request.setAttribute("Pairing_list",list);
             
             List<Integer> Id = PlayersDAO.Players_Country_id();
             
             request.setAttribute("Id", Id);
             
             List<String> name = PlayersDAO.Players_Country_Name();
             
             request.setAttribute("Name",name);
	    	
           	this.getServletContext().getRequestDispatcher("/Soccer_form_Pairings.jsp").forward(request, response);
	    }
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("players") != null) {
            
            List<Integer> Id = PlayersDAO.Players_Country_id();
            
            request.setAttribute("Id",Id);
            
            List<String> name = PlayersDAO.Players_Country_Name();
            
            request.setAttribute("Name",name);
            
            if(request.getParameter("errMsg") != null) {
	    		request.setAttribute("errMsg", request.getParameter("errMsg"));
	    	}
            
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
             
             List<Integer> Pairing_Id = GoalsDAO.Goals_pairings_id();
             
             request.setAttribute("Pairing_Id",Pairing_Id);
             
             List<Integer> Player_Id = GoalsDAO.Goals_players_id();
             
             request.setAttribute("Player_Id", Player_Id);
             
             List<String> Player_Name = GoalsDAO.Goals_players_name();
             
             request.setAttribute("Player_Name", Player_Name);
	    	
	    	this.getServletContext().getRequestDispatcher("/Soccer_form_Goals.jsp").forward(request, response);
	    }
		
		if(request.getParameter("pairings") != null) {

			 List<Pairings> list = dao.Pairing_list();
		    	
             request.setAttribute("Pairing_list",list);
             
             List<Integer> Id = PlayersDAO.Players_Country_id();
             
             request.setAttribute("Id", Id);
             
             List<String> name = PlayersDAO.Players_Country_Name();
             
             request.setAttribute("Name",name);
	    	
           	this.getServletContext().getRequestDispatcher("/Soccer_form_Pairings.jsp").forward(request, response);
	    }
	}
	
	
}
