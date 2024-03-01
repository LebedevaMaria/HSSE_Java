package org.example;

import java.awt.*;
import java.util.ArrayList;

class Pair {
  public int firstCord;
  public int secondCord;

  public Pair(int firstCord, int secondCord) {
    this.firstCord = firstCord;
    this.secondCord = secondCord;
  }
}

public class Snake {
  public static enum Directions {
    UP,
    RIGHT,
    DOWN,
    LEFT,
    STAY
  }

  private int cordX = 100;
  private int cordY = 100;
  public static ArrayList<Pair> cordsSnake = new ArrayList<>();

  public void addHead() {
    cordsSnake.add(new Pair(cordX, cordY));
  }


  private int size = 25;
  private Directions direction = Directions.STAY;

  public void addElem() {
    if (direction == Snake.Directions.UP) {
      cordsSnake.add(new Pair(cordsSnake.get(cordsSnake.size() - 1).firstCord, cordsSnake.get(cordsSnake.size() - 1).secondCord - 25));
      cordsSnake.add(new Pair(cordsSnake.get(cordsSnake.size() - 1).firstCord, cordsSnake.get(cordsSnake.size() - 1).secondCord - 25));
    } else if (direction == Snake.Directions.DOWN) {
      cordsSnake.add(new Pair(cordsSnake.get(cordsSnake.size() - 1).firstCord, cordsSnake.get(cordsSnake.size() - 1).secondCord + 25));
      cordsSnake.add(new Pair(cordsSnake.get(cordsSnake.size() - 1).firstCord, cordsSnake.get(cordsSnake.size() - 1).secondCord + 25));
    } else if (direction == Directions.RIGHT) {
      cordsSnake.add(new Pair(cordsSnake.get(cordsSnake.size() - 1).firstCord - 25, cordsSnake.get(cordsSnake.size() - 1).secondCord));
      cordsSnake.add(new Pair(cordsSnake.get(cordsSnake.size() - 1).firstCord - 25, cordsSnake.get(cordsSnake.size() - 1).secondCord));
    } else {
      cordsSnake.add(new Pair(cordsSnake.get(cordsSnake.size() - 1).firstCord + 25, cordsSnake.get(cordsSnake.size() - 1).secondCord));
      cordsSnake.add(new Pair(cordsSnake.get(cordsSnake.size() - 1).firstCord + 25, cordsSnake.get(cordsSnake.size() - 1).secondCord));
    }
  }

  public Snake() {
  }

  public Snake(int cordX, int cordY, int size) {
    this.cordX = cordX;
    this.cordY = cordY;
    this.size = size;
  }

  public Directions getDirection() {
    return direction;
  }

  public void draw(Graphics g) {
    for (int i = 0; i < cordsSnake.size(); i++) {
      g.setColor(Color.green);
      g.fillRect(cordsSnake.get(i).firstCord, cordsSnake.get(i).secondCord, size, size);
    }

  }

  public int getCordX() {
    return cordsSnake.get(0).firstCord;
  }

  public int getCordY() {
    return cordsSnake.get(0).secondCord;
  }

  Snake move() {
    for (int i = cordsSnake.size() - 1; i >= 1; i--) {
      cordsSnake.get(i).firstCord = cordsSnake.get(i - 1).firstCord;
      cordsSnake.get(i).secondCord = cordsSnake.get(i - 1).secondCord;
    }

    switch (direction) {
      case UP:
        if ((cordsSnake.get(0).secondCord - size / 9) < 0 || (cordsSnake.get(0).secondCord - size / 9) > 560) {
          System.exit(0);
        }
        cordsSnake.get(0).secondCord -= size / 9;
        break;
      case RIGHT:
        if ((cordsSnake.get(0).firstCord + size / 9) < 0 || (cordsSnake.get(0).firstCord + size / 9) > 560) {
          System.exit(0);
        }
        cordsSnake.get(0).firstCord += size / 9;
        break;
      case DOWN:
        if ((cordsSnake.get(0).secondCord + size / 9) < 0 || (cordsSnake.get(0).secondCord + size / 9) > 540) {
          System.exit(0);
        }
        cordsSnake.get(0).secondCord += size / 9;
        break;
      case LEFT:
        if ((cordsSnake.get(0).firstCord - size / 9) < 0 || (cordsSnake.get(0).firstCord - size / 9) > 560) {
          System.exit(0);
        }
        cordsSnake.get(0).firstCord -= size / 9;
        break;
    }

    /*switch (direction) {
      case UP -> cordsSnake.get(0).secondCord -= size / 9;
      case RIGHT -> cordsSnake.get(0).firstCord += size / 9;
      case DOWN -> cordsSnake.get(0).secondCord += size / 9;
      case LEFT -> cordsSnake.get(0).firstCord -= size / 9;
    }*/


    return this;
  }

  Snake changeDirection(Directions direction) {
    this.direction = direction;

    return this;
  }
}