package com.company.backjoon.PreTest;

import java.io.*;
import java.util.*;

public class PreTestFebrary3 {

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) {
            String[] cardMethod = br.readLine().split(" ");
            int cardCount = Integer.parseInt(cardMethod[0]);
            long targetNum = Long.parseLong(cardMethod[1]);

            long[] cardDec = new long[cardCount];
            String[] cardStr = br.readLine().split(" ");

            for (int k = 0; k < cardCount; k++) {
                cardDec[k] = Long.parseLong(cardStr[k]);
            }

            int left = cardCount / 2;
            List<Long> leftList = new ArrayList<>();
            for (int k = 0; k < (1<<left); k++) { // 왼쪽 리스트의 사이즈의 2배만큼 리스트의 크기를 만들어 순환한다.
                long sum = 0;
                for (int j = 0; j < left; j++) {    // 0부터 리스트 카드갯수의 반만큼 순환한다
                    if ((k & (1 << j)) > 0) {       // 원소를 포함 하는지 j만큼 이동하며 0보다 크면
                        sum = sum + cardDec[j];     // 입력된 카드값의 왼쪽 값과 sum값을 더한다
                    }
                }
                leftList.add(sum);                  // leftList에 값을 더한다
            }
            Collections.sort(leftList);

            int right = cardCount - left;
            List<Long> rightList =  new ArrayList<>();
            for (int k = 0; k < (1<<right); k++) { // 왼쪽 리스트의 사이즈의 2배만큼 리스트의 크기를 만들어 순환한다.
                long sum = 0;
                for (int j = 0; j < right; j++) {    // 0부터 리스트 카드갯수의 반만큼 순환한다
                    if ((k & (1 << j)) > 0) {       // 원소를 포함 하는지 j만큼 이동하며 0보다 크면
                        sum = sum + cardDec[left+j];     // 입력된 카드값의 왼쪽 값과 sum값을 더한다
                    }
                }
                rightList.add(sum);                  // leftList에 값을 더한다
            }
            Collections.sort(rightList);

            int L = 0;                              // 가장 왼쪽 인덱스
            int R = rightList.size() - 1;           // 오른쪽 리스트의 갯수
            int LSize = leftList.size() - 1;        // 왼쪽 리스트의 갯수
            long result = 0;

            while (L <= LSize && R >= 0) {          // 왼쪽 인덱스가 왼쪽 리스트의 갯수보다 작고 오른쪽 리스트의 갯수가 0보다 크면 반복문 수행
                long leftValue = leftList.get(L);   // 왼쪽 리스트의 왼쪽 인덱스에 있는 값 GET
                long rightValue = rightList.get(R); // 오른쪽 리스트의 오른쪽 인덱스에 있는 값 GET
                long sum = leftValue + rightValue;  // 리스트의 인덱스에 해당하는 값 꺼내서 Sum값으로 만들기
                if (sum < targetNum) {              // 찾는값이 더한값의 합보다 크면
                    L++;                            // 왼쪽 리스트의 index + 1
                } else if (sum > targetNum) {       // 찾는값이 더한값의 합보다 작으면
                    R--;                            // 오른쪽 리스트의 index - 1
                } else {                            // 찾는값과 합의 값이 같으면
                    long leftCheck = 0;
                    long rightCheck = 0;

                    while ((L <= LSize) && (leftValue == leftList.get(L))) {   //왼쪽 인덱스가 왼쪽 리스트 사이즈보다 작고 왼쪽 리스트값이 왼쪽 인덱스의 값과 같으면
                        L++;                        // 왼쪽 리스트 인덱스 증가
                        leftCheck++;                // leftCheck + 1 ;
                    }

                    while ((R >= 0) && (rightValue == rightList.get(R))) {      //오른쪽 인덱스가 0보다 크고 오른쪽 리스트값이 오른쪽 인덱스에서 꺼낸값과 같으면
                        R--;                        // 오른쪽 리스트 인덱스 증가
                        rightCheck++;               // rightCheck + 1;
                    }
                    result = result + (leftCheck * rightCheck); // result = result + (leftCheck+rightCheck)
                }
            }

            if (targetNum == 0) {  //찾는값이 0이면
                result = result -1;              // result - 1
            }

            System.out.println(result);
        }
    }

}
