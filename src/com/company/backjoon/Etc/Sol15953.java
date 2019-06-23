package com.company.backjoon.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol15953 {

    static int[] firstYear = {0,500,300,300,200,200,200,50,50,50,50,30,30,30,30,30,10,10,10,10,10,10};
    static int[] secondYear = {0, 512, 256, 256, 128,128,128,128,64,64,64,64,64,64,64,64,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String[] th = br.readLine().split(" ");
            int first = Integer.parseInt(th[0]);
            int second = Integer.parseInt(th[1]);
            int firstPrice = 0;
            int secondPrice = 0;
            if (first <= 21) {
                firstPrice = firstYear[first];
            }
            if (second <= 31) {
                secondPrice = secondYear[second];
            }
            int price = (firstPrice + secondPrice) * 10000;
            System.out.println(price);
        }
    }
}
