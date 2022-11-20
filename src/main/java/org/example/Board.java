package org.example;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Board extends JPanel implements ActionListener, KeyListener {

    // controls the delay between each tick in ms
    private final int DELAY = 25;
    // controls the size of the board
    public static final int TILE_SIZE = 65;//tamaño de los no c
    public static final int ROWS = 8;
    public static final int COLUMNS = 10;
    // controls how many tools appear on the board
    public static final int NUM_TOOLS = 25; //numero de herramientas
    //controls how many enemies appear on the board
    public static final int NUM_ROCKS = 5; //numero de obtaculos

    // suppress serialization warning
    private static final long serialVersionUID = 490905409104883233L;
    
    // keep a reference to the timer object that triggers actionPerformed() in
    // case we need access to it in another method
    private Timer timer;
    // objects that appear on the game board
    //arraylist of players
    private ArrayList<Player> players;

    private Plant plant;
    //arraylist of tools
    private ArrayList<Tool> tools;
    //to signal de beginning of the game
    private int begin=0;
    public Board() {
        // set the game board size
        setPreferredSize(new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS));//tamaño de tablero
        // set the game board background color
        setBackground(new Color(232, 232, 232));//color de tablero
        // initialize the game state
        if(begin==0){//si es el inicio del juego
            String welcomeText="Welcome to the game 'Take care of the plant'! \nDESCRIPTION \nYou are both gardeners who need to take care of a plant";
            welcomeText+="\nYou must work together by watering the plant and giving it fertilizer to keep it alive";
            welcomeText+="\nAlso, don't forget a plant needs sunlight to grow, so make sure you give it some";
            welcomeText+="\n\nBut beware, You also must avoid the weeds at all costs or else the plant will die";
            welcomeText+="\n\nNOTE: One of you can move around the board using the arrow keys and the other can use WASD";

            JOptionPane.showMessageDialog(null,welcomeText);//mensaje de bienvenida
            begin+=1;
        }
        //initialize players
        players=populatePlayers();//inicializar jugadores
        //initialize plant
        plant=new Plant(COLUMNS/2-1,ROWS/2-1);//inicializar planta
        //initialize tools and weeds/obstacles
        tools = populateTools();//inicializar herramientas

        // this timer will call the actionPerformed() method every DELAY ms
        timer = new Timer(DELAY, this);//inicializar timer
        timer.start();//iniciar timer
    }


    @Override
    public void actionPerformed(ActionEvent e) {//cada vez que se presiona una tecla
        // this method is called by the timer every DELAY ms.
        // this space updates the state of the game
        // before the graphics are redrawn.

        // to prevent the players from disappearing off the board
        for (Player p:players){//para cada jugador
            p.tick();//actualizar posicion
        }
        // give the players points for collecting tools
        collectTools();//suma los puntos o los resta dependiendo el uso de herramientas
        //check state of game to see if they won or lost
        checkState();//verifica si gano o perdio

        // calling repaint() will trigger paintComponent() to run again,
        // which will refresh/redraw the graphics.
        repaint();//redibujar
    }

    @Override
    public void paintComponent(Graphics g) {//dibuja el tablero
        super.paintComponent(g);//dibuja el tablero
        // when calling g.drawImage() we can use "this" for the ImageObserver 
        // because Component implements the ImageObserver interface, and JPanel 
        // extends from Component. So "this" Board instance, as a Component, can 
        // react to imageUpdate() events triggered by g.drawImage()

        // draw our graphics.
        drawBackground(g);//dibuja el fondo de ajedrez
        drawScore(g);//dibuja el puntaje
        //draw tools
        for (Tool tool : tools) {//mosrtrar herramientas
            tool.draw(g, this);//dibujar
        }
        //draw players
        for (Player p:players){//muestra jugadores
            p.draw(g,this);//dibujar
        }
        //draw plant
        plant.draw(g,this);//dibujar planta en el tablero, inmedio
        // this smooths out animations on some systems
        Toolkit.getDefaultToolkit().sync();//sincronizar
    }

    @Override
    public void keyTyped(KeyEvent e) {//cuando se presiona una tecla
        // this is not used but must be defined as part of the KeyListener interface
    }

    @Override
    public void keyPressed(KeyEvent e) {//cuando se presiona una tecla
        // so the player characters react to key down events
        for(Player p:players){
            p.keyPressed(e);//checa si se presiono una tecla
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {//cuando se suelta una tecla
        // react to key up events
    }

    public void drawBackground(Graphics g) {//dibuja el fondo de ajedrez
        ///SE PUEDE BORRAR SI QUIEREN
        // draw a checkered background
        g.setColor(new Color(214, 214, 214));//color de cuadros
        for (int row = 0; row < ROWS; row++) {//para cada fila
            for (int col = 0; col < COLUMNS; col++) {//para cada columna
                // only color every other tile
                if ((row + col) % 2 == 1) {//si es impar
                    // draw a square tile at the current row/column position
                    g.fillRect(
                        col * TILE_SIZE, 
                        row * TILE_SIZE, 
                        TILE_SIZE, 
                        TILE_SIZE
                    );
                }
            }    
        }
    }

    public void drawScore(Graphics g) {//dibuja el puntaje
        // set the text to be displayed
        String text = "TOTAL: "+Integer.toString(getTotalScore())+" points";//retorna el puntaje total
        // we need to cast the Graphics to Graphics2D to draw nicer text
        Graphics2D g2d = (Graphics2D) g;//castear
        g2d.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(
            RenderingHints.KEY_RENDERING,
            RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(
            RenderingHints.KEY_FRACTIONALMETRICS,
            RenderingHints.VALUE_FRACTIONALMETRICS_ON);

        // set the text color and font
        g2d.setColor(new Color(30, 201, 139));//color de texto
        g2d.setFont(new Font("Lato", Font.BOLD, 25));//tipo de letra

        // draw the score in the bottom center of the screen
        // https://stackoverflow.com/a/27740330/4655368
        FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());//obtener las medidas de la letra

        // the text will be contained within this rectangle.
        // here sized to be the entire bottom row of board tiles
        Rectangle rect = new Rectangle(0, TILE_SIZE * (ROWS - 1), TILE_SIZE * COLUMNS, TILE_SIZE);//rectangulo

        // determine the x coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;//posicion x

        // determine the y coordinate for the text
        // (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();//posicion y

        // draw the string
        g2d.drawString(text, x, y);//dibujar texto
    }
    public ArrayList<Tool> populateTools() {//inicializar herramientas
        // create a list of tools
        ArrayList<Tool> toolList = new ArrayList<>();//lista de herramientas
        //create list of tool names
        ArrayList<String> toolName=new ArrayList<>();//lista de nombres de herramientas

        //add tool names to list according to image names
        toolName.add("water");
        toolName.add("fertilizer");
        toolName.add("sun");

        Random rand = new Random();//generar numeros aleatorios

        //add tools to the arraylist
        for (int i = 0; i < NUM_TOOLS-NUM_ROCKS; i++) {//generar herramientas
            int toolX = rand.nextInt(COLUMNS);
            int toolY = rand.nextInt(ROWS);
            int randomIndex = rand.nextInt(toolName.size());
            String tool = toolName.get(randomIndex);
            //check for repeating position of tools
            //check for repeating position with player
            //check for repeating position with plant
            //check for repeating position of score
            for (Player p:players){//checar si se repite la posicion con el jugador
                for (int j=0;j<toolName.size();j++){
                    while(toolList.contains(new Tool(toolX,toolY,toolName.get(j)))|| (toolX==plant.getX() && toolY==plant.getY())//si la posicion de la herramienta es igual a la posicion de la planta
                            || (toolX==p.getX() && toolY==p.getY()) || toolX==9 && toolY==12|| toolX==10 && toolY==12){//si la posicion de la herramienta es igual a la posicion del jugador
                        toolX = rand.nextInt(COLUMNS);
                        toolY = rand.nextInt(ROWS);
                    }
                }
            }

            toolList.add(new Tool(toolX, toolY,tool));//agregar herramienta a la lista
        }
        //add rocks
        for (int i = 0; i < NUM_ROCKS; i++) {//generar rocas
            int rockX = rand.nextInt(COLUMNS);
            int rockY = rand.nextInt(ROWS);
            //check for repeating position of tools
            //check for repeating position with player
            //check for repeating position with plant
            //check for repeating position of score
            //check for repeating position of rocks
            for (Player p:players){//checar si se repite la posicion con el jugador
                for(int j=0;toolName.size()>j;j++){//checar si se repite la posicion con la herramienta
                    while(toolList.contains(new Tool(rockX,rockY,toolName.get(j)))|| (rockX==plant.getX() && rockY==plant.getY())//si la posicion de la roca es igual a la posicion de la planta
                            || (rockX==p.getX() && rockY==p.getY()) || rockX==9 && rockY==12|| rockX==10 && rockY==12){//si la posicion de la roca es igual a la posicion del jugador
                        rockX = rand.nextInt(COLUMNS);
                        rockY = rand.nextInt(ROWS);
                    }
                }
            }
            toolList.add(new Tool(rockX,rockY,"weeds"));//agregar roca a la lista
        }
        return toolList;
    }
    public ArrayList<Player> populatePlayers() {//inicializar jugadores
        // create a list of players
        ArrayList<Player> playerList = new ArrayList<>();//lista de jugadores

        // add players to the arraylist in the board's corners
        playerList.add(new Player1(0,0,"player1"));//agregar jugador 1
        playerList.add(new Player2(COLUMNS-1,ROWS-1,"player2"));//agregar jugador 2
        return playerList;//regresar lista de jugadores
    }


    // allow player to pickup tools
    public void collectTools() {//checar si el jugador recoge una herramienta
        // check if the player is on the same tile as a tool
       ArrayList<Tool> collectedTools= new ArrayList<>();//lista de herramientas recogidas
        for (Tool tool : tools) {//recorrer lista de herramientas
            // if the player is on the same tile as a tools, collect it
            if (players.get(0).getPos().equals(tool.getPos())) {//si la posicion del jugador es igual a la posicion de la herramienta
                //check if the tool is a weed/obsacle
                if(tool.getType().equals("weeds")){//si la herramienta es una maleza
                    //if it is a weed, remove points
                    players.get(0).addScore(-15);//restar puntos
                }else {
                    // give the player some points for picking any other tools up
                    players.get(0).addScore(10);//sumar puntos
                }
                //change image of plant depending on score
                growingPlant();//cambiar imagen de la planta
                //add the tool to the list of collected tools
                collectedTools.add(tool);//agregar herramienta a la lista de herramientas recogidas
            }

            //repeat for player 2
            if (players.get(1).getPos().equals(tool.getPos())) {
                // give the player some points for picking this up
                if(tool.getType().equals("weeds")){
                    players.get(0).addScore(-15);
                }else {
                    players.get(0).addScore(10);
                }
                //change image of plant
                growingPlant();
                collectedTools.add(tool);
            }
        }
        // remove collected coins from the board
        tools.removeAll(collectedTools);

    }
    public void growingPlant(){//cambiar imagen de la planta
        //change image of plant depending on score
        //
        int score=getTotalScore();//obtener puntaje total
        //if score is less than 0, change image of baby plant
        //as the score increases, change the image of the plant to a bigger one
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
    }
    private int getTotalScore(){//obtener puntaje total
        int score=0;//inicializar puntaje
        //get the total score of both players
        for (int i=0;i<players.size();i++){//recorrer lista de jugadores
            score+=Integer.parseInt(players.get(i).getScore());//sumar puntaje
        }
        return score;
    }

    public void checkState(){//checar estado del juego
        //check number of tools and rocks/obstacles on the board
        int rocks=0,numTools=0;//inicializar rocas y herramientas
        for (Tool tool:tools){//recorrer lista de herramientas
            if(tool.getType().equals("weeds")){//si la herramienta es una maleza
                rocks++;//sumar roca
            }else {//si la herramienta no es una maleza
                numTools++;//sumar herramienta
            }
        }
        //if there are no more rocks on the board, end the game and declare a loser
        if(rocks==0){//si no hay rocas en el tablero
            JOptionPane.showMessageDialog(null,"....You clearly lost! \nFinal Score: "+getTotalScore()+"\nGame Over");//mostrar mensaje
            System.exit(0);//salir del juego
        }
        //if there are no more tools on the board but the score is less than  150, end the game and declare a loser
        if(numTools==0 && getTotalScore()<150){//si no hay herramientas en el tablero y el puntaje es menor a 150
            JOptionPane.showMessageDialog(null,"YOU BOTH LOST! \nFinal Score: "+getTotalScore()+"\nGame Over.");//mostrar mensaje
            System.exit(0);//salir del juego
        }
        //if there are no more tools on the board but the score is greater than 150, end the game and declare a winner
        if(getTotalScore()==200 || (getTotalScore()>=150 && numTools==0)){//si el puntaje es igual a 200 o el puntaje es mayor a 150 y no hay herramientas en el tablero
            JOptionPane.showMessageDialog(null," CONGRATULATIONS YOU BOTH WON! \n Good Job Team\n Final Score: "+getTotalScore()+"\nGame Over");//mostrar mensaje
            System.exit(0);//salir del juego
        }
    }

}
