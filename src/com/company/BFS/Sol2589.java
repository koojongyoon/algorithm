package com.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Sol2589 {

    static char[][] map;
    static int[][] distMap;
    static boolean[][] visited;
    static LinkedList<Point> queue;
    static int ROW;
    static int COL;
    static int[] dRow = {-1, 0, 1, 0};
    static int[] dCol = {0, -1, 0, 1};
    static int MAX = 0;

    static class Point {
        int row;
        int col;

        public Point (int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] rowCol = br.readLine().split(" ");
        ROW = Integer.parseInt(rowCol[0]);
        COL = Integer.parseInt(rowCol[1]);

        map = new char[ROW][COL];
        // 입력은 가로 세로 최대 50까지
        for (int i = 0; i < ROW; i++) {
            String rowStr = br.readLine();
            map[i] = rowStr.toCharArray();
        }

        for (int i = 0; i < ROW; i++) {
            for (int k = 0; k < COL; k++) {

                queue = new LinkedList<>();
                visited = new boolean[ROW][COL];
                distMap = new int[ROW][COL];
                if (map[i][k] == 'L') {
                    BFS(i, k);
                }
            }
        }

        System.out.println(MAX);
    }

    private static void BFS(int row, int col) {
        Point point = new Point(row, col);
        queue.add(point);
        int distance = 0;
        distMap[row][col] = distance;
        visited[row][col] = true;

        while(!queue.isEmpty()) {
            Point pollPoint = queue.poll();
            int xRow = pollPoint.row;
            int yCol = pollPoint.col;

            for (int i = 0; i < 4; i++) {
                int nx = dRow[i] + xRow;
                int ny = dCol[i] + yCol;
                if (nx >= 0 && nx < ROW && ny >= 0 && ny < COL && !visited[nx][ny] && map[nx][ny] == 'L') {
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny));
                    distMap[nx][ny] = distMap[xRow][yCol] + 1;
                }
            }
        }

        for (int i = 0; i < ROW; i++) {
            for (int k = 0; k < COL; k++) {
                if ( MAX < distMap[i][k]) {
                    MAX = distMap[i][k];
                }
            }
        }
    }
}
