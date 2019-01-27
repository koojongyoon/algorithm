package com.company.PreTest;

import java.io.IOException;
import java.util.Scanner;

public class PreTestFebrary2 {
    static int cardCount;
    static long targetNum;
    static long[] cardDec;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int result = sc.nextInt();
        int[] arr = new int[n];
        int bitMax = (int) (Math.pow(2, n) - 1);
        int cnt = 0;

        for (int i = 0; i < n; i++) { // Initialization
            arr[i] = sc.nextInt();
        }

        for (int bit = 1; bit <= bitMax; bit++) { // 모든 비트 순회
            int sum = 0;

            for (int i = 0; i < n; i++) { // 1인 비트 체크
                if ((bit & (1 << i)) != 0)
                    sum += arr[i];
            }

            if (sum == result)
                cnt++;
        }

        System.out.println(cnt);
    }
}
