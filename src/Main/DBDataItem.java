package Main;

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
    
    /**
     * sets the local variables equal to the information of item with id = itemID
     * @param itemID 
     */
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
    
    /**
     * gets the number of items in the database
     * @return 
     */
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
    
    /**
     * inserts item with id = itemID in user backpack
     * @param itemID 
     */
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
    
    /**
     * checks if the item with id = itemID is in user backpack
     * @param itemID
     * @return 
     */
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
    
    /**
     * example : item id = 1 is in user backpack => ItemsBackpack={true,false,false,(...)}
     * @param ItemsBackpack
     * @return 
     */
    public boolean[] areItemsInBackpack(boolean[] ItemsBackpack){
        for(int i = 0 ; i < getNumberOfItems() ; i++){
            ItemsBackpack[i] = isItemInBackpack(i+1);
            System.out.println("backpack"+i+ItemsBackpack[i]);
        }
        return ItemsBackpack;
    }
    
    /**
     * example : item id = 1 is avaiable to buy => ItemsToBuy={true,false,false,false}
     * @param ItemsToBuy
     * @return 
     */
    public boolean[] areItemsAvaiableToBuy(boolean[] ItemsToBuy){
        for(int i = 0 ; i < getNumberOfItems() ; i++){
            ItemsToBuy[i] = !(isItemInBackpack(i+1));
            System.out.println("buy"+i+ItemsToBuy[i]);
        }
        return ItemsToBuy;
    }
    
    /**
     * checks if the atual user wallet is enough to buy the item
     * @param item_coins
     * @param user_wallet
     * @return 
     */
    public boolean areCoinsEnough(int item_coins , int user_wallet){
        boolean aux = false;
        if( user_wallet - item_coins >= 0) aux = true;
        return aux;
    }
    
    /**
     * gets the atual state of the item with id = itemID
     * @param itemID
     * @return 
     */
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
    
    /**
     * sets the state to the itemID 
     * @param itemID
     * @param state 
     */
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
    
    /**
     * checks if the user has all items bought from the wizard's house
     * @return 
     */
    public boolean allItemsBought(){
        int j = 0;
        for( int i = 1 ; i < getNumberOfItems()+1 ; i++){
            if ( isItemInBackpack(i) ) j++;
        }
        return j == getNumberOfItems();
    }
}

