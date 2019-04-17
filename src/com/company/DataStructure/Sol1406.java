package com.company.DataStructure;

import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class Sol1406 {

    static String L = "L";
    static String D = "D";
    static String B = "B";
    static String P = "P";
    static int CURSOR_POSITION = 0;
    static LinkedList<Character> linkedList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        String sentence = br.readLine();
        CURSOR_POSITION = sentence.length();

        linkedList = new LinkedList<>();
        ListIterator<Character> listIterator = linkedList.listIterator();

        char[] sentenceCharacter = sentence.toCharArray();
        for (char c : sentenceCharacter) {
            listIterator.add(c);
        }

        int commandCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < commandCount; i++) {
            String[] inputStr = br.readLine().split(" ");
            String command = inputStr[0];

            if (command.equals(L)) {
                //제일 왼쪽칸이 아니면 커서의 위치를 하나 왼쪽으로 옮긴다.
                if (listIterator.hasPrevious()) {
                    listIterator.previous();
                }
            }

            if (command.equals(D)) {
                //제일 오른쪽칸이 아니면 커서의 위치를 하나 오른쪽으로 옮긴다
                if (listIterator.hasNext()) {
                    listIterator.next();
                }
            }

            if (command.equals(B)) {
                if (listIterator.hasPrevious()) {
                    listIterator.previous();
                    listIterator.remove();
                }
            }

            if (command.equals(P)) {
                listIterator.add(inputStr[1].charAt(0));
            }
        }

        for (char c : linkedList) {
            bw.write(c);
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
