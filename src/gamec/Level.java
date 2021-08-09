/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
    YAPILACAKLAR
    -bullet i kendi classında yarat
    -point oluştur x ve y ler için
    -

*/


package gamec;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author edanu
 */ 
public abstract class Level extends JPanel /*implements ActionListener*/ {
    protected Timer frameTimer;
    protected Enemy Cats;
    protected Player Chicken;
    protected Target target;
    protected int WEIGHT, HEIGHT,enemySize,targetSize,enemyDiff, targetDiff;
    protected Timer time;
    protected int score;
    protected ActionListener timerTask,frameTask;
    protected boolean gameOver=false;
    protected Cloud cloud;
    JButton restartBtn;
    JButton nextBtn;
    protected boolean nextLevel;
    
    //protected Action RightMoveAction;

    public Level( int WEIGHT, int HEIGHT,int enemyDiff,int targetDiff,int enemySize,int targetSize) {
        timerTask=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if(Chicken.checkCollision(Cats)){ 
                  gameOver=true;
                  time.stop();
                  
                }
                if(target.checkCollision(Chicken.leftBullet)){
                    score+=1;
                }
                if(target.checkCollision(Chicken.rightBullet)){
                    score+=1;
                }
                Cats.thick();
                target.thick();
                Chicken.thick();
                Chicken.checkBounds(WEIGHT);
                cloud.thick();
                nextLevel();
                //System.out.println(score);
                
                repaint();
            }
        };
        
        frameTask=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //Cats.frame++;
                Chicken.frame++;
  
                if(Chicken.frame==5){
                    Chicken.frame=1;
                    Cats.frame++;
                    if(Cats.frame==3)
                        Cats.frame=1;
                }
                  
            }
        };
        frameTimer=new Timer(70,frameTask);
        time=new Timer(1,timerTask);
        this.enemySize=enemySize;
        this.targetSize=targetSize;
        this.enemyDiff=enemyDiff;
        this.targetDiff=targetDiff;
        this.HEIGHT=HEIGHT;
        this.WEIGHT=WEIGHT;
        init();
        restartBtn=new JButton("Restart");
        restartBtn.setBackground(new Color(255, 215, 115));
        this.add(restartBtn);
        restartBtn.setVisible(false);
        this.setBackground(new Color(199, 234, 248));
        nextBtn=new JButton("Next Level");
        nextBtn.setBackground(new Color(255, 215, 115));
        this.add(nextBtn);
        nextBtn.setVisible(nextLevel);
         nextLevel=false;

        //enemy movement
        
        //time.start();
        
    }
    public void init(){
        Chicken = new Player(WEIGHT);
        Cats=new Enemy(enemySize,WEIGHT,HEIGHT,enemyDiff);
        target=new Target(targetSize,WEIGHT,HEIGHT,targetDiff);
        cloud=new Cloud(enemySize,WEIGHT,HEIGHT,enemyDiff+1);
        this.WEIGHT = WEIGHT;
        this.HEIGHT = HEIGHT;
        this.setPreferredSize(new Dimension(WEIGHT, HEIGHT));
        this.setLayout(null);
        this.setFocusable(true);
        score=0;
        
        addKeyBinding();
        
        //time.start();
    }
    
    public void timerRestart(){
        time.start();
        frameTimer.start();

 
    }
    public void timerStop(){
        time.stop();
        frameTimer.stop();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBackground(g);
        int x=WEIGHT/2-120,y=HEIGHT/2+10,size=75;
        if(gameOver){
            drawGameOver(g);

        }
        else if(nextLevel){
            DrawnextLevel(g);
        }

        else{
        cloud.draw(g);
        Chicken.drawBullet(g);
        Chicken.draw(g);
        Cats.draw(g);
        target.draw(g);
        x=20;
        y=50;
        size=50;
        }
        drawScore(g,x,y,size);
        restartBtn.setVisible(gameOver);
        nextBtn.setVisible(nextLevel);
    }
    
    public void nextLevel(){
        if(Cats.getTaskFinish()<=0){
        
        //timer.stop();
        //DrawnextLevel();
        nextLevel=true;
        
        
        
        }
    }
    
        public void DrawnextLevel(Graphics g){
        
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(new Color(222, 245, 255));
        g2d.fillRect(75, 75, 1300, 650);
        g2d.setColor(new Color(146, 195, 211));
        g2d.setFont(new Font("Thoma",Font.BOLD,150));
        /*
        g2d.setColor(new Color(109, 154, 182));
        g2d.drawString("NEXT LEVEL!!", WEIGHT/2-450, HEIGHT/2-150);*/
        drawText(g);
        nextBtn.setFocusPainted(false);
        nextBtn.setForeground(Color.white);
        nextBtn.setBorder(null);
        nextBtn.setText("NEXT LEVEL");
        
        nextBtn.setFont(new Font("Thoma",Font.BOLD,50));
        nextBtn.setBounds(WEIGHT/2-200, HEIGHT/2+100, 500, 150);
        
        //nextBtn.setBounds(100,100,100,100);

        this.add(nextBtn);
        
        addNextBtn();
        
    }
    public void drawText(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(new Color(109, 154, 182));
        g2d.drawString("NEXT LEVEL!!", WEIGHT/2-450, HEIGHT/2-150);
    }
    
    public void addNextBtn(){
        nextBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                CardLayout card;
                card = (CardLayout) getParent().getLayout();
                Level2 level= (Level2) getParent().getComponents()[2];
                level.timerRestart();
                level.init();
                card.show(getParent(), "level2");
                timerStop();
                nextLevel=false;
            }
            public void mouseEntered(MouseEvent evt){
                nextBtn.setBackground(new Color(255, 230, 128));
            }
            public void mouseExited(MouseEvent evt){
                nextBtn.setBackground(new Color(255, 215, 115)); 
            }
        });
    }
    
    public void drawScore(Graphics g,int x,int y,int size){
        Graphics2D g2d = (Graphics2D)g;
        RenderingHints hints = new RenderingHints(
        RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2d.setRenderingHints(hints);
        
        g2d.setFont(new Font("Thoma",Font.BOLD,size));
        g2d.setColor(new Color(146, 195, 211));
        g2d.drawString("SCORE: "+ String.valueOf(score),x,y);
    }
    
    public void drawBackground(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_PURE);
        Image back=new ImageIcon("res/sun.png").getImage();

        g2d.drawImage(back, 900,0,null);

    }
    public void  drawGameOver(Graphics g){
        /*
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.red);
        g2d.setFont(new Font("Cooper Std Black", Font.PLAIN,100));
        RenderingHints hints = new RenderingHints(
        RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2d.setRenderingHints(hints);
        g.drawString("GAME", 100, 100);
        g2d.drawString("GAME OVER", WEIGHT/2-300, HEIGHT/2);
        addResartBtn();
        restartBtn.setVisible(false);*/
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(new Color(222, 245, 255));
        g2d.fillRect(75, 75, 1300, 650);
        g2d.setColor(new Color(146, 195, 211));
        g2d.setFont(new Font("Thoma",Font.BOLD,150));
        RenderingHints hints = new RenderingHints(
        RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2d.setRenderingHints(hints);
        g2d.setColor(new Color(109, 154, 182));
        g2d.drawString("GAME OVER", WEIGHT/2-400, HEIGHT/2-150);
        addResartBtn();
        restartBtn.setFocusPainted(false);
        restartBtn.setForeground(Color.white);
        restartBtn.setBorder(null);
        restartBtn.setText("RESTART");
        //
        restartBtn.setFont(new Font("Thoma",Font.BOLD,50));
        restartBtn.setBounds(WEIGHT/2-200, HEIGHT/2+100, 500, 150);
        restartBtn.setVisible(false);
        
    }
    
    public void addResartBtn(){
        
        restartBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                gameOver=false;
                init();
                timerRestart();
            }
            public void mouseEntered(MouseEvent evt){
                restartBtn.setBackground(new Color(255, 230, 128));

            }
            public void mouseExited(MouseEvent evt){
               restartBtn.setBackground(new Color(255, 215, 115)); 

            }
        });
        
    }
   
    private void addKeyBinding(){
        addMoveKeyBinding("leftMove","A",-7);
        addMoveKeyBinding("leftMove","LEFT",-7);
        addMoveKeyBinding("rightMove","D",7);
        addMoveKeyBinding("rightMove","RIGHT",7);
        addMoveKeyBinding("released","released D",0);
        addMoveKeyBinding("released","released A",0);
        addMoveKeyBinding("released","released LEFT",0);
        addMoveKeyBinding("released","released RIGHT",0);
        addShootBinding("leftShoot","Z");
        addShootBinding("rightShoot","X");
    }
 
    private void addMoveKeyBinding(String s,String key,int velocity){
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW ).put(KeyStroke.getKeyStroke(key), s);
        this.getActionMap().put(s, new  AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(s.equals("leftMove")){
                    Chicken.setSide('L');
                }
                else if(s.equals("rightMove")){
                    Chicken.setSide('R');
                }
                Chicken.setVel(velocity);               
            }   
        });
    }
    private void addShootBinding(String s,String key){
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW ).put(KeyStroke.getKeyStroke(key), s);
        this.getActionMap().put(s, new  AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if("leftShoot".equals(s)){
                    Chicken.shoot('L');
                }
                else if("rightShoot".equals(s)){
                   Chicken.shoot('R'); 
                }
                               
            }   
        });
        
    }
    /*
    @Override
    public void actionPerformed(ActionEvent e) {
       //To change body of generated methods, choose Tools | Templates.
    }
    */
}
