package com.company.backjoon.Tree;

import java.util.ArrayList;
import java.util.Scanner;

public class Sol2820 {

    static ArrayList<Integer>[] tree;
    static String SALARY_MODIFY = "p";
    static String SALARY_OUTPUT = "u";
    static boolean[] visited;
    static int[] start;
    static int[] end;
    static int order;
    static int node;
    static int[] lazy;
    static int[] segmentTree;
    static int[] salary;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int employeeNum = sc.nextInt();
        int query = sc.nextInt();

        tree = new ArrayList[employeeNum+1];
        salary = new int[employeeNum+1];
        salary[1] = sc.nextInt();

        for (int i = 0; i <= employeeNum; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 2; i <= employeeNum; i++) {
            int mySalary = sc.nextInt();
            int myBoss = sc.nextInt();
            salary[i] = mySalary;
            tree[i].add(myBoss);
            tree[myBoss].add(i);
        }

        visited = new boolean[employeeNum+1];
        start = new int[employeeNum+1];
        end = new int[employeeNum+1];

        dfs(1);

        int m = 0;
        while(true) {
            if (employeeNum < Math.pow(2, m)) {
                node = (int) Math.pow(2, m);
                break;
            }
            m++;
        }

        lazy = new int[node*2];
        segmentTree = new int[node*2];

        for (int i = 1; i <= employeeNum; i++) {
            update(1, node ,1 ,start[i], start[i], salary[i]);
        }


        for (int i = 0; i < query; i++) {
            String execute = sc.next();
            if (execute.equals(SALARY_MODIFY)) {
                int employee = sc.nextInt();
                int modifySalary = sc.nextInt();
                if (start[employee] == end[employee]) {
                    continue;
                }
                update(1, node, 1, start[employee]+1 , end[employee], modifySalary);

            } else if (execute.equals(SALARY_OUTPUT)) {
                int employee = sc.nextInt();
                System.out.println(sum(1, node, 1, start[employee], start[employee]));
            }
        }
    }

    private static void update(int l, int r, int target, int L, int R, int salary) {
        propagate(l, r, target);
        //구간한의 범위를 벗어난 경우
        if (r < L || l > R) {
            return;
        }

        if (L<=l && r<=R) {
            lazy[target] = lazy[target] + salary;
            propagate(l, r, target);
            return;
        }

        int mid = (l+r)/2;
        update(l, mid, target*2, L, R, salary);
        update(mid+1, r, target*2+1, L, R, salary);
        segmentTree[target] = segmentTree[target*2] + segmentTree[target*2+1];

    }

    private static int sum(int l, int r, int target, int L, int R) {
        propagate(l, r, target);

        if (r < L || l > R) {
            return 0;
        }

        if (L <= l && R >= r) {
            return segmentTree[target];
        }

        int mid = (l+r)/2;
        return sum(l, mid, target*2, L, R) + sum(mid+1, r, target*2+1, L, R);
    }

    private static void propagate(int l, int r, int target) {
        if (lazy[target] == 0) {
            return;
        }

        if (l != r) {
            lazy[target*2] = lazy[target*2] + lazy[target];
            lazy[target*2+1] = lazy[target*2+1] + lazy[target];
        }
        segmentTree[target] = segmentTree[target] + ((r-l+1) * lazy[target]);
        lazy[target] = 0;
    }

    private static void dfs(int employee) {
        visited[employee] = true;
        start[employee] = ++order;
        for (int k : tree[employee])
            if (!visited[k])
                dfs(k);
        end[employee] = order;
    }
}
