/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Gustavo
 */
public class gameplayForm extends javax.swing.JFrame {
   
    public String user;
    private String Quest;
    private String Class;
    private String Chall_text;
    private int Chall_id;
    private int Chall_coins;
    private int flag;
    private int buttonPressedNo=0;
    private int buttonCat1=0 , buttonCat2=0;
  
    /**
     * Creates new form gameplayForm
     */
    public gameplayForm() {
        initComponents();
    }
    
    public void wizardLabelsTimer(){  
        Timer timer = new Timer(2000, new ActionListener() {public void actionPerformed(ActionEvent e) {
            jLabelWizardBallon.setVisible(false);
            jLabeltextWizardSpeach.setVisible(false);} });
        timer.setRepeats(false);
        timer.start();
    }    
    
    public void passData(String user) {
        this.user = user;
        PreparedStatement st;
        ResultSet rs;
        
        //GET USER INFO
        String queryClass = "SELECT * FROM `users` WHERE `username` = ? ";
        try {
            st = My_CNX.getConnection().prepareStatement(queryClass);
            st.setString(1, user);
            rs = st.executeQuery();
            
            while(rs.next()){
                this.Class = rs.getString("Class");
                this.Quest = rs.getString("Quest");
                jLabelMoney.setText(rs.getString("wallet"));
                System.out.println(this.Quest);
                System.out.println(this.Class);
            }
            } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //SET BACKGROUND AND IMAGES
        if ("fitness".equals(Quest)){
            Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fitnessCenter.png")));
            jLabeltextWizardSpeach.setText("<html> Welcome back to the Fighters Guild!<html>");
            wizardLabelsTimer();
            flag=1;
          }
          if ("academic".equals(Quest)){
            Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/academicCenter.png")));
            jLabeltextWizardSpeach.setText("<html> Welcome back to the College of Wizardry and Science! <html>");
            wizardLabelsTimer();
            flag=2;
          }
          if ("mind".equals(Quest)){
            Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mindCenter.png")));
            jLabeltextWizardSpeach.setText("<html> Welcome back to the Arcane Order of the Mind!<html>");
            wizardLabelsTimer();
            flag=3;
          }
          
        if ("archer".equals(Class)){
            jLabelCharacterImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/archer2.png")));
            jLabelName.setText(user+" the "+Class);
            jPanel2.setVisible(true);
            jLabelCoin.setVisible(true);
            jLabelMoney.setVisible(true);
            jPanelMoney.setVisible(true);
            }
        if ("fighter".equals(Class)){
            jLabelCharacterImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fighter2.png")));
            jLabelName.setText(user+" the "+Class);
            jPanel2.setVisible(true);
            jLabelCoin.setVisible(true);
            jLabelMoney.setVisible(true);
            jPanelMoney.setVisible(true);
        }
        if ("healer".equals(Class)){
            jLabelCharacterImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/healer2.png")));
            jLabelName.setText(user+" the "+Class);
            jPanel2.setVisible(true);
            jLabelCoin.setVisible(true);
            jLabelMoney.setVisible(true);
            jPanelMoney.setVisible(true);
        }
        
        //FILL RANK TABLE
        jCatalfRankDisplayPane.setVisible(false);
        String queryWalletRank = "SELECT * FROM `users` ORDER BY `wallet` DESC";
            try {
                st = My_CNX.getConnection().prepareStatement(queryWalletRank);   
                rs = st.executeQuery();

                while(rs.next()){
                    String data[] = { rs.getString("username"), rs.getString("Quest") , rs.getString("wallet") };
                    DefaultTableModel tb1Model = (DefaultTableModel)jUserRankTable.getModel();
                    tb1Model.addRow(data);
                }
                } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelMinCloseAll = new javax.swing.JPanel();
        CloseButton = new javax.swing.JLabel();
        MinimizeButton = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabeltextWizardSpeach = new javax.swing.JLabel();
        jLabelWizardBallon = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabelName = new javax.swing.JLabel();
        jLabelCharacterImage = new javax.swing.JLabel();
        jPanelMoney = new javax.swing.JPanel();
        jLabelCoin = new javax.swing.JLabel();
        jLabelMoney = new javax.swing.JLabel();
        jLabelCat = new javax.swing.JLabel();
        jButtonCatalf = new javax.swing.JButton();
        jCatalfRankDisplayPane = new javax.swing.JDesktopPane();
        jUserRankTable = new javax.swing.JTable();
        jLabelChall = new javax.swing.JLabel();
        jButtonChallenge = new javax.swing.JButton();
        jLabelMarketMan = new javax.swing.JLabel();
        JbuttonMarket = new javax.swing.JButton();
        jButtonComplete = new javax.swing.JButton();
        jButtonReturn = new javax.swing.JButton();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(null);

        jPanelMinCloseAll.setBackground(new java.awt.Color(153, 102, 0));
        jPanelMinCloseAll.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        CloseButton.setFont(new java.awt.Font("Book Antiqua", 1, 24)); // NOI18N
        CloseButton.setText("X");
        CloseButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CloseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CloseButtonMouseClicked(evt);
            }
        });

        MinimizeButton.setFont(new java.awt.Font("Book Antiqua", 1, 48)); // NOI18N
        MinimizeButton.setText("-");
        MinimizeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MinimizeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MinimizeButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelMinCloseAllLayout = new javax.swing.GroupLayout(jPanelMinCloseAll);
        jPanelMinCloseAll.setLayout(jPanelMinCloseAllLayout);
        jPanelMinCloseAllLayout.setHorizontalGroup(
            jPanelMinCloseAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMinCloseAllLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MinimizeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CloseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelMinCloseAllLayout.setVerticalGroup(
            jPanelMinCloseAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMinCloseAllLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanelMinCloseAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MinimizeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CloseButton, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        jPanel1.add(jPanelMinCloseAll);
        jPanelMinCloseAll.setBounds(640, 0, 60, 30);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/wizard2.png"))); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 20, 120, 130);

        jLabeltextWizardSpeach.setFont(new java.awt.Font("Book Antiqua", 1, 16)); // NOI18N
        jPanel1.add(jLabeltextWizardSpeach);
        jLabeltextWizardSpeach.setBounds(200, 30, 280, 80);

        jLabelWizardBallon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/balloon3.png"))); // NOI18N
        jPanel1.add(jLabelWizardBallon);
        jLabelWizardBallon.setBounds(160, 10, 350, 140);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 67, Short.MAX_VALUE)
        );

        jLabelName.setFont(new java.awt.Font("Book Antiqua", 1, 16)); // NOI18N
        jLabelName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 56, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(20, 370, 160, 30);
        jPanel1.add(jLabelCharacterImage);
        jLabelCharacterImage.setBounds(30, 240, 120, 130);

        jPanelMoney.setBackground(new java.awt.Color(255, 255, 204));
        jPanelMoney.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelCoin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/coin.png"))); // NOI18N

        jLabelMoney.setBackground(new java.awt.Color(255, 204, 51));
        jLabelMoney.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabelMoney.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanelMoneyLayout = new javax.swing.GroupLayout(jPanelMoney);
        jPanelMoney.setLayout(jPanelMoneyLayout);
        jPanelMoneyLayout.setHorizontalGroup(
            jPanelMoneyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMoneyLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelCoin, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanelMoneyLayout.setVerticalGroup(
            jPanelMoneyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelMoney, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
            .addComponent(jLabelCoin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelMoney);
        jPanelMoney.setBounds(50, 410, 90, 30);

        jLabelCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Catalf.png"))); // NOI18N
        jPanel1.add(jLabelCat);
        jLabelCat.setBounds(530, 80, 170, 150);

        jButtonCatalf.setBackground(new java.awt.Color(255, 255, 255));
        jButtonCatalf.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jButtonCatalf.setText("Catalf the Grey");
        jButtonCatalf.setBorder(new javax.swing.border.MatteBorder(null));
        jButtonCatalf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCatalfMouseClicked(evt);
            }
        });
        jPanel1.add(jButtonCatalf);
        jButtonCatalf.setBounds(530, 240, 160, 30);

        jUserRankTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Quest", "Coins"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });

        jCatalfRankDisplayPane.setLayer(jUserRankTable, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jCatalfRankDisplayPaneLayout = new javax.swing.GroupLayout(jCatalfRankDisplayPane);
        jCatalfRankDisplayPane.setLayout(jCatalfRankDisplayPaneLayout);
        jCatalfRankDisplayPaneLayout.setHorizontalGroup(
            jCatalfRankDisplayPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jUserRankTable, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
        );
        jCatalfRankDisplayPaneLayout.setVerticalGroup(
            jCatalfRankDisplayPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jUserRankTable, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
        );

        jPanel1.add(jCatalfRankDisplayPane);
        jCatalfRankDisplayPane.setBounds(190, 150, 307, 327);

        jLabelChall.setForeground(new java.awt.Color(255, 51, 51));
        jLabelChall.setToolTipText("");
        jPanel1.add(jLabelChall);
        jLabelChall.setBounds(210, 200, 270, 100);

        jButtonChallenge.setBackground(new java.awt.Color(255, 0, 0));
        jButtonChallenge.setFont(new java.awt.Font("Book Antiqua", 1, 24)); // NOI18N
        jButtonChallenge.setText("New challenge");
        jButtonChallenge.setBorder(new javax.swing.border.MatteBorder(null));
        jButtonChallenge.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonChallengeMouseClicked(evt);
            }
        });
        jPanel1.add(jButtonChallenge);
        jButtonChallenge.setBounds(210, 170, 270, 30);

        jLabelMarketMan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/market.png"))); // NOI18N
        jPanel1.add(jLabelMarketMan);
        jLabelMarketMan.setBounds(570, 280, 90, 160);

        JbuttonMarket.setBackground(new java.awt.Color(255, 255, 255));
        JbuttonMarket.setFont(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        JbuttonMarket.setText("Market");
        JbuttonMarket.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel1.add(JbuttonMarket);
        JbuttonMarket.setBounds(530, 441, 160, 30);

        jButtonComplete.setText("Complete");
        jButtonComplete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCompleteActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonComplete);
        jButtonComplete.setBounds(280, 310, 110, 25);

        jButtonReturn.setText("Return");
        jButtonReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReturnActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonReturn);
        jButtonReturn.setBounds(380, 450, 66, 25);

        Background.setBackground(new java.awt.Color(255, 255, 51));
        Background.setFont(new java.awt.Font("Book Antiqua", 1, 24)); // NOI18N
        jPanel1.add(Background);
        Background.setBounds(0, 0, 710, 500);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CloseButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseButtonMouseClicked
        System.exit(0);
    }//GEN-LAST:event_CloseButtonMouseClicked

    private void MinimizeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinimizeButtonMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_MinimizeButtonMouseClicked

    private void jButtonCatalfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCatalfMouseClicked
               
        if( buttonCat1 == 0 ){
            jCatalfRankDisplayPane.setVisible(true);
            buttonCat1 = 1;
        }
        else if( buttonCat1 == 1 ){
            jCatalfRankDisplayPane.setVisible(false);
            buttonCat1 = 0;
        }
    }//GEN-LAST:event_jButtonCatalfMouseClicked

    private void jButtonChallengeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonChallengeMouseClicked
        PreparedStatement st;
        ResultSet rs;

        String queryChall = "SELECT * FROM `challenge` WHERE `chall_quest` = ? AND `chall_id` NOT IN "
                            + "(SELECT `chall_id` FROM `backpack_chall` where `usr`= ? ) ORDER BY RAND() LIMIT 1";
        try {
            st = My_CNX.getConnection().prepareStatement(queryChall);   
            st.setString(1, Quest);
            st.setString(2, user);
            rs = st.executeQuery();
           
            while(rs.next()){
                this.Chall_text = rs.getString("chall_text");
                this.Chall_id = rs.getInt("chall_id");
                this.Chall_coins = rs.getInt("coins");
                jLabelChall.setText(Chall_text);
                System.out.println(this.Chall_text + this.Chall_id);
            }
            } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonChallengeMouseClicked

    private void jButtonCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCompleteActionPerformed
        // TODO add your handling code here:
        PreparedStatement st;
        ResultSet rs;
        
        //UPDATE USER WALLET
        String pointQuery = "UPDATE `users` SET `wallet`=`wallet` + ? WHERE `username` = ? ";
        try {
            st = My_CNX.getConnection().prepareStatement(pointQuery);
            st.setInt(1,Chall_coins);
            st.setString(2,user);
            st.executeUpdate();
            } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //UPDATE WALLET DISPLAY
        String returnWallet = "SELECT `wallet` FROM `users` WHERE `username` = ? ";
        try {
            st = My_CNX.getConnection().prepareStatement(returnWallet);
            st.setString(1,user);
            rs = st.executeQuery();
            rs.next();
            jLabelMoney.setText(rs.getString(1));
            jLabelChall.setText(" ");
            } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //INSERT COMPLETED CHALL INTO BACKPACK_CHALL
        String updateBackpack_chall = "INSERT INTO `backpack_chall`(`usr`,`chall_id`) VALUES (?,?)";
         try {
            st = My_CNX.getConnection().prepareStatement(updateBackpack_chall);
            st.setString(1,user);
            st.setInt(2,Chall_id);
            st.executeUpdate();
            } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonCompleteActionPerformed

    /* ADD RETURN STUFF */
    private void jButtonReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReturnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonReturnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(gameplayForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gameplayForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gameplayForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gameplayForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gameplayForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel CloseButton;
    private javax.swing.JButton JbuttonMarket;
    private javax.swing.JLabel MinimizeButton;
    private javax.swing.JButton jButtonCatalf;
    public javax.swing.JButton jButtonChallenge;
    private javax.swing.JButton jButtonComplete;
    private javax.swing.JButton jButtonReturn;
    private javax.swing.JDesktopPane jCatalfRankDisplayPane;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelCat;
    private javax.swing.JLabel jLabelChall;
    private javax.swing.JLabel jLabelCharacterImage;
    private javax.swing.JLabel jLabelCoin;
    private javax.swing.JLabel jLabelMarketMan;
    private javax.swing.JLabel jLabelMoney;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelWizardBallon;
    private javax.swing.JLabel jLabeltextWizardSpeach;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelMinCloseAll;
    private javax.swing.JPanel jPanelMoney;
    private javax.swing.JTable jUserRankTable;
    // End of variables declaration//GEN-END:variables
}
