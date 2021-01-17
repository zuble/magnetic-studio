/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Legion
 */
public class DataItem {
    
    private int itemID , item_price;
    private final String Username;
    private String item_name, item_info;
    
    public DataItem(String Username){
        this.Username = Username;
    }
    
    public void getDataItem(int itemID){
        PreparedStatement ps;
        ResultSet rs;
        String queryItem = "SELECT * FROM `item` WHERE `item_id` = ?";
        
        try {
            ps = My_CNX.getConnection().prepareStatement(queryItem);
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
    
    
    public void insertItemBackpack(int itemID){
        PreparedStatement st;
        String updateBackpack_chall = "INSERT INTO `backpack_item`(`usr`,`item_id`,`state`) VALUES (?,?,?)";
        try {
           st = My_CNX.getConnection().prepareStatement(updateBackpack_chall);
           st.setString(1,this.Username);
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
            st = My_CNX.getConnection().prepareStatement(queryItemDisponible); 
            st.setString(1, this.Username);
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
        for(int i = 0 ; i < 4 ; i++){
            ItemsBackpack[i] = isItemInBackpack(i+1);
            System.out.println("backpack"+i+ItemsBackpack[i]);
        }
        return ItemsBackpack;
    }
    
    public boolean[] areItemsAvaiableToBuy(boolean[] ItemsToBuy){
        for(int i = 0 ; i < 4 ; i++){
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
            st = My_CNX.getConnection().prepareStatement(queryItemDisponible); 
            st.setString(1, this.Username);
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
            ps = My_CNX.getConnection().prepareStatement(query);
            ps.setString(1, state);
            ps.setString(2, this.Username);
            ps.setInt(3, itemID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BasicsForm.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    
}

