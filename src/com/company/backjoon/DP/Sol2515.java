package com.company.backjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sol2515 {

    static Picture[] pictures;
    static long dp[];
    static long max;

    static class Picture implements Comparable<Picture> {
        int height;
        int value;
        Picture (int height, int value) {
            this.height = height;
            this.value = value;
        }

        @Override
        public int compareTo(Picture o) {
            return this.height - o.height;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NS = br.readLine().split(" ");

        int N = Integer.parseInt(NS[0]);
        int S = Integer.parseInt(NS[1]);

        pictures = new Picture[N];
        dp = new long[N];

        for (int i = 0; i < N; i++) {
            String[] heightValue = br.readLine().split(" ");
            int height = Integer.parseInt(heightValue[0]);
            int value = Integer.parseInt(heightValue[1]);

            pictures[i] = new Picture(height, value);
        }

        Arrays.sort(pictures);


        dp[0] = pictures[0].value;
        for (int i = 0; i < pictures.length-1; i++) {
            if (pictures[i+1].height - pictures[i].height >= S) {
                dp[i+1] = dp[i] + pictures[i+1].value;
            } else {
                int max = 0;
                for (int s = 0; s < S; s++) {
                    if (i >= s) {
                        if (pictures[i + 1].height - pictures[i - s].height > S) {
                            if (pictures[i - s].value > max) {
                                max = pictures[i - s].value;
                            }
                        }
                    }
                }
                dp[i+1] = Math.max(dp[i], max);
            }
        }

        System.out.println(dp[N-1]);
    }
}
