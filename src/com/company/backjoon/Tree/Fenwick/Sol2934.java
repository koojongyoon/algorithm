package com.company.backjoon.Tree.Fenwick;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol2934 {

    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new int[100001];

        for (int i = 0; i < N; i++) {
            String[] LR = br.readLine().split(" ");
            int left = Integer.parseInt(LR[0]);
            int right = Integer.parseInt(LR[1]);

            int l = count(left);
            int r = count(right);
            System.out.println(l+r);

            sum(left,-l);
            sum(left+1,l);
            sum(right,-r);
            sum(right+1,r);
            sum(left+1,1);
            sum(right,-1);

        }
    }

    private static int count(int pos) {
        int ret = 0;
        while(pos > 0) {
            ret = ret + tree[pos];
            pos = pos & (pos-1);
        }
        return ret;
    }

    private static void sum(int pos, int val) {
        while(pos < tree.length) {
            tree[pos] = tree[pos] +val;
            pos = pos + (pos & -pos);
        }
    }
}
