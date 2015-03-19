/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankwars;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Kevin Soncuya
 */

/* 
 * Health Bar 
 * Reusable
 */

public class HealthBar extends GameObject {

    TankWars tw;

    HealthBar(Image img, int x, int y, TankWars tw) {
        super(img, x, y, 0);
        this.tw = tw;
    }

    public void draw(Graphics g, ImageObserver obs) {
        g.drawImage(img, this.x, this.y, obs);
    }

    public void update() {
        //replace health bar if get hit
        //hp is health points 
        //hb is health bar
        //life is the the amount of lives of the player
       
//        if (tw.hp1 < 8) {
//            tw.life1 = new HealthBar(tw.life, 590, 370, tw);
//        }
        
        if(tw.hp1 == 0) {
            tw.hb1 = new HealthBar(tw.healthFull, tw.tank1.getX(), tw.tank1.getY()+64, tw);
        }

        if (tw.hp1 >= 50) {
            tw.hb1 = new HealthBar(tw.health1, tw.tank1.getX(), tw.tank1.getY()+64, tw);
        }
        if (tw.hp1 >= 100) {
            tw.hb1 = new HealthBar(tw.health2, tw.tank1.getX(), tw.tank1.getY()+64, tw);
        }
        if (tw.hp1 >= 150) {
            tw.hb1 = new HealthBar(tw.health3, tw.tank1.getX(), tw.tank1.getY()+64, tw);
        }

//        if (tw.hp1 == 8) {
//            tw.hb1 = new HealthBar(tw.healthFull, tw.tank1.getX(), tw.tank1.getY()+64, tw);
//            tw.pickedUp1 = false;
//        }
//        if (tw.hp1 == 10) {
//            tw.hb1 = new HealthBar(tw.health1, tw.tank1.getX(), tw.tank1.getY()+64, tw);
//        }
//        if (tw.hp1 == 12) {
//            tw.hb1 = new HealthBar(tw.health2, tw.tank1.getX(), tw.tank1.getY()+64, tw);
//        }
//        if (tw.hp1 == 14) {
//            tw.hb1 = new HealthBar(tw.health3, tw.tank1.getX(), tw.tank1.getY()+64, tw);
//        }
        if (tw.hp1 >= 200) {
            tw.pickedUp1 = false;
            tw.exp = new Explosion(tw.explosion1_1, tw.tank1.x, tw.tank1.y);
            tw.exp.draw(tw.g2, tw.observer);
            tw.tank1.x = 900; 
            tw.tank1.y = 250;
            tw.hp1 = 0;
            tw.score2++;
        }

        /**
         * ***********************************************
         */
//        if (tw.hp2 < 8) {
//            tw.life2 = new HealthBar(tw.life, 110, 370, tw);
//        }
        
        if(tw.hp2 == 0) {
            tw.hb2 = new HealthBar(tw.healthFull, tw.tank2.getX(), tw.tank2.getY()+64, tw);
        }
        if (tw.hp2 >= 50) {
            tw.hb2 = new HealthBar(tw.health1, tw.tank2.getX(), tw.tank2.getY()+64, tw);
        }
        if (tw.hp2 >= 100) {
            tw.hb2 = new HealthBar(tw.health2, tw.tank2.getX(), tw.tank2.getY()+64, tw);
        }
        if (tw.hp2 >= 150) {
            tw.hb2 = new HealthBar(tw.health3, tw.tank2.getX(), tw.tank2.getY()+64, tw);
        }
//        if (tw.hp2 == 8) {
//            tw.hb2 = new HealthBar(tw.healthFull, tw.tank2.getX(), tw.tank2.getY()+64, tw);
//            tw.pickedUp2 = false;
//        }
//        if (tw.hp2 == 10) {
//            tw.hb2 = new HealthBar(tw.health1, tw.tank2.getX(), tw.tank2.getY()+64, tw);
//        }
//        if (tw.hp2 == 12) {
//            tw.hb2 = new HealthBar(tw.health2, tw.tank2.getX(), tw.tank2.getY()+64, tw);
//        }
//        if (tw.hp2 == 14) {
//            tw.hb2 = new HealthBar(tw.health3, tw.tank2.getX(), tw.tank2.getY()+64, tw);
//        }
        if (tw.hp2 >= 200) {
            tw.pickedUp2 = false;
            tw.exp = new Explosion(tw.explosion1_1, tw.tank2.x, tw.tank2.y);
            tw.exp.draw(tw.g2, tw.observer);
            tw.tank2.x = 75;
            tw.tank2.y = 250;
            tw.hp2 = 0;
            tw.score1++;
        }

//        if (tw.hp1 > 7 && tw.hp2 > 7) {
//            tw.gameOver = true;
//        }
        
        if (tw.score1 == 3 || tw.score2 == 3) {
            tw.gameOver = true;
        }
        
    }
}
