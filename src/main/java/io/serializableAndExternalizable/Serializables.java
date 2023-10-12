package io.serializableAndExternalizable;

import java.io.*;
import java.util.Objects;

// Java serialization is platform independent and uses serialVersionUID for serialization version control
// has to implement Serializable to be written and read using ObjectInput.readObject() and ObjectOutput.writeObject()
public class Serializables {
    public static void main(String[] args) {
        try (ObjectOutput oo = new ObjectOutputStream(new FileOutputStream("io/serializableAndExternalizable/serializable.txt"));
             ObjectInput oi = new ObjectInputStream(new FileInputStream("io/serializableAndExternalizable/serializable.txt"))) {

            MySerializable writtenObject = new MySerializable(1, 1, new MyInnerSerializable(1));
            oo.writeObject(writtenObject);
            System.out.println("writtenObject: " + writtenObject);
            MySerializable readObject = (MySerializable) oi.readObject();
            System.out.println("readObject: " + readObject);

            System.out.println("== " + (writtenObject == readObject));
            System.out.println("equals() " + writtenObject.equals(readObject));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static class MyParentSerializable implements Serializable {
        public int parentProp;

        public MyParentSerializable() { // required for deserialization if not serializable
        }

        public MyParentSerializable(int parentProp) {
            this.parentProp = parentProp;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            MyParentSerializable that = (MyParentSerializable) object;
            return parentProp == that.parentProp;
        }

        @Override
        public int hashCode() {
            return Objects.hash(parentProp);
        }
    }

    // In the deserialization process, it is required that all the parent classes of instance should be Serializable;
    // and if any super-class in the hierarchy is not Serializable then it must have a default constructor. Now it makes sense.
    // So, while deserialization the super most class is searched first until any non-serializable class is found.
    // If all superclasses are serializable then JVM ends up reaching Object class itself and create an instance of Object class first.
    // If in between searching the superclasses, any class is found non-serializable then its default constructor will be used to allocate an instance in memory.
    static class MySerializable
            extends MyParentSerializable // if not serializable, then parent's properties won't be serialized, BUT the parent requires an accessible no-args constructor to be deserialized
            implements Serializable { // follows the default serialization process, so you DON'T have control over which properties to hidde

        private int prop;
        private MyInnerSerializable myInnerSerializable; // inner objects have to be serializable as well, or it throws java.io.NotSerializableException

        public MySerializable(int parentProp, int prop, MyInnerSerializable myInnerSerializable) {
            super(parentProp);
            this.prop = prop;
            this.myInnerSerializable = myInnerSerializable;
        }

        @Override
        public String toString() {
            return "MySerializable{" +
                    "prop=" + prop +
                    ", myInnerSerializable=" + myInnerSerializable +
                    ", parentProp=" + parentProp +
                    '}';
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            if (!super.equals(object)) return false;
            MySerializable that = (MySerializable) object;
            return prop == that.prop && Objects.equals(myInnerSerializable, that.myInnerSerializable);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), prop, myInnerSerializable);
        }
    }

    static class MyInnerSerializable implements Serializable {
        private int innerProp;

        public MyInnerSerializable(int innerProp) {
            this.innerProp = innerProp;
        }

        @Override
        public String toString() {
            return "MyInnerSerializable{" +
                    "innerProp=" + innerProp +
                    '}';
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            MyInnerSerializable that = (MyInnerSerializable) object;
            return innerProp == that.innerProp;
        }

        @Override
        public int hashCode() {
            return Objects.hash(innerProp);
        }
    }
}
