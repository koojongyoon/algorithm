package com.company.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol10868 {

    static int N;
    static int M;
    static int[] tree;
    static int[] inputArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NM = br.readLine().split(" ");

        N = Integer.parseInt(NM[0]);
        M = Integer.parseInt(NM[1]);

        tree = new int[N*4];
        inputArr = new int[N+1];

        for (int i = 1; i <= N; i++) {
            inputArr[i] = Integer.parseInt(br.readLine());
        }

        initTree(1);

        for (int i = 0; i < M; i++) {
            String[] startEnd = br.readLine().split(" ");
            int start = Integer.parseInt(startEnd[0]);
            int end = Integer.parseInt(startEnd[1]);
            query(start, end);
        }
    }

    private static int initTree(int index, int start, int end) {
        int mid = (start+end) / 2;
        tree[index] = initTree(index+1, start, mid) + initTree(index+1, mid+1, end);
    }

    private static int query(int start, int end) {
        return 0;
    }
}
