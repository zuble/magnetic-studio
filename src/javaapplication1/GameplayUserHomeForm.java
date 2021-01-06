/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Legion
 */
public class GameplayUserHomeForm extends javax.swing.JFrame {
   
    public String user;
    private String Quest , Class , Chall_text;
    private int Chall_id , Chall_coins , Chall_exp;
    private int wallet , exp;
    private int buttonCat1 = 0;
    private final String[] quests = {"academic" , "fitness" , "mind"};
    private boolean challOn;
    DataChall ChallData;
    DataUser UserData;
    
  
    /**
     * Creates new form gameplayForm
     */
    public GameplayUserHomeForm() {
        initComponents();
        setIcon();
    }
    
    private void setIcon(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/wizard.png"))); 
    }
    
    public void wizardLabelsTimer(){
        Timer timer = new Timer(3000, (ActionEvent e) -> {
            WizardBallonWelcome.setVisible(false);
            WizardSpeachWelcome.setVisible(false);
            jButtonChallenge.setVisible(true);
            jNewAdventureButton.setVisible(true);
            JWizHouseButton.setVisible(true);
            jCatalfButton.setVisible(true);
        });
        timer.setRepeats(false);
        timer.start();
    }    
    
    public void FillRank( boolean update , String Quest ){
        PreparedStatement st;
        ResultSet rs;
        DefaultTableModel tb1Model = null;
        String query = "SELECT * FROM `users` WHERE `Quest`= ? ORDER BY `exp` DESC";
        
        if(Quest.equals("academic")){ tb1Model = (DefaultTableModel)jRankAcademic.getModel();}
        if(Quest.equals("fitness")){ tb1Model = (DefaultTableModel)jRankFitness.getModel();}
        if(Quest.equals("mind")){ tb1Model = (DefaultTableModel)jRankMind.getModel();}
        
        //UPDATES EVERY TABLE QUEST FRIST TIME 
        if(!update){
            try {
                st = My_CNX.getConnection().prepareStatement(query);
                st.setString(1,Quest);
                rs = st.executeQuery();
                while(rs.next()){
                    String data[] = { rs.getString("username"), rs.getString("exp") };
                    tb1Model.addRow(data);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        //UPDATE USER QUEST TABLE AFTER COMPLETE CHALLENGE 
        if(update){
            //CLEARS TABLE
            int rowCount = tb1Model.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) { tb1Model.removeRow(i);}
            try {
                st = My_CNX.getConnection().prepareStatement(query);
                st.setString(1,Quest);
                rs = st.executeQuery();
                while(rs.next()){
                    String data[] = { rs.getString("username"), rs.getString("exp") };
                    tb1Model.addRow(data);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
    }
    
    public void passData(String user, String home) {
        UserData = new DataUser(user);
        this.user = user;
        this.Class = UserData.getUserClass();
        this.Quest = UserData.getUserQuest();
        this.wallet = UserData.getUserWallet();
        this.exp = UserData.getUserExp();
        
        jLabelMoney.setText(String.valueOf(wallet));
        jLabelExp.setText(String.valueOf(exp));
        JUserButton.setText(user+" the "+Class);
        
        System.out.println(this.Quest);
        System.out.println(this.Class);
            
        //SET CHALLENGE STUFF NOT VISIBLE AND FILLS THE RANK TABLE FOR THE FRIST TIME 
        jCatalfButton.setVisible(false);
        jCatalfRankDisplay.setVisible(false);
        JWizHouseButton.setVisible(false);
        jButtonComplete.setVisible(false);
        jButtonChallenge.setVisible(false);
        jNewAdventureButton.setVisible(false);
        WizardBallonChall.setVisible(false);
        WizardSpeachChall.setVisible(false);
        FillRank(false,quests[0]);
        FillRank(false,quests[1]);
        FillRank(false,quests[2]);
        
        //SET BACKGROUND/USER CLASS IMAGE AND WIZARD WELCOME MESSAGE 
        setImgs(true,true,true);
        if("login".equals(home)){
            if ("fitness".equals(Quest)){WizardSpeachWelcome.setText("<html> Welcome back to the Fighters Guild!<html>");}
            if ("academic".equals(Quest)){ WizardSpeachWelcome.setText("<html> Welcome back to the College of Wizardry and Science! <html>");}
            if ("mind".equals(Quest)){ WizardSpeachWelcome.setText("<html> Welcome back to the Arcane Order of the Mind!<html>");}
        }
        if("register".equals(home)){
            if ("fitness".equals(Quest)){WizardSpeachWelcome.setText("<html> Welcome to the Fighters Guild!<html>");}
            if ("academic".equals(Quest)){ WizardSpeachWelcome.setText("<html> Welcome to the College of Wizardry and Science! <html>");}
            if ("mind".equals(Quest)){ WizardSpeachWelcome.setText("<html> Welcome to the Arcane Order of the Mind!<html>");}
        }
        if("WizHome".equals(home)){
            WizardSpeachWelcome.setText("<html> What a good travel !<html>");
        }
        wizardLabelsTimer(); //TIMER FOR THE WELCOME BALOON/SPEACH  
    }
  
    private void setImgs(boolean bckg,boolean userimg , boolean mushie){
        if(bckg){
            if ("fitness".equals(Quest)){ Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fitnessCenter.png")));}
            if ("academic".equals(Quest)){ Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/academicCenter.png")));}
            if ("mind".equals(Quest)){ Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mindCenter.png")));}
        }
        if(userimg){
            if ("archer".equals(Class)){ jUserImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/archer2.png")));}
            if ("fighter".equals(Class)){ jUserImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fighter2.png")));}
            if ("healer".equals(Class)){ jUserImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/healer2.png")));}   
        }
        if(mushie){
            UserData = new DataUser(user);
            this.exp = UserData.getUserExp();
            if(exp <= 40){jLabelMush.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mushies_stage1.png"))); }
            if((exp > 40) && (exp <= 80)){jLabelMush.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mushies_stage2.png"))); }
            if(exp > 80){jLabelMush.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mushies_stage3.png"))); }
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
        jWizardImg = new javax.swing.JLabel();
        jNewAdventureButton = new javax.swing.JButton();
        jCatImg = new javax.swing.JLabel();
        jCatalfButton = new javax.swing.JButton();
        jWizRideImg = new javax.swing.JLabel();
        JWizHouseButton = new javax.swing.JButton();
        jUserImg = new javax.swing.JLabel();
        JUserButton = new javax.swing.JButton();
        UserInfoPanel = new javax.swing.JPanel();
        jLabelMush = new javax.swing.JLabel();
        jLabelExp = new javax.swing.JLabel();
        jLabelCoin = new javax.swing.JLabel();
        jLabelMoney = new javax.swing.JLabel();
        jButtonComplete = new javax.swing.JButton();
        jButtonChallenge = new javax.swing.JButton();
        jCatalfRankDisplay = new javax.swing.JPanel();
        jRankImg = new javax.swing.JLabel();
        jLabelAcademic = new javax.swing.JLabel();
        jRankAcademic = new javax.swing.JTable();
        jLabelFitness = new javax.swing.JLabel();
        jRankFitness = new javax.swing.JTable();
        jLabelMind = new javax.swing.JLabel();
        jRankMind = new javax.swing.JTable();
        WizardSpeachWelcome = new javax.swing.JLabel();
        WizardBallonWelcome = new javax.swing.JLabel();
        WizardSpeachChall = new javax.swing.JLabel();
        WizardBallonChall = new javax.swing.JLabel();
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

        jWizardImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/wizard2.png"))); // NOI18N
        jPanel1.add(jWizardImg);
        jWizardImg.setBounds(20, 50, 120, 130);

        jNewAdventureButton.setBackground(new java.awt.Color(255, 123, 17));
        jNewAdventureButton.setFont(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        jNewAdventureButton.setForeground(new java.awt.Color(255, 255, 255));
        jNewAdventureButton.setText("New Adventure");
        jNewAdventureButton.setBorder(new javax.swing.border.MatteBorder(null));
        jNewAdventureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNewAdventureButtonActionPerformed(evt);
            }
        });
        jPanel1.add(jNewAdventureButton);
        jNewAdventureButton.setBounds(10, 180, 140, 30);

        jCatImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Catalf.png"))); // NOI18N
        jPanel1.add(jCatImg);
        jCatImg.setBounds(550, 40, 130, 130);

        jCatalfButton.setBackground(new java.awt.Color(204, 102, 255));
        jCatalfButton.setFont(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        jCatalfButton.setForeground(new java.awt.Color(255, 255, 255));
        jCatalfButton.setText("Catalf the Grey");
        jCatalfButton.setBorder(new javax.swing.border.MatteBorder(null));
        jCatalfButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCatalfButtonMouseClicked(evt);
            }
        });
        jPanel1.add(jCatalfButton);
        jCatalfButton.setBounds(530, 170, 160, 30);

        jWizRideImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/dragon.png"))); // NOI18N
        jPanel1.add(jWizRideImg);
        jWizRideImg.setBounds(550, 260, 130, 160);

        JWizHouseButton.setBackground(new java.awt.Color(170, 63, 7));
        JWizHouseButton.setFont(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        JWizHouseButton.setForeground(new java.awt.Color(255, 255, 255));
        JWizHouseButton.setText("Wizard House");
        JWizHouseButton.setBorder(new javax.swing.border.MatteBorder(null));
        JWizHouseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JWizHouseButtonMouseClicked(evt);
            }
        });
        jPanel1.add(JWizHouseButton);
        JWizHouseButton.setBounds(530, 410, 160, 30);
        jPanel1.add(jUserImg);
        jUserImg.setBounds(30, 280, 120, 130);

        JUserButton.setBackground(new java.awt.Color(247, 193, 82));
        JUserButton.setFont(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        JUserButton.setForeground(new java.awt.Color(255, 255, 255));
        JUserButton.setBorder(new javax.swing.border.MatteBorder(null));
        JUserButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JUserButtonMouseClicked(evt);
            }
        });
        jPanel1.add(JUserButton);
        JUserButton.setBounds(10, 410, 140, 30);

        UserInfoPanel.setBackground(new java.awt.Color(255, 255, 204));
        UserInfoPanel.setBorder(new javax.swing.border.MatteBorder(null));
        UserInfoPanel.setOpaque(false);

        jLabelMush.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mushies_stage1.png"))); // NOI18N

        jLabelExp.setBackground(new java.awt.Color(255, 204, 51));
        jLabelExp.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabelExp.setForeground(new java.awt.Color(255, 255, 255));
        jLabelExp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabelCoin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/coin.png"))); // NOI18N

        jLabelMoney.setBackground(new java.awt.Color(255, 204, 51));
        jLabelMoney.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabelMoney.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMoney.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout UserInfoPanelLayout = new javax.swing.GroupLayout(UserInfoPanel);
        UserInfoPanel.setLayout(UserInfoPanelLayout);
        UserInfoPanelLayout.setHorizontalGroup(
            UserInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCoin, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelExp, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelMush)
                .addGap(40, 40, 40))
        );
        UserInfoPanelLayout.setVerticalGroup(
            UserInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelMush, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(UserInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(UserInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelCoin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabelMoney, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelExp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel1.add(UserInfoPanel);
        UserInfoPanel.setBounds(10, 440, 140, 40);

        jButtonComplete.setBackground(new java.awt.Color(103, 209, 81));
        jButtonComplete.setFont(jCatalfButton.getFont());
        jButtonComplete.setForeground(new java.awt.Color(255, 255, 255));
        jButtonComplete.setText("Complete");
        jButtonComplete.setBorder(new javax.swing.border.MatteBorder(null));
        jButtonComplete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCompleteActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonComplete);
        jButtonComplete.setBounds(260, 440, 180, 30);

        jButtonChallenge.setBackground(new java.awt.Color(255, 66, 47));
        jButtonChallenge.setFont(new java.awt.Font("Book Antiqua", 1, 16)); // NOI18N
        jButtonChallenge.setForeground(new java.awt.Color(255, 255, 255));
        jButtonChallenge.setText("New challenge");
        jButtonChallenge.setBorder(new javax.swing.border.MatteBorder(null));
        jButtonChallenge.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonChallengeMouseClicked(evt);
            }
        });
        jPanel1.add(jButtonChallenge);
        jButtonChallenge.setBounds(210, 410, 280, 30);

        jCatalfRankDisplay.setOpaque(false);

        jRankImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/rank.png"))); // NOI18N

        jLabelAcademic.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        jLabelAcademic.setText("Academic");

        jRankAcademic.setFont(new java.awt.Font("Book Antiqua", 0, 12)); // NOI18N
        jRankAcademic.setForeground(new java.awt.Color(0, 0, 0));
        jRankAcademic.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Coins"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jRankAcademic.setOpaque(false);
        jRankAcademic.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jRankAcademic.getTableHeader().setReorderingAllowed(false);

        jLabelFitness.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        jLabelFitness.setText("Fitness");

        jRankFitness.setFont(new java.awt.Font("Book Antiqua", 0, 12)); // NOI18N
        jRankFitness.setForeground(new java.awt.Color(0, 0, 0));
        jRankFitness.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Coins"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jRankFitness.setOpaque(false);
        jRankFitness.setRowSelectionAllowed(false);
        jRankFitness.setShowHorizontalLines(false);
        jRankFitness.setShowVerticalLines(false);
        jRankFitness.getTableHeader().setReorderingAllowed(false);

        jLabelMind.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        jLabelMind.setText("Mind");

        jRankMind.setFont(new java.awt.Font("Book Antiqua", 0, 12)); // NOI18N
        jRankMind.setForeground(new java.awt.Color(0, 0, 0));
        jRankMind.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Coins"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jRankMind.setOpaque(false);
        jRankMind.setShowGrid(false);
        jRankMind.getTableHeader().setReorderingAllowed(false);

        javax.swing.GroupLayout jCatalfRankDisplayLayout = new javax.swing.GroupLayout(jCatalfRankDisplay);
        jCatalfRankDisplay.setLayout(jCatalfRankDisplayLayout);
        jCatalfRankDisplayLayout.setHorizontalGroup(
            jCatalfRankDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jCatalfRankDisplayLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jCatalfRankDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jCatalfRankDisplayLayout.createSequentialGroup()
                        .addComponent(jLabelMind)
                        .addGap(158, 158, 158))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jCatalfRankDisplayLayout.createSequentialGroup()
                        .addComponent(jLabelFitness)
                        .addGap(149, 149, 149))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jCatalfRankDisplayLayout.createSequentialGroup()
                .addContainerGap(152, Short.MAX_VALUE)
                .addComponent(jLabelAcademic)
                .addGap(143, 143, 143))
            .addGroup(jCatalfRankDisplayLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addGroup(jCatalfRankDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jRankAcademic, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(jRankMind, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jRankFitness, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jCatalfRankDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jCatalfRankDisplayLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jRankImg)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jCatalfRankDisplayLayout.setVerticalGroup(
            jCatalfRankDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jCatalfRankDisplayLayout.createSequentialGroup()
                .addContainerGap(96, Short.MAX_VALUE)
                .addComponent(jLabelAcademic)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRankAcademic, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelFitness)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRankFitness, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelMind)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRankMind, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addGap(91, 91, 91))
            .addGroup(jCatalfRankDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jCatalfRankDisplayLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jRankImg)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        if (jRankAcademic.getColumnModel().getColumnCount() > 0) {
            jRankAcademic.getColumnModel().getColumn(0).setResizable(false);
            jRankAcademic.getColumnModel().getColumn(1).setResizable(false);
        }
        if (jRankFitness.getColumnModel().getColumnCount() > 0) {
            jRankFitness.getColumnModel().getColumn(0).setResizable(false);
            jRankFitness.getColumnModel().getColumn(1).setResizable(false);
        }
        if (jRankMind.getColumnModel().getColumnCount() > 0) {
            jRankMind.getColumnModel().getColumn(0).setResizable(false);
            jRankMind.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel1.add(jCatalfRankDisplay);
        jCatalfRankDisplay.setBounds(160, 40, 350, 430);

        WizardSpeachWelcome.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        jPanel1.add(WizardSpeachWelcome);
        WizardSpeachWelcome.setBounds(200, 20, 280, 90);

        WizardBallonWelcome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/balloon3.png"))); // NOI18N
        jPanel1.add(WizardBallonWelcome);
        WizardBallonWelcome.setBounds(150, 0, 360, 140);

        WizardSpeachChall.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        jPanel1.add(WizardSpeachChall);
        WizardSpeachChall.setBounds(200, 140, 280, 170);

        WizardBallonChall.setFont(WizardSpeachWelcome.getFont());
        WizardBallonChall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/art.png"))); // NOI18N
        jPanel1.add(WizardBallonChall);
        WizardBallonChall.setBounds(140, 110, 390, 240);

        Background.setBackground(new java.awt.Color(255, 255, 51));
        Background.setFont(new java.awt.Font("Book Antiqua", 1, 24)); // NOI18N
        jPanel1.add(Background);
        Background.setBounds(0, 0, 700, 500);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 500));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CloseButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseButtonMouseClicked
        System.exit(0);
    }//GEN-LAST:event_CloseButtonMouseClicked

    private void MinimizeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinimizeButtonMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_MinimizeButtonMouseClicked

    private void jCatalfButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCatalfButtonMouseClicked
        
        if( buttonCat1 == 0 ){
            jCatalfRankDisplay.setVisible(true);
            WizardBallonChall.setVisible(false);
            WizardSpeachChall.setVisible(false);
            jButtonComplete.setVisible(false);
            jButtonChallenge.setVisible(false);
            buttonCat1 = 1;
        }
        else if( buttonCat1 == 1 ){
            jCatalfRankDisplay.setVisible(false);
            jButtonChallenge.setVisible(true);
            if( challOn ){
                WizardBallonChall.setVisible(true);
                WizardSpeachChall.setVisible(true);
                jButtonComplete.setVisible(true);
            }
            buttonCat1 = 0;
        }
    }//GEN-LAST:event_jCatalfButtonMouseClicked

