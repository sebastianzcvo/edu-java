package switches;

public class Switches {
    public static void main(String[] args) {
//        switch is only compatible with:
//        byte and Byte,
//        short and Short,
//        int and Integer,
//        char and Character,
//        enum, available since java 5
//        Wrapper classes are available since java 5
//        String, available since java 7. Uses the equals method.
        String myString = "myString"; // null values will throw NullPointerException
        final String case1 = "MYSTRING"; // case values have to be constants
        switch (myString) {
            case case1:
                System.out.println("Compatible with 'MYSTRING'");
                break;
            case "myString", "myString2":
                System.out.println("Compatible with 'myString'");
                break;
            default:
                System.out.println("not compatibility found");
                break;
        }

        String result = switch (myString) { // switch expressions are available since java 14
            case case1 -> "Compatible with 'MYSTRING'";
            case "myString", "myString2" -> {
                yield "Compatible with 'myString'"; // code blocks require yield keyword
            }
            default ->
                    "not compatibility found"; // switch expressions must be exhaustive: have to cover all possible values or contain a default branch
        };
        System.out.println(result);

        switch (myString) { // you can use return from switch statements, not from switch expression
            case "myString", "myString2" -> {
                return; // return needs to be inside a block
            }
            default -> System.out.println("not compatibility found");
        }
    }
}
