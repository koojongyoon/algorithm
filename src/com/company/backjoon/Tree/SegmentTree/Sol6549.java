package com.company.backjoon.Tree.SegmentTree;

import java.io.*;

//https://m.blog.naver.com/PostView.nhn?blogId=occidere&logNo=221057769152&proxyReferer=https%3A%2F%2Fwww.google.com%2F
public class Sol6549 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] histogram;
        SegmentTree segmentTree;
        int[] arr;
        StringBuilder sb = new StringBuilder();

        while ((histogram = br.readLine().split(" ")).length > 1) {
            int n = Integer.parseInt(histogram[0]);
            arr = new int[n];
            for (int i = 1; i <= n; i++) {
                arr[i-1] = Integer.parseInt(histogram[i]);
            }
            segmentTree = new SegmentTree(arr);
            sb.append(segmentTree.getMaxWidth(0, n-1) + "\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    public static class SegmentTree {
        public int n;
        public int[] arr;
        public int[] rangeMinIndex;
        public int INF = Integer.MAX_VALUE;

        public SegmentTree(int[] arr) {
            n = arr.length;
            this.arr = arr.clone();
            rangeMinIndex = new int[n*4];  //segment tree를 만들때 *4 를 한다.

            init(0, n-1, 1);
        }

        public int init(int left, int right, int node) {
            if (left == right) {
                return rangeMinIndex[node] = left;
            }

            int mid = (left + right)/2;
            int leftMinIndex = init(left, mid, node * 2);
            int rightMinIndex = init(mid+1, right, (node*2)+1);
            return rangeMinIndex[node] = arr[leftMinIndex] < arr[rightMinIndex] ? leftMinIndex : rightMinIndex;
        }

        public int query (int left, int right, int node, int nodeLeft, int nodeRight) {

            if (nodeRight < left || right < nodeLeft) {
                return INF;
            }

            if (left <= nodeLeft && nodeRight <= right) {
                return rangeMinIndex[node];
            }

            int mid = (nodeLeft + nodeRight) / 2;
            int leftMinIndex = query(left, right, node*2, nodeLeft, mid);
            int rightMinIndex = query(left, right, (node*2)+1, mid+1, nodeRight);

            if (leftMinIndex == INF) {
                return rightMinIndex;
            } else if (rightMinIndex == INF) {
                return leftMinIndex;
            } else {
                return arr[leftMinIndex] < arr[rightMinIndex] ? leftMinIndex : rightMinIndex;
            }
        }

        public long getMaxWidth(int left, int right) {
            int minIndex = query(left, right, 1, 0, n-1);
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
}
