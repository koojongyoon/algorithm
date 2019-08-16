package com.company.backjoon.Tree.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol6549 {

    static int N;
    static int[] arr;
    static int[] tree;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] histogram;

        while ((histogram = br.readLine().split(" ")).length > 1) {
            N = Integer.parseInt(histogram[0]);
            arr = new int[N+1];
            tree = new int[N*4];
            for (int i = 1; i <= N; i++) {
                arr[i-1] = Integer.parseInt(histogram[i]);
            }
            init(1, 0, N-1);
            System.out.println(getMaxWidth(0, N-1));
        }
    }

    private static int init(int index, int start, int end) {
        if (start == end) {
            return tree[index] = start;
        }
        int mid = (start + end)/2;
        int leftMinIndex = init(index*2, start, mid);
        int rightMinIndex = init(index*2+1, mid+1, end);
        return tree[index] = arr[leftMinIndex] < arr[rightMinIndex] ? leftMinIndex : rightMinIndex;
    }

    private static int query(int index, int start, int end, int left, int right) {
        if (start > right ||end < left) {
            return INF;
        }
        if (start >= left && end <= right) {
            return tree[index];
        }

        int mid = (start + end) / 2;
        int leftMinIndex = query(index*2, start, mid, left, right);
        int rightMinIndex = query(index*2+1, mid+1, end, left, right);

        if (leftMinIndex == INF) {
            return rightMinIndex;
        } else if (rightMinIndex == INF) {
            return leftMinIndex;
        } else {
            return arr[leftMinIndex] < arr[rightMinIndex] ? leftMinIndex : rightMinIndex;
        }
    }

    private static long getMaxWidth(int left, int right) {
        int minIndex = query(1, 0, N-1, left, right);
        long maxWidth = (long) (right - left + 1) * (long) arr[minIndex];
        if (left <= minIndex-1) {
            maxWidth = Math.max(maxWidth, getMaxWidth(left, minIndex-1));
        }
        if (minIndex+1 <= right) {
            maxWidth = Math.max(maxWidth,  getMaxWidth(minIndex+1, right));
        }
        return maxWidth;
    }
}
