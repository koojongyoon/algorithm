package com.company.Etc;

import java.io.*;

public class Sol11947 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) {
            String num = br.readLine();
            int numCount = num.length();

            String reverseNum = "";
            for (int k = 0; k < numCount; k++) {
                reverseNum = reverseNum + "9";
            }

            long reverse = Long.parseLong(reverseNum);
            long origin = Long.parseLong(num);
            long calcResult = reverse - origin;

            String originStr = origin + "";
            String calcResultStr = calcResult+ "";
            long finalValue = Long.parseLong(calcResultStr.substring(numCount - originStr.length(), numCount));

            bw.write(finalValue * origin+"\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
