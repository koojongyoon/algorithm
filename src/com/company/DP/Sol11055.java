package com.company.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol11055 {

    static int[] numberArr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] inputStr = br.readLine().split(" ");
        numberArr = new int[N+1];

        for (int i = 0; i < N; i++) {
            numberArr[i] = Integer.parseInt(inputStr[0]);
        }

    }
}
