package com.company;

public class Main {

    public static void main(String[] args) {
        HashMap1 project = new HashMap1();
        project.put("KING", 1);
        project.put("BLAKE", 3);
        project.put("Andrew", 213);
        System.out.println(project.containsKey("BLAKE"));
        System.out.println(project.containsValue(3));
        System.out.println(project.size() + " SIZE");
    }
}
