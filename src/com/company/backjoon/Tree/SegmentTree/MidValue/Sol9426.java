package com.company.backjoon.Tree.SegmentTree.MidValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol9426 {
    static int N;
    static int K;
    static int[] arr;
    static int[] tree;
    static int MAX = 65540;
    static int total = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        arr = new int[MAX];
        tree = new int[MAX*4];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0 ; i < K - 1; i++) {
            update(1, 0, MAX-1, arr[i], 1);
        }

        for (int i = K - 1; i < N; i++) {
            update(1, 0, MAX-1, arr[i], 1);
            total = total + sum(1, 0, MAX-1,  (K+1)/2);
            update(1, 0, MAX-1, arr[i-K+1], -1);
        }

        System.out.println(total);
    }

    private static int update(int index, int start, int end, int updateIndex, int updateValue) {
        if (updateIndex < start || updateIndex > end) {
            return tree[index];
        }
        if (start == end) {
            return tree[index] = tree[index] + updateValue;
        }
        int mid = (start + end)/2;
        return tree[index] = update(index*2, start, mid, updateIndex, updateValue) + update(index*2+1, mid+1, end, updateIndex, updateValue);

    }

    private static int sum(int index, int start, int end, int value) {
        if (start == end) {
            return start;
        }

        int mid = (start + end)/2;

        if (tree[index*2] >= value) {
            return sum(index*2, start, mid, value);
        } else {
            return sum(index*2+1, mid+1, end, value- tree[index*2]);
        }
    }
}
