package Main;

import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Gustavo
 */
public class AdventureSelection extends javax.swing.JFrame {

    private int buttonPressedNo;    
    private static String Quest;
    public static String Location;
    public boolean aux = false;
    DBDataUser UserData;
    DBDataChall ChallData;
    
    
    public AdventureSelection() {
        UserData= new DBDataUser();
        initComponents();
        setIcon();
        setInitialVisuals();
        setWizWelcome();
    }
    
    /**
     * sets the location according to the class that calls AdventureSelection
     * @param location 
     */
    public static void setLocation(String location){
        AdventureSelection.Location = location;
    }
    
    /**
     * sets the welcome speach of the wizard according to the location that the users comes from 
     */
    private void setWizWelcome(){
        System.out.println("2"+AdventureSelection.Location);
        if("register".equals(AdventureSelection.Location)){ wizSpeach.setText("<html>As any ambitious human, you need a focus, a direction, a quest! <html>"); }
        if(AdventureSelection.Location.equals("newAdventure")){ wizSpeach.setText("<html>The thirst of knowledge must never, never cease.<html>"); }
    }
    
    /**
     * sets the inital visibility for labels/buttons and images
     */
    private void setInitialVisuals(){
        academicImg.setVisible(false);
        academicButton.setVisible(false);
        academicLabel.setVisible(false);
        fitnessImg.setVisible(false);
        fitnessButton.setVisible(false);
        mindImg.setVisible(false);
        mindButton.setVisible(false);
        mindLabel.setVisible(false);
    }
    
