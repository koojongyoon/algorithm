package com.company.NetworkFlow;

import java.util.ArrayList;
import java.util.Scanner;

public class Sol2188 {

    static Cow[] cowArr;
    static int[] houseArr;

    static class Cow {
        boolean[] visitedHouse;
        ArrayList<Integer> house;

        Cow(boolean[] visitedHouse, ArrayList<Integer> house) {
            this.visitedHouse = visitedHouse;
            this.house = house;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        cowArr = new Cow[n+1];

        for (int i = 1; i <= m; i++) {
            int likeHouseCount = sc.nextInt();
            cowArr[i] = new Cow (new boolean[m+1], new ArrayList<>());
            for (int k = 0; k < likeHouseCount; k++) {
                cowArr[i].house.add(sc.nextInt());
            }
        }

        houseArr = new int[m+1];

        for (int cow = 1; cow <= n; cow++) {
            //소가 가고싶은 하우스가 비어있는 경우 소를 집어 넣고 방문 표시를 한다
            findLikeHouse(cow, cowArr[cow].house.get(0));
        }

        int result = 0;

        for (int i = 1; i < houseArr.length; i++) {
            if (houseArr[i] != 0) {
                result++;
            }
        }

        System.out.println(result);
    }

    static void findLikeHouse(int cow, int likeHouse) {
        //소가 들어가고싶어하는 축사가 비어 있는 경우 바로 들어간다.
        if (houseArr[likeHouse] == 0 && !cowArr[cow].visitedHouse[likeHouse]) {
            houseArr[likeHouse] = cow;
            cowArr[cow].visitedHouse[likeHouse] = true;
            System.out.println("original cow " + cow + " : " + likeHouse);
        } else {
            int existsCow = houseArr[likeHouse];
            Cow findOtherHouseCow = cowArr[houseArr[likeHouse]];
            houseArr[likeHouse] = cow;
            cowArr[cow].visitedHouse[likeHouse] = true;
            System.out.println("settle cow " + cow + " : " + likeHouse);
            for (int i = 0; i < findOtherHouseCow.house.size(); i++) {
                if (!cowArr[existsCow].visitedHouse[findOtherHouseCow.house.get(i)]) {
                    System.out.println("find cow " + existsCow + " : " + findOtherHouseCow.house.get(i));
                    findLikeHouse(existsCow, findOtherHouseCow.house.get(i));
                    break;
                }
            }
        }
    }
}
