package com.company.backjoon.Geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

public class Sol1708_re {

    static class Point {
        int x;
        int y;
        Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static ArrayList<Point> list;
    static Stack<Point> stack;
    static Point startPoint;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            int x = Integer.parseInt(str[0]);
            int y = Integer.parseInt(str[1]);
            list.add(new Point(x, y));
        }

        startPoint = findStartPoint();

        list.sort(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                int result = ccw(startPoint, p1, p2);
                if (result > 0) {
                    return -1;
                } else if (result == 0) {
                    double d = getDistance(startPoint, p1) - getDistance(startPoint, p2);
                    if (d > 0) {
                        return 1;
                    }
                } else {
                    return 1;
                }
                return -1;
            }
        });
        stack.add(startPoint);
        for (int i = 1; i < list.size(); i++) {
            while(stack.size() > 1 && 0 >= ccw(stack.get(stack.size()-2), stack.get(stack.size()-1), list.get(i))) {
                stack.pop();
            }
            stack.add(list.get(i));
        }

        System.out.println(stack.size());
    }

    private static double getDistance(Point p1, Point p2) {
        return Math.sqrt((Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2)));
    }

    private static Point findStartPoint() {
        Point start = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (int i = 0; i < list.size(); i++) {
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

    private static int ccw(Point p1, Point p2, Point p3) {
        int result = p1.x*p2.y + p2.x *p3.y + p3.x*p1.y - p2.x*p1.y - p3.x*p2.y - p1.x*p3.y;
        if (result > 0) {
            return 1;
        } else if (result == 0) {
            return 0;
        } else {
            return -1;
        }
    }
}
