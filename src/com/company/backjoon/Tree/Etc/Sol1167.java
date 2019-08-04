package com.company.backjoon.Tree.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Sol1167 {

    static int numberCount;
    static ArrayList<Node>[] list;
    static int[] dist;
    static int diameter;
    static int farFrom;

    static class Node {
        int next;
        int weight;
        Node (int next, int weight) {
            this.next = next;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        numberCount = Integer.parseInt(br.readLine());

        list = new ArrayList[numberCount+1];
        dist = new int[numberCount+1];

        for (int i = 0; i <= numberCount; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < numberCount; i++) {
            String[] inputStr = br.readLine().split(" ");
            int number = Integer.parseInt(inputStr[0]);

            for (int k = 1; k < inputStr.length - 1; k++) {
                int next = Integer.parseInt(inputStr[k]);
                int weight = Integer.parseInt(inputStr[k + 1]);
                list[number].add(new Node(next, weight));
                list[next].add(new Node(number, weight));
                k++;
            }
        }

        diameter = 0;
        dfs(1, 0);

        diameter = 0;
        dist = new int[numberCount+1];
        dfs(farFrom, 0);

        System.out.println(diameter);

    }

    private static void dfs(int v, int d) {
        dist[v] = d;
        if (dist[v] > diameter) {
            diameter = dist[v];
            farFrom = v;
        }

        for (Node node : list[v]) {
            int next = node.next;
            int weight = node.weight;
            if (dist[next] == 0) {
                dfs(next, d+weight);
            }
        }
    }
}
