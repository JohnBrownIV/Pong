public class Paddle {
  int y;
  Paddle() {
    y = 350;
  }
  public void advance(int inY, boolean left, int speed) {
    if (left) {
      if (inY > y + 80) {
      y += speed + 1;
      }
      if (inY < y + 20) {
        y -= speed + 1;
      }
      if (y + 100 > 800) {
        y = 700;
      } else if (y < 0) {
        y = 0;
      }
    } else if (y > 350) {
      y -= 2;
    } else if (y < 350) {
      y += 2;
    }
  }
}