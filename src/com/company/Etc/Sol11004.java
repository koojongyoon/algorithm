package com.company.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol11004 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        int numCount = Integer.parseInt(input[0]);
        int num = Integer.parseInt(input[1]);
        long[] array = new long[numCount];

        StringTokenizer st = new StringTokenizer( br.readLine(), " ");

        for(int i = 0; i < numCount; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array);

        System.out.println(array[num-1]);
    }
}
