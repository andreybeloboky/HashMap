package com.company;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        //HashMap KEK = new HashMap(123,  31);
        HashMap1 project = new HashMap1();
        project.put("KING", 1);
        project.put("BLAKE", 3);
        project.put("KEk", 31);
        project.put("kek", 3);
        project.put("fd", 3);
        project.put("sdf", 3);
        project.put("asdf", 3);
        project.put("Andrew", 213);
        project.put("Le", 3);
        project.put("Ke", 3);
        project.put("Di", 3);
        project.put("Hi", 3);
        project.put("Aha", 3);
        project.put("lol", 3);
        System.out.println(project.containsKey("BLAKE"));
        System.out.println(project.containsValue(3));
        System.out.println(project.size() + " SIZE");
    }
}
