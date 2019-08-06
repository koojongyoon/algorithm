package com.company.backjoon.Tree.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol11505 {

    static int n;
    static long[] arr;
    static long[] tree;
    static long MOD = 1000000007L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputStr = br.readLine().split(" ");
        n = Integer.parseInt(inputStr[0]);
        int m = Integer.parseInt(inputStr[1]);
        int k = Integer.parseInt(inputStr[2]);

        arr = new long[n+1];
        tree = new long[4*n];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        initTree(1, 1, n);

        for (int i = 0; i < m+k; i++) {
            String[] commandStr = br.readLine().split(" ");
            int x = Integer.parseInt(commandStr[1]);
            int y = Integer.parseInt(commandStr[2]);
            if (commandStr[0].equals("1")) {
                update(1, 1, n, x, y);
            } else {
                System.out.println(multiply(1, 1, n, x, y)%MOD);
            }
        }
    }

    private static void update(int index, int start, int end, int updateIndex, long diff) {
        if (updateIndex < start || updateIndex > end) {
            return;
        }

        if (start == end) {
            tree[index] = diff;
        } else {
            tree[index] = (tree[index] * diff) % MOD;
        }

        if (start != end) {
            int mid = (start + end)/2;
            update(index*2, start, mid, updateIndex, diff);
            update(index*2+1, mid+1, end, updateIndex, diff);
            tree[index] = (tree[index*2] * tree[index*2+1]) % MOD;
        }
    }

    private static long multiply(int index, int start, int end, int left, int right) {
        if (start > right || end < left) {
            return 1;
        }
        if (start >= left && end <= right) {
            return tree[index];
        }
        int mid = (start + end)/2;
        return (multiply(index*2, start, mid, left, right)
                * multiply(index*2+1, mid+1, end, left, right)) % MOD;
    }

    private static long initTree(int index, int start, int end) {
        if (start == end) {
            return tree[index] = arr[start];
        }
        int mid = (start + end)/2;
        return tree[index] = (initTree(index*2, start, mid) * initTree(index*2+1, mid+1, end)) % MOD;
    }
}
