package packages.b;

import packages.a.A;

public class C extends A {
    void f() {
        System.out.println(A.publicProp);
        System.out.println(A.protectedProp);
//        System.out.println(A.defaultProp);
//         System.out.println(A.privateProp);
    }
}
