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

public class InsertPairingsDAO {

	public List<Pairings> InsertPairings(String id, String kickoff, String my_country_id, String enemy_country_id) {
		List<Pairings> list = new ArrayList<>();
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Soccerjsp");

			try (Connection db = ds.getConnection()) {

				String[] playerSet = {id,kickoff, my_country_id, enemy_country_id };

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
				
				sql ="SELECT * FROM pairings WHERE id=(SELECT max(id) FROM pairings)" ;
				
				ps = db.prepareStatement(sql);
             
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

	static String insertSort(String[] soccer) {

		String sql = "INSERT  pairings set ";

		for (int i = 0; i < soccer.length; i++) {
			if (soccer[i] != null && soccer[i] != "") {
				
				if (i == 0) {
					sql += "id =  ?,";
				}
				if (i == 1) {
					sql += "kickoff  =  ?,";
				}
				if (i == 2) {
					sql += "my_country_id = ?,";
				}

				if (i == 3) {
					sql += "enemy_country_id =  ?,";
				}

			}
			}
		
		    sql=sql.substring(0,sql.length()-1);
		

		return sql;
	}
}
