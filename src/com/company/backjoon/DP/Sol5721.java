package com.company.backjoon.DP;

import java.util.Scanner;

public class Sol5721 {

    static int rowCnt;
    static int colCnt;
    static int xLine[];
    static int yLine[];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            rowCnt = sc.nextInt();
            colCnt = sc.nextInt();

            if (rowCnt == 0 && colCnt == 0) {
                return;
            } else {
                yLine = new int[rowCnt+1];

                for (int i = 1; i <= rowCnt; i++) {
                    xLine = new int[colCnt+1];
                    for (int k = 1; k <= colCnt; k++) {
                        xLine[k] = sc.nextInt();
                        if (k >= 2) {
                            xLine[k] = Math.max(xLine[k-2] + xLine[k], xLine[k-1]);
                        }
                    }

                    yLine[i] = xLine[colCnt];

                    if (i >= 2) {
                        yLine[i] = Math.max(yLine[i-2] + yLine[i], yLine[i-1]);
                    }
                }

                System.out.println(yLine[rowCnt]);
            }
        }
    }
}
