package com.company.backjoon.Etc;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Sol1927 {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for (int idx = 0; idx < N; idx++) {
            int x = sc.nextInt();
            if (x == 0) {
                if (queue.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(queue.poll());
                }
            } else {
                queue.offer(x);
            }
        }
    }
}
