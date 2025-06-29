package com.udemy.hashing.md5;

public class App {
    public static void main(String[] args) {

        MD5 md5 = new MD5();
//        String message = "Hashing algorithms are crucial in cryptography!";
        String message = "Hashing";
        System.out.println(md5.digest(message));
    }
}
