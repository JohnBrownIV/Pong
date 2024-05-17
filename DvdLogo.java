public class DvdLogo {
  int x;
  int y;
  boolean up;
  boolean left;
  DvdLogo() {
    x = 300;
    y = 400;
    up = false;
    left = true;
  }
  public void advance() {
    if (x < 0) {
      left = false;
    } else if (x + 230 > 1300) {
      left = true;
    }
    if (y < 0) {
      up = false;
    } else if (y + 104 > 800) {
      up = true;
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