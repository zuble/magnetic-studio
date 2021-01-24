/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Legion
 */
public class DBCommunicationTest {
    
    /**
     * Test of DBConnect method, of class DBCommunication.
     */
    @Test
    public void testDBConnect(){
        System.out.println("DBConnect");
        String up = "up201605740";
        String password = "pNfL6LkiT";
        try {
            DBCommunication.DBConnect(up, password);
        } catch (SQLException ex) {
            Logger.getLogger(DBCommunicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assert(!(DBCommunication.plugConn == null));
    }
    
}
