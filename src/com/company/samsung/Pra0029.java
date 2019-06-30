package com.company.samsung;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Pra0029 {
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader(new File("pra0029.txt")));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            Map playerMap = new HashMap<Integer, Integer>();
            int validXlength = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                String[] players = br.readLine().split(" ");
                int x = Integer.parseInt(players[0]);
                int y = Integer.parseInt(players[1]);
                if (playerMap.get(x) == null) {
                    playerMap.put(x, y);
                } else {
                    if ((Integer) playerMap.get(x) < y) {
                        playerMap.put(x, y);
                    }
                }
                if (x > validXlength) {
                    validXlength = x;
                }
            }
            long cases = 0;
            long min = 1000000001;
            for (int x = 1; x <= validXlength; x++) {
                int y = playerMap.get(x) == null ? 0 : (int) playerMap.get(x);
                if (y != 0 && y < min) {
                    min = y;
                }
                long until = y == 0 ? min : y;
                if (until == 1000000001) {
                    until = 1;
                }
                cases = cases + until - 1;
            }
            System.out.println("#" + t + " " + cases);
        }

    }
}
