package com.company.backjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol1389 {

    static boolean[][] map;
    static int[][] costMap;
    static int[] sumArr;

    static int MAX_VALUE = 100000001;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] peopleRelation = br.readLine().split(" ");

        int people = Integer.parseInt(peopleRelation[0]);
        int relation = Integer.parseInt(peopleRelation[1]);

        costMap = new int[people+1][people+1];
        sumArr = new int[people+1];

        for (int i = 0; i < relation; i++) {
            String[] fromTo = br.readLine().split(" ");
            int from = Integer.parseInt(fromTo[0]);
            int to = Integer.parseInt(fromTo[1]);
            costMap[from][to] = 1;
            costMap[to][from] = 1;
        }

        for (int i = 1; i < people+1; i++) {
            for (int k = 1; k < people+1; k++) {
                if (i == k) {
                    costMap[i][k] = 0;
                } else {
                    if (costMap[i][k] != 1) {
                        costMap[i][k] = MAX_VALUE;
                    }
                }
            }
        }

        for (int k = 1; k < people+1; k++) {
            for (int i = 1; i < people+1; i++) {
                for (int j = 1; j < people+1; j++) {
                    if (costMap[i][j] > costMap[i][k] + costMap[k][j]) {
                        costMap[i][j] = costMap[i][k] + costMap[k][j];
                    }
                }
            }
        }

        for (int i = 1; i < people+1; i++) {
            int tmpSum = 0;
            for (int k = 1; k < people+1; k++) {
                tmpSum = tmpSum + costMap[i][k];
            }
            sumArr[i] = tmpSum;
        }

        int minIndex = 99999999;
        int minSum = 99999999;
        for (int i = 1; i < sumArr.length; i++) {
            if (minSum > sumArr[i]) {
                minSum = sumArr[i];
                minIndex = i;
            }
        }

        System.out.println(minIndex);
    }
}
