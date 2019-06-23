package com.company.backjoon.DP;

import java.math.BigInteger;
import java.util.Scanner;

public class Sol1793 {
    public static void main(String[] args) {
        BigInteger[] dp = new BigInteger[251];
        dp[0] = new BigInteger("1");
        dp[1] = new BigInteger("1");
        dp[2] = new BigInteger("3");
        for (int i = 3; i <=250; i++) {
            dp[i] = dp[i-2].multiply(new BigInteger("2"));
            dp[i] = dp[i].add(dp[i-1]);
        }
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            System.out.println(dp[n]);
        }
    }
}
