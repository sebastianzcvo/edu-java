package sorting.comparableAndComparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AppleComparator implements Comparator<Apple> {
    @Override
    public int compare(Apple apple1, Apple apple2) {
        return apple1.compareTo(apple2);
    }

    public static void main(String[] args) {
        Apple[] arr = {
                new Apple(3, 1, Apple.Variety.RED, Apple.Origin.IMPORTED),
                new Apple(2, 1, Apple.Variety.RED, Apple.Origin.IMPORTED),
                new Apple(1, 1, Apple.Variety.RED, Apple.Origin.IMPORTED)
        };
        Arrays.sort(arr, new AppleComparator());
        System.out.println(Arrays.toString(arr));

        List<Apple> list = Arrays.asList(arr);
        list.sort(Comparator.reverseOrder());
        System.out.println(list);
    }
}
