package com.company.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by koojongyun on 2018. 12. 14..
 */
public class Sol1003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        int[] zeroArr = new int[42];
        zeroArr[0] = 1;
        zeroArr[1] = 0;
        zeroArr[2] = 1;
        zeroArr[3] = 1;

        int[] oneArr = new int[42];
        oneArr[0] = 0;
        oneArr[1] = 1;
        oneArr[2] = 1;
        oneArr[3] = 2;

        for(int k = 4; k < 42; k++) {
            zeroArr[k] = zeroArr[k-1] + zeroArr[k-2];
            oneArr[k] = oneArr[k-1] + oneArr[k-2];
        }

        for(int i = 0; i < testCase; i++) {
            int fibonacci = Integer.parseInt(br.readLine());
            System.out.println(zeroArr[fibonacci]+ " " + oneArr[fibonacci]);
        }
    }
}
