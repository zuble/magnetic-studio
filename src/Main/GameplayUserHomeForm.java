package Main;

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
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Zublé & Henrique
 */
public class GameplayUserHomeForm extends javax.swing.JFrame {

    public static String Location;
    private String user , Quest , Class , Chall_text ;
    private int Chall_id , Chall_coins , Chall_wisdom;
    private int wallet , wisdom;
    private int buttonCat1 = 0 , buttonUser = 0 , buttonBackpack = 0;
    private final String[] quests = {"academic" , "fitness" , "mind"};
    private boolean challOn , allChallDone;
    boolean[] ItemsBackpack = {false,false,false,false} ;
    DBDataChall ChallData;
    DBDataUser UserData;
    DBDataItem ItemData;
    DBCommunication DBaux;
    Clip clipaux;

    public GameplayUserHomeForm() {
        initComponents();
        setIcon();
        setInitialVisuals();

        //INITIALIZE DATAUSER
        UserData= new DBDataUser();
        UserData.setDataUser();

        //INITIALIZE DATAITEM
        ItemData = new DBDataItem();

        //INITIALIZE DATACHALL
        ChallData = new DBDataChall();

        setUser();

        //SET USER INVENTORY ITEMS
        setInventoryItems("passdata", Location);
        setInventoryChalls();

        //SET USER BACKGROUND AND WISDOM MUSHIES IMAGES
        setImgs("background");
        setImgs("mushie");

        //FILLS THE RANK TABLE FOR THE FRIST TIME
        fillRank(false,quests[0]);
        fillRank(false,quests[1]);
        fillRank(false,quests[2]);

        setWizWelcome(GameplayUserHomeForm.Location);
    }

    /**
     * sets the location to the class that call GameplayUserHome
     * @param location
     */
    public static void setLocation(String location){
        Location = location;
    }

    /**
     * set app icon
     */
    private void setIcon(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/wizard.png")));
    }

    /**
     * set user information panel and class img
     */
    private void setUser(){
        //GETS ALL USER DATA NEEDED
        this.user = UserData.getUsername();
        this.Class = UserData.getUserClass();
        this.Quest = UserData.getUserQuest();
        this.wallet = UserData.getUserWallet();
        this.wisdom = UserData.getUserWisdom();

        userButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/"+this.Class+"2.png")));

        //SETS USER INFO PANEL ( COINS + WISDOM )
        coinLabel.setText(String.valueOf(this.wallet));
        wisdomLabel.setText(String.valueOf(this.wisdom));
        userLabel.setText(this.user+" the "+this.Class);
    }

    /**
     * sets the inital visibility for labels/buttons and images
     */
    private void setInitialVisuals(){
        //SET ALL BUTTONS INVISIBLE UNTIL WIZARD FINHISH WELCOME SPEACH
        catalfButton.setVisible(false);
        catalfRankDisplay.setVisible(false);
        rankImg.setVisible(false);
        wizHouseButton.setVisible(false);

        userButton.setVisible(false);
        userLabel.setVisible(false);
        userInfoPanel.setVisible(false);

        newAdventureButton.setVisible(false);
        completeButton.setVisible(false);
        challengeButton.setVisible(false);
        wizBallonChall.setVisible(false);
        wizSpeachChall.setVisible(false);

        userInventoryPanel.setVisible(false);
    }

