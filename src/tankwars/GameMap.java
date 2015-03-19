/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankwars;

/**
 *
 * @author Kevin Soncuya
 */
import java.io.*;

public class GameMap {

    private BufferedReader source;
    private static int lineno = 0;   // line number of source program
    private int position;     // position of last character processed
    private boolean isPriorEndLine = true;  // if true then last character read was newline
    // so read in the next line

    private String nextLine;    //String type variable nextLine
    TankWars tw;
    
    GameMap(String sourceFile, TankWars tw) throws IOException {
        this.tw = tw;
        System.out.println("Source file: " + sourceFile);
        System.out.println("user.dir: " + System.getProperty("user.dir"));
        source = new BufferedReader(new FileReader(sourceFile));
    }

    void close() {
        try {
            source.close();
        } catch (Exception e) {
        }
    }
    
    /**
    *  read next char; track line #, character position in line<br>
    *  return space for newline
     * @throws java.io.IOException
    *  @IOException is thrown for IO problems such as end of file
    */    
    
    public void read() throws IOException {
        try {
            while ((nextLine = source.readLine()) != null) {
                for (int i = 0; i < nextLine.length(); i++) {
                    if (nextLine.charAt(i) == '0') {    //if the character is 0, draw nothing and read next char
                        i++;
                    } else if (nextLine.charAt(i) == '1') { //if the character is 1, draw a breakable w1 then read next char
                        tw.weakWalls.add(new Wall(tw.wall2, i * 32, lineno * 32, 0, tw));
                    } else if (nextLine.charAt(i) == '2') { //if the character is 2, draw a dark w2 then read next char
                        tw.strongWalls.add(new Wall(tw.wall1, i * 32, lineno * 32, 0, tw));
                    }
                }
                lineno++;
            }
            if (nextLine == null) {  // hit eof or some I/O problem
                throw new IOException();
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

}
