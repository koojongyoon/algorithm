package com.company.backjoon.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol2167 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] xy = br.readLine().split(" ");
        int x = Integer.parseInt(xy[0]);
        int y = Integer.parseInt(xy[1]);

        int[][] array = new int[x][y];

        for(int i = 0 ; i < x; i++) {
            String[] ik = br.readLine().split(" ");
            for(int k = 0; k < y; k++) {
                array[i][k] = Integer.parseInt(ik[k]);
            }
        }

        int testCase = Integer.parseInt(br.readLine());
        int j = 0;

        while (j < testCase) {
            String[] points = br.readLine().split(" ");
            int xStart = Integer.parseInt(points[0]) - 1;
            int yStart = Integer.parseInt(points[1]) - 1;
            int xEnd = Integer.parseInt(points[2]) - 1;
            int yEnd = Integer.parseInt(points[3]) - 1;
            int result = 0;

            for (int i = xStart; i <= xEnd; i++) {
                for (int k = yStart; k <= yEnd; k++) {
                    result = result + array[i][k];
                }
            }

            j++;
            System.out.println(result);
        }
    }
}
