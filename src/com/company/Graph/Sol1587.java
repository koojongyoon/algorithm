package com.company.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol1587 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] AB = br.readLine().split(" ");

        int aCount = Integer.parseInt(AB[0]);
        int bCount = Integer.parseInt(AB[1]);
        int addLineCount = Integer.parseInt(br.readLine());

        int remainPoint = 0;

        for (int i = 0; i < addLineCount; i++) {
            String[] addLine = br.readLine().split(" ");
            int addedApoint = Integer.parseInt(addLine[0]);
            int addedBpoint = Integer.parseInt(addLine[1]);
            if (aCount%2 == 1 && bCount%2 == 1) {
                if (addedApoint % 2 == 1 && addedBpoint % 2 == 1) {
                    remainPoint = 1;
                }
            }
        }

        System.out.println(aCount/2 + bCount/2 + remainPoint);
    }
}
