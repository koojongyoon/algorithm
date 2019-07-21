package com.company.backjoon.Geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

public class Sol9240 {

    static int N;
    static ArrayList<Point> list;
    static Stack<Point> stack;
    static Point startPoint;

    static class Point {
        int x;
        int y;
        Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            String[] xyStr = br.readLine().split(" ");
            int x = Integer.parseInt(xyStr[0]);
            int y = Integer.parseInt(xyStr[1]);
            list.add(new Point(x, y));
        }

        startPoint = getStartPoint();
        stack.add(startPoint);

        sortList();

        for (int i = 1; i < list.size(); i++) {
            while (stack.size() > 1
                    && 0 >= ccw(stack.get(stack.size()-2), stack.get(stack.size()-1), list.get(i))) {
                stack.pop();
            }
            stack.add(list.get(i));
        }
        double maxDist = 0;
        for (int i = 0; i < stack.size(); i++) {
            for (int j = 0; j < stack.size(); j++) {
                if (maxDist < getDistance(stack.get(i), stack.get(j))) {
                    maxDist = getDistance(stack.get(i), stack.get(j));
                }
            }
        }
        System.out.println(maxDist);

    }

    private static void sortList() {
        list.sort(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                int result = ccw(startPoint, p1, p2);
                if (result > 0){
                    return -1;
                } else if (result == 0) {
                    if (getDistance(startPoint, p1) > getDistance(startPoint, p2)) {
                        return 1;
                    }
                } else {
                    return 1;
                }
                return -1;
            }
        });
    }

    private static double getDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p2.x - p1.x),2) + Math.pow((p2.y - p1.y), 2));
    }

    private static int ccw(Point p1, Point p2, Point p3) {
        int resultCcw = p1.x*p2.y + p2.x*p3.y + p3.x*p1.y - p2.x*p1.y - p3.x*p2.y - p1.x*p3.y;
        if (resultCcw > 0) {
            return 1;
        } else if (resultCcw == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    private static Point getStartPoint() {
        Point start = new Point(1010, 1010);
        for (int i = 0; i < N; i++) {
            if (start.x > list.get(i).x) {
                start = list.get(i);
            } else if (start.x == list.get(i).x) {
                if (start.y > list.get(i).y) {
                    start = list.get(i);
                }
            }
        }
        return start;
    }
}
