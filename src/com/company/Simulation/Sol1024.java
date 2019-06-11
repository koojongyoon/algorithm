package com.company.Simulation;

import java.util.Scanner;

public class Sol1024 {

    static int N;
    static int L;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();

        boolean flag = true;
        for (int i = L; i <= 100 && flag; i++) {
            if((2 * N >= i * (i - 1)) && (2 * N - i * (i - 1)) % (2 * i) == 0) {
                int a = ((2 * N - i * (i - 1)) / (2 * i));
                for (int j = 0; j < i; j++) {
                    System.out.print((a+j) + " ");
                }
                System.out.println();
                flag = false;
            }
        }

        if(flag){
            System.out.println(-1);
        }
    }
}
