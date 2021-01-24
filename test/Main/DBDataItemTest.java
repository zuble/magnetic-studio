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
public class DBDataItemTest {
    
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
        
        DBDataItem itemdata = new DBDataItem();
        itemdata.getDataItem(1);
    }
    
    @AfterEach
    public void tearDown() {
        DBDataUser userdata = new DBDataUser();
        userdata.deleteUserAcc();
    }

    /**
     * Test of getItemID method, of class DBDataItem.
     */
    @Test
    public void testGetItemID() {
        System.out.println("getItemID");
        DBDataItem itemdata = new DBDataItem();
        int expected = 1;
        int real = itemdata.getItemID();
        
        assertEquals(expected, real);
    }

    /**
     * Test of getItemName method, of class DBDataItem.
     */
    @Test
    public void testGetItemName() {
        System.out.println("getItemName");
        DBDataItem itemdata = new DBDataItem();
        String expected = "Ocapse Blood Rubi";
        String real = itemdata.getItemName();
        
        assertEquals(expected, real);
    }

    /**
     * Test of getItemInfo method, of class DBDataItem.
     */
    @Test
    public void testGetItemInfo() {
        System.out.println("getItemInfo");
        DBDataItem itemdata = new DBDataItem();
        String expected = "This is straight made from one wound that my dragon, Ocapse, got from one of our adventures. This amulet really has a powerful energy within! Each challenge will give you more 5 points of wisdom.";
        String real = itemdata.getItemInfo();
        
        assertEquals(expected, real);
    }

    /**
     * Test of getItemPrice method, of class DBDataItem.
     */
    @Test
    public void testGetItemPrice() {
        System.out.println("getItemPrice");
        DBDataItem itemdata = new DBDataItem();
        int expected = 30;
        int real = itemdata.getItemPrice();
        
        assertEquals(expected, real);
    }

    /**
     * Test of getNumberOfItems method, of class DBDataItem.
     */
    @Test
    public void testGetNumberOfItems() {
        System.out.println("getNumberOfItems");
        DBDataItem itemdata = new DBDataItem();
        int expected = 4;
        int real = itemdata.getNumberOfItems();
        
        assertEquals(expected, real);
    }

    /**
     * Test of isItemInBackpack method, of class DBDataItem.
     */
    @Test
    public void testIsItemInBackpack() {
        System.out.println("isItemInBackpack");
        int itemID = 1;
        DBDataItem itemdata = new DBDataItem();
        itemdata.insertItemBackpack(itemID);
        
        boolean expected = true;
        boolean real = itemdata.isItemInBackpack(itemID);
        
        assertEquals(expected, real);
    }

    /**
     * Test of areItemsInBackpack method, of class DBDataItem.
     */
    @Test
    public void testAreItemsInBackpack() {
        System.out.println("areItemsInBackpack");
        boolean[] ItemsBackpack = {false,false,false,false};
        DBDataItem itemdata = new DBDataItem();
        boolean[] expected = {false,false,false,false};
        boolean[] real = itemdata.areItemsInBackpack(ItemsBackpack);
        
        assertArrayEquals(expected, real);
    }


    /**
     * Test of areCoinsEnough method, of class DBDataItem.
     */
    @Test
    public void testAreCoinsEnough() {
        System.out.println("areCoinsEnough");
        int item_coins = 10;
        int user_wallet = 10;
        DBDataItem itemdata = new DBDataItem();
        boolean expected = true;
        boolean real = itemdata.areCoinsEnough(item_coins, user_wallet);
        
        assertEquals(expected, real);
    }

    /**
     * Test of getItemState method, of class DBDataItem.
     */
    @Test
    public void testGetItemState() {
        System.out.println("getItemState");
        int itemID = 1;
        DBDataItem itemdata = new DBDataItem();
        itemdata.insertItemBackpack(itemID);
        
        itemdata.setItemState(itemID, "ON");
        
        String expected = "ON";
        String real = itemdata.getItemState(itemID);
        
        assertEquals(expected, real);
    }

 
    /**
     * Test of anyItemBought method, of class DBDataItem.
     */
    @Test
    public void testAnyItemBought() {
        System.out.println("anyItemBought");
        DBDataItem itemdata = new DBDataItem();
        boolean expected = false;
        boolean real = itemdata.anyItemBought();
        
        assertEquals(expected, real);
    }

    /**
     * Test of allItemsBought method, of class DBDataItem.
     */
    @Test
    public void testAllItemsBought() {
        System.out.println("allItemsBought");
        DBDataItem itemdata = new DBDataItem();
        boolean expected = false;
        boolean real = itemdata.allItemsBought();
        
        assertEquals(expected, real);
    }
    
}
