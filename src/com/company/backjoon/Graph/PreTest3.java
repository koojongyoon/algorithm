package com.company.backjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class PreTest3 {

    static ArrayList weightList;
    static int[] parent;
    static int[][] adj;

    static class Edge implements Comparable<Edge>{

        int index;
        int weight;

        Edge(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            if (this.weight > edge.weight) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <=T; t++) {
            String[] VE = br.readLine().split(" ");
            int V = Integer.parseInt(VE[0]);
            int E = Integer.parseInt(VE[1]);

            adj = new int[E+1][2];
            weightList = new ArrayList();
            parent = new int[V+1];
            weightList.add(new Edge(0, Integer.MAX_VALUE));
            for (int e = 1; e <= E; e++) {
                String[] ABS = br.readLine().split(" ");
                int A = Integer.parseInt(ABS[0]);
                int B = Integer.parseInt(ABS[1]);
                int S = Integer.parseInt(ABS[2]);
                adj[e][0] = A;
                adj[e][1] = B;
                weightList.add(new Edge(e, S));
                Collections.sort(weightList);
            }

            for (int i = 0; i <= V; i++) {
                parent[i] = i;
            }

            String[] startEnd = br.readLine().split(" ");
            int start = Integer.parseInt(startEnd[0]);
            int end = Integer.parseInt(startEnd[1]);

            int result = kruskal(start, end);
            System.out.println("#" + t + " " + result);
        }
    }

    private static int kruskal(int start, int end) {
        int result = Integer.MAX_VALUE;
        for (int i = 1; i < weightList.size(); i++) {
            result = Math.min(result, findMinWeight(start, end, i) - ((Edge) weightList.get(i)).weight);
        }
        return result;
    }

    private static int findMinWeight(int start, int end, int index) {
        Edge edge = (Edge) weightList.get(index);
        int startEdge = adj[edge.index][0];
        int endEdge = adj[edge.index][1];
        for (int i = 0; i < weightList.size(); i++) {
            Edge compareEdge = (Edge) weightList.get(i);
            if (compareEdge.weight < edge.weight) {
                continue;
            }
            union(startEdge, endEdge);
            if (find(start) == find(end)) {
                return edge.weight;
            }
        }
        return Integer.MAX_VALUE;
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return;
        } else {
            parent[y] = x;
        }
    }
}
