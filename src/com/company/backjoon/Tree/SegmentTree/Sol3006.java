package com.company.backjoon.Tree.SegmentTree;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Sol3006 {

    static final int max_value = 100001;
    static int N;
    static int[] tree = new int[4 * max_value];
    static Node[] data = new Node[max_value];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int num = 0;

        for(int i=1; i<= N; i++) {
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
            data[i] = new Node(num, i);
        }

        Arrays.sort(data, 1, N+1, new Sort());
        init(1, 1, N);

        int s =1, e = N;

        int result = 0;

        for(int i=1; i<= N; i++) {
            if(i % 2 != 0) {
                int target = data[s].index;
                result = getCount(1, 1, N, 1, target -1);
                update(1, 1, N, target);
                s++;
            } else {
                int target = data[e].index;
                result = getCount(1, 1, N, target+1, N);
                update(1, 1, N, target);
                e--;
            }
            bw.write(result + "\n");
        }
        bw.flush();
    }

    public static void update(int index, int start, int end, int value) {
        if(value < start || end < value) {
            return;
        }
        tree[index] = tree[index] - 1;
        if (start == end) {
            return;
        }
        int mid = (start + end) / 2;
        update(index*2, start, mid, value);
        update(index*2+1, mid+1, end, value);
    }

    public static int getCount(int index, int start, int end, int left, int right){
        if (end < left || right < start) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[index];
        }
        int mid = (start + end) / 2;
        return getCount(index*2, start, mid, left, right) + getCount(index*2+1, mid+1, end, left, right);
    }

    public static int init(int node, int start, int end) {
        if(start == end) {
            return tree[node] = 1;
        }
        int mid = (start + end) / 2;
        return tree[node] = init(node*2, start, mid) + init(node*2+1, mid+1, end);
    }

    static class Sort implements Comparator<Node> {
        @Override
        public int compare(Node a, Node b) {
            if(a.num < b.num) return -1;
            else if(a.num > b.num) return 1;
            else return 0;
        }
    }

    static class Node {
        int num, index;
        Node(int a, int b) {
            num = a; index = b;
        }
    }

}
