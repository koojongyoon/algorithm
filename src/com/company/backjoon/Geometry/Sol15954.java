package com.company.backjoon.Geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol15954 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NK = br.readLine().split(" ");
        int N = Integer.parseInt(NK[0]);
        int K = Integer.parseInt(NK[1]);
        String[] dollsStr = br.readLine().split(" ");

        int[] dolls = new int[N];
        int[] medians = new int[N];

        int totalSum = 0;
        double median = 0;

        for (int i = 0; i < N; i++) {
            dolls[i] = Integer.parseInt(dollsStr[i]);
            totalSum = totalSum + dolls[i];
        }
        int average = totalSum / N;
        for (int i = 0; i < N; i++) {
            median = Math.pow(dolls[i] - average, 2) + median;
        }
        double standardMedian = Math.sqrt(median / N);
        System.out.println(Math.sqrt(standardMedian) * (2/3));
    }
}
