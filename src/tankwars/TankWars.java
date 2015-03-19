/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankwars;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Font;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin Soncuya
 */

public class TankWars extends JApplet implements Runnable {

    private Thread thread;
    Image background;
    Image myTank;
    Image bullet, Rocket_strip60;
    Image Pickup_1, Pickup_3;   //power up icons 
    Image healthFull, health1, health2, health3, life;
    Image wall1, wall2;
    Image explosion1_1;
    Image gameover, youWin;
    Image minimap;
    Image Shield1, Shield2; 
    private BufferedImage bimg1;
    private BufferedImage bimg2;
//    private BufferedImage view1;
//    private BufferedImage view2;
    Graphics2D g2;
    int speed = 2, move = 0;
    Random generator = new Random(1234567);
    Wall w1, w2;
    MyTank tank1, tank2;
    GameEvents ge;
    Bullet b1, b2;
    Rocket r1, r2;
    HealthBar hb1, hb2, life1, life2;
    Sound music, snd_explosion1, snd_explosion2;
    Explosion exp;
    PowerUp rocketPowUp, shieldPowUp;
    Shield redShield, blueShield;
    GameMap gm;
    int wallWidth = 0;
    int hp1, hp2 = 0;
    int score1, score2 = 0;
    int ammo1 = 0;
    int ammo2 = 0;
    boolean pickedUp1, pickedUp2 = false;
    boolean shieldOn1, shieldOn2 = false;
    int w = 1024, h = 640; // fixed size window game 

    boolean gameOver;
    // Graphics2D g2 = null;
    ImageObserver observer;

    ArrayList<Bullet> bullets1 = new ArrayList();
    ArrayList<Bullet> bullets2 = new ArrayList();
    ArrayList<Rocket> rockets1 = new ArrayList();
    ArrayList<Rocket> rockets2 = new ArrayList();
    ArrayList<Wall> weakWalls = new ArrayList();
    ArrayList<Wall> strongWalls = new ArrayList();
//    ArrayList<KeyEvent> keyPressed = new ArrayList();
   
    public void init() {

        setFocusable(true);
        setBackground(Color.white);

        try {
            //sea = getSprite("Resources/water.png");
            background = ImageIO.read(new File("Resources/Background.png"));
            wall1 = ImageIO.read(new File("Resources/Wall1.png"));
            wall2 = ImageIO.read(new File("Resources/Wall2.png"));
            myTank = ImageIO.read(new File("Resources/Tank1_strip60.png"));
            bullet = ImageIO.read(new File("Resources/bigBullet.png"));
            Rocket_strip60 = ImageIO.read(new File("Resources/Rocket_strip60.png"));
            Shield1 = ImageIO.read(new File("Resources/Shield1.png"));
            Shield2 = ImageIO.read(new File("Resources/Shield2.png"));
            healthFull = ImageIO.read(new File("Resources/health.png"));
            health1 = ImageIO.read(new File("Resources/health1.png"));
            health2 = ImageIO.read(new File("Resources/health2.png"));
            health3 = ImageIO.read(new File("Resources/health3.png"));
            life = ImageIO.read(new File("Resources/life.png"));
            explosion1_1 = ImageIO.read(new File("Resources/Explosion_large_4.png"));
//            explosion1_3 = ImageIO.read(new File("Resources/explosion1_3"));
//            explosion1_6 = ImageIO.read(new File("Resources/explosion1_6.png"));
            Pickup_1 = ImageIO.read(new File("Resources/Pickup_1.png"));
            Pickup_3 = ImageIO.read(new File("Resources/Pickup_3.png"));
            gameover = ImageIO.read(new File("Resources/gameOver.png"));
            youWin = ImageIO.read(new File("Resources/youWin.png"));
            music = new Sound("Resources/background.wav");
            snd_explosion1 = new Sound("Resources/snd_explosion1.wav");
            snd_explosion2 = new Sound("Resources/snd_explosion2.wav");

            music.play();
            music.loop();
            gameOver = false;
            observer = this;

            wallWidth = wall2.getWidth(this);
            gm = new GameMap("gamemap.txt", this);
            gm.read();
            gm.close();
            
            tank1 = new MyTank(myTank, 900, 490, 3, this, 0);
            tank2 = new MyTank(myTank, 75, 50, 3, this, 0);
            
            rocketPowUp = new PowerUp(Pickup_1, Math.abs(generator.nextInt() % (1024)), 
                                                Math.abs(generator.nextInt() % (640)), 0, this);
            shieldPowUp = new PowerUp(Pickup_3, Math.abs(generator.nextInt() % (1024)), 
                                                Math.abs(generator.nextInt() % (640)), 0, this);
            
            blueShield = new Shield(Shield2, tank1.getX(), tank2.getY(), 0, this);            
            redShield = new Shield(Shield1, tank2.getX(), tank2.getY(), 0, this);
            hb1 = new HealthBar(healthFull, tank1.getX(), tank1.getY() + 64, this);
            hb2 = new HealthBar(healthFull, tank2.getX(), tank2.getY() + 64, this);
            life1 = new HealthBar(life, 590, 370, this);
            life2 = new HealthBar(life, 110, 370, this);

            KeyControl key = new KeyControl(this, ge);
            addKeyListener(key);
            ge = new GameEvents();
            ge.addObserver(tank1);
            ge.addObserver(tank2);

        } catch (Exception e) {
            System.out.print("No resources are found");
        }
    }

