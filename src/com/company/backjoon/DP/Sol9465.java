package com.company.backjoon.DP;

import java.util.Scanner;

public class Sol9465 {

    public static void main(String[] args) {

        Scanner sc =  new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            int[][] sticker = initialInputSet(sc, n);
            int maxScore = cutSticker(sticker, n);
            System.out.println(maxScore);

        }
    }

    private static int cutSticker(int[][] sticker, int n) {
        int[][] DP = new int[2][100050]; // sticker 점수 저장
        for (int i = 2; i <= n+1; i++) {
            DP[0][i] = Math.max(Math.max(DP[0][i-1], DP[1][i-1] + sticker[0][i]), DP[1][i - 2]+ sticker[0][i]);
            DP[1][i] = Math.max(Math.max(DP[1][i-1], DP[0][i-1] + sticker[1][i]), DP[0][i - 2]+ sticker[1][i]);
        }
        return Math.max(DP[0][n+1], DP[1][n+1]);
    }

    private static int[][] initialInputSet(Scanner sc, int n) {
        int[][] arr = new int[2][100050];
        for (int j = 0; j < 2; j++) {
            for (int k = 2; k <= n+1; k++) {
                arr[j][k] = sc.nextInt();
            }
        }
        return arr;
    }
}
