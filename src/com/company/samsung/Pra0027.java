package com.company.samsung;

import java.io.*;

// 기출 P0027, 기름값, 13305
public class Pra0027 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int villageCount = Integer.parseInt(br.readLine());
        String[] roadLengthStr = br.readLine().split(" ");
        String[] litterValueStr = br.readLine().split(" ");

        int[] roadLength = new int[roadLengthStr.length];
        int[] litterValue = new int[roadLengthStr.length];
        long[] dp = new long[roadLengthStr.length];

        for (int i = 0; i < roadLengthStr.length; i++) {
            roadLength[i] = Integer.parseInt(roadLengthStr[i]);
        }

        for (int i = 0; i < litterValueStr.length-1; i++) {
            litterValue[i] = Integer.parseInt(litterValueStr[i]);
        }

        long MIN = Long.MAX_VALUE;
        long value = 0;

        for (int i = 0; i < roadLength.length; i++) {
            if (MIN > litterValue[i]) {
                MIN = litterValue[i];
            }
            value = MIN * roadLength[i] + value;
        }
        bw.write(String.valueOf(value));
        bw.flush();
        bw.close();
        br.close();
    }
}
