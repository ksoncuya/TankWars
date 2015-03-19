/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tankwars;

import java.util.Observable;
import java.awt.event.KeyEvent;

/**
 *
 * @author Kevin Soncuya
 */

public class GameEvents extends Observable {
       int type;
       Object event;
       
   public void setValue(KeyEvent e) {
          type = 1; // let's assume this means key input. 
          //Should use CONSTANT value for this when you program
          event = e;
          setChanged();
         // trigger notification
         notifyObservers(this);  
   }

   public void setValue(String msg) {
          type = 2; 
          event = msg;
          setChanged();
         // trigger notification
         notifyObservers(this);  
        }
    }