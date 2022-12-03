package org.example;

import gameLibrary.Board;
import gameLibrary.Plant;
import gameLibrary.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class App {
    private static void initWindow() {
        // create a window frame and set the title in the toolbar
        JFrame window = new JFrame("Take care of the plant!");
        // when we close the window, stop the app
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int tileSize=60;
        int rows=12;
        int cols=18;
        int numTools=20;
        int numObstacles=5;
        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(new Player1(0,0,"player1.png"));
        playerList.add(new Player2(cols-1,rows-1,"player2.png"));
        Plant plant = new Plant(cols/2-1,rows/2-1,"plant1.png");
        ArrayList<String> plantPhases = new ArrayList<>();
        plantPhases.add("plant1.png");
        plantPhases.add("plant2.png");
        plantPhases.add("plant3.png");
        plantPhases.add("plant4.png");
        plantPhases.add("plant5.png");

        int pointsToWin=200;
        int pointsAdded=10;
        int pointsSubtracted=-15;

        String welcomeText="Welcome to the game 'Take care of the plant'! \nDESCRIPTION \nYou are both gardeners who need to take care of a plant";
        welcomeText+="\nYou must work together by watering the plant and giving it fertilizer to keep it alive";
        welcomeText+="\nAlso, don't forget a plant needs sunlight to grow, so make sure you give it some";
        welcomeText+="\n\nBut beware, You also must avoid the weeds at all costs or else the plant will die";
        welcomeText+="\n\nNOTE: One of you can move around the board using the arrow keys and the other can use WASD";

        ArrayList<String> toolName=new ArrayList<>();

        toolName.add("water.png");
        toolName.add("fertilizer.png");
        toolName.add("sun.png");
        ArrayList<String> obstacleName=new ArrayList<>();
        obstacleName.add("weeds.png");

        // create and start the board of the game
        Board board = new Board(tileSize,rows,cols,numTools,numObstacles,playerList,plant,plantPhases,welcomeText,toolName,
                obstacleName,pointsToWin,pointsAdded,pointsSubtracted);
        board.setBackground(new Color(139,69,19));

        // add the jpanel to the window
        window.add(board);
        // pass keyboard inputs to the jpanel
        window.addKeyListener(board);

        // don't allow the user to resize the window
        window.setResizable(false);
        // fit the window size around the components (just our jpanel).
        // pack() should be called after setResizable() to avoid issues on some platforms
        window.pack();
        // open window in the center of the screen
        window.setLocationRelativeTo(null);
        // display the window
        window.setVisible(true);
    }

    public static void main(String[] args) {
        // invokeLater() is used here to prevent our graphics processing from
        // blocking the GUI. https://stackoverflow.com/a/22534931/4655368
        // this is a lot of boilerplate code that you shouldn't be too concerned about.
        // just know that when main runs it will call initWindow() once.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initWindow();
            }
        });
    }
}
