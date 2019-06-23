package com.company.backjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol2169 {

    static int[][] map;
    static int[][] dpMap;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);   // 1 <= N <= 1000
        int M = Integer.parseInt(NM[1]);   // 1 <= M <= 1000

        map = new int[N][M];
        dpMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] row = br.readLine().split(" ");
            for (int k = 0; k < M; k++) {
                map[i][k] = Integer.parseInt(row[k]);
            }
        }

        // 위로 이동할 수 없다.(오른쪽, 아래, 왼쪽으로만 이동 가능)
        // 한번 지나간곳은 다시 지나갈 수 없다
        dpMap[0][0] = map[0][0];
        for (int i = 1; i < N; i++) {
            dpMap[0][i] = dpMap[0][i-1] + map[0][i];
        }

        for (int i = 1; i < N; i++) {
            int[] rightTemp = new int[M];
            int[] leftTemp = new int[M];

            leftTemp[0] = dpMap[i-1][0] + map[i][0];
            dpMap[i][0] = leftTemp[0];
            for (int k = 1; k < M; k++) {
                leftTemp[k] = Math.max(dpMap[i-1][k], dpMap[i][k-1]) + map[i][k];
                dpMap[i][k] = leftTemp[k];
            }

            rightTemp[M-1] = dpMap[i-1][M-1] + map[i][M-1];
            dpMap[i][M-1] = rightTemp[M-1];
            for (int k = M-1; k > 0; k--) {
                rightTemp[k-1] = Math.max(dpMap[i-1][k-1], dpMap[i][k]) + map[i][k-1];
                dpMap[i][k-1] = rightTemp[k-1];
            }
            for (int k = 0; k < M; k++) {
                dpMap[i][k] = leftTemp[k] > rightTemp[k] ? leftTemp[k] : rightTemp[k];
            }
        }

        System.out.println(dpMap[N-1][M-1]);
    }
}
