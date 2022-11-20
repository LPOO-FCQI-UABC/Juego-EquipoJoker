package org.example;

import org.junit.jupiter.api.Test;


import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void actionPerformed() {
        ArrayList<Player> playerList = new ArrayList<>();
        Player player1 = new Player(0, 0, "") {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        };
        playerList.add(player1);
        assertEquals(player1.getScore(),"0");
    }

    @Test
    void paintComponent() {
        ArrayList<Player> playerList = new ArrayList<>();
        Player player1 = new Player(0, 0, "") {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        };
        playerList.add(player1);
        assertEquals(player1.getScore(),"0");
    }

    @Test
    void keyTyped() {
        ArrayList<Player> playerList = new ArrayList<>();
        Player player1 = new Player(0, 0, "") {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        };
        playerList.add(player1);
        assertEquals(player1.getScore(),"0");
    }

    @Test
    void keyPressed() {
        ArrayList<Player> playerList = new ArrayList<>();
        Player player1 = new Player(0, 0, "") {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        };
        playerList.add(player1);
        assertEquals(player1.getScore(),"0");
    }

    @Test
    void keyReleased() {
        ArrayList<Player> playerList = new ArrayList<>();
        Player player1 = new Player(0, 0, "") {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        };
        playerList.add(player1);
        assertEquals(player1.getScore(),"0");
    }

    @Test
    void drawBackground() {
        ArrayList<Player> playerList = new ArrayList<>();
        Player player1 = new Player(0, 0, "") {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        };
        playerList.add(player1);
        assertEquals(player1.getScore(),"0");
    }

    @Test
    void drawScore() {
        ArrayList<Player> playerList = new ArrayList<>();
        Player player1 = new Player(0, 0, "") {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        };
        playerList.add(player1);
        assertEquals(player1.getScore(),"0");
    }

    @Test
    void populateTools() {
        ArrayList<Player> playerList = new ArrayList<>();
        Player player1 = new Player(0, 0, "") {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        };
        playerList.add(player1);
        assertEquals(player1.getScore(),"0");
    }

    @Test
    void populatePlayers() {
        ArrayList<Player> playerList = new ArrayList<>();
        Player player1 = new Player(0, 0, "") {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        };
        playerList.add(player1);
        assertEquals(player1.getScore(),"0");
    }

    @Test
    void collectTools() {
        ArrayList<Player> playerList = new ArrayList<>();
        Player player1 = new Player(0, 0, "") {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        };
        playerList.add(player1);
        assertEquals(player1.getScore(),"0");
    }

    @Test
    void growingPlant() {
        ArrayList<Player> playerList = new ArrayList<>();
        Player player1 = new Player(0, 0, "") {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        };
        playerList.add(player1);
        assertEquals(player1.getScore(),"0");
    }

    @Test
    void checkState() {
        ArrayList<Player> playerList = new ArrayList<>();
        Player player1 = new Player(0, 0, "") {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        };
        playerList.add(player1);
        assertEquals(player1.getScore(),"0");
    }
}