package reflection.instanceOf;

public class SuperType {
    String prop = "super";
}

class SubType extends SuperType {
    String prop = "sub";
    public static void main(String[] args) {
        SuperType superType = new SuperType();
        checkType(superType);

        SuperType subType = new SubType();
        checkType(subType);

        System.out.println(subType.prop);
        if (subType instanceof SubType castedSubType) {
            System.out.println(castedSubType.prop);
        }
    }

    static void checkType(Object object) {
        System.out.println(object instanceof SuperType);
        System.out.println(object instanceof SubType);
        System.out.println("-------------");
    }
}
