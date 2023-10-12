package io.serializableAndExternalizable;

import java.io.*;
import java.util.Objects;

// Externalizable extends Serializable, so externalizable objects are serializable as well
public class Externalizables {
    public static void main(String[] args) {
        try (ObjectOutput oo = new ObjectOutputStream(new FileOutputStream("io/serializableAndExternalizable/externalizable.txt"));
             ObjectInput oi = new ObjectInputStream(new FileInputStream("io/serializableAndExternalizable/externalizable.txt"))) {

            MyExternalizable writtenObject = new MyExternalizable(1, 1, 1, new MyInnerExternalizable(1, 1));
            oo.writeObject(writtenObject);
            System.out.println("writtenObject: " + writtenObject);
            MyExternalizable readObject = (MyExternalizable) oi.readObject();
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

    static class MyParent {
        public int parentProp;

        public MyParent(int parentProp) {
            this.parentProp = parentProp;
        }

        public MyParent() {
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            MyParent myParent = (MyParent) object;
            return parentProp == myParent.parentProp;
        }

        @Override
        public int hashCode() {
            return Objects.hash(parentProp);
        }
    }

    static class MyExternalizable
            extends MyParent // parent doesn't have to be serializable, since it's properties are serialized by writeExternal(out) and readExternal(in) methods
            implements Externalizable { // serialization process uses writeExternal(out) and readExternal(in) methods, so you have control over which properties to hidde
        private int privateProp;
        public int publicProp;
        public MyInnerExternalizable myInnerExternalizable; // inner objects have to be serializable as well, or it throws java.io.NotSerializableException

        public MyExternalizable(int parentProp, int privateProp, int publicProp, MyInnerExternalizable myInnerExternalizable) {
            super(parentProp);
            this.privateProp = privateProp;
            this.publicProp = publicProp;
            this.myInnerExternalizable = myInnerExternalizable;
        }

        public MyExternalizable() { // externalizable requires no-args constructor
        }

        @Override
        public String toString() {
            return "MyExternalizable{" +
                    "privateProp=" + privateProp +
                    ", publicProp=" + publicProp +
                    ", myInnerExternalizable=" + myInnerExternalizable +
                    ", parentProp=" + parentProp +
                    '}';
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            if (!super.equals(object)) return false;
            MyExternalizable that = (MyExternalizable) object;
            return privateProp == that.privateProp && publicProp == that.publicProp && Objects.equals(myInnerExternalizable, that.myInnerExternalizable);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), privateProp, publicProp, myInnerExternalizable);
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeInt(parentProp); // parent's properties

            out.writeInt(privateProp);
            out.writeInt(publicProp);
            out.writeObject(myInnerExternalizable);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            parentProp = in.readInt();

            privateProp = in.readInt();
            publicProp = in.readInt();
            myInnerExternalizable = (MyInnerExternalizable) in.readObject();
        }
    }

    static class MyInnerExternalizable implements Externalizable {
        private int privateProp;
        public int publicProp;

        public MyInnerExternalizable(int privateProp, int publicProp) {
            this.privateProp = privateProp;
            this.publicProp = publicProp;
        }

        public MyInnerExternalizable() {
        }

        @Override
        public String toString() {
            return "MyInnerExternalizable{" +
                    "privateProp=" + privateProp +
                    ", publicProp=" + publicProp +
                    '}';
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            MyInnerExternalizable that = (MyInnerExternalizable) object;
            return privateProp == that.privateProp && publicProp == that.publicProp;
        }

        @Override
        public int hashCode() {
            return Objects.hash(privateProp, publicProp);
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeInt(privateProp);
            out.writeInt(publicProp);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            privateProp = in.readInt();
            publicProp = in.readInt();
        }
    }
}