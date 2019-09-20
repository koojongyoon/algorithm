package com.company.backjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class PreTest2 {

    static class Edge {
        int destination;
        int weight;
        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    static ArrayList[] edges;
    static ArrayList<Integer> weights;
    static ArrayList<Integer> sortedWeight;
    static int[] visitedEdge;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            String[] NM = br.readLine().split(" ");
            N = Integer.parseInt(NM[0]);   // 1<=n<=1000
            M = Integer.parseInt(NM[1]);   // 1<=m<=4000

            edges = new ArrayList[N+2];

            for (int i = 0; i <= N; i++) {
                edges[i] = new ArrayList<Edge>();
            }

            weights = new ArrayList<>();
            sortedWeight = new ArrayList<>();

            for (int m = 0; m < M; m++) {
                String[] ABS = br.readLine().split(" ");
                int A = Integer.parseInt(ABS[0]);
                int B = Integer.parseInt(ABS[1]);
                int S = Integer.parseInt(ABS[2]);

                edges[A].add(new Edge(B, S));
                edges[B].add(new Edge(A, S));
                weights.add(S);
            }
            sortedWeight = modifyDuplicate(weights);
            String[] startEnd = br.readLine().split(" ");

            int start = Integer.parseInt(startEnd[0]);
            int end = Integer.parseInt(startEnd[1]);

            int minDifference = 1000000001;
            int foundPathUsing = 0;
            for (int low = 0; low < weights.size(); low++) {
                boolean foundPath = false;
                for (int high = foundPathUsing; high < weights.size(); high++) {
                    if (hasPath(weights.get(low), weights.get(high))) {
                        minDifference = Math.min(minDifference, weights.get(high) - weights.get(low));
                        foundPath = true;
                        foundPathUsing = high;
                        break;
                    }
                }
                if (!foundPath) {
                    break;
                }
            }
            System.out.println(minDifference);
        }
    }

    private static ArrayList modifyDuplicate(ArrayList list) {
        Set<Integer> weightSet = new LinkedHashSet<>();
        weightSet.addAll(list);
        list.clear();
        list.addAll(weightSet);
        Collections.sort(list);
        return list;
    }

    private static boolean hasPath(int low, int high) {
        visitedEdge = new int[N+1];
        return dfs(0, low, high);
    }

    private static boolean dfs(int start, int low, int high) {
        if (start == M-1) {
            return true;
        }
        visitedEdge[start] = 1;
        for (int i = 0; i < edges[start].size(); i++) {
            int destination = ((Edge) edges[start].get(i)).destination;
            int weight = ((Edge) edges[start].get(i)).weight;
            if (weight < low || weight > high || visitedEdge[destination] != 0) {
                continue;
            }
            if (dfs(destination, low, high)) {
                return true;
            }
        }
        return false;
    }
}
