package com.company.Permutation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol10974 {

    static int[] output;
    static boolean[] visited;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        output = new int[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            visited[i] = true;
            DFS(i, 0);
            visited[i] = false;
        }
    }

    private static void DFS(int start, int depth) {
        output[depth] = start + 1;
        if (depth == N-1) {
            for (int i = 0; i < N; i++) {
                System.out.print(output[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            DFS(i, depth+1);
            visited[i] = false;
        }
    }
}
