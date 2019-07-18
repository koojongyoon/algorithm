package com.company.backjoon.Geometry;

import java.io.*;

public class Sol11758 {

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
        String[] firstStr = br.readLine().split(" ");
        String[] secondStr = br.readLine().split(" ");
        String[] thirdStr = br.readLine().split(" ");
        Point p1 = new Point(Integer.parseInt(firstStr[0]), Integer.parseInt(firstStr[1]));
        Point p2 = new Point(Integer.parseInt(secondStr[0]), Integer.parseInt(secondStr[1]));
        Point p3 = new Point(Integer.parseInt(thirdStr[0]), Integer.parseInt(thirdStr[1]));
        ccw(p1, p2, p3);

    }

    private static void ccw(Point p1, Point p2, Point p3) {
        int result = (p1.x * p2.y + p2.x*p3.y + p3.x * p1.y) - (p2.x*p1.y + p3.x*p2.y + p1.x*p3.y);
        if (result > 0) {
            System.out.println("1");
        } else if (result == 0) {
            System.out.println("0");
        } else {
            System.out.println("-1");
        }
    }
}
