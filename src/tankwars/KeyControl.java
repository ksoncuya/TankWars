/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankwars;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Kevin Soncuya
 */
public class KeyControl extends KeyAdapter {

//        public int x, y, w, h;
//        int speed;
    TankWars tw;
    GameEvents ge;

//        KeyControl(int x, int y, int speed) {
    KeyControl(TankWars tw, GameEvents ge) {
//            this.x = x;
//            this.y = y;
//            this.speed = speed;
        this.tw = tw;
        this.ge = ge;
    }

    public void keyPressed(KeyEvent e) {
        tw.ge.setValue(e);
    }
    
    public void keyReleased(KeyEvent key) {
        tw.ge.setValue(key);
    }
    

}
