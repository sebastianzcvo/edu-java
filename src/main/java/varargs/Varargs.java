package varargs;

import java.util.Arrays;
import java.util.List;

public class Varargs {
    public static void main(String[] args) {
        varargs("a", 1, 2);
        varargs("b", new int[]{1, 2});

        genericVarargs("c", "1", "2");
        genericVarargs("d", new String[]{"1", "2"});

//        unsafeVarargs(List.of("1", "2"), List.of("1", "2"));
    }

    static void varargs(String message, int... ints) { // has to be the last parameter
        System.out.println(ints.getClass().getTypeName()); // autoboxed to int[]
        System.out.println(message);
        for (int i : ints) {
            System.out.println(i);
        }
    }


    @SafeVarargs
    static <T> void genericVarargs(String message, T... elements) {
        System.out.println(elements.getClass().getTypeName());
        System.out.println(message);
        for (T e : elements) {
            System.out.println(e);
        }
    }

//    When we use varargs with generic types, as there’s a potential risk of a fatal runtime exception,
//    the Java compiler warns us about a possible unsafe varargs usage:
//      'Possible heap pollution from parameterized vararg type'
//    The varargs usage is safe if and only if:
//      - We don’t store anything in the implicitly created array. In this example, we did store a List<Integer> in that array
//      - We don’t let a reference to the generated array escape the method
//    If we are sure that the method itself does safely use the varargs, we can use @SafeVarargs to suppress the warning.
//    Put simply, the varargs usage is safe if we use them to transfer a variable number of arguments from the caller to the method and nothing more!
    @SafeVarargs // Not actually safe!
    static void unsafeVarargs(List<String>... stringLists) {
        Object[] arrayOfObjects = stringLists;
        List<Integer> listOfInts = Arrays.asList(42);
        arrayOfObjects[0] = listOfInts; // Semantically invalid, but compiles without warnings. Heap pollution: reference changes from List<String> to List<Integer>
        String s = stringLists[0].get(0); // Oh no, ClassCastException at runtime!
    }
}
