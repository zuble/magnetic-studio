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
public class DataUser {
    private int User_wallet , User_exp;
    private String Username , User_class, User_quest;
 
    public DataUser(String username) {
        this.Username = username;
        PreparedStatement st;
        ResultSet rs;
        String queryClass = "SELECT * FROM `users` WHERE `username` = ? ";
        
        try {
            st = My_CNX.getConnection().prepareStatement(queryClass);
            st.setString(1, username);
            rs = st.executeQuery();
            rs.next();
            this.User_class = rs.getString("Class");
            this.User_quest = rs.getString("Quest");
            this.User_wallet = rs.getInt("wallet");
            this.User_exp = rs.getInt("exp");   
        } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getUserClass(){
        return this.User_class;
    }
    public String getUserQuest(){
        return this.User_quest;
    }
    public int getUserWallet(){
        return this.User_wallet;
    }
    public int getUserExp(){
        return this.User_exp;
    }
    public void updateUserWallet(int value, String operation){
        PreparedStatement st;
        String walletDBquery = null;
        if("minus".equals(operation)){ walletDBquery = "UPDATE `users` SET `wallet`=`wallet` - ? WHERE `username` = ? ";}
        if("plus".equals(operation)){ walletDBquery = "UPDATE `users` SET `wallet`=`wallet` + ? WHERE `username` = ? ";}
        try {
            st = My_CNX.getConnection().prepareStatement(walletDBquery);
            st.setInt(1,value);
            st.setString(2,this.Username);
            st.executeUpdate();
            } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateUserExp(int Chall_exp_value){
        PreparedStatement st;
        String expDBquery = "UPDATE `users` SET `exp`=`exp` + ? WHERE `username` = ? ";
        try {
            st = My_CNX.getConnection().prepareStatement(expDBquery);
            st.setInt(1,Chall_exp_value);
            st.setString(2,this.Username);
            st.executeUpdate();
            } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}