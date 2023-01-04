package to.msn.wings.Soccerjsp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ListBoxDAO {

	public List<Countries> Country_list() {
		
		 List<Countries> list = new ArrayList<>();
		 
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Soccerjsp");
           
			try (Connection db = ds.getConnection()) {

				PreparedStatement ps = db.prepareStatement("select * from countries");
				
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					Countries country = new Countries();

					country.setId(rs.getInt("id"));
					country.setName(rs.getString("name"));
					country.setRanking(rs.getInt("ranking"));
					country.setGroup_name(rs.getString("group_name"));
					
					list.add(country);
				}

			}
          
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return list;

	}
	
	public List<Players> Player_List() {
		
		 List<Players> list = new ArrayList<>();
		 
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Soccerjsp");
          
			try (Connection db = ds.getConnection()) {

				PreparedStatement ps = db.prepareStatement("select * from Players");
				
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					Players player = new Players();

					player.setId(rs.getInt("id"));
					player.setCountry_id(rs.getInt("country_id"));
					player.setUniform_num(rs.getInt("uniform_num"));
					player.setPosition(rs.getString("position"));
					player.setName(rs.getString("name"));
					player.setClub(rs.getString("club"));
					player.setBirth(rs.getString("birth"));
					player.setHeight(rs.getInt("height"));
					player.setWeight(rs.getInt("weight"));

					list.add(player);
				}

			}
         
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return list;

	}
	
	public List<Goals> Goal_list() {
		
		 List<Goals> list = new ArrayList<>();
		 
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Soccerjsp");
          
			try (Connection db = ds.getConnection()) {

				PreparedStatement ps = db.prepareStatement("select * from Goals");
				
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					Goals goal = new Goals();

					goal.setId(rs.getInt("id"));
					goal.setPairing_id(rs.getInt("pairing_id"));
					goal.setPlayer_id(rs.getInt("player_id"));
					goal.setGoal_time(rs.getString("goal_time"));
					
					list.add(goal);
				}

			}
         
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return list;

	}
	
	public List<Pairings> Pairing_list() {
		
		 List<Pairings> list = new ArrayList<>();
		 
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Soccerjsp");
         
			try (Connection db = ds.getConnection()) {

				PreparedStatement ps = db.prepareStatement("select * from Pairings");
				
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					Pairings pairings = new Pairings();

					pairings.setId(rs.getInt("id"));
					pairings.setKickoff(rs.getString("kickoff"));
					pairings.setMy_country_id(rs.getInt("my_country_id"));
					pairings.setEnemy_country_id(rs.getInt("enemy_country_id"));
					
					list.add(pairings);
				}

			}
        
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return list;

	}
}