    /**
     * sets the background img acording to the special item2 state
     * sets the mushie icon according to the wisdom points
     * @param IMG
     */
    private void setImgs( String IMG ){
        if( "background".equals(IMG) ){
            //SET FINN BACKGROUND AND ACCORDING FONT IF ITEM2 IS ACTIVED
            if("ON".equals(ItemData.getItemState(2)) /*&& ( "WizHome".equals(GameplayUserHomeForm.Location) || "UserHome".equals(GameplayUserHomeForm.Location) )*/ ) {
                background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Saana.gif")));

                catalfButton.setFont(new java.awt.Font("Futura Md BT", 1, 15));
                userLabel.setFont(new java.awt.Font("Futura Md BT", 1, 15));
                wizHouseButton.setFont(new java.awt.Font("Futura Md BT", 1, 15));
                newAdventureButton.setFont(new java.awt.Font("Futura Md BT", 1, 15));
                completeButton.setFont(new java.awt.Font("Futura Md BT", 1, 15));
                challengeButton.setFont(new java.awt.Font("Futura Md BT", 1, 15));
                wisdomLabel.setFont(new java.awt.Font("Futura Md BT", 1, 15));
                coinLabel.setFont(new java.awt.Font("Futura Md BT", 1, 15));
            }
            //SET THE USER QUEST BACKGROUND AND FONT
            else {
                background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/"+this.Quest+"Center.png")));

                catalfButton.setFont(new java.awt.Font("Book Antiqua", 1, 14));
                userLabel.setFont(new java.awt.Font("Book Antiqua", 1, 14));
                wizHouseButton.setFont(new java.awt.Font("Book Antiqua", 1, 14));
                newAdventureButton.setFont(new java.awt.Font("Book Antiqua", 1, 14));
                completeButton.setFont(new java.awt.Font("Book Antiqua", 1, 14));
                challengeButton.setFont(new java.awt.Font("Book Antiqua", 1, 14));
                wisdomLabel.setFont(new java.awt.Font("Book Antiqua", 1, 18));
                coinLabel.setFont(new java.awt.Font("Book Antiqua", 1, 18));
            }
        }
        //SET MUSHIE SIZE
        if( "mushie".equals(IMG) ){

            this.wisdom = UserData.getUserWisdom();
            if(this.wisdom <= 40){mushieImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mushies_stage1.png"))); }
            if((this.wisdom > 40) && (this.wisdom <= 80)){mushieImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mushies_stage2.png"))); }
            if(this.wisdom > 80){mushieImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mushies_stage3.png"))); }
        }
    }

    /**
     * sets the inventory items tooltip and visibility according to what the user has bought
     * sets the background of the speacial item 2 off everytime it coems from login or a newadventure
     * @param functionLoc
     * @param classLoc
     */
    private void setInventoryItems(String functionLoc , String classLoc){

        if( "newAdventure".equals(classLoc) || "login".equals(classLoc) ){
            if( ItemData.isItemInBackpack(2) ) ItemData.setItemState(2, "OFF");
            if( ItemData.isItemInBackpack(3) ) ItemData.setItemState(3, "OFF");
            if( ItemData.isItemInBackpack(4) ) ItemData.setItemState(4, "OFF");
        }
      
        //SETS NAME IN TOOLTIP FOR EACH ITEM
        if( "passdata".equals(functionLoc) ){
            ItemData.getDataItem(1);
            Item1Inventory.setToolTipText(ItemData.getItemName());
            Item1Inventory.setVisible(false);
            ItemData.getDataItem(2);
            Item2Inventory.setToolTipText(ItemData.getItemName());
            Item2Inventory.setVisible(false);
            ItemData.getDataItem(3);
            Item3Inventory.setToolTipText(ItemData.getItemName());
            Item3Inventory.setVisible(false);
            ItemData.getDataItem(4);
            Item4Inventory.setToolTipText(ItemData.getItemName());
            Item4Inventory.setVisible(false);
        }

        if( "backpack".equals(functionLoc) ){
            //POPULATES ITEMSBACKPACK
            ItemsBackpack = ItemData.areItemsInBackpack(ItemsBackpack);

            //SET USER ITEMS VISIBLE
            if(ItemsBackpack[0]){Item1Inventory.setVisible(true);}
            else Item1Inventory.setVisible(false);
            if(ItemsBackpack[1]){Item2Inventory.setVisible(true);}
            else Item2Inventory.setVisible(false);
            if(ItemsBackpack[2]){Item3Inventory.setVisible(true);}
            else Item3Inventory.setVisible(false);
            if(ItemsBackpack[3]){Item4Inventory.setVisible(true);}
            else Item4Inventory.setVisible(false);
        }
    }

    /**
     * sets the inventory challs that the user has done
     */
    private void setInventoryChalls(){
        //SETS WHICH CHALLS WERE DONE BY THE USER
        chall10.setVisible(ChallData.isChallDone(1));
        chall1.setVisible(ChallData.isChallDone(1));
        chall2.setVisible(ChallData.isChallDone(2));
        chall3.setVisible(ChallData.isChallDone(3));
        chall4.setVisible(ChallData.isChallDone(4));
        chall5.setVisible(ChallData.isChallDone(5));
        chall6.setVisible(ChallData.isChallDone(6));
        chall7.setVisible(ChallData.isChallDone(7));
        chall8.setVisible(ChallData.isChallDone(8));
        chall9.setVisible(ChallData.isChallDone(9));
    }

    /**
     * sets the welcome speach of the wizard according to the location that the users comes from and if all challs are done
     * @param Location
     */
    private void setWizWelcome(String Location){
        //TIMER FOR THE WELCOME BALOON/SPEACH
        allChallDone = ChallData.allChallDone();
        System.out.println("allChallDone= "+allChallDone);
        System.out.println("location.setWizWelcome= "+Location);

        if( allChallDone && ( "login".equals(Location) || "newAdventure".equals(Location) ) ){
            wizSpeachWelcome.setText("<html> You got everything from here already. Maybe time for a new adventure?<html>");
            System.out.println("1");
            wizardLabelsTimer(5000,"allchallsdone");
        }
        if( allChallDone && "WizHome".equals(Location)){
            wizBallonWelcome.setVisible(false);
            wizSpeachWelcome.setVisible(false);
            System.out.println("2");
            wizardLabelsTimer(1,"allchallsdone");
        }
        if( !allChallDone ){
            //SET WIZARD WELCOME MESSAGE
            if("login".equals(Location)){
                if (quests[0].equals(Quest)){ wizSpeachWelcome.setText("<html> Welcome back to the College of Wizardry and Science! <html>");}
                if (quests[1].equals(Quest)){wizSpeachWelcome.setText("<html> Welcome back to the Fighters Guild!<html>");}
                if (quests[2].equals(Quest)){ wizSpeachWelcome.setText("<html> Welcome back to the Arcane Order of the Mind!<html>");}
            }
            if("register".equals(Location) || "newAdventure".equals(Location)){
                if (quests[0].equals(Quest)){ wizSpeachWelcome.setText("<html> Welcome to the College of Wizardry and Science! <html>");}
                if (quests[1].equals(Quest)){wizSpeachWelcome.setText("<html> Welcome to the Fighters Guild!<html>");}
                if (quests[2].equals(Quest)){ wizSpeachWelcome.setText("<html> Welcome to the Arcane Order of the Mind!<html>");}
            }
            if("WizHome".equals(Location)){
                wizSpeachWelcome.setText("<html> Ocapse is a good driver , isn't he ? :)<html>");
            }
            wizardLabelsTimer(3000,"passdata");
        }
    }

    /**
     * a timer for the wizard speach to be visible and for what will become visible after according to the location
     * @param Time
     * @param Location
     */
    private void wizardLabelsTimer(int Time, String Location){
        Timer timer = new Timer(Time, (ActionEvent e) -> {
            if( "passdata".equals(Location) ){
                wizBallonWelcome.setVisible(false);
                wizSpeachWelcome.setVisible(false);
                challengeButton.setVisible(true);
                newAdventureButton.setVisible(true);

                userLabel.setVisible(true);
                userInfoPanel.setVisible(true);
                userButton.setVisible(true);

                catalfButton.setVisible(true);
                wizHouseButton.setVisible(true);

            }
            if( "allchallsdone".equals(Location)){
                wizBallonWelcome.setVisible(false);
                wizSpeachWelcome.setVisible(false);
                wizImg.setVisible(true);
                challengeButton.setVisible(false);
                newAdventureButton.setVisible(true);
                wizHouseButton.setVisible(true);
                catalfButton.setVisible(true);
                userLabel.setVisible(true);
                userInfoPanel.setVisible(true);
                userButton.setVisible(true);
            }

        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * play or stop the clip
     * @param dowhat
     * @param clip
     */
    private void setSound(String dowhat , Clip clip){
        if ("play".equals(dowhat)) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        if ("stop".equals(dowhat)) {
            clip.stop();
        }
    }

    /**
     * updates the coins display in the user painel
     */
    private void updateCoinsDisplay(){
        //UserData.setDataUser();
        this.wallet = UserData.getUserWallet();
        coinLabel.setText(String.valueOf(this.wallet));
    }

    /**
     * updates the wisdom points in the user painel and checks if the mushie has grown
     */
    private void updateWisdomDisplay(){
        this.wisdom = UserData.getUserWisdom();
        wisdomLabel.setText(String.valueOf(this.wisdom));

        setImgs("mushie");
    }

    /**
     * sets or updates the 3 rank tables according to their wisdom points and Quest
     * @param update
     * @param Quest
     */
    @SuppressWarnings("null")
    public void fillRank( boolean update , String Quest ) {
        PreparedStatement st;
        ResultSet rs;
        DefaultTableModel tb1Model = null;
        String query = "SELECT * FROM `users` WHERE `Quest`= ? ORDER BY `wisdom` DESC";

        if(Quest.equals(quests[0])){ tb1Model = (DefaultTableModel)academicTableRank.getModel();}
        if(Quest.equals(quests[1])){ tb1Model = (DefaultTableModel)fitnessTableRank.getModel();}
        if(Quest.equals(quests[2])){ tb1Model = (DefaultTableModel)mindTableRank.getModel();}

        //UPDATES EVERY TABLE QUEST FRIST TIME
        if(!update){
            try {
                st = DBCommunication.plugConn.prepareStatement(query);
                st.setString(1,Quest);
                rs = st.executeQuery();
                while(rs.next()){
                    String data[] = { rs.getString("username"), rs.getString("wisdom") };
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
                st = DBCommunication.plugConn.prepareStatement(query);
                st.setString(1,Quest);
                rs = st.executeQuery();
                while(rs.next()){
                    String data[] = { rs.getString("username"), rs.getString("wisdom") };
                    tb1Model.addRow(data);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        wizImg = new javax.swing.JLabel();
        closeMinPanel = new javax.swing.JPanel();
        closeButton = new javax.swing.JLabel();
        minimizeButton = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        wizHouseButton = new javax.swing.JButton();
        userButton = new javax.swing.JButton();
        userLabel = new javax.swing.JLabel();
        userInfoPanel = new javax.swing.JPanel();
        mushieImg = new javax.swing.JLabel();
        wisdomLabel = new javax.swing.JLabel();
        coinImg = new javax.swing.JLabel();
        coinLabel = new javax.swing.JLabel();
        catalfButton = new javax.swing.JButton();
        catalfRankDisplay = new javax.swing.JPanel();
        wisdomRankLabel = new javax.swing.JLabel();
        academicLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        academicTableRank = new javax.swing.JTable();
        fitnessLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        fitnessTableRank = new javax.swing.JTable();
        mindLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mindTableRank = new javax.swing.JTable();
        usernameRankLabel = new javax.swing.JLabel();
        rankImg = new javax.swing.JLabel();
        newAdventureButton = new javax.swing.JButton();
        userInventoryPanel = new javax.swing.JPanel();
        Item1Inventory = new javax.swing.JLabel();
        Item2Inventory = new javax.swing.JLabel();
        Item3Inventory = new javax.swing.JLabel();
        Item4Inventory = new javax.swing.JLabel();
        BackpackButton = new javax.swing.JButton();
        chall10 = new javax.swing.JLabel();
        chall9 = new javax.swing.JLabel();
        chall8 = new javax.swing.JLabel();
        chall7 = new javax.swing.JLabel();
        chall6 = new javax.swing.JLabel();
        chall5 = new javax.swing.JLabel();
        chall4 = new javax.swing.JLabel();
        chall3 = new javax.swing.JLabel();
        chall2 = new javax.swing.JLabel();
        chall1 = new javax.swing.JLabel();
        completeButton = new javax.swing.JButton();
        challengeButton = new javax.swing.JButton();
        wizSpeachWelcome = new javax.swing.JLabel();
        wizBallonWelcome = new javax.swing.JLabel();
        wizSpeachChall = new javax.swing.JLabel();
        wizBallonChall = new javax.swing.JLabel();
        background = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(null);

        wizImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/wizard2.png"))); // NOI18N
        jPanel1.add(wizImg);
        wizImg.setBounds(20, 60, 120, 130);

        closeMinPanel.setBackground(new java.awt.Color(153, 102, 0));

        closeButton.setFont(new java.awt.Font("Book Antiqua", 1, 24)); // NOI18N
        closeButton.setForeground(new java.awt.Color(255, 255, 255));
        closeButton.setText("x");
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

        logoutButton.setBackground(new java.awt.Color(102, 51, 0));
        logoutButton.setFont(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        logoutButton.setForeground(new java.awt.Color(255, 255, 255));
        logoutButton.setText("Logout");
        logoutButton.setBorder(null);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutButton.setFocusPainted(false);
        logoutButton.setFocusTraversalPolicyProvider(true);
        logoutButton.setFocusable(false);
        logoutButton.setMaximumSize(new java.awt.Dimension(51, 28));
        logoutButton.setMinimumSize(new java.awt.Dimension(51, 28));
        logoutButton.setPreferredSize(new java.awt.Dimension(51, 28));
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout closeMinPanelLayout = new javax.swing.GroupLayout(closeMinPanel);
        closeMinPanel.setLayout(closeMinPanelLayout);
        closeMinPanelLayout.setHorizontalGroup(
            closeMinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(closeMinPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(minimizeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addComponent(logoutButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        closeMinPanelLayout.setVerticalGroup(
            closeMinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(closeMinPanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(closeMinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(minimizeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(closeMinPanel);
        closeMinPanel.setBounds(620, 0, 80, 50);

        wizHouseButton.setBackground(new java.awt.Color(0, 0, 0));
        wizHouseButton.setFont(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        wizHouseButton.setForeground(new java.awt.Color(255, 255, 255));
        wizHouseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/dragon.png"))); // NOI18N
        wizHouseButton.setText("<html>Ocapse the Wizard Ride");
        wizHouseButton.setToolTipText("");
        wizHouseButton.setBorder(new javax.swing.border.MatteBorder(null));
        wizHouseButton.setContentAreaFilled(false);
        wizHouseButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        wizHouseButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        wizHouseButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        wizHouseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wizHouseButtonMouseClicked(evt);
            }
        });
        jPanel1.add(wizHouseButton);
        wizHouseButton.setBounds(510, 310, 190, 160);

        userButton.setBorder(new javax.swing.border.MatteBorder(null));
        userButton.setContentAreaFilled(false);
        userButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        userButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userButtonMouseClicked(evt);
            }
        });
        jPanel1.add(userButton);
        userButton.setBounds(20, 290, 120, 130);

        userLabel.setFont(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        userLabel.setForeground(new java.awt.Color(255, 255, 255));
        userLabel.setBorder(new javax.swing.border.MatteBorder(null));
        userLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(userLabel);
        userLabel.setBounds(30, 420, 120, 30);

        userInfoPanel.setBackground(new java.awt.Color(255, 255, 204));
        userInfoPanel.setBorder(new javax.swing.border.MatteBorder(null));
        userInfoPanel.setOpaque(false);

        mushieImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mushies_stage1.png"))); // NOI18N

        wisdomLabel.setBackground(new java.awt.Color(255, 204, 51));
        wisdomLabel.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        wisdomLabel.setForeground(new java.awt.Color(255, 255, 255));
        wisdomLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        wisdomLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        coinImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/coin.png"))); // NOI18N

        coinLabel.setBackground(new java.awt.Color(255, 204, 51));
        coinLabel.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        coinLabel.setForeground(new java.awt.Color(255, 255, 255));
        coinLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        coinLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout userInfoPanelLayout = new javax.swing.GroupLayout(userInfoPanel);
        userInfoPanel.setLayout(userInfoPanelLayout);
        userInfoPanelLayout.setHorizontalGroup(
            userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userInfoPanelLayout.createSequentialGroup()
                .addComponent(coinLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(coinImg, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wisdomLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mushieImg, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
        );
        userInfoPanelLayout.setVerticalGroup(
            userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mushieImg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
            .addComponent(coinImg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(coinLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(wisdomLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(userInfoPanel);
        userInfoPanel.setBounds(10, 450, 140, 40);

        catalfButton.setBackground(new java.awt.Color(204, 102, 255));
        catalfButton.setFont(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        catalfButton.setForeground(new java.awt.Color(255, 255, 255));
        catalfButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Catalf.png"))); // NOI18N
        catalfButton.setText("Voiello the Rank Medium");
        catalfButton.setBorder(new javax.swing.border.MatteBorder(null));
        catalfButton.setContentAreaFilled(false);
        catalfButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        catalfButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        catalfButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        catalfButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                catalfButtonMouseClicked(evt);
            }
        });
        jPanel1.add(catalfButton);
        catalfButton.setBounds(490, 60, 210, 180);

        catalfRankDisplay.setOpaque(false);

        wisdomRankLabel.setFont(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        wisdomRankLabel.setForeground(new java.awt.Color(0, 0, 0));
        wisdomRankLabel.setText("Wisdom");

        academicLabel.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        academicLabel.setForeground(new java.awt.Color(0, 0, 0));
        academicLabel.setText("Academic");

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setFont(new java.awt.Font("Book Antiqua", 0, 10)); // NOI18N
        jScrollPane3.setOpaque(false);
        jScrollPane3.setViewportView(academicTableRank);

        academicTableRank.setFont(new java.awt.Font("Book Antiqua", 0, 10)); // NOI18N
        academicTableRank.setForeground(new java.awt.Color(0, 0, 0));
        academicTableRank.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", ""
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
        academicTableRank.setOpaque(false);
        academicTableRank.setSelectionForeground(new java.awt.Color(0, 0, 0));
        academicTableRank.setShowHorizontalLines(false);
        academicTableRank.setShowVerticalLines(false);
        academicTableRank.getTableHeader().setReorderingAllowed(false);
        academicTableRank.setTableHeader((null));
        jScrollPane3.setViewportView(academicTableRank);
        if (academicTableRank.getColumnModel().getColumnCount() > 0) {
            academicTableRank.getColumnModel().getColumn(0).setResizable(false);
            academicTableRank.getColumnModel().getColumn(1).setResizable(false);
        }

        fitnessLabel.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        fitnessLabel.setForeground(new java.awt.Color(0, 0, 0));
        fitnessLabel.setText("Fitness");

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setFont(new java.awt.Font("Book Antiqua", 0, 10)); // NOI18N
        jScrollPane2.setOpaque(false);
        jScrollPane2.setViewportView(null);
        jScrollPane2.setWheelScrollingEnabled(false);

        fitnessTableRank.setFont(new java.awt.Font("Book Antiqua", 0, 10)); // NOI18N
        fitnessTableRank.setForeground(new java.awt.Color(0, 0, 0));
        fitnessTableRank.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", ""
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
        fitnessTableRank.setOpaque(false);
        fitnessTableRank.setRowSelectionAllowed(false);
        fitnessTableRank.setShowHorizontalLines(false);
        fitnessTableRank.setShowVerticalLines(false);
        fitnessTableRank.getTableHeader().setReorderingAllowed(false);
        fitnessTableRank.setTableHeader((null));
        jScrollPane2.setViewportView(fitnessTableRank);
        if (fitnessTableRank.getColumnModel().getColumnCount() > 0) {
            fitnessTableRank.getColumnModel().getColumn(0).setResizable(false);
            fitnessTableRank.getColumnModel().getColumn(1).setResizable(false);
        }

        mindLabel.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        mindLabel.setForeground(new java.awt.Color(0, 0, 0));
        mindLabel.setText("Mind");

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setFont(new java.awt.Font("Book Antiqua", 0, 10)); // NOI18N
        jScrollPane1.setOpaque(false);
        jScrollPane1.setViewportView(null);
        jScrollPane1.getViewport().setOpaque(false);

        mindTableRank.setFont(new java.awt.Font("Book Antiqua", 0, 10)); // NOI18N
        mindTableRank.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", ""
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
        mindTableRank.setOpaque(false);
        mindTableRank.setRowSelectionAllowed(false);
        mindTableRank.setShowGrid(false);
        mindTableRank.getTableHeader().setResizingAllowed(false);
        mindTableRank.getTableHeader().setReorderingAllowed(false);
        mindTableRank.setTableHeader((null));
        jScrollPane1.setViewportView(mindTableRank);
        if (mindTableRank.getColumnModel().getColumnCount() > 0) {
            mindTableRank.getColumnModel().getColumn(0).setResizable(false);
            mindTableRank.getColumnModel().getColumn(1).setResizable(false);
        }

        usernameRankLabel.setFont(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        usernameRankLabel.setForeground(new java.awt.Color(0, 0, 0));
        usernameRankLabel.setText("Username");

        javax.swing.GroupLayout catalfRankDisplayLayout = new javax.swing.GroupLayout(catalfRankDisplay);
        catalfRankDisplay.setLayout(catalfRankDisplayLayout);
        catalfRankDisplayLayout.setHorizontalGroup(
            catalfRankDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(catalfRankDisplayLayout.createSequentialGroup()
                .addGroup(catalfRankDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(catalfRankDisplayLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(usernameRankLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(wisdomRankLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(catalfRankDisplayLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(catalfRankDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(catalfRankDisplayLayout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(fitnessLabel))
                    .addGroup(catalfRankDisplayLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(mindLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, catalfRankDisplayLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(academicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128))
        );
        catalfRankDisplayLayout.setVerticalGroup(
            catalfRankDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, catalfRankDisplayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(catalfRankDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(wisdomRankLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(usernameRankLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addComponent(academicLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitnessLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mindLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142))
        );

        jPanel1.add(catalfRankDisplay);
        catalfRankDisplay.setBounds(170, 50, 350, 360);

        rankImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/rank.png"))); // NOI18N
        jPanel1.add(rankImg);
        rankImg.setBounds(160, 10, 350, 480);

        newAdventureButton.setBackground(new java.awt.Color(255, 123, 17));
        newAdventureButton.setFont(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        newAdventureButton.setForeground(new java.awt.Color(255, 255, 255));
        newAdventureButton.setText("New Adventure");
        newAdventureButton.setBorder(new javax.swing.border.MatteBorder(null));
        newAdventureButton.setBorderPainted(false);
        newAdventureButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newAdventureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAdventureButtonActionPerformed(evt);
            }
        });
        jPanel1.add(newAdventureButton);
        newAdventureButton.setBounds(10, 200, 140, 30);

        userInventoryPanel.setBorder(new javax.swing.border.MatteBorder(null));
        userInventoryPanel.setOpaque(false);

        Item1Inventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/xtal1icon.png"))); // NOI18N
        Item1Inventory.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Item1Inventory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Item1InventoryMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Item1InventoryMouseExited(evt);
            }
        });

        Item2Inventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/xtal2icon.png"))); // NOI18N
        Item2Inventory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Item2Inventory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Item2InventoryMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Item2InventoryMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Item2InventoryMouseExited(evt);
            }
        });

        Item3Inventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/xtal3icon.png"))); // NOI18N
        Item3Inventory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Item3Inventory.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Item3Inventory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Item3InventoryMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Item3InventoryMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Item3InventoryMouseExited(evt);
            }
        });

        Item4Inventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/xtal4icon.png"))); // NOI18N
        Item4Inventory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Item4Inventory.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Item4Inventory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Item4InventoryMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Item4InventoryMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Item4InventoryMouseExited(evt);
            }
        });

        BackpackButton.setFont(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        BackpackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/chest-close.png"))); // NOI18N
        BackpackButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        BackpackButton.setBorderPainted(false);
        BackpackButton.setContentAreaFilled(false);
        BackpackButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BackpackButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BackpackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BackpackButtonMouseClicked(evt);
            }
        });

        chall10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/9.png"))); // NOI18N
        chall10.setBorder(new javax.swing.border.MatteBorder(null));
        chall10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chall10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chall10MouseExited(evt);
            }
        });

        chall9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/8.png"))); // NOI18N
        chall9.setBorder(new javax.swing.border.MatteBorder(null));
        chall9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chall9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chall9MouseExited(evt);
            }
        });

        chall8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/7.png"))); // NOI18N
        chall8.setBorder(new javax.swing.border.MatteBorder(null));
        chall8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chall8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chall8MouseExited(evt);
            }
        });

        chall7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/6.png"))); // NOI18N
        chall7.setBorder(new javax.swing.border.MatteBorder(null));
        chall7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chall7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chall7MouseExited(evt);
            }
        });

        chall6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/5.png"))); // NOI18N
        chall6.setBorder(new javax.swing.border.MatteBorder(null));
        chall6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chall6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chall6MouseExited(evt);
            }
        });

        chall5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/4.png"))); // NOI18N
        chall5.setBorder(new javax.swing.border.MatteBorder(null));
        chall5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chall5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chall5MouseExited(evt);
            }
        });

        chall4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/3.png"))); // NOI18N
        chall4.setBorder(new javax.swing.border.MatteBorder(null));
        chall4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chall4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chall4MouseExited(evt);
            }
        });

        chall3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/2.png"))); // NOI18N
        chall3.setBorder(new javax.swing.border.MatteBorder(null));
        chall3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chall3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chall3MouseExited(evt);
            }
        });

        chall2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/1.png"))); // NOI18N
        chall2.setBorder(new javax.swing.border.MatteBorder(null));
        chall2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chall2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chall2MouseExited(evt);
            }
        });

        chall1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/0.png"))); // NOI18N
        chall1.setBorder(new javax.swing.border.MatteBorder(null));
        chall1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chall1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chall1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout userInventoryPanelLayout = new javax.swing.GroupLayout(userInventoryPanel);
        userInventoryPanel.setLayout(userInventoryPanelLayout);
        userInventoryPanelLayout.setHorizontalGroup(
            userInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userInventoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(userInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(userInventoryPanelLayout.createSequentialGroup()
                        .addComponent(chall1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chall2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chall3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chall4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(userInventoryPanelLayout.createSequentialGroup()
                        .addComponent(chall6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chall7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chall8, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chall9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(userInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chall5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chall10, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userInventoryPanelLayout.createSequentialGroup()
                .addGroup(userInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(userInventoryPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(BackpackButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(userInventoryPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(Item1Inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Item2Inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(Item3Inventory)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Item4Inventory)))
                .addGap(24, 24, 24))
        );
        userInventoryPanelLayout.setVerticalGroup(
            userInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userInventoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(userInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chall4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chall2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chall1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chall3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chall5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addGroup(userInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(userInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(chall7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chall6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chall8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chall9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(chall10, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(userInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Item4Inventory)
                    .addComponent(Item3Inventory)
                    .addComponent(Item2Inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Item1Inventory))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BackpackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(userInventoryPanel);
        userInventoryPanel.setBounds(190, 170, 290, 310);

        completeButton.setBackground(new java.awt.Color(103, 209, 81));
        completeButton.setFont(new java.awt.Font("Futura Md BT", 1, 14)); // NOI18N
        completeButton.setForeground(new java.awt.Color(255, 255, 255));
        completeButton.setText("Complete");
        completeButton.setBorder(new javax.swing.border.MatteBorder(null));
        completeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        completeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completeButtonActionPerformed(evt);
            }
        });
        jPanel1.add(completeButton);
        completeButton.setBounds(260, 440, 180, 30);

        challengeButton.setBackground(new java.awt.Color(255, 66, 47));
        challengeButton.setFont(new java.awt.Font("Book Antiqua", 1, 16)); // NOI18N
        challengeButton.setForeground(new java.awt.Color(255, 255, 255));
        challengeButton.setText("New challenge");
        challengeButton.setBorder(new javax.swing.border.MatteBorder(null));
        challengeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        challengeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                challengeButtonMouseClicked(evt);
            }
        });
        jPanel1.add(challengeButton);
        challengeButton.setBounds(210, 410, 280, 30);

        wizSpeachWelcome.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        jPanel1.add(wizSpeachWelcome);
        wizSpeachWelcome.setBounds(200, 20, 280, 90);

        wizBallonWelcome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/balloon3.png"))); // NOI18N
        jPanel1.add(wizBallonWelcome);
        wizBallonWelcome.setBounds(150, 0, 360, 140);

        wizSpeachChall.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        jPanel1.add(wizSpeachChall);
        wizSpeachChall.setBounds(190, 140, 280, 170);

        wizBallonChall.setFont(wizSpeachWelcome.getFont());
        wizBallonChall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/art.png"))); // NOI18N
        jPanel1.add(wizBallonChall);
        wizBallonChall.setBounds(130, 110, 390, 240);

        background.setBackground(new java.awt.Color(255, 255, 51));
        background.setFont(new java.awt.Font("Book Antiqua", 1, 24)); // NOI18N
        jPanel1.add(background);
        background.setBounds(0, 0, 700, 500);

        usernameLabel.setText("Username");
        jPanel1.add(usernameLabel);
        usernameLabel.setBounds(230, 60, 49, 15);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 500));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void catalfButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_catalfButtonMouseClicked
        //OPENS RANK
        if( buttonCat1 == 0 ){
            catalfRankDisplay.setVisible(true);
            rankImg.setVisible(true);
            wizBallonChall.setVisible(false);
            wizSpeachChall.setVisible(false);
            completeButton.setVisible(false);
            challengeButton.setVisible(false);
            userInventoryPanel.setVisible(false);
            buttonCat1 = 1;
        }
        //CLOSES RANK
        else if( buttonCat1 == 1 ){
            catalfRankDisplay.setVisible(false);
            rankImg.setVisible(false);

            if( challOn ){
                wizBallonChall.setVisible(true);
                wizSpeachChall.setVisible(true);
                challengeButton.setVisible(true);
                completeButton.setVisible(true);
            }
            else if( buttonUser == 1){
                userInventoryPanel.setVisible(true);
            }
            else if ( !ChallData.allChallDone() ){
                challengeButton.setVisible(true);
            }


            buttonCat1 = 0;
        }
    }//GEN-LAST:event_catalfButtonMouseClicked

    private void challengeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_challengeButtonMouseClicked

        ChallData.setNewChall();

        wizBallonChall.setVisible(true);
        wizSpeachChall.setVisible(true);

        this.Chall_id = ChallData.getChallId();
        this.Chall_text = ChallData.getChallText();
        this.Chall_coins = ChallData.getChallCoins();
        this.Chall_wisdom = ChallData.getChallWisdom();

        wizSpeachChall.setText("<html>"+Chall_text+"</html>");
        completeButton.setVisible(true);
        System.out.println("CHALL TEXT : "+this.Chall_text+"CHALL ID : "+this.Chall_id);
        challOn = true;
    }//GEN-LAST:event_challengeButtonMouseClicked

    private void completeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completeButtonActionPerformed
        //COINS UPDATE
        UserData.updateUserWallet(this.Chall_coins,"plus");
        updateCoinsDisplay();

        //WISDOM UPDATE
        //IF USER HAS OCAPSE RUBI GETS +5 WISDOM
        int auxWisdom = Chall_wisdom;
        if ( ItemData.isItemInBackpack(1) ){
            auxWisdom = auxWisdom + 5;
        }
        UserData.updateUserWisdom(auxWisdom);
        updateWisdomDisplay();

        //INSERT COMPLETED CHALL INTO BACKPACK_CHALL
        ChallData.insertChallBackpack();

        //UPDATES THE USER INVENTORY
        setInventoryChalls();

        //CALLS CATRANK TO UPDATE THE USER QUEST RANK
        fillRank(true,Quest);

        //ALL CHALL COMPLETED = FINALE MESSAGE + BONUS(COINS , WISDOM POINTS)
        if( ChallData.allChallDone() ){
            //BONUS COINS
            UserData.updateUserWallet(10,"plus");
            updateCoinsDisplay();
            //BONUS WISDOM
            UserData.updateUserWisdom(20);
            updateWisdomDisplay();

            completeButton.setVisible(false);
            challengeButton.setVisible(false);

            wizSpeachChall.setText("<html>Your train is completed. You done superb. Altough this isn`t the end, life is a continuos flow in space. Hope you find peace and happiness within yourself. I leave my loved dragon Ocapse for you to come to my house whenever. Hope to see you in another adventure.</html>");
            wizBallonChall.setVisible(true);
            wizSpeachChall.setVisible(true);

            wizardLabelsTimer(7000,"allchalldone");
        }
        //SETS THE CHALL BALLON AND COMPLETE BUTTON OFF UNTIL IT CLICKS IN NEW CHALL
        else{
            wizBallonChall.setVisible(false);
            wizSpeachChall.setVisible(false);
            completeButton.setVisible(false);
        }

        //SETS CHALL OFF
        challOn = false;
    }//GEN-LAST:event_completeButtonActionPerformed

    private void newAdventureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAdventureButtonActionPerformed
        AdventureSelection.setLocation("newAdventure");
        AdventureSelection new_adventure=new AdventureSelection();
        new_adventure.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_newAdventureButtonActionPerformed

    private void wizHouseButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wizHouseButtonMouseClicked
        GameplayWizardHomeForm WizHomeTravel = new GameplayWizardHomeForm();
        WizHomeTravel.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_wizHouseButtonMouseClicked

    private void userButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userButtonMouseClicked
        if( buttonUser == 0 ){
            //CLOSES BAU
            BackpackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/chest-close.png")));
            //SET STUFF VISIBLE AND WHAT NOT
            catalfRankDisplay.setVisible(false);
            rankImg.setVisible(false);
            wizBallonChall.setVisible(false);
            wizSpeachChall.setVisible(false);
            completeButton.setVisible(false);
            challengeButton.setVisible(false);
            Item1Inventory.setVisible(false);
            Item2Inventory.setVisible(false);
            Item3Inventory.setVisible(false);
            Item4Inventory.setVisible(false);
            userInventoryPanel.setVisible(true);
            wizSpeachWelcome.setFont(new java.awt.Font("Book Antiqua", 1, 10));

            buttonUser = 1;
        }
        else if( buttonUser == 1 ){
            //CLOSES BAU
            BackpackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/chest-close.png")));
            userInventoryPanel.setVisible(false);

            wizSpeachWelcome.setFont(new java.awt.Font("Book Antiqua", 1, 12));

            if( ChallData.allChallDone() ){
                challengeButton.setVisible(false);
            }
            else challengeButton.setVisible(true);

            if( challOn ){
                wizBallonChall.setVisible(true);
                wizSpeachChall.setVisible(true);
                completeButton.setVisible(true);
            }

            if( buttonCat1 == 1 ){
               catalfRankDisplay.setVisible(true);
               rankImg.setVisible(true);
            }

            buttonUser = 0;
        }
    }//GEN-LAST:event_userButtonMouseClicked

    //LITLE INFORMATION APPEARS WHEN USER ITEMS ARE MOUSE ENTERED
    private void Item1InventoryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item1InventoryMouseEntered
        wizSpeachWelcome.setText("<html>Pure Ocapse's wound blood. It's giving you +5% of wisdom points per challenge! <html>");
        wizBallonWelcome.setVisible(true);
        wizSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_Item1InventoryMouseEntered

    private void Item1InventoryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item1InventoryMouseExited
        wizBallonWelcome.setVisible(false);
        wizSpeachWelcome.setVisible(false);
    }//GEN-LAST:event_Item1InventoryMouseExited

    private void Item2InventoryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item2InventoryMouseEntered
        if("OFF".equals(ItemData.getItemState(2))){
            wizSpeachWelcome.setText("<html>Lets travel all the away to Finnland ?<html>");
        }
        if("ON".equals(ItemData.getItemState(2))){
            wizSpeachWelcome.setText("<html> White coldness full of beauty :) <html>");
        }
        wizBallonWelcome.setVisible(true);
        wizSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_Item2InventoryMouseEntered

    private void Item2InventoryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item2InventoryMouseExited
        wizBallonWelcome.setVisible(false);
        wizSpeachWelcome.setVisible(false);
    }//GEN-LAST:event_Item2InventoryMouseExited

    private void Item3InventoryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item3InventoryMouseEntered
        if( "OFF".equals(ItemData.getItemState(3)) ){
            wizSpeachWelcome.setText("<html>Love is a force. Love is inexplicable. Love makes the present a infinite moment. Love is acceptance for what you and the others are. And when it's shared and lived with another being, it creates a monumental bubble of peace and pleasure. May this piece of sound waves let you feel all the love i have for you. <html>");
        }
        if( "ON".equals(ItemData.getItemState(3)) ){
            wizSpeachWelcome.setText("<html>Let your Love be seen, never cease it, never stop to believe in it. It will give the most divine moments on earth!<html>");
        }
        wizBallonWelcome.setVisible(true);
        wizSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_Item3InventoryMouseEntered

    private void Item3InventoryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item3InventoryMouseExited
        wizBallonWelcome.setVisible(false);
        wizSpeachWelcome.setVisible(false);
    }//GEN-LAST:event_Item3InventoryMouseExited

    private void Item4InventoryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item4InventoryMouseEntered
        if( "OFF".equals(ItemData.getItemState(4)) ){
            wizSpeachWelcome.setText("<html>Get a bit of this cookie and bring the ocean a litle closer!<html>");
        }
        if( "ON".equals(ItemData.getItemState(4)) ){
            wizSpeachWelcome.setText("<html>So peaceful, I'm completed immersed. Hope you are to.<html>");
        }
        wizBallonWelcome.setVisible(true);
        wizSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_Item4InventoryMouseEntered

    private void Item4InventoryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item4InventoryMouseExited
        wizBallonWelcome.setVisible(false);
        wizSpeachWelcome.setVisible(false);
    }//GEN-LAST:event_Item4InventoryMouseExited

    private void BackpackButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackpackButtonMouseClicked
        if( buttonBackpack == 0 ){
            //SET USER ITEMS VISIBLE
            setInventoryItems("backpack","UserHome");
            //SETS BAU OPEN
            BackpackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/chest-open.png")));

            buttonBackpack = 1;
        }
        else if( buttonUser == 1 ){
            Item1Inventory.setVisible(false);
            Item2Inventory.setVisible(false);
            Item3Inventory.setVisible(false);
            Item4Inventory.setVisible(false);
            //SETS BAU CLOSE
                BackpackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/chest-close.png")));
            buttonBackpack = 0;
        }
    }//GEN-LAST:event_BackpackButtonMouseClicked

    //SPECIAL USER INVENTORY ACTIONS
    private void Item2InventoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item2InventoryMouseClicked
        //MOVES USER HOUSE TO FINN
        if( "OFF".equals(ItemData.getItemState(2))){
            ItemData.setItemState(2, "ON");
            setImgs("background");
        }
        //MOVES BACK TO THE QUEST HOUSE
        else if( "ON".equals(ItemData.getItemState(2)) ){
            ItemData.setItemState(2,"OFF");
            setImgs("background");
        }
    }//GEN-LAST:event_Item2InventoryMouseClicked
    
    private void Item3InventoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item3InventoryMouseClicked
        //if( "ON".equals(ItemData.getItemState(4)) ){ JOptionPane.showMessageDialog(null, "Yet i can´t do mixing, not good at it. You must pause the ocean frist :) "); }
        //GET LOVE CLOSER
        if ( "OFF".equals(ItemData.getItemState(3))){
            ItemData.setItemState(3, "ON");
            try {
                this.clipaux = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("C:\\infernoup\\LPRO\\VitaQuest\\src\\Sound\\Lichen.wav"));
                clipaux.open(inputStream);
                setSound("play",clipaux);
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
                Logger.getLogger(GameplayUserHomeForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //LET LOVE GO
        else{
            ItemData.setItemState(3,"OFF");
            setSound("stop",clipaux);
        }
    }//GEN-LAST:event_Item3InventoryMouseClicked

    private void Item4InventoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item4InventoryMouseClicked
        //if( "ON".equals(ItemData.getItemState(3)) ){ JOptionPane.showMessageDialog(null, "Yet i can´t do mixing, not good at it. You must pause the sound of love frist :) "); }
        //GET OCEAN CLOSER
        if( "OFF".equals(ItemData.getItemState(4))){
            ItemData.setItemState(4, "ON");
            try {
                this.clipaux = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("C:\\infernoup\\LPRO\\VitaQuest\\src\\Sound\\mar.wav"));
                clipaux.open(inputStream);
                setSound("play",clipaux);
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
                Logger.getLogger(GameplayUserHomeForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //LET OCEAN GO
        else{
            ItemData.setItemState(4,"OFF");
            setSound("stop",clipaux);
        }
    }//GEN-LAST:event_Item4InventoryMouseClicked

    //CLOSE MINIMIZE AND LOGOUT ACTIONS
    private void closeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseClicked
        try {
            DBCommunication.DBDisconnect();
        } catch (SQLException ex) {
            Logger.getLogger(GameplayUserHomeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(DBCommunication.plugConn);
        System.exit(0);
    }//GEN-LAST:event_closeButtonMouseClicked

    private void minimizeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeButtonMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_minimizeButtonMouseClicked

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        Login lgf = new Login();
        lgf.setVisible(true);
        lgf.pack();
        lgf.setLocationRelativeTo(null);
        lgf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_logoutButtonActionPerformed

    //CHALLENGE INFO APPEARS WHEN MOUSE GOES OVER IT IN USER INVENTORY
    private void chall1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chall1MouseEntered
        wizSpeachWelcome.setText(ChallData.getChallTextbyID(1));
        wizBallonWelcome.setVisible(true);
        wizSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_chall1MouseEntered

    private void chall1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chall1MouseExited
        wizSpeachWelcome.setVisible(false);
        wizBallonWelcome.setVisible(false);
    }//GEN-LAST:event_chall1MouseExited

    private void chall2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chall2MouseEntered
        wizSpeachWelcome.setText(ChallData.getChallTextbyID(2));
        wizBallonWelcome.setVisible(true);
        wizSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_chall2MouseEntered

    private void chall2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chall2MouseExited
        wizSpeachWelcome.setVisible(false);
        wizBallonWelcome.setVisible(false);
    }//GEN-LAST:event_chall2MouseExited

    private void chall3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chall3MouseEntered
        wizSpeachWelcome.setText(ChallData.getChallTextbyID(3));
        wizBallonWelcome.setVisible(true);
        wizSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_chall3MouseEntered

    private void chall3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chall3MouseExited
        wizSpeachWelcome.setVisible(false);
        wizBallonWelcome.setVisible(false);
    }//GEN-LAST:event_chall3MouseExited

    private void chall4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chall4MouseEntered
        wizSpeachWelcome.setText(ChallData.getChallTextbyID(4));
        wizBallonWelcome.setVisible(true);
        wizSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_chall4MouseEntered

    private void chall4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chall4MouseExited
        wizSpeachWelcome.setVisible(false);
        wizBallonWelcome.setVisible(false);
    }//GEN-LAST:event_chall4MouseExited

    private void chall5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chall5MouseEntered
        wizSpeachWelcome.setText(ChallData.getChallTextbyID(5));
        wizBallonWelcome.setVisible(true);
        wizSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_chall5MouseEntered

    private void chall5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chall5MouseExited
        wizSpeachWelcome.setVisible(false);
        wizBallonWelcome.setVisible(false);
    }//GEN-LAST:event_chall5MouseExited

    private void chall6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chall6MouseEntered
        wizSpeachWelcome.setText(ChallData.getChallTextbyID(6));
        wizBallonWelcome.setVisible(true);
        wizSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_chall6MouseEntered

    private void chall6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chall6MouseExited
        wizSpeachWelcome.setVisible(false);
        wizBallonWelcome.setVisible(false);
    }//GEN-LAST:event_chall6MouseExited

    private void chall7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chall7MouseEntered
        wizSpeachWelcome.setText(ChallData.getChallTextbyID(7));
        wizBallonWelcome.setVisible(true);
        wizSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_chall7MouseEntered

    private void chall7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chall7MouseExited
        wizSpeachWelcome.setVisible(false);
        wizBallonWelcome.setVisible(false);
    }//GEN-LAST:event_chall7MouseExited

    private void chall8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chall8MouseEntered
        wizSpeachWelcome.setText(ChallData.getChallTextbyID(8));
        wizBallonWelcome.setVisible(true);
        wizSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_chall8MouseEntered

    private void chall8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chall8MouseExited
        wizSpeachWelcome.setVisible(false);
        wizBallonWelcome.setVisible(false);
    }//GEN-LAST:event_chall8MouseExited

    private void chall9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chall9MouseEntered
        wizSpeachWelcome.setText(ChallData.getChallTextbyID(9));
        wizBallonWelcome.setVisible(true);
        wizSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_chall9MouseEntered

    private void chall9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chall9MouseExited
        wizSpeachWelcome.setVisible(false);
        wizBallonWelcome.setVisible(false);
    }//GEN-LAST:event_chall9MouseExited

    private void chall10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chall10MouseEntered
        wizSpeachWelcome.setText(ChallData.getChallTextbyID(10));
        wizBallonWelcome.setVisible(true);
        wizSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_chall10MouseEntered

    private void chall10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chall10MouseExited
        wizSpeachWelcome.setVisible(false);
        wizBallonWelcome.setVisible(false);
    }//GEN-LAST:event_chall10MouseExited



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
            java.util.logging.Logger.getLogger(GameplayUserHomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GameplayUserHomeForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackpackButton;
    private javax.swing.JLabel Item1Inventory;
    private javax.swing.JLabel Item2Inventory;
    private javax.swing.JLabel Item3Inventory;
    private javax.swing.JLabel Item4Inventory;
    private javax.swing.JLabel academicLabel;
    public javax.swing.JTable academicTableRank;
    private javax.swing.JLabel background;
    private javax.swing.JButton catalfButton;
    private javax.swing.JPanel catalfRankDisplay;
    private javax.swing.JLabel chall1;
    private javax.swing.JLabel chall10;
    private javax.swing.JLabel chall2;
    private javax.swing.JLabel chall3;
    private javax.swing.JLabel chall4;
    private javax.swing.JLabel chall5;
    private javax.swing.JLabel chall6;
    private javax.swing.JLabel chall7;
    private javax.swing.JLabel chall8;
    private javax.swing.JLabel chall9;
    public javax.swing.JButton challengeButton;
    private javax.swing.JLabel closeButton;
    private javax.swing.JPanel closeMinPanel;
    private javax.swing.JLabel coinImg;
    private javax.swing.JLabel coinLabel;
    private javax.swing.JButton completeButton;
    private javax.swing.JLabel fitnessLabel;
    public javax.swing.JTable fitnessTableRank;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton logoutButton;
    private javax.swing.JLabel mindLabel;
    public javax.swing.JTable mindTableRank;
    private javax.swing.JLabel minimizeButton;
    private javax.swing.JLabel mushieImg;
    private javax.swing.JButton newAdventureButton;
    private javax.swing.JLabel rankImg;
    private javax.swing.JButton userButton;
    private javax.swing.JPanel userInfoPanel;
    private javax.swing.JPanel userInventoryPanel;
    private javax.swing.JLabel userLabel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLabel usernameRankLabel;
    private javax.swing.JLabel wisdomLabel;
    private javax.swing.JLabel wisdomRankLabel;
    private javax.swing.JLabel wizBallonChall;
    private javax.swing.JLabel wizBallonWelcome;
    private javax.swing.JButton wizHouseButton;
    private javax.swing.JLabel wizImg;
    private javax.swing.JLabel wizSpeachChall;
    private javax.swing.JLabel wizSpeachWelcome;
    // End of variables declaration//GEN-END:variables
}
