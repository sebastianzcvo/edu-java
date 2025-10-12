package threads.threadsAndCallables;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Callables {
    // 1 basic form of creation:
    // - implementing Callable<T>
    // 1 basic form of execution:
    // - pool.submit()
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Object> c1 = () -> {
            System.out.println("implementing Callable<String> with lambda");
            return null;
        };

        Callable<Boolean> c2 = new MyCallable();

        Callable<Integer> c3 = new Callable<Integer>() {
            @Override
            public Integer call() {
                System.out.println("implementing Callable<Integer> with anonymous local class");
                return 0;
            }
        };

        ExecutorService pool = Executors.newSingleThreadExecutor();
        Future<Object> f1 = pool.submit(c1);
        Future<Boolean> f2 = pool.submit(c2);
        Future<Integer> f3 = pool.submit(c3);
        pool.shutdown(); // required to exit the application

        System.out.println(f1.get()); // blocks and prints null
        System.out.println(f2.get()); // blocks and prints false
        System.out.println(f3.get()); // blocks and prints 0
    }

    static class MyCallable implements Callable<Boolean> {
        @Override
        public Boolean call() {
            System.out.println("implementing Callable<Boolean>");
            return false;
        }
    }
}
