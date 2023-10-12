package clonables;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person(1, "name1");
        Person person2 = person1.clone();
        System.out.println(person1 == person2); // false
        System.out.println(person1.equals(person2)); // true
        System.out.println(person1.getClass() == person2.getClass()); //true
    }

    static class Person implements Cloneable {
        int id;
        String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public Person clone() {
            try {
                Person clone = (Person) super.clone();
                // TODO: copy mutable state here, so the clone can't change the internals of the original
                return clone;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Person person = (Person) object;
            return id == person.id && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }
}
