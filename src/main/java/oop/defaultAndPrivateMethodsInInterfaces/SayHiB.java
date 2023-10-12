package oop.defaultAndPrivateMethodsInInterfaces;

@FunctionalInterface
public interface SayHiB extends SayHi {
    void sayHi();

    default void sayBye() {
        System.out.println("bye from B");
    }
}
