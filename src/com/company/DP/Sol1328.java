package com.company.DP;

import java.util.Scanner;

public class Sol1328 {

    static long[][][] buildings;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int L = sc.nextInt();
        int R = sc.nextInt();

        buildings = new long[N+1][N+1][N+1];
        buildings[1][1][1] = 1L;

        for (int n = 2; n <= N; n++) {
           for (int l = 1; l <= L; l++) {
               for (int r = 1; r <= R; r++) {
                   buildings[n][l][r] = buildings[n-1][l-1][r] + buildings[n-1][l][r-1] +  (buildings[n-1][l][r] * (n-2));
                   buildings[n][l][r] = buildings[n][l][r]%1000000007L;
               }
           }
        }

        System.out.println(buildings[N][L][R]);
    }
}
