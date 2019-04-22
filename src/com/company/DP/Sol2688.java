package com.company.DP;

import java.util.Scanner;

public class Sol2688 {

    static long[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        dp = new long[66][10];
        for (int i = 0; i < 10; i++) {
            dp[1][i] = i+1;
        }

        for (int k = 2; k < 65; k++) {
            dp[k][0] = 1;
            for (int j = 1; j < 10; j++) {
                dp[k][j] = dp[k-1][j] + dp[k][j-1];
            }
        }

        int T = sc.nextInt();

        for (int i = 0; i < T; i++){
            int n = sc.nextInt();
            System.out.println(dp[n][9]);
        }
    }
}
