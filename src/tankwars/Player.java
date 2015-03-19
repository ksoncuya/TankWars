/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tankwars;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Kevin Soncuya
 */

public abstract class Player extends GameObject {
    
    //ArrayList<Bullet> bullets = new ArrayList();
    
    public Player(Image img, int x, int y, int speed) {
        super(img, x, y, speed);
    }
   
    
}
