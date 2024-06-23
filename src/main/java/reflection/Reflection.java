package reflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// feature to access classes and their members at runtime, even to private members, using classes in java.lang.reflect:
//     Class, Field, Method, Constructor
// not very performant but useful in conjunction with annotations to build frameworks and other tools
public class Reflection {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // TestClass.class, object.getClass(), Class.forName("TestClass")
        Class<?> clazz = TestClass.class;

        System.out.println("---getName()-----------------------------------------------------------------------------");
        System.out.println(clazz.getName());
        System.out.println("---getSimpleName()-----------------------------------------------------------------------");
        System.out.println(clazz.getSimpleName());
        System.out.println("---getCanonicalName()--------------------------------------------------------------------");
        System.out.println(clazz.getCanonicalName());
        System.out.println("---getPackageName()----------------------------------------------------------------------");
        System.out.println(clazz.getPackageName());
        System.out.println("---getModule().getName()-----------------------------------------------------------------");
        System.out.println(clazz.getModule().getName());
        System.out.println("---getClassLoader()----------------------------------------------------------------------");
        System.out.println(clazz.getClassLoader());
        System.out.println();

        System.out.println("---getField('publicField')---------------------------------------------------------------");
        Field publicField = clazz.getField("publicField");
        System.out.println(publicField);
        System.out.println("---getFields()---------------------------------------------------------------------------");
        Field[] publicFields = clazz.getFields();
        for (Field f : publicFields) System.out.println(f);
        System.out.println("---getDeclaredFields()-------------------------------------------------------------------");
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) System.out.println(f);
        System.out.println();

        System.out.println("---getMethod('publicMethod', String.class)-----------------------------------------------");
        Method publicMethod = clazz.getMethod("publicMethod", String.class);
        System.out.println(publicMethod);
        System.out.println("---getMethods()--------------------------------------------------------------------------");
        Method[] publicMethods = clazz.getMethods();
        for (Method method : publicMethods) System.out.println(method);
        System.out.println("---getDeclaredMethods()------------------------------------------------------------------");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) System.out.println(method);
        System.out.println("---method.invoke(object, ...args)--------------------------------------------------------");
        TestClass object = new TestClass("arg");
        publicMethod.invoke(object, "arg");
        System.out.println();

        System.out.println("---getConstructor(String.class)----------------------------------------------------------");
        Constructor<?> publicConstructor = clazz.getConstructor(String.class);
        System.out.println(publicConstructor);
        System.out.println("---getConstructors()---------------------------------------------------------------------");
        Constructor<?>[] publicConstructors = clazz.getConstructors();
        for (Constructor<?> constructor : publicConstructors) System.out.println(constructor);
        System.out.println("---getDeclaredConstructors()-------------------------------------------------------------");
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) System.out.println(constructor);
        System.out.println("---constructor.newInstance(...args)------------------------------------------------------");
        TestClass newInstance = (TestClass) publicConstructor.newInstance("arg");
        System.out.println(newInstance);
        System.out.println();

        System.out.println("---getClasses()--------------------------------------------------------------------------");
        Class<?>[] publicClasses = clazz.getClasses();
        for (Class<?> c : publicClasses) System.out.println(c);
        System.out.println("---getDeclaredClasses--------------------------------------------------------------------");
        Class<?>[] classes = clazz.getDeclaredClasses();
        for (Class<?> c : classes) System.out.println(c);
        System.out.println();
    }
}

@Retention(RetentionPolicy.RUNTIME) // required to be accessed at runtime
@interface TestAnnotation {
}

@TestAnnotation
class TestClass {
    public static String publicStaticField;
    private static String privateStaticField;
    public String publicField;
    private String privateField;

    private TestClass() {
    }

    public TestClass(String parameter) {
        System.out.println("constructor invoked with argument:" + parameter);
    }

    private void privateMethod() {
    }

    public void publicMethod(String parameter) {
        System.out.println("method invoked with argument:" + parameter);
    }

    public class publicClass {
    }

    private class privateClass {
    }

    private static interface privateInterface {
    }
}

