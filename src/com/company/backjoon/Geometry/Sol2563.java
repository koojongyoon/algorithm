package com.company.backjoon.Geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol2563 {

    static boolean[][] white;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        white = new boolean[101][101];
        for (int n = 0; n < N; n++) {
            String[] input = br.readLine().split(" ");
            int x1 = Integer.parseInt(input[0]);
            int y1 = Integer.parseInt(input[1]);
            int x2 = x1 + 10;
            int y2 = y1 + 10;
            for (int i = x1; i < x2; i++) {
                for (int k = y1; k < y2; k++) {
                    white[i][k] = true;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < 101; i++) {
            for (int k = 0; k < 101; k++) {
                if (white[i][k]) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
