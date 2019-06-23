package com.company.backjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol2240 {

    static int[][][] dp;
    static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] TW = br.readLine().split(" ");

        int T = Integer.parseInt(TW[0]);
        int W = Integer.parseInt(TW[1]);

        dp = new int[3][T+1][W+2];
        arr = new int[T+1];

        for (int i = 1; i <= T; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int t = 1; t <= T; t++) {
            for (int w = 1; w <= W+1; w++) {
                if (arr[t] == 1) {
                    dp[1][t][w] = Math.max(dp[1][t-1][w]+1, dp[2][t-1][w-1]+1);
                    dp[2][t][w] = Math.max(dp[1][t-1][w-1], dp[2][t-1][w]);
                } else {
                    if (t == 1 && w == 1) {
                        continue;
                    }
                    dp[1][t][w] = Math.max(dp[1][t-1][w], dp[2][t-1][w-1]);
                    dp[2][t][w] = Math.max(dp[1][t-1][w-1]+1, dp[2][t-1][w]+1);
                }
            }
        }

        int result = 0;
        for (int i = 1; i <= W+1; i++) {
            result = Math.max(result, Math.max(dp[1][T][i], dp[2][T][i]));
        }

        System.out.println(result);
    }
}
