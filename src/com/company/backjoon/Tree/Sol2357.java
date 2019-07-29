package com.company.backjoon.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol2357 {

    static int[] minTreeArr;
    static int[] maxTreeArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputStr = br.readLine().split(" ");
        int N = Integer.parseInt(inputStr[0]);
        int M = Integer.parseInt(inputStr[1]);
        minTreeArr = new int[4*N];
        maxTreeArr = new int[4*N];
        for (int n = 1; n <= N; n++) {
            int updateValue = Integer.parseInt(br.readLine());
            minUpdate(n, 1, 1, N, updateValue);
            maxUpdate(n, 1, 1, N, updateValue);
        }
        for (int m = 0; m < M; m++) {
            String[] scope = br.readLine().split(" ");
            int start = Integer.parseInt(scope[0]);
            int end = Integer.parseInt(scope[1]);
            System.out.print(minQuery(1, 1, N, start, end) + " ");
            System.out.print(maxQuery(1, 1, N, start, end));
            System.out.println();
        }
    }

    private static int minUpdate(int pos, int index, int start, int end, int updateValue) {
        if (pos < start || end < pos) {
            return minTreeArr[index];
        }
        if (start == end) {
            return minTreeArr[index] = updateValue;
        }
        int mid = (start+end)/2;
        return minTreeArr[index] = Math.min(minUpdate( pos, index*2, start, mid, updateValue)
                , minUpdate( pos, index*2+1, mid+1, end, updateValue));
    }

    private static int maxUpdate(int pos, int index, int start, int end, int updateValue) {
        if (pos < start || end < pos) {
            return maxTreeArr[index];
        }
        if (start == end) {
            return maxTreeArr[index] =updateValue;
        }
        int mid = (start+end)/2;
        return maxTreeArr[index] =Math.max(maxUpdate( pos, index*2, start, mid, updateValue)
                , maxUpdate( pos, index*2+1, mid+1, end, updateValue));
    }

    private static int minQuery(int index, int start, int end, int findStart, int findEnd) {
        if (start > findEnd || end < findStart) {
            return Integer.MAX_VALUE;
        }
        if (findStart <= start && findEnd >= end) {
            return minTreeArr[index];
        }
        int mid = (start + end) / 2;
        return Math.min(minQuery(index*2, start, mid, findStart, findEnd),
                minQuery(index*2+1, mid+1, end, findStart, findEnd));
    }
    private static int maxQuery(int index, int start, int end, int findStart, int findEnd) {
        if (start > findEnd || end < findStart) {
            return Integer.MIN_VALUE;
        }
        if (findStart <= start && findEnd >= end) {
            return maxTreeArr[index];
        }
        int mid = (start + end) / 2;
        return Math.max(maxQuery(index*2, start, mid, findStart, findEnd),
                maxQuery(index*2+1, mid+1, end, findStart, findEnd));
    }
}
