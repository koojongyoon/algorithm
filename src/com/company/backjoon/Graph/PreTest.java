package com.company.backjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// http://blog.naver.com/PostView.nhn?blogId=sksdong1&logNo=220832931473&categoryNo=18&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=1&from=postView
public class PreTest {

    static ArrayList[] pairs;
    static ArrayList weight;
    static int N;
    static int M;

    static class Pair {
        int start;
        int end;

        Pair (int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            String[] NM = br.readLine().split(" ");
            N = Integer.parseInt(NM[0]);
            M = Integer.parseInt(NM[1]);

            pairs = new ArrayList[N];

            for (int i = 0; i < N; i++) {
                pairs[i] = new ArrayList<Pair>();
                weight = new ArrayList<Integer>();
            }

            for (int m = 0; m < M; m++) {
                String[] ABS = br.readLine().split(" ");
                int a = Integer.parseInt(ABS[0]);
                int b = Integer.parseInt(ABS[1]);
                int w = Integer.parseInt(ABS[2]);

                pairs[a].add(new Pair(b, w));
                pairs[b].add(new Pair(a, w));
                weight.add(w);
            }
//            String[] startEnd = br.readLine().split(" ");
//
//            int startNum = Integer.parseInt(startEnd[0]);
//            int endNum = Integer.parseInt(startEnd[1]);

            Collections.sort(weight);

            int ret = 10001;

            for (int i = 0; i < weight.size(); ++i) {
                int lo = (int) weight.get(i);
                int hi = 1000;
                int mid = 0;
                while (lo <= hi) {
                    mid = (lo + hi) / 2;
                    int w = (int) weight.get(i);
                    if (bfs(mid, w)) {
                        ret = Math.min(ret, mid - w);
                        hi = mid - 1;
                    } else {
                        lo = mid + 1;
                    }
                }
            }

            System.out.println("#" + t + " " + ret);
        }
    }

    private static boolean bfs (int hi,int lo) {
        LinkedList<Integer> queue = new LinkedList<>();
        ArrayList<Integer> visit = new ArrayList<>();

        for (int i = 0; i < 2001; i++) {
            visit.add(0);
        }
        queue.add(0);
        visit.add(0, 1);
        while (!queue.isEmpty()) {
            int here = queue.peekFirst();
            queue.pop();
            if (here == N - 1) {
                break;
            }
            for (int i = 0; i < pairs[here].size(); ++i) {
                int there = ((Pair) pairs[here].get(i)).start;
                int velocity = ((Pair) pairs[here].get(i)).end;
                if (velocity < lo || velocity > hi || visit.get(i) != 0) {
                    continue;
                }
                visit.add(there, 1);
                queue.add(there);
            }
        }
        if (visit.get(N-1) != 0) {
            return true;
        }
        return false;
    }
}
