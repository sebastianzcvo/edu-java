package arrays;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayToList {
    public static void main(String[] args) {
        asList(); // mutable, fixed-size list. Modifications are shared bidirectionally (array<->list). Think of it as a view or a wrapper, not as a copy
        listOf(); // immutable list. Think of it as a copy
    }

    public static void asList() {
        System.out.println("asList -----------------");
        Integer[] arr = {1, 2, 3};
        List<Integer> list = Arrays.asList(arr);

        assertThrows(UnsupportedOperationException.class, () -> list.add(4));
        assertThrows(UnsupportedOperationException.class, () -> list.remove(4));

        arr[0] = 99;
        list.set(1, 99);

        System.out.printf("array: %s%n", Arrays.toString(arr));
        System.out.printf("list: %s%n", list);
    }

    private static void listOf() {
        System.out.println("listOf -----------------");
        Integer[] arr = {1, 2, 3};
        List<Integer> list = List.of(arr);

        assertThrows(UnsupportedOperationException.class, () -> list.add(4));
        assertThrows(UnsupportedOperationException.class, () -> list.remove(4));

        arr[0] = 99;
        assertThrows(UnsupportedOperationException.class, () -> list.set(1, 99));

        System.out.printf("array: %s%n", Arrays.toString(arr));
        System.out.printf("list: %s%n", list);
    }
}
