/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Gustavo
 */
public class BasicsForm extends javax.swing.JFrame {

    /**
     * Creates new form BasicsForm
     */
    private int buttonPressedNo=0;
    private String user;
    private String Class;
    
    Login login;
    
    public BasicsForm() {
        initComponents();
        login = new Login();
        setIcon();
         this.setLocationRelativeTo(null);
         jLabelArcher.setVisible(false);
         jLabelFighter.setVisible(false);
         jLabelHealer.setVisible(false);
         jButtonFighter.setVisible(false);
         jButtonArcher.setVisible(false);
         jButtonHealer.setVisible(false);
    }
 public void setIcon()
    {
         setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/wizard.png")));
    }
    
    
    public void passData(String user) {
        this.user = user;     
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabeltext = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonContinue = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        CloseButton = new javax.swing.JLabel();
        MinimizeButton = new javax.swing.JLabel();
        jLabelFighter = new javax.swing.JLabel();
        jLabelHealer = new javax.swing.JLabel();
        jLabelArcher = new javax.swing.JLabel();
        jButtonFighter = new javax.swing.JButton();
        jButtonHealer = new javax.swing.JButton();
        jButtonArcher = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/wizard2.png"))); // NOI18N
        jPanel2.add(jLabel2);
        jLabel2.setBounds(20, 20, 120, 130);

        jLabeltext.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabeltext.setText("<html> Let's start with the basics. <html>");
        jPanel2.add(jLabeltext);
        jLabeltext.setBounds(190, 20, 160, 80);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/balloon22.png"))); // NOI18N
        jPanel2.add(jLabel3);
        jLabel3.setBounds(160, 0, 240, 130);

        jButtonContinue.setBackground(new java.awt.Color(153, 153, 255));
        jButtonContinue.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        jButtonContinue.setText("Continue");
        jButtonContinue.setBorder(new javax.swing.border.MatteBorder(null));
        jButtonContinue.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonContinue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonContinueMouseClicked(evt);
            }
        });
        jPanel2.add(jButtonContinue);
        jButtonContinue.setBounds(140, 350, 250, 30);

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

        jPanel2.add(jPanel3);
        jPanel3.setBounds(500, 0, 60, 30);

        jLabelFighter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fighter.png"))); // NOI18N
        jLabelFighter.setText("warrior");
        jLabelFighter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelFighterMouseClicked(evt);
            }
        });
        jPanel2.add(jLabelFighter);
        jLabelFighter.setBounds(60, 170, 140, 150);

        jLabelHealer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/healer.png"))); // NOI18N
        jPanel2.add(jLabelHealer);
        jLabelHealer.setBounds(210, 180, 110, 130);

        jLabelArcher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/archer.png"))); // NOI18N
        jPanel2.add(jLabelArcher);
        jLabelArcher.setBounds(360, 180, 110, 130);

        jButtonFighter.setBackground(new java.awt.Color(255, 51, 51));
        jButtonFighter.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        jButtonFighter.setText("Fighter");
        jButtonFighter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonFighter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonFighterMouseClicked(evt);
            }
        });
        jPanel2.add(jButtonFighter);
        jButtonFighter.setBounds(80, 317, 80, 25);

        jButtonHealer.setBackground(new java.awt.Color(204, 255, 255));
        jButtonHealer.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        jButtonHealer.setText("Healer");
        jButtonHealer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonHealer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonHealerMouseClicked(evt);
            }
        });
        jPanel2.add(jButtonHealer);
        jButtonHealer.setBounds(230, 317, 80, 25);

        jButtonArcher.setBackground(new java.awt.Color(153, 255, 0));
        jButtonArcher.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        jButtonArcher.setText("Archer");
        jButtonArcher.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonArcher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonArcherMouseClicked(evt);
            }
        });
        jPanel2.add(jButtonArcher);
        jButtonArcher.setBounds(370, 317, 80, 25);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tavern.jpg"))); // NOI18N
        jPanel2.add(jLabel1);
        jLabel1.setBounds(0, 0, 560, 410);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonContinueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonContinueMouseClicked
        // TODO add your handling code here:
        buttonPressedNo ++;
        if (buttonPressedNo==1){
            jLabeltext.setText("<html> Let's pick your class! <html>");
        }
        if (buttonPressedNo==2){
            jLabeltext.setText("<html> I'm already the wizard! <html>");
            jLabelArcher.setVisible(true);
            jLabelFighter.setVisible(true);
            jLabelHealer.setVisible(true);
            jButtonFighter.setVisible(true);
            jButtonArcher.setVisible(true);
            jButtonHealer.setVisible(true);
            jButtonContinue.setVisible(false);
        }
        if (buttonPressedNo==4){
            AdventureSelection ads = new AdventureSelection();
            ads.setVisible(true);
            ads.pack();
            ads.passData(user, this.Class, "register");
            ads.setLocationRelativeTo(null);
            this.dispose();
        }
    }//GEN-LAST:event_jButtonContinueMouseClicked

    private void CloseButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseButtonMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_CloseButtonMouseClicked

    private void MinimizeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinimizeButtonMouseClicked
        // TODO add your handling code here:
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_MinimizeButtonMouseClicked

    private void jLabelFighterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelFighterMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelFighterMouseClicked

    private void jButtonFighterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonFighterMouseClicked
        // TODO add your handling code here:
        PreparedStatement ps;
        String username = this.user;
        String query = "UPDATE `users` SET `Class`= 'fighter' WHERE `username` = ?";        
        
        try {
            ps = My_CNX.getConnection().prepareStatement(query);
            ps.setString(1, username);
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null, "Class selected!");                          
            }
        } catch (SQLException ex) {
            Logger.getLogger(BasicsForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.Class= "fighter";
        buttonPressedNo ++;
        if (buttonPressedNo==3){
            jLabeltext.setText("<html> A Fighter! I see you've got strength in you! <html>");
            jButtonArcher.setVisible(false);
            jButtonHealer.setVisible(false);
            jButtonFighter.setVisible(false);
            jLabelArcher.setVisible(false);
            jLabelHealer.setVisible(false);
            jButtonContinue.setVisible(true);
        }
                  
    }//GEN-LAST:event_jButtonFighterMouseClicked

    private void jButtonHealerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonHealerMouseClicked
        // TODO add your handling code here:
        PreparedStatement ps;
        String username = this.user;
        String query = "UPDATE `users` SET `Class`= 'healer' WHERE `username` = ?";        

        try {
            ps = My_CNX.getConnection().prepareStatement(query);
            ps.setString(1, username);
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null, "Class selected!");                          
            }
        } catch (SQLException ex) {
            Logger.getLogger(BasicsForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.Class= "healer";
        buttonPressedNo ++;
        if (buttonPressedNo==3){
            jLabeltext.setText("<html> A Healer! The brave protector of those in need! <html>");
            jButtonFighter.setVisible(false);
            jButtonArcher.setVisible(false);
            jButtonHealer.setVisible(false);
            jLabelFighter.setVisible(false);
            jLabelArcher.setVisible(false);
            jButtonContinue.setVisible(true);
        }
    }//GEN-LAST:event_jButtonHealerMouseClicked

    private void jButtonArcherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonArcherMouseClicked
        // TODO add your handling code here:
        PreparedStatement ps;
        String username = this.user;
        String query = "UPDATE `users` SET `Class`= 'archer' WHERE `username` = ?";        
     
        try {
            ps = My_CNX.getConnection().prepareStatement(query);
            ps.setString(1, username);
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null, "Class selected!");                          
            }
        } catch (SQLException ex) {
            Logger.getLogger(BasicsForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.Class= "archer";
        buttonPressedNo ++;
        if (buttonPressedNo==3){
            jLabeltext.setText("<html> An Archer! I can tell you've got keen eyes! <html>");
            jButtonFighter.setVisible(false);
            jButtonArcher.setVisible(false);
            jButtonHealer.setVisible(false);
            jLabelFighter.setVisible(false);
            jLabelHealer.setVisible(false);
            jButtonContinue.setVisible(true);
        }
    }//GEN-LAST:event_jButtonArcherMouseClicked

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
            java.util.logging.Logger.getLogger(BasicsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BasicsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BasicsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BasicsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BasicsForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CloseButton;
    private javax.swing.JLabel MinimizeButton;
    private javax.swing.JButton jButtonArcher;
    private javax.swing.JButton jButtonContinue;
    private javax.swing.JButton jButtonFighter;
    private javax.swing.JButton jButtonHealer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelArcher;
    private javax.swing.JLabel jLabelFighter;
    private javax.swing.JLabel jLabelHealer;
    private javax.swing.JLabel jLabeltext;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
