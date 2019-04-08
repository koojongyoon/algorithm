package com.company.Recursive;

import java.util.Scanner;

public class Sol1019 {

    static int[] numArray;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        numArray = new int[10];
        int page = sc.nextInt();

        for (int i = 1; i <= page; i++) {
            String[] numStr = String.valueOf(i).split("");
            for (int k = 0; k < numStr.length; k++) {
                numArray[Integer.parseInt(numStr[k])] = numArray[Integer.parseInt(numStr[k])] + 1;
            }
        }

        for (int i = 0; i < numArray.length; i++) {
            System.out.print(numArray[i]+ " ");
        }
    }
}
