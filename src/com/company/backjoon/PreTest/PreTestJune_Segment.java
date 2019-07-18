package com.company.backjoon.PreTest;

import java.io.*;

public class PreTestJune_Segment {

    static class Shop {
        int hat;
        int top;
        int trouser;
        long set = getPreparedSet();

        public Shop(int hat, int top, int trouser) {
            this.hat = hat;
            this.top = top;
            this.trouser = trouser;
        }

        private long getPreparedSet() {
            return Math.min(hat, Math.min(top, trouser));
        }
    }

    static private Shop[] segmentTree;
    static private Shop[] lazy;
    static private long accumulateRemain;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            String[] NQ = br.readLine().split(" ");
            int N = Integer.parseInt(NQ[0]);
            int Q = Integer.parseInt(NQ[1]);

            segmentTree = new Shop[N*3];
            lazy = new Shop[N*3];

            //init
            for (int n = 1; n < N*3; n++) {
                segmentTree[n] = new Shop(0, 0, 0);
                lazy[n] = new Shop(0, 0, 0);
            }

            for (int q = 0; q < Q; q++) {
                String[] queryStr = br.readLine().split(" ");
                String command = queryStr[0];
                int startShopNum = 0;
                int endShopNum = 0;

                switch (command) {
                    case "1" :
                        startShopNum = Integer.parseInt(queryStr[1]);
                        endShopNum = Integer.parseInt(queryStr[2]);
                        int productCode = Integer.parseInt(queryStr[3]);
                        int supplyCount = Integer.parseInt(queryStr[4]);

                        updateAddShop(startShopNum, endShopNum, 1, 1, N, productCode, supplyCount);

                        break;
                    case "2":
                        int shopNum = Integer.parseInt(queryStr[1]);
                        int setCount = Integer.parseInt(queryStr[2]);

                        //updateSubstractShop(shopNum, shopNum, 1, 1, N, setCount);
                        break;
                    case "3":
                        startShopNum = Integer.parseInt(queryStr[1]);
                        endShopNum = Integer.parseInt(queryStr[2]);

//                        accumulateRemain = findAccumulateSellCount(startShopNum, endShopNum, 1, 1, N);
                        break;
                }
            }
            bw.write("#"+t +" " + accumulateRemain);
        }

        br.close();
        bw.flush();
        bw.close();
    }

    private static void updateSubstractShop() {

    }

    private static void updateAddShop(int startShopNum, int endShopNum, int index,int left, int right, int productCode, int supplyCount) {
        if (productCode == 1) {
            if (lazy[index].hat != 0) {
                segmentTree[index].hat = (right-left+1)*lazy[index].hat;
                if (right != left) {
                    lazy[index*2].hat = lazy[index*2].hat + lazy[index].hat;
                    lazy[index*2+1].hat = lazy[index*2+1].hat + lazy[index].hat;
                }
                lazy[index].hat = 0;
            }
        }

        if (productCode == 2) {
            if (lazy[index].top != 0) {
                segmentTree[index].top = (right-left+1)*lazy[index].top;
                if (right != left) {
                    lazy[index*2].top = lazy[index*2].top + lazy[index].top;
                    lazy[index*2+1].top = lazy[index*2+1].top + lazy[index].top;
                }
                lazy[index].top = 0;
            }
        }

        if (productCode == 3) {
            if (lazy[index].trouser != 0) {
                segmentTree[index].trouser = (right-left+1)*lazy[index].trouser;
                if (right != left) {
                    lazy[index*2].trouser = lazy[index*2].trouser + lazy[index].trouser;
                    lazy[index*2+1].trouser = lazy[index*2+1].trouser + lazy[index].trouser;
                }
                lazy[index].trouser = 0;
            }
        }

        if (startShopNum > right || endShopNum < left) {
            return;
        }

        if (startShopNum <= left && endShopNum <= right) {
            if (productCode == 1) {
                segmentTree[index].hat = (right-left+1) * supplyCount;
                if(left != right){
                    lazy[index*2].hat = lazy[index*2].hat + supplyCount;
                    lazy[index*2+1].hat  = lazy[index*2+1].hat + supplyCount;
                }
                return;
            }
            if (productCode == 2) {
                segmentTree[index].top = (right-left+1) * supplyCount;
                if(left != right){
                    lazy[index*2].top = lazy[index*2].top + supplyCount;
                    lazy[index*2+1].top = lazy[index*2+1].top + supplyCount;
                }
                return;
            }
            if (productCode == 3) {
                segmentTree[index].trouser = (right-left+1) * supplyCount;
                if(left != right){
                    lazy[index*2].trouser = lazy[index*2].trouser + supplyCount;
                    lazy[index*2+1].trouser = lazy[index*2+1].trouser + supplyCount;
                }
                return;
            }
        }

        int mid = (left + right) / 2;
        updateAddShop(startShopNum, endShopNum, index*2, left, mid, productCode, supplyCount);
        updateAddShop(startShopNum, endShopNum, index*2+1, mid + 1, right, productCode, supplyCount);

        if (productCode == 1) {
            segmentTree[index].hat = segmentTree[index*2].hat + segmentTree[index*2+1].hat;
        }
        if (productCode == 2) {
            segmentTree[index].top = segmentTree[index*2].top + segmentTree[index*2+1].top;
        }
        if (productCode == 3) {
            segmentTree[index].trouser = segmentTree[index*2].trouser + segmentTree[index*2+1].trouser;
        }
    }

//    private static long findAccumulateSellCount(int shopStart, int shopEnd, int index, int left, int right) {
//        if(lazy[index].remain != 0){
//            segmentTree[index].remain = segmentTree[index].remain + (right-left+1) * lazy[index].remain;
//
//            if(right != left){
//                lazy[index*2].remain = lazy[index*2].remain + lazy[index].remain;
//                lazy[index*2+1].remain = lazy[index*2+1].remain + lazy[index].remain;
//            }
//
//            lazy[index].remain = 0;
//        }
//        if (shopStart > right || shopEnd < left) {
//            return 0;
//        }
//
//        if (shopStart <= left && right <= shopEnd) {
//            return segmentTree[index].remain;
//        }
//        int mid = (left + right) / 2;
//        return findAccumulateSellCount(shopStart, shopEnd, index * 2, left, mid) + findAccumulateSellCount(shopStart, shopEnd, index * 2+1, mid + 1, right);
//    }
}
