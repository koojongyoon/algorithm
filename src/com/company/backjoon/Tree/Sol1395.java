package com.company.backjoon.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol1395 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputStr = br.readLine().split(" ");
        int n = Integer.parseInt(inputStr[0]);
        int m = Integer.parseInt(inputStr[1]);
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            int command = Integer.parseInt(input[0]);
            int start = Integer.parseInt(input[1]);
            int end = Integer.parseInt(input[2]);
        }
    }
}

