/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamec;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author edanu
 */
public class Player /*implements ActionListener*/ {
    private int x,y,vel;
    private final int HEIGHT,WEIGHT;
    private  int SCREENWEIGHT=50;
    ArrayList <Bullet> rightBullet,leftBullet;
    private Image img;
    private char side;
    int frame;
    public Player(int SCREENWEIGHT) {
        img=new ImageIcon("res/chickens/L1.png").getImage();
        this.x = 50;
        this.y = 50;
        frame=1;
        side='L';
        this.HEIGHT=img.getHeight(null);
        this.WEIGHT=img.getWidth(null);
        this.SCREENWEIGHT=SCREENWEIGHT;
        rightBullet=new ArrayList<>();
        leftBullet=new ArrayList<Bullet>();
    }
    public int getX() {
        return x+vel;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }



    public int getWEIGHT() {
        return WEIGHT;
    }
    
    public ArrayList <Bullet> getRightBullet(){
        return rightBullet;
    }

    public ArrayList<Bullet> getLeftBullet() {
        return leftBullet;
    }
    
    public void moveX(int x){
        this.x+= x;
    }
    public void moveY(int y){
        setY(y);
    }

    public void setVel(int vel) {
        this.vel = vel;
    }

    public void setSide(char side) {
        this.side = side;
    }
    
    public void thick(){
        x+=vel;
        for(int i=0;i < rightBullet.size();i++ ){
            Bullet bullet=rightBullet.get(i);
            bullet.thick();
            if(bullet.getX()+ bullet.WEIGHT > SCREENWEIGHT){
                rightBullet.remove(i);
                i--;
            }
        }
        for(int i=0;i < leftBullet.size();i++ ){           
            Bullet bullet=leftBullet.get(i);
            bullet.thick();           
            if(bullet.getX() <0){              
                leftBullet.remove(i);
                i--;
            }
        }
    }
    
    public void checkBounds(int WEIGHT ){
        if(x<=0){
            x=0;
            vel=0;
        }
        else if(x+this.WEIGHT >= WEIGHT){
            x= WEIGHT-this.WEIGHT;
            vel=0;
        }
    }
    
    public void shoot(char side){
        if(side=='L'){
            leftBullet.add(new Bullet(x,y,30,30,-8));
        }
        if(side=='R'){
            rightBullet.add(new Bullet(x,y,30,30,8));
        }
    }
    public void draw(Graphics g){

        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_PURE);
 
        img=new ImageIcon("res/chickens/"+side+String.valueOf(frame)+".png").getImage();
        
      
        g2d.drawImage(img, x,y,null);
       
        
        
    }
    public void drawBullet(Graphics g){
        for(int i=0;i < rightBullet.size();i++ ){
            rightBullet.get(i).draw(g);
        }
        for(int i=0;i < leftBullet.size();i++ ){
            leftBullet.get(i).draw(g);
        }
    }
    
    public boolean checkCollision(Enemy enemy){
        Rectangle playerRect=new Rectangle(x,y,WEIGHT,HEIGHT);
        for(int i=0;i<enemy.getSize();i++){
            Rectangle enemyRect=new Rectangle(enemy.getX(i),enemy.getY(i),enemy.WEIGHT,enemy.HEIGHT);
            if(enemyRect.intersects(playerRect)){
                return true;
            }
        }
        return false;
    }
/*
    @Override
    public void actionPerformed(ActionEvent e) {
        frame++;
        if(frame==5){
            frame=1;
        }
    }*/
}
