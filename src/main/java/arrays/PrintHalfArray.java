package arrays;

// prints half array, including the middle if size is odd
public class PrintHalfArray {
    public static void main(String[] args) {
        print(new int[]{1, 2, 3, 4, 5});
        print(new int[]{1, 2, 3, 4});
        print(new int[]{1, 2, 3});
        print(new int[]{1, 2});
        print(new int[]{1});
    }

    // the key is:
    // 1. half = ceil(length / 2)
    // 2. loop( 0 <= i < half )
    public static void print(int[] arr) {
        int half = Math.ceilDiv(arr.length, 2);
        System.out.println("half:" + half);

        for (int i = 0; i < half; i++) {
            System.out.println(arr[i]);
        }
    }
}
