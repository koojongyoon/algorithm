package com.company.backjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sol2629 {

    static String Y = "Y";
    static String N = "N";
    static int weightNum;
    static int cache[][];
    static int[] weight;
    static int[] marble;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        weightNum = Integer.parseInt(br.readLine());
        String[] weightStr = br.readLine().split(" ");   //chus <= 30, weight <= 500

        weight = new int[weightNum+1];
        for (int i = 0; i < weightNum; i++) {
            weight[i] = Integer.parseInt(weightStr[i]);
        }

        int marbleNum = Integer.parseInt(br.readLine());
        String[] marbleStr = br.readLine().split(" ");    //confirmChus <= 7, WEIGHT <= 40000

        marble = new int[marbleNum];
        for (int i = 0; i < marbleNum; i++) {
            marble[i] = Integer.parseInt(marbleStr[i]);
        }

        cache = new int[31][15001];   //추의 갯수, 추의 갯수로 만들수 있는 무게

        for (int i = 0; i < 31; i++) {
            Arrays.fill(cache[i], -1);
        }

        preCalculation(0, 0);

        for (int i = 0; i < marbleNum; i++) {
            if (marble[i] > 15000) {
                System.out.print(N + " ");
            } else if (cache[weightNum][marble[i]] == 1) {
                System.out.print(Y + " ");
            } else {
                System.out.print(N + " ");
            }
        }
    }

    private static void preCalculation(int currentWeightNum, int currentWeight) {
        if (currentWeightNum > weightNum) {
            return;
        }

        int result = cache[currentWeightNum][currentWeight];

        if (result != -1) {
            return;
        }

        cache[currentWeightNum][currentWeight] = 1;

        //양팔 저울을 기준으로 왼쪽에 추를 올릴지, 아예 올리지 않을지, 오른쪽에 추를 올릴지 모든 경우의 수를 구해준다.
        preCalculation(currentWeightNum+1, currentWeight+weight[currentWeightNum]);
        preCalculation(currentWeightNum+1, currentWeight);
        preCalculation(currentWeightNum+1, Math.abs(currentWeight-weight[currentWeightNum]));
    }
}
