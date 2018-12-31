package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol2178 {
    static int[][] map;

    static int[] xArr;
    static int[] yArr;
    static int[] lArr;

    static int cnt;
    static int index = 0;

    static int row;
    static int col;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NM = br.readLine().split(" ");

        row = Integer.parseInt(NM[0]);
        col = Integer.parseInt(NM[1]);

        map = new int[row][col];

        for(int i = 0; i < row; i++) {
            String[] xInput = br.readLine().split("");
            for(int k = 0; k < col; k++) {
                map[i][k] = Integer.parseInt(xInput[k]);
            }
        }

        xArr = new int[1000001];
        yArr = new int[1000001];
        lArr = new int[1000001];

        index = 0;
        enqueue(0, 0, 1);

        findRoad(0,0);
    }

    private static void enqueue(int x, int y, int l) {
        map[x][y] = 0;
        xArr[cnt] = x;
        yArr[cnt] = y;
        lArr[cnt] = l;
        cnt++;
    }

    private static void findRoad(int x, int y) {

        while ((xArr[index] != row-1 || yArr[index] != col-1) && index < cnt) {
            map[xArr[index]][yArr[index]] = 0;

            if (yArr[index] > 0 && map[xArr[index]][yArr[index] - 1] == 1) {
                enqueue(xArr[index], yArr[index] - 1, lArr[index] + 1);
            }
            if (xArr[index] + 1 < row && map[xArr[index] + 1][yArr[index]] == 1) {
                enqueue(xArr[index] + 1, yArr[index], lArr[index] + 1);
            }
            if (yArr[index] + 1< col && map[xArr[index]][yArr[index] + 1] == 1) {
                enqueue(xArr[index], yArr[index] + 1, lArr[index] + 1);
            }
            if (xArr[index] > 0 && map[xArr[index] - 1][yArr[index]] == 1) {
                enqueue(xArr[index] - 1, yArr[index], lArr[index] + 1);
            }
            index++;
        }

        System.out.println(lArr[index]);
    }
}
