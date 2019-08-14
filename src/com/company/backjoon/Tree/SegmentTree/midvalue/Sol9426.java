package com.company.backjoon.Tree.SegmentTree.midvalue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol9426 {

    static int[] arr;
    static int[] tree;
    static long totalSum;
    static long N;
    static int K;
    static int MAX = 65536;
    static int MAX_N = 250001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputArr = br.readLine().split(" ");
        N = Integer.parseInt(inputArr[0]);
        K =  Integer.parseInt(inputArr[1]);
        arr = new int[MAX_N];
        tree = new int[4 * MAX_N];
        totalSum = 0;

        for (int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i <  K - 1; i++) {
            update(1, 0, MAX-1, arr[i], 1);
        }

        for (int i = K - 1; i <  N; i++) {
            update(1, 0, MAX-1, arr[i], 1);
            totalSum = totalSum + query( 1, 0, MAX-1, (K+1)/2);
            update(1, 0, MAX-1, arr[i - K + 1], -1);
        }
        System.out.println(totalSum);
    }

    private static int update (int index, long start, long end, int updateIndex, int updateValue) {
        if (updateIndex < start || updateIndex > end) {
            return tree[index];
        }
        if (start == end) {
            return tree[index] = tree[index] + updateValue;
        }
        long mid = (start + end) / 2;
        return tree[index] = update(index*2, start, mid, updateIndex, updateValue) + update(index*2+1, mid+1, end, updateIndex, updateValue);
    }

    private static long query (int index, long start, long end, long value) {
        if (start == end) {
            return start;
        }

        long mid = (start + end) / 2;
        if (tree[index * 2] >= value) {
            return query(index*2, start, mid, value);
        }
        return query(index*2+1, mid+1, end, value - tree[index*2]);
    }
}
