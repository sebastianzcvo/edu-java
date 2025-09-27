package accessModifiers.a;

public class A {
    static private String privateProp = "";
    static String defaultProp = ""; // packageprivate
    static protected String protectedProp = ""; // package-private + subclasses
    static public String publicProp = "";
}
