package com.company.Tree;

import java.util.ArrayList;
import java.util.Scanner;

public class Sol2957 {

    static int[] height;
    static ArrayList<Integer> list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int inputCount = sc.nextInt();

        height = new int[inputCount+1];
        list = new ArrayList<>();

        int insertCount = 0;

        for (int i = 0; i < inputCount; i++) {
            insertCount = insertCount + getNodeDepth(sc.nextInt())-1;
            System.out.println(insertCount);
        }

        sc.close();
    }

    private static int getNodeDepth(int node) {
        int size = list.size();
        int lowerBound = getLowerBound(list, 0, size, node);
        int left = lowerBound > 0 ? height[list.get(lowerBound - 1)] : 0;
        int right = lowerBound < size ? height[list.get(lowerBound)] : 0;

        height[node] = Math.max(left, right) + 1;
        list.add(lowerBound, node);

        return height[node];
    }

    // 이진 탐색과 같은 방식으로 찾고자 하는 값(value)를 찾을때까지 반복문을 돌린다. (중간값을 찾음)
    private static int getLowerBound(ArrayList<Integer> list, int front, int rear, int value) {
        while(front < rear) {
            int mid = (front + rear)/2;
            if (list.get(mid) < value) {
                front = mid + 1;
            } else {
                rear = mid;
            }
        }
        return rear;
    }
}
