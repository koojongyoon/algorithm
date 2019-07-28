package com.company.backjoon.Geometry;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

//https://donggov.tistory.com/75
// 두 점간의 거리 구하기
public class Sol10254 {

    static class Point {
        long x;
        long y;
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    private static ArrayList<Point> points;
    private static ArrayList<Point> hull;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            points = new ArrayList<>();
            hull = new ArrayList<>();
            for (int n = 0; n < N; n++) {
                String[] inputStr = br.readLine().split(" ");
                long x1 = Long.parseLong(inputStr[0]);
                long y1 = Long.parseLong(inputStr[1]);
                points.add(new Point(x1, y1));
            }

            rotatingCalipers();
        }
    }

    private static void rotatingCalipers() {
        hull = getConvexHull();

        long maxDist = 0;
        long a1=0, a2=0, a3=0, a4=0;
        int j = 1;
        for(int i=0; i<hull.size(); i++) {
            int i_next = (i+1) % hull.size();
            for (;;) {
                int j_next = (j+1) % hull.size();
                long bx = hull.get(i_next).x - hull.get(i).x;
                long by = hull.get(i_next).y - hull.get(i).y;
                long cx = hull.get(j_next).x - hull.get(j).x;
                long cy = hull.get(j_next).y - hull.get(j).y;
                int ccw = ccw(new Point(0, 0), new Point(bx, by), new Point(cx, cy));
                if(ccw > 0) {
                    j = j_next;
                } else {
                    break;
                }
            }
            long dist = distance(hull.get(i), hull.get(j));
            if(dist > maxDist) {
                maxDist = dist;
                a1 = hull.get(i).x;
                a2 = hull.get(i).y;
                a3 = hull.get(j).x;
                a4 = hull.get(j).y;
            }
        }
        System.out.println(a1 + " " + a2 + " " + a3 + " " + a4);
    }

    private static ArrayList<Point> getConvexHull() {
        Point startPoint = getStartPoint();

        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                long result = ccw(startPoint, p1, p2);
                if (result > 0) {
                    return -1;
                } else if (result == 0) {
                    if (distance(startPoint, p1) > distance(startPoint, p2)) {
                        return 1;
                    }
                } else {
                    return 1;
                }
                return -1;
            }
        });

        hull.add(startPoint);
        for (int i = 1; i < points.size(); i++) {
            if (hull.size() > 1 && ccw(hull.get(hull.size() - 2), hull.get(hull.size() -1), points.get(i)) <= 0) {
                hull.remove(hull.size()-1);
            }
            hull.add(points.get(i));
        }
        return hull;
    }

    static long distance(Point point1, Point point2) {
        return (long) (Math.pow(point2.x-point1.x, 2) + Math.pow(point2.y-point1.y, 2));
    }

    private static Point getStartPoint() {
        Point point = points.get(0);
        for (int i = 0; i < points.size(); i++) {
            if (point.x > points.get(i).x) {
                point = points.get(i);
            } else if (point.x == points.get(i).x) {
                if (point.y > points.get(i).y) {
                    point = points.get(i);
                }
            }
        }
        return point;
    }

    private static int ccw(Point p1, Point p2, Point p3) {
        long result = p1.x*p2.y + p2.x*p3.y + p3.x*p1.y - p2.x*p1.y - p3.x*p2.y - p1.x*p3.y;
        if (result > 0) {
            return 1;
        } else if (result == 0) {
            return 0;
        } else {
            return -1;
        }
    }
}
