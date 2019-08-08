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
        tree = new long[4*N];

        for (int i = 0; i < N; i++) {
            String[] inputStr = br.readLine().split(" ");
            int command = Integer.parseInt(inputStr[0]);
            result = 0;
            if (command == 1) {
                int pickIndex = Integer.parseInt(inputStr[1]);
                long getIndex = pick(1, 1, N, pickIndex);
                System.out.println(put(1, 1, N, getIndex, -1));
            } else {
                int putIndex = Integer.parseInt(inputStr[1]);
                int putCount = Integer.parseInt(inputStr[2]);
                put(1, 1, N, putIndex, putCount);
            }
        }
    }

    private static long pick(int index, int start, int end, int pickIndex) {
        if (index < start || index > end) {
            return 0;
        }
        if (pickIndex >= start && pickIndex <= end) {
            int middle = (start + end)/2;
            if (pick(index*2, start, middle, pickIndex) < pickIndex) {
                pick(index*2, middle+1, end, pickIndex);
            }
            if (pick(index*2, middle+1, end, pickIndex) > pickIndex) {
                pick(index*2, start, middle+1, pickIndex);
            }
            return start;
        }
        int mid = (start + end)/2;
        return tree[index] = pick(index*2, start, mid, pickIndex) + pick(index*2+1, mid+1, start, pickIndex);
    }

    private static long put(int index, int start, int end, long updateIndex, int updateCount) {
        if (updateIndex < start || updateIndex > end) {
            return 0;
        }

        tree[index] = tree[index] + updateCount;

        if (start != end) {
            int mid = (start + end)/2;
            put(index*2, start, mid, updateIndex, updateCount);
            put(index*2+1, mid+1, end, updateIndex, updateCount);
            return tree[index] = tree[index*2] + tree[index*2+1];
        }
        return 0;
    }
}
