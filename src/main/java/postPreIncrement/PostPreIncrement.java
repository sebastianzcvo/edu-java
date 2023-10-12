package postPreIncrement;

public class PostPreIncrement {
    public static void main(String[] args) {
        int a = 3;
        int b = a++; // b = 3, a = 4  POST-INCREMENT  returns and then increments
        int c = ++b; // c = 4, b = 4  PRE-INCREMENT   increments and then returns

        for (int i = 0; i++ < 2;) {
            System.out.println(i);
        }
        System.out.println("-----------");
        for (int i = 0; ++i < 2;) {
            System.out.println(i);
        }
    }
}
