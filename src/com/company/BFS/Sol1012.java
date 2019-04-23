package com.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Sol1012 {

    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int M;
    static int N;
    static int warm;
    static LinkedList<Point> queues;

    static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String[] colRowFlower = br.readLine().split(" ");
            M = Integer.parseInt(colRowFlower[0]);
            N = Integer.parseInt(colRowFlower[1]);
            int flower = Integer.parseInt(colRowFlower[2]);
            warm = 0;

            map = new int[N][M];
            visited = new boolean[N][M];
            queues = new LinkedList<>();

            // 모두 방문 했던것으로 초기화한다.
            for (int k = 0; k < N; k++) {
                Arrays.fill(visited[k], true);
            }

            // 입력값에 대한 배추값 셋팅
            for (int k = 0; k < flower; k++) {
                String[] rowCol = br.readLine().split(" ");
                int row = Integer.parseInt(rowCol[1]);
                int col = Integer.parseInt(rowCol[0]);
                map[row][col] = 1;
                visited[row][col] = false;
            }

            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (map[n][m] == 1 && !visited[n][m]) {
                        bfs(n, m);
                        warm++;
                    }
                }
            }

            System.out.println(warm);
        }
    }

    private static void bfs(int n, int m) {
        queues.add(new Point(n, m));
        while (!queues.isEmpty()) {
            Point point = queues.poll();
            for (int i = 0; i < 4; i++) {
                int x = point.x + dx[i];
                int y = point.y + dy[i];
                if (y >= 0 && y < M && x >= 0 && x < N && !visited[x][y] && map[x][y] == 1) {
                    visited[x][y] = true;
                    map[x][y] = 0;
                    queues.add(new Point(x, y));
                }
            }
        }
    }
}
