/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankwars;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Observer;

public class MyTank extends Player {

    int sizeX, sizeY, width, height;
    int boom;
    Rectangle bbox;
    TankWars tw;
    float angle;
    boolean A, D, W, S, left, right, up, down, shoot1, shoot2;

    MyTank(Image img, int x, int y, int speed, TankWars tw, float angle) {

        super(img, x, y, speed);

        width = img.getWidth(null);
        width = width / 60;
        height = img.getHeight(null);
        sizeX = img.getWidth(null);
        sizeY = img.getHeight(null);
        this.angle = angle;
        System.out.println("plane w: " + width + "," + "plane h: " + height);
        boom = 0;
        this.tw = tw;

    }

    public void update(int w, int h) {
        
        for (int i = 0; i < tw.bullets1.size(); i++) {
            if (CollisionDetector.bulletOnTank2(tw.bullets1, tw.tank2)) {
//                System.out.println("tank2 got hit");
                tw.bullets1.remove(tw.b1);
                tw.snd_explosion1.play();
                tw.hp2++;
//                System.out.println("health points 2 = " + tw.hp2);
            }
        }
        for (int i = 0; i < tw.bullets2.size(); i++) {
            if (CollisionDetector.bulletOnTank1(tw.bullets2, tw.tank1)) {
//                System.out.println("tank1 got hit");
                tw.bullets2.remove(tw.b2);
                tw.snd_explosion1.play();
                tw.hp1++;
//                System.out.println("health points 1 = " + tw.hp1);
            }
        }

        for (int i = 0; i < tw.rockets1.size(); i++) {
            if (CollisionDetector.rocketOnTank2(tw.rockets1, tw.tank2)) {
//                System.out.println("tank2 got hit by a rocket");
                tw.rockets1.remove(tw.r1);
                tw.snd_explosion1.play();
                tw.hp2 += 5;
//                System.out.println("health points 2 = " + tw.hp2);
            }
        }
        for (int i = 0; i < tw.rockets2.size(); i++) {
            if (CollisionDetector.rocketOnTank1(tw.rockets2, tw.tank1)) {
//                System.out.println("tank1 got hit by a rocket");
                tw.rockets2.remove(tw.r2);
                tw.snd_explosion1.play();
                tw.hp1 += 5;
//                System.out.println("health points 1 = " + tw.hp1);
            }
        }
        /* tank bounds and weak walls */
        for (int i = 0; i < tw.weakWalls.size(); i++) {
            if (CollisionDetector.tank1AndWeakWalls(tw.tank1, tw.weakWalls, tw)) {
//                System.out.println("tank1 bounces off a wall");
                tw.tank1.x -= speed * Math.cos(Math.toRadians(tw.tank1.angle * 6));
                tw.tank1.y += speed * Math.sin(Math.toRadians(tw.tank1.angle * 6));
            }
        }

        for (int i = 0; i < tw.weakWalls.size(); i++) {
            if (CollisionDetector.tank2AndWeakWalls(tw.tank2, tw.weakWalls, tw)) {
//                System.out.println("tank2 bounces off a wall");
                tw.tank2.x -= speed * Math.cos(Math.toRadians(tw.tank2.angle * 6));
                tw.tank2.y += speed * Math.sin(Math.toRadians(tw.tank2.angle * 6));
            }
        }

        /* tank bounds and strong walls*/
        for (int i = 0; i < tw.strongWalls.size(); i++) {
            if (CollisionDetector.tank1AndStrongWalls(tw.tank2, tw.strongWalls, tw)) {
//                System.out.println("tank1 bounces off a wall");
                tw.tank1.x -= speed * Math.cos(Math.toRadians(tw.tank1.angle * 6));
                tw.tank1.y += speed * Math.sin(Math.toRadians(tw.tank1.angle * 6));
            }
        }

        for (int i = 0; i < tw.strongWalls.size(); i++) {
            if (CollisionDetector.tank2AndStrongWalls(tw.tank2, tw.strongWalls, tw)) {
//                System.out.println("tank2 bounces off a wall");
                tw.tank2.x -= speed * Math.cos(Math.toRadians(tw.tank2.angle * 6));
                tw.tank2.y += speed * Math.sin(Math.toRadians(tw.tank2.angle * 6));
            }
        }

        /* tank1 on tank2 collision 
         * allow tanks to push each other
         */
        if (CollisionDetector.tank1OnTank2(tw.tank1, tw.tank2)) {
            if (tw.tank2.x < tw.tank1.x) {  //if tank 2 is on the left, allow it to push tank 1 to the right
                tw.tank1.x += 5;
            } else if (tw.tank2.y < tw.tank1.y) {   //if tank 2 is below tank 1, allow it to push tank 1 up
                tw.tank1.y += 5;
            } else if (tw.tank2.y > tw.tank1.y) { //push down
                tw.tank1.y -= 5;
            } else if (tw.tank2.x > tw.tank1.x) { //push left
                tw.tank1.x -= 5;
            }
        }

        /* tank2 on tank1 collision 
         * allow tanks to push each other
         */
        if (CollisionDetector.tank2OnTank1(tw.tank2, tw.tank1)) {
            if (tw.tank1.x < tw.tank2.x) {  //if tank 1 is on the left, allow it to push tank 2 to the right
                tw.tank1.x += 5;
            } else if (tw.tank1.y < tw.tank2.y) { //if tank 1 is below tank 2, allow it to push tank 2 up
                tw.tank1.y += 5;
            } else if (tw.tank1.y > tw.tank2.y) { //push down
                tw.tank2.y -= 5;
            } else if (tw.tank1.x > tw.tank2.x) { //push left
                tw.tank2.x -= 5;
            }
        }
        /* tank1 key controls */
        if (left) {
            tw.tank1.angle += .5;
            if (tw.tank1.angle == 60) {
                tw.tank1.angle = 0;
            }
        }
        if (right) {
            if (tw.tank1.angle == 0) {
                tw.tank1.angle = 60;
            }
            tw.tank1.angle -= .5;
        }
        if (up) {
            tw.tank1.x += speed * Math.cos(Math.toRadians(tw.tank1.angle * 6));
            tw.tank1.y -= speed * Math.sin(Math.toRadians(tw.tank1.angle * 6));
        }
        if (down) {
            if (tw.tank1.getY() < (tw.h - 120) && tw.tank1.getX() > 32) {
                tw.tank1.x -= speed * Math.cos(Math.toRadians(tw.tank1.angle * 6));
                tw.tank1.y += speed * Math.sin(Math.toRadians(tw.tank1.angle * 6));
            }
        }

        /* tank2 key controls */
        if (A) {
            tw.tank2.angle += .5;
            if (tw.tank2.angle == 60) {
                tw.tank2.angle = 0;
            }
        }
        if (D) {
            if (tw.tank2.angle == 0) {
                tw.tank2.angle = 60;
            }
            tw.tank2.angle -= .5;
        }
        if (W) {
            tw.tank2.x += speed * Math.cos(Math.toRadians(tw.tank2.angle * 6));
            tw.tank2.y -= speed * Math.sin(Math.toRadians(tw.tank2.angle * 6));
        }
        if (S) {
            if (tw.tank2.getY() < (tw.h - 120) && tw.tank2.getX() > 32) {
                tw.tank2.x -= speed * Math.cos(Math.toRadians(tw.tank2.angle * 6));
                tw.tank2.y += speed * Math.sin(Math.toRadians(tw.tank2.angle * 6));
            }
        }
    }

