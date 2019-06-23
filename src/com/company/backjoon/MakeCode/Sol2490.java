package com.company.backjoon.MakeCode;

import java.util.Scanner;

public class Sol2490 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int k = 0; k < 3; k++) {
            int[] YUT = new int[4];
            int cnt = 0;
            for (int i = 0; i < 4; i++) {
                YUT[i] = sc.nextInt();
                if (YUT[i] == 1) {
                    cnt = cnt + 1;
                }
            }
            switch (cnt) {
                case 0:
                    System.out.println("D");
                    break;
                case 1:
                    System.out.println("C");
                    break;
                case 2:
                    System.out.println("B");
                    break;
                case 3:
                    System.out.println("A");
                    break;
                case 4:
                    System.out.println("E");
                    break;
            }
        }
    }
}
