package to.msn.wings.Soccerjsp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserNameDAO {

	public static String UserName(String id) {
		String user_name="";
		
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Soccerjsp");

			try (Connection db = ds.getConnection()) {
				String sql ="select unam from usr where uid = "+id;

				PreparedStatement ps = db.prepareStatement(sql);

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
				    user_name = rs.getString("unam");
				}

			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return user_name;

	}
}
