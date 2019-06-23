package com.company.backjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Sol7576 {

    static int row;
    static int col;
    static int count;
    static Tomato[][] map;
    static LinkedList<Tomato> tomatoQueue;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Tomato {
        int x;
        int y;
        int value;
        boolean visited;

        public Tomato (int x, int y, int value, boolean visited) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.visited = visited;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] colRow = br.readLine().split(" ");
        col = Integer.parseInt(colRow[0]);
        row = Integer.parseInt(colRow[1]);
        map = new Tomato[row][col];
        tomatoQueue = new LinkedList<>();

        for (int i = 0; i < row; i++) {
            String[] inputTomato = br.readLine().split(" ");
            for (int k = 0; k < col; k++) {
                int tomato = Integer.parseInt(inputTomato[k]);
                if (tomato == -1) {
                    map[i][k] = new Tomato(i, k, tomato, true);
                } else if (tomato == 1){
                    map[i][k] = new Tomato(i, k, tomato, true);
                    tomatoQueue.add(map[i][k]);
                } else if (tomato == 0){
                    map[i][k] = new Tomato(i, k, tomato, false);
                }
            }
        }

        BFS();

        //하나라도 0이 있을 경우 -1을 리턴한다.
        for (int i = 0; i < row; i++) {
            for (int k = 0; k < col; k++) {
                if (map[i][k].value == 0) {
                    System.out.println("-1");
                    return;
                }
                count = Math.max(count, map[i][k].value);
            }
        }

        System.out.println(count-1);
    }

    private static void BFS() {
        while(!tomatoQueue.isEmpty()) {
            Tomato tomato = tomatoQueue.poll();
            visitedTomato(tomato.x, tomato.y);
        }
    }

    private static void visitedTomato(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX < 0 || nextY < 0 || nextX >= row || nextY >= col) {
                continue;
            }

            if (map[nextX][nextY].value != 0) {
                continue;
            }

            map[nextX][nextY].value = map[x][y].value + 1;
            tomatoQueue.add(new Tomato(nextX, nextY, map[nextX][nextY].value, false));
        }
    }

}
