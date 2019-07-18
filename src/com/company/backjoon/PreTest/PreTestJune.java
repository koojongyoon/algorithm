package com.company.backjoon.PreTest;

import java.io.*;

public class PreTestJune {
    static long[][] shop;
    static long result;

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader(new File("PreTestJune.txt")));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            String[] NQ = br.readLine().split(" ");
            int N = Integer.parseInt(NQ[0]);
            int Q = Integer.parseInt(NQ[1]);
            shop = new long[5][N+1];
            result = 0;
            for (int q = 0; q < Q; q++) {
                String[] commandStr = br.readLine().split(" ");
                String command = commandStr[0];
                switch (command) {
                    case "1":
                            int x1 = Integer.parseInt(commandStr[1]);
                            int y1 = Integer.parseInt(commandStr[2]);
                            int k1 = Integer.parseInt(commandStr[3]);
                            int c1 = Integer.parseInt(commandStr[4]);
                            for (int i = x1; i <= y1; i++) {
                                shop[k1][i] = shop[k1][i] + c1;
                            }
                        break;
                    case "2":
                            int x2 = Integer.parseInt(commandStr[1]);
                            int c2 = Integer.parseInt(commandStr[2]);
                            long min = Long.MAX_VALUE;
                            for (int n = 1; n <=3; n++) {
                                if (min > shop[n][x2]) {
                                    min = shop[n][x2];
                                }
                            }
                            if (min < c2) {
                                for (int n = 1; n <=3; n++) {
                                    shop[n][x2] = shop[n][x2] - min;
                                }
                                shop[4][x2] = shop[4][x2] + min;
                            } else {
                                for (int n = 1; n <=3; n++) {
                                    shop[n][x2] = shop[n][x2] - c2;
                                }
                                shop[4][x2] = shop[4][x2] + c2;
                            }
                        break;
                    case "3":
                            int x3 = Integer.parseInt(commandStr[1]);
                            int y3 = Integer.parseInt(commandStr[2]);
                            for (int n = x3; n <= y3; n++) {
                                result = result + shop[4][n];
                            }
                        break;

                }
            }
            bw.write("#"+ t + " " + result);
            bw.write("\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
