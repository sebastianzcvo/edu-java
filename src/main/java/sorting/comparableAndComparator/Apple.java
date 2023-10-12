package sorting.comparableAndComparator;

public class Apple implements Comparable<Apple> {
    private final int weight;
    private final int size;
    private final Variety variety;
    private final Origin origin;

    enum Variety {
        RED, // ordinal 0
        GREEN, // ordinal 1
        GOLDEN, // ordinal 2
        GALA // ordinal 3 and so on...
    }

    enum Origin {
        LOCAL(10),
        IMPORTED(1);

        final int priority;

        Origin(int priority) {
            this.priority = priority;
        }
    }

    public Apple(int weight, int size, Variety variety, Origin origin) {
        this.weight = weight;
        this.size = size;
        this.variety = variety;
        this.origin = origin;
    }

    @Override
    public int compareTo(Apple o) {
//        return (weight < o.weight) ? -1 : ((weight == o.weight) ? 0 : 1);
//        return Integer.compare(weight, o.weight);
//        return weight - o.weight;
        int result = weight - o.weight;

        // multi-characteristics comparison
        if (result == 0) result = size - o.size;
        if (result == 0) result = variety.compareTo(o.variety); // checks enum's ordinal
        if (result == 0) result = origin.priority - o.origin.priority; // checks enum's property
        return result;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", size=" + size +
                ", variety=" + variety +
                ", origin=" + origin +
                '}';
    }

    public static void main(String[] args) {
        Apple apple1, apple2, apple3;
        // weight comparison
        apple1 = new Apple(1, 1, Apple.Variety.RED, Apple.Origin.LOCAL);
        apple2 = new Apple(2, 2, Apple.Variety.RED, Apple.Origin.LOCAL);
        apple3 = new Apple(1, 1, Apple.Variety.RED, Apple.Origin.LOCAL);
        System.out.println(apple1.compareTo(apple2));
        System.out.println(apple2.compareTo(apple1));
        System.out.println(apple1.compareTo(apple3));
        // size comparison
        apple1 = new Apple(1, 1, Apple.Variety.RED, Apple.Origin.LOCAL);
        apple2 = new Apple(1, 2, Apple.Variety.RED, Apple.Origin.LOCAL);
        apple3 = new Apple(1, 1, Apple.Variety.RED, Apple.Origin.LOCAL);
        System.out.println(apple1.compareTo(apple2));
        System.out.println(apple2.compareTo(apple1));
        System.out.println(apple1.compareTo(apple3));
        // Variety comparison
        apple1 = new Apple(1, 1, Apple.Variety.RED, Apple.Origin.LOCAL);
        apple2 = new Apple(1, 1, Apple.Variety.GREEN, Apple.Origin.LOCAL);
        apple3 = new Apple(1, 1, Apple.Variety.RED, Apple.Origin.LOCAL);
        System.out.println(apple1.compareTo(apple2));
        System.out.println(apple2.compareTo(apple1));
        System.out.println(apple1.compareTo(apple3));
        // Origin comparison
        apple1 = new Apple(1, 1, Apple.Variety.RED, Apple.Origin.IMPORTED);
        apple2 = new Apple(1, 1, Apple.Variety.RED, Apple.Origin.LOCAL);
        apple3 = new Apple(1, 1, Apple.Variety.RED, Apple.Origin.IMPORTED);
        System.out.println(apple1.compareTo(apple2));
        System.out.println(apple2.compareTo(apple1));
        System.out.println(apple1.compareTo(apple3));
    }
}


