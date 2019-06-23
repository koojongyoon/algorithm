package com.company.backjoon.MakeCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol2577 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num1 = Integer.parseInt(br.readLine());
        int num2 = Integer.parseInt(br.readLine());
        int num3 = Integer.parseInt(br.readLine());
        long result = num1 * num2 * num3;
        String[] number = String.valueOf(result).split("");
        for (int i = 0; i < 10; i++) {
            int cnt = 0;
            for (int k = 0; k < number.length; k++) {
                if (i == Integer.parseInt(number[k])) {
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }
}
