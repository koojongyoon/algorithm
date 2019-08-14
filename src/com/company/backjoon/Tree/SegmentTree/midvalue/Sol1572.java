package com.company.backjoon.Tree.SegmentTree.midvalue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol1572 {

    static long ans, mid;
    static final int max_node = 65537;
    static int[] data = new int[250005];
    static int N, K, start;
    static long[] tree = new long[4 * max_node];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        mid = ( K / 2 ) + 1;
        start = 1;
        ans = 0;
        while(max_node > start) {
            start = start *2;
        }

        int num = 0;
        for (int i=1; i<= N; i++) {
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
            data[i] = num;
            update(num, 1);
            if (i >= K) {
                long tmp = query();
                ans = ans + tmp;
                update(data[i - K + 1] ,0);
            }
        }
        System.out.println(ans);
    }
    public static long query() {
        long left = 0;
        long right = 0;
        int idx = 1; // 루트에서 출발
        long target = mid;
        while(idx < start) {
            left = tree[(idx*2)];
            right = tree[(idx*2) + 1];

            if (target <= left) {
                idx = idx*2;
            } else if(target > left){
                target = target - left;
                idx = (idx*2) + 1;
            }
        }

        return (idx - start);
    }

    public static void update (int idx, int chk) {
        int dx = idx + start;
        if(chk == 1) {
            tree[dx]++;
        } else if(chk == 0) {
            tree[dx]--;
        }
        while(dx > 1) {
            dx = dx / 2;
            tree[dx] = tree[dx * 2] + tree[dx * 2 + 1];
        }
    }
}
