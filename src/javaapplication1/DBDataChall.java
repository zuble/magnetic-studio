package javaapplication1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zublé
 */
public class DBDataChall extends DBCommunication{
    
    private static String Chall_quest, Username;
    private int Chall_id , Chall_coins , Chall_wisdom;
    private String Chall_text;
    DBDataUser UserData;
    
    public DBDataChall() {
        UserData= new DBDataUser();
        DBDataChall.Username = UserData.getUsername();
        DBDataChall.Chall_quest = UserData.getUserQuest();
    }
    
    public void setNewChall(){
        PreparedStatement st;
        ResultSet rs;

        String queryChall = "SELECT * FROM `challenge` WHERE `chall_quest` = ? AND `chall_id` NOT IN "
                            + "(SELECT `chall_id` FROM `backpack_chall` where `usr`= ? ) ORDER BY RAND() LIMIT 1";
        try {
            st = plugConn.prepareStatement(queryChall);   
            st.setString(1, DBDataChall.Chall_quest);
            st.setString(2, DBDataChall.Username);
            rs = st.executeQuery();
            while ( rs.next() ){
                this.Chall_id = rs.getInt("chall_id");
                this.Chall_text = rs.getString("chall_text");
                this.Chall_coins = rs.getInt("coins");
                this.Chall_wisdom = rs.getInt("wisdom"); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("CHALL.SETNEWCHALL ERROR");
        }finally{
            System.out.println("CHALL.SETNEWCHALL SUCESS");
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
    public int getChallWisdom(){
        return this.Chall_wisdom;
    }
    
    public int getNumberOfChalls(){
        int aux = 0;
        PreparedStatement st;
        ResultSet rs;
        String queryNChall = "SELECT COUNT(*) FROM `challenge`";
        
        try {
            st = plugConn.prepareStatement(queryNChall);   
            rs = st.executeQuery();
            rs.next();
            aux = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("CHALL.GETNUMBEROFCHALL ERROR");
        }
        return aux;  
    }
    
    public int getChallID(int inventoryChallID){
        if("fitness".equals(DBDataChall.Chall_quest)){
            return inventoryChallID + 10;
        }
        if("academic".equals(DBDataChall.Chall_quest)){
            return inventoryChallID + 20;
        }
        else return inventoryChallID;
    }
    
    public String getChallTextbyID(int challID){
        PreparedStatement st;
        ResultSet rs;
        String auxtext = null;
        
        String queryChall = "SELECT * FROM `challenge` WHERE `chall_quest` = ? AND `chall_id`=?";
        try {
                st = plugConn.prepareStatement(queryChall);   
                st.setString(1, DBDataChall.Chall_quest);
                st.setInt(2, getChallID(challID));
                rs = st.executeQuery();
                while ( rs.next() ){
                    auxtext = rs.getString("chall_text");
                }
            } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        return "<html>"+auxtext;
    }
    
    public void insertChallBackpack(){
        PreparedStatement st;
        String updateBackpack_chall = "INSERT INTO `backpack_chall`(`usr`,`chall_id`,`chall_quest`) VALUES (?,?,?)";
        try {
           st = plugConn.prepareStatement(updateBackpack_chall);
           st.setString(1,DBDataChall.Username);
           st.setInt(2,this.Chall_id);
           st.setString(3,DBDataChall.Chall_quest);
           st.executeUpdate();
        } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
     public boolean allChallDone(){
        PreparedStatement st;
        ResultSet rs;
        boolean aux = false;
        String ChallCount = "SELECT COUNT(*) FROM `backpack_chall` WHERE `usr`= ? AND `chall_quest` = ?";
        try {
            st = plugConn.prepareStatement(ChallCount);
            st.setString(1,DBDataChall.Username);
            st.setString(2,DBDataChall.Chall_quest);
            rs = st.executeQuery();
            while( rs.next() ){
               if(rs.getInt(1) == 10){
                   aux=true;
               }
           }
        } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return aux;
    }
     
    public boolean isChallDone(int challID){
        PreparedStatement st;
        ResultSet rs;
        boolean aux = false;
        String queryItemDisponible = "SELECT * FROM `backpack_chall` where `usr`= ? and `chall_id`= ?";
        
        try {
            st = plugConn.prepareStatement(queryItemDisponible); 
            st.setString(1, DBDataChall.Username);
            st.setInt(2, getChallID(challID));
            rs = st.executeQuery();
            while(rs.next()){
                aux=true;
            }   
        } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aux;
    }
    
    /**
     * 
     * checks if theres is any chall done by the atual user quest
     * @return aux
     */
    public boolean anyChallDone(){
        boolean aux = false;
        for( int i = 1 ; i < getNumberOfChalls() ; i++){
            if ( isChallDone(i) ) aux = true; 
        }
        return aux;
    }
}
