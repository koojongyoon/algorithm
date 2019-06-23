package com.company.backjoon.Permutation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol6603 {

    static int numberCount;
    static int[] number;
    static int[] combi;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputArr;

        while ((inputArr = br.readLine().split(" ")).length > 1) {
            numberCount = Integer.parseInt(inputArr[0]);
            number = new int[numberCount];
            combi = new int[numberCount];

            for (int i = 1; i < inputArr.length; i++) {
                number[i-1] = Integer.parseInt(inputArr[i]);
            }

            dfs(0,0);
            System.out.println();
        }
    }

    private static void dfs(int start, int depth) {
        if (depth == 6) {
            for (int i = 0; i < 6; i++) {
                System.out.print(combi[i] + " ");
            }
            System.out.println();
        }

        for (int i = start; i < numberCount; i++) {
            combi[depth] = number[i];
            dfs(i+1, depth+1);
        }
    }
}
