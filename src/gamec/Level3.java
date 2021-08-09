/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamec;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author edanu
 */
public class Level3 extends Level {

    //private JButton nextBtn;
    //protected boolean nextLevel = false;

    public Level3(int WEIGHT, int HEIGHT, int enemyDiff, int targetDiff, int enemySize, int targetSize) {
        super(WEIGHT, HEIGHT, enemyDiff, targetDiff, enemySize, targetSize);
        nextBtn = new JButton("MENU");
        nextBtn.setBackground(new Color(255, 215, 115));
    }
/*
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameOver) {
            drawGameOver(g);

        } else if (!nextLevel) {
            Chicken.draw(g);
            Chicken.drawBullet(g);
            Cats.draw(g);
            target.draw(g);
        }
        restartBtn.setVisible(gameOver);
        nextBtn.setVisible(nextLevel);
    }*/

    public void DrawnextLevel() {
/*
        nextBtn.setBounds(100, 100, 100, 100);
        this.add(nextBtn);
        nextBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                CardLayout card;
                card = (CardLayout) getParent().getLayout();
                card.show(getParent(), "menu");
                timerStop();
                nextLevel = false;
            }
        });*/
    }
/*
    public void nextLevel() {
        if (Cats.getTaskFinish() <= 0) {

            //timer.stop();
            DrawnextLevel();
            nextLevel = true;

        }
    }*/
    
    public void addNextBtn(){
      
        nextBtn.setText("MENU");
        nextBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                CardLayout card;
                card = (CardLayout) getParent().getLayout();
                card.show(getParent(), "menu");
                timerStop();
                nextLevel = false;
            }
            public void mouseEntered(MouseEvent evt){
                nextBtn.setBackground(new Color(255, 230, 128));
            }
            public void mouseExited(MouseEvent evt){
                nextBtn.setBackground(new Color(255, 215, 115)); 
            }
        });
    }

    public void drawText(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(new Color(109, 154, 182));
        g2d.drawString("GAME FINISHED", WEIGHT/2-550, HEIGHT/2-150);
    }
}
