package to.msn.wings.Soccerjsp.selectSoccerDAO;

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

import to.msn.wings.Soccerjsp.Goals;

public class SelectGoalsDAO {

	public List<Goals> selectGoals(String id,String pairing_id,String player_id,String goal_time) {
		List<Goals> list = new ArrayList<>();
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Soccerjsp");

			try (Connection db = ds.getConnection()) {

				String[] goalSet = {id,pairing_id,player_id,goal_time};

				String sql = selectSort(goalSet);

				PreparedStatement ps = db.prepareStatement(sql);

				int idx = 0;

				for (int i = 0; i < goalSet.length; i++) {
					if (goalSet[i] != null && goalSet[i] != "") {
						idx++;
						ps.setString(idx, goalSet[i]);
					}
				}

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

	static String selectSort(String[] soccer) {

		String sql = "SELECT * FROM goals WHERE 0 = 0 ";

		for (int i = 0; i < soccer.length; i++) {
			if (soccer[i] != null && soccer[i] != "") {
				if (i == 0) {
					sql += "AND id =  ? ";
				}
				if (i == 1) {
					sql += "AND pairing_id = ? ";
				}
				if (i == 2) {
					sql += "AND player_id =  ? ";
				}

				if (i == 3) {
					sql += "AND goal_time =  ?";
				}

			}
		}

		return sql;
	}
}
