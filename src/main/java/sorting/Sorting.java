package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sorting {
    public static void main(String[] args) {
        String[] arr = {"c", "a", "b"};
        Arrays.sort(arr, String::compareTo);
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr, Collections.reverseOrder());
        System.out.println(Arrays.toString(arr));

        List<Integer> list = new ArrayList<>(List.of(3, 1, 2));
        list.sort(Integer::compareTo);
        System.out.println(list);
        list.sort(Collections.reverseOrder());
        System.out.println(list);
    }
}
