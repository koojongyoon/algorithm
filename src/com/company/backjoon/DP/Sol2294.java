package com.company.backjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sol2294 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int coinType = Integer.parseInt(input[0]);
        int coinSum = Integer.parseInt(input[1]);

        int[] coinTypeArr = new int[coinType+1];
        for(int i = 1; i <= coinType; i++) {
            coinTypeArr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coinTypeArr);

        int[] coinNeedCount = new int[coinSum+1];
        for(int i = 1; i <= coinSum; i++) {
            coinNeedCount[i] = 100001;
        }
        coinNeedCount[0] = 0;

        for(int i = 1; i <= coinType; i++) {
            for(int k = coinTypeArr[i]; k <= coinSum; k++) {
                coinNeedCount[k] = Math.min(coinNeedCount[k], coinNeedCount[k-coinTypeArr[i]]+1);
            }
        }

        if (coinNeedCount[coinSum] == 100001) {
            System.out.println(-1);
        } else {
            System.out.println(coinNeedCount[coinSum]);
        }

    }
}
