package com.company;

public class Main {

    public static void main(String[] args) {
        HashMap1 project = new HashMap1();
        project.put("KING", 1);
        project.put("BLAKE", 3);
        project.put("Andrew", 213);
        //System.out.println(project.containsKey("BLAKE"));
        //System.out.println(project.containsValue(3)); // here is trouble. And the string upper
        System.out.println(project.size() + " SIZE");
    }


    public static int randomPassportNumber() {
        int beginPassport = 20000;
        int endPassport = 100000;
        return beginPassport + (int) (Math.random() * endPassport);
    }

    public static int randomData() {
        int beginData = 0;
        int endData = 4;
        return beginData + (int) (Math.random() * endData);
    }


}
