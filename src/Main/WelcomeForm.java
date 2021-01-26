package Main;

import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Gustavo
 */
public class WelcomeForm extends javax.swing.JFrame {

    private int buttonPressedNo = 0;
    DBDataUser UserData;
    
    /**
     * Creates new form MenuForm
     */
    public WelcomeForm() {
        initComponents();
        this.setLocationRelativeTo(null);
        setIcon();
        UserData= new DBDataUser();
    }
    
    /**
     * set app icon
     */
    public void setIcon(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/wizard.png")));
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
        closeMinPanel = new javax.swing.JPanel();
        closeButton = new javax.swing.JLabel();
        minimizeButton = new javax.swing.JLabel();
        welcomeLabel = new javax.swing.JLabel();
        wizImg = new javax.swing.JLabel();
        wizSpeach = new javax.swing.JLabel();
        wizBallon = new javax.swing.JLabel();
        continueButton = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 500));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(null);

        closeMinPanel.setBackground(new java.awt.Color(204, 255, 204));
        closeMinPanel.setBorder(new javax.swing.border.MatteBorder(null));

        closeButton.setFont(new java.awt.Font("Book Antiqua", 1, 24)); // NOI18N
        closeButton.setForeground(new java.awt.Color(255, 255, 255));
        closeButton.setText("X");
        closeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeButtonMouseClicked(evt);
            }
        });

        minimizeButton.setFont(new java.awt.Font("Book Antiqua", 1, 48)); // NOI18N
        minimizeButton.setForeground(new java.awt.Color(255, 255, 255));
        minimizeButton.setText("-");
        minimizeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minimizeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout closeMinPanelLayout = new javax.swing.GroupLayout(closeMinPanel);
        closeMinPanel.setLayout(closeMinPanelLayout);
        closeMinPanelLayout.setHorizontalGroup(
            closeMinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, closeMinPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(minimizeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        closeMinPanelLayout.setVerticalGroup(
            closeMinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(closeMinPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(closeMinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(minimizeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeButton, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        jPanel1.add(closeMinPanel);
        closeMinPanel.setBounds(620, 0, 80, 30);

        welcomeLabel.setFont(new java.awt.Font("Book Antiqua", 1, 36)); // NOI18N
        welcomeLabel.setForeground(new java.awt.Color(255, 255, 255));
        welcomeLabel.setText("Welcome!");
        jPanel1.add(welcomeLabel);
        welcomeLabel.setBounds(10, 10, 170, 47);

        wizImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/wizard.png"))); // NOI18N
        jPanel1.add(wizImg);
        wizImg.setBounds(40, 280, 170, 170);

        wizSpeach.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        wizSpeach.setText("<html> Greetings adventurer! <html>");
        wizSpeach.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(wizSpeach);
        wizSpeach.setBounds(210, 200, 150, 100);

        wizBallon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ballon1.png"))); // NOI18N
        jPanel1.add(wizBallon);
        wizBallon.setBounds(180, 180, 210, 150);

        continueButton.setBackground(new java.awt.Color(153, 153, 255));
        continueButton.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        continueButton.setForeground(new java.awt.Color(255, 255, 255));
        continueButton.setText("Continue");
        continueButton.setBorder(new javax.swing.border.MatteBorder(null));
        continueButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        continueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                continueButtonMouseClicked(evt);
            }
        });
        jPanel1.add(continueButton);
        continueButton.setBounds(280, 460, 160, 30);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/backgroundTown.png"))); // NOI18N
        background.setText("jLabel2");
        background.setMinimumSize(new java.awt.Dimension(700, 500));
        background.setPreferredSize(new java.awt.Dimension(700, 500));
        jPanel1.add(background);
        background.setBounds(0, 0, 700, 500);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseClicked
        UserData.deleteUserAcc();
        try {
            DBCommunication.DBDisconnect();
        } catch (SQLException ex) {
            Logger.getLogger(GameplayUserHomeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }//GEN-LAST:event_closeButtonMouseClicked

    private void minimizeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeButtonMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_minimizeButtonMouseClicked

    private void continueButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_continueButtonMouseClicked
        // TODO add your handling code here:
        buttonPressedNo++;
        if (buttonPressedNo == 1) {
            wizSpeach.setText("<html>Welcome to VitaQuest!<html>");
        }
        if (buttonPressedNo == 2) {
            wizSpeach.setText("<html>I am Curuga Noxeror, a human being on planet earth with some knowledge to share. I'll be your friendly tutor!<html>");
        }
        if (buttonPressedNo == 3) {
            wizSpeach.setText("<html>Are you ready to start your adventure?<html>");

            continueButton.setText("Yes!");
        }
        if (buttonPressedNo == 4) {
            wizSpeach.setText("<html>That's the spirit!<html>");
            continueButton.setText("Let's go!");
        }
        if (buttonPressedNo == 5) {
            wizSpeach.setText("<html>Let's get to the Tavern and talk there!<html>");
            continueButton.setText("Let's get a beer!");
        }
        if (buttonPressedNo == 6) {
            BasicsForm bsf = new BasicsForm();
            bsf.setVisible(true);
            bsf.pack();
            bsf.setLocationRelativeTo(null);
            this.dispose();
        }
    }//GEN-LAST:event_continueButtonMouseClicked

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
            java.util.logging.Logger.getLogger(WelcomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WelcomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WelcomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WelcomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new WelcomeForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel closeButton;
    private javax.swing.JPanel closeMinPanel;
    private javax.swing.JButton continueButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel minimizeButton;
    private javax.swing.JLabel welcomeLabel;
    private javax.swing.JLabel wizBallon;
    private javax.swing.JLabel wizImg;
    private javax.swing.JLabel wizSpeach;
    // End of variables declaration//GEN-END:variables

}
