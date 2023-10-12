package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        var x = "";
        System.out.println(x.getClass().getName());
        System.out.println(x.getClass().getPackageName());
        System.out.println(x.getClass().getModule().getName());
        System.out.println("--------------------");

        MyClassLoader myClassLoader = new MyClassLoader("C:\\Users\\sbszc\\workspace");
        Class<?> switches = myClassLoader.loadClass("switches.Switches2");
        System.out.println(switches.getClassLoader());
        System.out.println("--------------------");

        Class<?> clazzForName = Class.forName("reflection.Reflection$MyClass");
        System.out.println(clazzForName.getClassLoader());
        System.out.println("--------------------");

        System.out.println(clazzForName);
        System.out.println("--------------------");

        Class<?> clazz = MyClass.class;
        System.out.println(clazz);
        System.out.println("--------------------");

        Field publicField = clazz.getField("publicField");
        System.out.println(publicField);
        System.out.println("--------------------");

        Field[] publicFields = clazz.getFields();
        for (Field field : publicFields) {
            System.out.println(field);
        }
        System.out.println("--------------------");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        System.out.println("--------------------");

        Method publicMethod = clazz.getMethod("publicMethod");
        System.out.println(publicMethod);
        System.out.println("--------------------");

        Method[] publicMethods = clazz.getMethods();
        for (Method method : publicMethods) {
            System.out.println(method);
        }
        System.out.println("--------------------");

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println("--------------------");

        Class<?>[] publicClasses = clazz.getClasses();
        for (Class<?> c : publicClasses) {
            System.out.println(c);
        }
        System.out.println("--------------------");

        Class<?>[] classes = clazz.getDeclaredClasses();
        for (Class<?> c : classes) {
            System.out.println(c);
        }
        System.out.println("--------------------");

        Constructor<?> publicConstructor = clazzForName.getConstructor(int.class);
        System.out.println(publicConstructor);
        MyClass myClass = (MyClass) publicConstructor.newInstance(2);
        System.out.println(myClass);
        System.out.println("--------------------");

        Constructor<?>[] publicConstructors = clazzForName.getConstructors();
        for (Constructor<?> constructor : publicConstructors) {
            System.out.println(constructor);
        }
        System.out.println("--------------------");

        Constructor<?>[] constructors = clazzForName.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }
        System.out.println("--------------------");
    }

    static class MyClass {
        public static String publicStaticField;
        private static String privateStaticField;
        public String publicField;
        private String privateField;

        private MyClass() {
        }

        public MyClass(int parameter) {
        }

        private void privateMethod() {
        }

        public void publicMethod() {
        }

        public class publicClass {
        }

        private class privateClass {
        }

        private static interface privateInterface {
        }
    }
}

