package producercosumer.pro_con_with_wait_notify;

import java.util.LinkedList;
import java.util.List;
import producercosumer.waitnotifydemo.WaitNotifyDemo;

public class ProducerConsumerWithWaitNotifyDemo {

    private List<Integer> list = new LinkedList<Integer>();
    private static final int LIMIT = 10;

    private Object lock = new Object();


    public void producer() throws InterruptedException {
        int value = 0;
        while (true) {

            synchronized (lock) {
                if (list.size() == LIMIT) {
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
        }
    }

    public void consumer() throws InterruptedException {

        while (true) {
            synchronized (lock) {
                if (list.size() == 0) {
                    lock.wait();
                }
                int number = list.remove(0);
                lock.notify();
                System.out.println(list.size() + " - " + number);
            }

            Thread.sleep(1000);

        }

    }

    public static void main(String[] args) {

        new ProducerConsumerWithWaitNotifyDemo().doWord();

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
}
