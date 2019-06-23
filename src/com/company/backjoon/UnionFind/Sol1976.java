package com.company.backjoon.UnionFind;

import java.util.Scanner;

public class Sol1976 {

    static int[] cityMap;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] city = new int[n+1][n+1];

        int m = sc.nextInt();

        for (int i = 1; i < n+1; i++) {
            for (int k = 1; k < n+1; k++) {
                city[i][k] = sc.nextInt();
            }
        }

        cityMap = new int[n+1];

        for (int i = 1; i <= n; i++) {
            cityMap[i] = i;
        }

        makeParent(city);


        int[] schedule = new int[m+1];

        for (int i = 1; i <= m; i++) {
            schedule[i] = sc.nextInt();
        }

        boolean isOk = true;

        for (int i = 2; i <= m; i++) {
            if (find(schedule[i-1]) != find(schedule[i])) {
                isOk = false;
                break;
            }
        }

        if (isOk) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static void makeParent(int[][] city) {
        for (int i = 1; i < city.length; i++) {
            for (int k = 1; k < city.length; k++) {
                if (city[i][k] == 1) {
                    union(i, k); 
                }
            }
        }
    }

    private static void union(int i, int k) {
        i = find(i);
        k = find(k);
        if(i != k) {
            cityMap[i] = k;
        }
    }

    private static int find(int x) {
        if (cityMap[x] != x) {
            return cityMap[x] = find(cityMap[x]);
        } else {
            return x;
        }
    }
}
