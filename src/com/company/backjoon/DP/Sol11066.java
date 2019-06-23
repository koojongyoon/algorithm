package com.company.backjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol11066 {

    static int[][] dp;
    static int[] cost;
    static int[] sum;
    static int MAX = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {

            int K = Integer.parseInt(br.readLine());
            String[] numbersStr = br.readLine().split(" ");

            dp = new int[K+1][K+1];
            cost = new int[K+1];
            sum = new int[K+1];

            for (int i = 1; i <= K; i++) {
                cost[i] = Integer.parseInt(numbersStr[i-1]);
                sum[i] = sum[i-1] + cost[i];
            }

            for (int d = 1; d < K; ++d) {
                for (int tx = 1; tx + d <= K; ++tx) {
                    int ty = tx + d;
                    dp[tx][ty] = MAX;

                    for (int mid = tx; mid < ty; ++mid) {
                        dp[tx][ty] = Math.min(dp[tx][ty], dp[tx][mid] + dp[mid+1][ty] + sum[ty] - sum[tx-1]);
                    }
                }
            }
            System.out.println(dp[1][K]);

        }
    }
}
