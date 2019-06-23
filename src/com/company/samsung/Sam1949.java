package com.company.samsung;

import java.io.*;

public class Sam1949 {

    static Point[][] map;
    static boolean[][] visited;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, -1, 0, 1 };
    static int K;
    static int N;

    static class Point {
        int x;
        int y;
        int high;
        int length;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            initializeMap(br);
            int startPoint = findHighest();
            startHighestPoint(startPoint);
            printLength();
        }
    }

    private static void printLength() {
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < N; m++) {
                System.out.print(map[n][m].length + " ");
            }
            System.out.println();
        }
    }

    private static void startHighestPoint(int startPoint) {
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < N; m++) {
                if (map[n][m].high == startPoint) {
                    int longestLength = 1;
                    initializeVisited();
                    visited[n][m] = true;
                    DFS(n, m, longestLength);
                }
            }
        }
    }

    private static void DFS(int x, int y, int longestLength) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            if (x + dx[i] >= 0 && y + dy[i] >= 0 && x + dx[i] < N && y + dy[i] < N && !visited[x + dx[i]][y + dy[i]]) {
                if (map[x][y].high > map[x + dx[i]][y + dy[i]].high) {
                    if (map[x + dx[i]][y + dy[i]].length < longestLength) {
                        map[x + dx[i]][y + dy[i]].length = longestLength;
                    }
                    DFS(x + dx[i], y + dy[i], longestLength + 1);
                }
            }
        }
    }

    private static void initializeVisited() {
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < N; m++) {
                visited[n][m] = false;
            }
        }
    }

    private static Point[][] initializeMap(BufferedReader br) throws IOException {
        String[] NK = br.readLine().split(" ");
        N = Integer.parseInt(NK[0]);
        K = Integer.parseInt(NK[1]);
        map = new Point[N][N];
        visited = new boolean[N][N];

        for (int n = 0; n < N; n++) {
            String[] rowStr = br.readLine().split(" ");
            for (int m = 0; m < N; m++) {
                map[n][m] = new Point(n, m);
                map[n][m].high = Integer.parseInt(rowStr[m]);
            }
        }
        return map;
    }

    private static int findHighest() {
        int MAX = 0;
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < N; m++) {
                if (map[n][m].high > MAX) {
                    MAX = map[n][m].high;
                }
            }
        }
        return MAX;
    }
}
