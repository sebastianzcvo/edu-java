package oop.innerStaticAnonymousLocalClasses;

import java.util.function.Consumer;

class Outer {
    public static void main(String[] args) {
        Outer outer = new Outer();
        /*Outer.*/InnerStatic innerStatic = new /*Outer.*/InnerStatic();
        /*Outer.*/InnerInterface innerInterface = null; // inner interfaces are 'static' by default
        /*Outer.*/Inner inner = new Outer().new Inner();
        /*Outer.*/Inner.InnerInner innerInner = new Outer().new Inner().new InnerInner();
        /*Outer.*/Inner.InnerInnerStatic innerInnerStatic = new /*Outer.*/Inner.InnerInnerStatic();

        System.out.println(outer.prop);
        System.out.println(innerStatic.prop);
        System.out.println(innerInterface.prop); // interface's properties are accessible even from a null reference
        System.out.println(inner.prop);
        System.out.println(innerInner.prop);
        System.out.println(innerInnerStatic.prop);
        outer.innerLocal();
        outer.innerLocalAnonymous();
        outer.innerAnonymous.accept("");
        outer.innerLambda.accept("innerLambdaArg");
        outer.innerMethodReference.accept("innerMethodReferenceArg");
    }

    String prop = "outerProp";

    static class InnerStatic {
        String prop = "innerStaticProp";
    }
    interface InnerInterface {
        String prop = "innerInterfaceProp";
    }

    class Inner {
        String prop = "innerProp";

        class InnerInner {
            String prop = "innerInnerProp";
        }

        static class InnerInnerStatic {
            String prop = "innerInnerStaticProp";
        }
    }

    void innerLocal() {
        class InnerLocal {
            String prop = "innerLocalProp";
        }
        InnerLocal innerLocal = new InnerLocal();
        System.out.println(innerLocal.prop);
    }

    void innerLocalAnonymous() {
        Consumer<String> innerLocalAnonymous = new Consumer<String>() {
            String prop = "innerLocalAnonymousProp";

            @Override
            public void accept(String s) {
                System.out.println(prop);
            }
        };
        innerLocalAnonymous.accept("");
    }

    Consumer<String> innerAnonymous = new Consumer<String>() {
        String prop = "innerAnonymousProp";

        @Override
        public void accept(String s) {
            System.out.println(prop);
        }
    };

    Consumer<String> innerLambda = s -> System.out.println(s);

    Consumer<String> innerMethodReference = System.out::println;
}
