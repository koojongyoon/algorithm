package com.company.BellmanFords;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol11657 {

    static Edge[] edges;
    static int[] dist;
    static int MAX_TIME = 10000000;
    static int city;
    static int bus;

    static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] cityBus = br.readLine().split(" ");

        city = Integer.parseInt(cityBus[0]);   // 1 <= city <= 500
        bus = Integer.parseInt(cityBus[1]);    // 1 <= bus = 6000

        edges = new Edge[bus];
        dist = new int[city+1];

        for (int i = 0; i < city+1; i++) {
            dist[i] = MAX_TIME;
        }

        dist[1] = 0;

        for (int i = 0; i < bus; i++) {
            String[] startEndTime = br.readLine().split(" ");
            int start = Integer.parseInt(startEndTime[0]);
            int end = Integer.parseInt(startEndTime[1]);
            int time = Integer.parseInt(startEndTime[2]);

            edges[i] = new Edge(start, end, time);
        }

        if (bellmanFord(edges, dist)) {
            for (int i = 2; i < city + 1; i++) {
                System.out.println(dist[i] == MAX_TIME ? -1 : dist[i]);
            }
        } else {
            System.out.println("-1");
        }

    }

    private static boolean bellmanFord(Edge[] edges, int[] dist) {
        for (int i = 0; i < city-1; i++) {
            for (int k = 0; k < bus; k++) {
                if (dist[edges[k].to] > dist[edges[k].from] + edges[k].weight) {
                    dist[edges[k].to] = dist[edges[k].from] + edges[k].weight;
                }
            }
        }

        for (int i = 0; i < bus; i++) {
            if (dist[edges[i].to] > dist[edges[i].from] + edges[i].weight) {
                return false;
            }
        }

        return true;
    }

}
