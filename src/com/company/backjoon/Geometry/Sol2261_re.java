package com.company.backjoon.Geometry;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Point implements Comparable<Point> {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) { // x 축 기반 정렬
        if (this.x < o.x) {
            return -1;
        }
        if (this.x == o.x) {
            return 0;
        }
        return 1;
    }

    public int getDistance(Point o) {
        return (int) (Math.pow(x - o.x, 2) + Math.pow(y - o.y, 2));
    }
}

public class Sol2261_re {
    static ArrayList<Point> points = new ArrayList<>();


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            Point point = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
            points.add(point);
        }
        Collections.sort(points);

        int min = getResult(0, points.size() - 1);

        if(min == Integer.MAX_VALUE) {
            min = 0; // 모든 점들이 중복된 점이었을 경우
        }

        bw.write(Integer.toString(min));
        bw.flush();
        bw.close();
    }

    public static int getResult(int s, int e) {

        if (e - s == 0) { // 점 한개
            return Integer.MAX_VALUE;
        }

        if (e - s == 1) { // 점 두개
            return points.get(s).getDistance(points.get(e));
        }

        int mid = (e + s) / 2;
        int min = Math.min(getResult(s, mid), getResult(mid + 1, e));

        double line = (points.get(mid).x + points.get(mid + 1).x) / 2; // 중심선
        ArrayList<Point> testPoint = new ArrayList<>(); // 확인해봐야 할 점들

        for (int i = mid + 1; i <= e; ++i) { // 중심선 기준으로 오른쪽
            if (Math.pow(line - points.get(i).x, 2) >= min) {
                break;
            }
            testPoint.add(points.get(i));
        }

        for (int i = mid; i >= s; --i) { // 중심선 기준으로 왼쪽
            if (Math.pow(line - points.get(i).x, 2) >= min) {
                break;
            }
            testPoint.add(points.get(i));
        }
        if (testPoint.size() == 0) {
            return min;
        }

        Collections.sort(testPoint, (o1, o2) -> (o1.y < o2.y) ? -1 : (o1.y == o2.y) ? 0 : 1); // y축 기반 정렬

        for (int i = 0; i < testPoint.size(); ++i) {
            Point point1 = testPoint.get(i);
            for (int j = i + 1; j < testPoint.size(); ++j) {
                Point point2 = testPoint.get(j);
                if (Math.pow(point2.y - point1.y, 2) >= min) { // y 거리 비교
                    break;
                }
                min = Math.min(min, point1.getDistance(point2));
            }
        }
        return min;
    }
}
