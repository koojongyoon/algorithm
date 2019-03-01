package com.company.SCC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Sol2150_new {

    static class Node {
        ArrayList<Integer> nextNode;

        Node (ArrayList<Integer> nextNode) {
            this.nextNode = nextNode;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] VE = br.readLine().split(" ");

        int v = Integer.parseInt(VE[0]);
        int e = Integer.parseInt(VE[1]);

        Node[] forward = new Node[e+1];
        Node[] reverse = new Node[e+1];

        for (int i = 1; i <= e; i++)  {
            forward[i] = new Node(new ArrayList<>());
            reverse[i] = new Node(new ArrayList<>());
        }

        for (int i = 0; i <= e; i++) {
            String[] input = br.readLine().split(" ");
            int currentNode = Integer.parseInt(input[0]);
            Integer.parseInt(input[1]);
        }
    }
}
