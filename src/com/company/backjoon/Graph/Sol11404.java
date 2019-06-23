package com.company.backjoon.Graph;

import java.io.*;

public class Sol11404 {

    static int MAX = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int townCounter = Integer.parseInt(br.readLine());
        int busCounter = Integer.parseInt(br.readLine());

        int[][] map = new int[townCounter+1][townCounter+1];

        for (int i = 1; i <= townCounter; i++) {
            for (int j = 1; j <= townCounter; j++) {
                map[i][j] = i == j ? 0 : MAX;
            }
        }

        for (int i = 1; i <= busCounter; i++) {
            String[] route = br.readLine().split(" ");
            int start = Integer.parseInt(route[0]);
            int end = Integer.parseInt(route[1]);
            int cost = Integer.parseInt(route[2]);

            map[start][end] = Math.min(cost, map[start][end]);
        }

        for (int k = 1; k <= townCounter; k++) {
            for (int i = 1; i <= townCounter; i++) {
                for (int j = 1; j <= townCounter; j++) {
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        for (int i = 1; i <= townCounter; i++) {
            for (int j = 1; j <= townCounter; j++) {
                if (map[i][j] != MAX) {
                    System.out.print(map[i][j]+ " ");
                } else {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        }
    }

}