    public void update(Observable obj, Object arg) {
        GameEvents ge = (GameEvents) arg;
        if (ge.type == 1) {

            KeyEvent e = (KeyEvent) ge.event;
            KeyEvent key = (KeyEvent) ge.event;

            /* key pressed */
            /* for player 1 */
            if (e.getKeyCode() == KeyEvent.VK_LEFT && e.getID() == KeyEvent.KEY_PRESSED) {
                left = true;
            } else if (key.getKeyCode() == KeyEvent.VK_LEFT && key.getID() == KeyEvent.KEY_RELEASED) {
                left = false;
//                System.out.println("left is false");
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT && e.getID() == KeyEvent.KEY_PRESSED) {
                right = true;
            } else if (key.getKeyCode() == KeyEvent.VK_RIGHT && key.getID() == KeyEvent.KEY_RELEASED) {
                right = false;
//               System.out.println("right is false");
            }

            if (e.getKeyCode() == KeyEvent.VK_UP && e.getID() == KeyEvent.KEY_PRESSED) {
                up = true;
            } else if (key.getKeyCode() == KeyEvent.VK_UP && key.getID() == KeyEvent.KEY_RELEASED) {
                up = false;
//                System.out.println("up is false");
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN && e.getID() == KeyEvent.KEY_PRESSED) {
                down = true;
            } else if (key.getKeyCode() == KeyEvent.VK_DOWN && key.getID() == KeyEvent.KEY_RELEASED) {
                down = false;
//                System.out.println("down is false");
            }

            if (e.getKeyCode() == KeyEvent.VK_M && e.getID() == KeyEvent.KEY_PRESSED) {
//                shoot2 = true;
                if (tw.pickedUp1 == true) {
                    tw.rockets1.add(new Rocket(tw.Rocket_strip60, tw.tank1.getX() + 16, tw.tank1.getY() + 8, 12, tw));
                    tw.ammo1--;
//                    System.out.println("ammo = " + tw.ammo2);
                } else {
                    tw.bullets1.add(new Bullet(tw.bullet, tw.tank1.getX() + 16, tw.tank1.getY() + 8, 12, tw));
                }
            } else if (e.getKeyCode() == KeyEvent.VK_M && e.getID() == KeyEvent.KEY_RELEASED) {
//                shoot2 = false;
//                System.out.println("shoot2 is false");
            }

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//            /* for player 2 */
            if (e.getKeyCode() == KeyEvent.VK_A && e.getID() == KeyEvent.KEY_PRESSED) {
                A = true;
            } else if (key.getKeyCode() == KeyEvent.VK_A && key.getID() == KeyEvent.KEY_RELEASED) {
                A = false;
//                System.out.println("A is false");
            }

            if (e.getKeyCode() == KeyEvent.VK_D && e.getID() == KeyEvent.KEY_PRESSED) {
                D = true;
            } else if (key.getKeyCode() == KeyEvent.VK_D && key.getID() == KeyEvent.KEY_RELEASED) {
                D = false;
//               System.out.println("D is false");
            }

            if (e.getKeyCode() == KeyEvent.VK_W && e.getID() == KeyEvent.KEY_PRESSED) {
                W = true;
            } else if (key.getKeyCode() == KeyEvent.VK_W && key.getID() == KeyEvent.KEY_RELEASED) {
                W = false;
//                System.out.println("W is false");
            }

            if (e.getKeyCode() == KeyEvent.VK_S && e.getID() == KeyEvent.KEY_PRESSED) {
                S = true;
            } else if (key.getKeyCode() == KeyEvent.VK_S && key.getID() == KeyEvent.KEY_RELEASED) {
                S = false;
//                System.out.println("S is false");
            }

            if (e.getKeyCode() == KeyEvent.VK_V && e.getID() == KeyEvent.KEY_PRESSED) {
//                shoot2 = true;
                if (tw.pickedUp2 == true) {
                    tw.rockets2.add(new Rocket(tw.Rocket_strip60, tw.tank2.getX() + 16, tw.tank2.getY() + 8, 12, tw));
                    tw.ammo2--;
//                    System.out.println("ammo = " + tw.ammo2);
                } else {
                    tw.bullets2.add(new Bullet(tw.bullet, tw.tank2.getX() + 16, tw.tank2.getY() + 8, 12, tw));
                }
            } else if (e.getKeyCode() == KeyEvent.VK_V && e.getID() == KeyEvent.KEY_RELEASED) {
//                shoot2 = false;
//                System.out.println("shoot2 is false");
            }
            
            
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
                tw.music.stop();
            }

//            keyboardInput();
        }
    }

