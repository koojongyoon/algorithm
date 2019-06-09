package com.company.Simulation;

import java.util.Scanner;

public class Sol1094 {
    static int[] arr;
    static int count;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int stick = sc.nextInt();

        arr = new int[8];
        arr[0] = 0;
        arr[1] = 1;
        count = 0;

        for (int i = 2, k = 2; k <= 64; k = k*2, i++) {
            arr[i] = k;
        }

        for (int i = 7; i > 0; i--) {
            if (stick >= arr[i]) {
                stick = stick - arr[i];
                count++;
            }
            if (stick == 0) {
                break;
            }
        }
        System.out.println(count);
    }
}
