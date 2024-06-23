package arrays;

import java.util.*;

public class CollectionToArray {
    public static void main(String[] args) {
        listToArray();
        setToArray();
    }

    private static void listToArray() {
        List<Integer> list = List.of(1, 2, 3);
        Integer[] arr = list.toArray(new Integer[0]);

        System.out.printf("listToArray: %s%n", Arrays.toString(arr));
    }

    private static void setToArray() {
        Set<String> set = Set.of("a", "b", "c");
        String[] arr = set.toArray(new String[0]);

        System.out.printf("setToArray: %s%n", Arrays.toString(arr));
    }
}
