package synchronizeddemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * synchronized added to increment method definition
 */
public class WithSynchronization {

    private int count = 0;

    public static void main(String[] args) throws InterruptedException {

        new WithSynchronization().doWork();
    }

    private synchronized void increment() {
        count++;
    }

    private void doWork() throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();
//                    System.out.println(Thread.currentThread().getName() + " - " + count);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();
//                    System.out.println(Thread.currentThread().getName() + " - " + count);
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(count);
    }
}
