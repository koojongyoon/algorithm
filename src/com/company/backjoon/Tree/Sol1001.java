package com.company.backjoon.Tree;

public class Sol1001 {
    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    static class Tree {
        public Node root;
        public void setRoot(Node node) {
            this.root = node;
        }
        public Node getRoot() {
            return root;
        }
        public Node makeNode(Node left, int data, Node right) {
            return new Node(data, left, right);
        }
        public void inorder(Node node) {
            if (node != null) {
                inorder(node.left);
                System.out.print(node.data);
                inorder(node.right);
            }
        }
        public void preorder(Node node) {
            if (node != null) {
                System.out.print(node.data);
                preorder(node.left);
                preorder(node.right);
            }
        }
        public void postorder(Node node) {
            if (node != null) {
                postorder(node.left);
                postorder(node.right);
                System.out.print(node.data);
            }
        }
    }
    /*
        1
      2   3
     4  5
     */
    public static void main(String[] args) {

        Tree t = new Tree();
        Node n4 = t.makeNode(null, 4, null);
        Node n5 = t.makeNode(null, 5, null);
        Node n2 = t.makeNode(n4, 2, n5);
        Node n3 = t.makeNode(null, 3, null);
        Node n1 = t.makeNode(n2, 1, n3);

        t.setRoot(n1);
        t.inorder(n1);
        System.out.println();
        t.preorder(n1);
        System.out.println();
        t.postorder(n1);
    }
}
