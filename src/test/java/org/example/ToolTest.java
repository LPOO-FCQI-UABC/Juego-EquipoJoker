package org.example;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ToolTest {

    @Test
    void draw() {
         Tool tool = new Tool(0, 0, "tool");
        assertEquals(tool.getX(),0);
    }

    @Test
    void getPos() {
        Tool tool = new Tool(0, 0, "tool");
        Point point = new Point(0,0);
        assertEquals(tool.getPos(),point);
    }

    @Test
    void getX() {
        Tool tool = new Tool(0, 0, "tool");
        assertEquals(tool.getX(),0);
    }

    @Test
    void getY() {
        Tool tool = new Tool(0, 0, "tool");
        assertEquals(tool.getY(),0);
    }

    @Test
    void getType() {
        Tool tool = new Tool(0, 0, "tool");
        assertEquals(tool.getType(),"tool");
    }
}