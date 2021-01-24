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
public class DBDataUser extends DBCommunication{
    
    public static int User_wallet , User_wisdom;
    public static String Username , User_class, User_quest;
    private boolean auxuser = false;
    
    public DBDataUser() {
    }
    
    /**
     * SETS THE LOCAL VARIABLES FOR USER
     */
    public void setDataUser(){
        PreparedStatement st;
        ResultSet rs;
        String queryUser = "SELECT * FROM `users` WHERE `username` = ? ";

        try {
            st = plugConn.prepareStatement(queryUser);
            st.setString(1, DBDataUser.Username);
            rs = st.executeQuery();
            while ( rs.next() ){
                DBDataUser.User_class = rs.getString("Class");
                DBDataUser.User_quest = rs.getString("Quest");
                DBDataUser.User_wallet = rs.getInt("wallet");
                DBDataUser.User_wisdom = rs.getInt("wisdom");
            }
        }catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("USER.SETDATA ERROR");
        }
        System.out.println("USER.SETDATA SUCESS  USERNAME:"+DBDataUser.Username+" CLASS:"+User_class+" QUEST:"+User_quest+" WALLET:"+User_wallet+" WISDOM:"+User_wisdom);
    }
    
    public void setUsername(String username){
        DBDataUser.Username = username;
    }
    public String getUsername(){
        return DBDataUser.Username;
    }
    public String getUserClass(){
        return DBDataUser.User_class;
    }
    public String getUserQuest(){
        return DBDataUser.User_quest;
    }
    public int getUserWallet(){
        return DBDataUser.User_wallet;
    }
    public int getUserWisdom(){
        return DBDataUser.User_wisdom;
    }
    
    /**
     * VERIFY IF THE INFORMATION FOR LOGIN IS EQUAL TO DB
     * @param password
     * @return 
     */
    public boolean loginVerification( String password){
        PreparedStatement st;
        ResultSet rs;
        boolean auxlog = false;
        String queryLogin = "SELECT * FROM `users` WHERE `username` = ? AND `password` = ?";

        try {
            st = plugConn.prepareStatement(queryLogin);
            st.setString(1, DBDataUser.Username);
            st.setString(2, password);
            rs = st.executeQuery();

            if (rs.next()){ 
                auxlog = true; 
            }
        }catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auxlog;   
    }
    
    /**
     * REGISTER A NEW USER
     * @param fname
     * @param password
     * @return 
     */
    public String doRegistration(String fname, String password){
        String auxreg = null;
        PreparedStatement ps1;
        String queryRegister  = "INSERT INTO `users`(`username`, `password`,`full_name`,`Class`,`Quest`,`wallet`,`wisdom`) VALUES (?,?,?,?,?,?,?)";
        
        if( isUsernameValid() ){
            try {
                ps1 = plugConn.prepareStatement(queryRegister);
                ps1.setString(1, DBDataUser.Username);
                ps1.setString(2, password);
                ps1.setString(3, fname);
                ps1.setString(4, "class");
                ps1.setString(5, "quest");
                ps1.setInt(6, 0);
                ps1.setInt(7, 0);

                if(ps1.executeUpdate()!=0){
                    auxreg = "sucess";
                }
            } catch (SQLException ex) {
                auxreg = "error-reg";
                Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if ( !isUsernameValid() ){ auxreg = "error-username"; }
        
        return auxreg;
    }
    
    /**
     * VERIFY IF THE USERNAME IS ALREADY IN USE
     * @return auxlog
     */
    private boolean isUsernameValid(){
        PreparedStatement st;
        ResultSet rs;
        String queryCheckUsername = "SELECT COUNT(*) FROM `users` WHERE `username` = ? ";
        
        try {
            st= plugConn.prepareStatement(queryCheckUsername);
            st.setString(1, DBDataUser.Username);
            rs= st.executeQuery();
            rs.next();
            if( ! (rs.getInt(1) == 1) ){
                auxuser = true;
            }
        }catch (SQLException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auxuser; 
    }
    
    /**
     * UPDATES USER WALLET
     * @param value
     * @param operation 
     */
    public void updateUserWallet(int value, String operation){
        PreparedStatement st;
        String walletDBquery = null;
        if("minus".equals(operation)){ walletDBquery = "UPDATE `users` SET `wallet`=`wallet` - ? WHERE `username` = ? ";}
        if("plus".equals(operation)){ walletDBquery = "UPDATE `users` SET `wallet`=`wallet` + ? WHERE `username` = ? ";}
        
        try {
            st = plugConn.prepareStatement(walletDBquery);
            st.setInt(1,value);
            st.setString(2,DBDataUser.Username);
            st.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("USER.WALLET UPDATE ERROR");
        }
        //UPDATES THE LOCAL WALLET VARIABLE
        if("minus".equals(operation)){ DBDataUser.User_wallet -= value; }
        if("plus".equals(operation)){ DBDataUser.User_wallet += value; }
        System.out.println("USER.WALLET UPDATE SUCESS");
             
    }
    
    /**
     * UPDATES USER WISDOM 
     * @param Chall_wisdom_value 
     */
    public void updateUserWisdom(int Chall_wisdom_value){
        PreparedStatement st;
        String wisdomDBquery = "UPDATE `users` SET `wisdom`=`wisdom` + ? WHERE `username` = ? ";
        try {
            st = plugConn.prepareStatement(wisdomDBquery);
            st.setInt(1,Chall_wisdom_value);
            st.setString(2,DBDataUser.Username);
            st.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("USER.WISDOM UPDATE ERROR");
        }
        //UPDATES THE LOCAL WISDOM VARIABLE
        DBDataUser.User_wisdom += Chall_wisdom_value;
        System.out.println("USER.WISDOM UPDATE SUCESS");
    }
    
    /**
     * UPDATES USER QUEST 
     * @param Quest 
     */
    public void updateUserQuest(String Quest){
        PreparedStatement ps;
        String query = "UPDATE `users` SET `Quest`= ? WHERE `username` = ?";
        
        try{
            ps = plugConn.prepareStatement(query);
            ps.setString(1, Quest);
            ps.setString(2, DBDataUser.Username);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BasicsForm.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("USER.QUEST UPDATE ERROR");
        }
        //UPDATES THE LOCAL QUEST VARIABLE
        DBDataUser.User_quest = Quest;
        System.out.println("USER.QUEST UPDATE SUCESS");
    }
    
    /**
     * UPDATES USER CLASS
     * @param Class 
     */
    public void updateUserClass(String Class){
        PreparedStatement ps;
        String query = "UPDATE `users` SET `Class`= ? WHERE `username` = ?";
        
        try{
            ps = plugConn.prepareStatement(query);
            ps.setString(1, Class);
            ps.setString(2, DBDataUser.Username);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BasicsForm.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("USER.CLASS UPDATE ERROR");
        }
        //UPDATES THE LOCAL CLASS VARIABLE
        DBDataUser.User_class = Class;
        System.out.println("USER.CLASS UPDATE SUCESS");
    }
    
    /**
     * DELETES USER ACCOUNT
     */
    public void deleteUserAcc(){
        PreparedStatement ps;
        String query = "DELETE FROM `users` WHERE `username` = ?";
        
        try{
            ps = plugConn.prepareStatement(query);
            ps.setString(1, DBDataUser.Username);
            ps.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(BasicsForm.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ACC DEL ERROR");
        }
        System.out.println("ACC DEL SUCESS");
    }
    
}