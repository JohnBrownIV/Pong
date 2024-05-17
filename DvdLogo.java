public class DvdLogo {
  int x;
  int y;
  boolean up;
  boolean left;
  DvdLogo() {
    x = 400;
    y = 400;
    up = true;
    left = true;
  }
  public void advance() {
    if (x < 0) {
      left = false;
    } else if (x + 460 > 1300) {
      left = true;
    }
    if (y < 0) {
      up = false;
    } else if (y + 208 > 800) {
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