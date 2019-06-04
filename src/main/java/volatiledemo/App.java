package volatiledemo;

import java.util.Scanner;

class Processor extends Thread {

    // running should be declared as volatile.
    private volatile boolean running = true;

    @Override
    public void run() {

        while (running) {
            System.out.println("Hello");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopRunning() {

        running = false;
    }
}

public class App {
    public static void main(String[] args) {

        Processor processor = new Processor();
        processor.start();
        System.out.println("Hit enter to exit...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        processor.stopRunning();
    }
}