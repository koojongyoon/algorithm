package com.company.Uncategorized;

import java.util.Scanner;

public class Sol2098 {

    static int v;
    static int[][] adj;
    static int[][] cache;
    static int end = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        v = sc.nextInt();
        adj = new int[v +1][v +1];
        cache = new int[v +1][1 << (v +1)];

        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                adj[i][j] = sc.nextInt();
                if (adj[i][j] == 0) {
                    adj[i][j] = 987654321;
                }
            }
        }

        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[i].length; j++) {
                cache[i][j] = -1;
            }
        }

        System.out.println(shortestPath(1,1));
    }

    private static int shortestPath(int start, int visited) {
        int result = 987654321;

        if (visited == ((1 << v)-1)) {
            return adj[start][end];
        }

        if (cache[start][visited] != -1) {
            return cache[start][visited];
        }

        for (int next = 1; next <= v; next++) {
            if ((visited & (1 << (next - 1))) >= 1) {
                continue;
            }
            result = Math.min(result, adj[start][next] + shortestPath(next, visited + (1 << (next -1))));
        }

        return cache[start][visited] = result;
    }
}
