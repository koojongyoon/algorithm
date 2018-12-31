package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by koojongyun on 2018. 12. 8..
 */
public class Sol1654 {

    private static int K;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NK = br.readLine().split(" ");

        int N = Integer.parseInt(NK[0]);
        K = Integer.parseInt(NK[1]);

        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        long maxLength = binarySearch(1, arr[N-1]);

        System.out.println(maxLength);
    }

    private static long binarySearch(long min, long max) {

        if (max < min) {
            return max;
        }

        long mid = (min + max)/2;

        long count = 0;

        for(int i = 0; i < arr.length; i++) {
            count += arr[i] / mid;
        }

        if (K <= count) {
            return binarySearch(mid + 1, max);
        } else {
            return binarySearch(min, mid - 1);
        }
    }
}
