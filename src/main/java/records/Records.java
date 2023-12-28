package records;

import java.io.Serializable;
import java.util.Objects;

// java 14 - preview
// java 15 - 2nd preview
// java 16 - standard
// record keyword for immutable classes. Similar to @Value in lombok
//   final class
//   private final fields
//   public canonical constructor (all-args constructor that sets all instance fields)
//   public getters, following the convention name(), not getName()
//   toString(), following the convention Person[name=name1, lastname=lastname1]
//   equals() and hashCode()
record Person(String name, String lastname) implements Serializable { // extends is not allowed because already extends Record class, but implements does is allowed
    public Person(String name) { // constructor can be overloaded but has to invoke the canonical one at the first line
        this(name, "nolastname");
    }
//    public Person(String name, String lastname) { // generated canonical constructor can be overriden but has to be canonical as well
//        Objects.requireNonNull(name);
//        Objects.requireNonNull(lastname);
//        this.name = name;
//        this.lastname = lastname;
//    }
    public Person { // generated canonical constructor can be customized using a compact constructor. Think of it as a filter of post-processor before the cannonical constructor is called, thus 'this' is not accesible yet in the lifecycle of the object
        Objects.requireNonNull(name);
        Objects.requireNonNull(lastname);
        // cannonical constructor is invoked here
    }

    public String name(int rep) { // getters and other generated methods can be overriden and overloaded
        return name;
    }

    // int age; additional instance fields are not allowed
    String fullname() { // additional instance methods are allowed
        return String.format("%s %s", name, lastname);
    }

    Person setName(String name) { // a setter could be achieved by returning a new instance with the new value. Similar to @With in lombok, also similar to mutations in ValueObjects
        return new Person(name, lastname);
    }

    public static final String tag = "TAG"; // additional static fields and methods are allowed
    public static String tag() { // additional static fields and methods are allowed
        return tag;
    }
}

public class Records {
    public static void main(String[] args) {
        Person person = new Person("name1", "lastname1");
        System.out.println(person.name());
        System.out.println(person.lastname());
        System.out.println(person.fullname());
        System.out.println(person.toString());
    }
}

