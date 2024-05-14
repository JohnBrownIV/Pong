import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class MyFrame extends JFrame implements KeyListener{
 
 MyPanel panel;
 
 MyFrame(){
  
  panel = new MyPanel();
  
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
  this.add(panel);
  this.pack();
  this.setLocationRelativeTo(null);
  this.addKeyListener(this);
  this.setVisible(true);
  
 }
 @Override
	public void keyTyped(KeyEvent e) {
  }
 @Override
	public void keyPressed(KeyEvent e) {
    //System.out.println(e.getKeyCode());
    if (e.getKeyCode() == 87) {
      panel.move = 1;
    } else if (e.getKeyCode() == 83) {
      panel.move = 2;
    }
  }
  @Override
	public void keyReleased(KeyEvent e) {
    panel.move = 0;
  }
}