package com.company.backjoon.LIS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol11055 {

    static int[] a;
    static int[] dp;
    static int MAX;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] inputStr = br.readLine().split(" ");

        a = new int[N+1];
        dp = new int[N+1];

        for (int i = 0; i < N; i++) {
            a[i+1] = Integer.parseInt(inputStr[i]);
            dp[i+1] = a[i+1];
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                if (dp[i] < a[i]+ dp[j] && a[i] > a[j]) {
                    dp[i] = a[i] + dp[j];
                }
            }
            if (MAX < dp[i]) {
                MAX = dp[i];
            }
        }

        System.out.println(MAX);

    }
}
