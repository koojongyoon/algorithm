package com.company.backjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol2662 {

    static int arr[][];
    static int cache[][];
    static int output[][];
    static int invest[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);

        arr = new int[301][21];
        cache = new int[301][21];
        output = new int[301][21];
        invest = new int[21];

        for (int i = 1; i <= N; i++) {
            String[] invest = br.readLine().split(" ");
            int num = Integer.parseInt(invest[0]);
            for (int b = 1; b <= M; b++) {
                arr[i][b] = Integer.parseInt(invest[b]);
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int k = 1; k <= M; k++) {
                cache[i][k] = Math.max(cache[i][k-1], arr[i][k]);
                for (int j = 1; j < i; j++) {
                    cache[i][j] = Math.max(cache[i][j], cache[j][k-1] + arr[i-j][k]);
                }
            }
        }
        System.out.println(cache[N][M]);
    }
}


