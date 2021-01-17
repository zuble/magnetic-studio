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
    private int User_wallet , User_wisdom;
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
            while ( rs.next() ){
                this.User_class = rs.getString("Class");
                this.User_quest = rs.getString("Quest");
                this.User_wallet = rs.getInt("wallet");
                this.User_wisdom = rs.getInt("wisdom");
            }
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
    public int getUserWisdom(){
        return this.User_wisdom;
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
    
    public void updateUserWisdom(int Chall_wisdom_value){
        PreparedStatement st;
        String wisdomDBquery = "UPDATE `users` SET `wisdom`=`wisdom` + ? WHERE `username` = ? ";
        try {
            st = My_CNX.getConnection().prepareStatement(wisdomDBquery);
            st.setInt(1,Chall_wisdom_value);
            st.setString(2,this.Username);
            st.executeUpdate();
            } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        //UPDATES THE LOCAL WISDOM VARIABLE
        this.User_wisdom = this.User_wisdom + Chall_wisdom_value;
    }
    
    public void updateUserQuest(String Quest){
        PreparedStatement ps;
        String query = "UPDATE `users` SET `Quest`= ? WHERE `username` = ?";
        try{
            ps = My_CNX.getConnection().prepareStatement(query);
            ps.setString(1, Quest);
            ps.setString(2, this.Username);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BasicsForm.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void updateUserClass(String Class){
        PreparedStatement ps;
        String query = "UPDATE `users` SET `Class`= ? WHERE `username` = ?";
        System.out.println(Class + this.Username);
        try{
            ps = My_CNX.getConnection().prepareStatement(query);
            ps.setString(1, Class);
            ps.setString(2, this.Username);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BasicsForm.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}