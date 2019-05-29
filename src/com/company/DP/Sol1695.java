package com.company.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol1695 {

    static int[] input;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] inputStr = br.readLine().split(" ");
        input = new int[N+1];
        dp = new int[N+1][N+1];

        for (int i = 0; i < N; i++) {
            input[i+1] = Integer.parseInt(inputStr[i]);
        }

        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= N; ++j) {
                if (input[i] == input[N-j+1]) {
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        System.out.println(N-dp[N][N]);
    }
}
