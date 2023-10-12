package oop.defaultAndPrivateMethodsInInterfaces;

public class Person extends SayHiA implements SayHiB, SayHiC {
    @Override
    public void sayHi() { // a class can override multiple methods with the same name
        System.out.println("hi");
    }

    // if multiple super INTERFACE implementations exist, you have to explicitly override and choose: SayHiB.super.sayBye() and/or SayHiC.super.sayBye() or none
    // not the case if a super CLASS implementation exists. Then super.sayBye() will be inherited, so you don't have to override
//    @Override
//    public void sayBye() {
//        SayHiB.super.sayBye();
//        SayHiC.super.sayBye();
//    }

    public static void main(String[] args) {
        SayHiC sayHiC = () -> System.out.println("hi from lambda");
        sayHiC.sayHi();
        sayHiC.sayBye();
        System.out.println(SayHiC.publicStaticFinalProp);
        SayHiC.publicStaticMethod();

        System.out.println("-------------------");
        Person person = new Person();
        person.sayHi();
        person.sayBye();
    }

}
