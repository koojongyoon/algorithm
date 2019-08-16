package com.company.backjoon.Tree.LazyPropagation;

import java.io.*;

//https://bowbowbow.tistory.com/4
public class Sol10999 {

    static long[] inputArr;
    static long[] tree;
    static long[] lazy;
    static int H;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] NMK = br.readLine().split(" ");
        int n = Integer.parseInt(NMK[0]);
        int m = Integer.parseInt(NMK[1]);
        int k = Integer.parseInt(NMK[2]);
        H = 1 << (int) Math.ceil(Math.log(n) / Math.log(2));

        inputArr = new long[n+1];
        tree = new long[4*n];
        lazy = new long[4*n];

        for (int i = 1; i <= n; i++) {
            inputArr[i] = Integer.parseInt(br.readLine());
        }

        initTree(1, 1, n);

        for (int i = 0; i < m+k; i++) {
            String[] commandStr = br.readLine().split(" ");
            int command = Integer.parseInt(commandStr[0]);
            int left = Integer.parseInt(commandStr[1]);
            int right = Integer.parseInt(commandStr[2]);
            if (command == 1) {
                int diff= Integer.parseInt(commandStr[3]);
                update(1, 1, n, left, right, diff);
            } else {
                bw.write(String.valueOf(query(1, 1, n, left, right)));
                bw.write("\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();

    }

    private static long query(int index, int start, int end, int left, int right) {
        propagate(index, start, end);

        if (start > right || left > end) {
            return 0;
        }
        if (start >= left && right >= end) {
            return tree[index];
        }
        int mid = (start + end)/2;
        return  query(index*2, start, mid, left, right) + query(index*2+1, mid+1, end, left,right);
    }

    private static long initTree(int index, int start, int end) {
        if (start == end) {
            return tree[index] = inputArr[start];
        }

        int mid = (start + end)/2;
        return tree[index] = initTree(index*2, start, mid) + initTree(index*2+1, mid+1, end);
    }

    private static void update(int index, int start, int end, int left, int right, int diff) {
        propagate(index, start, end);

        if (start > right || end < left) {
            return;
        }

        if (start >= left && end <= right) {
            lazy[index] = lazy[index] + diff;
            propagate(index, start, end);
            return;
        }
        int mid = (start + end)/2;
        update(index*2, start, mid, left, right, diff);
        update(index*2+1, mid+1, end, left, right, diff);
        tree[index] = tree[index*2] + tree[index*2+1];
    }

    private static void propagate(int index, int start, int end) {
        if (lazy[index] == 0) {
            return;
        }
        tree[index] = tree[index] + (end - start + 1) * lazy[index];
        if (index < H) {
            lazy[index*2] = lazy[index*2] + lazy[index];
            lazy[index*2+1] = lazy[index*2+1] + lazy[index];
        }
        lazy[index] = 0;
    }
}
