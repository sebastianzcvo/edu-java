package collections;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Lists {

    public static void main(String[] args) {
        list(); // Indexed elements.

        arrayList(); // Implements an array. Best for random access.
        linkedList(); // Implements a double-linked list. Best for mutable list.
        vector(); // Implements a thread-safe array.
        stack(); // Implements a thread-safe array. push(), pop(), peak().
    }

    private static void list() {
        List<String> list = new ArrayList<>();

        Object[] arr = list.toArray();
        List<String> sublist = list.subList(0, list.size());

        list.add("0");
        list.add(1, "1");
        list.addAll(List.of("2", "2"));
        list.addAll(4, List.of("3", "3")); // 0 <= index <= size. if (index < size) shifts elements to the right.
        list.set(0, "0");
        System.out.println(list); // [0, 1, 2, 2, 3, 3]

        list.indexOf("3"); // 4
        list.lastIndexOf("3"); // 5
        list.indexOf("666"); // -1
        list.contains("1"); // true
        list.containsAll(List.of("3", "4")); // false

        list.remove(4); // 0 <= index < size. if (index < size-1) shifts elements to the left
        list.remove("1"); // removes first occurrence
        list.removeAll(List.of("1", "2")); // removes first occurrence of each
        list.retainAll(List.of("1", "2")); // removes all elements except for elements in the collection
        list.removeIf(s -> s.equals("666")); // removes if predicate is true
        list.replaceAll(s -> "666");
        list.clear(); // removes all
        list.isEmpty(); // true

        ListIterator<String> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) System.out.println(listIterator.previous());
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) System.out.println(iterator.next());
        for (String s : list) System.out.println(s);
        list.forEach(System.out::println);
    }

    private static void arrayList() {
        ArrayList<String> arrayList = new ArrayList<>(2);
        arrayList.ensureCapacity(7);
        arrayList.trimToSize();
    }

    private static void linkedList() {
        LinkedList<String> linkedList = new LinkedList<>();

        //deque
        linkedList.descendingIterator();
        linkedList.addFirst("1");
        linkedList.addLast("2");
        linkedList.getFirst();
        linkedList.getLast();
        linkedList.offer("1");
        linkedList.offerFirst("1");
        linkedList.offerLast("1");
        linkedList.element();
        linkedList.push("");
        linkedList.pop();
        linkedList.peek();
        linkedList.peekFirst();
        linkedList.peekLast();
        linkedList.poll();
        linkedList.pollFirst();
        linkedList.pollLast();

        System.out.println(linkedList);
    }

    private static void vector() {
        Vector<String> vector = new Vector<>(2);
        System.out.println(vector.capacity()); // 2
        vector.ensureCapacity(7);
        System.out.println(vector.capacity()); // 7
        vector.trimToSize();
        System.out.println(vector.capacity()); // 0
    }


    private static void stack() {
        Stack<String> stack = new Stack<>(); // doesn't support passing a collection throw constructor

        stack.push("0");
        stack.push("1");
        System.out.println(stack); // [0, 1] still indexed

        // gets object on top, doesn't remove it
        System.out.println(stack.peek()); // 1
        System.out.println(stack.peek()); // 1

        System.out.println("----------");
        Iterator<String> iterator = stack.iterator();
        while (iterator.hasNext()) System.out.println(iterator.next()); // iterates over index 0, 1
        for (String e : stack) System.out.println(e); // iterates over index 0, 1
        stack.forEach(System.out::println); // iterates over index 0, 1
        System.out.println("----------");

        // search based on stack position, not on index
        System.out.println(stack.search("1")); // 1 (on top of the stack)
        System.out.println(stack.search("0")); // 2 (second)
        System.out.println(stack.search("not existent")); // -1

        // removes object on top
        System.out.println(stack.pop()); // 1
        System.out.println(stack.pop()); // 0
    }

}

