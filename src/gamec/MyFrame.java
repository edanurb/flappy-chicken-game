/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamec;

import java.awt.CardLayout;
import javax.swing.*;


/**
 *
 * @author edanu
 */
public class MyFrame extends JFrame {
    CardLayout card;
    JPanel container;
    Level1 level1;
    Level2 level2;
    Level3 level3;
    Menu menuScreen;
    public void init(){
        card=new CardLayout();
        container= new JPanel();
        level1=new Level1(1440, 810,5,5,20,10);   
        level2 = new Level2(1440, 810,8,8,40,10);
        level3=new Level3(1440,810,9,9,70,15);
        menuScreen=new Menu();
        menuScreen.init();
        container.setLayout(card);
        
        
        container.add(menuScreen,"menu");
        container.add(level1,"level1");
        container.add(level2,"level2");
        container.add(level3,"level3");
        card.show(container, "menu");
        this.add(container);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
	this.pack();
        this.setVisible(true);
    }
}
