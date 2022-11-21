package org.example;

import org.junit.jupiter.api.Test;


import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    void growingPlant() {
        int score=100;
        Plant plant = new Plant(0, 0);
        if(score >=50 && score <100){//si el puntaje es mayor o igual a 50 y menor a 100
            plant.setImage("plant2");//cambiar imagen de la planta
        }else if(score>=100 && score <150){//si el puntaje es mayor o igual a 100 y menor a 150
            plant.setImage("plant3");//cambiar imagen de la planta
        } else if(score>=150 && score <200){//si el puntaje es mayor o igual a 150 y menor a 200
            plant.setImage("plant4");//cambiar imagen de la planta
        } else if(score==200 ){//si el puntaje es igual a 200
            plant.setImage("plant5");//cambiar imagen de la planta
        } else if(score<50){//si el puntaje es menor a 50
            plant.setImage("plant1");//cambiar imagen de la planta
        }
        assertEquals(plant.getImage(),"plant3");

    }

}