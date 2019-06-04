package synchronizeddemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * the problem and why synchronization is required.
 * adding sout does gives correct output but its not solution
 */
public class WithoutSynchronization {

    private int count = 0;

    public static void main(String[] args) throws InterruptedException {

        new WithoutSynchronization().doWork();
    }

    private void doWork() throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    count++;
//                    System.out.println(Thread.currentThread().getName() + " - " + count);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    count++;
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
