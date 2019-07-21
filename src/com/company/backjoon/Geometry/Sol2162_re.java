package com.company.backjoon.Geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol2162_re {

    static int N;
    static Line[] list;
    static int[] par;

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
        N = Integer.parseInt(br.readLine());
        list = new Line[N+1];
        par = new int[N+1];
        for (int i = 1; i <= N; i++) {
            String[] pointStr = br.readLine().split(" ");
            int x1 = Integer.parseInt(pointStr[0]);
            int x2 = Integer.parseInt(pointStr[1]);
            int y1 = Integer.parseInt(pointStr[2]);
            int y2 = Integer.parseInt(pointStr[3]);
            list[i] = new Line(x1, y1, x2, y2);
        }
        for (int i = 1; i <= N; i++) {
            par[i] = i;
        }
        int iPar, jPar;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    continue;
                }
                iPar = find(i);
                jPar = find(j);
                if (iPar != jPar) {
                    if (isCrossed(list[i], list[j])) {
                        union(i, j);
                    }
                }

            }
        }
        int[] count = new int[N+1];
        for (int i = 1; i <= N; i++) {
            count[par[i]]++;
        }
        int max = 0;
        int size = 0;
        for (int i = 1; i <=N; i++) {
            if (max < count[i]) {
                max = count[i];
            }
            if (count[i] != 0) {
                size++;
            }
        }
        System.out.println(max);
        System.out.println(size);

    }
    private static boolean isCrossed(Line l1, Line l2) {
        int chk1 = ccw(l1.x1, l1.y1, l1.x2, l1.y2, l2.x1, l2.y1) * ccw(l1.x1, l1.y1, l1.x2, l1.y2, l2.x2, l2.y2);
        int chk2 = ccw(l2.x1, l2.y1, l2.x2, l2.y2, l1.x1, l1.y1) * ccw(l2.x1, l2.y1, l2.x2, l2.y2, l1.x2, l1.y2);
        if (chk1 == 0 && chk2 == 0) {
            return isOverlapped(l1, l2);
        }
        return chk1 <= 0 && chk2 <= 0;
    }

    private static boolean isOverlapped(Line l1, Line l2) {
        if (Math.max(l1.x1, l1.x2) < Math.min(l2.x1, l2.x2)) {
            return false;
        }
        if (Math.min(l1.x1, l1.x2) > Math.max(l2.x1, l2.x2)) {
            return false;
        }
        if (Math.max(l1.y1, l1.y2) < Math.min(l2.y1, l2.y2)) {
            return false;
        }
        if (Math.min(l1.y1, l1.y2) > Math.max(l2.y1, l2.y2)) {
            return false;
        }
        return true;
    }

    private static void union(int i, int j) {
        int p = find(i);
        int q = find(j);
        if (p == q) {
            return;
        }
        par[q] = p;
    }

    private static int find(int i) {
        if (par[i] == i) {
            return i;
        }
        return par[i] = find(par[i]);
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
}