    /**
     * set app icon
     */
    private void setIcon(){
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
        wizImg = new javax.swing.JLabel();
        wizSpeach = new javax.swing.JLabel();
        wizBallon = new javax.swing.JLabel();
        academicImg = new javax.swing.JLabel();
        academicLabel = new javax.swing.JLabel();
        academicButton = new javax.swing.JButton();
        fitnessImg = new javax.swing.JLabel();
        fitnessButton = new javax.swing.JButton();
        mindImg = new javax.swing.JLabel();
        mindLabel = new javax.swing.JLabel();
        mindButton = new javax.swing.JButton();
        continueButton = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 500));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(700, 500));
        jPanel1.setLayout(null);

        closeMinPanel.setBackground(new java.awt.Color(153, 102, 0));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
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

        wizImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/wizard2.png"))); // NOI18N
        jPanel1.add(wizImg);
        wizImg.setBounds(20, 80, 120, 130);

        wizSpeach.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        jPanel1.add(wizSpeach);
        wizSpeach.setBounds(170, 20, 160, 80);

        wizBallon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/balloon22.png"))); // NOI18N
        jPanel1.add(wizBallon);
        wizBallon.setBounds(140, 0, 240, 130);

        academicImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/book.png"))); // NOI18N
        jPanel1.add(academicImg);
        academicImg.setBounds(120, 270, 90, 70);

        academicLabel.setFont(fitnessButton.getFont());
        academicLabel.setForeground(new java.awt.Color(255, 255, 255));
        academicLabel.setText("<html>Improve     Academic  Performance");
        academicLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        academicLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        academicLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(academicLabel);
        academicLabel.setBounds(120, 350, 110, 40);

        academicButton.setBackground(new java.awt.Color(51, 153, 255));
        academicButton.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        academicButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        academicButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                academicButtonMouseClicked(evt);
            }
        });
        jPanel1.add(academicButton);
        academicButton.setBounds(110, 350, 120, 40);

        fitnessImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/SwordAndShield.png"))); // NOI18N
        jPanel1.add(fitnessImg);
        fitnessImg.setBounds(310, 240, 100, 100);

        fitnessButton.setBackground(new java.awt.Color(255, 51, 51));
        fitnessButton.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        fitnessButton.setForeground(new java.awt.Color(255, 255, 255));
        fitnessButton.setText("<html>Improve Fitness <html>");
        fitnessButton.setBorder(new javax.swing.border.MatteBorder(null));
        fitnessButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        fitnessButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fitnessButton.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        fitnessButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fitnessButtonMouseClicked(evt);
            }
        });
        jPanel1.add(fitnessButton);
        fitnessButton.setBounds(300, 350, 120, 40);

        mindImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/heart.png"))); // NOI18N
        jPanel1.add(mindImg);
        mindImg.setBounds(510, 260, 90, 90);

        mindLabel.setFont(academicLabel.getFont());
        mindLabel.setForeground(new java.awt.Color(255, 255, 255));
        mindLabel.setText("<html>Achieve Peace of Mind<html>");
        mindLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(mindLabel);
        mindLabel.setBounds(510, 350, 90, 40);

        mindButton.setBackground(new java.awt.Color(102, 255, 102));
        mindButton.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        mindButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mindButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mindButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mindButtonMouseClicked(evt);
            }
        });
        jPanel1.add(mindButton);
        mindButton.setBounds(490, 350, 120, 40);

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

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tavern.jpg"))); // NOI18N
        background.setMaximumSize(new java.awt.Dimension(700, 500));
        background.setMinimumSize(new java.awt.Dimension(700, 500));
        background.setPreferredSize(new java.awt.Dimension(700, 500));
        jPanel1.add(background);
        background.setBounds(0, 0, 700, 500);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 500));

        pack();
        setLocationRelativeTo(null);
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
        buttonPressedNo ++;
        if (buttonPressedNo==1){
            if(AdventureSelection.Location.equals("register")){wizSpeach.setText("<html>What type of adventure are you after? <html>");}
            if(AdventureSelection.Location.equals("newAdventure")){wizSpeach.setText("<html>What adventure are we looking after this time? <html>");}
            academicImg.setVisible(true);
            academicButton.setVisible(true);
            academicLabel.setVisible(true);
            fitnessImg.setVisible(true);
            fitnessButton.setVisible(true);
            mindImg.setVisible(true);
            mindButton.setVisible(true);
            mindLabel.setVisible(true);
            continueButton.setVisible(false);
        }
        if(buttonPressedNo == 3){
            TutorialForm ttf = new TutorialForm();
            ttf.setLocation(AdventureSelection.Location);
            ttf.setVisible(true);
            ttf.pack();
            ttf.setLocationRelativeTo(null);
            this.dispose();
        }
    }//GEN-LAST:event_continueButtonMouseClicked

    private void fitnessButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fitnessButtonMouseClicked
        AdventureSelection.Quest= "fitness";
        UserData.updateUserQuest(AdventureSelection.Quest);
        JOptionPane.showMessageDialog(null, "Quest selected!");
        
        buttonPressedNo ++;
        if (buttonPressedNo==2){
            wizSpeach.setText("<html>Let's start! <html>");
            academicImg.setVisible(false);
            academicButton.setVisible(false);
            academicLabel.setVisible(false);
            fitnessImg.setVisible(true);
            fitnessButton.setVisible(false);
            mindImg.setVisible(false);
            mindButton.setVisible(false);
            mindLabel.setVisible(false);
            continueButton.setVisible(true);
        }
    }//GEN-LAST:event_fitnessButtonMouseClicked

    private void academicButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_academicButtonMouseClicked
        AdventureSelection.Quest= "academic";
        UserData.updateUserQuest(AdventureSelection.Quest);
        JOptionPane.showMessageDialog(null, "Quest selected!");
        
        buttonPressedNo ++;
        if (buttonPressedNo==2){
            wizSpeach.setText("<html>Let's start! <html>");
            academicImg.setVisible(true);
            academicButton.setVisible(false);
            academicLabel.setVisible(false);
            fitnessImg.setVisible(false);
            fitnessButton.setVisible(false);
            mindImg.setVisible(false);
            mindButton.setVisible(false);
            mindLabel.setVisible(false);
            continueButton.setVisible(true);
        }
    }//GEN-LAST:event_academicButtonMouseClicked

    private void mindButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mindButtonMouseClicked
        AdventureSelection.Quest = "mind";
        UserData.updateUserQuest(AdventureSelection.Quest);
        JOptionPane.showMessageDialog(null, "Quest selected!");
        
        buttonPressedNo ++;  
        if (buttonPressedNo==2){
            wizSpeach.setText("<html>Let's start! <html>");
            academicImg.setVisible(false);
            academicButton.setVisible(false);
            academicLabel.setVisible(false);
            fitnessImg.setVisible(false);
            fitnessButton.setVisible(false);
            mindImg.setVisible(true);
            mindButton.setVisible(false);
            mindLabel.setVisible(false);
            continueButton.setVisible(true);
        }
    }//GEN-LAST:event_mindButtonMouseClicked
    
     
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdventureSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new AdventureSelection().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton academicButton;
    private javax.swing.JLabel academicImg;
    private javax.swing.JLabel academicLabel;
    private javax.swing.JLabel background;
    private javax.swing.JLabel closeButton;
    private javax.swing.JPanel closeMinPanel;
    private javax.swing.JButton continueButton;
    private javax.swing.JButton fitnessButton;
    private javax.swing.JLabel fitnessImg;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton mindButton;
    private javax.swing.JLabel mindImg;
    private javax.swing.JLabel mindLabel;
    private javax.swing.JLabel minimizeButton;
    private javax.swing.JLabel wizBallon;
    private javax.swing.JLabel wizImg;
    private javax.swing.JLabel wizSpeach;
    // End of variables declaration//GEN-END:variables
}