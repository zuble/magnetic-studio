package javaapplication1;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author Legion
 */
public class GameplayWizardHomeForm extends javax.swing.JFrame {
   
    public String user;
    private String Class;
    private int wallet , wisdom , buttonUser = 0 , buttonBackpack = 0;
    boolean[] ItemsToBuy = {false,false,false,false} ;
    boolean[] ItemsBackpack = {false,false,false,false} ;
    boolean wizspeach = false , atLeast1BougtItem = false;
    DataUser UserData;
    DataItem ItemData;
    Random rand = new Random();
    
  
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
            wizBallonWelcome.setVisible(false);
            wizSpeachWelcome.setVisible(false);
            wizInventoryPanel.setVisible(true);
            wizSpecialMessage.setVisible(true);
            wizspeach = true;
            
            userLabel.setVisible(true);
            userInfoPanel.setVisible(true);
            userButton.setVisible(true);
            
            dragonButton.setVisible(true);     
        });
        timer.setRepeats(false);
        timer.start();
    } 
    
    public void wizardItemsTimer(boolean[] ItemsToShow , int Time){
        Timer timer = new Timer(Time, (ActionEvent e) -> {
            setItems("wizitems");
            wizInventoryPanel.setVisible(true);
        });
        timer.setRepeats(false);
        timer.start();
    }   
    
    public void wizardSpecialMessageTimer(int time){
        Timer timer = new Timer(time, (ActionEvent e) -> {
            wizBallonWelcome.setVisible(false);
            wizSpeachWelcome.setVisible(false);
        });
        timer.setRepeats(false);
        timer.start();
    } 
    
    public void passData(String user) {
        //SET USER INFO
        this.user = user;
        setUser();
        
        //SET ITEMS 
        setItems("passdata");
        setItems("wizitems");
        
        //SET BACKGROUND/USER CLASS IMAGE
        setImgs();
        
        //SET WIZARD SPEACH + BUTTONS DISABLE
        setWizWelcome();
        wizBallonWelcome.setVisible(true);
        wizSpeachWelcome.setVisible(true);
        
        dragonSpeach.setVisible(false);
        dragonBallon.setVisible(false);
        dragonButton.setVisible(false);
        
        userButton.setVisible(false);
        userLabel.setVisible(false);
        userInfoPanel.setVisible(false);
        
        catBallon.setVisible(false);
        catSpeach.setVisible(false);
        
        wizSpecialMessage.setVisible(false);
        
        //SETS THE DISPONIBLE WIZARD ITEMS AFTER TIME OVER
        wizardItemsTimer(ItemsToBuy,8000);
        wizardLabelsTimer(8000);
    }
    
    private void setUser(){
        //GETS ALL USER DATA NEEDED
        UserData = new DataUser(this.user);  
        this.Class = UserData.getUserClass();
        this.wallet = UserData.getUserWallet();
        this.wisdom = UserData.getUserWisdom();
        
        coinLabel.setText(String.valueOf(this.wallet));
        wisdomLabel.setText(String.valueOf(this.wisdom));
        userLabel.setText(this.user+" the "+this.Class);
    }
    
    private void setImgs(){
        userButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/"+this.Class+"2.png")));
        userButton.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/"+this.Class+"2.png")));
        
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/witchroom.png")));
        
        UserData = new DataUser(user);
        this.wisdom = UserData.getUserWisdom();
        if(wisdom <= 30){mushieImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mushies_stage1.png"))); }
        if((wisdom >= 40) && (wisdom <= 70)){mushieImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mushies_stage2.png"))); }
        if(wisdom >= 80){mushieImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mushies_stage3.png"))); }
    }
    
    private void setItems(String Location){
        if( "passdata".equals(Location) ){
            //INITIALIZE DATAITEM
            ItemData = new DataItem(this.user);
            //SETS ITEMS NOT VISIBLE 
            wizInventoryPanel.setVisible(false);
            userInventoryPanel.setVisible(false);
            //SETS TOOLTIP+PRICE FOR EACH ITEM
            ItemData.getDataItem(1);
            wizItem1Img.setToolTipText(ItemData.getItemName());
            item1Inventory.setToolTipText(ItemData.getItemName());
            wizItem1Button.setText(String.valueOf(ItemData.getItemPrice()));
            ItemData.getDataItem(2);
            wizItem2Img.setToolTipText(ItemData.getItemName());
            item2Inventory.setToolTipText(ItemData.getItemName());
            wizItem2Button.setText(String.valueOf(ItemData.getItemPrice()));
            ItemData.getDataItem(3);
            wizItem3Img.setToolTipText(ItemData.getItemName());
            item3Inventory.setToolTipText(ItemData.getItemName());
            wizItem3Button.setText(String.valueOf(ItemData.getItemPrice()));
            ItemData.getDataItem(4);
            wizItem4Img.setToolTipText(ItemData.getItemName());
            item4Inventory.setToolTipText(ItemData.getItemName());
            wizItem4Button.setText(String.valueOf(ItemData.getItemPrice()));  
        }
        
        if( "wizitems".equals(Location) ){
            //POPULATES ITEMSTOBUY 
            ItemsToBuy = ItemData.areItemsAvaiableToBuy(ItemsToBuy);
            if(ItemsToBuy[0])wizItem1Panel.setVisible(true);
            else wizItem1Panel.setVisible(false);
            if(ItemsToBuy[1])wizItem2Panel.setVisible(true);
            else wizItem2Panel.setVisible(false);
            if(ItemsToBuy[2])wizItem3Panel.setVisible(true);
            else wizItem3Panel.setVisible(false);
            if(ItemsToBuy[3])wizItem4Panel.setVisible(true);
            else wizItem4Panel.setVisible(false);
        }
        if( "backpack".equals(Location) ){
            //INITIALIZE DataItem AND POPULATES ITEMSBACKPACK 
            ItemData = new DataItem(this.user);
            ItemsBackpack = ItemData.areItemsInBackpack(ItemsBackpack); 
            //SET USER ITEMS VISIBLE
            if(ItemsBackpack[0]){item1Inventory.setVisible(true);}
            else item1Inventory.setVisible(false); 
            if(ItemsBackpack[1]){item2Inventory.setVisible(true);}
            else item2Inventory.setVisible(false); 
            if(ItemsBackpack[2]){item3Inventory.setVisible(true);}
            else item3Inventory.setVisible(false); 
            if(ItemsBackpack[3]){item4Inventory.setVisible(true);}
            else item4Inventory.setVisible(false);
        } 
    }
    
    private void setWizWelcome(){
        if( anyBoughtItem() ){
            wizSpeachWelcome.setText("<html>Welcome "+this.user+"<html>. Always a joy to see you here :) ");
        }
        else wizSpeachWelcome.setText("<html> Here is my humble source of enlighment and knowledge, my house. You can get special crystals and some other threats :) <html>");
    }
    
    private boolean anyBoughtItem(){
        ItemData = new DataItem(this.user);
        for( int i = 1 ; i < 5 ; i++){
            if ( ItemData.isItemInBackpack(i) ) atLeast1BougtItem = true; 
        }
        return atLeast1BougtItem;
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
        logoutButton = new javax.swing.JButton();
        wizInventoryPanel = new javax.swing.JPanel();
        wizItem1Panel = new javax.swing.JPanel();
        wizItem1Img = new javax.swing.JLabel();
        wizItem1Button = new javax.swing.JButton();
        wizItem2Panel = new javax.swing.JPanel();
        wizItem2Img = new javax.swing.JLabel();
        wizItem2Button = new javax.swing.JButton();
        wizItem3Panel = new javax.swing.JPanel();
        wizItem3Img = new javax.swing.JLabel();
        wizItem3Button = new javax.swing.JButton();
        wizItem4Panel = new javax.swing.JPanel();
        wizItem4Img = new javax.swing.JLabel();
        wizItem4Button = new javax.swing.JButton();
        userInventoryPanel = new javax.swing.JPanel();
        item1Inventory = new javax.swing.JLabel();
        item2Inventory = new javax.swing.JLabel();
        item3Inventory = new javax.swing.JLabel();
        item4Inventory = new javax.swing.JLabel();
        backpackButton = new javax.swing.JButton();
        wizSpeachWelcome = new javax.swing.JLabel();
        wizBallonWelcome = new javax.swing.JLabel();
        wizImg = new javax.swing.JLabel();
        dragonSpeach = new javax.swing.JLabel();
        dragonBallon = new javax.swing.JLabel();
        dragonButton = new javax.swing.JButton();
        catImg = new javax.swing.JLabel();
        catSpeach = new javax.swing.JLabel();
        catBallon = new javax.swing.JLabel();
        userButton = new javax.swing.JButton();
        userLabel = new javax.swing.JLabel();
        userInfoPanel = new javax.swing.JPanel();
        mushieImg = new javax.swing.JLabel();
        wisdomLabel = new javax.swing.JLabel();
        coinImg = new javax.swing.JLabel();
        coinLabel = new javax.swing.JLabel();
        wizSpecialMessage = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(null);

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

        wizInventoryPanel.setBorder(new javax.swing.border.MatteBorder(null));
        wizInventoryPanel.setOpaque(false);

        wizItem1Panel.setOpaque(false);

        wizItem1Img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/xtal1.png"))); // NOI18N
        wizItem1Img.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                wizItem1ImgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                wizItem1ImgMouseExited(evt);
            }
        });

        wizItem1Button.setBackground(new java.awt.Color(0, 0, 0));
        wizItem1Button.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        wizItem1Button.setForeground(new java.awt.Color(255, 255, 255));
        wizItem1Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/coin.png"))); // NOI18N
        wizItem1Button.setBorder(new javax.swing.border.MatteBorder(null));
        wizItem1Button.setFocusPainted( false );
        wizItem1Button.setBorderPainted(false);
        wizItem1Button.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        wizItem1Button.setOpaque(false);
        wizItem1Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wizItem1ButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout wizItem1PanelLayout = new javax.swing.GroupLayout(wizItem1Panel);
        wizItem1Panel.setLayout(wizItem1PanelLayout);
        wizItem1PanelLayout.setHorizontalGroup(
            wizItem1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, wizItem1PanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(wizItem1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(wizItem1Button, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wizItem1Img))
                .addGap(24, 24, 24))
        );
        wizItem1PanelLayout.setVerticalGroup(
            wizItem1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wizItem1PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(wizItem1Img, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(wizItem1Button, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        wizItem2Panel.setOpaque(false);

        wizItem2Img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/xtal2.png"))); // NOI18N
        wizItem2Img.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                wizItem2ImgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                wizItem2ImgMouseExited(evt);
            }
        });

        wizItem2Button.setBackground(new java.awt.Color(0, 0, 0));
        wizItem2Button.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        wizItem2Button.setForeground(new java.awt.Color(255, 255, 255));
        wizItem2Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/coin.png"))); // NOI18N
        wizItem2Button.setBorder(new javax.swing.border.MatteBorder(null));
        wizItem2Button.setFocusPainted( false );
        wizItem2Button.setBorderPainted(false);
        wizItem2Button.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        wizItem2Button.setOpaque(false);
        wizItem2Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wizItem2ButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout wizItem2PanelLayout = new javax.swing.GroupLayout(wizItem2Panel);
        wizItem2Panel.setLayout(wizItem2PanelLayout);
        wizItem2PanelLayout.setHorizontalGroup(
            wizItem2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wizItem2PanelLayout.createSequentialGroup()
                .addGroup(wizItem2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(wizItem2PanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(wizItem2Button, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(wizItem2PanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(wizItem2Img)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        wizItem2PanelLayout.setVerticalGroup(
            wizItem2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wizItem2PanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(wizItem2Img)
                .addGap(18, 18, 18)
                .addComponent(wizItem2Button, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        wizItem3Panel.setOpaque(false);

        wizItem3Img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/xtal3.png"))); // NOI18N
        wizItem3Img.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                wizItem3ImgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                wizItem3ImgMouseExited(evt);
            }
        });

        wizItem3Button.setBackground(new java.awt.Color(0, 0, 0));
        wizItem3Button.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        wizItem3Button.setForeground(new java.awt.Color(255, 255, 255));
        wizItem3Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/coin.png"))); // NOI18N
        wizItem3Button.setBorder(new javax.swing.border.MatteBorder(null));
        wizItem3Button.setBorderPainted(false);
        wizItem3Button.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        wizItem3Button.setOpaque(false);
        wizItem3Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wizItem3ButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout wizItem3PanelLayout = new javax.swing.GroupLayout(wizItem3Panel);
        wizItem3Panel.setLayout(wizItem3PanelLayout);
        wizItem3PanelLayout.setHorizontalGroup(
            wizItem3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wizItem3PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(wizItem3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(wizItem3Button, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wizItem3Img))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        wizItem3PanelLayout.setVerticalGroup(
            wizItem3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wizItem3PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(wizItem3Img, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(wizItem3Button, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        wizItem4Panel.setOpaque(false);

        wizItem4Img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/xtal4.png"))); // NOI18N
        wizItem4Img.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                wizItem4ImgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                wizItem4ImgMouseExited(evt);
            }
        });

        wizItem4Button.setBackground(new java.awt.Color(0, 0, 0));
        wizItem4Button.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        wizItem4Button.setForeground(new java.awt.Color(255, 255, 255));
        wizItem4Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/coin.png"))); // NOI18N
        wizItem4Button.setBorder(new javax.swing.border.MatteBorder(null));
        wizItem4Button.setBorderPainted(false);
        wizItem4Button.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        wizItem4Button.setOpaque(false);
        wizItem4Button.setFocusPainted( false );
        wizItem4Button.setBorderPainted(false);
        wizItem4Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wizItem4ButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout wizItem4PanelLayout = new javax.swing.GroupLayout(wizItem4Panel);
        wizItem4Panel.setLayout(wizItem4PanelLayout);
        wizItem4PanelLayout.setHorizontalGroup(
            wizItem4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wizItem4PanelLayout.createSequentialGroup()
                .addComponent(wizItem4Button, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 32, Short.MAX_VALUE))
            .addGroup(wizItem4PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(wizItem4Img, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        wizItem4PanelLayout.setVerticalGroup(
            wizItem4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wizItem4PanelLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(wizItem4Img)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(wizItem4Button, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout wizInventoryPanelLayout = new javax.swing.GroupLayout(wizInventoryPanel);
        wizInventoryPanel.setLayout(wizInventoryPanelLayout);
        wizInventoryPanelLayout.setHorizontalGroup(
            wizInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wizInventoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(wizInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(wizInventoryPanelLayout.createSequentialGroup()
                        .addComponent(wizItem1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(wizItem2Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(wizInventoryPanelLayout.createSequentialGroup()
                        .addComponent(wizItem3Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(wizItem4Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)))
                .addContainerGap())
        );
        wizInventoryPanelLayout.setVerticalGroup(
            wizInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wizInventoryPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(wizInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(wizItem1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wizItem2Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(wizInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(wizItem4Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(wizItem3Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(wizInventoryPanel);
        wizInventoryPanel.setBounds(150, 110, 300, 350);

        userInventoryPanel.setBorder(new javax.swing.border.MatteBorder(null));
        userInventoryPanel.setOpaque(false);

        item1Inventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/xtal1icon.png"))); // NOI18N
        item1Inventory.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        item1Inventory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                item1InventoryMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                item1InventoryMouseExited(evt);
            }
        });

        item2Inventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/xtal2icon.png"))); // NOI18N
        item2Inventory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                item2InventoryMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                item2InventoryMouseExited(evt);
            }
        });

        item3Inventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/xtal3icon.png"))); // NOI18N
        item3Inventory.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        item3Inventory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                item3InventoryMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                item3InventoryMouseExited(evt);
            }
        });

        item4Inventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/xtal4icon.png"))); // NOI18N
        item4Inventory.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        item4Inventory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                item4InventoryMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                item4InventoryMouseExited(evt);
            }
        });

        backpackButton.setFont(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        backpackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/chest-close.png"))); // NOI18N
        backpackButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        backpackButton.setBorderPainted(false);
        backpackButton.setContentAreaFilled(false);
        backpackButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        backpackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backpackButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout userInventoryPanelLayout = new javax.swing.GroupLayout(userInventoryPanel);
        userInventoryPanel.setLayout(userInventoryPanelLayout);
        userInventoryPanelLayout.setHorizontalGroup(
            userInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userInventoryPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(backpackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(userInventoryPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(item1Inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(item2Inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(item3Inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(item4Inventory)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        userInventoryPanelLayout.setVerticalGroup(
            userInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userInventoryPanelLayout.createSequentialGroup()
                .addGroup(userInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(userInventoryPanelLayout.createSequentialGroup()
                        .addContainerGap(197, Short.MAX_VALUE)
                        .addComponent(item1Inventory))
                    .addGroup(userInventoryPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(userInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(item4Inventory)
                            .addComponent(item3Inventory)
                            .addComponent(item2Inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backpackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(userInventoryPanel);
        userInventoryPanel.setBounds(200, 130, 280, 350);

        wizSpeachWelcome.setFont(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        jPanel1.add(wizSpeachWelcome);
        wizSpeachWelcome.setBounds(190, 20, 280, 90);

        wizBallonWelcome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/balloon3.png"))); // NOI18N
        jPanel1.add(wizBallonWelcome);
        wizBallonWelcome.setBounds(150, 0, 360, 140);

        wizImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/wizard2.png"))); // NOI18N
        jPanel1.add(wizImg);
        wizImg.setBounds(20, 60, 120, 130);

        dragonSpeach.setFont(new java.awt.Font("Book Antiqua", 1, 10)); // NOI18N
        dragonSpeach.setForeground(new java.awt.Color(0, 0, 0));
        dragonSpeach.setText("<html> Lets ride back, fast as light! <html>");
        jPanel1.add(dragonSpeach);
        dragonSpeach.setBounds(580, 340, 90, 50);

        dragonBallon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/balloon4.png"))); // NOI18N
        jPanel1.add(dragonBallon);
        dragonBallon.setBounds(550, 320, 160, 110);

        dragonButton.setBackground(new java.awt.Color(170, 63, 7));
        dragonButton.setFont(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        dragonButton.setForeground(new java.awt.Color(255, 255, 255));
        dragonButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/dragon.png"))); // NOI18N
        dragonButton.setToolTipText("Ocapse, the Wizard ride");
        dragonButton.setBorder(new javax.swing.border.MatteBorder(null));
        dragonButton.setContentAreaFilled(false);
        dragonButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dragonButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        dragonButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dragonButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dragonButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dragonButtonMouseExited(evt);
            }
        });
        jPanel1.add(dragonButton);
        dragonButton.setBounds(420, 350, 200, 140);

        catImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cat.png"))); // NOI18N
        catImg.setToolTipText("Voiello");
        catImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                catImgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                catImgMouseExited(evt);
            }
        });
        jPanel1.add(catImg);
        catImg.setBounds(460, 200, 70, 130);

        catSpeach.setFont(new java.awt.Font("Book Antiqua", 1, 10)); // NOI18N
        catSpeach.setForeground(new java.awt.Color(0, 0, 0));
        catSpeach.setText("<html>Not doing wizzie things now, have meows matters.");
        jPanel1.add(catSpeach);
        catSpeach.setBounds(550, 160, 90, 50);

        catBallon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/balloon4.png"))); // NOI18N
        jPanel1.add(catBallon);
        catBallon.setBounds(520, 140, 160, 110);

        userButton.setBorder(new javax.swing.border.MatteBorder(null));
        userButton.setContentAreaFilled(false);
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
        wisdomLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        wisdomLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        coinImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/coin.png"))); // NOI18N

        coinLabel.setBackground(new java.awt.Color(255, 204, 51));
        coinLabel.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        coinLabel.setForeground(new java.awt.Color(255, 255, 255));
        coinLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coinLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout userInfoPanelLayout = new javax.swing.GroupLayout(userInfoPanel);
        userInfoPanel.setLayout(userInfoPanelLayout);
        userInfoPanelLayout.setHorizontalGroup(
            userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userInfoPanelLayout.createSequentialGroup()
                .addComponent(coinLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(coinImg, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(wisdomLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mushieImg, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        userInfoPanelLayout.setVerticalGroup(
            userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(coinLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(wisdomLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(coinImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mushieImg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        jPanel1.add(userInfoPanel);
        userInfoPanel.setBounds(10, 450, 140, 40);

        wizSpecialMessage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/moon-potion.png"))); // NOI18N
        wizSpecialMessage.setToolTipText("Special moon potion");
        wizSpecialMessage.setBorderPainted(false);
        wizSpecialMessage.setContentAreaFilled(false);
        wizSpecialMessage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wizSpecialMessageMouseClicked(evt);
            }
        });
        jPanel1.add(wizSpecialMessage);
        wizSpecialMessage.setBounds(590, 260, 50, 60);

        background.setBackground(new java.awt.Color(255, 255, 51));
        background.setFont(new java.awt.Font("Book Antiqua", 1, 24)); // NOI18N
        jPanel1.add(background);
        background.setBounds(0, 0, 700, 500);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 500));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dragonButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dragonButtonMouseClicked
        GameplayUserHomeForm UserHomeTravel = new GameplayUserHomeForm();
        UserHomeTravel.passData(this.user,"WizHome");
        UserHomeTravel.setVisible(true);
        this.dispose();
         
    }//GEN-LAST:event_dragonButtonMouseClicked

    private void userButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userButtonMouseClicked
        if( buttonUser == 0 ){
            //SETS BAU CLOSE
            backpackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/chest-close.png")));
            wizInventoryPanel.setVisible(false);
            item1Inventory.setVisible(false);
            item2Inventory.setVisible(false);
            item3Inventory.setVisible(false);
            item4Inventory.setVisible(false);
            userInventoryPanel.setVisible(true);
            buttonUser = 1;
        }
        else if( buttonUser == 1 ){
            //SETS BAU CLOSE
            backpackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/chest-close.png")));
            userInventoryPanel.setVisible(false);
            wizInventoryPanel.setVisible(true);
            buttonUser = 0;
        }
    }//GEN-LAST:event_userButtonMouseClicked
    
    private void wizItem4ButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wizItem4ButtonMouseClicked
        ItemData.getDataItem(4);
        if( ItemData.areCoinsEnough(ItemData.getItemPrice(), wallet) ){
            UserData.updateUserWallet(ItemData.getItemPrice(),"minus");
            ItemData.insertItemBackpack(4);
            ItemData.setItemState(3,"OFF");
            setUser();

            wizItem4Panel.setVisible(false);
            wizSpeachWelcome.setText("<html> Now you are heavier, in the pockets and in the soul!<html>");
            wizBallonWelcome.setVisible(true);
            wizSpeachWelcome.setVisible(true);
            wizardLabelsTimer(3000);
        }
        else{
            wizSpeachWelcome.setText("<html>There's not enough coins. Fight a litle more and come visit me again :) <html>");
            wizBallonWelcome.setVisible(true);
            wizSpeachWelcome.setVisible(true);
            wizardLabelsTimer(3000);
        }
    }//GEN-LAST:event_wizItem4ButtonMouseClicked

    private void wizItem4ImgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wizItem4ImgMouseExited
        wizBallonWelcome.setVisible(false);
        wizSpeachWelcome.setVisible(false);
    }//GEN-LAST:event_wizItem4ImgMouseExited

    private void wizItem4ImgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wizItem4ImgMouseEntered
        ItemData.getDataItem(4);
        wizSpeachWelcome.setText("<html>"+ItemData.getItemInfo()+"<html>");
        wizBallonWelcome.setVisible(true);
        wizSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_wizItem4ImgMouseEntered

    private void wizItem3ButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wizItem3ButtonMouseClicked
        ItemData.getDataItem(3);
        if( ItemData.areCoinsEnough(ItemData.getItemPrice(), wallet) ){
            UserData.updateUserWallet(ItemData.getItemPrice(),"minus");
            ItemData.insertItemBackpack(3);
            ItemData.setItemState(3,"ON");
            setUser();

            wizItem3Panel.setVisible(false);
            wizSpeachWelcome.setText("<html> Now you are heavier, in the pockets and in the soul!<html>");
            wizBallonWelcome.setVisible(true);
            wizSpeachWelcome.setVisible(true);
            wizardLabelsTimer(3000);
        }
        else{
            wizSpeachWelcome.setText("<html>There's not enough coins. Fight a litle more and come visit me again :) <html>");
            wizBallonWelcome.setVisible(true);
            wizSpeachWelcome.setVisible(true);
            wizardLabelsTimer(3000);
        }
    }//GEN-LAST:event_wizItem3ButtonMouseClicked

    private void wizItem3ImgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wizItem3ImgMouseExited
        wizBallonWelcome.setVisible(false);
        wizSpeachWelcome.setVisible(false);
    }//GEN-LAST:event_wizItem3ImgMouseExited

    private void wizItem3ImgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wizItem3ImgMouseEntered
        ItemData.getDataItem(3);
        wizSpeachWelcome.setText("<html>"+ItemData.getItemInfo()+"<html>");
        wizBallonWelcome.setVisible(true);
        wizSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_wizItem3ImgMouseEntered

    private void wizItem2ButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wizItem2ButtonMouseClicked
        ItemData.getDataItem(2);
        if( ItemData.areCoinsEnough(ItemData.getItemPrice(), wallet) ){
            UserData.updateUserWallet(ItemData.getItemPrice(),"minus");
            ItemData.insertItemBackpack(2);
            ItemData.setItemState(2,"OFF");
            setUser();

            wizItem2Panel.setVisible(false);
            wizSpeachWelcome.setText("<html> Now you are heavier, in the pockets and in the soul!<html>");
            wizBallonWelcome.setVisible(true);
            wizSpeachWelcome.setVisible(true);
            wizardLabelsTimer(3000);
        }
        else{
            wizSpeachWelcome.setText("<html>There's not enough coins. Fight a litle more and come visit me again :) <html>");
            wizBallonWelcome.setVisible(true);
            wizSpeachWelcome.setVisible(true);
            wizardLabelsTimer(3000);
        }
    }//GEN-LAST:event_wizItem2ButtonMouseClicked

    private void wizItem2ImgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wizItem2ImgMouseExited
        wizBallonWelcome.setVisible(false);
        wizSpeachWelcome.setVisible(false);
    }//GEN-LAST:event_wizItem2ImgMouseExited

    private void wizItem2ImgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wizItem2ImgMouseEntered
        ItemData.getDataItem(2);
        wizSpeachWelcome.setText("<html>"+ItemData.getItemInfo()+"<html>");
        wizBallonWelcome.setVisible(true);
        wizSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_wizItem2ImgMouseEntered

    private void wizItem1ButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wizItem1ButtonMouseClicked
        //TRUE = ENOUGH COINS 4 ITEM
        ItemData.getDataItem(1);
        if( ItemData.areCoinsEnough(ItemData.getItemPrice(), wallet) ){
            UserData.updateUserWallet(ItemData.getItemPrice(),"minus");
            ItemData.insertItemBackpack(1);
            ItemData.setItemState(1,"ON");
            setUser();

            wizItem1Panel.setVisible(false);
            wizSpeachWelcome.setText("<html> Now you are heavier, in the pockets and in the soul!<html>");
            wizBallonWelcome.setVisible(true);
            wizSpeachWelcome.setVisible(true);
            wizardLabelsTimer(3000);
        }
        else{
            wizSpeachWelcome.setText("<html>There's not enough coins. Fight a litle more and come visit me again :) <html>");
            wizBallonWelcome.setVisible(true);
            wizSpeachWelcome.setVisible(true);
            wizardLabelsTimer(3000);
        }
    }//GEN-LAST:event_wizItem1ButtonMouseClicked

    private void wizItem1ImgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wizItem1ImgMouseExited
        wizBallonWelcome.setVisible(false);
        wizSpeachWelcome.setVisible(false);
    }//GEN-LAST:event_wizItem1ImgMouseExited

    private void wizItem1ImgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wizItem1ImgMouseEntered
        ItemData.getDataItem(1);
        wizSpeachWelcome.setText("<html>"+ItemData.getItemInfo()+"<html>");
        wizBallonWelcome.setVisible(true);
        wizSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_wizItem1ImgMouseEntered

    private void item1InventoryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_item1InventoryMouseEntered
        ItemData.getDataItem(1);
        wizSpeachWelcome.setText("<html>"+ItemData.getItemInfo()+"<html>");
        wizBallonWelcome.setVisible(true);
        wizSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_item1InventoryMouseEntered

    private void item1InventoryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_item1InventoryMouseExited
        wizBallonWelcome.setVisible(false);
        wizSpeachWelcome.setVisible(false);
    }//GEN-LAST:event_item1InventoryMouseExited

    private void item2InventoryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_item2InventoryMouseEntered
        ItemData.getDataItem(2);
        wizSpeachWelcome.setText("<html>"+ItemData.getItemInfo()+"<html>");
        wizBallonWelcome.setVisible(true);
        wizSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_item2InventoryMouseEntered

    private void item2InventoryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_item2InventoryMouseExited
        wizBallonWelcome.setVisible(false);
        wizSpeachWelcome.setVisible(false);
    }//GEN-LAST:event_item2InventoryMouseExited

    private void item3InventoryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_item3InventoryMouseEntered
        ItemData.getDataItem(3);
        wizSpeachWelcome.setText("<html>"+ItemData.getItemInfo()+"<html>");
        wizBallonWelcome.setVisible(true);
        wizSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_item3InventoryMouseEntered

    private void item3InventoryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_item3InventoryMouseExited
        wizBallonWelcome.setVisible(false);
        wizSpeachWelcome.setVisible(false);
    }//GEN-LAST:event_item3InventoryMouseExited

    private void item4InventoryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_item4InventoryMouseEntered
        ItemData.getDataItem(4);
        wizSpeachWelcome.setText("<html>"+ItemData.getItemInfo()+"<html>");
        wizBallonWelcome.setVisible(true);
        wizSpeachWelcome.setVisible(true);
    }//GEN-LAST:event_item4InventoryMouseEntered

    private void item4InventoryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_item4InventoryMouseExited
        wizBallonWelcome.setVisible(false);
        wizSpeachWelcome.setVisible(false);
    }//GEN-LAST:event_item4InventoryMouseExited

    private void backpackButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backpackButtonMouseClicked
        if( buttonBackpack == 0 ){
            
            //SET USER ITEMS VISIBLE
            setItems("backpack");
            
            //SETS BAU OPEN
            backpackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/chest-open.png")));
            
            buttonBackpack = 1;
        }
        else if( buttonUser == 1 ){
            item1Inventory.setVisible(false); 
            item2Inventory.setVisible(false); 
            item3Inventory.setVisible(false); 
            item4Inventory.setVisible(false);
            //SETS BAU CLOSE
            backpackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/chest-close.png")));
            buttonBackpack = 0;
        }
    }//GEN-LAST:event_backpackButtonMouseClicked

    private void catImgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_catImgMouseEntered
        if(wizspeach){
            catBallon.setVisible(true);
            catSpeach.setVisible(true);
        }  
    }//GEN-LAST:event_catImgMouseEntered

    private void catImgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_catImgMouseExited
        if(wizspeach){
        catBallon.setVisible(false);
        catSpeach.setVisible(false);
        }
    }//GEN-LAST:event_catImgMouseExited

    private void dragonButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dragonButtonMouseEntered
        if(wizspeach){
        dragonBallon.setVisible(true);
        dragonSpeach.setVisible(true);
        }
    }//GEN-LAST:event_dragonButtonMouseEntered

    private void dragonButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dragonButtonMouseExited
        if(wizspeach){
        dragonBallon.setVisible(false);
        dragonSpeach.setVisible(false);
        }
    }//GEN-LAST:event_dragonButtonMouseExited

    private void closeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseClicked
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

    private void wizSpecialMessageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wizSpecialMessageMouseClicked
        int auxRnd = rand.nextInt(4); 
        System.out.println(auxRnd);
        switch (auxRnd) {
            case 1:
                wizSpeachWelcome.setText("The dream is the precursor of everything!");
                wizBallonWelcome.setVisible(true);
                wizSpeachWelcome.setVisible(true);
                wizardSpecialMessageTimer(4000);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }  
    }//GEN-LAST:event_wizSpecialMessageMouseClicked

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
    private javax.swing.JLabel background;
    private javax.swing.JButton backpackButton;
    private javax.swing.JLabel catBallon;
    private javax.swing.JLabel catImg;
    private javax.swing.JLabel catSpeach;
    private javax.swing.JLabel closeButton;
    private javax.swing.JPanel closeMinPanel;
    private javax.swing.JLabel coinImg;
    private javax.swing.JLabel coinLabel;
    private javax.swing.JLabel dragonBallon;
    private javax.swing.JButton dragonButton;
    private javax.swing.JLabel dragonSpeach;
    private javax.swing.JLabel item1Inventory;
    private javax.swing.JLabel item2Inventory;
    private javax.swing.JLabel item3Inventory;
    private javax.swing.JLabel item4Inventory;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton logoutButton;
    private javax.swing.JLabel minimizeButton;
    private javax.swing.JLabel mushieImg;
    private javax.swing.JButton userButton;
    private javax.swing.JPanel userInfoPanel;
    private javax.swing.JPanel userInventoryPanel;
    private javax.swing.JLabel userLabel;
    private javax.swing.JLabel wisdomLabel;
    private javax.swing.JLabel wizBallonWelcome;
    private javax.swing.JLabel wizImg;
    private javax.swing.JPanel wizInventoryPanel;
    private javax.swing.JButton wizItem1Button;
    private javax.swing.JLabel wizItem1Img;
    private javax.swing.JPanel wizItem1Panel;
    private javax.swing.JButton wizItem2Button;
    private javax.swing.JLabel wizItem2Img;
    private javax.swing.JPanel wizItem2Panel;
    private javax.swing.JButton wizItem3Button;
    private javax.swing.JLabel wizItem3Img;
    private javax.swing.JPanel wizItem3Panel;
    private javax.swing.JButton wizItem4Button;
    private javax.swing.JLabel wizItem4Img;
    private javax.swing.JPanel wizItem4Panel;
    private javax.swing.JLabel wizSpeachWelcome;
    private javax.swing.JButton wizSpecialMessage;
    // End of variables declaration//GEN-END:variables
}
