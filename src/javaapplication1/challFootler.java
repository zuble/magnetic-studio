package javaapplication1;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableModel;

public class challFootler extends javax.swing.JFrame{
   
    private String missionType;
    
    public challFootler() {
        
    }
    
    /*
    public void FillRank( boolean update , String Quest ){
        PreparedStatement st;
        ResultSet rs;
        String query = "SELECT * FROM `users` WHERE `Quest`= ? ORDER BY `wallet` DESC";
        DefaultTableModel tb1Model = null;
        gameplayForm gmf = new gameplayForm();
        
        if(Quest.equals("academic")){ tb1Model = (DefaultTableModel)gmf.jAcademicRank.getModel();}
        if(Quest.equals("fitness")){ tb1Model = (DefaultTableModel)gmf.jFitnessRank.getModel();}
        if(Quest.equals("mind")){ tb1Model = (DefaultTableModel)gmf.jMindRank.getModel();}
        
        //UPDATE USER QUEST TABLE AFTER COMPLETE CHALLENGE 
        if(update){
            int rowCount = tb1Model.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) { tb1Model.removeRow(i);}
            System.out.println("1");
            try {
                st = My_CNX.getConnection().prepareStatement(query);
                st.setString(1,Quest);
                rs = st.executeQuery();

                while(rs.next()){
                    String data[] = { rs.getString("username"), rs.getString("Quest") , rs.getString("wallet") };
                    tb1Model.addRow(data);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        //UPDATES EVERY TABLE QUEST FRIST TIME 
        if(!update){
            System.out.println(Quest);
            if(Quest.equals("academic")){ System.out.println(Quest); gmf.jLabelAcademicRank.setText("academic"); }
            if(Quest.equals("fitness")){ gmf.jLabelAcademicRank.setText("fitness");}
            if(Quest.equals("mind")){ gmf.jLabelAcademicRank.setText("mind");}
            gmf.jLabelAcademicRank.setText("academic");
            try {
                st = My_CNX.getConnection().prepareStatement(query);
                st.setString(1,Quest);
                rs = st.executeQuery();

                while(rs.next()){
                    String data[] = { rs.getString("username"), rs.getString("Quest") , rs.getString("wallet") };
                    tb1Model.addRow(data);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
    }
    */
   
    
    //Passar como parâmetro: academic, mind, fitness quando se instanciar
    challFootler(String missionType){ 
        this.missionType=missionType;
    }
    
    /*
    public String[] getMissions(){
        int i=0;
        String[] missions = null;
        PreparedStatement st;
        ResultSet rs;
    
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
      }
        return missions;
    }
    */
    
    
public javax.swing.JLabel jLabelAcademicRank;  
}