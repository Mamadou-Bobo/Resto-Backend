package com.bobo.resto.shared.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class Utils {

    public static String generateCode(int length) {
        StringBuilder sb = new StringBuilder();
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

        Random random = new Random();

        for (int i = 0; i < length; i++) {

            int index = random.nextInt(alphaNumeric.length());

            char randomChar = alphaNumeric.charAt(index);

            sb.append(randomChar);
        }

        return sb.toString();
    }

    public static String generateToken() {
        return UUID.randomUUID().toString();
    }

    public static Date generateTokenExpiryDate(int codeValidity) {
        final Calendar cal = Calendar.getInstance();

        long timeInSecs = cal.getTimeInMillis();

        return new Date(timeInSecs + ((long) codeValidity * 60 * 1000));
    }
}