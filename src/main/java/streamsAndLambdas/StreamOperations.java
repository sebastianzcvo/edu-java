package streamsAndLambdas;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

public class StreamOperations {
    public static void main(String[] args) {
        filter();
        limitAndTakeWhile();
        skipAndDropWhile();

        flatMap();
        reduce();
        totalPrice();

        // special reductions
        specialReductions();

        // mutable reductions: accumulates input elements into a mutable result container, such as a Collection or StringBuilder
        collectJoining();
        collectToArrayToListToSetToCollection();
        collectToMap();
        collectMapping();
        collectGroupBy();
    }

    private static void filter() {
        List<Integer> numbers = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> filterList = numbers.stream()
                .filter(e -> e % 2 == 0) // takes a Predicate
                .toList();
        System.out.printf("filterList: %s%n", filterList);
    }

    private static void limitAndTakeWhile() {
        List<Integer> numbers = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        List<Integer> limitList = numbers.stream()
                .limit(5)
                .toList();
        System.out.printf("limitList: %s%n", limitList);

        List<Integer> takeWhileList = numbers.stream()
                .takeWhile(e -> e < 5) // same as limit, but takes a Predicate
                .toList();
        System.out.printf("takeWhileList: %s%n", takeWhileList);
    }

    private static void skipAndDropWhile() {
        List<Integer> numbers = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        List<Integer> skipList = numbers.stream()
                .skip(5)
                .toList();
        System.out.printf("skipList: %s%n", skipList);

        List<Integer> dropWhileList = numbers.stream()
                .dropWhile(e -> e < 5) // same as skip, but takes a Predicate
                .toList();
        System.out.printf("dropWhileList: %s%n", dropWhileList);
    }

    private static void flatMap() {
        List<List<Integer>> listOfLists = List.of(List.of(1, 2), List.of(3, 4));

        List<Integer> flatMapList = listOfLists.stream()
//                .flatMap(e -> e.stream())
                .flatMap(List::stream)
                .toList();
    }

    private static void reduce() {
        // overloaded with 2 args: T reduce(T identity, BinaryOperator<T> accumulator)
        List<Integer> numbers = List.of(1, 2, 3);
        List<String> strings = List.of("a", "b", "c");

        int reduce1 = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.printf("reduce1: %d%n", reduce1);

        String reduce2 = strings.stream()
                .reduce("", String::concat);
        System.out.printf("reduce2: %s%n", reduce2);

        // overloaded with 1 arg: Optional<T> reduce(BinaryOperator<T> accumulator)
        Optional<String> reduce3 = strings.stream()
                .reduce(String::concat);
        System.out.printf("reduce3: %s%n", reduce3.orElse(""));

        // overloaded with 3 arg: <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)
        String reduce4 = strings.parallelStream() // when parallel, the combiner is used to combine the accumulated substreams
                .reduce("", String::concat, String::concat); // accumulator == combiner cuz: BinaryOperator<T> extends BiFunction<T, T, T>
        System.out.printf("reduce4: %s%n", reduce4);

        List<Item> items = List.of(
                new Item(1, 1),
                new Item(2, 2),
                new Item(3, 3));
        int reduce5 = items.stream()
//                .reduce(0, (total, item) -> total + item.price()); // won't compile. for 2 args expects (T identity, BinaryOperator<T> accumulator)
                .reduce(0, (total, item) -> total + item.price, Integer::sum); // API Note: Many reductions using this form can be represented more simply by an explicit combination of map and reduce operations. The accumulator function acts as a fused mapper and accumulator, which can sometimes be more efficient than separate mapping and reduction, such as when knowing the previously reduced value allows you to avoid some computation.
        System.out.printf("reduce5: %d%n", reduce5);

        // accumulator and combiner functions must be:
        // -associative: the result is not affected by the order of the operands
        // -non-interfering: the operation doesn't affect the data source
        // -stateless and deterministic: the operation doesn't have state and produces the same output for a given input
        //
        // additionally:
        // combiner must be compatible with accumulator
        // i.e, combiner.apply(u, accumulator.apply(identity, t)) == accumulator.apply(u, t)
        // e.g,
        // | 1| 2|  3| 4|  5|
        // \accum/\accum/   /
        //  \ 3      7    5/
        //   \  combiner  /
        //    \    15    /
    }

    private static void totalPrice() {
        List<Order> orders = List.of(
                new Order(1, List.of(new Item(1, 10), new Item(2, 10))),
                new Order(2, List.of(new Item(3, 10), new Item(4, 10))),
                new Order(3, List.of(new Item(5, 10), new Item(6, 10))));

        int totalPrice1 = orders.stream()
                .flatMap(order -> order.items().stream())
                .map(Item::price)
                .reduce(0, Integer::sum);
        System.out.printf("totalPrice1: %s%n", totalPrice1);

        int totalPrice2 = orders.stream()
                .flatMap(order -> order.items.stream())
                .mapToInt(Item::price)
                .sum();
        System.out.printf("totalPrice2: %s%n", totalPrice2);

        int totalPrice3 = orders.stream()
                .flatMap(order -> order.items.stream())
                .reduce(0, (total, item) -> total + item.price, Integer::sum);
        System.out.printf("totalPrice3: %s%n", totalPrice3);
    }

