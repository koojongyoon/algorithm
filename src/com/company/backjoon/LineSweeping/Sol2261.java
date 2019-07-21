package com.company.backjoon.LineSweeping;

import java.io.*;
import java.util.*;

// https://mygumi.tistory.com/294

public class Sol2261 {
    static class Point implements Comparable<Point> {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return x < o.x && y > o.y ? 1 : -1;
        }
    }

    static class ComparatorSet implements Comparator<Point> {

        @Override
        public int compare(Point p1, Point p2) {
           if (p1.y == p2.x) {
               if (p1.x < p2.x) {
                   return -1;
               } else if (p1.x == p2.x) {
                   return 0;
               } else {
                   return 1;
               }
           } else {
               return p1.y < p2.y ? 1: -1;
           }
        }
    }

    static class ComparatorDescending implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            if (p1.x < p2.x) {
                return -1;
            } else if (p1.x == p2.x) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    static ArrayList<Point> pointList;
    static TreeSet<Point> candidate;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        pointList = new ArrayList<>();
        candidate = new TreeSet<>(new ComparatorSet());

        for (int n = 0; n < N; n++) {
            String[] pointStr = br.readLine().split(" ");
            int x = Integer.parseInt(pointStr[0]);
            int y = Integer.parseInt(pointStr[1]);

            pointList.add(new Point(x, y));
        }

        Collections.sort(pointList, new ComparatorDescending());
        candidate.add(pointList.get(0));
        candidate.add(pointList.get(1));
        int ans = getDistanceBetweenTwoPoints(pointList.get(0), pointList.get(1));
        int start = 0;

        for (int i = 2; i < N; i++) {
            Point now = pointList.get(i);
            while (start < i) {
                Point pivot = pointList.get(start);
                int x = pivot.x - now.x;
                if (x*x > ans) {
                    candidate.remove(pivot);
                    start = start + 1;
                } else {
                    break;
                }
            }
            int d = (int) Math.sqrt((double) ans) + 1;
            Point lower_point = new Point(now.y - d, now.y + d);
            SortedSet<Point> lower = candidate.tailSet(lower_point);
            Iterator<Point> it_lower = lower.iterator();
            while(it_lower.hasNext()) {
                Point p = it_lower.next();
                d = getDistanceBetweenTwoPoints(now, p);
                if (d < ans) {
                    ans = d;
                }
            }
            candidate.add(pointList.get(i));
        }
        System.out.println(ans);
        br.close();
    }

    private static int getDistanceBetweenTwoPoints(Point p1, Point p2) {
        return (p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y);
    }
}
