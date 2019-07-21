package com.company.backjoon.Geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Sol2166_re {

    static class Point {
        long x;
        long y;
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    static ArrayList<Point> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for (int n = 0; n < N; n++) {
            String[] inputStr = br.readLine().split(" ");
            long x = Integer.parseInt(inputStr[0]);
            long y = Integer.parseInt(inputStr[1]);
            list.add(new Point(x, y));
        }

        Point origin = list.get(0);
        long sumResult = 0;
        for (int n = 1; n < N-1; n++) {
            sumResult += ccw(origin, list.get(n), list.get(n+1));
        }
        long result = Math.abs(sumResult);
        String prime= "";
        if (result%2 == 1) {
            prime = ".5";
        } else {
            prime = ".0";
        }
        result /= 2;
        System.out.println(result+prime);
    }

    private static long ccw(Point p1, Point p2, Point p3) {
        return p1.x*p2.y + p2.x*p3.y + p3.x*p1.y - p2.x*p1.y - p3.x*p2.y - p1.x*p3.y;
    }
}
