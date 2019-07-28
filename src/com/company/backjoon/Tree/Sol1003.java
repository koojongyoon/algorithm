package com.company.backjoon.Tree;

import java.util.HashMap;

public class Sol1003 {

    static class Tree {
        class Node {
            int data;
            Node left;
            Node right;
            Node parent;
            public Node(int data) {
                this.data = data;
            }
        }
        Node root;
        HashMap<Integer, Node> rootMap;

        Tree (int size) {
            rootMap = new HashMap<>();
            root = makeBST(0, size -1, null);
        }

        private Node makeBST(int start, int end, Node parent) {
            if (start > end) {
                return null;
            }
            int mid = (start + end) / 2;
            Node node = new Node(mid);
            node.left = makeBST(start, mid-1, node);
            node.right = makeBST(mid + 1, end, node);
            node.parent = parent;
            rootMap.put(mid, node);
            return node;

        }
        Node getNode (int data) {
            return rootMap.get(data);
        }
        Node commonAncestor(int d1, int d2) {
            Node p = getNode(d1);
            Node q = getNode(d2);
            int diff = Math.abs(depth(p) - depth(q));
            Node first = diff > 0 ? q : p ;
            Node second = diff > 0 ? p : q;
            second = goUpBy(second, Math.abs(diff));
            while (first != second && first != null && second != null) {
                first = first.parent;
                second = second.parent;
            }
            return (first == null || second == null) ? null : first;
        }
        Node goUpBy(Node node, int diff) {
            while(diff > 0 && node != null) {
                node = node.parent;
                diff--;
            }
            return node;
        }

        int depth(Node node) {
            int depth = 0; {
            while(node != null)
                node = node.parent;
                depth++;
            }
            return depth;

        }
    }

    public static void main(String[] args) {
        Tree t = new Tree(10);
        Tree.Node f = t.commonAncestor(3, 5);
        System.out.println(f.data);
    }
}
