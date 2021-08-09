/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamec;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author edanu
 */
public class Enemy {
    protected int size,difficulty;
    protected ArrayList <Integer> x,y;
    protected  Random rand;
    public  int WEIGHT, HEIGHT;
    private int TaskFinish;
    Image img;
    public int frame;

    
    Enemy(int size,int SWEIGHT, int SHEIGHT,int difficulty){
        this.size=size;
        frame=1;
        img=new ImageIcon("res/cats/"+String.valueOf(frame)+".png").getImage();
        x=new ArrayList <Integer> ();
        y=new ArrayList <Integer> ();
        rand=new Random();
        WEIGHT=img.getWidth(null);
        HEIGHT=img.getHeight(null);
        this.difficulty=difficulty;
        randomCordinate(SWEIGHT,SHEIGHT);
        TaskFinish=SHEIGHT*difficulty + (HEIGHT*2) ;

        
        
    }

    public int getSize() {
        return size;
    }

    public void randomCordinate( int SWEIGHT, int SHEIGHT){
        for(int i=0;i<size;i++){
            y.add(i, rand.nextInt(SHEIGHT*difficulty-SHEIGHT) + SHEIGHT);
            
            x.add(i, rand.nextInt(SWEIGHT-150));
        }
    }
    public void thick(){
        for (int i = 0; i < size; i++) {
            y.set(i, y.get(i)-5);
        }
        TaskFinish-=5;
    }

    public int getTaskFinish() {
        return TaskFinish;
    }
    
    
    public int getX(int i) {
        return x.get(i);
    }

    public void setX(int x, int i) {
        this.x.set(i, x);
    }

    public int getY(int i) {
        return y.get(i);
    }

    public void setY(int y,int i) {
        this.y.set(i,y);
    }
    
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_PURE);
        for(int i=0; i< size;i++){
            
            
            img=new ImageIcon("res/cats/"+String.valueOf(frame)+".png").getImage();
            g2d.drawImage(img, x.get(i),y.get(i),null);
            
        }
    }

  
}
