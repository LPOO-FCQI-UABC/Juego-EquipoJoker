package org.example;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Player {

    // image that represents the player's position on the board
    private BufferedImage image;

    // current position of the player on the board grid
    private Point pos;
    // keep track of the player's score
    private int score;

    private int x;
    private int y;
    public Player(int x, int y, String imagePlayer) {
        // load the assets
        loadImage(imagePlayer);//name of the image of the player
        // initialize the state
        this.x=x;
        this.y=y;
        pos = new Point(x, y);
        score = 0;
    }

    private void loadImage(String imagePlayer) {
        try {
            image = ImageIO.read(new File("images/"+imagePlayer+".png"));
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(
            image, 
            pos.x * Board.TILE_SIZE, 
            pos.y * Board.TILE_SIZE, 
            observer
        );
    }
    
    public abstract void keyPressed(KeyEvent e);

    public void tick() {
        // this gets called once every tick, before the repainting process happens.
        // so we can do anything needed in here to update the state of the player.

        // prevent the player from moving off the edge of the board sideways
        if (pos.x < 0) {
            pos.x = 0;
        } else if (pos.x >= Board.COLUMNS) {
            pos.x = Board.COLUMNS - 1;
        }
        // prevent the player from moving off the edge of the board vertically
        if (pos.y < 0) {
            pos.y = 0;
        } else if (pos.y >= Board.ROWS) {
            pos.y = Board.ROWS - 1;
        }
    }

    public String getScore() {
        return String.valueOf(score);
    }

    public void addScore(int amount) {
        score += amount;
    }

    public Point getPos() {
        return pos;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
