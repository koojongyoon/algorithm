package com.company.backjoon.Geometry;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

public class Sol10254 {

    static class Point implements Comparable<Point> {
        long x;
        long y;
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point p) {
            if (p.x == 0 && p.y == 0) {
                return 1;
            }
            if (this.x == 0 && this.y == 0) {
                return -1;
            }
            if (product(this, p) != 0) {
                if (product(this, p) > 0) {
                    return -1;
                } else if (product(this, p) < 0) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                if (dist(this) < dist(p)) {
                    return -1;
                } else if (dist(this) > dist(p)) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }
    private static ArrayList<Point> points;
    private static ArrayList<Point> hull;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            points = new ArrayList<Point>();
            hull = new ArrayList<Point>();
            for (int n = 0; n < N; n++) {
                String[] inputStr = br.readLine().split(" ");
                long x1 = Long.parseLong(inputStr[0]);
                long y1 = Long.parseLong(inputStr[1]);
                points.add(new Point(x1, y1));
            }

            bw.write(rotatingCalipers());
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static String rotatingCalipers() {
        hull = getConvexHull();

        int cur = 1;
        hull.add(hull.get(0));

        for (; cur + 1 < hull.size(); cur++) {
            long ccw = product(subtract(hull.get(0), hull.get(1)), subtract(hull.get(cur), hull.get(cur + 1)));
            if (ccw <= 0)
                break;
        }

        Point A = hull.get(0);
        Point B = hull.get(cur);
        long dist = dist(subtract(hull.get(cur), hull.get(0)));

        for (int i = 1; i + 1 < hull.size(); i++) {
            for (; cur + 1 < hull.size(); cur++) {
                long ccw = product(subtract(hull.get(i), hull.get(i + 1)), subtract(hull.get(cur), hull.get(cur + 1)));

                if (dist(subtract(hull.get(cur), hull.get(i))) > dist) {
                    dist = dist(subtract(hull.get(cur), hull.get(i)));
                    A = hull.get(i);
                    B = hull.get(cur);
                }

                if (ccw <= 0)
                    break;
            }

            if (dist(subtract(hull.get(cur), hull.get(i))) > dist) {
                dist = dist(subtract(hull.get(cur), hull.get(i)));
                A = hull.get(i);
                B = hull.get(cur);
            }
        }

        return String.format("%d %d %d %d\n", A.x, A.y, B.x, B.y);
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
    private static Point subtract(Point p1, Point p2) {
        return new Point(p1.x - p2.x, p1.y - p2.y);
    }

    private static long product(Point p1, Point p2) {
        return p1.x*p2.y - p2.x*p1.y;
    }

    private static long dist(Point p1) {
        return p1.x*p1.x + p1.y*p1.y;
    }
}
