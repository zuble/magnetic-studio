package javaapplication1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zublé
 */
public class DBDataItem extends DBCommunication{
    
    private int itemID , item_price;
    private static String Username;
    private String item_name, item_info;
    DBDataUser UserData;
    
    public DBDataItem(){
        UserData= new DBDataUser();
        DBDataItem.Username = UserData.getUsername();
    }
    
    public void getDataItem(int itemID){
        PreparedStatement ps;
        ResultSet rs;
        String queryItem = "SELECT * FROM `item` WHERE `item_id` = ?";
        
        try {
            ps = plugConn.prepareStatement(queryItem);
            ps.setInt(1, itemID);
            rs = ps.executeQuery();
            rs.next();
            this.itemID = rs.getInt("item_id");
            this.item_name = rs.getString("item_name");
            this.item_info = rs.getString("item_info");
            this.item_price = rs.getInt("price");      
        } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getItemID(){
        return this.itemID;
    }
    public String getItemName(){
        return this.item_name;
    }
    public String getItemInfo(){
        return this.item_info;
    }
    public int getItemPrice(){
        return this.item_price;
    }
    
    public int getNumberOfItems(){
        PreparedStatement st;
        ResultSet rs;
        int aux = 0;
        String queryNChall = "SELECT COUNT(*) FROM `item`";
        
        try {
            st = plugConn.prepareStatement(queryNChall);   
            rs = st.executeQuery();
            if ( rs.next() ){
                aux = rs.getInt(1);
            }
        } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aux;
    }
    
    public void insertItemBackpack(int itemID){
        PreparedStatement st;
        String updateBackpack_chall = "INSERT INTO `backpack_item`(`usr`,`item_id`,`state`) VALUES (?,?,?)";
        try {
           st = plugConn.prepareStatement(updateBackpack_chall);
           st.setString(1,DBDataItem.Username);
           st.setInt(2,itemID);
           st.setString(3,"OFF");
           st.executeUpdate();
        } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isItemInBackpack(int itemID){
        PreparedStatement st;
        ResultSet rs;
        boolean aux = false;
        String queryItemDisponible = "SELECT * FROM `backpack_item` where `usr`= ?";
        try {
            st = plugConn.prepareStatement(queryItemDisponible); 
            st.setString(1, DBDataItem.Username);
            rs = st.executeQuery();
            while(rs.next()){
                if(rs.getInt("item_id") == itemID )aux=true;
            }   
        } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aux;
    }
    
    public boolean[] areItemsInBackpack(boolean[] ItemsBackpack){
        for(int i = 0 ; i < getNumberOfItems() ; i++){
            ItemsBackpack[i] = isItemInBackpack(i+1);
            System.out.println("backpack"+i+ItemsBackpack[i]);
        }
        return ItemsBackpack;
    }
    
    public boolean[] areItemsAvaiableToBuy(boolean[] ItemsToBuy){
        for(int i = 0 ; i < getNumberOfItems() ; i++){
            ItemsToBuy[i] = !(isItemInBackpack(i+1));
            System.out.println("buy"+i+ItemsToBuy[i]);
        }
        return ItemsToBuy;
    }
    
    public boolean areCoinsEnough(int item_coins , int user_wallet){
        boolean aux = false;
        if( user_wallet - item_coins >= 0) aux = true;
        return aux;
    }
    
    public String getItemState(int itemID){
        PreparedStatement st;
        ResultSet rs;
        String aux = null;
        String queryItemDisponible = "SELECT * FROM `backpack_item` where `usr`= ? and `item_id`= ?";
        try {
            st = plugConn.prepareStatement(queryItemDisponible); 
            st.setString(1, DBDataItem.Username);
            st.setInt(2, itemID);
            rs = st.executeQuery();
            while(rs.next()){
                aux = rs.getString("state");
            }   
        } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aux;
    }
    
    public void setItemState(int itemID , String state){
        PreparedStatement ps;
        String query = "UPDATE `backpack_item` SET `state`= ? WHERE `usr` = ? and `item_id` = ?";
        if( !(getItemState(itemID).equals(state)) ) try{
            ps = plugConn.prepareStatement(query);
            ps.setString(1, state);
            ps.setString(2, DBDataItem.Username);
            ps.setInt(3, itemID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BasicsForm.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    /**
     * checks if the user has any item in the backpack
     * @return aux
     */
    public boolean anyItemBought(){
        boolean aux = false;
        for( int i = 1 ; i < getNumberOfItems()+1 ; i++){
            if ( isItemInBackpack(i) ) aux = true; 
        }
        return aux;
    }
    
    public boolean allItemsBought(){
        int j = 0;
        for( int i = 1 ; i < getNumberOfItems()+1 ; i++){
            if ( isItemInBackpack(i) ) j++;
        }
        return j == getNumberOfItems();
    }
}

