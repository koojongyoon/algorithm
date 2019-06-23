package com.company.backjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by koojongyun on 2018. 12. 29..
 */
public class Sol1520 {

    static int rowCnt;
    static int colCnt;
    static int map[][];
    static int memo[][];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] rowCol = br.readLine().split(" ");
        rowCnt = Integer.parseInt(rowCol[0]);
        colCnt = Integer.parseInt(rowCol[1]);

        map = new int[rowCnt][colCnt];
        memo = new int[rowCnt][colCnt];

        for (int i = 0; i < rowCnt; i++) {
            String[] colNum = br.readLine().split(" ");
            for (int k = 0; k < colCnt; k++) {
                map[i][k] = Integer.parseInt(colNum[k]);
                memo[i][k] = -1;
            }
        }

        System.out.println(findRoad(0, 0));
    }

    private static int findRoad(int row, int col) {

        if (row == rowCnt-1 && col == colCnt-1) {
            return 1;
        }

        if (memo[row][col] == -1) {
            memo[row][col] = 0;

            // 위쪽
            if (row > 0 && map[row][col] > map[row - 1][col]) {
                memo[row][col] = memo[row][col] + findRoad(row - 1, col);
            }
            // 오른쪽
            if (col < colCnt-1 &&  map[row][col] > map[row][col + 1]) {
                memo[row][col] = memo[row][col] + findRoad(row, col + 1);
            }
            // 아래쪽
            if (row < rowCnt-1 && map[row][col] > map[row + 1][col]) {
                memo[row][col] = memo[row][col] + findRoad(row + 1, col);
            }
            // 왼쪽
            if (col > 0 &&  map[row][col] > map[row][col - 1]) {
                memo[row][col] = memo[row][col] + findRoad(row, col - 1);
            }
        }

        return memo[row][col];
    }
}
