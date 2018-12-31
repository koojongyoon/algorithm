package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by koojongyun on 2018. 11. 25..
 */
public class Sol1463 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int inputNumber = Integer.parseInt(br.readLine());

        int[] mem = new int[inputNumber+4];
        mem[0] = 0;
        mem[1] = 0;
        mem[2] = 1;
        mem[3] = 1;

        for(int i = 4; i < inputNumber+1; i++) {
            mem[i] = mem[i-1] + 1;
            if(i%2 == 0) {
                mem[i] = mem[i/2]+1 < mem[i] ? mem[i/2]+1 : mem[i];
            }
            if(i%3 == 0) {
                mem[i] = mem[i/3]+1 < mem[i] ? mem[i/3]+1 : mem[i];
            }
        }

        System.out.println(mem[inputNumber]);
    }
}
