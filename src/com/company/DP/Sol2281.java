package com.company.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sol2281 {

    static int N;
    static int M;
    static int[] len;
    static int[][] note;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);

        len = new int[1001];
        note = new int[1000][1000];

        for (int i = 0; i < 1000; i++) {
            Arrays.fill(note[i], -1);
        }

        for (int i = 0; i < N; i++) {
            len[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(minSquareSum(len[0] + 1, 1));
    }

    private static int minSquareSum(int col, int cur) {
        // 모든 단어를 다 사용함
        if (cur == N) {
            return 0;
        }
        //해당 열을 꽉 채워쓰거나 핳ㄴ칸만 남는 경우
        if (col >= M) {
            return minSquareSum(len[cur]+1, cur+1) + (col == M ? 1 : 0);
        }
        int ret = note[col][cur];
        if (ret != -1) {
            return ret;
        }
        //다음 줄로 옮겨쓰면서 남은 여백의 칸수의 제곱을 더함
        ret = minSquareSum(len[cur] + 1, cur + 1) + (M-col+1) * (M-col+1);
        //해당 줄에 이어서 쓸수 있을 경우
        if (col+len[cur] <= M) {
            ret = Math.min(ret, minSquareSum(col + len[cur]+1, cur+1));
        }
        return ret;
    }
}
