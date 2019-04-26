package com.company.DP;

import java.io.*;

public class Sol7579 {
    static int[] memory;
    static int[] cost;
    static int[] appMemory;
    static int sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] countMemory = br.readLine().split(" ");

        int count = Integer.parseInt(countMemory[0]);
        long needMemory = Long.parseLong(countMemory[1]);

        String[] inputAppMemory = br.readLine().split(" ");
        String[] inputAppCost = br.readLine().split(" ");
        memory = new int[count];
        cost = new int[count];
        appMemory = new int[10001];

        for (int i = 0; i < count; i++) {
            memory[i] = Integer.parseInt(inputAppMemory[i]);
            cost[i] = Integer.parseInt(inputAppCost[i]);
            sum = sum + cost[i];
        }

        for (int i = 0; i < count; i++) {
            for (int j = sum; j >= cost[i]; j--) {
                appMemory[j] = Math.max(appMemory[j], appMemory[j - cost[i]] + memory[i]);
            }
        }

        int i = 0;
        for (i = 0; i < sum && appMemory[i] < needMemory; i++); {
            System.out.println(i);
            return;
        }
    }
}
