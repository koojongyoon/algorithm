package com.company.backjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol14501 {

    static int[][] pay;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        pay = new int[2][N+1];
        dp = new int[N+1];

        for (int i = 1 ; i <= N; i++) {
            String[] TP = br.readLine().split(" ");
            int T = Integer.parseInt(TP[0]);
            int P = Integer.parseInt(TP[1]);

            pay[0][i] = T;
            pay[1][i] = P;
            dp[i] = P;
        }

        for (int i = 2; i <= N; i++) {
            for (int k = 1; k < i; k++) {
                if (i - k >= pay[0][k]) {
                    dp[i] = Math.max(pay[1][i] + dp[k], dp[i]);
                }
            }
        }

        // 해결하려는 날이 남은날보다 더 많을때는 처리 할수 없으므로 0으로 만든다.
        for (int i = 1; i <= N; i++) {
            if (pay[0][i] > N-i+1) {
                dp[i] = 0;
            }
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
        }

        System.out.println(max);
    }
}
