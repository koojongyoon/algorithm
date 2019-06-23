package com.company.backjoon.SCC;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

public class Sol2150 {

    static int v;
    static int e;

    static Node[] forward;
    static Node[] reverse;
    static boolean[] visited;
    static boolean[] reverseVisited;

    static Stack<Integer> stack;
    static ArrayList<Integer> scc;
    static ArrayList<ArrayList<Integer>> result;

    static class Node {
        ArrayList<Integer> nextNode;
        Node (ArrayList<Integer> nextNode) {
            this.nextNode = nextNode;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        initValueSet(br);

        for (int i = 1; i <= v; i++) {
            if (!visited[i]) {
                findScc(i);
            }
        }

        reverseFindScc();

        sortResult();

        output(bw);

        br.close();
        bw.flush();
        bw.close();
    }

    private static void initValueSet(BufferedReader br) throws IOException {

        String[] VE = br.readLine().split(" ");

        v = Integer.parseInt(VE[0]);
        e = Integer.parseInt(VE[1]);

        forward = new Node[e+1];
        reverse = new Node[e+1];

        for (int i = 1; i <= e; i++)  {
            forward[i] = new Node(new ArrayList<>());
            reverse[i] = new Node(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            String[] input = br.readLine().split(" ");
            int currentNode = Integer.parseInt(input[0]);
            int nextNode = Integer.parseInt(input[1]);
            forward[currentNode].nextNode.add(nextNode);
            reverse[nextNode].nextNode.add(currentNode);
        }

        visited = new boolean[v+1];
        stack = new Stack();
    }

    private static void findScc(int start) {
        visited[start] = true;
        for (int i = 0; i < forward[start].nextNode.size(); i++) {
            int number = forward[start].nextNode.get(i);
            if(!visited[number]) {
                findScc(number);
            }
        }
        stack.push(start);
    }

    private static void reverseFindScc() {
        result = new ArrayList<>();
        reverseVisited = new boolean[v+1];

        while(!stack.empty()) {
            scc = new ArrayList<>();
            int reverseStart = stack.pop();
            if(!reverseVisited[reverseStart]) {
                groupingScc(reverseStart);
                Collections.sort(scc);
                result.add(scc);
            }
        }
    }

    private static void groupingScc(int reverseStart) {
        scc.add(reverseStart);
        reverseVisited[reverseStart] = true;

        for (int i = 0; i < reverse[reverseStart].nextNode.size(); i++) {
            int number = reverse[reverseStart].nextNode.get(i);
            if (!reverseVisited[number]) {
                groupingScc(number);
            }
        }
    }

    private static void sortResult() {
        Collections.sort(result, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return o1.get(0) - o2.get(0);
            }
        });
    }

    private static void output(BufferedWriter bw) throws IOException {
        bw.write(result.size() + "\n");

        for (int i = 0; i < result.size(); i++) {
            for (int k = 0; k < result.get(i).size(); k++) {
                bw.write(result.get(i).get(k)+ " ");
            }
            bw.write("-1");
            bw.write(" \n");
        }
    }
}
