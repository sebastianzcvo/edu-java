package accessModifiers.a;

public class B {
     void f() {
         System.out.println(A.publicProp);
         System.out.println(A.protectedProp);
         System.out.println(A.defaultProp);
//         System.out.println(A.privateProp);
     }
}
