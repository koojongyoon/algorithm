package com.company.backjoon.BruteForce;

import java.util.Arrays;
import java.util.Scanner;

public class Sol2309 {

    static int[] dwarf;
    static int HEIGHT = 100;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        dwarf = new int[9];

        int sum = 0;

        for (int i = 0; i < 9; i++) {
            dwarf[i] = sc.nextInt();
            sum = sum + dwarf[i];
        }

        checkedDwarf(sum);

        Arrays.sort(dwarf);

        for (int i = 2; i < 9; i++) {
            System.out.println(dwarf[i]);
        }
    }

    private static void checkedDwarf(int sum) {
        for (int i = 0; i < dwarf.length; i++) {
            for (int j = i+1; j < dwarf.length; j++) {
                if (sum - (dwarf[i] + dwarf[j]) == HEIGHT) {
                    dwarf[i] = -1;
                    dwarf[j] = -1;
                    return;
                }
            }
        }
    }
}
