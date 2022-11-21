# Juego-EquipoJoker
Biblioteca de Juego interativo, educativo y cooperativo/multijugador.
Juego "Take care of the Plant" hecho con la biblioteca de Juego"
//
¡Bienvenido al juego "Take care of the Plant" !
DESCRIPCIÓN
Juego donde dos jugadores son jardineros que necesitan cuidar una planta.
Deben trabajar juntos regando la planta y dándole fertilizante para mantenerla viva.
Además de proporcionar luz solar a la planta para crecer
Pero deben evitar coger obstaculos como las hierbas malas o de lo contrario la planta morirá.

/////////////////////
CLASE APP

private static void initWindow() ;
Donde se crea un marco de ventanacuyo tamano no se puede modificar
con el titulo y tablero principal donde se lleva acabo el juego.

///////////////////
CLASE BOARD

public Board();

metodo donde se inicializa el tablero al llamar metodos donde se 
establece el tamaño, fondo, jugadores, herramientas y planta del tablero de juego,
al igual que un temporizador que checa las acciones hechas durante el hugo
cada cierta tiempo


public void actionPerformed(ActionEvent e)
  
 metodo que llama una funcion tick, el que quita y pone 
 puntos al jugador, checa el estado del juego y esta constantemente 
 cambiando el diseno grafico del tablero
 

public void paintComponent(Graphics g);
 
  metodo que muestra las herramientas, trabajos, 
  putuaeje y fondo del tablero


 public void keyPressed(KeyEvent e);
 
metodo para que los personajes del jugador reaccionen a los eventos del teclado


public void drawBackground(Graphics g);

metodo que colorea el trablero de forma que parexca tablero de ajedrez


 public void drawScore(Graphics g);
 
 metodo donde se imprime el puntuaje total entre los dos jugadores
 en la parte inferior del tablero
 
 

 public ArrayList<Tool> populateTools();
 
 metodo donde se incializan herramientas y obstaculos en posiciones aleatorias 
 del tablero checando que no se repitan posiciones
 
 
 public ArrayList<Player> populatePlayers();
 
 metodo donde se incializan los jugadores en el tablero
 
 
 public void collectTools();
 
   metodo que da a los jugadores puntos por recolectar herramientas
   y le quita puntos por recoger un obstaculo al crear un arreglo con
   los herramientas recogidas
   
   
 public void growingPlant();
 
 cambia la imagen de la planta para simular su crecieminto dependiendo del 
 puntuaje total entre los jugadores
 
 
 private int getTotalScore();
 
 Consigue la puntuación total de ambos jugadores.
       

 public void checkState();
    verifica el número de herramientas y rocas/obstáculos en el tablero
    Y dependiendo si no hay más rocas en el tablero, termina el juego y declara perdedor 
    Si no hay más herramientas en el tablero pero  la puntuación es inferior a 150, termina el juego y declara perdedor
    Si no hay más herramientas en el tablero pero la puntuación es superior a 150, finaliza el juego y declara un ganador

////////////////////////
CLASE PLAYER

public Player(int x, int y, String imagePlayer);
incializa y adminsitra la posicion, imagen y puntuaje de un jugador


private void loadImage(String imagePlayer);
lee y carga una imagen 


public void draw(Graphics g, ImageObserver observer);
dibuja el jugador en el tablero con la imagen y posicion 
    
public abstract void keyPressed(KeyEvent e);
mueve el jugador dependiendo de la tecla presionada

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
       
