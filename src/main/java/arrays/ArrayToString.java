package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ArrayToString {
    public static void main(String[] args) {
        arraysToString();
        stringJoin();
        collectorsJoining();
    }

    private static void arraysToString() {
        String[] arr = {"a", "b", "c"};
        String str = Arrays.toString(arr); // [a, b, c]
        System.out.printf("arrayToString %s%n", str);
    }

    private static void stringJoin() {
        String[] arr = {"a", "b", "c"};
        String str = String.join("-", arr); // a-b-c
        System.out.printf("stringJoin %s%n", str);
    }

    private static void collectorsJoining() {
        String[] arr = {"a", "b", "c"};
        String str = Arrays.stream(arr).collect(Collectors.joining("-")); // a-b-c
        System.out.printf("collectorsJoining %s%n", str);
    }
}
