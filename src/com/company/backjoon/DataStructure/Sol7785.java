package com.company.backjoon.DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Sol7785 {

    static String ENTER = "enter";

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int peopleCount = Integer.parseInt(br.readLine());

        HashSet<String> hashSet = new HashSet<>();

        for (int i = 0; i < peopleCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String command = st.nextToken();
            if (command.equals(ENTER)) {
                hashSet.add(name);
            } else {
                hashSet.remove(name);
            }
        }

        ArrayList list = new ArrayList(hashSet);

        Collections.sort(list);

        for (int i = list.size()-1; i >= 0; i--) {
            System.out.println(list.get(i));
        }
    }
}
