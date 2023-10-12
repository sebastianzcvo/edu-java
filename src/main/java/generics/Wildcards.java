package generics;

import java.util.ArrayList;
import java.util.List;

public class Wildcards {

    public static void main(String[] args) {
        List<?> result1 = genericType(new ArrayList<>(List.of("1", 2)));

        List<?> result2 = unbounded(new ArrayList<>(List.of("1", 2)));

        List<? extends Middle> result3 =
                upperBounded(new ArrayList<>(List.of(new Middle(), new Lower())));

        List<? super Middle> result4 =
                lowerBounded(new ArrayList<>(List.of(new Middle(), new Upper()))); // also possible with Object() or Lower()*?*
    }

    // the only place we can use them is as part of a generic code. Ex: List<?>
    // wildcards make code simpler and more flexible
    // wildcards apply on methods only, not in classes
    // wildcards can't be used as type parameter
    static public <T> List<T> genericType(List<T> input) {
        return input;
    }

    static public List<?> unbounded(List<?> input) {
        return input;
    }

    static public List<? extends Middle> upperBounded(List<? extends Middle> input) {
        return input;
    }

    static public List<? super Middle> lowerBounded(List<? super Middle> input) {
        return input;
    }

    static class Upper {

    }

    static class Middle extends Upper {

    }

    static class Lower extends Middle {

    }

}
