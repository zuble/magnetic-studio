package javaapplication1;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author Legion
 */
public class GameplayWizardHomeForm extends javax.swing.JFrame {
   
    public String user;
    private String Class;
    private int wallet , exp;
    boolean[] ItemsToShow = {false,false,false,false} ;
    DataUser UserData;
    DataItem ItemData;
    
  
    /**
     * Creates new form gameplayForm
     */
    public GameplayWizardHomeForm() {
        initComponents();
        setIcon();
    }
    
    private void setIcon(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/wizard.png"))); 
    }
    
    public void wizardLabelsTimer(int time){
        Timer timer = new Timer(time, (ActionEvent e) -> {
            WizardBallonWelcome.setVisible(false);
            WizardSpeachWelcome.setVisible(false);
            JWizHouseButton.setVisible(true);
        });
        timer.setRepeats(false);
        timer.start();
    } 
    
    public void wizardItemsTimer(boolean[] ItemsToShow){
        Timer timer = new Timer(1000, (ActionEvent e) -> {
            if(ItemsToShow[0])Item1Panel.setVisible(true);
            if(ItemsToShow[1])Item2Panel.setVisible(true);
            if(ItemsToShow[2])Item3Panel.setVisible(true);
            if(ItemsToShow[3])Item4Panel.setVisible(true);
        });
        timer.setRepeats(false);
        timer.start();
    }   
    
    public void passData(String user) {
        //SET USER INFO
        this.user = user;
        setUser();
        
        //INITIALIZE DataItem WITH USERNAME  
        ItemData = new DataItem(this.user);
        //FINDS WICH ITEMS ARE STILL DISPONIBLE
        for(int i = 0 ; i < 4 ; i++){ItemsToShow[i] = ItemData.isItemDisp(i+1);}
        //SET ITEMS 
        setItems();

        //SET BACKGROUND/USER CLASS IMAGE
        setImgs();
        
        //SET WIZARD SPEACH + DRAGON BUTTON OFF
        WizardBallonWelcome.setVisible(true);
        WizardSpeachWelcome.setVisible(true);
        WizardSpeachWelcome.setText("<html> Here is my humble source of enlighment and knowledge, my house.<html>"
                +  "<html>You can get some special and potent crystals or some other threats :) <html>");
        JWizHouseButton.setVisible(false);
        
        //SETS THE DISPONIBLE WIZARD ITEMS AFTER TIME OVER
        wizardItemsTimer(ItemsToShow);
        wizardLabelsTimer(5000);
    }
    
