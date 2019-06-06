package countdownlatchdemo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class MainClass {

    public static void main(String[] args) {

        Random random = new Random();

        System.out.println(random.nextInt(10));

//        CountDownLatch latch = new CountDownLatch(3);
//
//        new Thread(new Waiter(latch)).start();
//        new Thread(new Processor(latch)).start();
    }
}

class Waiter implements Runnable {

    CountDownLatch latch;

    Waiter(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Waiter released");
    }
}

class Processor implements Runnable {

    CountDownLatch latch;

    Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            Thread.sleep(1000);
            latch.countDown();
            Thread.sleep(1000);
            latch.countDown();
            Thread.sleep(1000);
            latch.countDown();
            System.out.println("latch decremented 3 times");
            Thread.sleep(1000);
            latch.countDown();
            Thread.sleep(1000);
            latch.countDown();
            Thread.sleep(1000);
            latch.countDown();
            Thread.sleep(1000);
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Processor released");
    }
}