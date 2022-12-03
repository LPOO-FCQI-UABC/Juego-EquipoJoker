package org.example;

import gameLibrary.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class Player1 extends Player {
    private Point pos=super.getPos();
    public Player1(int x, int y,String image) {
        super(x, y,image);
    }

    public void keyPressed(KeyEvent e) {
        // every keyboard get has a certain code. get the value of that code from the
        // keyboard event so that we can compare it to KeyEvent constants
        int key = e.getKeyCode();

        // depending on which arrow key was pressed, we're going to move the player by
        // one whole tile for this input
        if ( key == KeyEvent.VK_W) {
            pos.translate(0, -1);
        }
        if (key == KeyEvent.VK_D) {
            pos.translate(1, 0);
        }
        if (key == KeyEvent.VK_S) {
            pos.translate(0, 1);
        }
        if (key == KeyEvent.VK_A) {
            pos.translate(-1, 0);
        }
    }
}
