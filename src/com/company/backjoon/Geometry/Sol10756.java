package com.company.backjoon.Geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Sol10756 {

    static ArrayList<Point> pizza;
    static ArrayList<Point> olive;

    static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        pizza = new ArrayList<>();
        for (int n = 0; n < N; n++) {
            String[] pizzaStr = br.readLine().split(" ");
            int x1 = Integer.parseInt(pizzaStr[0]);
            int y1 = Integer.parseInt(pizzaStr[1]);
            pizza.add(new Point(x1, y1));
        }

        int M = Integer.parseInt(br.readLine());
        olive = new ArrayList<>();
        for (int m = 0; m < M; m++) {
            String[] oliveStr = br.readLine().split(" ");
            int x1 = Integer.parseInt(oliveStr[0]);
            int y1 = Integer.parseInt(oliveStr[1]);
            olive.add(new Point(x1, y1));
        }

    }

    private static int ccw(Point p1, Point p2, Point p3) {
        long result = p1.x*p2.y + p2.x*p3.y + p3.x*p1.y - p2.x*p1.y - p3.x* p2.y - p1.x*p3.y;
        if (result > 0) {
            return 1;
        } else if (result == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    private static int getWidth(Point p1, Point p2, Point p3) {
        return p1.x*p2.y + p2.x*p3.y + p3.x*p1.y - p2.x*p1.y - p3.x* p2.y - p1.x*p3.y;
    }
}
