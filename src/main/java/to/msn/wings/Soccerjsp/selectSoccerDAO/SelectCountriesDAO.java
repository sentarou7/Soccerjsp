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

import to.msn.wings.Soccerjsp.Countries;

public class SelectCountriesDAO {

	public List<Countries> selectCountries(String id,String name,String ranking,String group_name) {
		List<Countries> list = new ArrayList<>();
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Soccerjsp");

			try (Connection db = ds.getConnection()) {

				String[] countrySet = {id,name,ranking,group_name};

				String sql = selectSort(countrySet);

				PreparedStatement ps = db.prepareStatement(sql);

				int idx = 0;

				for (int i = 0; i < countrySet.length; i++) {
					if (countrySet[i] != null && countrySet[i] != "") {
						idx++;
						ps.setString(idx, countrySet[i]);
					}
				}

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

	static String selectSort(String[] soccer) {

		String sql = "SELECT * FROM countries WHERE 0 = 0 ";

		for (int i = 0; i < soccer.length; i++) {
			if (soccer[i] != null && soccer[i] != "") {
				if (i == 0) {
					sql += "AND id =  ? ";
				}
				if (i == 1) {
					sql += "AND name = ? ";
				}
				if (i == 2) {
					sql += "AND ranking =  ? ";
				}

				if (i == 3) {
					sql += "AND group_name =  ?";
				}

			}
		}

		return sql;
	}
}
