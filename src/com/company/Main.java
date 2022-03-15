package com.company;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        //HashMap KEK = new HashMap(123,  31);
        HashMap1 project = new HashMap1();
        project.put("KING", 1);
        project.put("BLAKE", 3);
        project.put("KEk", 31);
        project.remove("KING");
        System.out.println(project.containsKey("BLAKE"));
        System.out.println(project.containsValue(3));
        System.out.println(project.size() + " SIZE");
    }
}
