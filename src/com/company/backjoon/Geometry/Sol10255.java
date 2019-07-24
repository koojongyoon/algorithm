package com.company.backjoon.Geometry;

import java.io.*;

public class Sol10255 {

    static class Square {

        int x1;
        int y1;
        int x2;
        int y2;

        Square(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    static class Line {

        int x1;
        int y1;
        int x2;
        int y2;

        Line(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        for (int n = 0; n < N; n++) {
            String[] squareStr = br.readLine().split(" ");
            int x1 = Integer.parseInt(squareStr[0]);
            int y1 = Integer.parseInt(squareStr[1]);
            int x2 = Integer.parseInt(squareStr[2]);
            int y2 = Integer.parseInt(squareStr[3]);

            Square square = new Square(x1, y1, x2, y2);

            String[] lineStr = br.readLine().split(" ");
            int x3 = Integer.parseInt(lineStr[0]);
            int y3 = Integer.parseInt(lineStr[1]);
            int x4 = Integer.parseInt(lineStr[2]);
            int y4 = Integer.parseInt(lineStr[3]);

            Line line = new Line(x3, y3, x4, y4);

            if (hasZeroPoint(square, line)) {
                bw.write("0");
                continue;
            }
            if (hasOnePoint(square, line)) {
                bw.write("1");
                continue;
            }
            if (hasTwoPoint(square, line)) {
                bw.write("2");
                continue;
            }
            if (infinitePoint(square, line)) {
                bw.write("4");
                continue;
            }

        }
        bw.flush();
        bw.close();
        br.close();

    }

    private static int isIntersect(Square square, Line line) {
        if (hasZeroPoint(square, line) > 0) {

        }
        hasOnePoint(square, line);
        hasTwoPoint(square, line);
        infinitePoint(square, line);
    }

    private static int ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
        int result = x1*y2 + x2*y3 + x3*y1 - x2*y1 - x3*y2 - x1*y3;
        if (result > 0) {
            return 1;
        } else if (result == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    //접점이 없는 경우
    private static boolean hasZeroPoint(Square square, Line line) {

        return false;
    }

    //접점이 하나만 있는 경우
    private static boolean hasOnePoint(Square square, Line line) {

        return false;
    }

    //점점이 두개인 경우
    private static boolean hasTwoPoint(Square square, Line line) {

        return false;
    }

    //접점이 무수히 많은 경우
    private static boolean infinitePoint(Square square, Line line) {

        return false;
    }
}
