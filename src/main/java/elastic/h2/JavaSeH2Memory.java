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

public class JavaSeH2Memory {

	@CaptureTransaction
    public static void main(String[] args) {

        String url = "jdbc:h2:mem:";

        try (Connection con = DriverManager.getConnection(url);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT 1+1")) {

            if (rs.next()) {
            
                System.out.println(rs.getInt(1));
            }
            
            dummy();

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JavaSeH2Memory.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
	
	@CaptureSpan
	private static void dummy() {
		
	}
}