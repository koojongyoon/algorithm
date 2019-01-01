package com.company.DFS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by koojongyun on 2019. 1. 1..
 */
public class Sol2820 {

    static final String SALARY_UPDATE = "p";
    static final String SALARY_PRINT = "u";
    static List<Employee> employeeList;
    static Stack<Employee> willUpdateEmployee;

    static class Employee {

        int myNumber;
        int salary;
        int upperBossNumber;

        public Employee(int myNumber, int salary, int upperBossNumber) {
            this.myNumber = myNumber;
            this.salary = salary;
            this.upperBossNumber = upperBossNumber;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NM = br.readLine().split(" ");

        int employeeCount = Integer.parseInt(NM[0]);
        int queryCount = Integer.parseInt(NM[1]);

        employeeList = new ArrayList<>();
        willUpdateEmployee = new Stack<>();

        for (int i = 0; i < employeeCount; i++) {
            String[] salaryBoss = br.readLine().split(" ");
            int salary = Integer.parseInt(salaryBoss[0]);
            int upperBossNumber = 0;

            if (1 <  salaryBoss.length) {
                upperBossNumber = Integer.parseInt(salaryBoss[1]);
            }
            employeeList.add(new Employee(i+1, salary, upperBossNumber));
        }

        for (int i = 0; i < queryCount; i++) {
            String[] queryCommand = br.readLine().split(" ");

            if(SALARY_UPDATE.equals(queryCommand[0])) {
                update(Integer.parseInt(queryCommand[1]), Integer.parseInt(queryCommand[2]));
            }

            if(SALARY_PRINT.equals(queryCommand[0])) {
                for (int k = 0; k < employeeList.size(); k++) {
                    if (employeeList.get(k).myNumber == Integer.parseInt(queryCommand[1])) {
                        System.out.println(employeeList.get(k).salary);
                    }
                }
            }
        }
    }

    private static void update(int upperBossNumber, int updateSalary) {

        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).upperBossNumber == upperBossNumber) {
                employeeList.get(i).salary = employeeList.get(i).salary + updateSalary;
                willUpdateEmployee.add(employeeList.get(i));
            }
        }

        if (willUpdateEmployee.empty()) {
            return;
        }

        Employee employee = willUpdateEmployee.pop();
        update(employee.myNumber, updateSalary);
    }
}
