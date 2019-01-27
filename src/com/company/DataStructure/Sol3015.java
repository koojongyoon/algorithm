package com.company.DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Created by koojongyun on 2018. 11. 17..
 */
public class Sol3015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int peopleCnt = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<Integer>();
        int count = 0;

        for(int i = 0; i < peopleCnt; i++) {
            int person = Integer.parseInt(br.readLine());

            if(stack.isEmpty()) {
                stack.push(person);
                continue;
            }
            count++;

            if(!stack.isEmpty() && stack.peek() < person) {
                while(!stack.isEmpty() && stack.peek() < person) {
                    stack.pop();
                }
                stack.push(person);
                count++;
            } else if(!stack.isEmpty() && stack.peek() > person){
                stack.push(person);
                count++;
            }
        }
        System.out.println(count);
    }
}
