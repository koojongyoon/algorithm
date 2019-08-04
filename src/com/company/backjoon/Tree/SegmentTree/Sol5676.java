package com.company.backjoon.Tree.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol5676 {

    private static long[] arr;
    private static long[] tree;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NK = br.readLine().split(" ");
        N = Integer.parseInt(NK[0]);
        int K = Integer.parseInt(NK[1]);

        arr = new long[N+1];
        tree = new long[4*N];
        String[] inputStr = br.readLine().split(" ");

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(inputStr[i-1]);
        }

        initTree(1, 1, N);

        for (int i = 0; i < K; i++) {
            String[] inputCommand = br.readLine().split(" ");

            if ("C".equals(inputCommand[0])) {
                int updateIndex = Integer.parseInt(inputCommand[1]);
                int updateValue = Integer.parseInt(inputCommand[2]);
                long diff = arr[updateIndex] - updateValue;
                update(1, 1, N, updateIndex, diff);
            } else {
                int left = Integer.parseInt(inputCommand[1]);
                int right = Integer.parseInt(inputCommand[2]);
                long result = multiply(1, 1, N, left, right);
                if (result > 0) {
                    System.out.print("+");
                } else if (result == 0) {
                    System.out.print("0");
                } else if (result < 0) {
                    System.out.println("-");
                }
            }
            System.out.println();
        }
    }

    private static long multiply(int index, int start, int end, int left, int right) {
        if (start > right || end < left) {
            return 0;
        }
        if (start >= left && end < right) {
            return tree[index];
        }
        int mid = (start + end)/2;
        return tree[index] = multiply(index*2, start, mid, left, right)
                + multiply(index*2+1, mid+1, end, left, right);
    }

    private static long update(int index, int start, int end, int updateIndex, long diff) {
        if (updateIndex < start || updateIndex > end) {
            return 0;
        }

        tree[index] = tree[index] + diff;

        int mid = (start + end)/2;
        update(index*2, start, mid, updateIndex, diff);
        update(index*2+1, mid+1, end, updateIndex, diff);
        return tree[index] = tree[index*2] + tree[index*2+1];
     }

    private static long initTree(int index, int start, int end) {
        if (start == end) {
            tree[index] = arr[start];
        }
        int mid = (start + end)/2;
        return tree[index] = initTree(index*2, start, mid)
                + initTree(index*2+1, mid+1, end);
    }
}
