/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamec;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author edanu
 */
public class Level2 extends Level{
    //private boolean gameFinished=false;

    public Level2( int WEIGHT, int HEIGHT, int enemyDiff, int targetDiff, int enemySize, int targetSize) {
        super(WEIGHT, HEIGHT, enemyDiff, targetDiff, enemySize, targetSize);
        /*finishBtn=new JButton("Restart");
        finishBtn.setBounds(100,100,100,100);
        this.add(finishBtn);
        finishBtn.setVisible(false);*/
        
    }
    
    public void addNextBtn(){
        nextBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                CardLayout card;
                card = (CardLayout) getParent().getLayout();
                Level3 level= (Level3) getParent().getComponents()[3];
                level.timerRestart();
                level.init();
                card.show(getParent(), "level3");
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
    /*
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(gameOver){
            drawGameOver(g);
            
        }
        else if(!nextLevel){
        Chicken.draw(g);
        Chicken.drawBullet(g);
        Cats.draw(g);
        target.draw(g);
        }
        restartBtn.setVisible(gameOver);
        nextBtn.setVisible(nextLevel);
    }*/
    
  
}
