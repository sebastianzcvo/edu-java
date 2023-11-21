package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Synchronized { // https://www.youtube.com/watch?v=8sgDgXUUJ68 https://www.youtube.com/watch?v=4I0KrGqGxOE https://www.youtube.com/watch?v=MWlqrLiscjQ

    public static void main(String[] args) {
        // For multithreaded applications, we need to ensure a couple of rules for consistent behaviour:
        // - Mutual Exclusion – only one thread executes a critical section at a time (atomicity - not-interleaving)
        // - Visibility – changes made by one thread to the shared data are visible to other threads to maintain data consistency
        // synchronized methods and blocks provide both of the above properties at the cost of application performance. volatile is quite a useful keyword because it can help ensure the visibility aspect of the data change without providing mutual exclusion. Thus, it’s useful where we’re ok with multiple threads executing a block of code in parallel, but we need to ensure the visibility property

        // every java object is a monitor (has an intrinsic-lock and a wait set). When a thread invokes a synchronized method/block, it attempts to acquire the lock of the monitor object for that method/block, if the lock is already acquired the thread is put in the wait set and waits. When the lock is released one of the threads in the wait set is chosen to acquire the lock and execute the method/block
        // nonstatic synchronized methods use 'this' object as monitor object: synchronized void method() {...}
        // static synchronized methods use the .class object as monitor object: synchronized static void method() {...}
        // synchronized blocks require to specify the monitor object: synchronized (obj) {...}

        // synchronized keyword is not allowed on: constructor methods, abstract methods nor inside an interface
        // by convention synchronized keyword goes after access modifier, and before static modifier and return type: private synchronized static void method() {...}
        new NotSynchronized().exec(); // stage1() and stage2() are not synchronized methods
        new SynchronizedMethodsOnSameMonitor().exec(); // stage1() and stage2() are synchronized methods, i.e. SAME monitor object
        new SynchronizedBlocksOnDifferentMonitors().exec(); // stage1() contains a synchronized block on monitor1, and stage2() contains a synchronized block on monitor2, i.e. DIFFERENT monitor objects

        // synchronized keyword uses the intrinsic-lock of a monitor object, which is a reentrant-lock (a lock that can be locked again by the thread that holds the lock).
        // the same behavior can be obtained using an external locks like ReentrantLock (an implementation of Lock interface that allows reentrancy)
        new SynchronizedOnSameReentrantLock().exec();
        new SynchronizedOnDifferentReentrantLocks().exec();
    }

    public final List<Integer> list1 = new ArrayList<>();
    public final List<Integer> list2 = new ArrayList<>();

    public final Random random = new Random();

    abstract void stage1();

    abstract void stage2();

    private void process() {
        for (int i = 0; i < 10; i++) {// ~2000ms
            stage1();// takes ~100ms
            stage2();// takes ~100ms
        }
    }

    public void exec() {
        long start = System.currentTimeMillis();

        Thread t1 = new Thread(this::process);
        Thread t2 = new Thread(this::process);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long end = System.currentTimeMillis();

        System.out.println(this.getClass().getSimpleName());
        System.out.printf("time in ms: %d%n", end - start);
        System.out.printf("list1 size: %d%n", list1.size());
        System.out.printf("list2 size: %d%n", list2.size());
    }

    static class NotSynchronized extends Synchronized {
        @Override
        void stage1() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list1.add(random.nextInt(100));
        }

        @Override
        void stage2() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list2.add(random.nextInt(100));
        }
    }

    static class SynchronizedMethodsOnSameMonitor extends Synchronized {
        @Override
        synchronized void stage1() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list1.add(random.nextInt(100));
        }

        @Override
        synchronized void stage2() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list2.add(random.nextInt(100));
        }
    }

    static class SynchronizedBlocksOnDifferentMonitors extends Synchronized {
        private final Object monitor1 = new Object();
        private final Object monitor2 = new Object();

        @Override
        void stage1() {
            synchronized (monitor1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                list1.add(random.nextInt(100));
            }
        }

        @Override
        void stage2() {
            synchronized (monitor2) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                list2.add(random.nextInt(100));
            }
        }
    }

    static class SynchronizedOnSameReentrantLock extends Synchronized {
        private final Lock lock = new ReentrantLock();

        @Override
        void stage1() {
            try {
                lock.lock();
                Thread.sleep(100);
                list1.add(random.nextInt(100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

        @Override
        void stage2() {
            try {
                lock.lock();
                Thread.sleep(100);
                list2.add(random.nextInt(100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }

    static class SynchronizedOnDifferentReentrantLocks extends Synchronized {
        private final Lock lock1 = new ReentrantLock();
        private final Lock lock2 = new ReentrantLock();

        @Override
        void stage1() {
            try {
                lock1.lock();
                Thread.sleep(100);
                list1.add(random.nextInt(100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock1.unlock();
            }
        }

        @Override
        void stage2() {
            try {
                lock2.lock();
                Thread.sleep(100);
                list2.add(random.nextInt(100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock2.unlock();
            }
        }
    }
}
