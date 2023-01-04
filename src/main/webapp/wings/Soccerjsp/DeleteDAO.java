package to.msn.wings.Soccerjsp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DeleteDAO {

	public void DeleteData(String id,int number) {
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Soccerjsp");

			try (Connection db = ds.getConnection()) {
				String[] tableName= {"players","countries","goals","pairings"};
				
				String sql="Delete from "+tableName[number]+" where id = "+id;

				PreparedStatement ps = db.prepareStatement(sql);
				
				ps.executeUpdate();
          
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}

	}
}
