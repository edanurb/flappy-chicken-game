/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamec;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author edanu
 */
public class Target extends Enemy {
    
    public Target(int size, int SWEIGHT, int SHEIGHT,int difficulty) {
        super(size, SWEIGHT, SHEIGHT,difficulty);
       
    }
    @Override
    public void randomCordinate( int SWEIGHT, int SHEIGHT){
        img=new ImageIcon("res/star.png").getImage();
        WEIGHT=75;
        HEIGHT=75;
        int val;
        for(int i=0;i<size;i++){
            y.add(i, rand.nextInt(SHEIGHT*difficulty-SHEIGHT) + SHEIGHT);         
            if(rand.nextInt(2)==1){
               val=SWEIGHT-WEIGHT+10;
            }
            else{
                val=-20;
            }
            x.add(i,val);
        }
    }
    
    @Override
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_PURE);
        
        for(int i=0; i< size;i++){
            
             g2d.drawImage(img, x.get(i),y.get(i),75,75,null);
            
        }
    }
    public boolean checkCollision(ArrayList <Bullet> bullet){
        for(int i=0;i<x.size();i++){
            Rectangle targetRect=new Rectangle(x.get(i),y.get(i),WEIGHT,HEIGHT);
            for (Bullet blt : bullet) {
                Rectangle bulletRect=new Rectangle(blt.getX(),blt.getY(),blt.WEIGHT,blt.HEIGHT);
                if(bulletRect.intersects(targetRect)){
                    x.remove(i);
                    y.remove(i);
                    size--;
                    System.out.println(x.size());
                    return true;
                }
            }
            
            
        }
        return false;
    }
            
}
