package threads;

public class Signaling {

    public static void main(String[] args) {
        Thread waiter = new Thread(Signaling::doWait);
        Thread notifier = new Thread(Signaling::doNotify);

        waiter.start();
        notifier.start();

        // Signaling.class.wait(); throws IllegalMonitorStateException when outside synchronized block
        // Signaling.class.notify(); throws IllegalMonitorStateException when outside synchronized block
    }

    public synchronized static void doWait() {
        try {
            System.out.println("waiter acquires the lock");
            System.out.println("waiter releases the lock and waits");
            Signaling.class.wait(); // current thread is paused and the lock is released
            System.out.println("waiter reacquires the lock and continues");
            System.out.println("waiter releases the lock");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized static void doNotify(){
        System.out.println("notifier acquires the lock");
        System.out.println("notifier notifies");
        Signaling.class.notify(); // notifies one waiting thread to reacquire the lock after current thread had released it
//        Signaling.class.notifyAll(); // notifies all waiting threads
        System.out.println("notifier continues");
        System.out.println("notifier releases the lock");
    }
}
