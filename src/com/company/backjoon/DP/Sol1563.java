package com.company.backjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol1563 {

    static long ref;
    static int N;
    static long div = 1000000;
    static int[][][] dp;   // dp[출석일수][지각 횟수][결석 횟수]

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[1001][3][4];

        System.out.println(find(0, 0, 0));
    }

    private static long find(int day, int late, int absent) {
        if (late >= 2 || absent >= 3) {
            return 0;
        }

        if (day == N) {
            return 1;
        }

        ref = dp[day][late][absent];

        if (ref != 0) {
            return ref;
        }

        return ref = (find(day+1, late, 0) + find(day+1, late + 1, 0)+ find(day+1, late, absent + 1)) % div;
    }
}
