/* 
import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

public class Sketch extends PApplet {

  int B_WIDTH = 300;
  int B_HEIGHT = 300;
  float RAND = 29;
  float apple_x;
  float apple_y;

  int dots;
  int DOT_SIZE = 15;
  int ALL_DOTS = (B_WIDTH*B_HEIGHT)/(DOT_SIZE*DOT_SIZE);

  final int[] snakeX = new int[ALL_DOTS];
  final int[] snakeY = new int[ALL_DOTS];

  boolean upPressed = false;
  boolean downPressed = false;
  boolean leftPressed = false;
  boolean rightPressed = false;

  boolean inGame = true;

  PImage ballImage;
  PImage appleImage;
  PImage headImage;
  
  public void settings() {
    
    size(300, 300);
    
  }

  public void setup() {
    
    background(210, 255, 173);

    ballImage = loadImage("Basic_green_dot.png");
    appleImage = loadImage("Gerald_G_Simple_Fruit_(FF_Menu)_5.png");
    headImage = loadImage("Basic_red_dot.png");

    ballImage.resize(15,15);
    appleImage.resize(15,15);
    headImage.resize(15,15);

  }
  
  public void draw() {

    System.out.println(dots);

    fill(0);
    textSize(20);
    text(dots, 30, 55);

    // if game is still running, player hasn't lost yet
    
    if (inGame) {
      
      image(appleImage, apple_x, apple_y);

      for (int z = 0; z < dots; z++) {
        if (z == 0) {
          image(headImage, snakeX[z], snakeY[z]);
        } else {
          image(ballImage, snakeX[z], snakeY[z]);
        }
      }

      initGame();
      checkApple();
      checkCollision();
      move();
      locateApple();

    } else {

      gameOver();
      
    }     

  }

  // game ending screen

  public void gameOver() {
    
    background(255);
    fill(0);
    textSize(50);
    text("GAME END", 30, 55);
  }
  
  public void initGame() {

    dots = 3;

    for (int z = 0; z < dots; z++) {
      snakeX[z] = 50 - z * 10;
      snakeY[z] = 50;
    }

  }

  // when apple and snake head collide, a dot is added to snake body

  public void checkApple() {

    if ((snakeX[0] == apple_x) && (snakeY[0] == apple_y)) {
      dots++;
    }

  }

  public void move() {

    // movement of snake, the second dot behind the first dot will be moved into the position of the first dot

    for (int z = dots; z > 0; z--) {
      snakeX[z] = snakeX[(z - 1)];
      snakeY[z] = snakeY[(z - 1)];
    }

    if (leftPressed) {
      snakeX[0] -= DOT_SIZE;
    }

    if (rightPressed) {
      snakeX[0] += DOT_SIZE;
    }

    if (upPressed) {
      snakeY[0] -= DOT_SIZE;
    }

    if (downPressed) {
      snakeY[0] += DOT_SIZE;
    }
  }

  // collision check

  public void checkCollision() {

    for (int z = dots; z > 0; z--) {

      if ((z > 4) && (snakeX[0] == snakeX[z]) && (snakeY[0] == snakeY[z])) {
        inGame = false;
      }

      if (snakeY[0] >= B_HEIGHT) {
        inGame = false;
      }

      if (snakeY[0] < 0) {
        inGame = false;
      }

      if (snakeX[0] >= B_WIDTH) {
        inGame = false;
      }

      if (snakeX[0] < 0) {
        inGame = false;
      }
    }
  }

  // apples spawn randomly, error in frequency of spawn
    
  public void locateApple() {
    
    int r = (int) (Math.random() * RAND);
    apple_x = ((r * DOT_SIZE));

    r = (int) (Math.random() * RAND);
    apple_y = ((r * DOT_SIZE));
    
  }

  // does not seem to be functioning

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
  
}

/* 

LIST FOR TOMORROW

 RECORD VIDEO OR MAKE SUBTITLES ;-;
 gameend = string
 mouseClick = change colour of snake
 while loop inGame

*/