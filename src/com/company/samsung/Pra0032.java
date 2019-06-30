package com.company.samsung;

import java.io.*;
import java.util.Arrays;

public class Pra0032 {
    static int[] colorPaper;
    static int N;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader(new File("pra0032.txt")));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            String[] NK = br.readLine().split(" ");
            N = Integer.parseInt(NK[0]);
            K = Integer.parseInt(NK[1]);
            String[] colorPaperStr = br.readLine().split(" ");

            initializeFirstLastColorPaper(colorPaperStr);

            // 1) 0의 앞 뒤로 색깔이 같은 경우 같은 색깔로 채운다.
            fillFrontLastSameNumber();

           int result = getResultLongestColorPaper();
            bw.write("#" + t + " " + result);
            bw.write("\n");
            bw.flush();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void fillFrontLastSameNumber() {
        for (int i = 0; i < N; i++) {
            if (colorPaper[i] == 0) {
                int previousColor = colorPaper[i-1];
                for (int k = i; k < N; k++) {
                    if (colorPaper[k] != 0) {
                        if (previousColor == colorPaper[k] ) {
                            // 0의 앞뒤로 색깔이 같은 경우
                            Arrays.fill(colorPaper, i, k-1, previousColor);
                            break;
                        } else {
                            // 0의 앞,뒤로 색깔이 다른 경우

                            break;
                        }
                    }
                }
            }
        }
    }

    private static void initializeFirstLastColorPaper(String[] colorPaperStr) {
        colorPaper = new int[N];
        for (int i = 0; i < N; i++) {
            colorPaper[i] = Integer.parseInt(colorPaperStr[i]);
        }
        if (colorPaper[0] == 0) {
            colorPaper[0] = 1;
        }

        if (colorPaper[N -1] != K) {
            colorPaper[N -1] = K;
        }
    }

    //가장 많은 색상값이 무엇인지???
    private static int getResultLongestColorPaper() {
        // 가장 많은 컬러값 도출
        int result = 0;
        int previousColor = colorPaper[0];
        int tempResult = 1;
        for (int i = 1; i < N; i++) {
            int currentColorPaper = colorPaper[i];
            if (previousColor == currentColorPaper) {
                tempResult++;
            } else {
                tempResult = 1;
            }
            if (result < tempResult) {
                result = tempResult;
            }
            previousColor = currentColorPaper;
        }
        return result;
    }
}
