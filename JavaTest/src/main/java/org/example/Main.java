package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");
    }
    public static int sum(int a, int b) {
        return a + b;
    }
    public static int minus(int a, int b) {
        return a - b;
    }
    public static int umnozhenie(int a, int b) {
        return a * b;
    }
    public static double delenie(int a, int b) {
        if (b == 0) {
            throw new IndexOutOfBoundsException("На ноль делить нельзя");
        }
        return (double) a / b;
    }
    public static double sqrt(double x) {
        if (x < 0) {
            throw new IndexOutOfBoundsException("Нельзя извлечь корень из отрицательного числа");
        }
        return Math.sqrt(x);
    }
}