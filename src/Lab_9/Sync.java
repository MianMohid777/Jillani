package Lab_9;

public class Sync {

    private int counter = 0;

    public synchronized void increment() {
        counter++;
        System.out.println("the counter is "+counter);
    }

    public synchronized int getCounter() {
        return counter;
    }

    public static void main(String[] args) {
        Sync counter = new Sync();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                counter.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                counter.increment();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final counter value: " + counter.getCounter());
    }
}