    public void drawBackGroundWithTileImage() {
        int TileWidth = background.getWidth(this);
        int TileHeight = background.getHeight(this);

        int NumberX = (int) (w / TileWidth);
        int NumberY = (int) (h / TileHeight);

        for (int i = -1; i <= NumberY; i++) {
            for (int j = 0; j <= NumberX; j++) {
                g2.drawImage(background, j * TileWidth,
                        i * TileHeight, TileWidth,
                        TileHeight, this);
            }
        }

    }

    public void drawDemo() {

        if (!gameOver) {
            drawBackGroundWithTileImage();
            //draw weakWalls on map
            for (int i = 0; i < weakWalls.size(); i++) {
                w1 = weakWalls.get((i));
                w1.update();
                w1.draw(g2, this);
            }
            
            //draw unbreakable walls on map
            for (int i = 0; i < strongWalls.size(); i++) {
                w2 = strongWalls.get(i);
                w2.draw(g2, this);
            }
            
            /*power up updates */
            rocketPowUp.update();
            shieldPowUp.update();
            hb1.update();
            hb2.update();
            redShield.update();
            blueShield.update();
//            life1.update();
//            life2.update();
            
            /* power up draws */
            rocketPowUp.draw(this);
            shieldPowUp.draw(this);
            
            if (shieldOn1 == true) {
                blueShield.draw(g2, this);
            }
            if(shieldOn2 == true) {
                redShield.draw(g2, this);
            }
            
            /*health points and tank draws/updates */
            if (hp1 <= 200) {
                tank1.draw(g2, this);
                tank1.update(w, h);
                hb1.draw(g2, this);
            }
            if (hp2 <= 200) {
                tank2.draw(g2, this);
//                tank2.update();
                tank2.update(w, h);
                hb2.draw(g2, this);
            }

//            if (hp1 <= 3) {
//                life1.draw(g2, this);
//            }
//            if (hp2 <= 3) {
//                life2.draw(g2, this);
//            }
            
            /* bullets and rockets draw */
            for (int i = 0; i < bullets1.size(); i++) {
                b1 = bullets1.get((i));
                if (b1.getY() < 0 || b1.getX() < 0 || b1.getX() > 950 || b1.getY() > 550) {
                    bullets1.remove(i);
                } else {
                    b1.update1();
                    b1.draw(g2, this);
                }
            }
            for (int i = 0; i < bullets2.size(); i++) {
                b2 = bullets2.get((i));
                if (b2.getY() < 0 || b2.getX() < 0 || b2.getX() > 950 || b2.getY() > 550) {
                    bullets2.remove(i);
                } else {
                    b2.update2();
                    b2.draw(g2, this);
                }
            }

            for (int i = 0; i < rockets1.size(); i++) {
                r1 = rockets1.get((i));
                if (r1.getY() < 0 || r1.getX() < 0 || r1.getX() > 950 || r1.getY() > 550) {
                    rockets1.remove(i);
                    if (ammo1 <= 0) {
                        pickedUp1 = false;
                    }
                } else {
                    r1.update1();
                    r1.draw1(g2, this);
                }
            }
            for (int i = 0; i < rockets2.size(); i++) {
                r2 = rockets2.get((i));
                if (r2.getY() < 0 || r2.getX() < 0 || r2.getX() > 950 || r2.getY() > 550) {
                    rockets2.remove(i);
                    if (ammo2 <= 0) {
                        pickedUp2 = false;
                    }
                } else {
                    r2.update2();
                    r2.draw2(g2, this);
                }
            }
            
            /* score draw */ 
            g2.setColor(Color.BLUE);
            g2.setFont(new Font("Garamond", Font.BOLD, 20));
            g2.drawString("Player 1", 925, 20);
            g2.setFont(new Font("Garamond", Font.BOLD, 60));
            g2.drawString("" + score1, 550, 60);

            g2.setColor(Color.RED);
            g2.setFont(new Font("Garamond", Font.BOLD, 20));
            g2.drawString("Player 2", 20, 20);
            g2.setFont(new Font("Garamond", Font.BOLD, 60));
            g2.drawString("" + score2, 450, 60);

        }

        if (gameOver) {
            drawBackGroundWithTileImage();
            g2.setColor(Color.BLUE);
            g2.setFont(new Font("Garamond", Font.BOLD, 20));
            g2.drawString("Player 1", 925, 20);
            g2.setFont(new Font("Garamond", Font.BOLD, 60));
            g2.drawString("" + score1, 550, 60);

            g2.setColor(Color.RED);
            g2.setFont(new Font("Garamond", Font.BOLD, 20));
            g2.drawString("Player 2", 20, 20);
            g2.setFont(new Font("Garamond", Font.BOLD, 60));
            g2.drawString("" + score2, 450, 60);

            if (score1 > score2) {
                g2.setColor(Color.BLUE);
                g2.setFont(new Font("Garamond", Font.BOLD, 50));
                g2.drawString("Player 1 Win! ", 350, 300);
            } else {
                g2.setColor(Color.RED);
                g2.setFont(new Font("Garamond", Font.BOLD, 50));
                g2.drawString("Player 2 Win! ", 350, 300);

            }
            music.stop();

        }
    }
    
