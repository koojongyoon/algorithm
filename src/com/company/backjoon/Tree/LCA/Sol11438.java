package com.company.backjoon.Tree.LCA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol11438 {

    static int K = 0;
    static int[][] parent;
    static int[] depth;
    static ArrayList[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = N; i >= 1; i = i/2, K++);

        depth = new int[N+1];
        parent = new int[K+1][N+1];
        adjList = new ArrayList[N+1];

        for (int i = 0; i <= N; i++) {
            adjList[i] = new ArrayList();
            depth[i] = -1;
        }

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);
        }

        bfs(1);

        for (int i = 1; i <=K; i++) {
            for (int j = 1; j <= N; j++) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }

        int M = Integer.parseInt(br.readLine());

        for (int i = 1; i <= M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int result = lca(a, b);
            System.out.println(result);
        }
    }

    static int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int diff = depth[a] - depth[b];
        int k = 0;

        while (diff >= 1) {
            if (diff%2 == 1) {
                a = parent[k][a];
            }
            diff = diff / 2;
            k++;
        }

        if (a == b) {
            return a;
        }

        for (k = K-1; k > -1; k--) {
            if (parent[k][a] != parent[k][b]) {
                a = parent[k][a];
                b = parent[k][b];
            }
        }

        return parent[0][a];
    }

    private static void bfs(int root) {
        Queue queue = new LinkedList();
        queue.add(root);
        parent[0][root] = 0;
        while (!queue.isEmpty()) {
            int cur = (int) queue.poll();
            for (Object n : adjList[cur]) {
                int next = (int) n;
                if (next == parent[0][cur]) {
                    continue;
                }

                depth[next] = depth[cur] + 1;
                parent[0][next] = cur;
                queue.add(next);
            }
        }
    }
}
