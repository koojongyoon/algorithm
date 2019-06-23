package com.company.backjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://blog.naver.com/PostView.nhn?blogId=occidere&logNo=221426880947
public class Sol5719 {

    static int INF = 100000001;
    static int[] dest;
    static int[][] map;
    static List<Integer>[] trace;
    static ArrayList<Edge>[] list;
    static int[] byArr;
    static int N;
    static int M;
    static int start;
    static int end;

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
            N = Integer.parseInt(NM[0]);
            M = Integer.parseInt(NM[1]);

            list = new ArrayList[N];    //각 시작점에서 갈수 있는 목적지의 edge종
            dest = new int[N]; //N까지의 거리
            byArr = new int[N];
            map = new int[N][N];
            trace = new ArrayList[N];

            for (int i = 0; i < N; i++) {
                list[i] = new ArrayList<>();
                dest[i] = INF;
                Arrays.fill(map[i], -1);
                trace[i] = new ArrayList<>();
            }

            String[] startEnd = br.readLine().split(" ");
            start = Integer.parseInt(startEnd[0]);
            end = Integer.parseInt(startEnd[1]);

            dest[start] = 0;

            for (int i = 0; i < M; i++) {
                String[] UVP = br.readLine().split(" ");
                int u = Integer.parseInt(UVP[0]);
                int v = Integer.parseInt(UVP[1]);
                int p = Integer.parseInt(UVP[2]);
                map[u][v] = p;
                list[u].add(new Edge(v, p));
            }

            dijkstra(start);
            traceback();
            Arrays.fill(dest, INF);
            dijkstra(start);

            System.out.println(dest[end] >= INF ? -1 : dest[end]);
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(start, 0));

        while(!queue.isEmpty()) {
            Edge e = queue.poll();
            for (int next = 0; next < N; next++) {
                if(map[e.next][next] == -1 && e.weight + map[e.next][next] <= dest[next]) {
                    queue.add(new Edge(next, dest[next] = e.weight + map[e.next][next]));
                    trace[next].add(e.next);
                }
            }
        }
    }

    private static void traceback() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(end);
        while(!q.isEmpty()) {
            int cur = q.poll();
            for (Integer pre : trace[cur]) {
                if (map[pre][cur] != -1 && dest[cur] == map[pre][cur] + dest[pre]) {
                    q.offer(pre);
                    map[pre][cur] = -1;
                }
            }
        }
    }
}
