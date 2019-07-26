package com.company.backjoon.Geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sol2261_me {

    private static ArrayList<Point> points;
    private static ArrayList<Point> candidate;

    static class Point implements Comparable<Point> {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.x < o.x) {
                return -1;
            } else if (this.x == o.x) {
                return 0;
            } else {
                return 1;
            }
        }

        public int getDistance(Point p) {
            return (int)(Math.pow(p.x - this.x, 2) + Math.pow(p.y - this.x, 2));
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        points = new ArrayList<>();
        candidate = new ArrayList<>();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            int x = Integer.parseInt(str[0]);
            int y = Integer.parseInt(str[1]);
            points.add(new Point(x, y));
        }

        Collections.sort(points);

        int min = (int) getResult(0, points.size()-1);
        System.out.println(min);
    }

    private static double getResult(int s, int e) {
        if (e-s == 0) {
            return Integer.MAX_VALUE;
        }
        if (e-s == 1) {
            return points.get(s).getDistance(points.get(e));
        }
        int mid = (s+e)/2;
        int min = (int)Math.min(getResult(s, mid), getResult(mid+1, e));
        int line = (points.get(mid).x + points.get(mid+1).x)/2;

        for (int i = mid+1; i <= e; i++) {
            if (Math.pow(line - points.get(i).x, 2) >=min) {
                break;
            }
            candidate.add(points.get(i));
        }

        for (int i = mid; i >= s; --i) {
            if (Math.pow(points.get(i).x, 2) >=min) {
                break;
            }
            candidate.add(points.get(i));
        }

        if (candidate.size() == 0) {
            return min;
        }

        Collections.sort(candidate, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.y < o2.y) {
                    return -1;
                } else if (o1.y == o2.y) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

        for (int i = 0; i < candidate.size()-1; i++) {
            Point point1 = candidate.get(i);
            for (int j = i+1; i < candidate.size()-1; j++) {
                Point point2 = candidate.get(j);
                if (Math.pow(point2.y - point1.y, 2) >= min) {
                    break;
                }
                min = Math.min(min, point1.getDistance(point2));
            }
        }
        return min;
    }
}
