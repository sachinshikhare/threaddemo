package executorsvcdemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Processor implements Runnable {

    private int id;

    public Processor(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Processing starts for: " + id);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Processing ends for: " + id);

    }
}

class MainClass {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i <10; i++) {
            executorService.submit(new Processor(i));
        }
        executorService.shutdown();
        System.out.println("All tasks submitted");
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("All tasks completed");
    }
}
