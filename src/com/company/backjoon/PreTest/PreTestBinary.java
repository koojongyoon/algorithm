package com.company.backjoon.PreTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class PreTestBinary {

    static String START_END = "101";
    static String MIDDLE = "01010";
    static Map<String, String> AGROUP;
    static Map<String, String> LGROUP;
    static Map<String, String> GGROUP;
    static Map<String, String> CGROUP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        initMapping();

        for (int i = 1; i <= testCase; i++) {
            String barcodeCandidate = br.readLine();    // 3000자가 입력됨
            String outputResult = "";
            ArrayList<String> correctBarcode = new ArrayList<>();
            for (int n = 0; n < 2; n++) {
                if(n == 1) {
                    String reverseBarcodeCandidate = "";
                    for(int t = barcodeCandidate.length(); t > 0; t--) {
                        reverseBarcodeCandidate = reverseBarcodeCandidate + barcodeCandidate.substring(t-1, t);
                    }
                    barcodeCandidate = reverseBarcodeCandidate;
                }
                for (int k = 0; k < barcodeCandidate.length() - 94; k++) {
                    if (barcodeCandidate.substring(k, k + 1).equals("1")) {
                        if (barcodeCandidate.substring(k, k + 3).equals(START_END)
                                && barcodeCandidate.substring(k + 92, k + 95).equals(START_END)
                                && barcodeCandidate.substring(k + 45, k + 50).equals(MIDDLE)) {

                            String candidate = barcodeCandidate.substring(k, k + 95);

                            String agroup = "";
                            String aGroupResult = "";
                            String bGroupResult = "";
                            String cGroupResult = "";

                            //C그룹
                            for (int j = 50; j < candidate.length() - 3; j = j + 7) {
                                String backGroup = candidate.substring(j, j + 7);
                                String cGroup = CGROUP.get(backGroup);
                                if (cGroup != null) {
                                    cGroupResult = cGroupResult + cGroup;
                                } else {
                                    break;
                                }
                            }

                            if (cGroupResult.length() != 6) {
                                continue;
                            }

                            //B그룹, A그룹
                            for (int j = 3; j < candidate.length() - 45; j = j + 7) {
                                String frontGroup = candidate.substring(j, j + 7);

                                String lGroup = LGROUP.get(frontGroup);
                                String gGroup = GGROUP.get(frontGroup);

                                if (lGroup != null) {
                                    agroup = agroup + "L";
                                    bGroupResult = bGroupResult + lGroup;
                                }
                                if (gGroup != null) {
                                    agroup = agroup + "G";
                                    bGroupResult = bGroupResult + gGroup;
                                }
                            }

                            if (agroup.length() != 6) {
                                continue;
                            } else {
                                if (AGROUP.get(agroup) == null) {
                                    continue;
                                } else {
                                    aGroupResult = AGROUP.get(agroup);
                                }
                            }

                            String validBarcode = aGroupResult + bGroupResult + cGroupResult;
                            int oddSum = 0;
                            int evenSum = 0;

                            for (int m = 0; m < validBarcode.length()-1; m++) {
                                if (m % 2 == 1) {
                                    oddSum = oddSum + Integer.parseInt(validBarcode.substring(m, m + 1) + "");
                                } else {
                                    evenSum = evenSum + Integer.parseInt(validBarcode.substring(m, m + 1) + "");
                                }
                            }

                            int checkValue = 10 - (evenSum + oddSum * 3) % 10;
                            int checkSum = checkValue == 10 ? 0 : checkValue;
                            if (Long.parseLong(validBarcode.substring(12, 13)) == checkSum) {
                                correctBarcode.add(validBarcode);
                            }
                        }
                    }
                    outputResult = makeOutput(correctBarcode);
                }
            }
            System.out.println("#" + i + " "+ correctBarcode.size() + " " + outputResult);
        }
    }

    private static String makeOutput(ArrayList<String> correctBarcode) {
        String output = "";
        Collections.sort(correctBarcode);
        for (int i = 0; i < correctBarcode.size(); i++) {
            output = output + correctBarcode.get(i)+ " ";
        }
        return output;
    }

    private static void initMapping() {
        getLgroup();
        getAgroup();
        getCGroup();
        getGGroup();
    }

    private static Map getLgroup() {
        LGROUP = new HashMap<>();
        LGROUP.put("0001101", "0");
        LGROUP.put("0011001", "1");
        LGROUP.put("0010011", "2");
        LGROUP.put("0111101", "3");
        LGROUP.put("0100011", "4");
        LGROUP.put("0110001", "5");
        LGROUP.put("0101111", "6");
        LGROUP.put("0111011", "7");
        LGROUP.put("0110111", "8");
        LGROUP.put("0001011", "9");
        return LGROUP;
    }

    private static Map getGGroup() {
        GGROUP = new HashMap<>();
        GGROUP.put("0100111", "0");
        GGROUP.put("0110011", "1");
        GGROUP.put("0011011", "2");
        GGROUP.put("0100001", "3");
        GGROUP.put("0011101", "4");
        GGROUP.put("0111001", "5");
        GGROUP.put("0000101", "6");
        GGROUP.put("0010001", "7");
        GGROUP.put("0001001", "8");
        GGROUP.put("0010111", "9");
        return GGROUP;
    }

    private static Map getCGroup() {
        CGROUP = new HashMap<>();
        CGROUP.put( "1110010","0");
        CGROUP.put( "1100110","1");
        CGROUP.put( "1101100","2");
        CGROUP.put( "1000010","3");
        CGROUP.put( "1011100","4");
        CGROUP.put( "1001110","5");
        CGROUP.put( "1010000","6");
        CGROUP.put( "1000100","7");
        CGROUP.put( "1001000","8");
        CGROUP.put( "1110100","9");
        return CGROUP;
    }

    private static Map getAgroup() {
        AGROUP = new HashMap<>();
        AGROUP.put("LLLLLL","0");
        AGROUP.put("LLGLGG","1");
        AGROUP.put("LLGGLG","2");
        AGROUP.put("LLGGGL","3");
        AGROUP.put("LGLLGG","4");
        AGROUP.put("LGGLLG","5");
        AGROUP.put("LGGGLL","6");
        AGROUP.put("LGLGLG","7");
        AGROUP.put("LGLGGL","8");
        AGROUP.put("LGGLGL","9");
        return AGROUP;
    }
}
