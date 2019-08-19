package com.company.backjoon.Tree.Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol5052 {

    static class Node {
        int count;
        char data;
        Node[] next = new Node[10];

        Node(int count, char data) {
            this.count = count;
            this.data = data;
        }
    }

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            Node root = new Node(0, 'r');

            for  (int n = 1; n <= N; n++) {
                Node node = root;
                String inputChar = br.readLine();
                for (int k = 0; k < inputChar.length(); k++) {
                    char c = inputChar.charAt(k);
                    if (node.next[c-'0'] == null) {
                        node.count++;
                        node.next[c-'0'] = new Node(0, c);
                    }
                    node = node.next[c-'0'];
                }
            }
            result = 0;
            searchLeaf(root);
            if (result != N) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
    }
    private static void searchLeaf(Node node) {
        if (node.count == 0) {
            result++;
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (node.next[i] != null) {
                searchLeaf(node.next[i]);
            }
        }
    }
}
