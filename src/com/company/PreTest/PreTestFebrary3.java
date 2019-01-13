package com.company.PreTest;

import java.io.*;
import java.util.*;

public class PreTestFebrary3 {
    static int cardCount;
    static long targetNum;
    static long result;
    static long[] cardDec;
    static List<Integer> leftList;
    static List<Integer> rightList;

    public static void main(String args[]) throws IOException {

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

            int left = cardCount / 2;
            int right = cardCount - left;

            leftList = new ArrayList<>();
            rightList = new ArrayList<>();

            for (int k = 0; k < (1 << left); k++) {
                int sum = 0;
                for (int j = 0; j < left; j++) {
                    if ((k & (1 << j)) > 0) {
                        sum += cardDec[j];
                    }
                }
                leftList.add(sum);
            }

            for (int k = 0; k < (1 << right); k++) {
                int sum = 0;
                for (int j = 0; j < right; j++) {
                    if ((k & (1 << j)) > 0) {
                        sum += cardDec[j + left];
                    }
                }
                rightList.add(sum);
            }

            Collections.sort(leftList);
            Collections.sort(rightList);

            int L = 0;
            int R = rightList.size() - 1;
            int LSize = leftList.size() - 1;
            long ans = 0;

            while (L <= LSize && R >= 0) {
                int leftValue = leftList.get(L);
                int rightValue = rightList.get(R);
                long sum = leftValue + rightValue;
                if (sum < targetNum) {
                    L++;
                } else if (sum > targetNum) {
                    R--;
                } else {
                    long leftCheck = 0;
                    long rightCheck = 0;

                    while ((L <= LSize) && (leftValue == leftList.get(L))) {
                        L++;
                        leftCheck++;
                    }

                    while ((R >= 0) && (rightValue == rightList.get(R))) {
                        R--;
                        rightCheck++;
                    }
                    ans += leftCheck * rightCheck;
                }
            }
            if (targetNum == 0) {
                ans--;
            }
            System.out.println(ans);
        }
    }
}
