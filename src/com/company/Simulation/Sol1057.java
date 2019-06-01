package com.company.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol1057 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NKI = br.readLine().split(" ");
        int N = Integer.parseInt(NKI[0]);
        int K = Integer.parseInt(NKI[1]);
        int I = Integer.parseInt(NKI[2]);

        if (N < K || N < I) {
            System.out.println("-1");
            return;
        }

        int meet = 0;

        while (K != I) {
            K = (K + 1) / 2;
            I = (I + 1) / 2;
            meet++;
        }

        System.out.println(meet);
    }
}
