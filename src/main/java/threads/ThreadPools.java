package threads;

import java.util.concurrent.*;

public class ThreadPools {
    public static void main(String[] args) {
        // size: amount of alive threads
        // keepAliveTime: time a thread has to be idle to be killed
        singleThreadedExecutor(); // implements LinkedBlockingQueue, fixed size (core=max=1), 0 keepAliveTime.
        fixedThreadPool(); // implements LinkedBlockingQueue, fixed size (core=max), 0 keepAliveTime.
        cachedThreadPool(); // implements SynchronousQueue, dynamic size (core=0,max=Integer.MAX_VALUE), 60s keepAliveTime.
        scheduledThreadPool(); // implements DelayWorkQueue, dynamic size (core=fixed,max=Integer.MAX_VALUE), 10ms keepAliveTime.
        singleThreadScheduledExecutor();

        customThreadPoolExecutor();

//        pool.shutdown(); // Initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks will be accepted. This method does not wait for previously submitted tasks to complete execution. Use awaitTermination to do that.
//        pool.shutdownNow(); // Attempts to stop all actively executing tasks, halts the processing of waiting tasks, and returns a list of the tasks that were awaiting execution. This method does not wait for actively executing tasks to terminate. Use awaitTermination to do that.
//        boolean terminatedBeforeTimeout = pool.awaitTermination(10, TimeUnit.SECONDS); // Blocks until all tasks have completed execution after a shutdown request, or the timeout occurs, or the current thread is interrupted, whichever happens first.
    }

    private static void singleThreadedExecutor() {
        // Tasks are guaranteed to execute sequentially, and no more than one task will be active at any given time
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(()-> System.out.println("singleThreadExecutor"));
    }

    private static void fixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        fixedThreadPool.execute(()-> System.out.println("fixedThreadPool"));

        // for cpu intensive tasks pool size = available processors
        ExecutorService s1 = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        // for io intensive pool size could be bigger, because of many threads waiting for OS response
        ExecutorService s2 = Executors.newFixedThreadPool(99);
    }

    private static void cachedThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(()-> System.out.println("cachedThreadPool"));
    }

    private static void scheduledThreadPool() {
        // executes after initial delay, and could be re-executed at fixedRate or with fixedDelay between the termination of one execution and the commencement of the next
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        scheduledThreadPool.schedule(()-> System.out.println("scheduledThreadPool"), 0, TimeUnit.SECONDS);
        scheduledThreadPool.scheduleAtFixedRate(() -> System.out.println("scheduledThreadPool"), 0, 5, TimeUnit.SECONDS);
        scheduledThreadPool.scheduleWithFixedDelay(() -> System.out.println("scheduledThreadPool"), 0, 5, TimeUnit.SECONDS);
    }

    private static void singleThreadScheduledExecutor() {
        ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        singleThreadScheduledExecutor.schedule(()-> System.out.println("singleThreadScheduledExecutor"), 0, TimeUnit.SECONDS);
        singleThreadScheduledExecutor.scheduleAtFixedRate(() -> System.out.println("singleThreadScheduledExecutor"), 0, 5, TimeUnit.SECONDS);
        singleThreadScheduledExecutor.scheduleWithFixedDelay(() -> System.out.println("singleThreadScheduledExecutor"), 0, 5, TimeUnit.SECONDS);
    }

    private static void customThreadPoolExecutor() {
        ExecutorService executorService = new ThreadPoolExecutor(
                0,
                3,
                0,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));
    }
}