    private void setUser(){
        //GETS ALL USER DATA NEEDED
        UserData = new DataUser(user);  
        this.Class = UserData.getUserClass();
        this.wallet = UserData.getUserWallet();
        this.exp = UserData.getUserExp();
       
        jLabelMoney.setText(String.valueOf(wallet));
        jLabelExp.setText(String.valueOf(exp));
        JUserButton.setText(user+" the "+Class);
    }
    private void setImgs(){
        if ("archer".equals(Class)){ jUserImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/archer2.png")));}
        if ("fighter".equals(Class)){ jUserImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fighter2.png")));}
        if ("healer".equals(Class)){ jUserImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/healer2.png")));}
        
        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/witchroom.png")));
        
        UserData = new DataUser(user);
        this.exp = UserData.getUserExp();
        if(exp <= 40){jLabelMush.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mushies_stage1.png"))); }
        if((exp > 40) && (exp <= 80)){jLabelMush.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mushies_stage2.png"))); }
        if(exp > 80){jLabelMush.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mushies_stage3.png"))); }
    }
    
    private void setItems(){
        //SETS TOOLTIP+PRICE FOR EACH ITEM
        ItemData.getDataItem(1);
        Item1Img.setToolTipText(ItemData.getItemName());
        Item1Button.setText(String.valueOf(ItemData.getItemPrice()));
        ItemData.getDataItem(2);
        Item2Img.setToolTipText(ItemData.getItemName());
        Item2Button.setText(String.valueOf(ItemData.getItemPrice()));
        ItemData.getDataItem(3);
        Item3Img.setToolTipText(ItemData.getItemName());
        Item3Button.setText(String.valueOf(ItemData.getItemPrice()));
        ItemData.getDataItem(4);
        Item4Img.setToolTipText(ItemData.getItemName());
        Item4Button.setText(String.valueOf(ItemData.getItemPrice()));
        
        //SETS ITEMS NOT VISIBLE 
        Item1Panel.setVisible(false);
        Item2Panel.setVisible(false);
        Item3Panel.setVisible(false);
        Item4Panel.setVisible(false);
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
        Item1Panel = new javax.swing.JPanel();
        Item1Img = new javax.swing.JLabel();
        Item1Button = new javax.swing.JButton();
        Item2Panel = new javax.swing.JPanel();
        Item2Img = new javax.swing.JLabel();
        Item2Button = new javax.swing.JButton();
        Item3Panel = new javax.swing.JPanel();
        Item3Img = new javax.swing.JLabel();
        Item3Button = new javax.swing.JButton();
        Item4Panel = new javax.swing.JPanel();
        Item4Img = new javax.swing.JLabel();
        Item4Button = new javax.swing.JButton();
        WizTreasurePanel = new javax.swing.JPanel();
        TreasureWizButton = new javax.swing.JButton();
        WizardSpeachWelcome = new javax.swing.JLabel();
        WizardBallonWelcome = new javax.swing.JLabel();
        jWizardImg = new javax.swing.JLabel();
        jWizRideImg = new javax.swing.JLabel();
        JWizHouseButton = new javax.swing.JButton();
        jUserImg = new javax.swing.JLabel();
        JUserButton = new javax.swing.JButton();
        UserInfoPanel = new javax.swing.JPanel();
        jLabelMush = new javax.swing.JLabel();
        jLabelExp = new javax.swing.JLabel();
        jLabelCoin = new javax.swing.JLabel();
        jLabelMoney = new javax.swing.JLabel();
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

        Item1Panel.setOpaque(false);

        Item1Img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/xtal1.png"))); // NOI18N
        Item1Img.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Item1ImgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Item1ImgMouseExited(evt);
            }
        });

        Item1Button.setBackground(new java.awt.Color(0, 0, 0));
        Item1Button.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        Item1Button.setForeground(new java.awt.Color(255, 255, 255));
        Item1Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/coin.png"))); // NOI18N
        Item1Button.setBorder(new javax.swing.border.MatteBorder(null));
        Item1Button.setFocusPainted( false );
        Item1Button.setBorderPainted(false);
        Item1Button.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        Item1Button.setOpaque(false);
        Item1Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Item1ButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Item1PanelLayout = new javax.swing.GroupLayout(Item1Panel);
        Item1Panel.setLayout(Item1PanelLayout);
        Item1PanelLayout.setHorizontalGroup(
            Item1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Item1PanelLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(Item1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Item1Img)
                    .addComponent(Item1Button, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
        Item1PanelLayout.setVerticalGroup(
            Item1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Item1PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Item1Img, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Item1Button, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel1.add(Item1Panel);
        Item1Panel.setBounds(210, 130, 140, 160);

        Item2Panel.setOpaque(false);

        Item2Img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/xtal2.png"))); // NOI18N
        Item2Img.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Item2ImgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Item2ImgMouseExited(evt);
            }
        });

        Item2Button.setBackground(new java.awt.Color(0, 0, 0));
        Item2Button.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        Item2Button.setForeground(new java.awt.Color(255, 255, 255));
        Item2Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/coin.png"))); // NOI18N
        Item2Button.setBorder(new javax.swing.border.MatteBorder(null));
        Item2Button.setFocusPainted( false );
        Item2Button.setBorderPainted(false);
        Item2Button.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        Item2Button.setOpaque(false);
        Item2Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Item2ButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Item2PanelLayout = new javax.swing.GroupLayout(Item2Panel);
        Item2Panel.setLayout(Item2PanelLayout);
        Item2PanelLayout.setHorizontalGroup(
            Item2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Item2PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Item2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Item2Img)
                    .addComponent(Item2Button, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 17, Short.MAX_VALUE))
        );
        Item2PanelLayout.setVerticalGroup(
            Item2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Item2PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Item2Img)
                .addGap(18, 18, 18)
                .addComponent(Item2Button, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1.add(Item2Panel);
        Item2Panel.setBounds(360, 130, 140, 160);

        Item3Panel.setOpaque(false);

        Item3Img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/xtal3.png"))); // NOI18N
        Item3Img.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Item3ImgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Item3ImgMouseExited(evt);
            }
        });

