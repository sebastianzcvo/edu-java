package threads;

import java.util.concurrent.TimeUnit;

// Threads can not be stopped abruptly, a stop() method doesn't exist
//   it would cause inconsistencies: data integrity, half atomic operations, leave open connections
// Interrupts are a cooperative mechanism to send a stop signal to a thread
//   it's cooperative because it's up to the receiver thread to handle the signal
public class Interruptions {

    public static void main(String[] args) throws InterruptedException {
        Thread sleeper = new Thread(Interruptions::doSleep);
        Thread waiter = new Thread(Interruptions::doWait);
        Thread joiner = new Thread(Interruptions::doJoin);
        Thread interruptHandler = new Thread(Interruptions::handleInterrupt);

        sleeper.start();
        waiter.start();
        joiner.start();
        interruptHandler.start();
        TimeUnit.SECONDS.sleep(1);
        sleeper.interrupt();
        waiter.interrupt();
        joiner.interrupt();
        interruptHandler.interrupt();
    }

    static void doSleep() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted while sleeping");
            return;
        }
    }

    synchronized static void doWait() {
        try {
            Interruptions.class.wait();
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted while waiting");
            return;
        }
    }

    static void doJoin() {
        Thread anotherSleeper = new Thread(Interruptions::doSleep);
        anotherSleeper.start();
        try {
            anotherSleeper.join();
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted while joining");
            return;
        }
    }

    static void handleInterrupt() {
        while (true) {
            if (Thread.interrupted()) { // Thread.interrupted() do both, checks interrupt state, without resetting it.
                Thread.currentThread().isInterrupted(); // Thread.currentThread().isInterrupted() checks interrupt state, without resetting it.
                System.out.println("Thread was interrupted and interruption was handled");
                return;
            }
        }
    }
}
