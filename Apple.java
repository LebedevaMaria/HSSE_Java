package org.example;

import java.awt.*;

import static java.lang.Math.random;

public class Apple {
  private int cordX = (int) (random() * 560);
  private int cordY = (int) (random() * 560);
  private int size = 20;

  public Apple() {
  }


  public Apple(int cordX, int cordY, int size) {
    this.cordX = cordX;
    this.cordY = cordY;
    this.size = size;
  }

  public void draw(Graphics g) {
    g.setColor(Color.RED);
    g.fillOval(cordX, cordY, size, size);
  }


  public int getCordX() {
    return cordX;
  }

  public int getCordY() {
    return cordY;
  }

  public void move(){
    cordX = (int) (random() * 100) + 100;
    cordY = (int) (random() * 100) + 100;
  }
}
