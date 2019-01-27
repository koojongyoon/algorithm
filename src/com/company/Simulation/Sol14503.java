package com.company.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Sol14503 {

    static int NORTH = 0;
    static int EAST = 1;
    static int SOUTH = 2;
    static int WEST = 3;
    static int[][] map;
    static int cleanCount = 0;
    static Queue<Robot> queue;

    static class Robot {
        int row;
        int col;
        int direction;

        Robot (int row, int col, int directin) {
            this.row = row;
            this.col = col;
            this.direction = directin;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);

        String[] currentRobotPosition = br.readLine().split(" ");
        map = new int[N][M];
        queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            String[] currentMapDraw = br.readLine().split(" ");
            for (int k = 0; k < M; k++) {
                map[i][k] = Integer.parseInt(currentMapDraw[k]);
            }
        }

        int robotStartRow = Integer.parseInt(currentRobotPosition[0]);
        int robotStartCol = Integer.parseInt(currentRobotPosition[1]);
        int robotDirection = Integer.parseInt(currentRobotPosition[2]);

        cleanRoom(robotStartRow, robotStartCol, robotDirection);        

        System.out.println(cleanCount);
    }

    private static void cleanRoom(int robotStartRow, int robotStartCol, int robotSeeDirection) {
        queue.add(new Robot(robotStartRow, robotStartCol, robotSeeDirection));

        // 1. 현재 위치를 청소하고 값을 1로 변경한다.
        currentPositionClean(robotStartRow, robotStartCol);

        // 2. 현재 방향을 기준으로 왼쪽으로 한바퀴 돈 후 값이 0이면 전진하고 1로 돌아간다.
        moveDirectionAndGo(robotStartRow, robotStartCol, robotSeeDirection);
    }

    private static void currentPositionClean(int row, int col) {
        map[row][col] = -1;
        cleanCount++;
    }

    private static void moveDirectionAndGo(int row, int col, int direction) {
        if (direction == NORTH && col - 1 == 0) {
            cleanRoom(row, col - 1, WEST);
        } else if (direction == WEST && row + 1 == 0) {
            cleanRoom(row+1, col, SOUTH);
        } else if (direction == SOUTH && col + 1 == 0) {
            cleanRoom(row, col + 1, EAST);
        } else if (direction == EAST && row - 1 == 0) {
            cleanRoom(row, col - 1, NORTH);
        } else {

        }
    }
}
