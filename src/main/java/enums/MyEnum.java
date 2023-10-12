package enums;

// Special type of class that represents a set of constants with name and ordinal, that behave as final static objects whose type is a subtype of the enum.
// Always extends the java.lang.Enum. No extends clause allowed for enum
// Introduced in java 5
public enum MyEnum implements SayHi {
    CONSTANT0("myProp0") { // behaves as an object whose type is a subtype of MyEnum, which in turn is a subtype of java.lang.Enum
        final String myProp = "CONSTANT0 prop";

        @Override
        void myAbstractMethod() {
            System.out.println(super.myProp);
            System.out.println(myProp);
        }
    },
    CONSTANT1("myProp1") {
        final String myProp = "CONSTANT1 prop";

        @Override
        void myAbstractMethod() {
            System.out.println(super.myProp);
            System.out.println(myProp);
        }

        @Override
        public void sayHi() {
            super.sayHi();
            System.out.println("Hi from CONSTANT1");
        }
    };

    final String myProp;

    MyEnum(String myProp) { // private by default. Public, protected, package-private not allowed
        System.out.println("MyEnum.MyEnum " + myProp);
        this.myProp = myProp;
    }

    abstract void myAbstractMethod();

    @Override
    public void sayHi() {
        System.out.println("Hi from MyEnum");
    }

    public static void main(String[] args) {
        System.out.println(MyEnum.CONSTANT0);
        System.out.println(MyEnum.CONSTANT1);

        for (MyEnum myEnum : MyEnum.values()) {
            System.out.println("----------");
            System.out.printf("%s name():%s ordinal():%s%n", myEnum.toString(), myEnum.name(), myEnum.ordinal());
            myEnum.myAbstractMethod();
            myEnum.sayHi();
        }
    }

}
interface SayHi {
    void sayHi();
}
