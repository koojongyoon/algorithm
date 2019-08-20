package com.company.backjoon.Tree.SegmentTree.LIS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol1365 {

    static int N;
    static int[] tree;
    static int start;
    static int input[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");

        input = new int [N+1];
        tree = new int [4*N];
        for(int i = 1 ;i <= N;i++){
            input[i] = Integer.parseInt(str[i-1]);
        }


        for(int i = 1; i <=N;i++){
            int num = input[i];
            int len = getMax(1,1,N, 1,num-1)+1;
            update (num,len);
        }
        System.out.println(N - getMax(1,1, N, 1, N));
    }

    public static void update (int idx ,int val){
        int index = start + idx - 1;
        tree[index]=val;
        while (index>1){
            index = index / 2;
            tree[index] = Math.max(tree[index*2], tree[index*2+1]);
        }
    }

    public static int getMax(int index, int left, int right,int start,int end){
        if (start > right || end < left) {
            return 0;
        }

        if (left <= start && end <= right) {
            return tree[index];
        }

        int mid = (start+end)/2;
        return Math.max( getMax(index*2, start, mid, left, right) , getMax(index*2+1, mid+1, end, left, right));
    }
}
