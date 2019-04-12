package com.company.DP;

import java.io.*;

public class Sol7579 {

    static App[] appList;

    static class App implements Comparable<App> {
        int index;
        int memory;
        int cost;

        App (int index, int memory, int cost) {
            this.index = index;
            this.memory = memory;
            this.cost = cost;
        }

        @Override
        public int compareTo(App o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] countMemory = br.readLine().split(" ");

        int count = Integer.parseInt(countMemory[0]);
        long needMemory = Long.parseLong(countMemory[1]);
        appList = new App[count];

        String[] inputAppMemory = br.readLine().split(" ");
        String[] inputAppCost = br.readLine().split(" ");

        for (int i = 0; i < count; i++) {
            appList[i] = new App(i, Integer.parseInt(inputAppMemory[i]) , Integer.parseInt(inputAppCost[i]));
        }


    }

}
