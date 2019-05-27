package com.company.LIS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol1925 {

    static int[] box;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        box = new int[N+1];
        dp = new int[N+1];

        String[] boxStr = br.readLine().split(" ");

        for (int i = 0; i < N; i++) {
            box[i] = Integer.parseInt(boxStr[i]);
            dp[i] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int k = 0; k <= i; k++) {
                if (box[i] > box[k] && dp[i] < dp[k] + 1) {
                    dp[i] = dp[k] + 1;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            if (dp[i] > max) {
                max = dp[i];
            }
        }

        System.out.println(max);
    }
}
