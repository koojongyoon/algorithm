package com.company.backjoon.Uncategorized;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sol1159 {

    public static void main(String[] args) throws IOException {

        String sol = "";

        Map map = new HashMap<String, Integer>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());

        for (int i = 0; i < num; i++) {
            String[] name = br.readLine().split("");
            int count = map.get(name[0]) == null ? 0 : (int) map.get(name[0]);
            map.put(name[0], count + 1);
        }

        ArrayList alphabet = new ArrayList<String>();
        for (Object k : map.keySet()) {
            String key = k.toString();
            if ((int) map.get(key) > 4) {
                alphabet.add(key.toCharArray()[0]);
            }
        }

        Collections.sort(alphabet);
        for (Object k : alphabet) {
            String value = k.toString();
            sol = sol + value;

        }

        if (!sol.equals("")) {
            System.out.println(sol);
        } else {
            System.out.println("PREDAJA");
        }
    }
}
