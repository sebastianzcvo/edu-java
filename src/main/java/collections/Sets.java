package collections;

import java.util.*;

public class Sets {
    public static void main(String[] args) {
        set(); // Unique elements. equals() and hashCode() to ensure uniqueness.

        hashSet(); // Unordered. Implements a HashMap. Nullable.
        linkedHashSet(); // Ordered by insertion. Implements a HashMap and a double-linked list. Nullable.
        treeSet(); // Ordered by value using compareTo(). Implements a TreeMap. Non-nullable.
    }

    private static void set() {

    }

    private static void hashSet() {
        HashSet<Persona> personas = new HashSet<>(List.of(new Persona(1)));
        personas.add(new Persona(1));
        System.out.println(personas);
        personas.add(new Persona(3));
        personas.add(new Persona(2));
        personas.add(null);
        System.out.println(personas);

    }

    private static void linkedHashSet() {
        LinkedHashSet<Persona> personas = new LinkedHashSet<>(List.of(new Persona(1)));
        personas.add(new Persona(1));
        System.out.println(personas);
        personas.add(new Persona(3));
        personas.add(new Persona(2));
        personas.add(null);
        System.out.println(personas);
    }

    private static void treeSet() {
        TreeSet<Persona> personas = new TreeSet<>(List.of(new Persona(1)));
        personas.add(new Persona(1));
        System.out.println(personas);
        personas.add(new Persona(3));
        personas.add(new Persona(2));
//        personas.add(null);
        System.out.println(personas);
    }

    static class Persona implements Comparable<Persona> {
        private int id;

        public Persona(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Persona{" +
                    "id=" + id +
                    '}';
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Persona persona = (Persona) object;
            return id == persona.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }


        @Override
        public int compareTo(Persona o) {
            return Integer.compare(id, o.id);
        }
    }

}
