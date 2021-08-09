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
import javax.swing.ImageIcon;

/**
 *
 * @author edanu
 */
public class Bullet {
    private int x,y,velocity;
    public final int WEIGHT,HEIGHT; 
    private Image img;
    
    public Bullet(int x, int y, int WEIGHT, int HEIGHT,int velocity) {
        this.x = x;
        this.y = y;
        img=new ImageIcon("res/bullet/egg.png").getImage();
        this.WEIGHT = img.getWidth(null);
        this.HEIGHT = img.getHeight(null);
        this.velocity=velocity;
    }

    

    public int getX() {
        return x;
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
    
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_PURE);
        
        g2d.drawImage(img, x,y+y/2,null);
    }
    
    
    public void thick(){
        x+=velocity;
    }
}
