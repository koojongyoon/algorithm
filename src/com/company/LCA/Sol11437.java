package com.company.LCA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sol11437 {

    private static int[] parent;
    private static int[] depth;
    private static ArrayList<Integer>[] con;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int nodeCount = Integer.parseInt(br.readLine());

        parent = new int[nodeCount+1];

        for (int i = 1; i <= nodeCount; i++) {
            parent[i] = -1;
        }

        depth = new int[nodeCount+1];
        con = new ArrayList[nodeCount+1];

        for (int i = 1; i <= nodeCount; i++) {
            con[i] = new ArrayList<Integer>();
        }

        for (int i = 1; i < nodeCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            con[a].add(b);
            con[b].add(a);
        }

        dfs(1, 1, 0);

        int queryCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < queryCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int aDepth = depth[a];
            int bDepth = depth[b];

            //depth의 깊이가 같을때까지 a배열의 깊이를 올리며 맞춘다
            while (aDepth > bDepth) {
                a = parent[a];
                aDepth--;
            }

            //depth의 깊이가 같을때까지 b배열의 깊이를 올리며 맞춘다.
            while (aDepth < bDepth) {
                b = parent[b];
                bDepth--;
            }

            //a와 b의 공통 조상이 같지 않ㅇ면 그 위의 조상을 공통 조상이라고 판단한다.
            while (a != b) {
                a = parent[a];
                b = parent[b];
            }

            System.out.println(a);
        }
    }

    private static void dfs(int currentNode, int d, int p) {
        depth[currentNode] = d;
        parent[currentNode] = p;
        //내 자손들의 노드들의 깊이와 부모 배열을 채워준다
        for (int next : con[currentNode]) {
            if (next != p) {
                dfs(next, d+1, currentNode);
            }
        }
    }
}
