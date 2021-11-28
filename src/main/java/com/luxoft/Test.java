package com.luxoft;


import com.luxoft.filemanager.FileManager;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        FileManager fileManager = new FileManager();

        int fileCount = fileManager.countFiles("D:\\BRAB\\urfgit1\\src\\html");
        int dirCount = fileManager.countDirs("D:\\BRAB\\tupoy\\dist\\public");
        System.out.println(fileCount);
        System.out.println(dirCount);

        fileManager.copy("D:\\Luxcampus\\test\\dir2", "D:\\Luxcampus\\test\\dir3");
        fileManager.move("D:\\Luxcampus\\test\\dir2", "D:\\Luxcampus\\test\\dir4");
    }

}