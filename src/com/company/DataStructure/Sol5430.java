package com.company.DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by koojongyun on 2018. 11. 17..
 */
public class Sol5430 {
    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);

        int testCnt = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCnt; i++) {
            String[] command = br.readLine().split("");
            int numCnt = Integer.parseInt(br.readLine());

            String inputNumber = br.readLine();
            String[] inputString = inputNumber.substring(1, inputNumber.length()-1).split(",");

            int[] deque = new int[numCnt];

            for(int k = 0; k < inputString.length; k++) {
                if(inputString[k].equals("")) {
                    inputString[k] = "0";
                    deque = new int[0];
                } else {
                    deque[k] = Integer.parseInt(inputString[k]);
                }
            }

            int front = 0;
            int rear = deque.length - 1;
            String error = "not error";
            boolean isFront = true;

            for(int m = 0; m < command.length; m++) {
                switch (command[m]) {
                    case "R" :
                        isFront = !isFront;
                        break;
                    case "D" :
                        if(front > rear) {
                            error = "error";
                        }

                        if(isFront) {
                            front++;
                        } else {
                            rear--;
                        }
                        break;
                }
            }

            if(error.equals("error")) {
                System.out.println(error);
                continue;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("[");
            if(isFront) {
                int j = front;
                for(; j <= rear; j++) {
                    sb.append(deque[j]+",");
                }
            } else {
                int j = rear;
                for(; j >= front; j--) {
                    sb.append(deque[j]+",");
                }
            }
            String answer = sb.toString();

            if(answer.substring(0,answer.length()-1).length() > 0) {
                answer = answer.substring(0, answer.length()-1) + "]";
            } else {
                answer = "[]";
            }

            System.out.println(answer);
        }
    }
}
