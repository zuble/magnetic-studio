/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Legion
 */
public class DBDataChallTest {
    
    @BeforeAll
    public static void setUpClass() throws SQLException {
        DBCommunication.DBConnect("up201605740","pNfL6LkiT");
    }
    
    @AfterAll
    public static void tearDownClass() throws SQLException {
        DBCommunication.DBDisconnect();
    
    }
    
    @BeforeEach
    public void setUp() {
        DBDataUser userdata = new DBDataUser();
        userdata.setUsername("root");
        userdata.doRegistration("root", "12345");
        userdata.updateUserQuest("mind");
    }
    
    @AfterEach
    public void tearDown() {
        DBDataUser userdata = new DBDataUser();
        userdata.deleteUserAcc();
    }

    /**
     * Test of getNumberOfChalls method, of class DBDataChall.
     */
    @Test
    public void testGetNumberOfChalls() {
        System.out.println("getNumberOfChalls");
        DBDataChall challdata = new DBDataChall();
        int expected = 30;
        int real = challdata.getNumberOfChalls();
        
        assertEquals(expected, real);
    }

    /**
     * Test of getChallID method, of class DBDataChall.
     */
    @Test
    public void testGetChallID() {
        System.out.println("getChallID");
        int inventoryChallID = 1;
        DBDataChall challdata = new DBDataChall();
        int expected = 1;
        int real = challdata.getChallID(inventoryChallID);
        
        assertEquals(expected, real);
    }

    /**
     * Test of getChallTextbyID method, of class DBDataChall.
     */
    @Test
    public void testGetChallTextbyID() {
        System.out.println("getChallTextbyID");
        int challID = 1;
        DBDataChall challdata = new DBDataChall();
        String expected = "To understand a bit about how remembering works, consider the “telephone game”. Over time the message passed on the game can become very different from the original. Here is your mission: get with your family or friends and talk about some event that you all experienced together then check how many different versions of the same event will there be. ";
        String real = challdata.getChallTextbyID(challID);
        
        assertEquals(expected, real);
    }

    /**
     * Test of anyChallDone method, of class DBDataChall.
     */
    @Test
    public void testAnyChallDone() {
        System.out.println("anyChallDone");
        DBDataChall challdata = new DBDataChall();
        boolean expected = false;
        boolean real = challdata.anyChallDone();
        
        assertEquals(expected, real);
    }
    
}
