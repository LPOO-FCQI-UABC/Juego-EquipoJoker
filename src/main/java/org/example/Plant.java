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
    public Plant(int x, int y,String imagePlant) {
        // load the assets
        loadImage(imagePlant);
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
            // you can use just the filename if the image file is in your
            // project folder, otherwise you need to provide the file path.
            showImage = ImageIO.read(new File("images/"+image));
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        // with the Point class, note that pos.getX() returns a double, but
        // pos.x reliably returns an int. https://stackoverflow.com/a/30220114/4655368
        // this is also where we translate board grid position into a canvas pixel
        // position by multiplying by the tile size.
        g.drawImage(
                showImage,
                pos.x * Board.tileSize,
                pos.y * Board.tileSize,
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