package com.company.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sol1389 {

    static boolean[][] map;
    static int[][] costMap;

    static int MAX_VALUE = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] peopleRelation = br.readLine().split(" ");

        int people = Integer.parseInt(peopleRelation[0]);
        int relation = Integer.parseInt(peopleRelation[1]);

        map = new boolean[people+1][people+1];
        costMap = new int[people+1][people+1];

        for (int i = 0; i < people+1; i++) {
           for (int k = 0; k < people+1; k++) {
               if (i == k) {
                   costMap[i][k] = 0;
               } else {
                   costMap[i][k] = MAX_VALUE;
               }
           }
        }

        for (int i = 0; i < relation; i++) {
            String[] fromTo = br.readLine().split(" ");
            int from = Integer.parseInt(fromTo[0]);
            int to = Integer.parseInt(fromTo[1]);
            map[from][to] = true;
            costMap[from][to] = 1;
        }

        for (int k = 1; k < people+1; k++) {
            for (int i = 1; i < people+1; i++) {
                for (int j = 1; j < people+1; j++) {
                    if (costMap[i][j] > costMap[i][k] + costMap[k][j]) {
                        costMap[i][j] =
                    }
                }
            }
        }
    }
}
