package com.company.backjoon.Geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol3392_re {

    static int MAX = 300001;
    static int[] tree;
    static int[] count;
    static Map[] map;

    static class Map implements Comparable<Map> {
        int x;
        int y1;
        int y2;
        int status;

        public Map(int x, int y1, int y2, int status) {
            this.x = x;
            this.y1 = y1;
            this.y2 = y2;
            this.status = status;
        }

        @Override
        public int compareTo(Map o) {
            return this.x - o.x;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()) * 2;
        int H = 1 << (int) Math.ceil(Math.log(MAX)/Math.log(2));
        map = new Map[N];
        tree = new int[H*2];
        count = new int[H*2];

        for (int i = 0; i < N; i=i+2) {
            String[] pointStr = br.readLine().split(" ");
            int x1 = Integer.parseInt(pointStr[0]);
            int y1 = Integer.parseInt(pointStr[1]);
            int x2 = Integer.parseInt(pointStr[2]);
            int y2 = Integer.parseInt(pointStr[3]);
            map[i] = new Map(x1, y1, y2, 1);    //시작점
            map[i+1] = new Map(x2, y1, y2, -1);//끝점
        }
        int dx = map[0].x;
        int area = 0;
        for (int i = 1; i < map.length; i++) {
            area = area + (tree[1] * (map[i].x - dx));
            update(0, H, i, map[i].y1+1, map[i].y2, map[i].status);
            dx = map[i].x;
        }
        System.out.println(area);
    }

    private static void update(int l, int r, int i, int L, int R, int status) {
        if (l > R || r < L) {
            return;
        }
        if (l >= L && r <= R) {
            count[i] = count[i] + status;
            merge(l, r, i);
            return;
        }
        int mid = (l + r) /2;
        update(l, mid, i*2 , L, R, status);
        update(mid+1, r, i*2+1 , L, R, status);
        merge(l, r, i);
    }

    private static void merge(int l, int r, int i) {
        if (count[i] > 0) {
            tree[i] = r-l+1;
        } else if (l != r) {
            tree[i] = tree[i*2] + tree[i*2+1];
        } else {
            tree[i] = 0;
        }
    }
}