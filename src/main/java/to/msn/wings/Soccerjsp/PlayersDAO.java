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

public class PlayersDAO {

	public static List<Integer>Players_Country_id() {
		
		List<Integer> list = new ArrayList<Integer>();
		int country_id;
		
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Soccerjsp");
			
			try (Connection db = ds.getConnection()) {
				
				String sql="Select id from countries";

				PreparedStatement ps = db.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					
				    country_id = rs.getInt("id");
				    list.add(country_id);
				    
				}
				
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	public static List<String>Players_Country_Name() {
		
		List<String> list = new ArrayList<String>();
		String country_name;
		
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Soccerjsp");
			
			try (Connection db = ds.getConnection()) {
				
				String sql="Select name from countries";

				PreparedStatement ps = db.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					
				    country_name = rs.getString("name");
				    list.add(country_name);
				    
				}
				
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	
}
