package accessModifiers.a;

public class A {
    static private String privateProp = "";
    static String defaultProp = ""; // package private
    static protected String protectedProp = ""; // default + subclasses
    static public String publicProp = "";
}
