package com.company.PreTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class PreTestApril {

    static Car[][] map;
    static Map<Integer, Car> parkMem;

    static String S = "S";
    static String N = "N";
    static String W = "W";
    static String E = "E";

    static class Car {
        String direction;
        int carNum;
        int x;
        int y;
        int x_b;
        int y_b;

        public Car(String direction, int carNum, int x, int y) {

            this.direction = direction;
            this.carNum = carNum;
            this.x = x;
            this.y = y;

            if (direction.equals(N)) {
                x_b = x+1;
                y_b = y;
            }
            if (direction.equals(S)) {
                x_b = x-1;
                y_b = y;
            }
            if (direction.equals(E)) {
                x_b = x;
                y_b = y-1;
            }
            if (direction.equals(W)) {
                x_b = x;
                y_b = y+1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new Car[5][5];
        parkMem = new HashMap<>();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int carCount = Integer.parseInt(br.readLine());

            // 이동하는 포인트 null 처리
            for (int m = 0; m < 5; m++) {
                for (int n = 0; n < 5; n++) {
                    map[m][n] = new Car("Y", 0, 0, 0);
                }
            }

            //초기 입력값 SET
            for (int k = 1; k <= carCount; k++) {
                String[] mapInput = br.readLine().split(" ");
                int x = Integer.parseInt(mapInput[0]);
                int y = Integer.parseInt(mapInput[1]);
                String direction = mapInput[2];
                parkMem.put(k, new Car(direction, k, x, y));
                initialFillMap(k, x - 1, y - 1, direction);
            }


            //이동 후
            for (int m = 0; m < 5; m++) {
                for (int n = 0; n < 5; n++) {
                    System.out.print(map[m][n].carNum + " ");
                }
                System.out.println();
            }
        }

    }

    //차의 형태대로 지도에 채워넣음
    private static void initialFillMap(int carNum, int x, int y, String direction) {
        Car car = new Car(direction, carNum, x, y);
        map[x][y] = car;
        if (direction.equals(N)) {
            map[x+1][y] = car;
        }
        if (direction.equals(S)) {
            map[x-1][y] = car;
        }
        if (direction.equals(E)) {
            map[x][y-1] = car;
        }
        if (direction.equals(W)) {
            map[x][y+1] = car;
        }
    }

    //차를 좌로 움직일수 있게 해줌
    private static void leftMove(int carNum) {
        Car car = parkMem.get(carNum);
        while (car.x > 0) {
            car.
        }
    }
}
