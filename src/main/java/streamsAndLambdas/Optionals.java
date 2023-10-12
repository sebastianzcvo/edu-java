package streamsAndLambdas;

import java.util.Optional;

public class Optionals {
    public static void main(String[] args) {
        Optional<String> optional = find();

        optional.get(); // ¡forGET!, same as orElseThrow()
        optional.orElseThrow(); // but at least orElseThrow() tells you the potential NoSuchElementException

        optional.orElse("default"); // gets the value, or provides a substitute value if empty
        optional.orElseGet(() -> "default"); // gets the value, or provides a substitute value if empty, using a Supplier
        optional.or(() -> Optional.of("default")); // gets the optional, or provides a substitute optional if empty, using a Supplier

        // not recommended approach since you want to avoid null checks
        optional.isEmpty();
        optional.isPresent();

        // more recommended approach
        optional.ifPresent(System.out::println);
        optional.ifPresentOrElse(System.out::println, () -> System.out.println("not value found"));

        // more recommended approach
        optional
                .filter(s -> s.equals("value"))
                .map(String::toUpperCase) // maps and automatically wraps it within an Optional
                .flatMap(e -> Optional.of(e.toLowerCase())) // similar to map, without automatic wrapping
                .ifPresentOrElse(System.out::println, () -> System.out.println("not value found"));
    }

    // intended to be used as a return type, to avoid null checks using a functional approach
    static Optional<String> find() {
//        return Optional.empty();
//        return Optional.of(null); // if null throws NullPointerException
//        return Optional.ofNullable(null); // if null creates an empty optional
        return Optional.of("value");
    }
}
