package com.company.backjoon.Tree;

import java.io.*;

public class Sol1275 {
    static long inputArr[];
    static long tree[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] NQ = br.readLine().split(" ");
        int n = Integer.parseInt(NQ[0]);
        int q = Integer.parseInt(NQ[1]);
        inputArr = new long[n+1];
        tree = new long[4*n];
        String[] inputNum = br.readLine().split(" ");

        for (int i = 1; i <= n; i++) {
            inputArr[i] = Integer.parseInt(inputNum[i-1]);
        }

        initTree(1, 1, n);

        for (int i = 0; i < q; i++) {
            String[] inputQuery = br.readLine().split(" ");
            int x = Integer.parseInt(inputQuery[0]);
            int y = Integer.parseInt(inputQuery[1]);
            if (y >= x) {
                bw.write(String.valueOf(sum(1, 1, n, x, y)));
            } else {
                bw.write(String.valueOf(sum(1, 1, n, y, x)));
            }
            bw.write("\n");

            int a = Integer.parseInt(inputQuery[2]);
            int b = Integer.parseInt(inputQuery[3]);
            long diff = b- inputArr[a];
            inputArr[a] = b;
            update(1, 1, n, a, diff);

        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static long initTree(int index, int start, int end) {
        if (start == end) {
            return tree[index] = inputArr[start];
        }
        int mid = (start+end)/2;
        return tree[index] = initTree(index*2, start, mid)
                + initTree(index*2+1, mid+1, end);
    }

    private static long sum(int index, long start, long end, long left, long right) {
        if (start > right || end < left) {
            return 0;
        }
        if (start >= left && end <= right) {
            return tree[index];
        }
        long mid = (start + end) / 2;
        return sum(index*2, start, mid, left, right)
                + sum(index*2+1, mid+1, end, left, right);
    }

    private static void update(int index, long start, long end, int updateIndex, long diff) {
        if (updateIndex < start || updateIndex > end) {
            return;
        }
        tree[index] = tree[index] + diff;
        if (start != end) {
            long mid = (start + end) / 2;
            update(index * 2, start, mid, updateIndex, diff);
            update(index * 2 + 1, mid + 1, end, updateIndex, diff);
        }
    }
}
