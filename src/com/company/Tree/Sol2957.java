package com.company.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol2957 {

    static int[] binaryTree;
    static int count = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int inputCnt = Integer.parseInt(br.readLine());
        binaryTree = new int[300001];

        binaryTree[1] = Integer.parseInt(br.readLine());

        for (int i = 2; i < inputCnt + 1; i++) {
            int number = Integer.parseInt(br.readLine());
            insert(number, 1);
        }
    }

    private static void insert(int number, int node) {

        count = count + 1;

        if (number < binaryTree[node]) {
            if (binaryTree[node * 2] == 0) {
                binaryTree[node * 2] = number;
            } else {
                insert(number, node * 2);
            }
        } else if (number > binaryTree[node]) {
            if (binaryTree[node * 2 + 1] == 0) {
                binaryTree[node * 2 + 1] = number;
            } else {
                insert(number, node * 2 + 1);
            }
        }
    }
}
