package classLoaders;

import java.io.*;

public class ClassLoaders extends ClassLoader {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoaders myClassLoader = new ClassLoaders("C:\\Users\\sbszc\\workspace\\edu-java");
        Class<?> switches = myClassLoader.loadClass("classLoaders.ClassToLoad");
        System.out.println(switches.getClassLoader());
        System.out.println("--------------------");
    }

    private final String classpath;

    public ClassLoaders(String classpath) {
        this.classpath = classpath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = loadClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] loadClassData(String name) {
        String classFile = classpath
                + File.separatorChar
                + name.replace('.', File.separatorChar)
                + ".class";
        try (FileInputStream fis = new FileInputStream(classFile);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int readBytes;
            while ((readBytes = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, readBytes);
            }
            return baos.toByteArray();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
