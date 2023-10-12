package threads;

public class Threads {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> System.out.println("implementing Runnable with lambda"));

        Thread t2 = new Thread(new MyRunnable());

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("implementing Runnable with anonymous local class");
            }
        });

        Thread t4 = new MyThread();

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("implementing Runnable");
        }
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("extending Thread");
        }
    }

}
