package com.company.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Sol5719 {

    static int INF = 100000001;
    static int[] dest;
    static int[] by;
    static ArrayList<Edge>[] list;
    static boolean[] visit;

    static class Edge implements Comparable<Edge>{
        int next;
        int weight;
        Edge(int next, int weight) {
            this.next = next;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NM;

        while ((NM = br.readLine().split(" ")).length > 0) {
            int N = Integer.parseInt(NM[0]);
            int M = Integer.parseInt(NM[1]);

            list = new ArrayList[N];    //각 시작점에서 갈수 있는 목적지의 edge종
            dest = new int[N]; //N까지의 거리
            by = new int[N];
            visit = new boolean[N];   // 방문여부 표시

            for (int i = 0; i < N; i++) {
                list[i] = new ArrayList<>();
                dest[i] = INF;
            }

            String[] startEnd = br.readLine().split(" ");
            int start = Integer.parseInt(startEnd[0]);
            int end = Integer.parseInt(startEnd[1]);

            dest[start] = 0;

            for (int i = 0; i < M; i++) {
                String[] UVP = br.readLine().split(" ");
                int u = Integer.parseInt(UVP[0]);
                int v = Integer.parseInt(UVP[1]);
                int p = Integer.parseInt(UVP[2]);

                list[u].add(new Edge(v, p));
            }

            PriorityQueue<Edge> queue = new PriorityQueue<>();
            queue.add(new Edge(start, 0));

            while(!queue.isEmpty()) {
                Edge e = queue.poll();
                if (visit[e.next]) {
                    continue;
                }
                visit[e.next] = true;

                for (Edge edge : list[e.next]) {
                    if (!visit[edge.next]) {
                        dest[edge.next] = Math.min(dest[edge.next], dest[e.next] + edge.weight);
                        queue.add(new Edge(edge.next, dest[edge.next]));
                    }
                }
            }
        }
    }
}
