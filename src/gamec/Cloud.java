/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamec;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author edanu
 */
public class Cloud extends Enemy {
    public Cloud(int size, int SWEIGHT, int SHEIGHT, int difficulty) {
        
        super(size, SWEIGHT, SHEIGHT, difficulty);
        
    }
    
    @Override
    public void randomCordinate( int SWEIGHT, int SHEIGHT){
        for(int i=0;i<size;i++){
            y.add(i, rand.nextInt(SHEIGHT*difficulty-SHEIGHT));            
            x.add(i, rand.nextInt(SWEIGHT)-100);
        }
    }
    
    @Override
    public void thick(){
        for (int i = 0; i < size; i++) {
                y.set(i, y.get(i)-2);
        }
      
    }
    
    @Override
    public void draw(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        RenderingHints hints = new RenderingHints(
        RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2D.setRenderingHints(hints);
        for(int i=0; i< size;i++){
            
            Image img=new ImageIcon("res/cloud/c.png").getImage();
            g2D.drawImage(img, x.get(i),y.get(i),null);
            //g.fillRect(x.get(i), y.get(i), WEIGHT, HEIGHT);
        }
    }
    
    
}