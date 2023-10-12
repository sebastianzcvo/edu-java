package collections;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Queue {
    public static void main(String[] args) {
        blockingQueue();
        priorityQueue(); // Ordered by value using compareTo()
        arrayDeque(); // Indexed.
    }

    private static void blockingQueue() {
        BlockingQueue<Person> queue = new PriorityBlockingQueue<>();
        queue.offer(new Person(2));
        queue.offer(new Person(1));
        queue.offer(new Person(3));
        System.out.println(queue);
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue);
    }

    private static void priorityQueue() {
        PriorityQueue<Person> queue = new PriorityQueue<>();
        queue.offer(new Person(2));
        queue.offer(new Person(1));
        queue.offer(new Person(3));
        System.out.println(queue);
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue);
    }

    private static void arrayDeque() {
        ArrayDeque<String> deque = new ArrayDeque<>();
        deque.offer("2");
        deque.offer("1");
        deque.offer("3");
        System.out.println(deque);
        deque.offerFirst("0");
        deque.offerLast("4");
        System.out.println(deque);
        System.out.println(deque.peekFirst());
        System.out.println(deque.pollFirst());
        System.out.println(deque.peekLast());
        System.out.println(deque.pollLast());
    }

    static class Person implements Comparable<Person>{
        private final int priority;

        public Person(int priority) {
            this.priority = priority;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "priority=" + priority +
                    '}';
        }

        @Override
        public int compareTo(Person o) {
            return Integer.compare(priority, o.priority);
        }
    }

}
