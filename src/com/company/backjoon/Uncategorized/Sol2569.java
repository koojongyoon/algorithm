package com.company.backjoon.Uncategorized;

import java.util.Arrays;
import java.util.Scanner;

public class Sol2569 {

    static int[] originBox;
    static int[] sortBox;
    static int resultNumber;
    static int min = 9999999;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int boxCount = sc.nextInt();

        originBox = new int[boxCount];
        sortBox = new int[boxCount];

        for(int i = 0; i < boxCount; i++) {
            int inputNumber = sc.nextInt();
            originBox[i] = inputNumber;
            sortBox[i] = inputNumber;
        }

        Arrays.sort(sortBox);

        for(int i = 0; i < boxCount; i++) {
            if(originBox[i] != sortBox[i]) {
                if (min > sortBox[i]) {
                    min = sortBox[i];
                }
            }
        }

        for(int i = 0; i < boxCount; i++) {
            if (sortBox[i] != originBox[i] && min != sortBox[i]) {
                resultNumber = resultNumber + sortBox[i] + min;
            }
        }


        System.out.println(resultNumber);
    }
}
