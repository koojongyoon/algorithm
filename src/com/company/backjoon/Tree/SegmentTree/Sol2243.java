package com.company.backjoon.Tree.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol2243 {

    static long[] arr;
    static long[] tree;
    static int SIZE = 1000001;
    static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        arr = new long[SIZE+1];
        tree = new long[4*SIZE];
        for (int i = 0; i < n; i++) {
            String[] inputStr = br.readLine().split(" ");
            int a = Integer.parseInt(inputStr[0]);
            int idx = Integer.parseInt(inputStr[1]);
            if (a == 1) {
                long getIndex = pickCandy(1, 1, SIZE, idx);
                System.out.println(getIndex);
                putCandy(1, 1, SIZE, getIndex, -1);
                result = 0;
            } else {
                int putCnt = Integer.parseInt(inputStr[2]);
                putCandy(1, 1, SIZE, idx, putCnt);
            }
        }
    }

    private static long pickCandy(int index, long start, long end, long updateIndex) {
        if (start == end && result == 0) {
            return start;
        }
        long mid = (start + end) / 2;

        if (result == 0 && (index*2 <= SIZE && tree[index*2] >= updateIndex)) {
            return result = pickCandy(index*2, start, mid, updateIndex);
        }
        updateIndex = updateIndex - tree[index*2];
        if (result == 0 && (index*2 + 1 <= SIZE && tree[index*2+1] >= updateIndex)) {
            return result = pickCandy(index*2+1, mid+1, end, updateIndex);
        }
        return 0;
    }

    private static void putCandy(int index, long start, long end, long updateIndex, long updateValue) {
        if (updateIndex < start || updateIndex > end) {
            return;
        }
        tree[index] = tree[index] + updateValue;
        if (start != end) {
            long mid = (start + end) / 2;
            putCandy(index * 2, start, mid, updateIndex, updateValue);
            putCandy(index * 2 + 1, mid + 1, end, updateIndex, updateValue);
        }
    }
}
