package com.company.backjoon.Tree.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Sol1068 {

    static ArrayList[] tree;
    static int deleteChildren, leaf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] inputStr = br.readLine().split(" ");

        tree = new ArrayList[N+1];

        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList();
        }
        int root = 0;

        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(inputStr[i]);
            if (parent != -1) {
                tree[parent].add(i);
            } else {
                root = i;
            }
        }

        deleteChildren = Integer.parseInt(br.readLine());

        if (root != deleteChildren) {
            deleteChild(root);
        }

        System.out.println(leaf);
    }

    private static void deleteChild(int deleteChild) {
        ArrayList children = tree[deleteChild];
        for (int i = 0; i < children.size(); i++) {
            int child = (int) tree[deleteChild].get(i);
            if (child == deleteChildren) {
                if (tree[deleteChild].size() == 1) {
                    leaf++;
                }
            } else {
                if (tree[child].size() == 0) {
                    leaf++;
                }
                deleteChild(child);
            }
        }
    }
}
