package oop.defaultAndPrivateMethodsInInterfaces;

// Its properties are 'public static final' by default (Interfaces don't have state, they're meant to define public APIs)
// Its methods are 'public abstract' by default, except for:
// - default methods: 'public' by default (non-abstract: implementation inherited by subtypes)
// - private methods
// - static methods: 'public' by default, or private
// Its inner classes/interfaces are 'public static' by default
@FunctionalInterface // SAM interface: Single Abstract Method
interface SayHiC extends SayHi {

    String publicStaticFinalProp = "publicStaticFinalProp"; // 'public static final' by default

    void sayHi(); // 'public abstract' by default

    default void sayBye() { // 'public' by default
        System.out.println("bye from C");
        privateMethod();
    }

    private void privateMethod() {
        System.out.println("privateMethod");
    }

    static void publicStaticMethod() { // 'public' by default, or private
        System.out.println("publicStaticMethod");
    }

    class PublicStaticClass {} // 'public static' by default
    interface PublicStaticInterface {} // 'public static' by default
}