        Item3Button.setBackground(new java.awt.Color(0, 0, 0));
        Item3Button.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        Item3Button.setForeground(new java.awt.Color(255, 255, 255));
        Item3Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/coin.png"))); // NOI18N
        Item3Button.setBorder(new javax.swing.border.MatteBorder(null));
        Item3Button.setBorderPainted(false);
        Item3Button.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        Item3Button.setOpaque(false);
        Item3Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Item3ButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Item3PanelLayout = new javax.swing.GroupLayout(Item3Panel);
        Item3Panel.setLayout(Item3PanelLayout);
        Item3PanelLayout.setHorizontalGroup(
            Item3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Item3PanelLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(Item3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Item3Button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Item3Img, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(20, 20, 20))
        );
        Item3PanelLayout.setVerticalGroup(
            Item3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Item3PanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Item3Img, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Item3Button, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jPanel1.add(Item3Panel);
        Item3Panel.setBounds(210, 290, 140, 160);

        Item4Panel.setOpaque(false);

        Item4Img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/xtal4.png"))); // NOI18N
        Item4Img.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Item4ImgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Item4ImgMouseExited(evt);
            }
        });

        Item4Button.setBackground(new java.awt.Color(0, 0, 0));
        Item4Button.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        Item4Button.setForeground(new java.awt.Color(255, 255, 255));
        Item4Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/coin.png"))); // NOI18N
        Item4Button.setBorder(new javax.swing.border.MatteBorder(null));
        Item4Button.setBorderPainted(false);
        Item4Button.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        Item4Button.setOpaque(false);
        Item4Button.setFocusPainted( false );
        Item4Button.setBorderPainted(false);
        Item4Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Item4ButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Item4PanelLayout = new javax.swing.GroupLayout(Item4Panel);
        Item4Panel.setLayout(Item4PanelLayout);
        Item4PanelLayout.setHorizontalGroup(
            Item4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Item4PanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(Item4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Item4Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Item4Img, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        Item4PanelLayout.setVerticalGroup(
            Item4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Item4PanelLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(Item4Img)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Item4Button, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(Item4Panel);
        Item4Panel.setBounds(360, 290, 140, 160);

        WizTreasurePanel.setOpaque(false);

        TreasureWizButton.setFont(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        TreasureWizButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/chest-close.png"))); // NOI18N
        TreasureWizButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TreasureWizButton.setFocusPainted( false );
        TreasureWizButton.setContentAreaFilled(false);
        TreasureWizButton.setOpaque(false);

        javax.swing.GroupLayout WizTreasurePanelLayout = new javax.swing.GroupLayout(WizTreasurePanel);
        WizTreasurePanel.setLayout(WizTreasurePanelLayout);
        WizTreasurePanelLayout.setHorizontalGroup(
            WizTreasurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WizTreasurePanelLayout.createSequentialGroup()
                .addComponent(TreasureWizButton)
                .addGap(0, 5, Short.MAX_VALUE))
        );
        WizTreasurePanelLayout.setVerticalGroup(
            WizTreasurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WizTreasurePanelLayout.createSequentialGroup()
                .addComponent(TreasureWizButton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel1.add(WizTreasurePanel);
        WizTreasurePanel.setBounds(560, 80, 100, 80);

        WizardSpeachWelcome.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        jPanel1.add(WizardSpeachWelcome);
        WizardSpeachWelcome.setBounds(200, 20, 280, 90);

        WizardBallonWelcome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/balloon3.png"))); // NOI18N
        jPanel1.add(WizardBallonWelcome);
        WizardBallonWelcome.setBounds(150, 0, 360, 140);

        jWizardImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/wizard2.png"))); // NOI18N
        jPanel1.add(jWizardImg);
        jWizardImg.setBounds(30, 50, 120, 130);

        jWizRideImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/dragon.png"))); // NOI18N
        jPanel1.add(jWizRideImg);
        jWizRideImg.setBounds(550, 260, 130, 160);

        JWizHouseButton.setBackground(new java.awt.Color(170, 63, 7));
        JWizHouseButton.setFont(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        JWizHouseButton.setForeground(new java.awt.Color(255, 255, 255));
        JWizHouseButton.setText("Travel Back");
        JWizHouseButton.setBorder(new javax.swing.border.MatteBorder(null));
        JWizHouseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JWizHouseButtonMouseClicked(evt);
            }
        });
        jPanel1.add(JWizHouseButton);
        JWizHouseButton.setBounds(530, 410, 160, 30);
        jPanel1.add(jUserImg);
        jUserImg.setBounds(20, 250, 120, 130);

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
        JUserButton.setBounds(10, 380, 140, 30);

        UserInfoPanel.setBackground(new java.awt.Color(255, 255, 204));
        UserInfoPanel.setBorder(new javax.swing.border.MatteBorder(null));
        UserInfoPanel.setOpaque(false);

        jLabelMush.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mushies_stage1.png"))); // NOI18N

        jLabelExp.setBackground(new java.awt.Color(255, 204, 51));
        jLabelExp.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabelExp.setForeground(new java.awt.Color(255, 255, 255));
        jLabelExp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabelCoin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/coin.png"))); // NOI18N

        jLabelMoney.setBackground(new java.awt.Color(255, 204, 51));
        jLabelMoney.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabelMoney.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMoney.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout UserInfoPanelLayout = new javax.swing.GroupLayout(UserInfoPanel);
        UserInfoPanel.setLayout(UserInfoPanelLayout);
        UserInfoPanelLayout.setHorizontalGroup(
            UserInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserInfoPanelLayout.createSequentialGroup()
                .addComponent(jLabelMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCoin, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelExp, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelMush, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        UserInfoPanelLayout.setVerticalGroup(
            UserInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelMoney, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelExp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelCoin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelMush, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        jPanel1.add(UserInfoPanel);
        UserInfoPanel.setBounds(10, 410, 140, 40);

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

    private void JUserButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JUserButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_JUserButtonMouseClicked

    private void JWizHouseButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JWizHouseButtonMouseClicked
        GameplayUserHomeForm UserHomeTravel = new GameplayUserHomeForm();
        UserHomeTravel.passData(this.user,"WizHome");
        UserHomeTravel.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_JWizHouseButtonMouseClicked

    private void Item1ImgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item1ImgMouseEntered
        ItemData.getDataItem(1);
        WizardSpeachWelcome.setText("<html>"+ItemData.getItemInfo()+"<html>");
        WizardBallonWelcome.setVisible(true);
        WizardSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_Item1ImgMouseEntered

    private void Item1ImgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item1ImgMouseExited
        WizardBallonWelcome.setVisible(false);
        WizardSpeachWelcome.setVisible(false);
    }//GEN-LAST:event_Item1ImgMouseExited

    private void Item2ImgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item2ImgMouseEntered
        ItemData.getDataItem(2);
        WizardSpeachWelcome.setText("<html>"+ItemData.getItemInfo()+"<html>");
        WizardBallonWelcome.setVisible(true);
        WizardSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_Item2ImgMouseEntered

    private void Item2ImgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item2ImgMouseExited
        WizardBallonWelcome.setVisible(false);
        WizardSpeachWelcome.setVisible(false);
    }//GEN-LAST:event_Item2ImgMouseExited

    private void Item3ImgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item3ImgMouseEntered
        ItemData.getDataItem(3);
        WizardSpeachWelcome.setText("<html>"+ItemData.getItemInfo()+"<html>");
        WizardBallonWelcome.setVisible(true);
        WizardSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_Item3ImgMouseEntered

    private void Item3ImgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item3ImgMouseExited
        WizardBallonWelcome.setVisible(false);
        WizardSpeachWelcome.setVisible(false);
    }//GEN-LAST:event_Item3ImgMouseExited

    private void Item4ImgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item4ImgMouseEntered
        ItemData.getDataItem(4);
        WizardSpeachWelcome.setText("<html>"+ItemData.getItemInfo()+"<html>");
        WizardBallonWelcome.setVisible(true);
        WizardSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_Item4ImgMouseEntered

    private void Item4ImgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item4ImgMouseExited
        WizardBallonWelcome.setVisible(false);
        WizardSpeachWelcome.setVisible(false);
    }//GEN-LAST:event_Item4ImgMouseExited

    private void Item1ButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item1ButtonMouseClicked
        //TRUE = ENOUGH COINS 4 ITEM
        ItemData.getDataItem(1);
        if( ItemData.isCoinsEnough(ItemData.getItemPrice(), wallet) ){
            UserData.updateUserWallet(ItemData.getItemPrice(),"minus");
            ItemData.insertItemBackpack(1);
            setUser();
            
            Item1Panel.setVisible(false);
            WizardSpeachWelcome.setText("<html> Now you are heavier, in the pockets and in the soul!<html>");
            WizardBallonWelcome.setVisible(true);
            WizardSpeachWelcome.setVisible(true);
            wizardLabelsTimer(3000);
        }
        else{
            WizardSpeachWelcome.setText("<html>There's not enough coins. Fight a litle more and come visit me again :) <html>");
            WizardBallonWelcome.setVisible(true);
            WizardSpeachWelcome.setVisible(true);
            wizardLabelsTimer(3000);
        }
    }//GEN-LAST:event_Item1ButtonMouseClicked

    private void Item2ButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item2ButtonMouseClicked
        ItemData.getDataItem(2);
        if( ItemData.isCoinsEnough(ItemData.getItemPrice(), wallet) ){
            UserData.updateUserWallet(ItemData.getItemPrice(),"minus");
            ItemData.insertItemBackpack(2);
            setUser();
            
            Item2Panel.setVisible(false);
            WizardSpeachWelcome.setText("<html> Now you are heavier, in the pockets and in the soul!<html>");
            WizardBallonWelcome.setVisible(true);
            WizardSpeachWelcome.setVisible(true);
            wizardLabelsTimer(3000);
        }
        else{
            WizardSpeachWelcome.setText("<html>There's not enough coins. Fight a litle more and come visit me again :) <html>");
            WizardBallonWelcome.setVisible(true);
            WizardSpeachWelcome.setVisible(true);
            wizardLabelsTimer(3000);
        }
    }//GEN-LAST:event_Item2ButtonMouseClicked

    private void Item3ButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item3ButtonMouseClicked
        ItemData.getDataItem(3);
        if( ItemData.isCoinsEnough(ItemData.getItemPrice(), wallet) ){
            UserData.updateUserWallet(ItemData.getItemPrice(),"minus");
            ItemData.insertItemBackpack(3);
            setUser();
            
            Item3Panel.setVisible(false);
            WizardSpeachWelcome.setText("<html> Now you are heavier, in the pockets and in the soul!<html>");
            WizardBallonWelcome.setVisible(true);
            WizardSpeachWelcome.setVisible(true);
            wizardLabelsTimer(3000);
        }
        else{
            WizardSpeachWelcome.setText("<html>There's not enough coins. Fight a litle more and come visit me again :) <html>");
            WizardBallonWelcome.setVisible(true);
            WizardSpeachWelcome.setVisible(true);
            wizardLabelsTimer(3000);
        }
    }//GEN-LAST:event_Item3ButtonMouseClicked

    private void Item4ButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item4ButtonMouseClicked
        ItemData.getDataItem(4);
        if( ItemData.isCoinsEnough(ItemData.getItemPrice(), wallet) ){
            UserData.updateUserWallet(ItemData.getItemPrice(),"minus");
            ItemData.insertItemBackpack(4);
            setUser();
            
            Item4Panel.setVisible(false);
            WizardSpeachWelcome.setText("<html> Now you are heavier, in the pockets and in the soul!<html>");
            WizardBallonWelcome.setVisible(true);
            WizardSpeachWelcome.setVisible(true);
            wizardLabelsTimer(3000);
        }
        else{
            WizardSpeachWelcome.setText("<html>There's not enough coins. Fight a litle more and come visit me again :) <html>");
            WizardBallonWelcome.setVisible(true);
            WizardSpeachWelcome.setVisible(true);
            wizardLabelsTimer(3000);
        }
    }//GEN-LAST:event_Item4ButtonMouseClicked

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
            java.util.logging.Logger.getLogger(GameplayWizardHomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameplayWizardHomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameplayWizardHomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameplayWizardHomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
      
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameplayWizardHomeForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel CloseButton;
    private javax.swing.JButton Item1Button;
    private javax.swing.JLabel Item1Img;
    private javax.swing.JPanel Item1Panel;
    private javax.swing.JButton Item2Button;
    private javax.swing.JLabel Item2Img;
    private javax.swing.JPanel Item2Panel;
    private javax.swing.JButton Item3Button;
    private javax.swing.JLabel Item3Img;
    private javax.swing.JPanel Item3Panel;
    private javax.swing.JButton Item4Button;
    private javax.swing.JLabel Item4Img;
    private javax.swing.JPanel Item4Panel;
    private javax.swing.JButton JUserButton;
    private javax.swing.JButton JWizHouseButton;
    private javax.swing.JLabel MinimizeButton;
    private javax.swing.JButton TreasureWizButton;
    private javax.swing.JPanel UserInfoPanel;
    private javax.swing.JPanel WizTreasurePanel;
    private javax.swing.JLabel WizardBallonWelcome;
    private javax.swing.JLabel WizardSpeachWelcome;
    private javax.swing.JLabel jLabelCoin;
    private javax.swing.JLabel jLabelExp;
    private javax.swing.JLabel jLabelMoney;
    private javax.swing.JLabel jLabelMush;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelMinCloseAll;
    private javax.swing.JLabel jUserImg;
    private javax.swing.JLabel jWizRideImg;
    private javax.swing.JLabel jWizardImg;
    // End of variables declaration//GEN-END:variables
}
