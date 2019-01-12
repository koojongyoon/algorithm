package com.company.PreTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PreTestFebrary {
    static int cardCount;
    static long targetNum;
    static long result;
    static long[] cardDec;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) {
            String[] cardMethod = br.readLine().split(" ");
            cardCount = Integer.parseInt(cardMethod[0]);
            targetNum = Long.parseLong(cardMethod[1]);
            result = 0;

            cardDec = new long[cardCount];
            String[] cardStr = br.readLine().split(" ");

            for (int k = 0; k < cardCount; k++) {
                cardDec[k] = Long.parseLong(cardStr[k]);
            }

            Arrays.sort(cardDec);
            System.out.println(System.currentTimeMillis());
            bruteForce(0, 0);
            System.out.println(result);
        }
    }

    static void bruteForce(int index, long total){
        if (index == cardCount) {
            return;
        }

        if (total + cardDec[index] > targetNum) {
            if (cardDec[index] >= 0) {
                return;
            }
        }
        if ((total + cardDec[index]) == targetNum ) {
            result++;
        }

        bruteForce(index+1, total + cardDec[index]);

        bruteForce(index+1, total);
    }
}
