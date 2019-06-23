package com.company.backjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol1670 {

    static long[] dp;
    static long div = 987654321L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int people = Integer.parseInt(br.readLine());
        dp = new long[100001];

        dp[0] = 1;
        dp[2] = 1;
        dp[4] = 2;
        dp[6] = 5;

        for (int i = 8; i <= people; i = i + 2) {
            for (int j = 0; j <= i - 2; j = j + 2) {
                dp[i] = dp[i] + dp[j] * dp[i - j - 2];
                dp[i] = dp[i] % div;
            }
        }

        System.out.println(dp[people]);
    }
}
