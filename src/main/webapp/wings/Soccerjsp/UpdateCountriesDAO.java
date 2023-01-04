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

public class UpdateCountriesDAO {

	public List<Countries> UpdateCountries(String id, String name, String ranking, String group_name){
		List<Countries> list = new ArrayList<>();
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Soccerjsp");

			try (Connection db = ds.getConnection()) {

				String[] playerSet = {name,ranking,group_name};

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
				
				sql ="SELECT * from countries WHERE id ='"+id+"'" ;
				
				ps = db.prepareStatement(sql);
             
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

	static String UpdateSort(String[] soccer) {
		String[] player = {"name","ranking","group_name" };
		
		String sql = "UPDATE  countries set ";

		for (int i = 0; i < soccer.length; i++) {
			if (soccer[i] != null && soccer[i] != "") {
				sql += player[i]+" = ?,";
				
			}
		}
		
		   sql=sql.substring(0,sql.length()-1);
		

		return sql;
	}
}
