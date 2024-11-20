package reactiveStreams;

import io.reactivex.rxjava3.core.Observable;
import reactiveStreams.javabrains.reactiveworkshop.User;
import reactor.core.publisher.Mono;

// Reactive Streams is an API introduced in java 9. It's just the specification, there are several implementations like Reactor or RxJava
// Follows the observable pattern, instead of the iterator pattern as classic streams do
public class ReactiveStreams {
    public static void main(String[] args) {
        reactorVsRxJava();
    }

    private static void reactorVsRxJava() {
        // Reactor
        Mono.just(new User(1, "name1", "lastname1"))
                .doOnNext(System.out::println)
                .subscribe();

        // RxJava
        Observable.just(new User(1, "name1", "lastname1"))
                .doOnNext(System.out::println)
                .subscribe();
    }
}
