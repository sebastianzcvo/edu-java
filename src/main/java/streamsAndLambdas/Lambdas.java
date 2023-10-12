package streamsAndLambdas;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Lambdas { // Lambdas don't create anonymous inner classes, because of DynamicInvoke since java 7
    public static void main(String[] args) {
        Set<Integer> numbers = Set.of(-1, 0, 9, 10);
        String customLambdas1 = numbers.stream()
                .filter(new MyPredicate.IntPredicate())
                .map(new IntToStringFunction())
                .collect(Collectors.joining());
        System.out.printf("customLambdas1: %s%n", customLambdas1);

        Map<Integer, String> map = Map.of(-1, "str", 0, "str", 9, "str", 10, "str");
        String customLambdas2 = map.entrySet().stream()
                .filter(new MyPredicate.EntryPredicate())
                .map(entry -> "(" + entry.getKey() + "=" + entry.getValue() + ")")
                .collect(Collectors.joining("-"));
        System.out.printf("customLambdas2: %s%n", customLambdas2);
    }

    @FunctionalInterface
    interface MyPredicate<T> extends Predicate<T> {
        boolean test(T t);

        class IntPredicate implements MyPredicate<Integer> {
            @Override
            public boolean test(Integer t) {
                return 0 <= t && t <= 9;
            }
        }

        class EntryPredicate implements MyPredicate<Map.Entry<Integer, String>> {
            @Override
            public boolean test(Map.Entry<Integer, String> entry) {
                return 0 <= entry.getKey() && entry.getKey() <= 9;
            }
        }
    }

    static class IntToStringFunction implements Function<Integer, String> {
        @Override
        public String apply(Integer integer) {
            return integer.toString();
        }
    }
}
