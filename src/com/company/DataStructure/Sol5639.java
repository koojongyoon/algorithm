package com.company.DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Sol5639 {

    static int[] treeArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        treeArray = new int[10005];
        int i = 0;
        String input;
        while((input = br.readLine()) != null) {
            treeArray[i++] = Integer.parseInt(input);
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
        System.out.println(treeArray[root]);
    }
}
