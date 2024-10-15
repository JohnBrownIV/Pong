import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class MyFrame extends JFrame implements KeyListener{
 
 MyPanel panel;
 boolean twoPlayer;
 
 MyFrame(int players){
  
  twoPlayer = false;
  if (players == 2) {
    twoPlayer = true;
  }
  panel = new MyPanel(twoPlayer);
  
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
  this.add(panel);
  this.pack();
  this.setLocationRelativeTo(null);
  this.addKeyListener(this);
  this.setVisible(true);
  this.toFront();
  this.requestFocus();
  
 }
 @Override
	public void keyTyped(KeyEvent e) {
  }
 @Override
	public void keyPressed(KeyEvent e) {
    //System.out.println(e.getKeyCode());
    if (e.getKeyCode() == 27) {//Escape
      if (twoPlayer) {
        twoPlayer = false;
        panel.twoPlayer = false;
      } else {
        twoPlayer = true;
        panel.twoPlayer = true;
      }
    }
    if (!twoPlayer) {
      if (e.getKeyCode() == 38) {
        panel.move = 1;
      } else if (e.getKeyCode() == 40) {
        panel.move = 2;
      }
    } else {
      if (e.getKeyCode() == 38) {
        panel.move = 1;
      } else if (e.getKeyCode() == 40) {
        panel.move = 2;
      }
      if (e.getKeyCode() == 87) {
        panel.move2 = 1;
      } else if (e.getKeyCode() == 83) {
        panel.move2 = 2;
      }
    }
    
  }
  @Override
	public void keyReleased(KeyEvent e) {
    if (!twoPlayer) {
      panel.move = 0;
    } else {
      if (e.getKeyCode() == 38 || e.getKeyCode() == 40) {
        panel.move = 0;
      } else {
        panel.move2 = 0;
      }
    }
  }
}