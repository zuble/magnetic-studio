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
public class DBDataUserTest {
    
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
        userdata.setDataUser();
    }
    
    @AfterEach
    public void tearDown() {
        DBDataUser userdata = new DBDataUser();
        userdata.deleteUserAcc();
    }

    /**
     * Test of getUsername method, of class DBDataUser.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        DBDataUser userdata = new DBDataUser();
        String expected = "root";
        String real = userdata.getUsername();
        
        assertEquals(expected, real);
    }

    
    /**
     * Test of getUserClass method, of class DBDataUser.
     */
    @Test
    public void testGetUserClass() {
        System.out.println("getUserClass");
        DBDataUser userdata = new DBDataUser();
        String expected = "class";
        String real = userdata.getUserClass();
        
        assertEquals(expected, real);
    }

    /**
     * Test of getUserQuest method, of class DBDataUser.
     */
    @Test
    public void testGetUserQuest() {
        System.out.println("getUserQuest");
        DBDataUser userdata = new DBDataUser();
        String expected = "quest";
        String real = userdata.getUserQuest();
        
        assertEquals(expected, real);
    }

    /**
     * Test of getUserWallet method, of class DBDataUser.
     */
    @Test
    public void testGetUserWallet() {
        System.out.println("getUserWallet");
        DBDataUser userdata = new DBDataUser();
        int expected = 0;
        int real = userdata.getUserWallet();
        
        assertEquals(expected, real);
    }

    /**
     * Test of getUserWisdom method, of class DBDataUser.
     */
    @Test
    public void testGetUserWisdom() {
        System.out.println("getUserWisdom");
        DBDataUser userdata = new DBDataUser();
        int expected = 0;
        int real = userdata.getUserWisdom();
        
        assertEquals(expected, real);
    }

    /**
     * Test of loginVerification method, of class DBDataUser.
     */
    @Test
    public void testLoginVerification() {
        System.out.println("loginVerification");
        String password = "12345";
        DBDataUser userdata = new DBDataUser();
        boolean expected = true;
        boolean real = userdata.loginVerification(password);
        
        assertEquals(expected, real);
    }

    /**
     * Test of updateUserWallet method, of class DBDataUser.
     */
    @Test
    public void testUpdateUserWallet() {
        System.out.println("updateUserWallet");
        int value = 10;
        String operation = "plus";
        DBDataUser userdata = new DBDataUser();
        userdata.updateUserWallet(value, operation);
        
        assertEquals(userdata.getUserWallet(),10);
    }

    /**
     * Test of updateUserWisdom method, of class DBDataUser.
     */
    @Test
    public void testUpdateUserWisdom() {
        System.out.println("updateUserWisdom");
        int Chall_wisdom_value = 10;
        DBDataUser userdata = new DBDataUser();
        userdata.updateUserWisdom(Chall_wisdom_value);
        
        assertEquals(userdata.getUserWisdom(),10);
    }

    /**
     * Test of updateUserQuest method, of class DBDataUser.
     */
    @Test
    public void testUpdateUserQuest() {
        System.out.println("updateUserQuest");
        String Quest = "mind";
        DBDataUser userdata = new DBDataUser();
        userdata.updateUserQuest(Quest);
        
        assertEquals(userdata.getUserQuest(),Quest);
    }

    /**
     * Test of updateUserClass method, of class DBDataUser.
     */
    @Test
    public void testUpdateUserClass() {
        System.out.println("updateUserClass");
        String Class = "healer";
        DBDataUser userdata = new DBDataUser();
        userdata.updateUserClass(Class);
        
        assertEquals(userdata.getUserQuest(),Class);
    }
    
}
