package ru.alexnevskiy;

import java.util.Scanner;
import java.math.*;


public class IntegerPolynomial {
    public static String foolCheck(String str) {
        if (str.matches("(\\+?-?\\s*\\d*[x]*\\^*\\d*)*")) return str;
        else return "Некорректный ввод. Введите, пожалуйста, заново полином " + str;
    }


    public static String substitution(String str, String stringX) {
        String trim = str.replaceAll("\\s+", "").trim();
        String plus = trim.replaceAll("\\+", " + ").trim();
        String minus = plus.replaceAll("-", " - ");
        String[] split = minus.split("\\s");
        int x = Integer.parseInt(stringX);
        double var;
        if (split[0] == "-") {
            for (int i = 1; i < split.length; i += 2) {
                if (split[i].matches("\\d+[x]\\^")) {
                    String[] parse = split[i].split("[x]\\^");
                    var = Math.pow(x, Integer.parseInt(parse[1])) * Integer.parseInt(parse[0]);
                    split[i] = Double.toString(var);
                } else if (split[i].matches("[x]\\^")) {
                    String replaceX = split[i].replaceAll("[x]\\^", "");
                    var = Math.pow(x, Integer.parseInt(replaceX));
                    split[i] = Double.toString(var);
                } else if (split[i].matches("\\d+[x]")) {
                    String replaceX = split[i].replaceAll("[x]", "");
                    var = x * Integer.parseInt(replaceX);
                    split[i] = Double.toString(var);
                } else {
                    split[i] = Integer.toString(x);
                }
            }
        } else {
            for (int i = 0; i < split.length; i += 2) {
                if (split[i].matches("\\d+[x]\\^")) {
                    String[] parse = split[i].split("[x]\\^");
                    var = Math.pow(x, Integer.parseInt(parse[1])) * Integer.parseInt(parse[0]);
                    split[i] = Double.toString(var);
                } else if (split[i].matches("[x]\\^")) {
                    String replaceX = split[i].replaceAll("[x]\\^", "");
                    var = Math.pow(x, Integer.parseInt(replaceX));
                    split[i] = Double.toString(var);
                } else if (split[i].matches("\\d+[x]")) {
                    String replaceX = split[i].replaceAll("[x]", "");
                    var = x * Integer.parseInt(replaceX);
                    split[i] = Double.toString(var);
                } else {
                    split[i] = Integer.toString(x);
                }
            }
        }
        String string = "";
        for (int i = 0; i < split.length; i++) {
            string += split[i];
        }
        return string;
    }
        /*int calculation = 0;
        if (split[0] == "-") {
            calculation -= Integer.parseInt(split[1]);
            if (split.length == 1) return calculation;
            for (int i = 3; i < split.length; i += 2) {
                if (split[i - 1] == "+") calculation += Integer.parseInt(split[i]);
                else calculation -= Integer.parseInt(split[i]);
            }
        }
        else {
            calculation += Integer.parseInt(split[0]);
            if (split.length == 1) return calculation;
            for (int i = 2; i < split.length; i += 2) {
                if (split[i - 1] == "+") calculation += Integer.parseInt(split[i]);
                else calculation -= Integer.parseInt(split[i]);
            }
        }
        return calculation;
    }*/


    public static int calculation(String str) {
        String plus = str.replaceAll("\\+", " + ").trim();
        String minus = plus.replaceAll("-", " - ");
        String[] split = minus.split("\\s");
        int calculation = 0;
        if (split[0] == "-") {
            calculation -= Integer.parseInt(split[1]);
            if (split.length == 1) return calculation;
            for (int i = 3; i < split.length; i += 2) {
                if (split[i - 1] == "+") calculation += Integer.parseInt(split[i]);
                else calculation -= Integer.parseInt(split[i]);
            }
        }
        else {
            calculation = Integer.parseInt(split[0]);
            if (split.length == 1) return calculation;
            for (int i = 2; i < split.length; i += 2) {
                if (split[i - 1] == "+") calculation += Integer.parseInt(split[i]);
                else calculation -= Integer.parseInt(split[i]);
            }
        }
        return calculation;
    }


    public static String polynom(String str) {
        return "";
    }


    public static void main(String[] args) {
        Scanner input1 = new Scanner(System.in);
        System.out.println("Введите первый полином");
        String polynom1 = input1.nextLine();
        System.out.println(polynom1);
        Scanner input2 = new Scanner(System.in);
        System.out.println("Введите второй полином");
        String polynom2 = input2.nextLine();
        System.out.println(polynom2);
        Scanner input3 = new Scanner(System.in);
        System.out.println("Введите x");
        String x = input3.nextLine();
        System.out.println(x);
        System.out.println(foolCheck(polynom1));
        System.out.println(foolCheck(polynom2));
        System.out.println(substitution(polynom1, x));

        String trim = polynom1.replaceAll("\\s+", "").trim();
        String plus = trim.replaceAll("\\+", " + ").trim();
        String minus = plus.replaceAll("-", " - ");
        String[] split = minus.split("\\s");
        int X = Integer.parseInt(x);
        double var;
        if (split[0] == "-") {
            for (int i = 1; i < split.length; i += 2) {
                if (split[i].matches("\\d+[x]\\^")) {
                    String[] parse = split[i].split("[x]\\^");
                    var = Math.pow(X, Integer.parseInt(parse[1])) * Integer.parseInt(parse[0]);
                    split[i] = Double.toString(var);
                } else if (split[i].matches("[x]\\^")) {
                    String replaceX = split[i].replaceAll("[x]\\^", "");
                    var = Math.pow(X, Integer.parseInt(replaceX));
                    split[i] = Double.toString(var);
                } else if (split[i].matches("\\d+[x]")) {
                    String replaceX = split[i].replaceAll("[x]", "");
                    var = X * Integer.parseInt(replaceX);
                    split[i] = Double.toString(var);
                } else {
                    split[i] = Integer.toString(X);
                }
            }
        } else {
            for (int i = 0; i < split.length; i += 2) {
                if (split[i].matches("\\d+[x]\\^")) {
                    String[] parse = split[i].split("[x]\\^");
                    var = Math.pow(X, Integer.parseInt(parse[1])) * Integer.parseInt(parse[0]);
                    split[i] = Double.toString(var);
                } else if (split[i].matches("[x]\\^")) {
                    String replaceX = split[i].replaceAll("[x]\\^", "");
                    var = Math.pow(X, Integer.parseInt(replaceX));
                    split[i] = Double.toString(var);
                } else if (split[i].matches("\\d+[x]")) {
                    String replaceX = split[i].replaceAll("[x]", "");
                    var = X * Integer.parseInt(replaceX);
                    split[i] = Double.toString(var);
                } else {
                    split[i] = Integer.toString(X);
                }
            }
        }
        String string = "";
        for (int i = 0; i < split.length; i++) {
            string += split[i];
        }
        System.out.println(string);
    }
}
