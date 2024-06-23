package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static void main(String[] args) {
        // n* {0,}
        // n+ {1,}
        // n? {0,1}
        // n{99}
        // .
        // |
        // [abc] [^abc]
        // [a-z] [^a-z]
        // [A-Z] [^A-Z]
        // [0-9] [^0-9]
        // \s \S  ->  space
        // \d \D  ->  digit
        // \w \W  ->  letter or digit
        // ()     ->  group
        // ^      ->  beginning
        // $      ->  ending
        Pattern p = Pattern.compile("[A-Z][a-z]");
        Matcher m = p.matcher("GeEkific");
        while (m.find()) {
            System.out.println(m.start());
            System.out.println(m.end());
            System.out.println(m.group());
        }
        System.out.println("--------------");
        p = Pattern.compile("(.*)\\s(.*)\\s(.*)");
        m = p.matcher("Ge EkI fic");
        if (m.matches()){
            System.out.println(m.groupCount());
            System.out.println(m.group());

            System.out.println(m.group(1));
            System.out.println(m.group(2));
            System.out.println(m.group(3));
        }
        System.out.println("--------------");
        p = Pattern.compile("\\s");
        m = p.matcher("Ge EkI fic");
        System.out.println(m.replaceFirst("_"));
        System.out.println(m.replaceAll("_"));
        System.out.println("--------------");
        p = Pattern.compile("\\."); // escaped point != wild card. It literally means a point character
        m = p.matcher("a");
        System.out.println(m.matches()); // false
        System.out.println("--------------");
        p = Pattern.compile("a", Pattern.CASE_INSENSITIVE); // CASE_INSENSITIVE flag
        m = p.matcher("A");
        System.out.println(m.matches()); // true
        p = Pattern.compile("(?i)a"); // embedded flags
        m = p.matcher("A");
        System.out.println(m.matches()); // true
        System.out.println("--------------");
        p = Pattern.compile("a#my comment", Pattern.COMMENTS); // COMMENTS flag
        m = p.matcher("a");
        System.out.println(m.matches()); // true
        p = Pattern.compile("(?x)a#my comment"); // embedded flags
        m = p.matcher("a");
        System.out.println(m.matches()); // true
    }
}
