import java.util.ArrayList;
public class TheBall {
  int x;
  int y;
  int speed;
  int vertSpeed;
  int increase;
  int lastIncrease;
  boolean up;
  boolean left;
  int hits;
  boolean beenHit;
  ArrayList<Integer> bounceX;
  ArrayList<Integer> bounceY;
  boolean whichSpeed;

  TheBall() {
    bounceX = new ArrayList<Integer>();
    bounceY = new ArrayList<Integer>();
    x = 650;
    y = (int) (Math.random() * 800);;
    speed = 2;
    vertSpeed = 2;
    increase = 1;
    lastIncrease = 0;
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
  public void advance(int leftP, int rightP, int phase) { //left and right are paddle coordinates
    if (y + 10 > 800 && !up) {
      up = true;
      beenHit = true;
    }
    if (x + 10 > 1250 && !left) {//right Check
      if (y > rightP && y < rightP + 100) {
        left = true;
        hits++;
        increase--;
        beenHit = true;
      }
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
      }
    }
    if (beenHit && phase > 1) {
      bounceX.add(x);
      bounceY.add(y);
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
    if (increase <= 0) {
      if (whichSpeed) {
        speed++;
        vertSpeed--;
      } else {
        vertSpeed++;
        speed--;
      }
      if (speed > 5) {
        whichSpeed = false;
      } else if (vertSpeed > 5) {
        whichSpeed = true;
      }
      if (speed < 2) {
        speed = 2;
      }
      if (vertSpeed < 2) {
        vertSpeed = 2;
      }
      increase = lastIncrease + 1;
      lastIncrease = increase;
    }
  }
}