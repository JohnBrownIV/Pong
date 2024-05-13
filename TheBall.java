public class TheBall {
  int x;
  int y;
  boolean up;
  boolean left;
  int hits;

  TheBall() {
    x = 100;
    y = 100;
    up = false;
    left = false;
    hits = 0;
  }
  public void advance(int leftP, int rightP) { //left and right are paddle coordinates
    if (y + 10 > 800 && !up) {
      up = true;
    }
    if (x + 10 > 1250 && !left) {//right Check
      left = true;
      hits++;
    }
    if (y < 0 && up) {
      up = false;
    }
    if (x < 60 && left) {//left check
      if (y > leftP && y < leftP + 100) {
        left = false;
        hits++;
      }
    }
    if (up) {
      y -= 2;
    } else {
      y += 2;
    }
    if (left) {
      x -= 2;
    } else {
      x += 2;
    }
  }
}