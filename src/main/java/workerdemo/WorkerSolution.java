package workerdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 1. stageOne and stageTwo are not synchronized... that caused --> these method wont run with atomicity
 * 2. When synchronized added on these 2 methods, we got required output but execution took more that double time
 * To resolve this problem please refer--> WorkerSolution.java
 * ---> To resolve this issue, we locked two methods on 2 different lock object so that they can be locked and executed independently.
 */
public class WorkerSolution{

    Random random = new Random();

    private List<Integer> list1 = new ArrayList<Integer>();
    private List<Integer> list2 = new ArrayList<Integer>();

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private void stageOne() throws InterruptedException {
        Thread.sleep(2);
        synchronized (lock1) {
            list1.add(random.nextInt());
        }
    }

    private void stageTwo() throws InterruptedException {
        Thread.sleep(2);
        synchronized (lock2) {
            list2.add(random.nextInt());
        }
    }

    private void process() throws InterruptedException {
        for (int i = 0; i <1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void doWork() throws InterruptedException {

        long start = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    process();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    process();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        //.join required otherwise, main thread wont wait for worker thread to finish
        t1.join();
        t2.join();

        long end = System.currentTimeMillis();

        System.out.println("Time taken:" + (end-start));
        System.out.println("list1.size(): "+ list1.size());
        System.out.println("list2.size(): "+ list2.size());
    }
}
