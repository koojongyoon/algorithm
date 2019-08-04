package com.company.backjoon.Tree.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol2268 {

    static int N, M, H;
    static long tree[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NM = br.readLine().split(" ");

        N = Integer.parseInt(NM[0]);
        M = Integer.parseInt(NM[1]);

        StringBuilder sb = new StringBuilder();
        H = (int) Math.ceil(Math.log(N) / Math.log(2));

        tree = new long[2 * H];

        for (int i = 0; i < M; i++) {
            String[] inputStr = br.readLine().split(" ");
            int command = Integer.parseInt(inputStr[0]);
            if (command == 0) {

                int left = Integer.parseInt(inputStr[1]);
                int right = Integer.parseInt(inputStr[2]);

                if (left > right) {
                    int temp = left;
                    left = right;
                    right = temp;
                }
                System.out.println(sum(1, 1, H, left, right));
            } else if (command == 1) {

                int updateIndex = Integer.parseInt(inputStr[1]);
                int modifyNumber = Integer.parseInt(inputStr[2]);
                update(updateIndex, modifyNumber);
            }
        }
    }

    public static void update(int idx, int val) {
        int index = H + idx - 1;
        tree[index] = val;

        while (index > 1) {
            index /= 2;
            tree[index] = tree[index * 2] + tree[index * 2 + 1];
        }
    }

    public static long sum(int index, int start, int end, int left, int right) {
        if (end < left || start > right) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[index];
        }
        int mid = (start + end) / 2;
        return sum(index * 2, start, mid, left, right)
                + sum(index * 2 + 1, mid + 1, end, left, right);
    }
}