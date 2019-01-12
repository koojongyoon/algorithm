package com.company.Geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;


public class Sol1708 {

    static class Point {
        long x;
        long y;
        Point (long x, long y){
            this.x = x;
            this.y = y;
        }

    }

    static Point startPoint;
    static Stack<Point> stack;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList pointList = new ArrayList<>();
        int inputCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < inputCount; i++) {
            String[] XY= br.readLine().split(" ");
            int x = Integer.parseInt(XY[0]);
            int y = Integer.parseInt(XY[1]);

            pointList.add(new Point(x, y));
        }

        System.out.println(findConvexHull(pointList).size());
    }

    static Stack<Point> findConvexHull(List<Point> pointList)
    {

        findStartPoint(pointList);

        stack = new Stack<>();
        stack.add(startPoint);

        // 입력값들의 값을 비교해 우선순위를 만들어서 반시계방향을 기준으로 정렬한다
        pointList.sort(new Comparator<Point>() {
            @Override
            public int compare(Point point1, Point point2) {

                int ccw = ccw(startPoint, point1, point2);

                if(ccw > 0) {
                    return -1;
                } else if(ccw < 0) {
                    return 1;
                } else {
                    // 우선순위가 겹칠경우 동일선상에 있는 점은 중간에 걸친선이 아닌 가장 먼 점을 기준으로 정렬한다.
                    if (distance(startPoint, point1) > distance(startPoint, point2)) {
                        return 1;
                    }
                }
                return 0;
            }
        });

        // 가장 외곽선이 아닌경우에 stack에서 값을 꺼내고 그렇지 않으면 가장 외곽에 있는 점이라고 판단하고 stack에 넣는다.
        for (int i = 1; i < pointList.size(); i++) {
            while (stack.size() > 1 && 0 >= ccw(stack.get(stack.size()-2), stack.peek(), pointList.get(i))) {
                stack.pop();
            }
            stack.add(pointList.get(i));
        }

        return stack;
    }

    //ccw를 수행할 시작 기준점을 찾는다 가장 하단 좌측의 점을 기준점으로
    static Point findStartPoint(List<Point> input) {
        startPoint = new Point(Long.MAX_VALUE, Long.MAX_VALUE);

        for(int i = 0; i < input.size(); i++) {
            if (input.get(i).y < startPoint.y) {
                startPoint = input.get(i);
            } else if (input.get(i).y == startPoint.y) {
                if (input.get(i).x < startPoint.x) {
                    startPoint = input.get(i);
                }
            }
        }

        return startPoint;
    }

    //두 점간의 거리 계산
    static long distance(Point point1, Point point2) {
        return (long) (Math.pow(point2.x-point1.x, 2) + Math.pow(point2.y-point1.y, 2));
    }

    // 비교 대상인 점이 시계 방향인지 반시계방향인지 판단 (오른쪽에 있는지 왼쪽에 있는지)
    static int ccw(Point point1, Point point2, Point point3) {

        long result = (point1.x * point2.y + point2.x * point3.y + point3.x * point1.y)
                - (point3.x * point2.y + point2.x * point1.y + point1.x * point3.y);

        if (result > 0) {
            //반시계 방향
            return 1;
        } else if (result < 0) {
            //시계 방향
            return -1;
        } else {
            //같은 직선상
            return 0;
        }
    }
}
