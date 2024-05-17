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
Color binaryColor;
int lineColor;
boolean somethingBin;
boolean lineType;
 
 MyPanel(){
  
  //image = new ImageIcon("sky.png").getImage();
  this.setPreferredSize(new Dimension(1300,800));
  timer = new Timer(5, this);
	timer.start();
  ball = new TheBall();
  rightP = new Paddle();
  leftP = new Paddle();
  binaryColor = new Color(1, 100, 1);
  lost = false;
  textX = 0;
  textY = 0;
  phase = 0;
  somethingBin = true;
  message = 0;
  move = 0; //0 = not moving, 1 = up, 2 = down
  text = 0;
  lineColor = 0;
  binary = new int[25][37];
  for (int i = 0; i < 25; i++) {
    for (int x = 0; x < 37; x++) {
      binary[i][x] = 2;
    }
  }
 }
 
 public void paint(Graphics g) {
  
  Graphics2D g2D = (Graphics2D) g;

  
  g2D.setPaint(Color.black);
  g2D.fillRect(0, 0, 1300, 800);
  if (phase > 0) {
    g2D.setPaint(binaryColor);
    g2D.setFont(new Font("Times New Roman",Font.BOLD,100));
    if (text > 40) {
      g2D.setPaint(Color.red);
      g2D.drawString("John", 480, 400);
    } else if (text > 30) {
      g2D.setPaint(Color.orange);
      g2D.drawString("Is", 480, 400);
    } else if(text > 20) {
      g2D.setPaint(Color.yellow);
      g2D.drawString("Cool", 480, 400);
    }
    g2D.setFont(new Font("Arial",Font.BOLD,35));
    for (int i = 0; i < 25; i++) {
      for (int x = 0; x < 37; x++) {
        if (binary[i][x] != 2) {
          g2D.drawString("" + binary[i][x],(35 * x) + 9,35 * i);
        }
      }
    }
  }
    //Ball Tracer
    //g2D.setPaint(Color.white);
    g2D.setStroke(new BasicStroke(5));
    for (int i  = 0; i < ball.bounceX.size(); i ++) {
      //g2D.drawLine(ball.x + 5, ball.y + 5, ball.bounceX.get(i), ball.bounceY.get(i));//Line drawer
      lineColor = 255 - ((ball.bounceX.size() - i) * 3);
      if (lineColor <= 0) {
        lineColor = 1;
      }
      g2D.setPaint(new Color(255 - lineColor, lineColor, 1));
      g2D.drawOval(ball.bounceX.get(i) - 10, ball.bounceY.get(i) - 10, 20, 20);
      if (ball.bounceX.size() > 1 && lineType) {
        if (i < ball.bounceX.size() - 1) {
          g2D.drawLine(ball.bounceX.get(i), ball.bounceY.get(i), ball.bounceX.get(i + 1), ball.bounceY.get(i + 1));
        } else {
          g2D.drawLine(ball.bounceX.get(i), ball.bounceY.get(i), ball.x + 5, ball.y + 5);
        }
      } else {
        g2D.drawLine(ball.bounceX.get(i), ball.bounceY.get(i), ball.x + 5, ball.y + 5);
      }
    }
    //g2D.drawLine(ball.x + 5, ball.y + 5, 0, 0);//Line drawer
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
  g2D.drawString("PONG",535,50);
  g2D.setFont(new Font("Comic Sans MS",Font.BOLD,15));
  g2D.drawString("SCORE: " + ball.hits,580,67);
  g2D.drawString("SPEED",683,15);
  g2D.drawString("" + ball.vertSpeed + " / " + ball.speed,685,30);
  g2D.drawString("NEXT: " + ball.increase,683,45);
  if (lost) {
    g2D.setFont(new Font("Comic Sans MS",Font.BOLD,75));
    g2D.drawString("GAME OVER",425,400);
  }
 }
  @Override
	public void actionPerformed(ActionEvent e) {
    if (!lost) {
      ball.advance(leftP.y,rightP.y,phase);//Use paddles later
      leftP.advance(ball.y, ball.left,ball.vertSpeed);
      if (move == 1) {
        rightP.y -= 4;
      } else if (move == 2) {
        rightP.y += 4;
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
        if (ball.hits >= 10 && phase == 0) {
          phase = 1;
        } else if (ball.hits >= 30 && phase == 1) {
          phase = 2;
        } else if (ball.hits >= 40 && phase == 2) {
          phase = 3;
        }
        if (phase == 3) {
          if (lineType) {
            lineType = false;
          } else {
            lineType = true;
          }
        }
      }
    }
    if (ball.x > 1300 && !lost) {
      lost = true;
    } else if (ball.x < 0 && !lost) {
      lost = true;
    }
    if (phase == 1) {
      for (int i = 0; i < 37; i++) {
        binary[(int) (Math.random() * 25)][i] = (int) (Math.random() * 2);
      } 
    } else if (phase > 1 && somethingBin) {
      somethingBin = false;
      for (int i = 0; i < 37; i++) {
        binary[(int) (Math.random() * 25)][i] = 2;
        for (int b = 0; b < 25; b++) {
          if (binary[b][i] != 2) {
            somethingBin = true;
          }
        }
      }
    }
    repaint();
  }
}