    private void jButtonChallengeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonChallengeMouseClicked
        ChallData = new DataChall(user,Quest);
        
        WizardBallonChall.setVisible(true);
        WizardSpeachChall.setVisible(true);
      
        this.Chall_id = ChallData.getChallId();
        this.Chall_text = ChallData.getChallText();
        this.Chall_coins = ChallData.getChallCoins();
        this.Chall_exp = ChallData.getChallExp();
        
        WizardSpeachChall.setText("<html>"+Chall_text+"</html>");
        jButtonComplete.setVisible(true);
        System.out.println(this.Chall_text + this.Chall_id);
        challOn = true;     
    }//GEN-LAST:event_jButtonChallengeMouseClicked

    private void jButtonCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCompleteActionPerformed
        //UPDATE USER WALLET
        UserData.updateUserWallet(Chall_coins,"plus");

        //UPDATE COINS DISPLAY
        UserData = new DataUser(this.user);
        this.wallet = UserData.getUserWallet();
        jLabelMoney.setText(String.valueOf(this.wallet));

        //UPDATE USER EXP IN DATABASE
        UserData.updateUserExp(Chall_exp);
        
        //UPDATE EXP DISPLAY
        UserData = new DataUser(this.user);
        this.exp = UserData.getUserExp();
        jLabelExp.setText(String.valueOf(this.exp));
        //CHECK IF THE MUSHIE HAS GROWN
        setImgs(false,false,true);
        
        //INSERT COMPLETED CHALL INTO BACKPACK_CHALL
        ChallData.insertChallBackpack(this.Chall_id);
         
        //CALLS CATRANK TO UPDATE THE USER QUEST RANK
        FillRank(true,Quest);
        
        //SETS THE CHALL BALLON NOT VISIBLE UNTIL IT CLICKS IN NEW CHALL
        WizardBallonChall.setVisible(false);
        WizardSpeachChall.setVisible(false);
        jButtonComplete.setVisible(false);
        challOn = false;
    }//GEN-LAST:event_jButtonCompleteActionPerformed

    private void jNewAdventureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNewAdventureButtonActionPerformed
        AdventureSelection new_adventure=new AdventureSelection();
        new_adventure.passData(this.user,this.Class,"UserHome");
        new_adventure.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jNewAdventureButtonActionPerformed

    private void JUserButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JUserButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_JUserButtonMouseClicked

    private void JWizHouseButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JWizHouseButtonMouseClicked
        GameplayWizardHomeForm WizHomeTravel = new GameplayWizardHomeForm();
        WizHomeTravel.passData(this.user);
        WizHomeTravel.setVisible(true);
        this.dispose();  
    }//GEN-LAST:event_JWizHouseButtonMouseClicked

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
            java.util.logging.Logger.getLogger(GameplayUserHomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameplayUserHomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameplayUserHomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameplayUserHomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameplayUserHomeForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel CloseButton;
    private javax.swing.JButton JUserButton;
    private javax.swing.JButton JWizHouseButton;
    private javax.swing.JLabel MinimizeButton;
    private javax.swing.JPanel UserInfoPanel;
    private javax.swing.JLabel WizardBallonChall;
    private javax.swing.JLabel WizardBallonWelcome;
    private javax.swing.JLabel WizardSpeachChall;
    private javax.swing.JLabel WizardSpeachWelcome;
    public javax.swing.JButton jButtonChallenge;
    private javax.swing.JButton jButtonComplete;
    private javax.swing.JLabel jCatImg;
    private javax.swing.JButton jCatalfButton;
    private javax.swing.JPanel jCatalfRankDisplay;
    private javax.swing.JLabel jLabelAcademic;
    private javax.swing.JLabel jLabelCoin;
    private javax.swing.JLabel jLabelExp;
    private javax.swing.JLabel jLabelFitness;
    private javax.swing.JLabel jLabelMind;
    private javax.swing.JLabel jLabelMoney;
    private javax.swing.JLabel jLabelMush;
    private javax.swing.JButton jNewAdventureButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelMinCloseAll;
    private javax.swing.JTable jRankAcademic;
    private javax.swing.JTable jRankFitness;
    private javax.swing.JLabel jRankImg;
    private javax.swing.JTable jRankMind;
    private javax.swing.JLabel jUserImg;
    private javax.swing.JLabel jWizRideImg;
    private javax.swing.JLabel jWizardImg;
    // End of variables declaration//GEN-END:variables
}
