package generics;

// From a single generic class/method you get multiple classes/method that only differ from its type parameters
// The type parameter gets erased at compilation time and replaced by Object or by the specified type
// Not primitives types are allowed, only subclasses of Object class
public class Generics {
    public static void main(String[] args) {
        System.out.println(genericMethod("Hello"));
        System.out.println(genericMethod(2));

        GenericClass genericClassObject = new GenericClass<>(); // if no type parameter is specified, raw type is used: Object
        genericClassObject.setProp("genericClassObject");
        System.out.println(genericClassObject.getProp());

        GenericClass<String> genericClassString = new GenericClass<>();
        genericClassString.setProp("genericClassString");
        System.out.println(genericClassString.getProp());

        GenericClass<Integer> genericClassInteger = new GenericClass<>();
        genericClassInteger.setProp(2);
        System.out.println(genericClassInteger.getProp());

        GenericClass2<Child> genericClassChild = new GenericClass2<>();
        genericClassChild.setProp(new Child());
        System.out.println(genericClassChild.getProp());
    }

    static public <G> G genericMethod(G input) { // Unbounded type parameter
        return input;
    }

    static class GenericClass<T> { // Unbounded type parameter
        private T prop;

        public void setProp(T prop) {
            this.prop = prop;
        }

        public T getProp() {
            return prop;
        }
    }

    static class GenericClass2<T extends Parent & Interface1 & Interface2> { // Bounded type parameter
        private T prop;

        public void setProp(T prop) {
            this.prop = prop;
        }

        public T getProp() {
            return prop;
        }
    }

    static class Child extends Parent implements Interface1, Interface2 {
    }

    static class Parent {
    }

    interface Interface1 {
    }

    interface Interface2 {
    }

}
