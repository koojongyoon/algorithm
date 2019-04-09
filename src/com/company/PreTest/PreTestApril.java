package com.company.PreTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PreTestApril {

    static String[][] map;

    static String S = "S";
    static String N = "N";
    static String W = "w";
    static String E = "e";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new String[5][5];

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int carCount = Integer.parseInt(br.readLine());
            for (int k = 0; k < carCount; k++) {
                String[] mapInput = br.readLine().split(" ");
                int x = Integer.parseInt(mapInput[0]);
                int y = Integer.parseInt(mapInput[1]);
                String direction = mapInput[2];
                fillMap(x, y, direction);
            }

        }
    }

    private static void fillMap(int x, int y, String direction) {
        if (direction.equals(N)) {
            map[x][y] = direction;
            map[x][y-1] = direction;
        }
        if (direction.equals(S)) {
            map[x][y] = direction;
            map[x][y+1] = direction;
        }
        if (direction.equals(E)) {
            map[x][y] = direction;
            map[x][x-1] = direction;
        }
        if (direction.equals(W)) {
            map[x][y] = direction;
            map[x][y+1] = direction;
        }
    }
}
