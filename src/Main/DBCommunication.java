package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Zublé
 */
public class DBCommunication {
    
    public static Connection plugConn = null;
    
    /**
     * -connect to the data base 
     * @param up
     * @param password
     * @throws SQLException
     */
    @SuppressWarnings({"CallToPrintStackTrace", "UseSpecificCatch"})
    public static void DBConnect(String up , String password) throws SQLException {
        try {
            plugConn = DriverManager.getConnection("jdbc:mariadb://db.fe.up.pt:3306/"+up ,up , password);
            System.out.println("CONNECTION ESTABLISHED");
        }
        catch (Exception exc) {
            exc.printStackTrace();
            System.out.println("ERRORS ESTABLISHING THE CONNECTION");
        }
    }
    
    /**
    * -disconnect from the data base
    * @throws SQLException
    */
    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public static void DBDisconnect() throws SQLException {
        try{
            plugConn.close();
            System.out.println("CONNECTION CLOSED");
        }
        catch (Exception exc) {
            exc.printStackTrace();
            System.out.println("ERRORS CLOSING THE CONNECTION");
        }
    }
    
    /**
     * 
     * @return plugConn
     */    
    public static Connection getPlugVariable(){
        return plugConn;
    }
    
        
}
	