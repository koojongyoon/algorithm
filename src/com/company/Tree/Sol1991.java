package com.company.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol1991 {

    static class TreeNode {
        char data;
        TreeNode leftNode;
        TreeNode rightNode;

        TreeNode (char data) {
            this.data = data;
        }
    }

    static class Tree {

        TreeNode root;

        public Tree(char data, char left, char right) {
            root = new TreeNode(data);

            if (data != '.') {
                root = new TreeNode(data);
            }
            if (left != '.') {
                root.leftNode = new TreeNode(left);
            }
            if (right != '.') {
                root.rightNode = new TreeNode(right);
            }
        }

        public void add (char data, char left, char right) {
            next(root.leftNode, data, left, right);
            next(root.rightNode, data, left, right);
        }

        public void next (TreeNode node, char data, char left, char right) {
            if (node == null) {
                return;
            }
            if (node.data == data) {
                if (left != '.') {
                    node.leftNode = new TreeNode(left);
                }
                if (right != '.') {
                    node.rightNode = new TreeNode(right);
                }
            } else {
                next(node.leftNode, data, left, right);
                next(node.rightNode, data,left, right);
            }
        }

        public void preOrder(TreeNode node) {
            System.out.print(node.data);
            if (node.leftNode != null) {
                preOrder(node.leftNode);
            }
            if (node.rightNode != null) {
                preOrder(node.rightNode);
            }
        }

        public void inOrder(TreeNode node) {
            if (node.leftNode != null) {
                inOrder(node.leftNode);
            }
            System.out.print(node.data);
            if (node.rightNode != null) {
                inOrder(node.rightNode);
            }
        }

        public void postOrder(TreeNode node) {
            if (node.leftNode != null) {
                postOrder(node.leftNode);
            }
            if (node.rightNode != null) {
                postOrder(node.rightNode);
            }
            System.out.print(node.data);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nodeCount = Integer.parseInt(br.readLine());
        char[] nodes = br.readLine().replaceAll(" ", "").toCharArray();
        Tree tree = new Tree(nodes[0], nodes[1], nodes[2]);

        for (int i = 1; i < nodeCount; i++) {
            char[] node = br.readLine().replaceAll(" ", "").toCharArray();
            tree.add(node[0], node[1], node[2]);
        }

        tree.preOrder(tree.root);
        System.out.println();
        tree.inOrder(tree.root);
        System.out.println();
        tree.postOrder(tree.root);
    }
}
