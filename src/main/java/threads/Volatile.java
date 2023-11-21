package threads;

// https://www.youtube.com/watch?v=71dgtPrbToE https://www.baeldung.com/java-volatile
// apply volatile to fields to ensure visibility: updates to variables propagate predictably to other threads. This way, we can communicate with runtime and processor to:
//    immediately flush to main memory any updates to these variables, and read directly from main memory.
//    and to not reorder any instruction involving the volatile variable
// although, it only ensures visibility, not mutual exclusion, so multiple threads can still update the variable at the same time. To ensure both use atomic variables, synchronized blocks/methods or locks

// happens-before principle in memory model ensures that: the values that were visible to "thread-A" before writing the volatile variable will be visible to "thread-B" after reading the volatile variable

public class Volatile { // Many may expect this program to print 42 after a short delay; however, the delay may be much longer. It may even hang forever or print zero (if not using volatile)
    private /*volatile */static int number; // Piggybacking: Because of the strength of the happens-before memory ordering, sometimes we can piggyback on the visibility properties of another volatile variable: even though it’s not a volatile variable, it’s exhibiting a volatile behaviour
    private volatile static boolean ready;

    private static class Reader extends Thread {

        @Override
        public void run() {
            while (!ready) { // reading volatile
                Thread.yield(); // after reading volatile (happens-before principle)
            } // after reading volatile
            // after reading volatile
            System.out.println(number);//after
        }
    }

    public static void main(String[] args) {
        new Reader().start(); // before writing volatile
        number = 42; // before writing volatile
        ready = true; // writing volatile (happens-before principle)
    }
}
