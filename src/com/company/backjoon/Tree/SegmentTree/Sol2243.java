package com.company.backjoon.Tree.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol2243 {

    static int N;
    static long[] tree;
    static int result;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new long[N+1];

        for (int i = 0; i < N; i++) {
            String[] inputStr = br.readLine().split(" ");
            int command = Integer.parseInt(inputStr[0]);
            result = 0;
            if (command == 1) {
                int pickIndex = Integer.parseInt(inputStr[1]);
                pick(1, 1, N, pickIndex);
                put(1, 1, N, pickIndex, -1);
            } else {
                int putIndex = Integer.parseInt(inputStr[1]);
                int putCount = Integer.parseInt(inputStr[2]);
                put(1, 1, N, putIndex, putCount);
            }
        }
    }

    private static long pick(int index, int start, int end, int updateIndex) {
        if (index < start || index > end) {
            return 0;
        }
        if (updateIndex > start && updateIndex < end) {
            return start;
        }
        int mid = (start + end)/2;
        return tree[index] = pick(index*2, start, mid, updateIndex) + pick(index*2+1, mid+1, start, updateIndex);
    }

    private static long put(int index, int start, int end, int updateIndex, int updateCount) {
        if (index < start || index > end) {
            return 0;
        }
        if (updateIndex > start && updateIndex < end) {
            tree[index] = tree[index] + updateCount;
            return start;
        }
        int mid = (start + end)/2;
        return tree[index] = put(index*2, start, mid, updateIndex, updateCount) + put(index*2+1, mid+1, start, updateIndex, updateCount);
    }
}
