package com.company.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by koojongyun on 2018. 12. 15..
 */
public class Sol2572 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int colorCount = Integer.parseInt(br.readLine());
        String[] inputColor = br.readLine().split(" ");
        String[] colorOrder = new String[colorCount+1];
        for (int i = 0; i < colorCount; i++) {
            colorOrder[i+1] = inputColor[i];
        }
        String[] townRoadColor = br.readLine().split(" ");

        int townCount = Integer.parseInt(townRoadColor[0]);
        int roadCount = Integer.parseInt(townRoadColor[1]);

        Integer.parseInt(townRoadColor[1]);
        String[][] map = new String[townCount+1][townCount+1];

        for(int i = 0; i < roadCount; i++) {
            String[] townRoad = br.readLine().split(" ");
            int start = Integer.parseInt(townRoad[0]);
            int end = Integer.parseInt(townRoad[1]);
            String color = townRoad[2];

            map[start][end] = color;
            map[end][start] = color;
        }

        int[][] scoreMap = new int[roadCount+1][townCount+1];

        for (int n = 1; n <= roadCount; n++) {
            for (int i = 1; i <= townCount; i++) {
                for (int k = 1; k <= townCount; k++) {
                    if (colorOrder[n].equals(map[i][k])) {
                        scoreMap[n][i] = Math.max(scoreMap[n-1][i] + 10, scoreMap[n][i]);
                    } else {
                        scoreMap[n][i] = Math.max(scoreMap[n-1][i], scoreMap[n][i]);
                    }
                }
            }
        }

        int maxScore = Integer.MIN_VALUE;
        for (int i = 1; i <= townCount; i++) {
            if (maxScore < scoreMap[roadCount][i]) {
                maxScore = scoreMap[roadCount][i];
            }
        }

        System.out.println(maxScore);
    }
}
