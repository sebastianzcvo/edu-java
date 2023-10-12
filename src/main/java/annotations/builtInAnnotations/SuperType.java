package annotations.builtInAnnotations;

public abstract class SuperType {

    abstract void method();

    @Deprecated // shows warning to the compiler: 'deprecatedMethod()' is deprecatedMethod
    void deprecatedMethod() {
    }

}

class SubType extends SuperType {

    @Override // shows error message to the compiler: Method does not override method from its superclass
    void method() {
    }

    @SuppressWarnings("deprecation") // removes warning message from the compiler: 'deprecatedMethod()' is deprecated
    public static void main(String[] args) {
        SubType subType = new SubType();
        subType.method();
        subType.deprecatedMethod();
    }
}






