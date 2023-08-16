package com.Premkumar.ShortenURL.service;

import org.springframework.stereotype.Component;

@Component
public class LogicImplementation {


    private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
     static   private final char[] allowedCharacters = allowedString.toCharArray();
    static private final int base = allowedCharacters.length;

   static public String encode(int input){
        var encodedString = new StringBuilder();
        if(input == 0) {
            return String.valueOf(allowedCharacters[0]);
        }
        while (input > 0) {
            int val=input % base;
            encodedString.append(allowedCharacters[val]);
            input = input / base;
        }
        return encodedString.reverse().toString();
    }

    static public long decode(String input) {
        var characters = input.toCharArray();
        var length = characters.length;
        var decoded = 0;
        var counter = 1;
        for (char character : characters) {
            double power=Math.pow(base, length - counter);
            decoded += allowedString.indexOf(character) * power;
            counter++;
        }
        return decoded;
    }
}
