package com.company.backjoon.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol7578 {

    static int[] tree;
    static int[] first;
    static int[] second;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        first = new int[1000001];
        second = new int[N+1];
        tree = new int[4*N];

        long result = 0;

        st = new StringTokenizer(br.readLine());
        for (int j = 1; j <= N; j++) {
            first[Integer.parseInt(st.nextToken())] = j;
        }

        st = new StringTokenizer(br.readLine());
        for (int j = 1; j <= N; j++) {
            second[j] = first[Integer.parseInt(st.nextToken())];
        }

        for (int j = 1; j <= N; j++) 	{
            update(1,1, N,1, second[j]-1);
            result = result + sum(1,1,N, second[j]);
        }

        System.out.println(result);
    }

    static private void update(int index, int start, int end , int left, int right) {
        if(right < start || end < left) {
            return;
        }
        if (start >= left && end <= right) {
            tree[index] += 1;
        }
        else {
            int mid = (start+end)/2;
            update(index*2, start, mid, left, right);
            update(index*2+1,  mid+1, end, left, right);
        }
    }

    static private int sum(int index, int start, int end, int leaf) {
        if(leaf < start || end < leaf) {
            return 0;
        }
        if( start == end) {
            return tree[index];
        }
        int mid = (start+end)/2;
        return sum(index*2, start, mid, leaf)
                + sum(index*2+1, mid+1, end, leaf)
                + tree[index];
    }
}
