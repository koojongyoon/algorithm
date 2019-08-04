package com.company.backjoon.Tree.Fenwick;

import java.io.*;

public class Sol3653 {

    private static int size;
    private static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] line;
        int T = Integer.parseInt(br.readLine());
        int[] pos;

        for (int t = 0; t < T; t++) {
            line = br.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);

            pos = new int[n+1];
            tree = new int[n + m + 1];
            size = n + m + 1;

            for (int i = 1; i <= n; i++) {
                pos[i] = i + m;
                update(pos[i], 1);
            }

            line = br.readLine().split(" ");

            for (int i = 0; i < m; i++) {
                int k = Integer.parseInt(line[i]);
                // 자기 자신을 포함한 합이 출력 되므로 -1 해준다
                bw.write((sum(pos[k]) -1) + " ");

                // 기존 위치 -1로 해서 부분합값에 반영되도록 한다.
                update(pos[k], -1);
                pos[k] = m - i;

                //새로 쌓인 위치를 1로 바꿔서 부분합값에 반영되도록 한다.
                update(pos[k], 1);
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void update(int pos, int val) {
        for (int i = 0; i < size; i = i + (i&-i)) {
            tree[pos] = tree[pos] + val;
        }
    }

    private static int sum(int pos) {
        int result = 0;
        for (int i = pos; i > 0; i = i - (i & -i)) {
            result = result + tree[pos];
        }
        return result;
    }

    private static void update2(int pos, int val) {
        while (pos < size) {
            tree[pos] = tree[pos] + val;
        }
    }

    private static  int sum2(int pos) {
        int ret = 0;
        while (pos > 0) {
            ret = ret + tree[pos];
            pos = pos & (pos-1);
        }
        return ret;
    }
}
