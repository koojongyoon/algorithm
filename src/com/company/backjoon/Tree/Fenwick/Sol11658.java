package com.company.backjoon.Tree.Fenwick;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol11658 {
    private static int[][] nmap;
    private static int[][] tree;
    private static int N = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NM = br.readLine().split(" ");
        N = Integer.parseInt(NM[0]);
        int m = Integer.parseInt(NM[1]);
        nmap = new int[N+1][N+1];
        tree = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            String[] inputStr = br.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                nmap[i][j] = Integer.parseInt(inputStr[j-1]);
                update(i, j, nmap[i][j]);
            }
        }

        for (int i = 0; i < m; i++) {
            String[] inputStr = br.readLine().split(" ");
            int command = Integer.parseInt(inputStr[0]);
            if (command == 0) {
                int xPoint = Integer.parseInt(inputStr[1]);
                int yPoint = Integer.parseInt(inputStr[2]);
                int updateValue = Integer.parseInt(inputStr[3]);
                update(xPoint, yPoint, updateValue - nmap[xPoint][yPoint]);
                nmap[xPoint][yPoint] = updateValue;
            } else {
                int x1 = Integer.parseInt(inputStr[1]);
                int y1 = Integer.parseInt(inputStr[2]);
                int x2 = Integer.parseInt(inputStr[3]);
                int y2 = Integer.parseInt(inputStr[4]);
                System.out.println((sum(x2, y2) - sum(x1 - 1, y2) - sum(x2, y1 - 1) + sum(x1 - 1, y1 - 1)));
            }
        }
    }

    private static long sum(int x, int y) {
        int result = 0;
        for (int i = x; i > 0; i = i - (i & -i)){
            for (int j = y; j > 0; j = j - (j & -j))
                result = result + tree[i][j];
        }
        return result;
    }

    private static void update(int x, int y, int diff) {
        for (int i = x; i <= N; i = i + (i & -i)) {
            for (int j = y; j <= N; j = j + (j & -j)) {
                tree[i][j] = tree[i][j] + diff;
            }
        }
    }
}
