package com.company.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        int N = Integer.parseInt(br.readLine());
        Heap heap = new Heap(N);

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                System.out.println(heap.removeNode());
            } else {
                heap.addNode(num);
            }
        }
    }

    private static class Heap {
        int heap[];
        int size = 0;

        public Heap(int n) {
            heap = new int[n];
        }

        public void addNode(int newInt) {
            heap[size] = newInt;
            int p = size;
            size++;
            while(p > 0 && heap[p] < heap[(p-1)/2]) {
                int temp = heap[p];
                heap[p] = heap[(p-1)/2];
                heap[(p-1)/2] = temp;
                p = (p-1)/2;
            }
        }

        public boolean isEmpty() {
            if (size == 0) {
                return true;
            } else {
                return false;
            }
        }

        public int removeNode() {
            int rst;
            if(!isEmpty()) {
                rst = heap[0];
                heap[0] = heap[--size];
                heap[size] = 0;
            } else {
                return 0;
            }

            arrange();
            return rst;
        }

        private void arrange() {
            int p = 0;  //루트노드에서부터 크기 비교 후 swap해준다
            while(p*2+1 < size-1) {
                if (heap[p*2+1] > heap[p*2+2]) {
                    int temp = heap[p];
                    heap[p] = heap[p*2+1];
                    heap[p*2+1] = temp;
                    p = p*2+1;
                } else {
                    int temp = heap[p];
                    heap[p] = heap[p*2+2];
                    heap[p*2+2] = temp;
                    p = p*2+2;
                }
            }
        }
    }
}
