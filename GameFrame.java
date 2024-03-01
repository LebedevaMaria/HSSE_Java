package org.example;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
  public GameFrame() throws HeadlessException {
    setBounds(200, 200, 600, 600);
    add(new GameField());
    setVisible(true);
  }
}