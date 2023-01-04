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

public class UpdateGoalsDAO {

	public List<Goals> UpdateGoals(String id, String pairing_id, String player_id, String goal_time){
		List<Goals> list = new ArrayList<>();
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Soccerjsp");

			try (Connection db = ds.getConnection()) {

				String[] playerSet = {pairing_id,player_id,goal_time};

				String sql = UpdateSort(playerSet);
				
				sql += " where id = ?";

				PreparedStatement ps = db.prepareStatement(sql);

				int idx = 0;

				for (int i = 0; i < playerSet.length; i++) {
					if (playerSet[i] != null && playerSet[i] != "") {
						idx++;
						ps.setString(idx, playerSet[i]);
					}
				}
				
			    ps.setString(idx+1, id);
				
				ps.executeUpdate();
				
				sql ="SELECT * from goals WHERE id ='"+id+"'" ;
				
				ps = db.prepareStatement(sql);
             
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					
					Goals goal=new Goals();

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

	static String UpdateSort(String[] soccer) {
		String[] player = {"pairing_id","player_id","goal_time" };
		
		String sql = "UPDATE  goals set ";

		for (int i = 0; i < soccer.length; i++) {
			if (soccer[i] != null && soccer[i] != "") {
				sql += player[i]+" = ?,";
				
			}
		}
		
		   sql=sql.substring(0,sql.length()-1);
		

		return sql;
	}
}
