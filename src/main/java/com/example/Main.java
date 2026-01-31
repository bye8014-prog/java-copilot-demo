package com.example;

public class Main {
    public static int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 6;
        int f = factorial(n);
        System.out.println("factorial(" + n + ") = " + f);

        for (int i = 1; i <= 10; i++) {
            int v = i * 3;
            if (v % 4 == 0) {
                System.out.println("multiple of 4: " + v);
            }
        }
    }
}
