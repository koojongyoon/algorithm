package com.company.backjoon.Tree.Penwick;

import java.io.*;

public class Sol3653_Fenwick {

    static class FenwickTree {
        private int n;
        private int[] tree;

        public FenwickTree(int n) {
            this.n = n;
            tree = new int[n];
        }

        public int sum(int pos) {
            int ret = 0;
            while (pos > 0) {
                ret = ret + tree[pos];
                pos = pos & (pos-1);
            }
            return ret;
        }

        public void add(int pos, int val) {
            while (pos < n) {
                tree[pos] = tree[pos] + val;
                pos = pos + (pos & -pos);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] line;
        FenwickTree fenwickTree;
        StringBuilder res = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        int[] pos;

        while (t-- > 0) {
            line = br.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            pos = new int[n+1];

            fenwickTree = new FenwickTree(n + m + 1);

            for (int i = 1; i <= n; i++) {
                pos[i] = i + m;
                fenwickTree.add(pos[i], 1);
            }

            line = br.readLine().split(" ");

            for (int i = 0; i < m; i++) {
                int k = Integer.parseInt(line[i]);
                // 자기 자신을 포함한 합이 출력 되므로 -1 해준다
                res.append((fenwickTree.sum(pos[k]) -1) + " ");

                // 기존 위치 -1로 해서 부분합값에 반영되도록 한다.
                fenwickTree.add(pos[k], -1);
                pos[k] = m - i;

                //새로 쌓인 위치를 1로 바꿔서 부분합값에 반영되도록 한다.
                fenwickTree.add(pos[k], 1);
            }
            res.append("\n");
        }

        bw.write(res.toString());
        bw.close();
        br.close();
    }
}
