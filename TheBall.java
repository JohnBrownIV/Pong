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
      hits++;
    }
    if (x + 10 > 1300 && !left) {
      left = true;
      hits++;
    }
    if (y < 0 && up) {
      up = false;
      hits++;
    }
    if (x < 0 && left) {
      left = false;
      hits++;
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