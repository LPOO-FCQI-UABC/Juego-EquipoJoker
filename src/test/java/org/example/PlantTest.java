package org.example;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlantTest {

    @Test
    void getImage() {
        Plant plant = new Plant(0, 0);
        plant.setImage("plant");
        assertEquals(plant.getImage(),"plant");
    }

    @Test
    void setImage() {
        Plant plant = new Plant(0, 0);
        plant.setImage("plant");
        assertEquals(plant.getImage(),"plant");
    }

    @Test
    void draw() {
        Plant plant = new Plant(0, 0);
        assertEquals(plant.getX(),0);
        assertEquals(plant.getY(),0);
    }

    @Test
    void getPos() {
        Plant plant = new Plant(0, 0);
        Point point = new Point(0,0);
        assertEquals(plant.getPos(),point);
    }

    @Test
    void getX() {
        Plant plant = new Plant(0, 0);
        assertEquals(plant.getX(),0);
    }

    @Test
    void getY() {
        Plant plant = new Plant(0, 0);
        assertEquals(plant.getY(),0);
    }
}