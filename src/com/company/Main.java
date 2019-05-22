package com.company;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.lang.*;

public class Main {
    private static final int START_RANGE = 33;
    private static final int END_RANGE = 126;

    public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException {

        long counter = 0;
        //PrintStream out = new PrintStream(new FileOutputStream("pw.txt"));
        //System.setOut(out);
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        String password = "Lamborghini";
        String passwordHash = bytesToHex(getHash(digest,password)) ;
        System.out.println(passwordHash);
        char[] start = new char[password.length()];

        // Wort mit den Start-Chars Initialisieren
        for(int i = 0; i < password.length();i++){
            start[i] = START_RANGE;
        }

        long t1
        while(true){
            counter++;
            long t3 = System.nanoTime();
            start = generateString(start).toCharArray();
            String hash = bytesToHex(getHash(digest,String.valueOf(start))) ;
            long t4 = System.nanoTime();
            if(passwordHash.equals(hash)){
                System.out.println("passwort ist " + String.valueOf(start) + ", hash ist " + hash + ", gefunden in " + counter + " versuchen");
                long t2 = System.nanoTime();
                System.out.println("Hash gefunden in " + ((t2-t1)/1000)/1000 + " ms");
                break;
            }
        }
    }

    private static byte[] getHash(MessageDigest dg,String pw) {
        byte[] encodedhash = dg.digest(pw.getBytes(StandardCharsets.UTF_8));
        return encodedhash;
    }

    private static String generateString(char[] vorgaenger){
        int index = vorgaenger.length - 1;

        while(true){
            if(vorgaenger[index] == END_RANGE){
                vorgaenger[index] = START_RANGE;
                index --;
            } else {
                vorgaenger[index]++;
                break;
            }
        }
        return String.valueOf(vorgaenger);
    }


    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
