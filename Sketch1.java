/*

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

public class Sketch extends PApplet {
	
	float snakeX = 150;
  float snakeY = 100;

  boolean upPressed = false;
  boolean downPressed = false;
  boolean leftPressed = false;
  boolean rightPressed = false;

  
  public void settings() {
	
    size(400, 400);
  }

  public void setup() {
    background(210, 255, 173);
  }
  
  public void draw() {

    fill(0,255,0);
    noStroke();
    ellipse(snakeX, snakeY, 20, 20);

    if (upPressed) {
      snakeY-= 2;
    }
    if (downPressed) {
      snakeY+=2;
    }
    if (leftPressed) {
      snakeX-=2;
    }
    if (rightPressed) {
      snakeX+=2;
    }
    
    if (snakeX < 0 || snakeX > width) {
      background(255);
      fill(0);
      textSize(50);
      text("GAME END", 30, 55);
    }
  
    if (snakeY < 0  || snakeY > height) {
      background(255);
      fill(0);
      textSize(50);
      text("GAME END", 30, 55);
    }
	
  }

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

*/