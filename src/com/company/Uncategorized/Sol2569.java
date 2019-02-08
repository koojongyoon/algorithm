package com.company.Uncategorized;

import java.io.*;
import java.util.Arrays;

public class Sol2569 {
    static int[] origin;
    static int[] sort;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int loadCount = Integer.parseInt(br.readLine());
        origin = new int[loadCount];
        sort = new int[loadCount];

        for (int i = 0; i < loadCount; i++) {
            origin[i] = Integer.parseInt(br.readLine());
        }

        sort = Arrays.copyOf(origin, origin.length);
        Arrays.sort(sort);

        br.close();
        bw.flush();
        bw.close();
    }
}
