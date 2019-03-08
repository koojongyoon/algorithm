package com.company.PreTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PreTestMarch {

    static ArrayList<Integer> usedPan; //최대로 사용될 판의 갯수
    static ArrayList<Pan> materialPan;
    static ArrayList<Pan> questionPan;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {

            int panCnt = Integer.parseInt(br.readLine());

            materialPan = new ArrayList<>();
            for (int m = 1; m <= panCnt; m++) {
                char[][] tmpPan = new char[8][8];
                for (int k = 0; k < 8; k++) {
                    char[] panRow = br.readLine().toCharArray();
                    tmpPan[k] = panRow;
                }
                materialPan.add(new Pan(tmpPan));
            }

            int Q = Integer.parseInt(br.readLine());

            questionPan = new ArrayList<>();
            for (int m = 1; m <= Q; m++) {
                char[][] tmpPan = new char[8][8];
                for (int k = 0; k < 8; k++) {
                    char[] panRow = br.readLine().toCharArray();
                    tmpPan[k] = panRow;
                }
                questionPan.add(new Pan(tmpPan));
            }

            usedPan = new ArrayList<>();
            for (int j = 1; j <= Q; j++) {
                solve(materialPan, questionPan);
            }
        }
    }

    private static void solve(ArrayList<Pan> materialPan, ArrayList<Pan> questionPan) {
        materialPan.get(i)

    }

    private static boolean compareRow(char[] row, char[] rowForQ) {
        for (int i = 0; i < row.length; i++) {
            if(row[i] != rowForQ[i]) {

            }
        }
    }

    private static class Pan {
        static char[][] pan;

        Pan (char[][] pan) {
            this.pan = pan;
        }
    }
}
