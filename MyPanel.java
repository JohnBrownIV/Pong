import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyPanel extends JPanel implements ActionListener {

Timer timer;
TheBall ball;
Paddle rightP;
Paddle leftP;
int move;
boolean lost;
int text;
int textX;
int textY;
int binary[][];
int binaryUpdate;
int message;
int phase;
int loc;
boolean locDirect;
private ImageIcon base;
private ImageIcon center;
 
 MyPanel(){
  
  //image = new ImageIcon("sky.png").getImage();
  this.setPreferredSize(new Dimension(1300,800));
  timer = new Timer(5, this);
	timer.start();
  ball = new TheBall();
  rightP = new Paddle();
  leftP = new Paddle();
  lost = false;
  textX = 0;
  textY = 0;
  phase = 2;
  message = 0;
  locDirect = false;
  loc = 0;
  move = 0; //0 = not moving, 1 = up, 2 = down
  text = 0;
  binary = new int[25][37];
  base = new ImageIcon("Images/EyeBase.png");
  center = new ImageIcon("Images/EyePupil.png");
  base.setImage(base.getImage().getScaledInstance(639,243,639));
  center.setImage(center.getImage().getScaledInstance(234,225,234));
 }
 
 public void paint(Graphics g) {
  
  Graphics2D g2D = (Graphics2D) g;

  
  g2D.setPaint(Color.black);
  g2D.fillRect(0, 0, 1300, 800);
  g2D.drawImage(center.getImage(), 0 + loc, 0 + 10, null);//+200 is the center
  g2D.drawImage(base.getImage(), 0, 0, null);
  if (phase > 0) {
    g2D.setPaint(Color.green);
    if (text > 40) {
      g2D.setPaint(Color.red);
    } else if (text > 30) {
      g2D.setPaint(Color.orange);
    } else if(text > 20) {
      g2D.setPaint(Color.yellow);
    }
    g2D.setFont(new Font("Arial",Font.BOLD,35));
    for (int i = 0; i < 25; i++) {
      for (int x = 0; x < 37; x++) {
        g2D.drawString("" + binary[i][x],(35 * x) + 9,35 * i);
      }
    }
    if (text > 20 && ball.hits >= 20 && phase == 1) {
      g2D.setFont(new Font("Times New Roman",Font.BOLD,100));
      g2D.drawString("HELP ME", 410, 400);
    }
    if (phase > 1) {
      if (loc > 280) {
        locDirect = true;
      } else if (loc < 120) {
        locDirect = false;
      }
      if (locDirect) {
        loc -= 3;
      } else {
        loc += 3;
      }
    }
  }
  g2D.setPaint(Color.white);
  g2D.fillRect(ball.x, ball.y, 10, 10);//Ball
  g2D.fillRect(1250, rightP.y, 10, 100);//Right paddle
  g2D.fillRect(50, leftP.y, 10, 100);//Left paddle
  g2D.setFont(new Font("Comic Sans MS",Font.BOLD,25));
  /*g2D.drawString("X: " + ball.x,5,30);
  g2D.drawString("Y: " + ball.y,5,60);
  g2D.drawString("Up: " + ball.up,5,90);
  g2D.drawString("Left: " + ball.left,5,120);
  g2D.drawString("Hits: " + ball.hits,5,150);
  g2D.drawString("Speed: " + ball.speed,5,180);
  g2D.drawString("Increase: " + ball.increase,5,210);
  g2D.drawString("move: " + move,5,240);*/
  if (text > 0) {
    g2D.drawString("plonk",textX,textY);
    text--;
    if (ball.left) {
      textX--;
    } else {
      textX++;
    }
  }
  g2D.setPaint(Color.white);
  g2D.setFont(new Font("Comic Sans MS",Font.BOLD,50));
  g2D.drawString("PONG",550,50);
  g2D.setFont(new Font("Comic Sans MS",Font.BOLD,15));
  g2D.drawString("SCORE: " + ball.hits,550,67);
  if (lost) {
    g2D.setFont(new Font("Comic Sans MS",Font.BOLD,75));
    g2D.drawString("GAME OVER",425,400);
  }
 }
  @Override
	public void actionPerformed(ActionEvent e) {
    if (!lost) {
      ball.advance(leftP.y,rightP.y);//Use paddles later
      leftP.advance(ball.y, ball.left);
      if (move == 1) {
        rightP.y -= 3;
      } else if (move == 2) {
        rightP.y += 3;
      }
      if (rightP.y + 100 > 800) {
          rightP.y = 700;
        } else if (rightP.y < 0) {
          rightP.y = 0;
      }
      if (ball.beenHit) {
        text = 50;
        if (ball.left) {
          textX = ball.x - 50;
        } else {
          textX = ball.x;
        }
        if (ball.up) {
          textY = ball.y;
        }else {
          textY = ball.y + 25;
        }
        ball.beenHit = false;
        for (int x = 0; x < 25; x++) {
          for (int y = 0; y < 37; y++) {
            if (binary[x][y] == 0) {
              binary[x][y] = 1;
            } else {
              binary[x][y] = 0;
            }
          }
        }
        if (ball.hits >= 10 && phase == 0) {
          phase = 1;
        } else if (ball.hits >= 15 && phase == 1) {
          phase = 2;
        }
      }
    }
    if (ball.x > 1300 && !lost) {
      lost = true;
    } else if (ball.x < 0 && !lost) {
      lost = true;
    }
    if (phase > 0) {
      for (int i = 0; i < 37; i++) {
        binary[(int) (Math.random() * 25)][i] = (int) (Math.random() * 2);
      } 
    }
    repaint();
  }
}