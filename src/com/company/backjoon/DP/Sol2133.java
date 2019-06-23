package com.company.backjoon.DP;

import java.io.*;

/**
 * Created by koojongyun on 2018. 12. 6..
 */
public class Sol2133 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int wall = Integer.parseInt(br.readLine());

        int[] mem = new int[wall+3];
        mem[0] = 1;
        mem[2] = 3;

        if(wall%2==1) {
            System.out.println("0");
            return;
        }

        for (int i = 4; i < wall + 1; i = i + 2) {
            mem[i] = 3 * mem[i-2];
            for (int j = 4; j <= i; j = j + 2) {
                mem[i] = mem[i] + 2 * mem[i-j];
            }
        }

        System.out.println(mem[wall]);

        br.close();
    }
}
