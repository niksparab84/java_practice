package org.nikhil.examples.coreJava.testArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class TestClass {
    int val = 10;

    public static void main(String args[] ) throws Exception {
        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        TestClass testClass = new TestClass();
        System.out.println("Hi, " + testClass.val+".");

    }
}

