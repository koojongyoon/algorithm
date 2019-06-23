package com.company.backjoon.DP;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by koojongyun on 2018. 11. 24..
 */
public class Sol2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int type = Integer.parseInt(st.nextToken());
        int value = Integer.parseInt(st.nextToken());

        int[] mem = new int[value+1];
        mem[0] = 1;

        for(int i = 0; i < type; i++) {
            int coin = Integer.parseInt(br.readLine());

            for(int k = coin; k <= value; k++) {
                mem[k] = mem[k] + mem[k-coin];
            }
        }

        System.out.println(mem[value]);

        br.close();
    }
}
