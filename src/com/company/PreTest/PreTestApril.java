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
        int carNum;
        int x;
        int y;
        int x_b;
        int y_b;
        String direction;

        public Car (int carNum, int x, int y, String direction) {

            this.direction = direction;
            this.carNum = carNum;
            this.x = x;
            this.y = y;

            //입력받은 차의 뒷부분에 대한 좌표 처리
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

            // 이동하는 포인트  default value처리
            for (int m = 0; m < 5; m++) {
                for (int n = 0; n < 5; n++) {
                    cleanOriginPosition(m, n);
                }
            }

            // 초기 입력값 SET
            for (int k = 1; k <= carCount; k++) {
                String[] mapInput = br.readLine().split(" ");
                int x = Integer.parseInt(mapInput[0]);
                int y = Integer.parseInt(mapInput[1]);
                String direction = mapInput[2];
                parkMem.put(k, new Car(k, x-1, y-1, direction));
                initialFillMap(k, x-1, y-1, direction);
            }

            // 이동 후 좌표 확인
            for (int m = 0; m < 5; m++) {
                for (int n = 0; n < 5; n++) {
                    System.out.print(map[m][n].carNum + " ");
                }
                System.out.println();
            }
            System.out.println("=========================");
            leftMove(7);

            // 이동 후 좌표 확인
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
        Car car = new Car(carNum, x, y, direction);
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

    private static void cleanOriginPosition (int x, int y) {
        map[x][y] = new Car(0, x, y, "Y");
    }

    private static void moveCarPosition (int carNum, int x, int y, String direction) {
        map[x][y] = new Car(carNum, x, y, direction);
    }

    private static void modifyParkCarInfo (int carNum, Car carInfo) {
        parkMem.put(carNum, carInfo);
    }

    // 방향을 보고 차를 왼쪽으로 움직일수 있게 해줌
    private static void leftMove(int carNum) {

        Car car = parkMem.get(carNum);
        int originX =  car.x;
        int originY = car.y;
        int originY_B =  car.y_b;

        if (car.direction.equals(W)) {
            while (car.y > 0 && map[car.x][car.y-1].carNum == 0) {
                car.y = car.y - 1;
                car.y_b = car.y_b - 1;
            }
        }

        if (car.direction.equals(E)) {
            while (car.y_b > 0 && map[car.x][car.y_b-1].carNum == 0) {
                car.y = car.y - 1;
                car.y_b = car.y_b - 1;
            }
        }

        cleanOriginPosition(originX, originY);   //원래 주차되어 있던 자리에서 clean
        cleanOriginPosition(originX, originY_B); //원래 주차되어 있던 자리에서 clean

        moveCarPosition(carNum, car.x, car.y, car.direction);   //이동한 자리로 숫자 대체
        moveCarPosition(carNum, car.x, car.y_b, car.direction); //이동한 자리로 숫자 대체

        modifyParkCarInfo(carNum, car);
    }
}
