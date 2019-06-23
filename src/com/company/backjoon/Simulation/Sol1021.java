package com.company.backjoon.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Sol1021 {
    static int[] candidate;
    static int[] queue;
    static ConcurrentLinkedDeque deque;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NM = br.readLine().split(" ");
        int n = Integer.parseInt(NM[0]);
        int m = Integer.parseInt(NM[1]);

        deque = new ConcurrentLinkedDeque();
        for (int i = 1; i <= n; i++) {
            deque.add(i);
        }

        String[] numStr = br.readLine().split(" ");

        candidate = new int[m];

        for (int i = 0; i < m; i++) {
            candidate[i] = Integer.parseInt(numStr[i]);
        }
        int calculate = 0;

        for (int i = 0; i < m; i++) {
            int candidateNum = candidate[i];
            Iterator it = deque.iterator();
            int k = 0;

            while (it.hasNext()) {
                int dequeNum = (int) it.next();
                if (candidateNum != dequeNum) {
                    k++;
                } else {
                    break;
                }
            }

            int mid = deque.size()/2;

            while(true) {
                if ((int)deque.peekFirst() == candidateNum) {
                    deque.removeFirst();
                    break;
                }
                if (k > mid) {
                    calculate++;
                    deque.addFirst(deque.pollLast());
                } else {
                    calculate++;
                    deque.addLast(deque.pollFirst());
                }
            }
        }

        System.out.println(calculate);
    }
}
