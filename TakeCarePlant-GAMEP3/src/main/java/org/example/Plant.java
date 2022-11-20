package org.example;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Plant {

    // image that represents the coin's position on the board
    private BufferedImage showImage;
    private String image;

    // current position of the plant on the board grid
    private Point pos;
    private int x;
    private int y;
    public Plant(int x, int y) {
        // load the assets
        loadImage("plant1");//this is the default image
        this.x=x;
        this.y=y;
        // initialize the state
        pos = new Point(x, y);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        loadImage(image);
    }
    private void loadImage(String image) {
        try {
            showImage = ImageIO.read(new File("images/"+image+".PNG"));
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(
                showImage,
                pos.x * Board.TILE_SIZE,
                pos.y * Board.TILE_SIZE,
                observer
        );
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