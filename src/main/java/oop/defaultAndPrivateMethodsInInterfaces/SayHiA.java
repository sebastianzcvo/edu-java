package oop.defaultAndPrivateMethodsInInterfaces;

public abstract class SayHiA implements SayHi {

    public abstract void sayHi();

    public void sayBye() {
        System.out.println("bye from A");
    }
}
