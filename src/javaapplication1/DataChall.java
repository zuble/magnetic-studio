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
public class DataChall {
    
    private int Chall_id , Chall_coins , Chall_exp;
    private String Chall_text, Username;
    
    public DataChall(String Username, String Quest) {
        this.Username = Username;
        PreparedStatement st;
        ResultSet rs;
        
        String queryChall = "SELECT * FROM `challenge` WHERE `chall_quest` = ? AND `chall_id` NOT IN "
                            + "(SELECT `chall_id` FROM `backpack_chall` where `usr`= ? ) ORDER BY RAND() LIMIT 1";
        try {
            st = My_CNX.getConnection().prepareStatement(queryChall);   
            st.setString(1, Quest);
            st.setString(2, Username);
            rs = st.executeQuery();
            rs.next();
            this.Chall_id = rs.getInt("chall_id");
            this.Chall_text = rs.getString("chall_text");
            this.Chall_coins = rs.getInt("coins");
            this.Chall_exp = rs.getInt("exp");
            
        } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    public int getChallId(){
        return this.Chall_id;
    }
    public String getChallText(){
        return this.Chall_text;
    }
    public int getChallCoins(){
        return this.Chall_coins;
    }
    public int getChallExp(){
        return this.Chall_exp;
    }
    
    public void insertChallBackpack(int Chall_id){
        PreparedStatement st;
        String updateBackpack_chall = "INSERT INTO `backpack_chall`(`usr`,`chall_id`) VALUES (?,?)";
        try {
           st = My_CNX.getConnection().prepareStatement(updateBackpack_chall);
           st.setString(1,this.Username);
           st.setInt(2,Chall_id);
           st.executeUpdate();
        } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
}
