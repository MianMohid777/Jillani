package Lab_9;

import java.util.concurrent.Semaphore;

public class ThreadCreation{

    private static final int MAX_NUMBER = 10;
    private final Semaphore semaphoreEven = new Semaphore(0);
    private final Semaphore semaphoreOdd = new Semaphore(1);

    private void printEvenNumbers() {
        for (int i = 2; i <= MAX_NUMBER; i += 2) {
            try {
                semaphoreEven.acquire();
                System.out.println("Even: " + i);
                semaphoreOdd.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printOddNumbers() {
        for (int i = 1; i < MAX_NUMBER; i += 2) {
            try {
                semaphoreOdd.acquire();
                System.out.println("Odd: " + i);
                semaphoreEven.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ThreadCreation printer = new ThreadCreation();

        Thread threadEven = new Thread(printer::printEvenNumbers);
        Thread threadOdd = new Thread(printer::printOddNumbers);
        threadOdd.start();
        threadEven.start();

        threadOdd.join();
        threadEven.join();

    }


}
