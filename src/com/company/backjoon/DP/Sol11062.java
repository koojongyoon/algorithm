package com.company.backjoon.DP;

import java.util.Scanner;

public class Sol11062 {

    static int[][] dp;
    static int[] cards;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int test = sc.nextInt();

        for(int t = 0 ; t < test ; t++){

            int n = sc.nextInt();

            cards= new int[n];
            for(int i = 0 ; i < n ; i++) {
                cards[i] = sc.nextInt();
            }

            dp = new int[n][n];

            System.out.println(BestCard(0, n-1));
        }
    }

    static int BestCard(int left, int right){
        if (dp[left][right] != 0)
            return dp[left][right];

        int sum=0;

        if (left == right) {
            return cards[left];
        } else {
            for(int i = left; i <= right; i++) {
                sum = sum + cards[i];
            }
            int tempSum= Math.max(sum - BestCard(left+1, right), sum - BestCard(left, right-1));

            dp[left][right] = tempSum;

            return tempSum;
        }
    }
}
