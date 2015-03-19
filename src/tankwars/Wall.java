/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankwars;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;
import java.awt.Graphics;

/**
 *
 * @author Kevin Soncuya
 */

/* 
 * Wall 
 * 
 */
public class Wall extends GameObject {

    TankWars tw;
    int width, height;
    int oldY;
    int timer = 0;

    Wall(Image img, int x, int y, int speed, TankWars tw) {

        super(img, x, y, speed);

        this.speed = speed;
        this.tw = tw;
        this.oldY = y;
        width = img.getWidth(null);
        height = img.getHeight(null);
    }

    public void update() {
        timer++;

        if (timer >= 300) {

        }

        for (int i = 0; i < tw.bullets1.size(); i++) {
            if (CollisionDetector.bullets1OnWall(tw.bullets1, tw.weakWalls, tw)) {
                tw.exp = new Explosion(tw.explosion1_1, tw.w1.x, tw.w1.y);
                tw.exp.draw(tw.g2, tw.observer);
                tw.bullets1.remove(tw.b1);
                tw.weakWalls.remove(tw.w1);
                tw.snd_explosion1.play();
            }
        }

        for (int i = 0; i < tw.bullets2.size(); i++) {
            if (CollisionDetector.bullets2OnWall(tw.bullets2, tw.weakWalls, tw)) {
                tw.exp = new Explosion(tw.explosion1_1, tw.w1.x, tw.w1.y);
                tw.exp.draw(tw.g2, tw.observer);
                tw.bullets2.remove(tw.b2);
                tw.weakWalls.remove(tw.w1);
                tw.snd_explosion1.play();
            }
        }

        for (int i = 0; i < tw.rockets1.size(); i++) {
            if (CollisionDetector.rockets1OnWall(tw.rockets1, tw.weakWalls, tw)) {
                tw.exp = new Explosion(tw.explosion1_1, tw.w1.x, tw.w1.y);
                tw.exp.draw(tw.g2, tw.observer);
                tw.rockets1.remove(tw.r1);
                tw.weakWalls.remove(tw.w1);
                tw.snd_explosion1.play();
            }
        }

        for (int i = 0; i < tw.rockets2.size(); i++) {
            if (CollisionDetector.rockets2OnWall(tw.rockets2, tw.weakWalls, tw)) {
                tw.exp = new Explosion(tw.explosion1_1, tw.w1.x, tw.w1.y);
                tw.exp.draw(tw.g2, tw.observer);
                tw.rockets2.remove(tw.r2);
                tw.weakWalls.remove(tw.w1);
                tw.snd_explosion1.play();
            }
        }

    }

    public void draw(Graphics g, ImageObserver obs) {

//        if(timer <= 0) {
//            this.y = oldY;
        g.drawImage(img, x, y, obs);
//        } 

    }
}