    private static void specialReductions() {
        List<Integer> numbers = List.of(1, 2, 3);

        long count = numbers.stream().count(); // numbers.size();
        System.out.println("count = " + count);

        Optional<Integer> max = numbers.stream().max(Comparator.naturalOrder());
        System.out.println("max = " + max);

        Optional<Integer> min = numbers.stream().min(Comparator.naturalOrder());
        System.out.println("min = " + min);

        int sum = numbers.stream()
                .mapToInt(e -> e)
                .sum();
        System.out.println("sum = " + sum);
    }

    private static void collectJoining() {
        List<String> strings = List.of("a", "b", "c");

        String collectJoining1 = strings.stream().collect(Collectors.joining()); // String.join("", strings);
        System.out.println("collectJoining1 = " + collectJoining1);

        String collectJoining2 = strings.stream().collect(Collectors.joining("-")); // String.join("-", strings);
        System.out.println("collectJoining2 = " + collectJoining2);

        String collectJoining3 = strings.stream().collect(Collectors.joining("-", "(", ")"));
        System.out.println("collectJoining3 = " + collectJoining3);
    }

    private static void collectToArrayToListToSetToCollection() {
        List<Integer> numbers = List.of(1, 2, 1, 2);

        Object[] collectToArray = numbers.stream().toArray(); // numbers.toArray();
        System.out.println("collectToArray = " + Arrays.toString(collectToArray));

        List<Integer> collectToList = numbers.stream()
//                .collect(Collectors.toUnmodifiableList());
//                .toList(); // Unmodifiable list produced as if by the following: Collections.unmodifiableList(new ArrayList<>(Arrays.asList(this.toArray())))
                .collect(Collectors.toList());
        System.out.println("collectToList = " + collectToList);

        Set<Integer> collectToSet = numbers.stream() // new HashSet<>(numbers);
//                .collect(Collectors.toUnmodifiableSet());
                .collect(Collectors.toSet());
        System.out.println("collectToSet = " + collectToSet);

        Collection<Integer> collectToCollection = numbers.stream() // new ConcurrentLinkedDeque<>(numbers);
                .collect(Collectors.toCollection(ConcurrentLinkedDeque::new));
        System.out.println("collectToCollection = " + collectToCollection);
    }

    private static void collectToMap() {
        List<Person> people = List.of(
                new Person("1", "Venkat", 30),
                new Person("2", "Subramaniam", 30),
                new Person("3", "sbszc", 28));

        Map<String, String> collectToMap = people.stream()
//                .collect(Collectors.toUnmodifiableMap(e -> e.id, e -> e.name + "-" + e.age));
//                .collect(Collectors.toConcurrentMap(e -> e.id, e -> e.name + "-" + e.age));
                .collect(Collectors.toMap(
                        e -> e.id, // key mapper
                        e -> e.name + "-" + e.age)); // value mapper
        System.out.println("collectToMap = " + collectToMap);
    }

    private static void collectMapping() {
        List<Person> people = List.of(
                new Person("1", "Venkat", 30),
                new Person("2", "Subramaniam", 30),
                new Person("3", "sbszc", 28));

        List<String> collectMapping = people.stream() // .map(e -> e.name).collect(Collectors.toList());
                .collect(Collectors.mapping(
                        e -> e.name, // mapper function
                        Collectors.toList()));// downstream collector
        System.out.println("collectMapping = " + collectMapping);
    }

    private static void collectGroupBy() {
        List<Person> people = List.of(
                new Person("1", "Venkat", 30),
                new Person("2", "Subramaniam", 30),
                new Person("3", "sbszc", 28));

        Map<Integer, List<Person>> collectGroupingBy1 = people.stream()
                .collect(Collectors.groupingBy(
                        e -> e.age/*, // classifier function
                        Collectors.toList())*/)); // downstream collector (Collectors.toList() by default)
        System.out.println("collectGroupingBy1 = " + collectGroupingBy1);

        Map<Integer, List<String>> collectGroupingBy2 = people.stream()
                .collect(Collectors.groupingBy(
                        e -> e.age, // classifier function
                        Collectors.mapping( // downstream collector
                                e -> e.name, // mapper function
                                Collectors.toList()))); // downstream collector
        System.out.println("collectGroupingBy2 = " + collectGroupingBy2);
    }

    record Order(long id, List<Item> items) {
    }

    record Item(long id, int price) {
    }

    record Person(String id, String name, int age) {
    }
}
