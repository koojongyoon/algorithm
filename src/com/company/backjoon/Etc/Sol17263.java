package com.company.backjoon.Etc;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Sol17263 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] numArr = new int[N];
        for (int i = 0; i < N; i++) {
            numArr[i] =sc.nextInt();
        }
        Arrays.sort(numArr);
        System.out.println(numArr[N-1]);
    }
}
