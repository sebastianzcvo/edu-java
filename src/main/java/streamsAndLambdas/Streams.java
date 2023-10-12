package streamsAndLambdas;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Streams {
    public static void main(String[] args) {
        streams();
        streamsCharacteristics();

        intLongDoubleStreams();

        parallelStreams();
    }

    private static void streams() {
        // stream != data structure
        // -No storage: streams convey elements from a source through a pipeline of aggregated operations.
        // -Functional in nature: An operation on a stream produces a result, but does not modify its source.
        // -Lazy(efficient) evaluation: Intermediate operations are efficiently evaluated, so the stream pipeline gets executed only if a terminal operation is executed. Then the elements traverse the pipeline as a single evaluation of aggregated operations.
        // -Possibly unbounded: While collections have a finite size, streams need not. Short-circuiting operations such as limit(n) or findFirst() can allow computations on infinite streams to complete in finite time.
        // -Consumable: The elements of a stream are only visited once during the life of a stream. Like an java.util.Iterator, a new stream must be generated to revisit the same elements of the source.

        // A stream is an abstractions that conveys elements from a source through a pipeline of aggregated operations.
        // comprised of: source, intermediate operations, terminal operation
        // -Source, can be:
        // a collection via the stream() and parallelStream()
        // an array via java.util.Arrays.stream(Object[])
        // a factory method Stream.of(Object[]), IntStream.range(int, int) or Stream.iterate(Object, UnaryOperator)
        // lines of a file from java.io.BufferedReader.lines()
        // file paths from java.nio.file.Files
        // random numbers from java.util.Random.ints()
        // other stream-bearing methods in the JDK and third party libs
        Stream.of(1, 2, 3)

                // -Intermediate operation (stream-producing), can be:
                .filter(e -> false) // Stateless intermediate operations, retain no state from previously seen element. Under parallel computation, pipelines containing exclusively stateless intermediate operations, can be processed in a single pass, whether sequential or parallel, with minimal data buffering.
                .distinct() // Stateful intermediate operations, may incorporate state from previously seen elements. Under parallel computation, pipelines containing stateful intermediate operations, may require multiple passes on the data or may need to buffer significant data.
                .takeWhile(e -> true) // Besides stateless or stateful, short-circuiting intermediate operations, may produce a finite stream as a result, when presented with infinite input

                // -Terminal operation (value- or side-effect-producing)
                //.findFirst(); // short-circuiting terminal operations, may terminate in finite time, when presented with infinite input. Having a short-circuiting operation in the pipeline is a necessary, but not sufficient, condition for the processing of an infinite stream to terminate normally in finite time.
                .forEach(System.out::println);
    }

    private static void streamsCharacteristics() {
        // a stream can be: sized|infinite, ordered|unordered, distinct|non-distinct, sorted|unsorted
        // these four characteristics are defined by the source, but can be tweaked

        Set<Integer> set = Set.of(1, 2, 3);
        set.stream(); // sized, unordered, distinct, unsorted

        List<Integer> list = List.of(1, 2, 3, 1, 2, 3);
        list.stream() // sized, ordered, non-distinct, unsorted
                .unordered() // tweaked to unordered
                .distinct() // tweaked to distinct
                .sorted() // tweaked to sorted
                .forEach(System.out::println);

        Stream.iterate(100, e -> e + 1) // infinite, ordered, non-distinct, unsorted
                //.sorted() // Non-short-circuit operation consumes infinite stream. Throws java.lang.OutOfMemoryError: Java heap space
                .limit(10) // tweaked to sized (short-circuit intermediate operation)
                .sorted(Comparator.reverseOrder()) // now it's sized, you can apply Non-short-circuit operation
                .forEach(System.out::println);
    }

    private static void intLongDoubleStreams() {
        IntStream.empty();
        IntStream.of(1, 2, 3);
        IntStream.concat(IntStream.of(1, 2), IntStream.of(3, 4)); // 1 2 3 4
        IntStream.builder().add(1).add(2).add(3).build();
        IntStream.range(1, 3); // 1 2
        IntStream.rangeClosed(1, 3); // 1 2 3
        IntStream.iterate(1, e -> e <= 3, e -> e + 1); // 1 2 3

        LongStream.empty();
        LongStream.of(1L, 2L, 3L);
        LongStream.concat(LongStream.of(1L, 2L), LongStream.of(3L, 4L)); // 1 2 3 4
        LongStream.builder().add(1L).add(2L).add(3L).build();
        LongStream.range(1L, 3L); // 1 2
        LongStream.rangeClosed(1L, 3L); // 1 2 3
        LongStream.iterate(1L, e -> e <= 3L, e -> e + 1L); // 1 2 3

        DoubleStream.empty();
        DoubleStream.of(1.0, 2.0, 3.0);
        DoubleStream.concat(DoubleStream.of(1.0, 2.0), DoubleStream.of(3.0, 4.0)); // 1.0 2.0 3.0 4.0
        DoubleStream.builder().add(1.0).add(2.0).add(3.0).build();
        // no range for DoubleStream
        // no rangeClosed for DoubleStream
        DoubleStream.iterate(1.0, e -> e <= 3.0, e -> e + 1.0); // 1.0 2.0 3.0

        Stream.empty();
        Stream.ofNullable(null); // single element, if null, same as empty
        Stream.of(1, 2, 3);
        Stream.concat(Stream.of(1, 2), Stream.of(3, 4)); // 1 2 3 4
        Stream.builder().add(1).add(2).add(3).build();
        // no range for Stream
        // no rangeClosed for Stream
        Stream.iterate(1, e -> e <= 3, e -> e + 1); // 1 2 3
    }

    private static void parallelStreams() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Timeit.timeit(() -> { // takes about 5 seconds
            System.out.println(
                    numbers.stream()
                            .filter(e -> e % 2 == 0)
                            .mapToInt(Streams::compute)
                            .sum()
            );
        });

        Timeit.timeit(() -> { // takes about 1 second, because parallel streams don't mind using a lot of threads/resources
            System.out.println(
                    numbers.parallelStream()
                            .filter(e -> e % 2 == 0)
                            .mapToInt(Streams::compute)
                            .sum()
            );
        });
        // just because you parallelize doesn't mean you'll allways get a better performance
        // only use parallel streams when:
        // A. when the problem is actually parallelizable
        // B. you are willing to use a lot of threads/resources to get the answer faster
        // C. the data size is big enough that you'll get a benefit in performance
        // D. the computation is big enough that you'll get a benefit in performance
    }

    private static int compute(int number) {
        // assume this is time intensive
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return number * 2;
    }

    private static class Timeit {
        static void timeit(Runnable runnable) {
            long start = System.nanoTime();
            try {
                runnable.run();
            } finally {
                long end = System.nanoTime();
                System.out.println("Time taken(s): " + (end - start) / 1.0e9);
            }
        }
    }
}
