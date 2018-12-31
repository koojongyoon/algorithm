package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by koojongyun on 2018. 12. 8..
 */
public class ThreeLine {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        String[] peopleRelation = br.readLine().split(" ");
        int peopleCount = Integer.parseInt(peopleRelation[0]);
        int relationCount = Integer.parseInt(peopleRelation[1]);

        int[][] map = new int[peopleCount + 1][peopleCount + 1];

        for (int i = 0; i < relationCount; i++) {
            String[] relation = br.readLine().split(" ");

            map[Integer.parseInt(relation[0])][Integer.parseInt(relation[1])] = 1;
            map[Integer.parseInt(relation[1])][Integer.parseInt(relation[0])] = 1;
        }

        for(int i = 0; i < peopleCount+1; i++) {
            for (int j = 0; j < peopleCount+1; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
