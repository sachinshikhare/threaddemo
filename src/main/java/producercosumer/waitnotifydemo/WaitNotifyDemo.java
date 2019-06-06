package producercosumer.waitnotifydemo;

import java.util.Scanner;

public class WaitNotifyDemo {

    public static void main(String[] args) {

        new WaitNotifyDemo().doWord();

    }

    private void doWord() {

        new Thread(() -> {
            try {
                producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ).start();

        new Thread(() -> {
                try {
                    consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        ).start();
    }

    public void producer() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer running");
            wait();
            System.out.println("Producer resumed");
        }
    }

    public void consumer() throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);

        synchronized (this) {
            System.out.println("Wating for return key");
            scanner.nextLine();
            notify();
        }
    }
}
