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
public class Rocket extends Weapon {

    TankWars tw;
    int sizeX, sizeY = 0;
    int rocketAngle1;
    int rocketAngle2;

    Rocket(Image img, int x, int y, int speed, TankWars tw) {
        super(img, x, y, speed);

        this.tw = tw;
        sizeX = img.getWidth(null);
        sizeY = img.getHeight(null);
        width = img.getWidth(null);
        width = width / 60;
        height = img.getHeight(null);
        rocketAngle1 = (int) tw.tank1.angle;
        rocketAngle2 = (int) tw.tank2.angle;
    }

    public void draw1(Graphics g, ImageObserver obs) {
        g.drawImage(img, x, y, x + 24, y + 24, 24 * rocketAngle1, 0, 24 * rocketAngle1 + 24, 24, obs);
    }

    public void draw2(Graphics g, ImageObserver obs) {
        g.drawImage(img, x, y, x + 24, y + 24, 24 * rocketAngle2, 0, 24 * rocketAngle2 + 24, 24, obs);
    }

    public void update1() {
        tw.r1.x += speed * Math.cos(Math.toRadians(rocketAngle1 * 6));
        tw.r1.y -= speed * Math.sin(Math.toRadians(rocketAngle1 * 6));

    }

    public void update2() {
        tw.r2.x += speed * Math.cos(Math.toRadians(rocketAngle2 * 6));
        tw.r2.y -= speed * Math.sin(Math.toRadians(rocketAngle2 * 6));

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
