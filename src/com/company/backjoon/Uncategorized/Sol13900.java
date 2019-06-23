package com.company.backjoon.Uncategorized;

import java.io.*;
import java.util.Scanner;

public class Sol13900 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long sum = 0;
        long remainSum = 0;
        int inputCount = sc.nextInt();
        int[] number = new int[inputCount];

        for (int i = 0; i < inputCount; i++) {
            number[i] = sc.nextInt();
            sum = sum + number[i];
        }

        for (int i = 0; i < inputCount; i++) {
            sum = sum - number[i];
            remainSum = remainSum + (sum * number[i]);
        }

        bw.write(remainSum + "");
        bw.flush();
        bw.close();
    }
}
