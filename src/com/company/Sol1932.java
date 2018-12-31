package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by koojongyun on 2018. 11. 24..
 */
public class Sol1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int high = Integer.parseInt(br.readLine());

        int[][] triangle = initTriangle(br, high);

        sumRoute(triangle, high);

        System.out.println(findMax(triangle, high));

    }

    private static int[][] sumRoute(int[][] triangle, int high) {
        for(int i=1; i < high; i++) {
            for(int k=0; k < high; k++) {
                if (k == 0) {
                    triangle[i][k] = triangle[i][k] + triangle[i - 1][k];
                } else {
                    triangle[i][k] = Math.max(triangle[i][k] + triangle[i - 1][k - 1], triangle[i][k] + triangle[i - 1][k]);
                }
            }
        }

        return triangle;
    }

    private static int findMax(int[][] triangle, int high) {
        int max = triangle[high-1][0];

        for(int i = 0; i < high; i++) {
            if(triangle[high-1][i] > max) {
                max = triangle[high-1][i];
            }
        }

        return max;
    }

    private static int[][] initTriangle(BufferedReader br, int high) throws IOException {
        int[][] triangle = new int[high][high];

        for(int i = 0; i < high; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int k = 0; k < i+1; k++) {
                triangle[i][k] = Integer.parseInt(st.nextToken());
            }
        }

        return triangle;
    }
}
