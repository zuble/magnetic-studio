package javaapplication1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class challFootler{
  private String missionType;
  challFootler(String missionType){ //Passar como parâmetro: academic, mind, fitness quando se instanciar
    this.missionType=missionType;
  }
  public String[] getMissions(){
    int i=0;
    String[] missions = null;
    PreparedStatement st;
    ResultSet rs;
    /*
     String queryChall = "SELECT * FROM `challenge` WHERE `chall_quest` = ? AND `chall_id` NOT IN "
                            + "(SELECT `chall_id` FROM `backpack_chall` where `usr`= ? ) ORDER BY RAND() LIMIT 1";
      try{
        st=My_CNX.getConnection().PreparedStatement(queryChall);
        st.setString(1,this.missionType);
        rs=st.executeQuery();

      while(rs.next()){
        missions[i]=rs.getString("chall_text");
        i++;
      }
    } catch (SQLException ex) {
    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }*/
      return missions;
  

}
}