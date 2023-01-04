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

public class UpdatePlayersDAO {

	public List<Players> UpdatePlayers(String id, String country_id, String uniform_num, String position, String name,
			String club, String birth, String height, String weight) {
		List<Players> list = new ArrayList<>();
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Soccerjsp");

			try (Connection db = ds.getConnection()) {

				String[] playerSet = {country_id, uniform_num, position, name, club, birth, height, weight };

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
				
				sql ="SELECT * from players WHERE id ='"+id+"'" ;
				
				ps = db.prepareStatement(sql);
             
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

	static String UpdateSort(String[] soccer) {
		String[] player = {"country_id", "uniform_num", "position", "name", "club","birth", "height", "weight" };
		
		String sql = "UPDATE  players set ";

		for (int i = 0; i < soccer.length; i++) {
			if (soccer[i] != null && soccer[i] != "") {
				sql += player[i]+" = ?,";
				
			}
		}
		
		   sql=sql.substring(0,sql.length()-1);
		

		return sql;
	}
}
