package com.ogabek.paypal;

import java.util.HashMap;
import java.util.Map;

public class NumberToEnglishWords {
    /*
    Convert a non-negative integer to its english words representation.

    input: 5675
    output: Five thousand six hundred seventy five

    input:  3456789
    output Three million four hundred fifty six thousand seven hundred eighty nine

    input: 1234567890

    output: One billion two hundred thirty four million five hundred sixty seven thousand eight hundred ninety

    */

    public static void main(String[] args) {
        System.out.println(toEnglishWords(75));
        System.out.println(toEnglishWords(5675));
        System.out.println(toEnglishWords(3456789));
        System.out.println(toEnglishWords(1234567890));
    }

    public static Map<Integer, String> oneDigits = new HashMap<>();

    public static Map<Integer, String> twoDigits = new HashMap<>();

    static {
        oneDigits.put(1, "one");
        oneDigits.put(2, "two");
        oneDigits.put(3, "three");
        oneDigits.put(4, "four");
        oneDigits.put(5, "five");
        oneDigits.put(6, "six");
        oneDigits.put(7, "seven");
        oneDigits.put(8, "eight");
        oneDigits.put(9, "nine");

        twoDigits.put(10, "ten");
        twoDigits.put(20, "twenty");
        twoDigits.put(30, "thirty");
        twoDigits.put(40, "forty");
        twoDigits.put(50, "fifty");
        twoDigits.put(60, "sixty");
        twoDigits.put(70, "seventy");
        twoDigits.put(80, "eighty");
        twoDigits.put(90, "ninety");
    }

    public static String toEnglishWords(Integer number) {
        String words = "";
        int threeDigitCounter = 0;

        while (number > 0) {
            switch (threeDigitCounter) {
                case 1:
                    words = "thousand " + words;
                    break;
                case 2:
                    words = "million " + words;
                    break;
                case 3:
                    words = "billion " + words;
                    break;
            }
            words = conversionHelper(number % 1000) + words;

            number = number / 1000;
            threeDigitCounter++;
        }

        return words;
    }

    public static String conversionHelper(Integer number) {
        String words = "";
        Integer remainder = 0;
        int loopCounter = 1;

        for(int i = 10; i <= 1000; i=i*10 ) {
            if (number <= 0) break;
            remainder = number % i;
            if (remainder == 0) {
                loopCounter++;
                continue;
            }
            number -= remainder;

            if (loopCounter == 1) {
                words = oneDigits.get(remainder) + " " + words;
            } else if (loopCounter == 2) {
                words = twoDigits.get(remainder) + " " + words;
            } else {
                words = oneDigits.get(remainder/100) + " hundred " + words;
            }
            loopCounter++;
        }

        return words;
    }
}
