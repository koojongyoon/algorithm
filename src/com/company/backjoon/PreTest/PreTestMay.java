package com.company.backjoon.PreTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PreTestMay {

    static class Point {
        int x;
        int y;
        int number;
        double degree;

        Point(int x, int y, int number) {
            this.x = x;
            this.y = y;
            this.number = number;
        }
    }

    static int N;
    static int H;
    static int L;
    static int[] drone;

    static Point zeroPoint;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int k = 1; k <= T; k++) {
            String[] NLH = br.readLine().split(" ");
            N = Integer.parseInt(NLH[0]);
            L = Integer.parseInt(NLH[1]);
            H = Integer.parseInt(NLH[2]);

            List<Point> pointList = new ArrayList<>();
            drone = new int[N + 1];
            zeroPoint = new Point(0, 0, 0);

            for (int i = 0; i < N; i++) {
                String[] XY = br.readLine().split(" ");
                int x = Integer.parseInt(XY[0]);
                int y = Integer.parseInt(XY[1]);

                Point inputPoint = new Point(x, y, i + 1);

                // 범위내의 원에 포함된 점들만 출력에 포함한다.
                if (checkDistance(inputPoint, zeroPoint)) {
                    pointList.add(inputPoint);
                }

                drone[i+1] = 0;  //파괴되지 않은 드론은 0으로 표기한다.(범위밖의 드론은 번호만 기억해둔다).
            }

            List<Point> resultPoint = new ArrayList<>();

            for (int i = 0; i < pointList.size(); i++) {
                resultPoint.add(new Point(pointList.get(i).x, pointList.get(i).y, pointList.get(i).number));
            }

            for (int i = 0; i < pointList.size(); i++) {
                calcAngle(pointList.get(i));
            }

            pointList.sort(new Comparator<Point>() {
                @Override
                public int compare(Point p1, Point p2) {
                    if (p1.degree < p2.degree) {
                        return -1;
                    } else if (p1.degree > p2.degree) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
//
            for (int i = 0; i < pointList.size(); i++) {
                Point point = pointList.get(i);
                if (i > 0 && point.degree == pointList.get(i-1).degree) {
                    drone[point.number] = drone[pointList.get(i-1).number];
                } else {
                    drone[point.number] = i+1;
                }
            }

            System.out.print("#" + k);
            for (int i = 1; i < drone.length; i++) {
                System.out.print(" " + drone[i]);
            }
            System.out.println();
        }
    }

    public static Point calcAngle(Point p1) {
        double theta = Math.atan2(p1.x, p1.y);
        double angle = Math.toDegrees(theta);
        if (angle - 90 < 0) {
            angle = angle + 360;
        }
        p1.degree = angle-90;
        return p1;
    }

    //두 점간의 거리 계산
    static double distance(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y - point1.y, 2));
    }


    // 유효한 범위안의 점들만 스택에 담긴다.
    static boolean checkDistance(Point point, Point zeroPoint) {
        double distanceZeroToPoint = distance(point, zeroPoint);
        if (distanceZeroToPoint <= L && distanceZeroToPoint >= H) {
            return true;
        }
        return false;
    }
}