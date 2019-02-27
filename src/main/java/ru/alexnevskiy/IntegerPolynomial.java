package ru.alexnevskiy;

import java.util.*;

public final class IntegerPolynomial {

    private final List<Integer> factors;

    // [1, 2, 3, 4, 5, 6, 7] == 7x^6 + 6x^5 + 5x^4 + .. + 1
    public IntegerPolynomial(List<Integer> input) {
        List<Integer> list = new ArrayList<>(input);
        factors = list;
    }

    public IntegerPolynomial(String input) {
        List<Integer> list = toList(input);
        factors = list;
    }

    // IntegerPolynomial obj1 = new IntegerPolynomial("...");
    // IntegerPolynomial obj2 = new IntegerPolynomial("...");
    // IntegerPolynomial obj3 = obj1.plus(obj2);
    public IntegerPolynomial plus(IntegerPolynomial other) {
        List<Integer> minList;
        List<Integer> maxList;
        if (factors.size() <= other.factors.size()) {
            minList = factors;
            maxList = other.factors;
        }
        else {
            minList = other.factors;
            maxList = factors;
        }
        List<Integer> answer = new ArrayList<>();
        int i = 0;
        while (i < minList.size()) {
            answer.add(minList.get(i) + maxList.get(i));
            i++;
        }
        while (i < maxList.size()) {
            answer.add(maxList.get(i));
            i++;
        }
        if (answer.isEmpty()) return new IntegerPolynomial("0");
        else return new IntegerPolynomial(answer);
    }

    private List<Integer> toList(String str) {
        List<Integer> list = new ArrayList<>();
        String trim = str.replaceAll("\\s+", "").trim();
        String minus = trim.replaceAll("-", "  -").trim();
        String plus = minus.replaceAll("\\+", "  +").trim();
        String[] split = plus.split("\\s{2}");
        Integer max = -1;
        for (int i = 0; i < split.length; i++) {
            if (split[i].matches("[+-]?\\d*[x]\\^\\d+")) {
                String[] parse = split[i].split("[x]\\^");
                if (Integer.parseInt(parse[1]) > max) max = Integer.parseInt(parse[1]);
            } else if (split[i].matches("[+-]?\\d*[x]")){
                String[] parse = split[i].split("[x]");
                if (1 > max) max = Integer.parseInt(parse[1]);
            } else {
                if (0 > max) max = 0;
            }
        }
        for (int i = 0; i <= max; i++) {
            for (int j = 0; j < split.length; j++) {
                if (split[j].matches("[+-]?\\d+[x]\\^\\d+")) {
                    String[] parse = split[j].split("[x]\\^");
                    if (Integer.parseInt(parse[1]) == i) {
                        list.add(Integer.parseInt(parse[0]));
                        break;
                    }
                } else if (split[j].matches("[+]?[x]\\^\\d+")) {
                    String[] parse = split[j].split("[x]\\^");
                    if (Integer.parseInt(parse[1]) == i) {
                        list.add(1);
                        break;
                    }
                } else if (split[j].matches("[-]?[x]\\^\\d+")) {
                    String[] parse = split[j].split("[x]\\^");
                    if (Integer.parseInt(parse[1]) == i) {
                        list.add(-1);
                        break;
                    }
                } else if (split[j].matches("[+-]?\\d+[x]")) {
                    String[] parse = split[j].split("[x]");
                    if (1 == i) {
                        list.add(Integer.parseInt(parse[0]));
                        break;
                    }
                } else if (split[j].matches("[+]?[x]")) {
                    if (1 == i) {
                        list.add(1);
                        break;
                    }
                } else if (split[j].matches("[-]?[x]")) {
                    if (1 == i) {
                        list.add(-1);
                        break;
                    }
                } else {
                    if (0 == i) {
                        list.add(Integer.parseInt(split[j]));
                        break;
                    }
                }
                if (j == split.length - 1) list.add(0);
            }
        }
        return list;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other instanceof IntegerPolynomial) {
            IntegerPolynomial newOther = (IntegerPolynomial) other;
            return factors.equals(newOther.factors);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(factors);
    }