    //code taken from gm1942
    //draw mini map
    public Graphics2D create1Graphics2D(int w, int h) {
        Graphics2D g2 = null;
        if (bimg1 == null || bimg1.getWidth() != w || bimg1.getHeight() != h) {
            bimg1 = (BufferedImage) createImage(w, h);
        }
        g2 = bimg1.createGraphics();
        g2.setBackground(getBackground());
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.clearRect(0, 0, w, h);
        return g2;
    }
    
    //draw left split screen
    public Graphics2D create2Graphics2D(int w, int h) {
        Graphics2D g2 = null;
        if (bimg2 == null || bimg2.getWidth() != w || bimg2.getHeight() != h) {
            bimg2 = (BufferedImage) createImage(w, h);
        }
        g2 = bimg2.createGraphics();
        g2.setBackground(getBackground());
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.clearRect(0, 0, w, h);
        return g2;
    }

    public void paint(Graphics g) {
        if (bimg1 == null) {
            Dimension windowSize = getSize();
            bimg1 = (BufferedImage) createImage(windowSize.width,
                    windowSize.height);
            g2 = bimg1.createGraphics();
        }
        drawDemo();
        //        draw mini map
        minimap = bimg1.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        g2.drawImage(minimap, 440, 460, this);
       
        g.drawImage(bimg1, 0, 0, this);

    }

    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    public void run() {

        Thread me = Thread.currentThread();
        while (thread == me) {
            repaint();
            try {
                thread.sleep(15);
            } catch (InterruptedException e) {
                break;
            }

        }
    }

    public static void main(String argv[]) {
        final TankWars demo = new TankWars();
        demo.init();
        JFrame f = new JFrame("Tank Wars");
        f.addWindowListener(new WindowAdapter() {
        });
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(1024, 640));
        f.setVisible(true);
        f.setResizable(false);
        demo.start();
    }

}
