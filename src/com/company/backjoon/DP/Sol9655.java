package com.company.backjoon.DP;

import java.util.Scanner;

public class Sol9655 {

    private static String SK = "SK";
    private static String CY = "CY";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();    // 1 <= N <= 1000
        if (N%2 == 1) {
            System.out.println(SK);
        } else {
            System.out.println(CY);
        }
    }
}
