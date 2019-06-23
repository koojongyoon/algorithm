package com.company.backjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol2449 {

    static int[] bolbs;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NK = br.readLine().split(" ");

        int n = Integer.parseInt(NK[0]);
        int k = Integer.parseInt(NK[1]);

        String[] numbers = br.readLine().split(" ");
        bolbs = new int[n];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            bolbs[i] = Integer.parseInt(numbers[i]);
        }

        System.out.println(findCount(0, n-1));
    }

    private static int findCount(int left, int right) {

        if (left == right) {
            return 0;
        }

        if (bolbs[left] == bolbs[left+1]) {
            return findCount(left+1, right);
        }

        if (bolbs[right] == bolbs[right-1]) {
            return findCount(left, right-1);
        }

        if (dp[left][right] != 0) {
            return dp[left][right];
        }

        dp[left][right] = dp[left][right] + findCount(left+1, right) + 1;

        for (int i = left + 1; i <= right; ++i) {
            if (bolbs[i] == bolbs[left]) {
                dp[left][right] = Math.min((findCount(left+1, i-1) + findCount(i, right)+1), dp[left][right]);
            }
        }

        return dp[left][right];
    }
}
