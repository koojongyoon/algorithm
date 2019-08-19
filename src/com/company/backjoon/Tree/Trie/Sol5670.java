package com.company.backjoon.Tree.Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol5670 {
    static int SIZE = 26;   //숫자는 10으로 하고 알파벳은 26으로 한다.
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while((line = br.readLine()) != null) {
            Trie trie = new Trie();
            int N = Integer.parseInt(line);
            String[] str = new String[N];

            for (int i = 0; i < N; i++) {
                str[i] = br.readLine();
                trie.insert(str[i]);
            }

            for (int i = 0; i < SIZE; i++) {
                if (trie.root.children[i] != null) {
                    trie.check(trie.root.children[i], 1);
                }
            }

            System.out.printf("%.2f", (double) count/N);
            System.out.println();
            count = 0;
        }
    }

    private static class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        private int toNumber(char c) {
            return c-'a';
        }

        void insert(String key) {
            int length = key.length();
            TrieNode currentNode = root;
            for (int i = 0; i < length; i++) {
                int next = toNumber(key.charAt(i));
                if (currentNode.children[next] == null) {
                    currentNode.children[next] = new TrieNode();
                    currentNode.nChild++;
                }
                currentNode = currentNode.children[next];
            }
            currentNode.isTerminal = true;
        }

        void check(TrieNode node, int result) {
            if (node.isTerminal) {
                count = count + result;
            }
            if (node.nChild >= 2) {
                result++;
            }
            if (node.isTerminal && node.nChild == 1) {
                result++;
            }
            for (int i = 0; i < SIZE; i++) {
                if (node.children[i] != null) {
                    check(node.children[i], result);
                }
            }
        }

    }

    private static class TrieNode {
        TrieNode[] children = new TrieNode[SIZE];
        boolean isTerminal;
        int nChild = 0;
        TrieNode() {
            isTerminal = false;
            for (int i = 0; i < SIZE; i++) {
                children[i] = null;
            }
        }
    }
}
