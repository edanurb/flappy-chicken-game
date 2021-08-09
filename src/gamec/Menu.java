/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamec;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;


/**
 *
 * @author edanu
 */
public class Menu extends JPanel {
    JButton startBtn,howtoBtn;
    boolean howtoPlay=false;
    public void init(){
        
        startBtn=new JButton("START");
        howtoBtn=new JButton("HOW TO PLAY");
        editButton();
        this.add(howtoBtn);
        this.add(startBtn);
        this.setPreferredSize(new Dimension(1440, 810));
        this.setLayout(null);
        this.setFocusable(true);
        
        startBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                nextScreen();
            }
            public void mouseEntered(MouseEvent evt){
                startBtn.setBackground(new Color(142, 153, 162));
                startBtn.setForeground(new Color(244, 244, 244));
            }
            public void mouseExited(MouseEvent evt){
                startBtn.setForeground(new Color(157, 179, 193));
                startBtn.setBackground(new Color(241, 241, 241));
            }
        });
        
        howtoBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                howtoPlay=true;
                JButton closeBtn =new JButton();
                closeBtn.setBorder(null);
                closeBtn.setBounds(150,50,35,35);
                closeBtn.setIcon(new ImageIcon("res/menu/cancel2.png"));
                closeBtn.setBackground(new Color(241,241,241));
                closeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                closeBtn.addMouseListener(new MouseAdapter(){
                   public void mouseClicked(MouseEvent evt) {
                       howtoPlay=false;
                       closeBtn.setVisible(false);
                       repaint();
                    } 
                });
                add(closeBtn);
                repaint();
                
            }
            public void mouseEntered(MouseEvent evt){
                
                howtoBtn.setBackground(new Color(142, 153, 162));
                howtoBtn.setForeground(new Color(244, 244, 244));
            }
            public void mouseExited(MouseEvent evt){
                howtoBtn.setForeground(new Color(157, 179, 193));
                howtoBtn.setBackground(new Color(241, 241, 241));
            }
        });
    }
    public void nextScreen(){
        System.out.println();
        CardLayout card=(CardLayout) this.getParent().getLayout();
        Level1 level= (Level1) this.getParent().getComponents()[1];
        level.timerRestart();
        level.init();
        card.show(this.getParent(), "level1");
        
        //this.getParent().getComponents()
        
    }
    @Override
    public void paintComponent(Graphics g) {
     Graphics2D g2d = (Graphics2D)g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_PURE);
    Image img=new ImageIcon("res/menu/menu.png").getImage();
    g2d.drawImage(img, 0,0,null);

        if(howtoPlay){
        Image img2=new ImageIcon("res/menu/howtoplay.png").getImage();
        g2d.drawImage(img2, 150,50,null);
        }
    }
    
    public void editButton(){
    startBtn.setFont(new Font("Cooper Std Black",Font.BOLD,50));
    startBtn.setForeground(new Color(157, 179, 193));
    startBtn.setBackground(new Color(241, 241, 241));
    startBtn.setBorder(null);
    startBtn.setBounds(500,400,500,100);
    startBtn.setFocusPainted(false);
    howtoBtn.setFont(new Font("Cooper Std Black",Font.BOLD,50));
    howtoBtn.setForeground(new Color(157, 179, 193));
    howtoBtn.setBackground(new Color(241, 241, 241));
    howtoBtn.setBorder(null);
    howtoBtn.setBounds(500,550,500,100);
    howtoBtn.setFocusPainted(false);
    }
}
