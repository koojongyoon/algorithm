package com.company.backjoon.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol2042_re {

    static long[] inputArr;
    static long[] treeArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputStr = br.readLine().split(" ");

        int N = Integer.parseInt(inputStr[0]);
        int M = Integer.parseInt(inputStr[1]);
        int K = Integer.parseInt(inputStr[2]);

        inputArr = new long[N+1];
        treeArr = new long[4*N];

        for (int i = 1; i < N; i++) {
            inputArr[i] = Integer.parseInt(br.readLine());
        }

        initTree(1, 0, N-1);

        for (int i = 0; i < M + K; i++) {
            String[] commandStr = br.readLine().split(" ");
            int a = Integer.parseInt(commandStr[0]);
            if (a == 1) {
                int targetNumber = Integer.parseInt(commandStr[1]);
                int updateNumber = Integer.parseInt(commandStr[2]);
                int updateIndex = targetNumber - 1;
                long diff = updateNumber - inputArr[updateIndex];
                inputArr[updateIndex] = updateNumber;

                update(1, 0, N-1, updateIndex, diff);
            } else if (a == 2) {
                int startIndex = Integer.parseInt(commandStr[1]);
                int endIndex = Integer.parseInt(commandStr[2]);
                sum(1, 0, N-1, startIndex - 1, endIndex - 1);
            }
        }

    }

    private static long sum(int index, int nodeStart, int nodeEnd, int findLeft, int findRight) {
        if (findLeft > nodeEnd || findRight < nodeStart) {
            return 0;
        }
        if (findLeft <= nodeStart && nodeEnd <= findRight) {
            return treeArr[index];
        }
        int mid = (nodeStart + nodeEnd)/2;
        return sum(index * 2, nodeStart, mid, findLeft, findRight)
                + sum(index*2+1, mid+1, nodeEnd, findLeft, findRight);
    }

    private static long initTree(int index, int start, int end) {
        if (start == end) {
            treeArr[index] = inputArr[index];
        }
        int mid = (start + end) / 2;
        return treeArr[index] = initTree(index*2, start, mid) + initTree(index*2+1, start, end);
    }

    private static void update(int index, long start, long end, long updateIndex, long diff){
        if (start > updateIndex || end < updateIndex) {
            return;
        }
        treeArr[index] = treeArr[index] + diff;
        if (start != end) {
            long mid = (start + end) / 2;
            update(index*2 , start, mid, updateIndex, diff);
            update(index*2 + 2 , mid, end, updateIndex, diff);
        }
    }
}
