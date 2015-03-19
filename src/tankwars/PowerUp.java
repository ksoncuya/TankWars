/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankwars;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;

public class PowerUp extends GameObject {

    int sizeX;
    int sizeY;
    Random gen;
    TankWars tw;

    PowerUp(Image img, int x, int y, int speed, TankWars tw) {
        super(img, x, y, speed);

        this.tw = tw;
        sizeX = img.getWidth(null);
        sizeY = img.getHeight(null);
    }

    public void update() {

        if (CollisionDetector.pickedUpRockets1(tw.rocketPowUp, tw.tank1, tw)) {
            tw.pickedUp1 = true;
            tw.ammo1 = 20;
            System.out.println("Power Up!");
            this.reset();
        }

        if (CollisionDetector.pickedUpRockets2(tw.rocketPowUp, tw.tank2, tw)) {
            tw.pickedUp2 = true;
            tw.ammo2 = 20;
            System.out.println("Power Up!");
            this.reset();
        }

        if (CollisionDetector.pickedUpShield1(tw.shieldPowUp, tw.tank1, tw)) {
            tw.shieldOn1 = true;
            System.out.println("Power Up!");
            this.reset();
        }

        if (CollisionDetector.pickedUpShield2(tw.shieldPowUp, tw.tank2, tw)) {
            tw.shieldOn2 = true;
            System.out.println("Power Up!");
            this.reset();
        }

        if (y >= tw.h) {
            y = -100;
            x = Math.abs(gen.nextInt() % (tw.w - 30));
        }
    }

    public void reset() {
        this.x = Math.abs(tw.generator.nextInt() % (1024));
        this.y = Math.abs(tw.generator.nextInt() % (640));
    }

    public void draw(ImageObserver obs) {
        tw.g2.drawImage(img, x, y, obs);
    }

}
