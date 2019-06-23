package com.company.backjoon.DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Sol2493 {
    static class Mem {
        int index;
        int high;

        Mem(int index, int high) {
            this.index = index;
            this.high = high;
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);

        int topCnt = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int index = 0;
        Stack<Mem> stack = new Stack<>();

        int[] sol = new int[st.countTokens()];

        while(st.hasMoreTokens()) {
            Mem currentTop = new Mem(index++, Integer.parseInt(st.nextToken()));

            if (stack.isEmpty()) {
                sol[index-1] = 0;
                stack.push(currentTop);
                continue;
            }

            if (!stack.empty() && stack.peek().high < currentTop.high) {

                while(!stack.empty() && currentTop.high > stack.peek().high) {
                    stack.pop();
                }

                if(stack.isEmpty()) {
                    sol[index-1] = 0;
                } else {
                    sol[index-1] = stack.peek().index +1;
                }
                stack.push(currentTop);
            } else {
                sol[index-1] = stack.peek().index + 1;
                stack.push(currentTop);
            }
        }

        for(int i = 0; i < sol.length; i++) {
            System.out.print(sol[i]+ " ");
        }
    }
}
