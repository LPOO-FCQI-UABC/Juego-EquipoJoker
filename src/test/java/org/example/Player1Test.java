package org.example;

import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class Player1Test {

    @Test
    void keyPressed() {
        Player player = new Player(0, 0, "") {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        };
        assertEquals(player.getX(),0);
        assertEquals(player.getY(),0);
    }
}