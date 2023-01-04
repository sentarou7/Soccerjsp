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

public class GoalsDAO {

	public static List<Integer>Goals_pairings_id() {
		
		List<Integer> list = new ArrayList<Integer>();
		int pairings_id;
		
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Soccerjsp");
			
			try (Connection db = ds.getConnection()) {
				
				String sql="Select distinct pairing_id from goals order by pairing_id";

				PreparedStatement ps = db.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					
					pairings_id = rs.getInt("pairing_id");
				    list.add(pairings_id);
				    
				}
				
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
public static List<Integer>Goals_players_id() {
		
		List<Integer> list = new ArrayList<Integer>();
		int players_id;
		
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Soccerjsp");
			
			try (Connection db = ds.getConnection()) {
				
				String sql="Select id from players";

				PreparedStatement ps = db.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					
					players_id = rs.getInt("id");
				    list.add(players_id);
				    
				}
				
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
public static List<String>Goals_players_name() {
	
	List<String> list = new ArrayList<>();
	String players_name;
	
	try {
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Soccerjsp");
		
		try (Connection db = ds.getConnection()) {
			
			String sql="Select name from players";

			PreparedStatement ps = db.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				players_name = rs.getString("name");
			    list.add(players_name);
			    
			}
			
		}
	} catch (NamingException | SQLException e) {
		e.printStackTrace();
	}

	return list;
}

	
}
