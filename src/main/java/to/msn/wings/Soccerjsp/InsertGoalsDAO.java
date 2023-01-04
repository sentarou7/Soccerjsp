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

public class InsertGoalsDAO {

	public List<Goals> InsertGoals(String id, String pairing_id, String player_id, String goal_time) {
		List<Goals> list = new ArrayList<>();
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Soccerjsp");

			try (Connection db = ds.getConnection()) {

				String[] playerSet = {id, pairing_id, player_id, goal_time };

				String sql = insertSort(playerSet);

				PreparedStatement ps = db.prepareStatement(sql);

				int idx = 0;

				for (int i = 0; i < playerSet.length; i++) {
					if (playerSet[i] != null && playerSet[i] != "") {
						idx++;
						ps.setString(idx, playerSet[i]);
					}
				}
				
				ps.executeUpdate();
				
				sql ="SELECT * FROM goals WHERE id=(SELECT max(id) FROM goals)" ;
				
				ps = db.prepareStatement(sql);
             
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

	static String insertSort(String[] soccer) {

		String sql = "INSERT  goals set ";

		for (int i = 0; i < soccer.length; i++) {
			if (soccer[i] != null && soccer[i] != "") {
				
				if (i == 0) {
					sql += "id =  ?,";
				}
				if (i == 1) {
					sql += "pairing_id  =  ?,";
				}
				if (i == 2) {
					sql += "player_id = ?,";
				}

				if (i == 3) {
					sql += "goal_time =  ?,";
				}

			}
			}
		
		    sql=sql.substring(0,sql.length()-1);
		

		return sql;
	}
}
