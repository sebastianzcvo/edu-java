package io.files;

import java.io.File;
import java.io.IOException;

public class Files {
    public static void main(String[] args) throws IOException, InterruptedException {
        // File representing a directory
        File dir = new File("io/files/dir");
        if (dir.exists()) {
            System.out.printf("dir: '%s' exists%n", dir.getPath());
        } else {
            System.out.printf("dir: '%s' doesn't exist%n", dir.getPath());
            if (dir.mkdirs()) System.out.printf("dir: '%s' was created%n", dir.getPath());
            else System.out.printf("dir: '%s' was NOT created%n", dir.getPath());
        }
        System.out.printf("dir isFile(): %s%n", dir.isFile());
        System.out.printf("dir isDirectory(): %s%n", dir.isDirectory());
        System.out.printf("dir getName(): %s%n", dir.getName());
        System.out.printf("dir getParent(): %s%n", dir.getParent());
        System.out.printf("dir getPath(): %s%n", dir.getPath());
        System.out.printf("dir getAbsolutePath(): %s%n", dir.getAbsolutePath());
        System.out.printf("dir getCanonicalPath(): %s%n", dir.getCanonicalPath());
        System.out.print("dir list():"); for (String path : dir.list()) System.out.print(" " + path);
        System.out.println();
        System.out.printf("dir setReadOnly(): %s%n", dir.setReadOnly());
        System.out.printf("dir canRead(): %s%n", dir.canRead());
        System.out.printf("dir canWrite(): %s%n", dir.canWrite());
        System.out.printf("dir canExecute(): %s%n", dir.canExecute());
        System.out.printf("dir setReadable(): %s%n", dir.setReadable(false, true));
        System.out.printf("dir setWritable(): %s%n", dir.setWritable(false, true));
        System.out.printf("dir setExecutable(): %s%n", dir.setExecutable(false, true));
        System.out.printf("dir canRead(): %s%n", dir.canRead());
        System.out.printf("dir canWrite(): %s%n", dir.canWrite());
        System.out.printf("dir canExecute(): %s%n", dir.canExecute());

        System.out.println("---------------------------------------------------");

        // File representing a file
        File file = new File("io/files/dir/file.txt");
        if (file.exists()) {
            System.out.printf("file: '%s' exists%n", dir.getPath());
        } else {
            System.out.printf("file: '%s' doesn't exist%n", file.getPath());
            if (file.createNewFile()) System.out.printf("file: '%s' was created%n", file.getPath());
            else System.out.printf("file: '%s' was NOT created%n", file.getPath());
        }
        System.out.printf("file isFile(): %s%n", file.isFile());
        System.out.printf("file isDirectory(): %s%n", file.isDirectory());
        System.out.printf("file getName(): %s%n", file.getName());
        System.out.printf("file getParent(): %s%n", file.getParent());
        System.out.printf("file getPath(): %s%n", file.getPath());
        System.out.printf("file getAbsolutePath(): %s%n", file.getAbsolutePath());
        System.out.printf("file getCanonicalPath(): %s%n", file.getCanonicalPath());
//        System.out.print("file list():"); for (String path : file.list()) System.out.print(" " + path);
//        System.out.println(); list() returns null if this abstract pathname does not denote a directory
        System.out.printf("file setReadOnly(): %s%n", file.setReadOnly());
        System.out.printf("file canRead(): %s%n", file.canRead());
        System.out.printf("file canWrite(): %s%n", file.canWrite());
        System.out.printf("file canExecute(): %s%n", file.canExecute());
        System.out.printf("file setReadable(): %s%n", file.setReadable(false, true));
        System.out.printf("file setWritable(): %s%n", file.setWritable(false, true));
        System.out.printf("file setExecutable(): %s%n", file.setExecutable(false, true));
        System.out.printf("file canRead(): %s%n", file.canRead());
        System.out.printf("file canWrite(): %s%n", file.canWrite());
        System.out.printf("file canExecute(): %s%n", file.canExecute());


        File file1 = new File("C:\\Users\\sbszc\\workspace\\edu-java\\io\\files\\hello.txt"); // '/' '\' are both allowed
        file1.createNewFile();
        Thread.sleep(5_000);
        file1.delete();
    }
}
