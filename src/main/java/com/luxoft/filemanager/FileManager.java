package com.luxoft.filemanager;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.List;

public class FileManager {

    /*
     * принимает путь к папке,
     * возвращает количество файлов в папке и всех подпапках по пути
     * */
    public static int countFiles(String path) throws NullPointerException {

        File[] innerFiles = getFilesList(path);
        int fileCounter = 0;

        if (innerFiles.length != 0) {
            for (File innerFile : innerFiles) {
                if (innerFile.isDirectory()) {
                    fileCounter += countFiles(innerFile.getAbsolutePath());
                } else if (innerFile.isFile()) {
                    fileCounter++;
                }
            }

            return fileCounter;
        } else {
            return 0;
        }

    }

    /*
     * принимает путь к папке,
     * возвращает количество папок в папке и всех подпапках по пути
     * */
    public static int countDirs(String path) throws NullPointerException {

        File[] innerFiles = getFilesList(path);
        int dirCounter = 0;

        if (innerFiles.length != 0) {
            for (File innerFile : innerFiles) {
                if (innerFile.isDirectory()) {
                    dirCounter++;
                    dirCounter += countDirs(innerFile.getAbsolutePath());
                }
            }
            return dirCounter;
        } else {
            return 0;
        }
    }


    /*
     * метод по копированию папок и файлов.
     * from - путь к файлу или папке,
     * to - путь к папке куда будет производиться копирование.
     * */

    public static void copy(String from, String to) throws IOException {
        File source = new File(from);
        File destDir = new File(to);

        if (!destDir.isFile()) {
            if (source.isFile()) {
                copyFile(source, destDir);
            } else if (source.isDirectory()){
                copyDir(source, destDir);
            }
        } else {
            throw new NullPointerException("File can't be sestination");
        }
    }

    /*
     * метод по перемещению папок и файлов.
     * from - путь к файлу или папке,
     * to - путь к папке куда будет производиться копирование.
     * */

    public static void move(String from, String to) throws IOException {
        new File(from).renameTo(new File(to));
    }

    private static File[] getFilesList(String path) throws NullPointerException {

        File[] innerFiles = null;
        File dir = new File(path);

        try {
            innerFiles = dir.listFiles();
        } catch (Exception e) {
            throw new NullPointerException("File doesn't exist");
        }

        return innerFiles;
    }

    private static void copyFile(@NotNull File source, @NotNull File destination) throws IOException {

        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;

        File destFile = new File(destination.getPath(), source.getName());

        try {

            inputStream = new FileInputStream(source);
            outputStream = new FileOutputStream(destFile);

            byte[] fileContent = inputStream.readAllBytes();
            outputStream.write(fileContent);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
            outputStream.close();
        }
    }

    private static void copyDir(File source, @NotNull File destination) throws IOException {
        if (!destination.exists()){
            destination.mkdir();
        }

        File[] sourceFiles = source.listFiles();

        for (File sourceFile : sourceFiles) {
            if (sourceFile.isFile()) {
                copyFile(sourceFile, destination);
            } else {
                copyDir(sourceFile, new File(destination.getPath(), sourceFile.getName()));
            }
        }

    }


}
