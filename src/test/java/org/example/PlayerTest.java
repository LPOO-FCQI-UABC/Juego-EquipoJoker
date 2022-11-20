package org.example;

import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void draw() {
        Player player = new Player(0, 0, "") {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        };
        assertEquals(player.getX(),0);
        assertEquals(player.getY(),0);

    }

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

    @Test
    void tick() {
        Player player = new Player(-2, 1, "") {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        };
        assertEquals(player.getX(),-2);
        assertEquals(player.getY(),1);
    }

    @Test
    void getScore() {
        Player player = new Player(0, 0, "") {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        };

        assertEquals(player.getScore(),"0");
    }

    @Test
    void addScore() {
        Player player = new Player(0, 0, "") {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        };
        player.addScore(1);
        assertEquals(player.getScore(),"1");
    }

    @Test
    void getPos() {
        Player player = new Player(0, 0, "") {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        };
        assertEquals(player.getPos().x,0);
        assertEquals(player.getPos().y,0);
    }

    @Test
    void getX() {
        Player player = new Player(0, 0, "") {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        };
        assertEquals(player.getX(),0);
    }

    @Test
    void getY() {
        Player player = new Player(0, 0, "") {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        };
        assertEquals(player.getY(),0);
    }
}