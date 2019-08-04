package com.company.backjoon.Tree.Etc;

import java.io.*;
import java.util.ArrayList;

public class Sol2957 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int nodeCount = Integer.parseInt(br.readLine());
        int[] nodeDepth = new int[nodeCount+1];
        int insertCount = 0;

        ArrayList<Integer> nodeList = new ArrayList<>();

        for (int i = 0; i < nodeCount; i++) {
            insertCount = insertCount + getNodeDepth(Integer.parseInt(br.readLine()), nodeDepth, nodeList);
            bw.write(insertCount+"\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    private static int getNodeDepth(int node, int[] nodeDepth, ArrayList<Integer> nodeList) {
        int size = nodeList.size();
        int lowerBound = getLowerBound(0, size, node, nodeList);
        int left = lowerBound > 0 ? nodeDepth[nodeList.get(lowerBound - 1)] : 0;
        int right = lowerBound < size ? nodeDepth[nodeList.get(lowerBound)] : 0;

        nodeDepth[node] = Math.max(left, right) + 1;
        nodeList.add(lowerBound, node);

        return nodeDepth[node] - 1;
    }

    // 이진 탐색과 같은 방식으로 찾고자 하는 값(value)를 찾을때까지 반복문을 돌린다. (중간값을 찾음)
    // lower bound = 찾고자 하는 값보다 이상인 값.
    // upper bound = 찾고자 하는 값을 초과하는 값.
    private static int getLowerBound(int front, int rear, int value, ArrayList<Integer> nodeList) {
        while(front < rear) {
            int mid = (front + rear)/2;
            if (nodeList.get(mid) < value) {
                front = mid + 1;
            } else {
                rear = mid;
            }
        }
        return rear;
    }
}
