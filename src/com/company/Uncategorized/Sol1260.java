package com.company.Uncategorized;

import java.io.IOException;
import java.util.*;

public class Sol1260 {

    static int[][] adjustArray;
    static boolean[] visited;
    static List<Integer> dfsResult;

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        N =  sc.nextInt();
        M =  sc.nextInt();
        int startPoint = sc.nextInt();

        adjustArray = new int[N+1][N+1];
        dfsResult = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            adjustArray[x][y] = 1;
            adjustArray[y][x] = 1;
        }

        visited = new boolean[N+1];
        dfs(startPoint);
        printDFS();

        visited = new boolean[N+1];
        bfs(startPoint);
    }

    private static void printDFS() {
        for (int i = 0; i < N; i++) {
            System.out.print(dfsResult.get(i)+ " ");
        }
        System.out.println();
    }


    private static void dfs(int startPoint) {
        visited[startPoint] = true;
        dfsResult.add(startPoint);
        for (int i = 1; i <= N; i++) {
            if (adjustArray[startPoint][i] == 1 && visited[i] == false) {
                dfs(i);
            }
        }
    }

    private static void bfs(int startPoint) {
        Queue<Integer> queue = new <Integer> LinkedList();
        queue.offer(startPoint);

        while(!queue.isEmpty()) {
            int index = queue.poll();
            visited[index] = true;
            System.out.print(index+ " ");

            for(int i = 1; i <= N; i++) {
                if(adjustArray[index][i] == 1 && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }

    }
}
