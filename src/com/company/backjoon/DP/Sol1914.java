package com.company.backjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Created by koojongyun on 2018. 12. 7..
 */
public class Sol1914 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int topCount = Integer.parseInt(br.readLine());

        BigInteger two = new BigInteger("2");
        System.out.println(two.pow(topCount).subtract(BigInteger.valueOf(1)));

        if (topCount == 1) {
            move(1, 3);
            return;
        }

        if (topCount < 21) {
            hanoi(topCount, 1, 2, 3);
        }
    }

    private static void hanoi(int topCount, int from, int by, int to) {
        if(topCount ==1) {
            move(from, to);
        } else {
            hanoi(topCount-1, from, to, by);
            move(from, to);
            hanoi(topCount-1, by, from, to);
        }
    }

    private static void move(int fromStick, int toStick) {
        System.out.println(fromStick + " " + toStick);
    }
}
