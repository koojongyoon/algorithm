package com.company.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sol2468 {

    static int N;
    static int[][] map;
    static boolean[][] visit;
    static LinkedList<Integer> hillHeight;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int maxArea = 0;
    static int snapshotArea = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        hillHeight = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            String[] high = br.readLine().split(" ");
            for (int k = 0; k < N; k++) {
                int inputWaterHigh = Integer.parseInt(high[k]);
                map[i][k] = inputWaterHigh;
                if (!hillHeight.contains(inputWaterHigh)) {
                    hillHeight.add(inputWaterHigh);
                }
            }
        }

        Collections.sort(hillHeight);
        int size = hillHeight.size();
        for (int i = 0; i < size; i++) {

            visit = new boolean[N][N];
            snapshotArea = 0;
            int water = hillHeight.poll();

            for (int m = 0; m < N; m++) {
                for (int n = 0; n < N; n++) {
                    if (map[m][n] <= water) {
                        visit[m][n] = true;
                    }
                }
            }

            for (int m = 0; m < N; m++) {
                for (int n = 0; n < N; n++) {
                    if (!visit[m][n]) {
                        DFS(m, n);
                        snapshotArea++;
                    }
                }
            }

            if (maxArea < snapshotArea) {
                maxArea = snapshotArea;
            }
        }
        if (maxArea == 0) {
            System.out.println("1");
        } else {
            System.out.println(maxArea);
        }
    }

    private static void DFS(int row, int col) {
        visit[row][col] = true;
        for (int i = 0; i < 4; i++) {
            int nextX = row + dx[i];
            int nextY = col + dy[i];

            if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < N && !visit[nextX][nextY]) {
                DFS(nextX, nextY);
            }
        }
    }
}
