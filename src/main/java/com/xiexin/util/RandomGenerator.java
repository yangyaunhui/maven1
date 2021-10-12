package com.xiexin.util;

import java.util.UUID;

public class RandomGenerator {

    private int length;

    public void setLength(int length) {
        this.length = length;
    }
    public RandomGenerator(int length){
        this.length=length;
    }
    public RandomGenerator(){
        this.length=32;
    }
    public String generate(){
        String s= UUID.randomUUID().toString();
        s=s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
        return s.substring(0,7);
    }

    public static void main(String[] args) {
        RandomGenerator randomGenerator=new RandomGenerator();
        String generate = randomGenerator.generate();
        System.out.println("generate = " + generate);
    }
}

