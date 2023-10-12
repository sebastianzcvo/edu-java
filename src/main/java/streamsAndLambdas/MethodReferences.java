package streamsAndLambdas;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MethodReferences {

    public static void main(String[] args) {
        oneParameterAsArgumentToAnInstanceMethod();
        oneParameterAsArgumentToAStaticMethod();

        twoParametersAsArgumentsToAnInstanceMethod();
        twoParametersAsArgumentsToAStaticMethod();

        oneParameterAsTarget();
        twoParametersOneAsTargetOtherAsArgument();
    }

    private static void oneParameterAsArgumentToAnInstanceMethod() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
//        numbers.forEach(e -> System.out.println(e));
        numbers.forEach(System.out::println);
    }

    private static void oneParameterAsArgumentToAStaticMethod() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        numbers.stream()
//                .map(e -> String.valueOf(e))
                .map(String::valueOf)
                .forEach(System.out::println);
    }

    private static void twoParametersAsArgumentsToAnInstanceMethod() {
        Map<String, String> map = Map.of("1", "1", "2", "2");
//        map.forEach((k, v) -> System.out.printf(k, v)); The order matters
        map.forEach(System.out::printf);
    }

    private static void twoParametersAsArgumentsToAStaticMethod() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        int result = numbers.stream()
//                .reduce(0, (total, e) -> Integer.sum(total, e)); The order matters
                .reduce(0, Integer::sum);
    }

    private static void oneParameterAsTarget() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        numbers.stream()
//                .map(e -> e.toString())
//                .map(Integer::toString) Reference to 'toString' is ambiguous, both 'toString(int)' and 'toString()' match
                .map(Object::toString)
                .forEach(System.out::println);
    }

    private static void twoParametersOneAsTargetOtherAsArgument() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        String result = numbers.stream()
                .map(String::valueOf)
//                .reduce("", (carry, e) -> carry.concat(e)); The order matters
                .reduce("", String::concat);
    }
}
