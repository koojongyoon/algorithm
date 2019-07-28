package com.company.backjoon.Tree;

public class Sol1000 {
    static class Tree {
        class Node {
            int data;
            Node left;
            Node right;
            Node (int data) {
                this.data = data;
            }
        }
        Node root;
        public void makeTree(int[] arr) {
            root = makeTreeRecursive(arr, 0, arr.length - 1);
        }

        public Node makeTreeRecursive(int[] arr, int startIndex, int endIndex) {
            if (startIndex > endIndex) {
                return null;
            }
            int mid = (startIndex + endIndex)/2;
            Node node = new Node(arr[mid]);
            node.left = makeTreeRecursive(arr, startIndex, mid-1);
            node.right = makeTreeRecursive(arr, mid+1, endIndex);
            return node;
        }

        public void searchBTree(Node n, int find) {
            if (n.data < find) {
                System.out.println(n.data + " is small than " + find);
                searchBTree(n.right, find);
            } else if (n.data > find) {
                System.out.println(n.data + " is bigger than " + find);
                searchBTree(n.left, find);
            } else {
                System.out.println("find");
            }
        }
    }
    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }
        Tree tree = new Tree();
        tree.makeTree(arr);
        tree.searchBTree(tree.root, 2);
    }
}
