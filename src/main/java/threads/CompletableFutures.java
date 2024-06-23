package threads;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class CompletableFutures {

    public static void main(String[] args) throws IOException {
        example1();
    }

    private static void example1() throws IOException {
        // supplyAsync() and runAsync() are executed by ForkJoinPool.commonPool by default, thenApply() MIGHT be executed by ForkJoinPool.commonPool, or by the main thread if the CompletableFuture is already completed
        // CompletableFuture follows the Railway Track pattern. With 2 channels: data channel and error channel. Similarly to Promises in JavaScript
        CompletableFuture.supplyAsync(() -> getOrder(1))
                .exceptionally(throwable -> makeFailedOrder(throwable))
                .exceptionally(throwable -> new Order(-999999999)) // ignored since the error was caught upstream by a previous exceptionally(), moving the flow back to the data channel
                .thenApply(order -> makeTransaction(order))
                .thenAccept(transaction -> saveTransaction(transaction))
                .thenRun(() -> System.out.println("end1"))
                .thenRun(() -> System.out.println("end2"))
                .thenRun(() -> System.out.println("end3"));

        System.out.println("press a button to continue"); // the main thread has to be alive for the process to be alive, therefore, for other threads to be alive
        System.in.read();
    }

    private static Order getOrder(int id) {
        System.out.println("getOrder(): " + Thread.currentThread());
        throw new RuntimeException();
//        return new Order(1);
    }

    private static Order makeFailedOrder(Throwable throwable){
        System.out.println("makeFailedOrder(): " + Thread.currentThread());
        return new Order(-1);
    }

    private static Transaction makeTransaction(Order order) {
        System.out.println("makeTransaction(): " + Thread.currentThread());
        return new Transaction(order.id);
    }

    private static void saveTransaction(Transaction transaction) {
        System.out.println("saveTransaction(): " + Thread.currentThread());
        System.out.println("Transaction saved: "+ transaction);
    }

    static class Order {
        public int id;

        public Order(int id) {
            this.id = id;
        }
    }

    static class Transaction {
        public int id;

        public Transaction(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "id=" + id +
                    '}';
        }
    }
}



