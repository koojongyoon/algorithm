package com.company.LCS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol9252 {
    static int[][] lcs;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String firstSequence = "0" + str1;

        String str2 = br.readLine();
        String secondSequence = "0" + str2;

        int firstLength = firstSequence.length();
        int secondLength = secondSequence.length();

        char[] first = firstSequence.toCharArray();
        char[] second = secondSequence.toCharArray();

        lcs = new int[firstLength][secondLength];

        for (int i = 0; i < firstLength; i++) {
            for (int k = 0; k < secondLength; k++) {
                // 제일 첫 부분은 (0,0)으로 채운다.
                if (i == 0 || k == 0) {
                    lcs[i][k] = 0;
                    continue;
                }

                // 현재 비교하는 값이 서로 같으면 lcs는 왼쪽 대각선이 있는 값에서+1 시켜준다.
                if (first[i] == second[k]) {
                    lcs[i][k] = lcs[i-1][k-1] + 1;
                } else {
                    // 현재 비교하는 값이 서로 다르다면 lcs의 값을 왼쪽 혹은 위의 값 중에서 큰 값을 가져온다
                    if (lcs[i-1][k] > lcs[i][k-1]) {
                        lcs[i][k] = lcs[i-1][k];
                    } else {
                        lcs[i][k] = lcs[i][k-1];
                    }
                }

            }
        }

        System.out.println(lcs[firstLength-1][secondLength-1]);
    }
}
