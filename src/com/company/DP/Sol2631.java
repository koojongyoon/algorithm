package com.company.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol2631 {

    static int[] line;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 2 <= N <= 200

        line = new int[N + 1];
        dp = new int[N+1];

        for (int i = 1; i <= N; i++) {
            line[i] = Integer.parseInt(br.readLine());
        }

        //최소한의 수를 움직여  아이들의 번호를 맞추자
        int lis = 0;
        for (int i = 1; i <= N; i++) {
            dp[i] = 1;
            for (int k = 1; k < i; k++) {
                if (line[k] < line[i] && dp[i] < dp[k]+1) {
                    dp[i]++;
                }
            }
            if (lis < dp[i]) {
                lis = dp[i];
            }
        }

        System.out.println(N-lis);
    }
}

