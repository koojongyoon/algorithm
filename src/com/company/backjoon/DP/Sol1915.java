package com.company.backjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol1915 {

    static int[][] map;
    static int[][] dpMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);

        map = new int[N+1][M+1];
        dpMap = new int[N+1][M+1];

        for (int i = 0; i < N; i++) {
            String[] rowStr = br.readLine().split("");
            for (int k = 0; k < M; k++) {
                map[i+1][k+1] = Integer.parseInt(rowStr[k]);
            }
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            for (int k = 1; k <= M; k++) {
                if (map[i][k] == 1) {
                    dpMap[i][k] = min(dpMap[i][k-1], dpMap[i-1][k], dpMap[i-1][k-1]) + 1;
                    max = max > dpMap[i][k] ? max : dpMap[i][k];
                }
            }
        }

        System.out.println((int) Math.pow(max, 2));
    }

    private static int min(int a, int b, int c) {
        int temp = Math.min(a, b);
        return Math.min(temp, c);
    }
}