//    public void keyboardInput() {
//        for (int i = 0; i < tw.keyPressed.size(); i++) {
//            if (tw.keyPressed.get(i).getKeyCode() == KeyEvent.VK_LEFT) {
//                tw.left = true;
//                tw.left = false;
//            }
//            if (tw.keyPressed.get(i).getKeyCode() == KeyEvent.VK_RIGHT) {
//                if (tw.tank1.angle == 0) {
//                    tw.tank1.angle = 60;
//                }
//                tw.tank1.angle -= .5;
//            }
//            if (tw.keyPressed.get(i).getKeyCode() == KeyEvent.VK_M) {
//                tw.bullets1.add(new Bullet(tw.bullet, tw.tank1.getX() + 50, tw.tank1.getY() + 10, 5, tw));
//            }
//        }
//    }
    public boolean collision(int x, int y, int w, int h) {
        bbox = new Rectangle(this.x, this.y, this.width, this.height);
        Rectangle otherBBox = new Rectangle(x, y, w, h);
        if (this.bbox.intersects(otherBBox)) {
            return true;
        }
        return false;
    }

    public void draw(Graphics g, ImageObserver obs) {
        g.drawImage(img, x, y, x + 64, y + 64, 64 * (int) angle, 0, 64 * (int) angle + 64, 64, obs);
    }

}
