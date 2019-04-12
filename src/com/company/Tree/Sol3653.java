package com.company.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Sol3653 {

    static int[] beforeMovieArray;
    static ArrayList afterMovieList;
    static ArrayList solve;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String[] NM = br.readLine().split(" ");

            int haveMovieCount = Integer.parseInt(NM[0]);
            int seeMovieCount = Integer.parseInt(NM[1]);

            beforeMovieArray = new int[haveMovieCount];
            afterMovieList = new ArrayList();
            solve = new ArrayList();

            String[] seeMovieOrder = br.readLine().split(" ");

            for (int k = 0; k < haveMovieCount; k++) {
                beforeMovieArray[k] = k+1;
            }

            for (int m = 0; m < seeMovieCount; m++) {
                int movieNum = Integer.parseInt(seeMovieOrder[m]);
                for (int n = 0; n < haveMovieCount; n++) {
                    if (movieNum == beforeMovieArray[n]) {
                        solve.add(n);
                        beforeMovieArray[n] = -1;
                        afterMovieList.add(movieNum);
                        for (int k = 0; k < haveMovieCount; k++) {
                            if (beforeMovieArray[k] > 0) {
                                afterMovieList.add(beforeMovieArray[k]);
                            }
                        }

                        for (int k = 0; k < afterMovieList.size(); k++) {
                            beforeMovieArray[k] = (int) afterMovieList.get(k);
                        }
                        afterMovieList.clear();
                    }

                }
            }

            for (int k = 0; k < solve.size(); k++) {
                System.out.print(solve.get(k) + " ");
            }

        }
    }
}
