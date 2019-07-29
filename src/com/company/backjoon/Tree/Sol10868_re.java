package com.company.backjoon.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol10868_re {

    static int[] treeArr;
    static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputStr = br.readLine().split(" ");
        int N = Integer.parseInt(inputStr[0]);
        int M = Integer.parseInt(inputStr[1]);

        treeArr = new int[4*N];

        for (int n = 1; n <= N; n++) {
            int updateValue = Integer.parseInt(br.readLine());
            update(1, 1, N, updateValue, n);

        }

        for (int m = 0; m < M; m++) {
            String[] query = br.readLine().split(" ");
            int start = Integer.parseInt(query[0]);
            int end = Integer.parseInt(query[1]);
            System.out.println(query(1, 1, N, start, end));
        }
    }

    private static int query(int index, int nodeStart, int nodeEnd, int findStart, int findEnd) {
        if (nodeEnd < findStart  || nodeStart > findEnd) {
            return INF;
        }
        if (nodeStart >= findStart && nodeEnd <= findEnd) {
            return treeArr[index];
        }
        int mid = (nodeStart + nodeEnd) / 2;
        return  Math.min(query(index*2, nodeStart, mid, findStart, findEnd),
                query(index*2+1, mid+1, nodeEnd, findStart, findEnd));
    }

    private static int update(int index, int start, int end, int updateValue, int pos) {
        if (pos > end || start > pos) {
            return treeArr[index];
        }
        if (start == end) {
            return treeArr[index] = updateValue;
        }
        int mid = (start + end) / 2;
        return treeArr[index] = Math.min(update(index*2, start, mid, updateValue, pos),
                update(index*2+1, mid+1, end, updateValue, pos));
    }
}
