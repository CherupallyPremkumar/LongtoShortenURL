package com.Premkumar.ShortenURL.service;

import org.springframework.stereotype.Component;

@Component
public class LogicImplementation {
    private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final char[] allowedCharacters = allowedString.toCharArray();
    private final int base = allowedCharacters.length;

    public String encode(int input){
        var encodedString = new StringBuilder();
        if(input == 0) {
            return String.valueOf(allowedCharacters[0]);
        }
        while (input > 0) {
            encodedString.append(allowedCharacters[input % base]);
            input = input / base;
        }
        return encodedString.reverse().toString();
    }

    public long decode(String input) {
        var characters = input.toCharArray();
        var length = characters.length;
        var decoded = 0;
        var counter = 1;
        for (char character : characters) {
            decoded += allowedString.indexOf(character) * Math.pow(base, length - counter);
            counter++;
        }
        return decoded;
    }
}