    @Override
    public String toString() {
        String answer = "";
        for (int i = factors.size() - 1; i >= 0; i--) {
            if (i == factors.size() - 1) {
                if (i > 1) {
                    if (factors.get(i) == 1) answer += "x^" + i;
                    else if (factors.get(i) == -1) answer += "-x^" + i;
                    else if (factors.get(i) != 0) answer += factors.get(i) + "x^" + i;
                    else continue;
                }
                if (i == 1) {
                    if (factors.get(i) == 1) answer += "x";
                    else if (factors.get(i) == -1) answer += "-x";
                    else if (factors.get(i) != 0) answer += factors.get(i) + "x";
                    else continue;
                }
                if (i == 0) {
                    if (factors.get(i) != 0) answer += factors.get(i);
                }
            } else {
                if (i > 1) {
                    if (factors.get(i) == 1) answer += "+x^" + i;
                    else if (factors.get(i) == -1) answer += "-x^" + i;
                    else if (factors.get(i) > 1) answer += "+" + factors.get(i) + "x^" + i;
                    else if (factors.get(i) < -1) answer += factors.get(i) + "x^" + i;
                    else continue;
                }
                if (i == 1) {
                    if (factors.get(i) == 1) answer += "+x";
                    else if (factors.get(i) == -1) answer += "-x";
                    else if (factors.get(i) > 1) answer += "+" + factors.get(i) + "x";
                    else if (factors.get(i) < -1) answer += factors.get(i) + "x";
                    else continue;
                }
                if (i == 0) {
                    if (factors.get(i) >= 1) answer += "+" + factors.get(i);
                    if (factors.get(i) <= -1) answer += factors.get(i);
                }
            }

        }
        return answer;
    }

    public IntegerPolynomial minus(IntegerPolynomial other) {
        return  new IntegerPolynomial(factors);
    }

    public IntegerPolynomial value(IntegerPolynomial other) {
        return  new IntegerPolynomial(factors);
    }

    public IntegerPolynomial multiplication(IntegerPolynomial other) {
        return  new IntegerPolynomial(factors);
    }

    public IntegerPolynomial division(IntegerPolynomial other) {
        return  new IntegerPolynomial(factors);
    }

