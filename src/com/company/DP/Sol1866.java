package com.company.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sol1866 {

    static int[] loadNumber;
    static int[] dp;
    static int[] sum;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] loadStr = br.readLine().split(" ");

        loadNumber = new int[N+1];
        dp = new int[N+1];
        sum = new int[N+1];

        for (int i = 1; i <= loadStr.length; i++) {
            loadNumber[i] = Integer.parseInt(loadStr[i-1]);
        }

        String[] loadKindStr = br.readLine().split(" ");
        int truckCost = Integer.parseInt(loadKindStr[0]);
        int hellicopter = Integer.parseInt(loadKindStr[1]);

        Arrays.sort(loadNumber);

        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i-1] + loadNumber[i];
        }

        // dp[i] = dp[i-1] + loadNumber[i] * t <- 이전값을 memoization해 두었다가 다음 값을 트럭으로 계산할
        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i-1] + loadNumber[i] * truckCost;
            for (int j = i; j >= 1; j--) {
                int mid = (i+j) / 2;
                int left = (loadNumber[mid] * (mid-j+1) - (sum[mid] - sum[j-1])) * truckCost;
                int right = ((sum[i] - sum[mid-1]) - (loadNumber[mid] * (i-mid+1))) * truckCost;
                int cost = left + right + hellicopter;
                dp[i] = Math.min(dp[i], dp[j-1] + cost);
            }
        }

        System.out.println(dp[N]);
    }
}
