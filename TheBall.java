import java.util.ArrayList;
public class TheBall {
  int x;
  int y;
  int speed;
  int vertSpeed;
  int increase;
  boolean up;
  boolean left;
  int hits;
  boolean beenHit;
  ArrayList<Integer> bounceX;
  ArrayList<Integer> bounceY;
  boolean whichSpeed;
  int ran;

  TheBall() {
    bounceX = new ArrayList<Integer>();
    bounceY = new ArrayList<Integer>();
    x = 650;
    y = (int) (Math.random() * 800);;
    speed = 2;
    vertSpeed = 2;
    increase = 1;
    whichSpeed = true;
    if (y < 400) {
      up = false;
    } else {
      up = true;
    }
    left = false;
    hits = 0;
    beenHit = false;
  }
  public void advance(int leftP, int rightP, int phase, int move1, int move2) { //left and right are paddle coordinates
    boolean paddled = false;
    if (y + 10 > 800 && !up) {
      up = true;
      beenHit = true;
    }
    if (y < 0 && up) {
      up = false;
      beenHit = true;
    }
    if (x < 60 && left) {//left check
      if (y > leftP && y < leftP + 100) {
        left = false;
        hits++;
        increase--;
        beenHit = true;
        paddled = true;
        if (move1 == -1) {
          up = false;
        } else if (move1 == 1) {
          up = true;
        }
      }
    }
    if (x + 10 > 1250 && !left) {//right Check
      if (y > rightP && y < rightP + 100) {
        left = true;
        hits++;
        increase--;
        beenHit = true;
        paddled = true;
        if (move2 == -1) {
          up = false;
        } else if (move2 == 1) {
          up = true;
        }
      }
    }
    if (beenHit && phase > 0) {
      bounceX.add(x + 5);
      bounceY.add(y + 5);
    }
    if (up) {
      y -= vertSpeed;
    } else {
      y += vertSpeed;
    }
    if (left) {
      x -= speed;
    } else {
      x += speed;
    }
    if (beenHit) {
      ran = (int) (Math.random() * 3);
      if (ran == 0) { //whichSpeed if doing old way
        speed++;
      } else if (ran == 1) {
        vertSpeed++;
      } else if (ran == 2) {
        if ((int) (Math.random() * 2) == 0) {
          if (hits < 60) {
            speed--;
          } else {
            vertSpeed++;
          }
        } else {
          if (hits < 60) {
            vertSpeed--;
          } else {
            vertSpeed++;
          }
        }
      }
      if (speed < 2) {
        speed = 3;
      }
      if (vertSpeed < 2) {
        vertSpeed = 3;
      }
      increase = 5;
    }
  }
}