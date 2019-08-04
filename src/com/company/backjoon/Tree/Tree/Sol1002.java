package com.company.backjoon.Tree.Tree;

public class Sol1002 {
    static class Tree {
        static class Node {
            int data;
            Node left;
            Node right;
            public Node(int data) {
                this.data = data;
            }
        }
        Node root;
        Tree(int size) {
            root = makeBST(0, size-1);
        }

        private Node makeBST(int start, int end) {
            if (start > end) {
                return null;
            }
            int mid = (start+end)/2;
            Node node = new Node(mid);
            node.left = makeBST(start, mid-1);
            node.right = makeBST(mid+1, end);
            return node;
        }

        private boolean isBalanced(Node root) {
            if (root == null) {
                return true;
            }
            int heightDiff = getHeight(root.left) - getHeight(root.right);
            if (Math.abs(heightDiff) > 1) {
                return false;
            } else {
                return isBalanced(root.left) && isBalanced(root.right);
            }
        }

        private int getHeight(Node root) {
            if (root == null) {
                return -1;
            }
            return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        }

        private int checkHeight(Node root) {
            if (root == null) {
                return -1;
            }
            int leftHeight = checkHeight(root.left);
            if (leftHeight == Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            int rightHeight = checkHeight(root.right);
            if (rightHeight == Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            int heightDiff = leftHeight - rightHeight;
            if (Math.abs(heightDiff) > 1) {
                return Integer.MIN_VALUE;
            } else {
                return Math.max(leftHeight, rightHeight) + 1;
            }
        }

        private boolean isBalanced2(Node root) {
            return checkHeight(root) != Integer.MIN_VALUE;
        }
    }
    public static void main(String[] args) {
        Tree tree = new Tree(10);
        System.out.println(tree.isBalanced(tree.root));

        Tree tree2 = new Tree(10);
        System.out.println(tree2.isBalanced2(tree.root));
    }
}
