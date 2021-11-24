package com.luxoft;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Test {
    public static String test() {

        String s = null;

        try {

            s.length();

            s +=1;

            return s;

        } catch(Exception e) {

            s +=2;

            return s;

        } finally {

            s +=3;

            return s;

        }

    }



    public static void main(String[] args) {

        System.out.print(test());

    }


}

