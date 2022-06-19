import processing.core.PImage;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.Arrays;

public class Sketch extends PApplet {

  /**
   * Authour: Ellis Guo
   * Date: June 15, 2022 
  */

    float B_WIDTH = 400;
    float B_HEIGHT = 400;
    float RAND = 29;
    float apple_x;
    float apple_y;
    float fltcolour1 = 0;
    float fltcolour2 = 0;
    float fltcolour3 = 0;

    int dots;
    float DOT_SIZE = 5;
    float ALL_DOTS = (B_WIDTH*B_HEIGHT)/(DOT_SIZE*DOT_SIZE);

    ArrayList<Float> snakeX = new ArrayList<Float>();
    ArrayList<Float> snakeY = new ArrayList<Float>();
    //float[] snakeX;
    //float[] snakeY;

    int CELL_WIDTH = 10;
    int CELL_HEIGHT = 10;
    int MARGIN = 5;
    
    int ROW_COUNT = 27;
    int COLUMN_COUNT = 27;

    int[][] intGrid = new int[ROW_COUNT][COLUMN_COUNT];

    boolean upPressed = false;
    boolean downPressed = false;
    boolean leftPressed = false;
    boolean rightPressed = false;

    boolean inGame = true;

    PImage ballImage;
    PImage appleImage;
    PImage headImage;

    String strGameEnd = "GAME END";
  
        public void settings(){
            size(400,400);
        }

         public void setup(){

             background(210, 255, 173);

             ballImage = loadImage("Basic_green_dot.png");
             appleImage = loadImage("Gerald_G_Simple_Fruit_(FF_Menu)_5.png");
             headImage = loadImage("Basic_red_dot.png");

             ballImage.resize(15,15);
             appleImage.resize(15,15);
             headImage.resize(15,15);
             initGame();

             int r = (int) (Math.random() * RAND);
             apple_x = ((r * DOT_SIZE));

             r = (int) (Math.random() * RAND);
             apple_y = ((r * DOT_SIZE));
         }
  
         public void draw(){

             if (inGame) {

                 background(210, 255, 173);
                 image(appleImage, apple_x, apple_y);
                 for (int z = 0; z < dots; z++) {

                     if (z == 0) {
                         image(headImage, snakeX.get(z), snakeY.get(z));
                     } else {
                         image(ballImage, snakeX.get(z), snakeY.get(z));
                     }
                 }

                

                 move();
                 checkApple();
                 checkCollision();
                 grid();

                 fill(fltcolour1, fltcolour2, fltcolour3);
                 textSize(30);
                 text("score: " + dots, 30, 55);
               
             } else {

                 gameOver();

             }
         }

    /**
       * The gameOver method is called when boolean inGame is false. It loads an end game screen, no return value.
       */
  
    public void gameOver() {

        background(255);
        fill(0);
        textSize(50);
        text(strGameEnd, 30, 55);
        image(appleImage, 200, 200);
        rotate(PI/3);
    }

    /**
       * The initGame method creates the snake. It sets the distance between the snake head and the snake body. No return value
       */

    public void initGame() {

        dots = 3;
        for (int i = 0; i < dots; i++) {
            snakeX.add((float)0);
        }
        for (int i = 0; i < dots; i++) {
            snakeY.add((float)0);
        }

        for (int z = 0; z < dots; z++) {
            snakeX.set(z, (float)(50 - z * 10));
            snakeY.set(z, (float)50);
        }

    }

    /**
       * The grid method creates a grid using 2D arrays. No return value
       */

    public void grid() {
      
      for (int row = 0; row < ROW_COUNT; row++) {
        for (int column = 0; column < COLUMN_COUNT; column++ ) 
          {
            if (intGrid[row][column] == 1)
              {
                fill(210, 255, 173);
              }
              fill(210, 255, 173);
              noStroke();
              rect(MARGIN*(column+1) + CELL_WIDTH*column, MARGIN*(row+1) + CELL_HEIGHT*row, CELL_WIDTH, CELL_HEIGHT);
          }

      }
    }

    /**
       * The checkApple method checks the distance between apple and snake. If a snake eats the apple another apple is created.  No return value
       */

    public void checkApple() {

        if ((snakeX.get(0) == apple_x) && (snakeY.get(0) == apple_y)) {
            snakeX.add((float)0);
            snakeY.add((float)0);
            dots++;
            locateApple();
        }



    }

    /**
       * The move method controls the movement of the snake. No return value. 
       */

    public void move() {

        for (int z = dots-1; z > 0; z--) {
            snakeX.set(z, (float)(snakeX.get(z-1)));
            snakeY.set(z, (float)(snakeY.get(z-1)));
            //snakeX[z] = snakeX[(z - 1)];
            //snakeY[z] = snakeY[(z - 1)];
        }

        if (leftPressed) {
            //System.out.println("test " + Arrays.toString(snakeX));
            // System.out.println("left pressed " + snakeX[0]);
            snakeX.set(0, (float)(snakeX.get(0) - DOT_SIZE)) ;
            //System.out.println("after " + Arrays.toString(snakeX));
        }

        if (rightPressed) {
            snakeX.set(0, (float)(snakeX.get(0) + DOT_SIZE)) ;
        }

        if (upPressed) {
            snakeY.set(0, (float)(snakeY.get(0) - DOT_SIZE)) ;
            //snakeY[0] -= DOT_SIZE;
        }

        if (downPressed) {
            snakeY.set(0, (float)(snakeY.get(0) + DOT_SIZE)) ;
            //snakeY[0] += DOT_SIZE;
        }
    }

  /**
       * The checkCollision method checks for collisions between the snake and the walls, and the snake and itself. If any collision is true then inGame will be false. No return value
       */

    public void checkCollision() {

        for (int z = dots-1; z >= 0; z--) {

            if ((z > 4) && (snakeX.get(0) == snakeX.get(z)) && (snakeY.get(0) == snakeY.get(z))) {
                inGame = false;
            }

            if (snakeY.get(0) >= B_HEIGHT) {
                inGame = false;
            }

            if (snakeY.get(0) < 0) {
                inGame = false;
            }

            if (snakeX.get(0) >= B_WIDTH) {
                inGame = false;
            }

            if (snakeX.get(0) < 0) {
                inGame = false;
            }
        }
    }

  /**
       * The locateApple method spawns in an apple in a random location. No return value. 
       */

    public void locateApple() {

        int r = (int) (Math.random() * RAND);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND);
        apple_y = ((r * DOT_SIZE));

    }

  /**
       * The keypressed method is built in, the updownleftright arrows control which booleans are true and false. No return value. 
       */

    public void keyPressed() {

        if (keyCode == UP) {

            upPressed = true;
            downPressed = false;
            leftPressed = false;
            rightPressed = false;
        }

        else if (keyCode == DOWN) {

            downPressed = true;
            upPressed = false;
            leftPressed = false;
            rightPressed = false;
        }

        else if (keyCode == LEFT) {

            leftPressed = true;
            downPressed = false;
            upPressed = false;
            rightPressed = false;
        }

        else if (keyCode == RIGHT) {

            rightPressed = true;
            downPressed = false;
            leftPressed = false;
            upPressed = false;
        }

    }

  /**
       * The mousePressed method is built in. It controls the colour of the scoreboard. Yes return value. 
       */

  public void mousePressed(){
    
    fltcolour1 = (float) Math.random() * RAND * (float) Math.random() * DOT_SIZE;
    fltcolour2 = (float) Math.random() * DOT_SIZE * DOT_SIZE;
    return;
    
  }
}
