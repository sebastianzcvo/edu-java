package collections;

import java.util.*;

public class Maps {
    public static void main(String[] args) {
        map(); // Key-value elements with unique key. equals() and hashCode() to ensure uniqueness.

        hashMap(); // Unordered. Implements a linked list. Nullable key-value.
        linkedHashMap(); // // Ordered by insertion. Implements a double-linked list. Nullable key-value.
        treeMap(); // Ordered by value using compareTo(). Implements a binary tree. Non-nullable key.
        hashTable(); // Unordered. Thread safe. Implements a linked list. Non-nullable key-value.
    }

    private static void map() {
        Map<String, String> map1 = new HashMap<>(Map.of("1", "1", "2", "2"));
        System.out.println(map1);

        map1.put(null, null);
        map1.put(null, "val");
        map1.put("key", null);
        System.out.println(map1);

        map1.put("2", "new value");
        map1.putIfAbsent("2", "not absent");
        map1.replace("1", "1", "one");
        map1.replace("1", "one");
        System.out.println(map1);

        System.out.println(map1.get("1"));
        System.out.println(map1.get(null));
        System.out.println(map1.get("null"));
        System.out.println(map1.get("nonexistent"));
        System.out.println(map1.getOrDefault("nonexistent", "defaultValue"));

        System.out.println(map1.containsKey("nonexistent"));
        System.out.println(map1.containsKey(null));
        System.out.println(map1.containsValue("nonexistent"));
        System.out.println(map1.containsValue(null));
        System.out.println(map1.size());
        System.out.println("-------------------");

        System.out.println("keySet:");
        map1.keySet().forEach(k -> System.out.println(k));
        System.out.println("entries:");
        map1.entrySet().forEach(entry -> System.out.println(entry));

//        map1.forEach((k, v) -> System.out.println(k+"="+v));

        System.out.println("-------------------");
        Map<MyKey, Object> map = new HashMap<>();
        map.put(new MyKey(1), "1");
        map.put(new MyKey(2), "2");
        System.out.println(map);
        map.putIfAbsent(new MyKey(2), "3");
        System.out.println(map);
    }

    private static void hashMap() {
        HashMap<MyKey, String> hashMap = new HashMap<>();
        hashMap.put(new MyKey(99), "99");
        hashMap.put(new MyKey(2), "2");
        hashMap.put(new MyKey(3), "3");
        hashMap.put(new MyKey(1), "1");

        hashMap.entrySet().forEach(System.out::println);
        System.out.println(hashMap);
    }

    private static void linkedHashMap() {
        LinkedHashMap<MyKey, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(null, null);
    }

    private static void treeMap() {
        TreeMap<MyKey, String> treeMap = new TreeMap<>();
        treeMap.put(new MyKey(1), null);
    }

    private static void hashTable() {
        Hashtable<MyKey, String> hashtable = new Hashtable<>();
        hashtable.put(new MyKey(1), "1");
        hashtable.put(new MyKey(2), "2");
        hashtable.put(new MyKey(99), "99");
        hashtable.put(new MyKey(3), "3");

        hashtable.entrySet().forEach(System.out::println);
        System.out.println(hashtable);
    }

    static class MyKey implements Comparable<MyKey>{
        final int prop;

        MyKey(int prop) {
            this.prop = prop;
        }

        @Override
        public String toString() {
            return "MyKey{" +
                    "prop=" + prop +
                    '}';
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            MyKey myKey = (MyKey) object;
            return prop == myKey.prop;
        }

        @Override
        public int hashCode() {
            return Objects.hash(prop);
        }


        @Override
        public int compareTo(MyKey o) {
//            return Integer.compare(prop, o.prop);
            return Integer.compare(o.prop, prop);
        }
    }

}
