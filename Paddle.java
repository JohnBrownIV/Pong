public class Paddle {
  int y;
  Paddle() {
    y = 100;
  }
  public void advance(int inY) {
    if (inY > y + 80) {
      y += 3;
    }
    if (inY < y + 20) {
      y -= 3;
    }
    if (y + 100 > 800) {
      y = 700;
    } else if (y < 0) {
      y = 0;
    }
  }
}