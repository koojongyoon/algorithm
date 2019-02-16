package com.company.Etc;

import java.io.*;

public class Sol11947 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) {
            String num = br.readLine();
            long upperNum = (long) Math.pow(10, num.length());
            long input = Long.parseLong(num);
            if (input > upperNum/2) {
                System.out.println(upperNum/2 * ((upperNum/2)-1));
            } else {
                System.out.println(input * (upperNum-1-input));
            }
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
