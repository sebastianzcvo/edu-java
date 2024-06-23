package reactiveStreams.javabrains.reactiveworkshop;

import java.util.List;

public class Exercise1 {

    public static void main(String[] args) {
        // print all the numbers in intNumbersStream
        System.out.println("print all the numbers in intNumbersStream");
        StreamSources.intNumbersStream()
                .forEach(System.out::println);

        // print all the numbers in intNumbersStream that are less than 5
        System.out.println("print all the numbers in intNumbersStream that are less than 5");
        StreamSources.intNumbersStream()
                .filter(e -> e < 5)
                .forEach(System.out::println);

        // print the second and the third number in intNumbersStream that are greater than 5
        System.out.println("print the second and the third number in intNumbersStream that are greater than 5");
        StreamSources.intNumbersStream()
                .filter(e -> e > 5)
                .skip(1)
                .limit(2)
                .forEach(System.out::println);

        // print the first number int intNumbersStream that is greater than 5
        System.out.println("print the first number int intNumbersStream that is greater than 5");
        System.out.println(StreamSources.intNumbersStream()
                .filter(e -> e > 5)
                .findFirst().orElse(-1));

        // print first names of all users in userStream
        System.out.println("print first names of all users in userStream");
        StreamSources.userStream()
                .forEach(user -> System.out.println(user.name()));

        // print first names of users in userStream that have user IDs from intNumbersStream
        System.out.println("print first names of users in userStream that have user IDs from intNumbersStream");
        List<Integer> intNumbersList = StreamSources.intNumbersStream().toList();
        StreamSources.userStream()
                .filter(user -> intNumbersList.contains(user.id()))
                .forEach(user -> System.out.println(user.name()));
        System.out.println("print first names of users in userStream that have user IDs from intNumbersStream");
        StreamSources.intNumbersStream()
                .flatMap(e -> StreamSources.userStream().filter(user -> user.id() == e))
                .map(User::name)
                .forEach(System.out::println);
        System.out.println("print first names of users in userStream that have user IDs from intNumbersStream");
        StreamSources.userStream()
                .filter(user -> StreamSources.intNumbersStream().anyMatch(e -> e == user.id()))
                .forEach(user -> System.out.println(user.name()));
    }
}
