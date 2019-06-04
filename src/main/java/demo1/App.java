package demo1;

class RunnerThreadExtended extends Thread {

    @Override
    public void run() {
        for (int cntr = 0; cntr < 10; cntr++) {
            System.out.println("Thread: " +  Thread.currentThread().getName() + ", Counter: " + cntr);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class RunnerRunnableImplemented implements Runnable {

    public void run() {
        for (int cntr = 0; cntr < 10; cntr++) {
            System.out.println("Thread: " +  Thread.currentThread().getName() + ", Counter: " + cntr);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class App {
    public static void main(String[] args) {
        new RunnerThreadExtended().start();
        new Thread(new RunnerRunnableImplemented()).start();
        new RunnerThreadExtended().start();
        new Thread(new RunnerRunnableImplemented()).start();

        new Thread(new Runnable() {
            public void run() {
                for (int cntr = 0; cntr < 10; cntr++) {
                    System.out.println("Thread: " +  Thread.currentThread().getName() + ", Counter: " + cntr);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
