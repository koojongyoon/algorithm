package com.company.backjoon.Tree.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol10868 {

    static int INF = Integer.MAX_VALUE;
    static int N;
    static int M;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NM = br.readLine().split(" ");

        N = Integer.parseInt(NM[0]);
        M = Integer.parseInt(NM[1]);

        tree = new int[N*4];

        for (int i = 1; i <= N; i++) {
            int val = Integer.parseInt(br.readLine());
            update(i, val, 1,1, N);
        }

        for (int i = 0; i < M; i++) {
            String[] startEnd = br.readLine().split(" ");
            int start = Integer.parseInt(startEnd[0]);
            int end = Integer.parseInt(startEnd[1]);
            System.out.println(query(1, 1, N, start, end));
        }
    }

    private static int update(int pos, int val, int index, int start, int end) {
        if (start > pos || end < pos) {
            return tree[index];
        }
        if (start == end) {
            return tree[index] = val;
        }
        int mid = (start+end)/2;
        return tree[index] = Math.min(update(pos, val, index*2, start, mid), update(pos, val, index*2+1, mid+1, end));
    }

    private static int query(int index, int start, int end, int findStart, int findStop) {
        if (start > findStop || end < findStart) {
            return INF;
        }
        if (start >= findStart && end <= findStop) {
            return tree[index];
        }
        int mid = (start+end)/2;
        return Math.min(query(index*2, start, mid, findStart, findStop)
                , query(index*2+1, mid+1, end, findStart, findStop));
    }
}
