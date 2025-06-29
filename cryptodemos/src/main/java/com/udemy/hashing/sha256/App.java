package com.udemy.hashing.sha256;

import com.udemy.hashing.md5.MD5;

public class App {

    public static void main(String[] args) {
        SHA256 sha256 = new SHA256();
//        String message = "Hashing algorithms are crucial in cryptography!";
        String message = "Hashing";
        System.out.println(sha256.digest(message));
    }
}
