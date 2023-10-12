package streamsAndLambdas;

import java.util.List;
import java.util.function.Consumer;

public class LambdaExceptions {
    public static void main(String[] args) {
        List.of("1", "2", "3", "text")
                .forEach(exceptionHandler(s -> System.out.println(Integer.parseInt(s))));
    }

    static Consumer<String> exceptionHandler(Consumer<String> consumer) {
        return obj -> {
            try {
                consumer.accept(obj);
            } catch (NumberFormatException e) {
                System.err.println("caught exception");
            }
        };
    }
}
