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
    
    private int item_id , item_price;
    
    private String Username , item_name, item_info;
    
    public DataItem(String Username){
        this.Username = Username;
    }
    
    public void getDataItem(int Item_Id){
        PreparedStatement ps;
        ResultSet rs;
        String queryItem = "SELECT * FROM `item` WHERE `item_id` = ?";
        
        try {
            ps = My_CNX.getConnection().prepareStatement(queryItem);
            ps.setInt(1, Item_Id);
            rs = ps.executeQuery();
            rs.next();
            this.item_id = rs.getInt("item_id");
            this.item_name = rs.getString("item_name");
            this.item_info = rs.getString("item_info");
            this.item_price = rs.getInt("price");      
        } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getItemId(){
        return this.item_id;
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
 
    public boolean isItemDisp(int itemID){
        PreparedStatement st;
        ResultSet rs;
        boolean aux = true;
        String queryItemDisponible = "SELECT * FROM `backpack_item` where `usr`= ?";
        try {
            st = My_CNX.getConnection().prepareStatement(queryItemDisponible); 
            st.setString(1, this.Username);
            rs = st.executeQuery();
            while(rs.next()){
                if(rs.getInt("item_id") == itemID )aux=false;
            }   
        } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aux;
    }
    
    public boolean isCoinsEnough(int item_coins , int user_wallet){
        boolean aux = false;
        if( user_wallet - item_coins >= 0) aux = true;
        return aux;
    }
    
    public void insertItemBackpack(int itemID){
        PreparedStatement st;
        String updateBackpack_chall = "INSERT INTO `backpack_item`(`usr`,`item_id`) VALUES (?,?)";
        try {
           st = My_CNX.getConnection().prepareStatement(updateBackpack_chall);
           st.setString(1,this.Username);
           st.setInt(2,itemID);
           st.executeUpdate();
        } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

