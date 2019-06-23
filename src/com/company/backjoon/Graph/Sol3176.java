package com.company.backjoon.Graph;

import java.util.Scanner;
import java.util.Stack;

public class Sol3176 {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static Stack<Integer> nextRoute;
    static int MAX;
    static int MIN;
    static int TO;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N+1][N+1];

        for (int i = 0; i < N-1; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int value = sc.nextInt();
            map[from][to] = value;
            map[to][from] = value;
        }

        int outputCount = sc.nextInt();

        for (int i = 0; i < outputCount; i++) {
            int from = sc.nextInt();
            TO = sc.nextInt();
            MAX = -1;
            MIN = Integer.MAX_VALUE;
            nextRoute = new Stack<>();
            visited = new boolean[N+1][N+1];

            dfs(from);
            if (TO == from) {
                System.out.println(MIN + " " + MAX);
            }

        }
    }

    private static int dfs(int from) {

        for (int k = 1; k <= N; k++) {
            int availableNextRoute = map[from][k];
            if (availableNextRoute > 0 && !visited[from][k]) {
                nextRoute.push(k);
            }
        }
        int next = nextRoute.pop();

        visited[from][next] = true;

        int value = map[from][next];

        if(value < MIN) {
            MIN = value;
        }
        if(value > MAX) {
            MAX = value;
        }

        return dfs(next);
    }
}
