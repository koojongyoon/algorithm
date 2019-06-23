package com.company.backjoon.DP;

import java.util.Scanner;

//게하 : https://m.post.naver.com/viewer/postView.nhn?volumeNo=19234191&
public class Sol1309 {

    static long result = 0;
    static long[] lineCount;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int wooriCount = sc.nextInt();
        lineCount = new long[100001];
        // 사자의 수와 우리의 수가 같을때까지
        lineCount[1] = 3;
        lineCount[2] = 7;

        for (int i = 3; i < 100001; i++) {
            lineCount[i] = (lineCount[i-1] * 2 + lineCount[i-2])%9901;
        }
        result = lineCount[wooriCount];
        System.out.println(result);
    }
}
