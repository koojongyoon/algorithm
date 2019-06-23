package com.company.backjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.kdata.or.kr/info/info_04_view.html?field=&keyword=&type=techreport&page=5&dbnum=188735&mode=detail&type=techreport
public class Sol10802 {

    static int REMAIN = 20150523;
    static int ans1;
    static int ans2;
    static int[] A;
    static int[] B;
    static int[] C;
    static int[] D;
    static int[] X;
    static int[] Num;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] startEnd = br.readLine().split(" ");

        A = new int[100005];
        B = new int[100005];
        C = new int[100005];
        D = new int[100005];
        X = new int[100005];
        Num = new int[100005];

        String start = startEnd[0];
        String end = startEnd[1];

        makeTable(end.length());

        int i;

        for (i = 0; i < start.length(); i++) {
            Num[i+1] = start.charAt(i) - '0';
        }

        for (i = start.length(); Num[i] == 0; i--) {
            Num[i] = 9;
        }
        Num[i]--;

        ans1 = calc(start.length());

        for (i = 0; i < end.length(); i++) {
            Num[i+1] = end.charAt(i) - '0';
        }

        ans2 = calc(end.length());
        System.out.println((ans2 - ans1 + REMAIN) % REMAIN) ;
    }

    private static int calc(int length) {
        int i;
        int j;
        int k;
        int s = 0;
        int s2 = 0;
        int sum = 0;
        int to;

        for (i = 1; i <=length; i++) {
            if (Num[i] == 3 || Num[i] == 6 || Num[i] == 9) {
                break;
            }
        }

        k = i;

        to = Math.min(k, length);

        for(i = 1; i <= to; i++) {
            for (j = 0; j < Num[i]; j++) {
                if (j == 3 || j==6 || j==9) {
                    s = s + X[length - i];
                } else {
                    s = s+ A[length -i];
                }
                s = s % REMAIN;
            }
        }

        for (i = 1; i <= to; i++) {
            for (j = 0; j < Num[i]; j++) {
                if (j == 3 || j == 6 || j == 9) {
                    continue;
                }
                if ((sum + j) % 3 == 0) {
                    s = s + C[length-i];
                } else {
                    s = s+ D[length-i];
                }
                s = s % REMAIN;
            }
            sum = (sum + Num[i]) % 3;
        }

        s--;

        for (j = k+1; j <= length; j++) {
            s2 = (s2*10+Num[j]) % REMAIN;
        }

        s = s + s2;

        if (k <= length || sum %3 == 0) {
            s++;
        }
        return s%REMAIN;

    }

    private static void makeTable(int length) {
        B[0] = 1;
        for (int i = 1; i <= length; i++) {
            A[i] = (A[i-1]*10 + B[i-1]*3) % REMAIN;
            B[i] = (B[i-1]*7) % REMAIN;
        }

        C[0] = 1;
        for (int i = 1; i <= length; i++) {
            C[i] = (C[i-1]*1 + D[i-1]*6) % REMAIN;
            D[i] = (C[i-1]*3 + D[i-1]*4) % REMAIN;
        }

        X[0] = 1;
        for (int i = 1; i <= length; i++) {
            X[i] = (X[i-1]*10) % REMAIN;
        }
    }
}
