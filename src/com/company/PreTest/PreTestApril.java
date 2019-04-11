package com.company.PreTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class PreTestApril {

    static Car[][] map;
    static Map<Integer, Car> parkMem;
    static LinkedList<Car> carList;
    static int moveCount = 0;

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

        for (int testCase = 1; testCase <= T; testCase++) {
            int carCount = Integer.parseInt(br.readLine());
            carList = new LinkedList<>();

            // 이동하는 포인트  default value 처리
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

            // 이동 전 좌표 확인
            printPark();

            if (checkPossibleOUTCar(testCase)) {
                continue;
            }

            // 1번차가 출구로 빠져나가기 위한 길 체크
            for (int k = 0; k < 5; k++) {
                //1번차가 나가는 경로에 차가 0이나 1이 아닐경우 치우기 위한 주차 되어 있는 차를 찾는다.
                if (map[2][k].carNum != 0 || map[2][k].carNum != 1) {
                    // 차량의 진로를 막고 있는 차량을 이동 대상에 포함시킨다.
                    BFS(map[2][k]);
                }
            }

            // 이동 후 좌표 확인
            printPark();

            // 이동횟수 출력
            System.out.println("#" + testCase + " " +  moveCount);
        }
    }

    private static boolean checkPossibleOUTCar(int testCase) {
        // 1번차가 출구로 나가는 길 확인 (1번차의 x축의 위치가 3에 있지 않으면 빠져나갈수 없음)
        if (parkMem.get(1).x != 2 || parkMem.get(1).x_b != 2) {
            System.out.println("#" + testCase + " " + "-1");
            return true;
        }

        // 1번차가 움직일수 있는 방향이 위아래 빠져나갈수 없음
        if (parkMem.get(1).direction.equals(N) || parkMem.get(1).direction.equals(S)) {
            System.out.println("#" + testCase + " " + "-1");
            return true;
        }

        // 1번차가 나가는 경로에 다른 차량이 서쪽이나 동쪽을 보고 있으면 1번 차량이 나갈수 없으므로 1번차가 빠져 나갈수 없다.
        for (int k = 0; k < 5; k++) {
            if (map[2][k].carNum != 0 || map[2][k].carNum != 1) {
                if (map[2][k].direction.equals(W) || map[2][k].direction.equals(E)) {
                    System.out.println("#" + testCase + " " + "-1");
                    return true;
                }
            }
        }
        return false;
    }

    private static void printPark() {
        for (int m = 0; m < 5; m++) {
            for (int n = 0; n < 5; n++) {
                System.out.print(map[m][n].carNum + " ");
            }
            System.out.println();
        }
        System.out.println("=========================");
    }

    private static void BFS(Car car) {
        carList.add(car);
        while (!carList.isEmpty()) {
            Car pollCar = carList.poll();
            // 1번차가 출구에 도달하면 끝
            if ((pollCar.carNum == 1) && (pollCar.x == 2|| pollCar.x_b == 2) && (pollCar.y == 4 || pollCar.y_b == 4)) {
                return;
            }
            while (pollCar.y >= 0) {

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

    // 기존에 있던 차의 위치를 clean 시켜줌
    private static void cleanOriginPosition (int x, int y) {
        map[x][y] = new Car(0, x, y, "Y");
    }

    // 차량의 위치를 변경한 좌표에다 차량 정보를 넣어줌
    private static void moveCarPosition (int carNum, int x, int y, String direction) {
        map[x][y] = new Car(carNum, x, y, direction);
    }

    // 차량의 정보를 갱신함
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

        cleanOriginPosition(originX, originY);   //원래 주차되어 있던 앞자리 clean
        cleanOriginPosition(originX, originY_B); //원래 주차되어 있던 뒷자리 clean

        moveCarPosition(carNum, car.x, car.y, car.direction);   //이동한 자리로 숫자 대체
        moveCarPosition(carNum, car.x, car.y_b, car.direction); //이동한 자리로 숫자 대체

        modifyParkCarInfo(carNum, car);

        //이동횟수 1 증가
        moveCount++;
    }
}
