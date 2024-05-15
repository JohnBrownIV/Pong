import java.util.ArrayList;
public class TheBall {
  int x;
  int y;
  int speed;
  int increase;
  int lastIncrease;
  boolean up;
  boolean left;
  int hits;
  boolean beenHit;
  ArrayList<Integer> bounceX;
  ArrayList<Integer> bounceY;

  TheBall() {
    bounceX = new ArrayList<Integer>();
    bounceY = new ArrayList<Integer>();
    x = 650;
    y = (int) (Math.random() * 800);;
    speed = 2;
    increase = 1;
    lastIncrease = 0;
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
      y -= 2;
    } else {
      y += 2;
    }
    if (left) {
      x -= speed;
    } else {
      x += speed;
    }
    if (increase <= 0) {
      speed++;
      increase = lastIncrease + 2;
      lastIncrease = increase;
    }
  }
}