package ru.alexnevskiy;

import java.util.*;


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
        String minus = plus.replaceAll("-", " - ").trim();
        String[] split = minus.split("\\s");
        int calculation = 0;
        if (split[0].equals("-")) {
            calculation -= Integer.parseInt(split[1]);
            if (split.length == 1) return calculation;
            for (int i = 3; i < split.length; i += 2) {
                if (split[i - 1].equals("+")) calculation += Integer.parseInt(split[i]);
                else calculation -= Integer.parseInt(split[i]);
            }
        } else {
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


    private static Map mapMaker(String str) {
        Map<String, Integer> map = new TreeMap<>();
        String trim = str.replaceAll("\\s+", "").trim();
        String minus = trim.replaceAll("-", "  -").trim();
        String plus = minus.replaceAll("\\+", "  +").trim();
        String[] split = plus.split("\\s{2}");
        for (int i = 0; i < split.length; i++) {
            if (split[i].matches("[+-]?\\d+[x]\\^\\d+")) {
                String[] parse = split[i].split("[x]\\^");
                map.put("x^" + parse[1], Integer.parseInt(parse[0]));
            } else if (split[i].matches("[+-]?[x]\\^\\d+")) {
                String[] parse = split[i].split("[x]\\^");
                map.put("x^" + parse[0], 1);
            } else if (split[i].matches("[+-]?\\d+[x]")) {
                String[] parse = split[i].split("[x]");
                map.put("x", Integer.parseInt(parse[0]));
            } else if (split[i].matches("[+]?[x]")) {
                map.put("x", 1);
            } else if (split[i].matches("[-]?[x]")) {
                map.put("x", -1);
            } else {
                map.put("", Integer.parseInt(split[i]));
            }
        }
        return map;
    }


    private static Map integerMapMaker(String str) {
        Map<Integer, Integer> map = new TreeMap<>();
        String trim = str.replaceAll("\\s+", "").trim();
        String minus = trim.replaceAll("-", "  -").trim();
        String plus = minus.replaceAll("\\+", "  +").trim();
        String[] split = plus.split("\\s{2}");
        for (int i = 0; i < split.length; i++) {
            if (split[i].matches("[+-]?\\d+[x]\\^\\d+")) {
                String[] parse = split[i].split("[x]\\^");
                map.put(Integer.parseInt(parse[1]), Integer.parseInt(parse[0]));
            } else if (split[i].matches("[+-]?[x]\\^\\d+")) {
                String[] parse = split[i].split("[x]\\^");
                map.put(Integer.parseInt(parse[1]), 1);
            } else if (split[i].matches("[+-]?\\d+[x]")) {
                String[] parse = split[i].split("[x]");
                map.put(1, Integer.parseInt(parse[0]));
            } else if (split[i].matches("[+]?[x]")) {
                map.put(1, 1);
            } else if (split[i].matches("[-]?[x]")) {
                map.put(1, -1);
            } else {
                map.put(0, Integer.parseInt(split[i]));
            }
        }
        return map;
    }


    public static String additionOfPolynomials(String str1, String str2) {
        Map<String, Integer> map1 = mapMaker(str1);
        Map<String, Integer> map2 = mapMaker(str2);
        Map<String, Integer> map3 = new TreeMap<>();
        String answer = new String();
        map3.putAll(map1);
        for (String key : map2.keySet()) {
            if (map3.containsKey(key)) map3.put(key, map3.get(key) + map2.get(key));
            else map3.put(key, map2.get(key));
        }
        map3.values().removeIf(value -> value.equals(0));
        ArrayList keyList = new ArrayList(map3.keySet());
        for (int i = keyList.size() - 1; i >= 0; i--) {
            Object key = keyList.get(i);
            if (key.equals(keyList.get(keyList.size() - 1))) answer += "" + map3.get(key) + key;
            else if (map3.get(key) > 0) answer += "+" + map3.get(key) + key;
            else answer += "" + map3.get(key) + key;
        }
        return answer;
    }


    public static String subtractionOfPolynomials(String str1, String str2) {
        Map<String, Integer> map1 = mapMaker(str1);
        Map<String, Integer> map2 = mapMaker(str2);
        Map<String, Integer> map3 = new TreeMap<>();
        String answer = new String();
        map3.putAll(map1);
        for (String key : map2.keySet()) {
            if (map3.containsKey(key)) map3.put(key, map3.get(key) - map2.get(key));
            else map3.put(key, -map2.get(key));
        }
        map3.values().removeIf(value -> value.equals(0));
        ArrayList keyList = new ArrayList(map3.keySet());
        for (int i = keyList.size() - 1; i >= 0; i--) {
            Object key = keyList.get(i);
            if (key.equals(keyList.get(keyList.size() - 1))) answer += "" + map3.get(key) + key;
            else if (map3.get(key) > 0) answer += "+" + map3.get(key) + key;
            else answer += "" + map3.get(key) + key;
        }
        return answer;
    }


    public static String multiplicationOfPolynomials(String str1, String str2) {
        Map<Integer, Integer> map1 = integerMapMaker(str1);
        Map<Integer, Integer> map2 = integerMapMaker(str2);
        Map<Integer, Integer> map3 = new TreeMap<>();
        String answer = new String();
        for (Integer key1 : map1.keySet()) {
            for (Integer key2 : map2.keySet()) {
                if (!map3.containsKey(key1 + key2)) map3.put(key1 + key2, map1.get(key1) * map2.get(key2));
                else map3.put(key1 + key2, map3.get(key1 + key2) + map1.get(key1) * map2.get(key2));
            }
        }
        ArrayList keyList = new ArrayList(map3.keySet());
        for (int i = keyList.size() - 1; i >= 0; i--) {
            Object key = keyList.get(i);
            if (key.equals(keyList.get(keyList.size() - 1))) {
                if (key.equals(0)) answer += map3.get(key);
                else if (key.equals(1)) {
                    if (map3.get(key).equals(1)) answer += "x";
                    else if (map3.get(key).equals(-1)) answer += "-x";
                    else answer += map3.get(key) + "x";
                }
                else {
                    if (map3.get(key).equals(1)) answer += "x^" + key;
                    else if (map3.get(key).equals(-1)) answer += "-x^" + key;
                    else answer += map3.get(key) + "x^" + key;
                }
            } else {
                if (key.equals(0)) {
                    if (map3.get(key) > 0) answer += "+" + map3.get(key);
                    else answer += map3.get(key);
                }
                else if (key.equals(1)) {
                    if (map3.get(key).equals(1)) answer += "+x";
                    else if (map3.get(key).equals(-1)) answer += "-x";
                    else if (map3.get(key) > 1) answer += "+" + map3.get(key) + "x";
                    else answer += map3.get(key) + "x";
                }
                else {
                    if (map3.get(key).equals(1)) answer += "+x^" + key;
                    else if (map3.get(key).equals(-1)) answer += "-x^" + key;
                    else if (map3.get(key) > 1) answer += "+" + map3.get(key) + "x^" + key;
                    else answer += map3.get(key) + "x^" + key;
                }
            }
        }
        return answer;
    }


    public static String divisionOfPolynomials(String str1, String str2) {

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
        System.out.println(valueCalculation(polynom1, x));
        System.out.println(polynomEquals(polynom1, polynom2));
        System.out.println(additionOfPolynomials(polynom1, polynom2));
        System.out.println(subtractionOfPolynomials(polynom1, polynom2));
        System.out.println(multiplicationOfPolynomials(polynom1, polynom2));
        System.out.println(mapMaker(polynom1));


    }
}
