package com.company.backjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol1507 {

    static int[][] dist;
    static int[][] temp;
    static boolean[][] visited;

    static int sum = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dist = new int[N][N];
        temp = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String[] inputStr = br.readLine().split(" ");
            for (int k = 0; k < N; k++) {
                int number = Integer.parseInt(inputStr[k]);
                dist[i][k] = number;
                temp[i][k] = number;
            }
        }

        for (int k = 0; k < N; k++ ) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == k || k == j) {
                        continue;
                    }

                    if(dist[i][j] > dist[i][k] + dist[k][j]) {
                        System.out.println(-1);
                        return;
                    }

                    if (dist[i][j] == dist[i][k] + dist[k][j]) {
                        temp[i][j] = 0;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (temp[i][j] != 0 && !visited[i][j]) {
                    sum = sum + temp[i][j];
                    visited[i][j] = true;
                    visited[j][i] = true;
                }
            }
        }

        System.out.println(sum);
    }
}
