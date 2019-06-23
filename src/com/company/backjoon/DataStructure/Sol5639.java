package com.company.backjoon.DataStructure;

import java.util.Scanner;

public class Sol5639 {

    static int[] treeArray;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        treeArray = new int[10005];
        int i = 0;
        while(sc.hasNextInt()) {
            treeArray[i++] = sc.nextInt();
        }
        recursive(0, i-1);
    }

    private static void recursive(int l, int r) {
        if (l > r) {
            return;
        }
        int root = l;
        int s = l;
        int e = r;
        while(treeArray[s] >= treeArray[root]) {
            s++;
        }
        while(treeArray[e] > treeArray[root]) {
            e--;
        }
        recursive(s, e);
        recursive(e+1, r);
    }
}
