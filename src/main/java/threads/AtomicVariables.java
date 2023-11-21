package threads;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicVariables {
    public static void main(String[] args) {
        atomicInteger();
        atomicBoolean();
        atomicReference();
    }

    static void atomicInteger() {
        System.out.println("atomicInteger");
        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println(atomicInteger.get());
        atomicInteger = new AtomicInteger(1);
        System.out.println(atomicInteger.getAndSet(10));
        atomicInteger.set(20);

        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.incrementAndGet());
        System.out.println(atomicInteger.getAndDecrement());
        System.out.println(atomicInteger.decrementAndGet());

        System.out.println(atomicInteger.compareAndSet(20, 30));
        System.out.println(atomicInteger.compareAndExchange(30, 40));
        System.out.println(atomicInteger.get());

        System.out.println(atomicInteger.addAndGet(5));
        System.out.println(atomicInteger.getAndAdd(-5));
    }

    static void atomicBoolean() {
        System.out.println("atomicBoolean");
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        System.out.println(atomicBoolean.get());
        atomicBoolean = new AtomicBoolean(true);
        System.out.println(atomicBoolean.getAndSet(false));
        atomicBoolean.set(true);

        System.out.println(atomicBoolean.compareAndSet(true, false));
        System.out.println(atomicBoolean.compareAndExchange(false, true));
        System.out.println(atomicBoolean.get());
    }

    static void atomicReference() {
        System.out.println("atomicReference");
        AtomicReference<Object> atomicReference = new AtomicReference<>();
        System.out.println(atomicReference.get());
        atomicReference = new AtomicReference<>("str1");
        System.out.println(atomicReference.getAndSet("str2"));
        atomicReference.set("str3");

        System.out.println(atomicReference.compareAndSet("str3", "str4"));
        System.out.println(atomicReference.compareAndExchange("str4", "str5"));
        System.out.println(atomicReference.get());
    }
}
