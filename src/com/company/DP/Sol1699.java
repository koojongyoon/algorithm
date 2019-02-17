package com.company.DP;

import java.io.IOException;
import java.util.Scanner;

public class Sol1699 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int inputNum = sc.nextInt();
        int[] mem = new int[inputNum + 1];

        for (int i = 0; i < mem.length; i++) {
            mem[i] = i;
        }

        for (int i = 2; i <= inputNum; i++) {
            for (int j = 2; j*j <= i; j++) {
                mem[i] = Math.min(mem[i], mem[i - j*j]+1);
            }
        }

        System.out.println(mem[inputNum]);
        sc.close();
    }
}
