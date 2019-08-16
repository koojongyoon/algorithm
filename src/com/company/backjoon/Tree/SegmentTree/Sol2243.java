package com.company.backjoon.Tree.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol2243 {

    static int MAX = 1000000;
    static int N;
    static int[] box;
    static long ret;
    static int treeSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        treeSize = 1 << (int) Math.ceil(Math.log(MAX)/ Math.log(2));
        box = new int[MAX*4];

        for (int i = 0; i < N; i++) {
            String[] inputStr = br.readLine().split(" ");
            ret = 0;
            int command = Integer.parseInt(inputStr[0]);
            if (command == 1) {
                int candyIndex = Integer.parseInt(inputStr[1]);
                long getIndex = query(1, 0, MAX-1, candyIndex);
                update(1, 0, MAX-1, getIndex, -1);
            } else if (command == 2) {
                int boxIndex = Integer.parseInt(inputStr[1]);
                int candyCount = Integer.parseInt(inputStr[2]);
                update(1, 0,  MAX-1, boxIndex, candyCount);
            }
        }
    }

    private static long query(int index, int start, int end, int candyIndex) {
        if (start == end && ret == 0) {
            System.out.println(start);
            return start;
        }
        int mid = (start + end)/2;
        if (ret == 0 && (index*2 <= MAX*4 && box[index*2] >= candyIndex)) {
            return ret = query(index*2, start, mid, candyIndex);
        }
        candyIndex = candyIndex - box[index*2];
        if (ret == 0 && (index*2+1 <= MAX*4 && box[index*2+1] >= candyIndex)) {
            return ret = query(index*2+1, mid+1, end, candyIndex);
        }
        return -1;
    }

    private static void update(int index, int start, int end, long updateIndex, int candyCount) {
        if (updateIndex < start && updateIndex > end) {
            return;
        }
        box[index] = box[index] + candyCount;
        if (start != end) {
            int mid = (start + end) / 2;
            update(index * 2, start, mid, updateIndex, candyCount);
            update(index * 2 + 1, mid + 1, end, updateIndex, candyCount);
        }
    }
}
