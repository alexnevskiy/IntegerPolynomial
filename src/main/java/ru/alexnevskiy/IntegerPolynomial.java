package ru.alexnevskiy;

import java.util.*;

public final class IntegerPolynomial {

    private final List<Integer> factors;

    public IntegerPolynomial(List<Integer> input) {
        factors = new ArrayList<>(input);
    }

    public IntegerPolynomial(String input) {
        if (input.matches("(\\+?-?\\s*\\d*[x]*\\^*\\d*)*")) {
            factors = zeroCheck(toList(input));
        } else throw new NumberFormatException("Некорректный ввод.");  //  Бросает ошибку, если полином не соответствует
    }  //  правильной записи, то есть вместо x используется любой другой символ

    private List<Integer> toList(String str) {  // Вспомогательная функция для преобразования строки в лист
        Map<Integer, Integer> map = new TreeMap<>();
        String trim = str.replaceAll("\\s+", "").trim();
        String minus = trim.replaceAll("-", "  -").trim();
        String plus = minus.replaceAll("\\+", "  +").trim();
        String[] split = plus.split("\\s{2}");
        for (int i = 0; i < split.length; i++) {  //  Добавление в map степени (ключ) и множителя (значение)
            if (split[i].matches("[+-]?\\d+[x]\\^\\d+")) {
                String[] parse = split[i].split("[x]\\^");
                map.put(Integer.parseInt(parse[1]), Integer.parseInt(parse[0]));
            } else if (split[i].matches("[+]?[x]\\^\\d+")) {
                String[] parse = split[i].split("[x]\\^");
                map.put(Integer.parseInt(parse[1]), 1);
            } else if (split[i].matches("[-]?[x]\\^\\d+")) {
                String[] parse = split[i].split("[x]\\^");
                map.put(Integer.parseInt(parse[1]), -1);
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
        List<Integer> list = new ArrayList<>((int) map.keySet().toArray()[map.keySet().size() - 1]);
        for (int i = 0; i <= (int) map.keySet().toArray()[map.keySet().size() - 1]; i++) {  //  Заполнение листа нулями
            list.add(0);
        }
        for (Integer key : map.keySet()) {  //  Изменение значений листа на множители степеней
            list.set(key, map.get(key));
        }
        return list;
    }

    private List<Integer> zeroCheck(List<Integer> list) {  //  Вспомогательная функиция для удаления нулевых старших членов
        if (list.get(list.size() - 1) == 0) {  //  Если множители старших членов равны нулю, то они удаляются. Это
            while (list.get(list.size() - 1) == 0 && list.size() != 1) {  //  реализовано для того, чтобы при
                list.remove(list.size() - 1);  //  вызове правильного ответа в тесте, не было конфликтов между
            }  //  элементами класса
        }
        return list;
    }

    @Override
    public String toString() {  //  Вспомогательная функция для преобразования листа в строку
        StringBuilder answer = new StringBuilder();
        for (int i = factors.size() - 1; i >= 0; i--) {
            if (i == factors.size() - 1) {
                if (i > 1) {
                    if (factors.get(i) == 1) answer.append("x^").append(i);
                    else if (factors.get(i) == -1) answer.append("-x^").append(i);
                    else if (factors.get(i) != 0) answer.append(factors.get(i)).append("x^").append(i);
                    else continue;
                }
                if (i == 1) {
                    if (factors.get(i) == 1) answer.append("x");
                    else if (factors.get(i) == -1) answer.append("-x");
                    else if (factors.get(i) != 0) answer.append(factors.get(i)).append("x");
                    else continue;
                }
                if (i == 0) {
                    if (factors.get(i) != 0) answer.append(factors.get(i));
                }
            } else {
                if (i > 1) {
                    if (factors.get(i) == 1) answer.append("+x^").append(i);
                    else if (factors.get(i) == -1) answer.append("-x^").append(i);
                    else if (factors.get(i) > 1) answer.append("+").append(factors.get(i)).append("x^").append(i);
                    else if (factors.get(i) < -1) answer.append(factors.get(i)).append("x^").append(i);
                    else continue;
                }
                if (i == 1) {
                    if (factors.get(i) == 1) answer.append("+x");
                    else if (factors.get(i) == -1) answer.append("-x");
                    else if (factors.get(i) > 1) answer.append("+").append(factors.get(i)).append("x");
                    else if (factors.get(i) < -1) answer.append(factors.get(i)).append("x");
                    else continue;
                }
                if (i == 0) {
                    if (factors.get(i) >= 1) answer.append("+").append(factors.get(i));
                    if (factors.get(i) <= -1) answer.append(factors.get(i));
                }
            }
        }
        if (answer.toString().equals("")) return "0";
        else return answer.toString();
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

    public int value(int x) {
        int answer = 0;
        for (int i = 0; i < factors.size(); i++) {
            int factor = (int) Math.pow(x, i);
            answer += factors.get(i) * factor;
        }
        return answer;
    }

    public IntegerPolynomial plus(IntegerPolynomial other) {
        List<Integer> minList;
        List<Integer> maxList;
        if (factors.size() <= other.factors.size()) {  //  Определение максимальной степени у многочленов
            minList = factors;
            maxList = other.factors;
        } else {
            minList = other.factors;
            maxList = factors;
        }
        List<Integer> answer = new ArrayList<>(maxList.size() - 1);
        for (int i = 0; i < maxList.size(); i++) {  //  Добавление в лист множителей
            if (i < minList.size()) answer.add(minList.get(i) + maxList.get(i));
            else answer.add(maxList.get(i));
        }
        return new IntegerPolynomial(zeroCheck(answer));
    }

    public IntegerPolynomial minus(IntegerPolynomial other) {
        List<Integer> list1 = other.factors;
        List<Integer> list2 = new ArrayList<>(list1.size() - 1);
        for (int i = 0; i < list1.size(); i++) {  //  Цикл для изменения всех множителей на отрицательные
            list2.add(-list1.get(i));
        }
        return this.plus(new IntegerPolynomial(list2));
    }

    public IntegerPolynomial multiply(IntegerPolynomial other) {
        int max = factors.size() - 1 + other.factors.size() - 1;
        List<Integer> list = new ArrayList<>(max);
        for (int i = 0; i <= max; i++) {  //  Заполнение листа нулями до максимальной степени многочлена, реализовано
            list.add(0);  // для удобства работы с листом
        }
        for (int i = 0; i < factors.size(); i++) {  //  Сам алгоритм умножения - добавление в конкретный индекс, то есть
            for (int j = 0; j < other.factors.size(); j++) {  //  степень многочлена, множителя или сложение предыдущего
                list.set(i + j, list.get(i + j) + factors.get(i) * other.factors.get(j));  //  множителя с новым
            }
        }
        return new IntegerPolynomial(zeroCheck(list));
    }

    public IntegerPolynomial divide(IntegerPolynomial other) {
        if (other.equals(new IntegerPolynomial("0"))) throw new NumberFormatException("На ноль делить нельзя");
        if (factors.size() < other.factors.size()) return new IntegerPolynomial("0");
        int max = factors.size() - 1 - (other.factors.size() - 1);
        List<Integer> list = new ArrayList<>(max);
        for (int i = 0; i <= max; i++) {  //  Тот же цикл для заполнения листа нулями
            list.add(0);
        }
        int factor;  //  Создание вспомогательных переменных
        int divider;
        int maxDivider1 = factors.size() - 1;
        int maxDivider2 = other.factors.size() - 1;
        int factor1 = factors.get(maxDivider1);
        int factor2 = other.factors.get(maxDivider2);
        IntegerPolynomial obj1 = this;
        while (obj1.factors.size() >= other.factors.size()) {
            divider = maxDivider1 - maxDivider2;  //Степень
            factor = factor1 / factor2;  //Множитель
            list.set(divider, factor);  //Добавление в лист степени и множителя разности
            List<Integer> var = new ArrayList<>();
            for (int j = divider; j >= 0; j--) {  //Создание и заполнение листа для члена частного
                if (j == 0) var.add(factor);
                else var.add(0);
            }
            IntegerPolynomial remainder = obj1.minus(new IntegerPolynomial(var).multiply(other));  //Остаток от вычитания
            if (remainder.factors.size() == obj1.factors.size())  //  Удаление максимального одночлена, если его степень
                remainder.factors.remove(remainder.factors.size() - 1);  //  совпадает со степенью уменьшаемого
            obj1 = remainder;
            maxDivider1 = obj1.factors.size() - 1;  //  Переопределение степени уменьшаемого
            factor1 = obj1.factors.get(obj1.factors.size() - 1);  //  Переопределение множителя уменьшаемого
        }
        return new IntegerPolynomial(list);
    }

    public IntegerPolynomial remainder(IntegerPolynomial other) {
        return this.minus(other.multiply(this.divide(other)));
    }
}