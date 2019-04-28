package com.company.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol1011 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String[] XY = br.readLine().split(" ");
            int x = Integer.parseInt(XY[0]);
            int y = Integer.parseInt(XY[1]);

            int until = y-x;    // 차이만큼만 구해주자

            int k = 0;

            for (k = 0; k <= until; k++) {
                if (Math.pow(k, 2) > until) {
                    break;
                }
            }
            long res = 0;

            int n = k-1;

            if (Math.pow(n,2)+1 <= until  && until <= Math.pow(n,2) + n) {
                res = n*2;
            } else if (Math.pow(n+1,2) - (n+1) + 1 <= until && until <= Math.pow(n+1, 2)){
                res = (n+1)*2-1;
            } else if (Math.pow(n,2) == until) {
                res = n*2-1;
            }
            System.out.println(res);
        }
    }
}
