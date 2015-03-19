/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankwars;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

/**
 *
 * @author Kevin Soncuya
 */
public class Bullet extends Weapon {

    KeyControl key;
    int sizeX, sizeY, move = 0;
    //Enemy e1, e2, e3;
    TankWars tw;
    boolean show;
    int bulletAngle1;
    int bulletAngle2;

    Bullet(Image img, int x, int y, int speed, TankWars tw) {
        super(img, x, y, speed);

        this.tw = tw;
        this.show = true;
        sizeX = img.getWidth(null);
        sizeY = img.getHeight(null);
        width = img.getWidth(null);
        height = img.getHeight(null);
        bulletAngle1 = (int) tw.tank1.angle;
        bulletAngle2 = (int) tw.tank2.angle;

        //addKeyListener(key);
    }

    public void draw(Graphics g, ImageObserver obs) {
        if (show) {
            g.drawImage(img, this.x, this.y, obs);
        }
    }

    public void update1() {
        tw.b1.x += speed * Math.cos(Math.toRadians(bulletAngle1 * 6));
        tw.b1.y -= speed * Math.sin(Math.toRadians(bulletAngle1 * 6));

    }

    public void update2() {
        tw.b2.x += speed * Math.cos(Math.toRadians(bulletAngle2 * 6));
        tw.b2.y -= speed * Math.sin(Math.toRadians(bulletAngle2 * 6));

    }

    public boolean collision(int x, int y, int w, int h) {
        bbox = new Rectangle(this.x, this.y, this.width, this.height);
        Rectangle otherBBox = new Rectangle(x, y, w, h);
        if (this.bbox.intersects(otherBBox)) {
            return true;
        }
        return false;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}
