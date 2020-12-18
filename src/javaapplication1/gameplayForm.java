/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JList;


/**
 *
 * @author Zublé
 */

public class gameplayForm extends javax.swing.JFrame {
    private String user;
    private String Quest;
    private String Class;
    private String Chall;
    private int flag;
    private int buttonPressedNo=0;
    private int buttonCatNo=0;
    private int minAca = 3 , maxAca = 5;
    private int minMind = 1 , maxMind = 3;
    private int minFit = 5 , maxFit = 7;
    private int MIN , MAX ; 
    private int rnumb;
    
    /**
     * Creates new form gameplayForm
     */
    public gameplayForm() {
        initComponents();
        jButtonComplete.setVisible(false);
        jButtonCancel.setVisible(false);
        //jLabelChall.setBackground(new Color(0,0,0,50));
        //jButtonChallenge.setVisible(false);
        setIcon();       
    }
    
    public void setIcon(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/wizard.png")));
    }
    
    public int random(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    //previamente era a passData
    public void getUserInfo(String user) {
        this.user = user;
        PreparedStatement st;
        ResultSet rs;
        String queryClass = "SELECT * FROM `users` WHERE `username` = ? ";

        try {
            st = My_CNX.getConnection().prepareStatement(queryClass);
            st.setString(1, user);
            rs = st.executeQuery();

            while(rs.next()){
                this.Class = rs.getString("Class");
                this.Quest = rs.getString("Quest");
                jLabelMoney.setText(rs.getString("wallet"));
                System.out.println(this.Class);
                System.out.println(this.Quest);
            }
            } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }   
    
        if ("fitness".equals(Quest)){
            Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fitnessCenter.png")));
            jLabeltext.setText("<html> Welcome back to the Fighters Guild!<html>");
            flag=1;
            this.MIN = minFit;
            this.MAX = maxFit;
            ActionListener taskPerformer;
            taskPerformer = (ActionEvent evt) -> {
            jLabel3.setVisible(false);
            jLabeltext.setVisible(false);};
            new Timer(4000, taskPerformer).start();
        }
        if ("academic".equals(Quest)){
            Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/academicCenter.png")));
            jLabeltext.setText("<html> Welcome back to the College of Wizardry and Science! <html>");
            flag=2;
            this.MIN = minAca;
            this.MAX = maxAca;
            ActionListener taskPerformer;
            taskPerformer = (ActionEvent evt) -> {
            jLabel3.setVisible(false);
            jLabeltext.setVisible(false);};
            new Timer(4000, taskPerformer).start();
        }
        if ("mind".equals(Quest)){
            Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mindCenter.png")));
            jLabeltext.setText("<html> Welcome back to the Arcane Order of the Mind!<html>");
            this.MIN = minMind;
            this.MAX = maxMind;
            flag=3;
            ActionListener taskPerformer;
            taskPerformer = (ActionEvent evt) -> {
            jLabel3.setVisible(false);
            jLabeltext.setVisible(false);};
            new Timer(4000, taskPerformer).start();
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
        jPanel3 = new javax.swing.JPanel();
        CloseButton = new javax.swing.JLabel();
        MinimizeButton = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabeltext = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabelName = new javax.swing.JLabel();
        jLabelCharacterImage = new javax.swing.JLabel();
        jPanelMoney = new javax.swing.JPanel();
        jLabelCoin = new javax.swing.JLabel();
        jLabelMoney = new javax.swing.JLabel();
        jLabelCat = new javax.swing.JLabel();
        jButtonChallenge = new javax.swing.JButton();
        jButtonCatalf = new javax.swing.JButton();
        jLabelMarketMan = new javax.swing.JLabel();
        jButtonCancel = new javax.swing.JButton();
        jButtonComplete = new javax.swing.JButton();
        jScrollChalls = new javax.swing.JScrollPane();
        jListChall = new javax.swing.JList<>();
        JbuttonMarket = new javax.swing.JButton();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(null);

        jPanel3.setBackground(new java.awt.Color(153, 102, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MinimizeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CloseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MinimizeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CloseButton, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(640, 0, 60, 30);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/wizard2.png"))); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 20, 120, 130);

        jLabeltext.setFont(new java.awt.Font("Book Antiqua", 1, 16)); // NOI18N
        jPanel1.add(jLabeltext);
        jLabeltext.setBounds(200, 30, 280, 80);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/balloon3.png"))); // NOI18N
        jPanel1.add(jLabel3);
        jLabel3.setBounds(160, 10, 350, 130);

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
        jLabelMoney.setText("0");

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
        jLabelCat.setBounds(520, 80, 170, 150);

        jButtonChallenge.setBackground(new java.awt.Color(255, 0, 0));
        jButtonChallenge.setFont(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        jButtonChallenge.setText("Set new challenges");
        jButtonChallenge.setBorder(new javax.swing.border.MatteBorder(null));
        jButtonChallenge.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonChallengeMouseClicked(evt);
            }
        });
        jPanel1.add(jButtonChallenge);
        jButtonChallenge.setBounds(180, 170, 300, 30);

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
        jButtonCatalf.setBounds(520, 240, 160, 30);

        jLabelMarketMan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/market.png"))); // NOI18N
        jPanel1.add(jLabelMarketMan);
        jLabelMarketMan.setBounds(560, 280, 90, 160);

        jButtonCancel.setBackground(new java.awt.Color(255, 0, 0));
        jButtonCancel.setFont(new java.awt.Font("Book Antiqua", 1, 8)); // NOI18N
        jButtonCancel.setText("Cancel");
        jButtonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCancelMouseClicked(evt);
            }
        });
        jPanel1.add(jButtonCancel);
        jButtonCancel.setBounds(330, 280, 70, 20);

        jButtonComplete.setBackground(new java.awt.Color(0, 204, 0));
        jButtonComplete.setFont(new java.awt.Font("Book Antiqua", 1, 8)); // NOI18N
        jButtonComplete.setText("Complete!");
        jButtonComplete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCompleteMouseClicked(evt);
            }
        });
        jPanel1.add(jButtonComplete);
        jButtonComplete.setBounds(240, 280, 80, 20);

        jScrollChalls.setViewportView(jListChall);

        jPanel1.add(jScrollChalls);
        jScrollChalls.setBounds(180, 200, 300, 80);

        JbuttonMarket.setBackground(new java.awt.Color(255, 255, 255));
        JbuttonMarket.setFont(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        JbuttonMarket.setText("Market");
        JbuttonMarket.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel1.add(JbuttonMarket);
        JbuttonMarket.setBounds(550, 450, 110, 21);

        Background.setBackground(new java.awt.Color(0, 0, 0));
        Background.setFont(new java.awt.Font("Book Antiqua", 1, 24)); // NOI18N
        jPanel1.add(Background);
        Background.setBounds(0, 0, 700, 500);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CloseButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseButtonMouseClicked
        System.exit(0);
    }//GEN-LAST:event_CloseButtonMouseClicked

    private void MinimizeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinimizeButtonMouseClicked
        // TODO add your handling code here:
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_MinimizeButtonMouseClicked

    private void jButtonChallengeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonChallengeMouseClicked
       
        PreparedStatement st;
        ResultSet rs;
        DefaultListModel model = new DefaultListModel();
        JList list = new JList(model);
        this.rnumb = random(MIN,MAX);
        
        String queryChall2= "SELECT * FROM `challenge` WHERE `chall_quest`= ? AND `chall_id` NOT IN ( SELECT `chall_id` FROM `backpack_chall` where `usr` = ? )";
        
        try {
            st = My_CNX.getConnection().prepareStatement(queryChall2);
            st.setString(1, Quest);
            st.setString(2, user);
            rs = st.executeQuery();
           
            while(rs.next()){
                jListChall.setVisible(true);
                this.Chall = rs.getString("chall_text");
                //jPanelChall.setVisible(true);
                //jLabelChall.setText(Chall);
                model.addElement(rs.getString("chall_text"));
                jButtonCancel.setVisible(true);
                jButtonComplete.setVisible(true);
                jButtonChallenge.setVisible(false);
                
                System.out.println(rs.getString("chall_text"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonChallengeMouseClicked

    private void jButtonCatalfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCatalfMouseClicked
 
        PreparedStatement st;
        ResultSet rs;
        String queryChall = "SELECT * FROM `users` ORDER BY `wallet` DESC ";

        try {
            st = My_CNX.getConnection().prepareStatement(queryChall);
            rs = st.executeQuery();

            while(rs.next()){
                System.out.println(rs.getString("username") + rs.getString("wallet"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonCatalfMouseClicked

    private void jButtonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCancelMouseClicked
        // TODO add your handling code here:
        jButtonComplete.setVisible(false);
        jButtonChallenge.setVisible(true);
        jButtonCancel.setVisible(false);

    }//GEN-LAST:event_jButtonCancelMouseClicked

    private void jButtonCompleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCompleteMouseClicked
        //adicionar as coins correspondentes á chall na wallet
        PreparedStatement st;
        ResultSet rs;
        
        String queryChall = "INSERT INTO `backpack_chall`(`usr`, `chall_id`) VALUES (?,?)";

        try {
            st = My_CNX.getConnection().prepareStatement(queryChall);
            st.setString(1,user);
            st.setInt(2,rnumb);
            rs = st.executeQuery();

           
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }//GEN-LAST:event_jButtonCompleteMouseClicked
   
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
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonCatalf;
    private javax.swing.JButton jButtonChallenge;
    private javax.swing.JButton jButtonComplete;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelCat;
    private javax.swing.JLabel jLabelCharacterImage;
    private javax.swing.JLabel jLabelCoin;
    private javax.swing.JLabel jLabelMarketMan;
    private javax.swing.JLabel jLabelMoney;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabeltext;
    private javax.swing.JList<String> jListChall;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelMoney;
    private javax.swing.JScrollPane jScrollChalls;
    // End of variables declaration//GEN-END:variables

   
}
