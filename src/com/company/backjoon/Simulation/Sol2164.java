package com.company.backjoon.Simulation;

import java.util.Scanner;

public class Sol2164 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int k = 1;

        for (int i = 0; k <= N; i++) {
            k = k + (int) Math.pow(2, i);
        }

        int j = (N - k - 1) * 2;

        System.out.println(j);
    }
}
