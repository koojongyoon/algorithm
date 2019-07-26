package com.company.backjoon.Geometry;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//다각형의 면적
public class Sol2166 {
    static class Point {
        long x;
        long y;
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    static ArrayList<Point> pointList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int pointCount = Integer.parseInt(br.readLine());

        pointList = new ArrayList<>();

        for (int i = 0; i < pointCount; i++) {
            String[] pointsStr = br.readLine().split(" ");
            int first = Integer.parseInt(pointsStr[0]);
            int second = Integer.parseInt(pointsStr[1]);
            pointList.add(new Point(first, second));
        }
        Point origin = pointList.get(0);
        long sumResult = 0;
        for (int i = 1; i < pointCount-1; i++) {
            sumResult = sumResult + ccw(origin, pointList.get(i), pointList.get(i+1));
        }

        long result = sumResult < 0 ? sumResult * -1 : sumResult;
        String add = result%2 == 1 ? ".5" : ".0";
        System.out.println(result/2 + add);
    }

    private static long ccw(Point p1, Point p2, Point p3) {
        long result = (p1.x * p2.y + p2.x*p3.y + p3.x * p1.y) - (p2.x*p1.y + p3.x*p2.y + p1.x*p3.y);
        return result;
    }
}
