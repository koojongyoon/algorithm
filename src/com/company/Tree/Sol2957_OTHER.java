package com.company.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sol2957_OTHER {

    static int[] depthRecord;
    static int[] resultRecord;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int nodeCount = Integer.parseInt(br.readLine());
        initializeDepthRecord(nodeCount);
        initializeResultRecord(nodeCount);

        int firstNode = Integer.parseInt(br.readLine());
        depthRecord[firstNode] = 0;
        resultRecord[1] = depthRecord[firstNode];

        for (int i = 2; i < depthRecord.length; i++) {
            int node = Integer.parseInt(br.readLine());
            depthRecord[node] = findNodeDepth(node);
            resultRecord[i] = depthRecord[node];
        }

        int resultSum = 0;
        for (int i = 1; i < depthRecord.length; i++) {
            resultSum = resultSum + resultRecord[i];
            System.out.println(resultSum);
        }
    }

    private static int findNodeDepth(int nodeNumber) {
        int closedHigher = 0;
        int closedLower = 0;

        for (int i = nodeNumber; i > 0; i--) {
            if (depthRecord[i] >= 0) {
                closedLower = depthRecord[i];
                break;
            }
        }

        for (int i = nodeNumber; i < depthRecord.length; i++) {
            if ( depthRecord[i] >= 0) {
                closedHigher = depthRecord[i];
                break;
            }
        }

        if (closedLower >= closedHigher) {
            depthRecord[nodeNumber] = closedLower + 1;
            return closedLower + 1;
        } else {
            depthRecord[nodeNumber] = closedHigher + 1;
            return closedHigher + 1;
        }
    }

    private static void initializeDepthRecord(int inputCount) {
        depthRecord = new int[inputCount + 1];
        Arrays.fill(depthRecord, -1);
    }

    private static void initializeResultRecord(int inputCount) {
        resultRecord = new int[inputCount + 1];
    }
}
