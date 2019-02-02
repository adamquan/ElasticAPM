package elastic.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.elastic.apm.api.CaptureSpan;
import co.elastic.apm.api.CaptureTransaction;

public class JavaSeH2Server {

	@CaptureTransaction
    public static void main(String[] args) {

        String url = "jdbc:h2:tcp://localhost/~/test";
        String user = "sa";
        String passwd = "changeme";

        String query = "SELECT * FROM cars";

        try (Connection con = DriverManager.getConnection(url, user, passwd);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {

                System.out.printf("%d %s %d%n", rs.getInt(1),
                        rs.getString(2), rs.getInt(3));
            }
            
            dummy();

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JavaSeH2Server.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
	
	@CaptureSpan
	private static void dummy() {
		
	}	
}