package threads.threadsAndCallables;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// https://www.youtube.com/watch?v=hN2Yrf4tqTY program > process > thread
// a process is the actual execution of a program, several processes can be associated to the same program
// a thread is an independent path of execution in a process, several threads can be associated to the same process. As long as there's one active thread, the process is still alive
//   a thread is the smallest sequence of instructions a scheduler can manage, the scheduler is a part of the OS that ensures efficient distribution/prioritization of processor time among threads

// each thread has dedicated resources like a stack and registries, and shared resources like the heap or files in disk
// inter-thread(same process) communication is faster than inter-process(different processes) communication, since threads in the same process share memory addresses
//   something to take into consideration when grouping threads in processes, e.g. in chrome each tab is an independent process, so if a thread fails, only one tab fails, not the entire browser

// parallelism: multiple threads executing at the same time. Each in a different processor/core
// concurrency: multiple threads executing but only one at the same time. It's common on synchronized access to shared resources, or when multiple threads are executed by the same processor
public class Threads {

    // 2 basic forms of creation:
    // - implementing Runnable and pass it to Thread constructor
    // - extending Thread class
    // 3 basic forms of execution:
    // - thread.start()
    // - pool.execute(runnable)
    // - pool.submit(runnable), or pool.submit(runnable, return)
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread t1 = new Thread(() -> System.out.println("implementing Runnable with lambda"));

        Thread t2 = new Thread(new MyRunnable());

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("implementing Runnable with anonymous local class");
            }
        });

        Thread t4 = new MyThread();

        Thread t5 = new Thread(){
            @Override
            public void run() {
                System.out.println("implementing Thread with anonymous local class");
            }
        };

        t1.start();
        t2.start();
        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.execute(t3);
        Future<?> f4 = pool.submit(t4);
        Future<Boolean> f5 = pool.submit(t5, true);
        pool.shutdown(); // required to exit the application

        System.out.println(f4.get()); // blocks and prints null
        System.out.println(f5.get()); // blocks and prints true
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
