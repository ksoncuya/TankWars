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
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Kevin Soncuya
 */

public abstract class GameObject implements Observer {
    Image img;
    int x;
    int y;
    int speed;
    ImageObserver obs;
    
    
    public GameObject(Image img, int x, int y, int speed) {
       
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void draw(Graphics g, ImageObserver obs) {
        g.drawImage(img, this.x, this.y, obs);
    }

    public void update(Observable obj, Object arg) {
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
}
