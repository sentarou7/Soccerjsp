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

import to.msn.wings.Soccerjsp.Pairings;

public class SelectPairingsDAO {

	public List<Pairings> selectPairings(String id,String kickoff,String my_country_id,String enemy_country_id) {
		List<Pairings> list = new ArrayList<>();
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Soccerjsp");

			try (Connection db = ds.getConnection()) {

				String[] pairingSet = {id,kickoff,my_country_id,enemy_country_id};

				String sql = selectSort(pairingSet);

				PreparedStatement ps = db.prepareStatement(sql);

				int idx = 0;

				for (int i = 0; i < pairingSet.length; i++) {
					if (pairingSet[i] != null && pairingSet[i] != "") {
						idx++;
						ps.setString(idx, pairingSet[i]);
					}
				}

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

	static String selectSort(String[] soccer) {

		String sql = "SELECT * FROM pairings WHERE 0 = 0 ";

		for (int i = 0; i < soccer.length; i++) {
			if (soccer[i] != null && soccer[i] != "") {
				if (i == 0) {
					sql += "AND id =  ? ";
				}
				if (i == 1) {
					sql += "AND kickoff = ? ";
				}
				if (i == 2) {
					sql += "AND my_country_id =  ? ";
				}

				if (i == 3) {
					sql += "AND enemy_country_id =  ?";
				}

			}
		}

		return sql;
	}
}
