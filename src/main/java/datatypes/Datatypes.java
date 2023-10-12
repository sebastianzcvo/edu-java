package datatypes;

import javax.xml.crypto.Data;

public class Datatypes {
    // Graph of valid promotions (implicit upcasting):
    //
    //                 float -> double
    //                   ^   \  ^
    // byte -> short -> int -> long
    //                   ^
    //                  char
    //
    // casting (explicit down-casting) may result in data loss
    public static void main(String[] args) {
        System.out.printf("%s: [%f,%f] bytes:%d%n", Double.TYPE, Double.MIN_VALUE, Double.MAX_VALUE, Double.BYTES);
        System.out.printf("%s: [%f,%f] bytes:%d%n", Float.TYPE, Float.MIN_VALUE, Float.MAX_VALUE, Float.BYTES);
        System.out.printf("%s: [%d,%d] bytes:%d%n", Long.TYPE, Long.MIN_VALUE, Long.MAX_VALUE, Long.BYTES);
        System.out.printf("%s: [%d,%d] bytes:%d%n", Integer.TYPE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.BYTES);
        System.out.printf("%s: [%d,%d] bytes:%d%n", Character.TYPE, (int) Character.MIN_VALUE, (int) Character.MAX_VALUE, Character.BYTES);
        System.out.printf("%s: [%d,%d] bytes:%d%n", Short.TYPE, Short.MIN_VALUE, Short.MAX_VALUE, Short.BYTES);
        System.out.printf("%s: [%d,%d] bytes:%d%n", Byte.TYPE, Byte.MIN_VALUE, Byte.MAX_VALUE, Byte.BYTES);
        System.out.printf("%s: [%b,%b] %n", Boolean.TYPE, Boolean.FALSE, Boolean.TRUE);
    }
}
