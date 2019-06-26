package com.company.samsung;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Pra0001 {

    static int[] cards;
    static Set set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            int K = Integer.parseInt(br.readLine());
            cards = new int[N];
            set = new HashSet();
            for (int i = 0; i < N; i++) {
                cards[i] = Integer.parseInt(br.readLine());
            }

            permutation(cards, 0, N, K);

            bw.write("#" + t + " " + set.size());
            bw.write("\n");
            bw.flush();
        }

        bw.close();
        br.close();
    }

    private static void permutation (int[] arr, int depth, int n, int r) {
        if (depth == r) {
            addMem(arr, r);
            return;
        }

        for (int i = depth; i < n; i++) {
            swap(arr, depth, i);
            permutation(arr, depth + 1 , n, r);
            swap(arr, depth, i);
        }
    }

    private static void swap(int[] arr, int n1, int n2) {
        int temp = arr[n1];
        arr[n1] = arr[n2];
        arr[n2] = temp;
    }

    private static void addMem(int[] arr, int r) {
        String makeNum = "";
        for (int i = 0; i < r; i++) {
            makeNum = arr[i] + makeNum;
        }
        set.add(makeNum);
    }
}