    public IntegerPolynomial remainder(IntegerPolynomial other) {
        return  new IntegerPolynomial(factors);
    }

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
        List<Integer> list = new ArrayList<>();
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
        Map<Integer, Integer> map1 = integerMapMaker(str1);
        Map<Integer, Integer> map2 = integerMapMaker(str2);
        Map<Integer, Integer> map3 = new TreeMap<>();
        map3.putAll(map1);
        for (Integer key : map2.keySet()) {
            if (map3.containsKey(key)) map3.put(key, map3.get(key) + map2.get(key));
            else map3.put(key, map2.get(key));
        }
        return mapToString(map3);
    }


    public static String subtractionOfPolynomials(String str1, String str2) {
        Map<Integer, Integer> map1 = integerMapMaker(str1);
        Map<Integer, Integer> map2 = integerMapMaker(str2);
        Map<Integer, Integer> map3 = new TreeMap<>();
        map3.putAll(map1);
        for (Integer key : map2.keySet()) {
            if (map3.containsKey(key)) map3.put(key, map3.get(key) - map2.get(key));
            else map3.put(key, -map2.get(key));
        }
        return mapToString(map3);
    }


    private static String mapToString(Map<Integer, Integer> map) {
        String answer = new String();
        map.values().removeIf(value -> value.equals(0));
        ArrayList keyList = new ArrayList(map.keySet());
        for (int i = keyList.size() - 1; i >= 0; i--) {
            Object key = keyList.get(i);
            if (key.equals(keyList.get(keyList.size() - 1))) {
                if (key.equals(0)) answer += map.get(key);
                else if (key.equals(1)) {
                    if (map.get(key).equals(1)) answer += "x";
                    else if (map.get(key).equals(-1)) answer += "-x";
                    else answer += map.get(key) + "x";
                }
                else {
                    if (map.get(key).equals(1)) answer += "x^" + key;
                    else if (map.get(key).equals(-1)) answer += "-x^" + key;
                    else answer += map.get(key) + "x^" + key;
                }
            } else {
                if (key.equals(0)) {
                    if (map.get(key) > 0) answer += "+" + map.get(key);
                    else answer += map.get(key);
                }
                else if (key.equals(1)) {
                    if (map.get(key).equals(1)) answer += "+x";
                    else if (map.get(key).equals(-1)) answer += "-x";
                    else if (map.get(key) > 1) answer += "+" + map.get(key) + "x";
                    else answer += map.get(key) + "x";
                }
                else {
                    if (map.get(key).equals(1)) answer += "+x^" + key;
                    else if (map.get(key).equals(-1)) answer += "-x^" + key;
                    else if (map.get(key) > 1) answer += "+" + map.get(key) + "x^" + key;
                    else answer += map.get(key) + "x^" + key;
                }
            }
        }
        if (answer.equals("")) return "0"; else return answer;
    }


    public static String multiplicationOfPolynomials(String str1, String str2) {
        Map<Integer, Integer> map1 = integerMapMaker(str1);
        Map<Integer, Integer> map2 = integerMapMaker(str2);
        Map<Integer, Integer> map3 = new TreeMap<>();
        for (Integer key1 : map1.keySet()) {
            for (Integer key2 : map2.keySet()) {
                if (!map3.containsKey(key1 + key2)) map3.put(key1 + key2, map1.get(key1) * map2.get(key2));
                else map3.put(key1 + key2, map3.get(key1 + key2) + map1.get(key1) * map2.get(key2));
            }
        }
        return mapToString(map3);
    }


    public static String divisionOfPolynomials(String str1, String str2) {
        if (str2.equals("0")) return "На ноль делить нельзя";
        if (str1.equals("0")) return "0";
        Map<Integer, Integer> map1 = integerMapMaker(str1);
        Map<Integer, Integer> map2 = integerMapMaker(str2);
        Map<Integer, Integer> map3 = new TreeMap<>();
        Map<Integer, Integer> map4 = new TreeMap<>();
        String str = str1;
        int factor;
        int divider;
        int maxDivider1 = (int) map1.keySet().toArray()[map1.keySet().size() - 1];
        int maxDivider2 = (int) map2.keySet().toArray()[map2.keySet().size() - 1];
        int factor1 = map1.get(maxDivider1);
        int factor2 = map2.get(maxDivider2);
        for (int i = maxDivider1; i >= maxDivider2;) {
            divider = maxDivider1 - maxDivider2;
            factor = factor1 / factor2;
            map3.put(divider, factor);
            for (Integer key : map2.keySet()) {
                map4.put(key + divider, map2.get(key) * factor);
            }
            str = subtractionOfPolynomials(str, mapToString(map4));
            if (str.equals("")) return mapToString(map3);
            map1 = integerMapMaker(str);
            maxDivider1 = (int) map1.keySet().toArray()[map1.keySet().size() - 1];
            i = maxDivider1;
            factor1 = map1.get(maxDivider1);
            map4.clear();
        }
        return mapToString(map3);
    }


    public static String remainderOfDivision(String str1, String str2) {
        if (str2.equals("0")) return "На ноль делить нельзя";
        if (str1.equals("0")) return "0";
        Map<Integer, Integer> map1 = integerMapMaker(str1);
        Map<Integer, Integer> map2 = integerMapMaker(str2);
        Map<Integer, Integer> map3 = new TreeMap<>();
        Map<Integer, Integer> map4 = new TreeMap<>();
        String str = str1;
        int factor;
        int divider;
        int maxDivider1 = (int) map1.keySet().toArray()[map1.keySet().size() - 1];
        int maxDivider2 = (int) map2.keySet().toArray()[map2.keySet().size() - 1];
        int factor1 = map1.get(maxDivider1);
        int factor2 = map2.get(maxDivider2);
        for (int i = maxDivider1; i >= maxDivider2;) {
            divider = maxDivider1 - maxDivider2;
            factor = factor1 / factor2;
            map3.put(divider, factor);
            for (Integer key : map2.keySet()) {
                map4.put(key + divider, map2.get(key) * factor);
            }
            str = subtractionOfPolynomials(str, mapToString(map4));
            if (str.equals("")) return "0";
            map1 = integerMapMaker(str);
            maxDivider1 = (int) map1.keySet().toArray()[map1.keySet().size() - 1];
            i = maxDivider1;
            factor1 = map1.get(maxDivider1);
            map4.clear();
        }
        return str;
    }


    public static void main(String[] args) {
        IntegerPolynomial str3 = new IntegerPolynomial("x^2-6");
        IntegerPolynomial str4 = new IntegerPolynomial("-x^2+6");
        System.out.println(str3.plus(str4).toString());
    }
}
