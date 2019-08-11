package com.company.backjoon.Tree.Tree;

import java.util.ArrayList;

class Upgrade {
    class Node {
        int data;
        Node left;
        Node right;
        Node (int data) {
            this.data = data;
        }
    }

    Node root;
    Upgrade (int size) {
        this.root = makeBST(0, size -1);
    }

    Node makeBST(int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) /2;
        Node node = new Node(mid);
        node.left = makeBST(start, mid -1);
        node.right = makeBST(mid + 1, end);
        return node;
    }

    int countPathWithSum(int targetSum) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        return countPathWithSum(root, targetSum, array);
    }

    int countPathWithSum(Node root, int targetSum, ArrayList<Integer> array) {
        if (root == null) {
            return 0;
        }
        int totalPaths = 0;
        addValue(array, root.data);
        totalPaths = countPaths(array, targetSum);
        totalPaths = totalPaths + countPathWithSum(root.left, targetSum, array);
        totalPaths = totalPaths + countPathWithSum(root.right, targetSum, array);
        removeLast(array);
        return totalPaths;
    }

    private void removeLast(ArrayList<Integer> array) {
        int value = array.remove(array.size() - 1);
        for (int i = 0; i < array.size(); i++) {
            array.set(i, array.get(i) - value);
        }
    }

    void addValue(ArrayList<Integer> array, int value) {
        for (int i = 0; i < array.size(); i++) {
            array.set(i, array.get(i) + value);
        }
        array.add(value);
    }

    int countPaths(ArrayList<Integer> array, int targetSum) {
        int totalPaths = 0;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) == targetSum) {
                totalPaths++;
            }
        }
        return totalPaths;
    }

    int countPathsWithSumFromNode(Node node, int targetSum, int currSum) {
        if (node == null) {
            return 0;
        }
        currSum = currSum + node.data;
        int totalPaths = 0;
        if (currSum == targetSum) {
            totalPaths++;
        }
        totalPaths = totalPaths + countPathsWithSumFromNode(node.left, targetSum, currSum);
        totalPaths = totalPaths + countPathsWithSumFromNode(node.right, targetSum, currSum);
        return totalPaths;
    }
}

public class Sol1005_upgrade {
    public static void main(String[] args) {
        Tree tree = new Tree(10);
        System.out.println(tree.countPathWithSum(5));
    }
}
