package reactiveStreams.javabrains.reactiveworkshop;

import java.io.IOException;

public class Exercise2 {

    public static void main(String[] args) throws IOException {
        // use ReactiveSources.intNumbersFlux() and ReactiveSources.userFlux()

        // print all the numbers in intNumbersFlux
        ReactiveSources.intNumbersFlux()
                .subscribe(System.out::println);

        // print all users in the userFlux
        ReactiveSources.userFlux()
                .subscribe(System.out::println);


        // keeps the main thread alive, therefore, the process
        System.out.println("Press a key");
        System.in.read();
    }
}
