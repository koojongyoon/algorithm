package com.company.backjoon.Tree.LazyPropagation;

import java.io.*;

public class Sol1395 {
    private static long[] tree;
    private static boolean[] lazy;
    private static long H;

    //홀수이면 불이 켜짐
    //짝수이면 불이 꺼짐
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputStr = br.readLine().split(" ");
        int n = Integer.parseInt(inputStr[0]);
        int m = Integer.parseInt(inputStr[1]);

        tree = new long[4*n];
        lazy = new boolean[4*n];
        H = 1 << (int) Math.ceil(Math.log(n) / Math.log(2));

        for (int i = 0; i < m; i++) {
            String[] input = br.readLine().split(" ");
            int command = Integer.parseInt(input[0]);
            int left = Integer.parseInt(input[1]);
            int right = Integer.parseInt(input[2]);
            if (command == 0) {
                update(1, 1, n, left, right);
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

    private static void update(int index, int start, int end, int left, int right) {
        propagate(index, start, end);

        if (start > right || end < left) {
            return;
        }

        if (start >= left && end <= right) {
            lazy[index] = lazy[index] ^ !lazy[index];
            propagate(index, start, end);
            return;
        }

        int mid = (start + end)/2;
        update(index*2, start, mid, left, right);
        update(index*2+1, mid+1, end, left, right);
        tree[index] = tree[index*2] + tree[index*2+1];
    }

    private static void propagate(int index, int start, int end) {
        if (!lazy[index]) {
            return;
        }
        tree[index] = Math.abs((end - start + 1) - tree[index]);
        if (index < H) {
            lazy[index*2] = lazy[index*2] ^ lazy[index];
            lazy[index*2+1] = lazy[index*2+1] ^ lazy[index];
        }
        lazy[index] = !lazy[index];
    }
}

