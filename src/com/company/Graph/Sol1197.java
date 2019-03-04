package com.company.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;



public class Sol1197 {

    private static ArrayList<Edge> edgeList;
    private static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();

        edgeList = new ArrayList<Edge>();

        for (int i = 0; i < e; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int cost = sc.nextInt();
            edgeList.add(new Edge(v1, v2, cost));
        }

        parent = new int[v+1];

        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        Collections.sort(edgeList);

        int sum = 0;
        for (int i = 0; i < edgeList.size(); i++) {
            Edge edge = edgeList.get(i);
            if (!isSameParent(edge.v1, edge.v2)) {
                sum = sum + edge.cost;
                union(edge.v1, edge.v2);
            }
        }

        System.out.println(sum);
    }

    private static void union(int v1, int v2) {
        v1 = find(v1);
        v2 = find(v2);
        if (v1 != v2) {
            parent[v2] = v1;
        }
    }

    private static boolean isSameParent(int v1, int v2) {
        v1 = find(v1);
        v2 = find(v2);
        if (v1 == v2) {
            return true;
        } else {
            return false;
        }
    }

    private static int find (int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static class Edge implements Comparable<Edge>{

        int v1;
        int v2;
        int cost;

        Edge(int v1, int v2, int cost) {
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.cost < o.cost) {
                return -1;
            } else if (this.cost == o.cost) {
                return 0;
            } else
                return 1;
        }
    }
}
