package com.company.backjoon.PreTest;

import java.io.*;

public class PreTestJune_SegmentTree2 {

    static class Shop {
        private int hat;
        private int top;
        private int trouser;
        int set;
        int setAccumulation;

        public int getHat() {
            return hat;
        }

        public void setHat(int hat) {
            this.hat = hat;
            this.calculationSetCount();
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
            this.calculationSetCount();
        }

        public int getTrouser() {
            return trouser;
        }

        public void setTrouser(int trouser) {
            this.trouser = trouser;
            this.calculationSetCount();
        }

        private void calculationSetCount() {
            this.set =  Math.min(Math.min(hat, top), trouser);
        }

        private void sellSet(int sellSetCount) {
            if (this.set >= sellSetCount) {
                this.hat = this.hat - sellSetCount;
                this.top = this.top - sellSetCount;
                this.trouser = this.trouser - sellSetCount;
                this.setAccumulation = this.setAccumulation + sellSetCount;
            } else {
                this.hat = this.hat - this.set;
                this.top = this.top - this.set;
                this.trouser = this.trouser - this.set;
                this.setAccumulation = this.setAccumulation + this.set;
                this.set = 0;
            }
        }
    }

    static private Shop[] segmentTree;
    static private Shop[] lazy;
    static private long accumulateRemain;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t < T; t++) {
            String[] NQ = br.readLine().split(" ");
            int N = Integer.parseInt(NQ[0]);
            int Q = Integer.parseInt(NQ[1]);

            segmentTree = new Shop[N*3];
            lazy = new Shop[N*3];

            for (int n = 1; n < N*3; n++) {
                segmentTree[n] = new Shop();
                lazy[n] = new Shop();
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

                        switch (productCode) {
                            case 1:
                                updateAddHat(startShopNum, endShopNum, 1, 1, N, supplyCount);
                                break;
                            case 2:
                                updateAddTop(startShopNum, endShopNum, 1, 1, N, supplyCount);
                                break;
                            case 4:
                                updateAddTrouser(startShopNum, endShopNum, 1, 1, N, supplyCount);
                                break;
                        }
                    case "2":
                        int shopNum = Integer.parseInt(queryStr[1]);
                        int setCount = Integer.parseInt(queryStr[2]);

//                         updateSubstractShop(shop, count);
                        break;
                    case "3":
                        startShopNum = Integer.parseInt(queryStr[1]);
                        endShopNum = Integer.parseInt(queryStr[2]);

//                        accumulateRemain = findAccumulateSellCount(startShopNum, endShopNum, 1, 1, N);
                        break;
                }
            }
        }

        br.close();
        bw.flush();
        bw.close();
    }

    private static void updateSubstractShop(int shop, int count) {

    }

    private static void updateAddHat(int startShopNum, int endShopNum, int shop,int left, int right, int supplyCount) {
        if (lazy[shop].hat != 0) {
            segmentTree[shop].setHat((right-left+1)*lazy[shop].hat);
            if (right != left) {
                lazy[shop*2].setHat(lazy[shop*2].hat + lazy[shop].hat);
                lazy[shop*2+1].setHat(lazy[shop*2+1].hat + lazy[shop].hat);
            }
            lazy[shop].setHat(0);
        }

        if (startShopNum > right || endShopNum < left) {
            return;
        }

        if (startShopNum <= left && endShopNum <= right) {
            segmentTree[shop].setHat((right-left+1) * supplyCount);
            if(left != right){
                lazy[shop*2].setHat(lazy[shop*2].hat + supplyCount);
                lazy[shop*2+1].setHat(lazy[shop*2+1].hat + supplyCount);
            }
            return;
        }

        int mid = (left + right) / 2;
        updateAddHat(startShopNum, endShopNum, shop*2, left, mid, supplyCount);
        updateAddHat(startShopNum, endShopNum, shop*2+1, mid + 1, right, supplyCount);

        segmentTree[shop].setHat(segmentTree[shop*2].getHat() + segmentTree[shop*2+1].getHat());
    }

    private static void updateAddTop(int startShopNum, int endShopNum, int shop,int left, int right, int supplyCount) {
        if (lazy[shop].top != 0) {
            segmentTree[shop].setTop((right-left+1)*lazy[shop].top);
            if (right != left) {
                lazy[shop*2].setTop(lazy[shop*2].top + lazy[shop].top);
                lazy[shop*2+1].setTop(lazy[shop*2+1].top + lazy[shop].top);
            }
            lazy[shop].setTop(0);
        }

        if (startShopNum > right || endShopNum < left) {
            return;
        }

        if (startShopNum <= left && endShopNum <= right) {
            segmentTree[shop].setHat((right-left+1) * supplyCount);
            if(left != right){
                lazy[shop*2].setTop(lazy[shop*2].top + supplyCount);
                lazy[shop*2+1].setTop(lazy[shop*2+1].top + supplyCount);
            }
            return;
        }

        int mid = (left + right) / 2;
        updateAddTop(startShopNum, endShopNum, shop*2, left, mid, supplyCount);
        updateAddTop(startShopNum, endShopNum, shop*2+1, mid + 1, right, supplyCount);

        segmentTree[shop].setTop(segmentTree[shop*2].getTop() + segmentTree[shop*2+1].getTop());
    }

    private static void updateAddTrouser(int startShopNum, int endShopNum, int shop,int left, int right, int supplyCount) {
        if (lazy[shop].trouser != 0) {
            segmentTree[shop].setTrouser((right-left+1)*lazy[shop].trouser);
            if (right != left) {
                lazy[shop*2].setTrouser(lazy[shop*2].trouser + lazy[shop].trouser);
                lazy[shop*2+1].setTrouser(lazy[shop*2+1].trouser + lazy[shop].trouser);
            }
            lazy[shop].setTrouser(0);
        }

        if (startShopNum > right || endShopNum < left) {
            return;
        }

        if (startShopNum <= left && endShopNum <= right) {
            segmentTree[shop].setTrouser((right-left+1) * supplyCount);
            if(left != right){
                lazy[shop*2].setTrouser(lazy[shop*2].trouser + supplyCount);
                lazy[shop*2+1].setTrouser(lazy[shop*2+1].trouser + supplyCount);
            }
            return;
        }

        int mid = (left + right) / 2;
        updateAddHat(startShopNum, endShopNum, shop*2, left, mid, supplyCount);
        updateAddHat(startShopNum, endShopNum, shop*2+1, mid + 1, right, supplyCount);

        segmentTree[shop].setTrouser(segmentTree[shop*2].getTrouser() + segmentTree[shop*2+1].getTrouser());
    }
}
