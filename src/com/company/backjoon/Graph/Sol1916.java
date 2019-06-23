package com.company.backjoon.Graph;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

// https://jaesungbong.tistory.com/26
public class Sol1916 {

    private static final int INF = 1000001;
    static int V;
    static int E;
    static ArrayList<Edge>[] list;
    static int dist[];
    static boolean[] visit;

    static class Edge implements Comparable<Edge> {
        int dest;   // 간선의 목적지
        int weight;     //간선의 가중치

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();   // 정점의 갯수
        E = sc.nextInt();   // 간선의 갯수

        dist = new int[V+1];    // 시작 정점으로부터 목적정점까지의 최소거리
        visit = new boolean[V+1];   // 방문 여부를 표시하는 배열

        // 각 정점의 연결된 간선을 저장
        list = new ArrayList[V+1];

        for (int i = 1; i <= V; i++) {
            dist[i] = INF;  // 모든 정점은 일단 무한인 상태로 초기화한다.
            list[i] = new ArrayList<Edge>();
        }


        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();   //간선의 시작
            int v = sc.nextInt();   //간선의 끝
            int w = sc.nextInt();   //가중치

            list[u].add(new Edge(v, w));
        }

        int start = sc.nextInt();
        int end = sc.nextInt();

        dist[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(); //우선순위큐 생성
        pq.offer(new Edge(start, 0));   //시작 정점을 우선순위 큐에 넣음

        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            if (visit[e.dest] == true) {
                continue;
            }
            visit[e.dest] = true;

            for (Edge k : list[e.dest]) {
                if (visit[k.dest] == false) {
                    dist[k.dest] = Math.min(dist[k.dest], dist[e.dest] + k.weight);
                    pq.offer(new Edge(k.dest, dist[k.dest]));
                }
            }
        }

        System.out.println(dist[end] == INF ? 0 : dist[end]);

        sc.close();
    }
}
