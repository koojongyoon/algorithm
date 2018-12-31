package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by koojongyun on 2018. 12. 15..
 */
public class Sol2572 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cardCount = Integer.parseInt(br.readLine());
        String[] cardList = br.readLine().split(" ");

        String[] townRoad = br.readLine().split(" ");
        int townCount = Integer.parseInt(townRoad[0]);
        int roadCount = Integer.parseInt(townRoad[1]);

        String[][] wayMap = new String[1002][1002];
        int[][] score = new int[1002][1002];
        boolean[][] isStartPoint = new boolean[1002][1002];

        for(int i=0; i<roadCount; i++)  {
            String[] roadColor = br.readLine().split(" ");
            wayMap[Integer.parseInt(roadColor[0])][Integer.parseInt(roadColor[1])] = roadColor[2];
            wayMap[Integer.parseInt(roadColor[1])][Integer.parseInt(roadColor[0])] = roadColor[2];
        }

        isStartPoint[0][1] = true;

        int currentScore;

        for (int i = 0; i < cardCount; i++)  {
            for (int j = 1; j <= townCount; j++) {
                if(isStartPoint[i][j]) {
                    for (int k = 1; k <= townCount; k++) {
                        if (wayMap[j][k] != null && wayMap[j][k].equals(cardList[i])) {
                            currentScore = score[i][j] + 10;
                        } else {
                            currentScore = score[i][j];
                        }

                        if (currentScore > score[i + 1][k]) {
                            score[i + 1][k] = currentScore;
                        }

                        isStartPoint[i + 1][k] = true;
                    }
                }
            }
        }

        int maxScore = 0;
        for(int i=0; i < townCount+1; i++) {
            System.out.println(score[cardCount][i]);
            maxScore = Math.max(maxScore, score[cardCount][i]);
        }

        System.out.println(maxScore);
    }
}
