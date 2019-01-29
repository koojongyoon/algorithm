package com.company.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol14503 {

    static int NORTH = 0;
    static int EAST = 1;
    static int SOUTH = 2;
    static int WEST = 3;

    static int[][] map;
    static int cleanCount;
    static int N;
    static int M;

    //북, 동, 남, 서
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};

    static class Robot {
        int currentRow;
        int currentCol;
        int currentDirection;

        public Robot (int currentRow, int currentCol, int currentDirection) {
            this.currentRow = currentRow;
            this.currentCol = currentCol;
            this.currentDirection = currentDirection;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NM = br.readLine().split(" ");
        N = Integer.parseInt(NM[0]);
        M = Integer.parseInt(NM[1]);

        String[] currentRobotPosition = br.readLine().split(" ");
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] currentMapDraw = br.readLine().split(" ");
            for (int k = 0; k < M; k++) {
                map[i][k] = Integer.parseInt(currentMapDraw[k]);
            }
        }

        int startRow = Integer.parseInt(currentRobotPosition[0]);
        int startCol = Integer.parseInt(currentRobotPosition[1]);
        int startDirection = Integer.parseInt(currentRobotPosition[2]);

        Robot robot = new Robot(startRow, startCol, startDirection);

        cleanCount = 0;
        cleanRoom(robot);

        System.out.println(cleanCount);
    }

    static void cleanRoom(Robot robot) {
        // 벽에 부딪힐 경우 return
        if (map[robot.currentRow][robot.currentCol] == 1) {
            return;
        }

        // 청소가 필요한 부분일 경우 -1로 변경하고 청소한 구간의 갯수 증가
        if (map[robot.currentRow][robot.currentCol] == 0) {
            map[robot.currentRow][robot.currentCol] = -1;
            cleanCount++;
        }


        // 4방향을 모두 확인
        for (int i = 0; i < 4; i++) {
            //현재 방향의 왼쪽 방향을 찾기위해 이전 방향
            int nextDirection = (robot.currentDirection + 3) % 4;
            int nextRow = robot.currentRow + dx[nextDirection];
            int nextCol = robot.currentCol + dy[nextDirection];
            //청소할수 있으면 다시 재귀
            if (map[nextRow][nextCol] == 0) {
                cleanRoom(new Robot(nextRow, nextCol, nextDirection));
                return;
            } else {
                //청소할수 없으면 그 다음 왼쪽 방향 찾기
                robot.currentDirection = nextDirection;
            }
        }

        // 방향에 따라 후진
        Robot backStepRobot = backStep(robot);

        // 후진한 방향을 기준으로 다시 청소
        cleanRoom(new Robot(backStepRobot.currentRow, backStepRobot.currentCol ,backStepRobot.currentDirection ));
    }

    private static Robot backStep(Robot robot) {
        // 북쪽을 바라본 경우 후진 방향
        if (robot.currentDirection == NORTH) {
            robot.currentRow = robot.currentRow + 1;
        }
        // 동쪽을 바라본 경우 후진 방향
        if (robot.currentDirection  == EAST) {
            robot.currentCol = robot.currentCol - 1;
        }
        // 남쪽을 바라본 경우 후진 방향
        if (robot.currentDirection  == SOUTH) {
            robot.currentRow = robot.currentRow - 1;
        }
        // 서쪽을 바라본 경우 후진 방향
        if (robot.currentDirection  == WEST) {
            robot.currentCol = robot.currentCol + 1;
        }
        return  robot;
    }
}
