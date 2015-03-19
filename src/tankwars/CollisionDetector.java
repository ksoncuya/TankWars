/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankwars;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Kevin Soncuya
 */
public class CollisionDetector {

    //empty constructor
    CollisionDetector() {
    }

    public static boolean bulletOnTank1(ArrayList<Bullet> bullets2, MyTank tank1) {
        for (int i = 0; i < bullets2.size(); i++) {
            Bullet t2Bullet = bullets2.get(i);
            if (t2Bullet.collision(tank1.x, tank1.y, tank1.width, tank1.height)) {
//                bullets2.remove(t2Bullet);
                return true;
            }
        }
        return false;
    }

    public static boolean bulletOnTank2(ArrayList<Bullet> bullets1, MyTank tank2) {
        for (int i = 0; i < bullets1.size(); i++) {
            Bullet t1Bullet = bullets1.get(i);
            if (t1Bullet.collision(tank2.x, tank2.y, tank2.width, tank2.height)) {
//                bullets1.remove(t1Bullet);
                return true;
            }
        }
        return false;
    }

    public static boolean rocketOnTank1(ArrayList<Rocket> rockets2, MyTank tank1) {
        for (int i = 0; i < rockets2.size(); i++) {
            Rocket t2Rocket = rockets2.get(i);
            if (t2Rocket.collision(tank1.x, tank1.y, tank1.width, tank1.height)) {
                rockets2.remove(t2Rocket);
                return true;
            }
        }
        return false;
    }

    public static boolean rocketOnTank2(ArrayList<Rocket> rockets1, MyTank tank2) {
        for (int i = 0; i < rockets1.size(); i++) {
            Rocket t1Rocket = rockets1.get(i);
            if (t1Rocket.collision(tank2.x, tank2.y, tank2.width, tank2.height)) {
                rockets1.remove(t1Rocket);
                return true;
            }
        }
        return false;
    }

    public static boolean bullets1OnWall(ArrayList<Bullet> bullets1, ArrayList<Wall> walls, TankWars tw) {
        for (int i = 0; i < bullets1.size(); i++) {
            Bullet t1Bullet = bullets1.get(i);
            for (int j = 0; j < walls.size(); j++) {
                if (t1Bullet.collision(tw.w1.x, tw.w1.y, tw.w1.width, tw.w1.height)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean bullets2OnWall(ArrayList<Bullet> bullets2, ArrayList<Wall> walls, TankWars tw) {
        for (int i = 0; i < bullets2.size(); i++) {
            Bullet t2Bullet = bullets2.get(i);
            for (int j = 0; j < walls.size(); j++) {
                if (t2Bullet.collision(tw.w1.x, tw.w1.y, tw.w1.width, tw.w1.height)) {
                    return true;
                }
            }
        }
        return false;
    }
    
        public static boolean rockets1OnWall(ArrayList<Rocket> rockets1, ArrayList<Wall> walls, TankWars tw) {
        for (int i = 0; i < rockets1.size(); i++) {
            Rocket t1Rocket = rockets1.get(i);
            for (int j = 0; j < walls.size(); j++) {
                if (t1Rocket.collision(tw.w1.x, tw.w1.y, tw.w1.width, tw.w1.height)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean rockets2OnWall(ArrayList<Rocket> rockets2, ArrayList<Wall> walls, TankWars tw) {
        for (int i = 0; i < rockets2.size(); i++) {
            Rocket t2Rocket = rockets2.get(i);
            for (int j = 0; j < walls.size(); j++) {
                if (t2Rocket.collision(tw.w1.x, tw.w1.y, tw.w1.width, tw.w1.height)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean pickedUpRockets1(PowerUp rocketPowUp, MyTank tank1, TankWars tw) {
        if (tw.tank1.collision(tw.rocketPowUp.x, tw.rocketPowUp.y, tw.rocketPowUp.sizeX, tw.rocketPowUp.sizeY)) {
            return true;
        }
        return false;
    }

    public static boolean pickedUpRockets2(PowerUp rocketPowUp, MyTank tank2, TankWars tw) {
        if (tw.tank2.collision(tw.rocketPowUp.x, tw.rocketPowUp.y, tw.rocketPowUp.sizeX, tw.rocketPowUp.sizeY)) {
            return true;
        }
        return false;
    }

    public static boolean pickedUpShield1(PowerUp shield, MyTank tank1, TankWars tw) {
        if (tw.tank1.collision(tw.shieldPowUp.x, tw.shieldPowUp.y, tw.shieldPowUp.sizeX, tw.shieldPowUp.sizeY)) {
            return true;
        }
        return false;
    }

    public static boolean pickedUpShield2(PowerUp shield, MyTank tank2, TankWars tw) {
        if (tw.tank2.collision(tw.shieldPowUp.x, tw.shieldPowUp.y, tw.shieldPowUp.sizeX, tw.shieldPowUp.sizeY)) {
            return true;
        }
        return false;
    }

    public static boolean tank1OnTank2(MyTank tank1, MyTank tank2) {
        if (tank1.collision(tank2.x, tank2.y, tank2.width, tank2.height)) {
            return true;
        }
        return false;
    }

    public static boolean tank2OnTank1(MyTank tank2, MyTank tank1) {
        if (tank2.collision(tank1.x, tank1.y, tank1.width, tank1.height)) {
            return true;
        }
        return false;
    }

    public static boolean tank1AndWeakWalls(MyTank tank1, ArrayList<Wall> weakWalls, TankWars tw) {
        for (int i = 0; i < weakWalls.size(); i++) {
            Wall wWall1 = weakWalls.get(i);
            if ((tw.tank1.collision(wWall1.x, wWall1.y, wWall1.width, wWall1.height))) {
                return true;
            }
        }
        return false;
    }

    public static boolean tank2AndWeakWalls(MyTank tank2, ArrayList<Wall> weakWalls, TankWars tw) {
        for (int i = 0; i < weakWalls.size(); i++) {
            Wall wWall2 = weakWalls.get(i);
            if ((tw.tank2.collision(wWall2.x, wWall2.y, wWall2.width, wWall2.height))) {
                return true;
            }
        }
        return false;
    }

    public static boolean tank1AndStrongWalls(MyTank tank1, ArrayList<Wall> strongWalls, TankWars tw) {
        for (int i = 0; i < strongWalls.size(); i++) {
            Wall sWall1 = strongWalls.get(i);
            if ((tw.tank1.collision(sWall1.x, sWall1.y, sWall1.width, sWall1.height))) {
                return true;
            }
        }
        return false;
    }

    public static boolean tank2AndStrongWalls(MyTank tank2, ArrayList<Wall> strongWalls, TankWars tw) {
        for (int i = 0; i < strongWalls.size(); i++) {
            Wall sWall2 = strongWalls.get(i);
            if ((tw.tank2.collision(sWall2.x, sWall2.y, sWall2.width, sWall2.height))) {
                return true;
            }
        }
        return false;
    }

}
