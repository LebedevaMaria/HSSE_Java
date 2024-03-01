package org.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GameField extends JPanel implements KeyListener, ActionListener {
  private Timer timer;
  private Snake snake;

  private Apple apple;

  public GameField() {

    snake = new Snake();
    apple = new Apple();
    snake.addHead();
    setFocusable(true);
    addKeyListener(this);

    timer = new Timer(5, this);
    timer.start();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    apple.draw(g);
    snake.draw(g);
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    Snake.Directions nowDirection = snake.getDirection();
    switch (e.getKeyCode()) {
      case 37:
        if (nowDirection != Snake.Directions.RIGHT) {
          snake.changeDirection(Snake.Directions.LEFT);
        } else {
          snake.changeDirection(Snake.Directions.UP);
        }
        break;
      case 38:
        if (nowDirection != Snake.Directions.DOWN) {
          snake.changeDirection(Snake.Directions.UP);
        } else {
          snake.changeDirection(Snake.Directions.RIGHT);
        }
        break;
      case 39:
        if (nowDirection != Snake.Directions.LEFT) {
          snake.changeDirection(Snake.Directions.RIGHT);
        } else {
          snake.changeDirection(Snake.Directions.DOWN);
        }
        break;
      case 40:
        if (nowDirection != Snake.Directions.UP) {
          snake.changeDirection(Snake.Directions.DOWN);
        } else {
          snake.changeDirection(Snake.Directions.LEFT);
        }
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    int cordXApple = apple.getCordX();
    int cordYApple = apple.getCordY();
    int cordXSnake = snake.getCordX();
    int cordYSnake = snake.getCordY();
    if (Math.pow((cordXSnake - cordXApple) * (cordXSnake - cordXApple) + (cordYSnake - cordYApple) * (cordYSnake - cordYApple), 0.5) < 10){
      apple.move();
      snake.addElem();
    }
    snake.move();
    repaint();
  }
}
