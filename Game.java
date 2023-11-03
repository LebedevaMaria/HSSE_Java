import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class Game {
  public static int[] players = new int[2];
  public static int[] numbers = new int[101];

  public static class Player implements Runnable {
    private final String namePlayer;

    public Player(String namePlayer, CountDownLatch latch) {
      this.namePlayer = namePlayer;
      this.latch = latch;
    }

    private final CountDownLatch latch;

    @Override
    public void run() {
      while (latch.getCount() > 0) {
        try {
          int newNumber = 1 + (int) Math.floor(Math.random() * 100);
          if (numbers[newNumber] == 0) {
            numbers[newNumber] = 1;
            if (Objects.equals(namePlayer, "first")) {
              players[0] += 1;
            } else {
              players[1] += 1;
            }
          }
          Thread.sleep(30);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          return;
        }
        latch.countDown();
      }
    }
  }

  public static class Judge implements Runnable {
    private final CountDownLatch latch;

    public Judge(CountDownLatch latch) {
      this.latch = latch;
    }

    @Override
    public void run() {
      try {
        latch.await();
        if (players[0] > players[1]) {
          System.out.println("First is the best!");
        } else if (players[0] < players[1]) {
          System.out.println("Second is the best!");
        } else {
          System.out.println("Well done everyone!");
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        return;
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    var executor = Executors.newCachedThreadPool();
    var latch = new CountDownLatch(100);
    executor.submit(new Player("first", latch));
    executor.submit(new Player("second", latch));
    executor.submit(new Judge(latch));
    executor.shutdown();
  }
}
