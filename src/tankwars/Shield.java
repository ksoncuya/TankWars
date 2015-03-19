/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankwars;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

/**
 *
 * @author Kevin Soncuya
 */
public class Shield extends Weapon {

    TankWars tw;
    boolean show = true;
    int counter1 = 0;
    int counter2 = 0;

    Shield(Image img, int x, int y, int speed, TankWars tw) {
        super(img, x, y, speed);
        this.tw = tw;
    }

    public void update() {
        if (tw.shieldOn1) {
            counter1++;
            tw.blueShield = new Shield(tw.Shield2, tw.tank1.getX() - 5, tw.tank1.getY() - 5, 0, tw);
            System.out.println("counter1: " + counter1);
        }
        if (tw.shieldOn2) {
            counter2++;
            tw.redShield = new Shield(tw.Shield1, tw.tank2.getX() - 5, tw.tank2.getY() - 5, 0, tw);
            System.out.println("counter2: " + counter2);
        }

        if (counter1 < 300 && counter1 != 0) {
//            System.out.println("BLUE SHIELD IS ON");
        } else if(counter1 >= 300){
            counter1 = 0;
            tw.shieldOn1 = false;
//            System.out.println("BLUE SHIELD IS OFF");
            show = false;
        }

        if (counter2 < 300 && counter2 != 0) {
//            System.out.println("RED SHIELD IS ON");
        } else if(counter2 >= 300) {
            counter2 = 0;
            tw.shieldOn2 = false;
//            System.out.println("RED SHIELD IS OFF");
            show = false;
        }

    }

    public void draw(Graphics g, ImageObserver obs) {
        if (show) {
            g.drawImage(img, this.x, this.y, obs);
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}
