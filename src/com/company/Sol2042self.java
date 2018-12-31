package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by koojongyun on 2018. 12. 31..
 */
public class Sol2042self {
    static long[] inputArray;
    static long[] treeArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NMK = br.readLine().split(" ");

        int numberCount = Integer.parseInt(NMK[0]);
        int changeCount = Integer.parseInt(NMK[1]);
        int boundaryCount = Integer.parseInt(NMK[2]);

        inputArray = new long[numberCount];

        for (int i = 0; i < numberCount; i++) {
            inputArray[i] = Integer.parseInt(br.readLine());
        }

        treeArray = new long[4 * numberCount];

        initTree(1, 0, numberCount-1);

        for(int i = 0; i < treeArray.length; i++) {
            System.out.print(treeArray[i]+" ");
        }

        for (int i = 0; i < changeCount + boundaryCount; i++) {
            String[] abc = br.readLine().split(" ");
            int command = Integer.parseInt(abc[0]);

            if (command == 1) {
                int targetNumber = Integer.parseInt(abc[1]);
                int updateNumber = Integer.parseInt(abc[2]);
                int updateIndex = targetNumber - 1;
                long diff = updateNumber - inputArray[updateIndex];
                inputArray[updateIndex] = updateNumber ;

                update(1, 0, numberCount - 1, updateIndex, diff);
            } else {
                int startIndex = Integer.parseInt(abc[1]);
                int endIndex = Integer.parseInt(abc[2]);

                System.out.println(sum(1, 0, numberCount-1, startIndex-1, endIndex-1));
            }
        }
    }

    private static long initTree(int index, int start, int end) {
        if(start == end) {
            return treeArray[index] = inputArray[start];
        } else {
            return treeArray[index] = initTree(index*2, start, (start+end)/2) + initTree(index*2+1, (start+end)/2+1, end);
        }
    }

    private static long sum(int index, int nodeStart, int nodeEnd, int findLeft, int findRight) {
        if(nodeStart > findRight || nodeEnd < findLeft) {
            return 0;
        }

        if(findLeft <= nodeStart && findRight >= nodeEnd) {
            return treeArray[index];
        }

        return sum(index*2, nodeStart, (nodeStart+nodeEnd)/2, findLeft, findRight)
                +sum(index*2+1, (nodeStart+nodeEnd)/2 + 1, nodeEnd, findLeft, findRight);
    }

    private static long update(int index, int nodeStart, int nodeEnd, int updateIndex, long diff) {
        if(updateIndex < nodeStart || updateIndex > nodeEnd) {
            return 0;
        }

        treeArray[index] = treeArray[index] + diff;

        return update(index*2, nodeStart, (nodeStart+nodeEnd)/2, updateIndex, diff);
    }

}
