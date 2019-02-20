package ru.alexnevskiy;

import java.util.Arrays;
import java.util.Scanner;


public class IntegerPolynomial {
    public static String foolCheck(String str) {
        if (str.matches("(\\+?-?\\s*\\d*[x]*\\^*\\d*)*")) return "Полином " + str + " удовлетворяет условиям ввода";
        else return "Некорректный ввод. Введите, пожалуйста, заново полином " + str;
    }


    public static int valueCalculation(String str, String stringX) {
        String trim = str.replaceAll("\\s+", "").trim();
        String plus = trim.replaceAll("\\+", " + ").trim();
        String minus = plus.replaceAll("-", " - ").trim();
        String[] split = minus.split("\\s");
        int X = Integer.parseInt(stringX);
        if (split[0].equals("-")) {
            for (int i = 1; i < split.length; i += 2) {
                if (split[i].matches("\\d+[x]\\^\\d+")) {
                    String[] parse = split[i].split("[x]\\^");
                    int var = (int) Math.pow(X, Integer.parseInt(parse[1])) * Integer.parseInt(parse[0]);
                    split[i] = Integer.toString(var);
                } else if (split[i].matches("[x]\\^\\d+")) {
                    String replaceX = split[i].replaceAll("[x]\\^", "");
                    int var = (int) Math.pow(X, Integer.parseInt(replaceX));
                    split[i] = Integer.toString(var);
                } else if (split[i].matches("\\d+[x]")) {
                    String replaceX = split[i].replaceAll("[x]", "");
                    int var = X * Integer.parseInt(replaceX);
                    split[i] = Integer.toString(var);
                } else if (split[i].matches("[x]")) {
                    split[i] = Integer.toString(X);
                } else {
                    split[i] = split[i];
                }
            }
        } else {
            for (int i = 0; i < split.length; i += 2) {
                if (split[i].matches("\\d+[x]\\^\\d+")) {
                    String[] parse = split[i].split("[x]\\^");
                    int var = (int) Math.pow(X, Integer.parseInt(parse[1])) * Integer.parseInt(parse[0]);
                    split[i] = Integer.toString(var);
                } else if (split[i].matches("[x]\\^\\d+")) {
                    String replaceX = split[i].replaceAll("[x]\\^", "");
                    int var = (int) Math.pow(X, Integer.parseInt(replaceX));
                    split[i] = Integer.toString(var);
                } else if (split[i].matches("\\d+[x]")) {
                    String replaceX = split[i].replaceAll("[x]", "");
                    int var = X * Integer.parseInt(replaceX);
                    split[i] = Integer.toString(var);
                } else if (split[i].matches("[x]")) {
                    split[i] = Integer.toString(X);
                } else {
                    split[i] = split[i];
                }
            }
        }
        String string = "";
        for (int i = 0; i < split.length; i++) {
            string += split[i];
        }
        return calculation(string);
    }


    private static int calculation(String str) {
        String plus = str.replaceAll("\\+", " + ").trim();
        String minus = plus.replaceAll("-", " - ");
        String[] split = minus.split("\\s");
        int calculation = 0;
        if (split[0].equals("-")) {
            calculation -= Integer.parseInt(split[1]);
            if (split.length == 1) return calculation;
            for (int i = 3; i < split.length; i += 2) {
                if (split[i - 1].equals("+")) calculation += Integer.parseInt(split[i]);
                else calculation -= Integer.parseInt(split[i]);
            }
        }
        else {
            calculation = Integer.parseInt(split[0]);
            if (split.length == 1) return calculation;
            for (int i = 2; i < split.length; i += 2) {
                if (split[i - 1].equals("+")) calculation += Integer.parseInt(split[i]);
                else calculation -= Integer.parseInt(split[i]);
            }
        }
        return calculation;
    }


    public static String polynomEquals(String str1, String str2) {
        String trim1 = str1.replaceAll("\\s+", "").trim();
        String minus1 = trim1.replaceAll("-", "  -").trim();
        String plus1 = minus1.replaceAll("\\+", "  ");
        String[] split1 = plus1.split("\\s{2}");
        String trim2 = str2.replaceAll("\\s+", "").trim();
        String minus2 = trim2.replaceAll("-", "  -").trim();
        String plus2 = minus2.replaceAll("\\+", "  ");
        String[] split2 = plus2.split("\\s{2}");
        if (split1.length != split2.length) return "Полиномы не равны";
        else {
            Arrays.sort(split1);
            Arrays.sort(split2);
        }
        if (Arrays.equals(split1, split2)) return "Полиномы равны";
        else return "Полиномы не равны";
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
        System.out.println(valueCalculation(polynom1, x));
        System.out.println(polynomEquals(polynom1, polynom2));
    }
}
