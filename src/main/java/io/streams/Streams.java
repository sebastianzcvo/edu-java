package io.streams;

import java.io.*;
import java.util.List;

public class Streams {
    public static void main(String[] args) throws FileNotFoundException {
        copyBytes();
        copyBufferedBytes();
        copyChars();
        copyLines();
        copyData();
        copyObjects();
    }

    private static void copyBytes() {
        try (InputStream is = new FileInputStream("io/streams/byte-input.txt");
             OutputStream os = new FileOutputStream("io/streams/byte-output.txt")) {
            int data = 0;
            while ((data = is.read()) != -1) {
                os.write(data);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void copyBufferedBytes() {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("io/streams/buffered-byte-input.txt"));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("io/streams/buffered-byte-output.txt"))) {
            int data = 0;
            while ((data = bis.read()) != -1) {
                bos.write(data);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void copyChars() {
        try (Reader r = new FileReader("io/streams/char-input.txt");
             Writer w = new FileWriter("io/streams/char-output.txt")) {
            int data = 0;
            while ((data = r.read()) != -1) {
                w.write(data);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void copyLines() {
        try (BufferedReader br = new BufferedReader(new FileReader("io/streams/lines-input.txt"));
             PrintWriter pw = new PrintWriter("io/streams/lines-output.txt")) {
            String data = "";
            while ((data = br.readLine()) != null) {
                pw.println(data);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void copyData() {
        // write
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("io/streams/data-input.txt"))) {
            dos.write(0x30);
            dos.writeBoolean(false);
            dos.writeChar('a');
            dos.writeChars("ab");
            dos.writeByte(0x30);
            dos.writeBytes("ab");
            dos.writeShort(Short.MAX_VALUE);
            dos.writeInt(Integer.MAX_VALUE);
            dos.writeLong(Long.MAX_VALUE);
            dos.writeFloat(Float.MAX_VALUE);
            dos.writeDouble(Double.MAX_VALUE);
            dos.writeUTF("MyUTF");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // read
        try (DataInputStream dis = new DataInputStream(new FileInputStream("io/streams/data-input.txt"))) {
            System.out.println(dis.read());
            System.out.println(dis.readBoolean());
            System.out.println(dis.readChar());
            System.out.printf("%c%c%n", dis.readChar(), dis.readChar());
            System.out.println(dis.readByte());
            System.out.printf("%c%c%n", dis.readByte(), dis.readByte());
            System.out.println(dis.readShort());
            System.out.println(dis.readInt());
            System.out.println(dis.readLong());
            System.out.println(dis.readFloat());
            System.out.println(dis.readDouble());
            System.out.println(dis.readUTF());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // copy
        try (DataInputStream dis = new DataInputStream(new FileInputStream("io/streams/data-input.txt"));
             DataOutputStream dos = new DataOutputStream(new FileOutputStream("io/streams/data-output.txt"))) {
            dos.write(dis.read());
            dos.writeBoolean(dis.readBoolean());
            dos.writeChar(dis.readChar());
            dos.writeChars(String.format("%c%c", dis.readChar(), dis.readChar()));
            dos.writeByte(dis.readByte());
            dos.writeBytes(String.format("%c%c", dis.readByte(), dis.readByte()));
            dos.writeShort(dis.readShort());
            dos.writeInt(dis.readInt());
            dos.writeLong(dis.readLong());
            dos.writeFloat(dis.readFloat());
            dos.writeDouble(dis.readDouble());
            dos.writeUTF(dis.readUTF());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void copyObjects() {
        // write
        try (ObjectOutput oo = new ObjectOutputStream(new FileOutputStream("io/streams/objects-input.txt"))) {
            oo.write(0x30);
            oo.writeBoolean(false);
            oo.writeChar('a');
            oo.writeChars("ab");
            oo.writeByte(0x30);
            oo.writeBytes("ab");
            oo.writeShort(Short.MAX_VALUE);
            oo.writeInt(Integer.MAX_VALUE);
            oo.writeLong(Long.MAX_VALUE);
            oo.writeFloat(Float.MAX_VALUE);
            oo.writeDouble(Double.MAX_VALUE);
            oo.writeUTF("MyUTF");
            oo.writeObject("MyString");
            oo.writeObject(new MySerializable(1));
            oo.writeObject(List.of(new MySerializable(2), new MySerializable(3)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // read
        try (ObjectInput oi = new ObjectInputStream(new FileInputStream("io/streams/objects-input.txt"))) {
            System.out.println(oi.read());
            System.out.println(oi.readBoolean());
            System.out.println(oi.readChar());
            System.out.printf("%c%c%n", oi.readChar(), oi.readChar());
            System.out.println(oi.readByte());
            System.out.printf("%c%c%n", oi.readByte(), oi.readByte());
            System.out.println(oi.readShort());
            System.out.println(oi.readInt());
            System.out.println(oi.readLong());
            System.out.println(oi.readFloat());
            System.out.println(oi.readDouble());
            System.out.println(oi.readUTF());
            System.out.println(oi.readObject());
            System.out.println(oi.readObject());
            System.out.println(oi.readObject());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // copy
        try (ObjectInput oi = new ObjectInputStream(new FileInputStream("io/streams/objects-input.txt"));
             ObjectOutput oo = new ObjectOutputStream(new FileOutputStream("io/streams/objects-output.txt"))) {
            oo.write(oi.read());
            oo.writeBoolean(oi.readBoolean());
            oo.writeChar(oi.readChar());
            oo.writeChars(String.format("%c%c", oi.readChar(), oi.readChar()));
            oo.writeByte(oi.readByte());
            oo.writeBytes(String.format("%c%c", oi.readByte(), oi.readByte()));
            oo.writeShort(oi.readShort());
            oo.writeInt(oi.readInt());
            oo.writeLong(oi.readLong());
            oo.writeFloat(oi.readFloat());
            oo.writeDouble(oi.readDouble());
            oo.writeUTF(oi.readUTF());
            oo.writeObject(oi.readObject());
            oo.writeObject(oi.readObject());
            oo.writeObject(oi.readObject());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static class MySerializable implements Serializable {
        private final int myProp;

        public MySerializable(int myProp) {
            this.myProp = myProp;
        }

        @Override
        public String toString() {
            return "MySerializable{" +
                    "myProp=" + myProp +
                    '}';
        }
    }
}


