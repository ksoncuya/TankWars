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

public class Explosion {

    Image img;
    int x, y;
//    Wingman wm;
//        boolean show;

    Explosion(Image img, int x, int y) {
        this.img = img;
//        this.wm = wm;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g2, ImageObserver obs) {
        g2.drawImage(img, x, y, obs);
    }

}
