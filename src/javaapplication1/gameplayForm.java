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
    private String Quest , Class , Chall_text;
    private int Chall_id , Chall_coins , flag;
    private int buttonPressedNo = 0 , buttonCat1 = 0 , buttonCat2 = 0;
    private final String[] quests = {"academic" , "fitness" , "mind"};
    private boolean challOn;
    
  
    /**
     * Creates new form gameplayForm
     */
    public gameplayForm() {
        initComponents();
        setIcon();
    }
    public void setIcon(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/wizard.png"))); 
    }
    
    public void wizardLabelsTimer(){ 
        Timer timer = new Timer(2000, new ActionListener() {public void actionPerformed(ActionEvent e) {
            WizardBallonWelcome.setVisible(false);
            WizardSpeachWelcome.setVisible(false);
            jButtonChallenge.setVisible(true);
            jButtonReturn.setVisible(true);} });
        timer.setRepeats(false);
        timer.start();

    }    
    
    public void FillRank( boolean update , String Quest ){
        PreparedStatement st;
        ResultSet rs;
        DefaultTableModel tb1Model = null;
        String query = "SELECT * FROM `users` WHERE `Quest`= ? ORDER BY `wallet` DESC";
        
        if(Quest.equals("academic")){ tb1Model = (DefaultTableModel)jAcademicRank.getModel();}
        if(Quest.equals("fitness")){ tb1Model = (DefaultTableModel)jFitnessRank.getModel();}
        if(Quest.equals("mind")){ tb1Model = (DefaultTableModel)jMindRank.getModel();}
        
        //UPDATES EVERY TABLE QUEST FRIST TIME 
        if(!update){

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
        //UPDATE USER QUEST TABLE AFTER COMPLETE CHALLENGE 
        if(update){
            int rowCount = tb1Model.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) { tb1Model.removeRow(i);}
            
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
    
    public void passData(String user, boolean welcomeback) {
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
        
        //SET CHALLENGE STUFF NOT VISIBLE AND FILLS THE RANK TABLE FOR THE FRIST TIME 
        jCatalfRankDisplayPane.setVisible(false);
        jButtonComplete.setVisible(false);
        jButtonChallenge.setVisible(false);
        jButtonReturn.setVisible(false);
        WizardBallonChall.setVisible(false);
        WizardSpeachChall.setVisible(false);
        FillRank(false,quests[0]);
        FillRank(false,quests[1]);
        FillRank(false,quests[2]);
        
        //SET BACKGROUND AND IMAGES AND WIZARD WELCOME MESSAGE
        if(welcomeback){
            if ("fitness".equals(Quest)){WizardSpeachWelcome.setText("<html> Welcome back to the Fighters Guild!<html>");}
            if ("academic".equals(Quest)){ WizardSpeachWelcome.setText("<html> Welcome back to the College of Wizardry and Science! <html>");}
            if ("mind".equals(Quest)){ WizardSpeachWelcome.setText("<html> Welcome back to the Arcane Order of the Mind!<html>");}
        }
        if(!welcomeback){
            if ("fitness".equals(Quest)){WizardSpeachWelcome.setText("<html> Welcome to the Fighters Guild!<html>");}
            if ("academic".equals(Quest)){ WizardSpeachWelcome.setText("<html> Welcome to the College of Wizardry and Science! <html>");}
            if ("mind".equals(Quest)){ WizardSpeachWelcome.setText("<html> Welcome to the Arcane Order of the Mind!<html>");}
        }
        
        if ("fitness".equals(Quest)){
            Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fitnessCenter.png")));
            flag=1;
        }
        if ("academic".equals(Quest)){
            Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/academicCenter.png")));
            flag=2;
        }
        if ("mind".equals(Quest)){
            Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mindCenter.png")));
            flag=3;
        }
       
        wizardLabelsTimer();
      
        if ("archer".equals(Class)){ jLabelCharacterImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/archer2.png")));}
        if ("fighter".equals(Class)){ jLabelCharacterImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fighter2.png")));}
        if ("healer".equals(Class)){ jLabelCharacterImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/healer2.png")));}
        
        jLabelName.setText(user+" the "+Class);
        jPanel2.setVisible(true);
        jLabelCoin.setVisible(true);
        jLabelMoney.setVisible(true);
        jPanelMoney.setVisible(true);
        
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
        WizardSpeachWelcome = new javax.swing.JLabel();
        WizardBallonWelcome = new javax.swing.JLabel();
        WizardSpeachChall = new javax.swing.JLabel();
        WizardBallonChall = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelName = new javax.swing.JLabel();
        jLabelCharacterImage = new javax.swing.JLabel();
        jPanelMoney = new javax.swing.JPanel();
        jLabelCoin = new javax.swing.JLabel();
        jLabelMoney = new javax.swing.JLabel();
        jLabelCat = new javax.swing.JLabel();
        jButtonCatalf = new javax.swing.JButton();
        jCatalfRankDisplayPane = new javax.swing.JDesktopPane();
        jAcademicRank = new javax.swing.JTable();
        jLabelAcademicRank = new javax.swing.JLabel();
        jFitnessRank = new javax.swing.JTable();
        jLabelFitnessRank = new javax.swing.JLabel();
        jMindRank = new javax.swing.JTable();
        jLabelMindRank = new javax.swing.JLabel();
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
        jLabel2.setBounds(30, 50, 120, 130);

        WizardSpeachWelcome.setFont(new java.awt.Font("Book Antiqua", 1, 16)); // NOI18N
        jPanel1.add(WizardSpeachWelcome);
        WizardSpeachWelcome.setBounds(200, 20, 280, 90);

        WizardBallonWelcome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/balloon3.png"))); // NOI18N
        jPanel1.add(WizardBallonWelcome);
        WizardBallonWelcome.setBounds(150, 0, 360, 140);

        WizardSpeachChall.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        jPanel1.add(WizardSpeachChall);
        WizardSpeachChall.setBounds(200, 150, 280, 170);

        WizardBallonChall.setFont(WizardSpeachWelcome.getFont());
        WizardBallonChall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/art.png"))); // NOI18N
        jPanel1.add(WizardBallonChall);
        WizardBallonChall.setBounds(140, 120, 390, 240);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelName.setFont(new java.awt.Font("Book Antiqua", 1, 16)); // NOI18N
        jLabelName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelName, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 410, 160, 30);
        jPanel1.add(jLabelCharacterImage);
        jLabelCharacterImage.setBounds(40, 280, 120, 130);

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
                .addComponent(jLabelMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelCoin, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelMoneyLayout.setVerticalGroup(
            jPanelMoneyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelMoney, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
            .addComponent(jLabelCoin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelMoney);
        jPanelMoney.setBounds(40, 440, 100, 30);

        jLabelCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Catalf.png"))); // NOI18N
        jPanel1.add(jLabelCat);
        jLabelCat.setBounds(530, 40, 160, 150);

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
        jButtonCatalf.setBounds(530, 200, 160, 30);

        jAcademicRank.setModel(new javax.swing.table.DefaultTableModel(
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

        jLabelAcademicRank.setFont(jButtonCatalf.getFont());
        jLabelAcademicRank.setText("Academic");
        jLabelAcademicRank.setBorder(new javax.swing.border.MatteBorder(null));

        jFitnessRank.setModel(new javax.swing.table.DefaultTableModel(
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

        jLabelFitnessRank.setFont(jButtonCatalf.getFont());
        jLabelFitnessRank.setText("Fitness");
        jLabelFitnessRank.setBorder(new javax.swing.border.MatteBorder(null));

        jMindRank.setModel(new javax.swing.table.DefaultTableModel(
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

        jLabelMindRank.setFont(jButtonCatalf.getFont());
        jLabelMindRank.setText("Mind");
        jLabelMindRank.setBorder(new javax.swing.border.MatteBorder(null));

        jCatalfRankDisplayPane.setLayer(jAcademicRank, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jCatalfRankDisplayPane.setLayer(jLabelAcademicRank, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jCatalfRankDisplayPane.setLayer(jFitnessRank, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jCatalfRankDisplayPane.setLayer(jLabelFitnessRank, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jCatalfRankDisplayPane.setLayer(jMindRank, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jCatalfRankDisplayPane.setLayer(jLabelMindRank, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jCatalfRankDisplayPaneLayout = new javax.swing.GroupLayout(jCatalfRankDisplayPane);
        jCatalfRankDisplayPane.setLayout(jCatalfRankDisplayPaneLayout);
        jCatalfRankDisplayPaneLayout.setHorizontalGroup(
            jCatalfRankDisplayPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jCatalfRankDisplayPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jCatalfRankDisplayPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jCatalfRankDisplayPaneLayout.createSequentialGroup()
                        .addGroup(jCatalfRankDisplayPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jFitnessRank, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                            .addComponent(jAcademicRank, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jMindRank, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jCatalfRankDisplayPaneLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelAcademicRank, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jCatalfRankDisplayPaneLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jCatalfRankDisplayPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jCatalfRankDisplayPaneLayout.createSequentialGroup()
                        .addComponent(jLabelFitnessRank, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jCatalfRankDisplayPaneLayout.createSequentialGroup()
                        .addComponent(jLabelMindRank, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89))))
        );
        jCatalfRankDisplayPaneLayout.setVerticalGroup(
            jCatalfRankDisplayPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jCatalfRankDisplayPaneLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabelAcademicRank)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jAcademicRank, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabelFitnessRank)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jFitnessRank, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelMindRank)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jMindRank, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jCatalfRankDisplayPane);
        jCatalfRankDisplayPane.setBounds(190, 130, 307, 348);

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
        jButtonChallenge.setBounds(210, 410, 270, 30);

        jLabelMarketMan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/market.png"))); // NOI18N
        jPanel1.add(jLabelMarketMan);
        jLabelMarketMan.setBounds(570, 240, 90, 160);

        JbuttonMarket.setBackground(new java.awt.Color(255, 255, 255));
        JbuttonMarket.setFont(jButtonCatalf.getFont());
        JbuttonMarket.setText("Market");
        JbuttonMarket.setBorder(new javax.swing.border.MatteBorder(null));
        JbuttonMarket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbuttonMarketActionPerformed(evt);
            }
        });
        jPanel1.add(JbuttonMarket);
        JbuttonMarket.setBounds(530, 410, 160, 30);

        jButtonComplete.setText("Complete");
        jButtonComplete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCompleteActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonComplete);
        jButtonComplete.setBounds(230, 450, 110, 20);

        jButtonReturn.setText("Return");
        jButtonReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReturnActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonReturn);
        jButtonReturn.setBounds(356, 450, 110, 20);

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
        
        WizardBallonChall.setVisible(true);
        WizardSpeachChall.setVisible(true);
        
        String queryChall = "SELECT * FROM `challenge` WHERE `chall_quest` = ? AND `chall_id` NOT IN "
                            + "(SELECT `chall_id` FROM `backpack_chall` where `usr`= ? ) ORDER BY RAND() LIMIT 1";
        try {
            st = My_CNX.getConnection().prepareStatement(queryChall);   
            st.setString(1, Quest);
            st.setString(2, user);
            rs = st.executeQuery();
            while(rs.next()){
                //SET UP THE CHALL BALLON AND STORES THE CURRENT CHALL INFO
                this.Chall_text = rs.getString("chall_text");
                this.Chall_id = rs.getInt("chall_id");
                this.Chall_coins = rs.getInt("coins");
                WizardSpeachChall.setText("<html>"+Chall_text+"</html>");
                jButtonComplete.setVisible(true);
                System.out.println(this.Chall_text + this.Chall_id);
            }
            } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_jButtonChallengeMouseClicked

    private void jButtonCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCompleteActionPerformed
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
         
        //CALLS CATRANK TO UPDATE THE USER QUEST RANK
        FillRank(true,Quest);
        
        //SETS THE CHALL BALLON NOT VISIBLE UNTIL IT CLICKS IN NEW CHALL
        WizardBallonChall.setVisible(false);
        WizardSpeachChall.setVisible(false);
        jButtonComplete.setVisible(false);
    }//GEN-LAST:event_jButtonCompleteActionPerformed

    /* ADD RETURN STUFF */
    private void jButtonReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReturnActionPerformed
        AdventureSelection new_adventure=new AdventureSelection();
        new_adventure.passData(this.user,this.Class);
        new_adventure.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonReturnActionPerformed

    private void JbuttonMarketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbuttonMarketActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JbuttonMarketActionPerformed

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
    private javax.swing.JLabel WizardBallonChall;
    private javax.swing.JLabel WizardBallonWelcome;
    private javax.swing.JLabel WizardSpeachChall;
    private javax.swing.JLabel WizardSpeachWelcome;
    public javax.swing.JTable jAcademicRank;
    private javax.swing.JButton jButtonCatalf;
    public javax.swing.JButton jButtonChallenge;
    private javax.swing.JButton jButtonComplete;
    private javax.swing.JButton jButtonReturn;
    public javax.swing.JDesktopPane jCatalfRankDisplayPane;
    public javax.swing.JTable jFitnessRank;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabelAcademicRank;
    private javax.swing.JLabel jLabelCat;
    private javax.swing.JLabel jLabelCharacterImage;
    private javax.swing.JLabel jLabelCoin;
    public javax.swing.JLabel jLabelFitnessRank;
    private javax.swing.JLabel jLabelMarketMan;
    public javax.swing.JLabel jLabelMindRank;
    private javax.swing.JLabel jLabelMoney;
    private javax.swing.JLabel jLabelName;
    public javax.swing.JTable jMindRank;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelMinCloseAll;
    private javax.swing.JPanel jPanelMoney;
    // End of variables declaration//GEN-END:variables
}
