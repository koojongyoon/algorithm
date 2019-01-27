package com.company.Graph;

import java.io.*;
import java.util.ArrayList;
/**
 * Created by koojongyun on 2018. 12. 3..
 */
public class Sol2660 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int candidateCount = Integer.parseInt(br.readLine());
        int[][] map = new int[candidateCount+1][candidateCount+1];

        for(int i = 1; i < candidateCount+1; i++) {
            for(int k = 1; k < candidateCount+1; k++) {
                map[i][k] = 99999999;
            }
        }

        for(int i = 0; i < Integer.MAX_VALUE; i++) {
            String[] relation = br.readLine().split(" ");
            int first = Integer.parseInt(relation[0]);
            int second = Integer.parseInt(relation[1]);
            if (first == -1 && second == -1) {
                break;
            }
            map[first][second] = 1;
            map[second][first] = 1;
        }

        for (int k = 1; k < candidateCount+1; k++) {
            map[k][k] = 1;
            for(int i = 1; i < candidateCount+1; i++) {
                for(int j = 1; j < candidateCount+1; j++) {
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        int max[] = new int[candidateCount+1];
        int min = Integer.MAX_VALUE;

        for (int i = 1; i < candidateCount+1; i++) {
            for(int j = 1; j < candidateCount+1; j++) {
                max[i] = Math.max(map[i][j], max[i]);
            }
            min = Math.min(max[i], min);
        }

        int count = 0;

        ArrayList<Integer> idxList = new ArrayList<>();

        for(int i = 1; i < candidateCount+1; i++) {
            if(min == max[i]) {
                idxList.add(i);
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(min + " " + idxList.size());
        bw.newLine();

        StringBuilder sb = new StringBuilder();

        for (int idx: idxList) {
            sb.append(idx + " ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
