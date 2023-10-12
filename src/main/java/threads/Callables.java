package threads;

import java.util.concurrent.*;

public class Callables {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newSingleThreadExecutor();

        Future<String> f1 = pool.submit(() -> {
            System.out.println("implementing Callable<String> with lambda");
            return null;
        });

        Future<Boolean> f2 = pool.submit(new MyCallable());

        Future<Integer> f3 = pool.submit(new Callable<Integer>() {
            @Override
            public Integer call() {
                System.out.println("implementing Callable<Integer> with anonymous local class");
                return 0;
            }
        });

        System.out.println(f1.get());
        System.out.println(f2.get());
        System.out.println(f3.get());

        pool.shutdown();
    }

    static class MyCallable implements Callable<Boolean> {
        @Override
        public Boolean call() {
            System.out.println("implementing Callable<Boolean>");
            return false;
        }
    }
}
