package com.company.backjoon.Tree.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

//x에서  i까지의 거리는  0점에서 x 까지의 거리 - 0 에서 i 까지의 거리를 이용 하여 구한다
//i 의 비용
// (  i 의 왼쪽에 있는 나무의 수 * i 까지의 거리 ) - (i 앞에 있는 나무들의 0 에서 나무까지의 거리 누적합) +
// (i 뒤에 있는 나무들의 끝 에서 나무까지의 거리 누적합) (  i 의 오른쪽에 있는 나무의 수 * i 까지의 거리 )
public class Sol1280 {
    static int N, cnt_tree[], start = 200001, input[], mod = 1000000007;
    static long sum_tree[];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        for(start = 1 ; start<200001;start*=2);
        cnt_tree = new int[2 * start];
        sum_tree = new long[2 * start];
        input = new int[N];

        BigInteger mul = BigInteger.ONE;
        for (int i = 0; i < N; i++) {
            long current = Long.parseLong(bf.readLine());
            long left = getCNT(0, (int) (current - 1), 1, 0, start - 1) * current - getSum(0, (int) (current - 1), 1, 0, start - 1);
            long right = getSum((int) (current + 1), start-1, 1, 0, start - 1)-getCNT((int) (current + 1), start-1, 1, 0, start - 1) * current;
            long result = left + right;
            update((int) current);

            if (i != 0) {
                mul  = mul.multiply(BigInteger.valueOf(result)).mod(BigInteger.valueOf(mod));
            }
        }
        System.out.println(mul);
    }

    public static void update(int idx) {
        int index = start + idx;
        cnt_tree[index] = cnt_tree[index] + 1;
        sum_tree[index] = sum_tree[index] + idx;
        while (index > 1) {
            index = index / 2;
            cnt_tree[index] = cnt_tree[index * 2] + cnt_tree[index * 2 + 1];
            sum_tree[index] = sum_tree[index * 2] + sum_tree[index * 2 + 1];
        }
    }

    public static long getCNT(int L, int R, int idx, int temp_L, int temp_R) {
        if (temp_L > R || temp_R < L) {
            return 0;
        }
        if (L <= temp_L && temp_R <= R) {
            return cnt_tree[idx];
        }
        int mid = (temp_L + temp_R) / 2;
        return getCNT(L, R, idx * 2, temp_L, mid) + getCNT(L, R, idx * 2 + 1, mid + 1, temp_R);
    }

    public static long getSum(int L, int R, int idx, int temp_L, int temp_R) {
        if (temp_L > R || temp_R < L) {
            return 0;
        }
        if (L <= temp_L && temp_R <= R) {
            return sum_tree[idx];
        }
        int mid = (temp_L + temp_R) / 2;
        return getSum(L, R, idx * 2, temp_L, mid) + getSum(L, R, idx * 2 + 1, mid + 1, temp_R);
    }
}
