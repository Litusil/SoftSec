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

    public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException {
        PrintStream out = new PrintStream(new FileOutputStream("pw.txt"));
        //System.setOut(out);
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        ArrayList passwords = new ArrayList<>();
        String password = bytesToHex(getHash(digest,"test")) ;
        String start = "!!!!";

        while(true){
            //long t1 = System.nanoTime();
            start = generateString(start.toCharArray());
            String hash = bytesToHex(getHash(digest,start)) ;
            //long t2 = System.nanoTime();
            //System.out.println("Time pro hash in ms = " + (t2-t1));
            if(password.equals(hash)){
                System.out.println("passwort ist " + start + ", hash ist " + hash);
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
            if(vorgaenger[index] == 126){
                vorgaenger[index] = 33;
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
