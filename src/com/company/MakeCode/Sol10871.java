package com.company.MakeCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol10871 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NX = br.readLine().split(" ");
        int N = Integer.parseInt(NX[0]);
        int X = Integer.parseInt(NX[1]);

        String[] arr = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int candidate = Integer.parseInt(arr[i]);
            if (candidate < X) {
                System.out.print(candidate + " ");
            }
        }
    }
}
