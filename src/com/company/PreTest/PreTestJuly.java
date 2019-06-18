package com.company.PreTest;

import java.io.*;
import java.math.BigInteger;

public class PreTestJuly {

    static class Team {
        boolean isDead = false;
        int deadTime;
        Player p1;
        Player p2;
    }

    static class Player {
        long x;
        long y;

        Player(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    static Team[] teamArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int teamCount = Integer.parseInt(br.readLine());
            teamArr = new Team[teamCount+1];

            for (int j = 1; j<= teamCount; j++) {
                String[] teamInputStr = br.readLine().split(" ");
                Team team = new Team();
                team.p1 = new Player(Integer.parseInt(teamInputStr[0]), Integer.parseInt(teamInputStr[1]));
                team.p2 = new Player(Integer.parseInt(teamInputStr[2]), Integer.parseInt(teamInputStr[3]));
                team.isDead = false;
                teamArr[j] = team;
            }

            //시간이 흐름
            for (int j = 0; j < 31; j++) {
                if (j > 0) {
                    halfMap();

                    if (j == 10 || j == 20 || j == 30) {
                        moveMap(j);
                    }
                }

                boolean[] deadPlayer = new boolean[teamCount+1];
                for (int n = 1; n < teamArr.length; n++) {
                    for (int m = n+1; m < teamArr.length; m++) {
                        if (!teamArr[n].isDead && !teamArr[m].isDead) {
                            if (samePoint(n, m)) {
                                deadPlayer[n] = true;
                                deadPlayer[m] = true;
                                continue;
                            }
                            if (sameLine(n, m)) {
                                deadPlayer[n] = true;
                                deadPlayer[m] = true;
                                continue;
                            }
                            if (intersect(teamArr[n].p1, teamArr[n].p2, teamArr[m].p1, teamArr[m].p2)) {
                                deadPlayer[n] = true;
                                deadPlayer[m] = true;
                                continue;
                            }
                        }
                    }
                }

                for (int i = 1; i <= teamCount; i++) {
                    if (deadPlayer[i]) {
                        deadFlag(i, j);
                    }
                }
            }

            bw.write("#" + t + " ");

            for (int j = 1; j < teamArr.length; j++) {
                if (!teamArr[j].isDead) {
                    teamArr[j].deadTime = 32;
                }
                bw.write(teamArr[j].deadTime+ " ");
            }
            bw.write("\n");
            bw.flush();
        }

        bw.close();
        br.close();
    }

    private static boolean samePoint (int n ,int m) {
        return (teamArr[n].p1.x == teamArr[m].p1.x && teamArr[n].p1.y == teamArr[m].p1.y)
                || (teamArr[n].p2.x == teamArr[m].p1.x && teamArr[n].p2.y == teamArr[m].p1.y)
                || (teamArr[n].p1.x == teamArr[m].p2.x && teamArr[n].p1.y == teamArr[m].p2.y)
                || (teamArr[n].p2.x == teamArr[m].p2.x && teamArr[n].p2.y == teamArr[m].p2.y);
    }

    private static boolean sameLine(int n, int m) {
        return onTheLine(teamArr[n].p1, teamArr[n].p2, teamArr[m].p1)
                || onTheLine(teamArr[n].p1, teamArr[n].p2, teamArr[m].p2)
                || onTheLine(teamArr[m].p1, teamArr[m].p2, teamArr[n].p1)
                || onTheLine(teamArr[m].p1, teamArr[m].p2, teamArr[n].p2);
    }

    private static boolean intersect (Player np1, Player np2, Player mp1, Player mp2) {

        if (separateLine(np1, np2, mp1, mp2)) {
            return false;
        }

        BigInteger vector1 = BigInteger.valueOf((np2.x - np1.x) * (mp1.y-np1.y)).subtract(BigInteger.valueOf((mp1.x - np1.x) * (np2.y - np1.y)));
        BigInteger vector2 = BigInteger.valueOf((np2.x - np1.x) * (mp2.y-np1.y)).subtract(BigInteger.valueOf((mp2.x - np1.x) * (np2.y - np1.y)));
        BigInteger vector3 = BigInteger.valueOf((mp2.x - mp1.x) * (np1.y-mp1.y)).subtract(BigInteger.valueOf((np1.x - mp1.x) * (mp2.y - mp1.y)));
        BigInteger vector4 = BigInteger.valueOf((mp2.x - mp1.x) * (np2.y-mp1.y)).subtract(BigInteger.valueOf((np2.x - mp1.x) * (mp2.y - mp1.y)));

        return vector1.multiply(vector2).compareTo(BigInteger.ZERO) < 0 &&  vector3.multiply(vector4).compareTo(BigInteger.ZERO) < 0;
    }

    private static boolean separateLine(Player np1, Player np2, Player mp1, Player mp2) {
        if (Math.min(mp1.x, mp2.x) > Math.max(np1.x, np2.x)
                || Math.min(np1.x, np2.x) > Math.max(mp1.x, mp2.x)
                || Math.min(mp1.y, mp2.y) > Math.max(np1.y, np2.y)
                || Math.min(np1.y, np2.y) > Math.max(mp1.y, mp2.y)) {
            return true;
        }
        return false;
    }

    public static boolean onTheLine(Player p1, Player p2, Player enemy) {
        if ((p1.x * (p2.y - enemy.y) + p2.x * (enemy.y - p1.y) + enemy.x * (p1.y - p2.y)) == 0) {
            if (Math.max(p1.x, p2.x) >= enemy.x &&  Math.min(p1.x, p2.x) <= enemy.x && Math.max(p1.y, p2.y) >= enemy.y &&  Math.min(p1.y, p2.y) <= enemy.y) {
                return true;
            }
        }
        return false;
    }

    private static void halfMap() {
        for (int k = 1; k < teamArr.length; k++) {
            Team team = teamArr[k];
            team.p1.x = team.p1.x/2;
            team.p1.y = team.p1.y/2;
            team.p2.x = team.p2.x/2;
            team.p2.y = team.p2.y/2;
            teamArr[k] = team;
        }
    }

    private static void moveMap(int time) {
        if (time == 10) {
            fourToThree();
        }

        if (time == 20) {
            threeToTwo();
        }

        if (time == 30) {
            twoToOne();
        }
    }

    private static void fourToThree() {
        for (int n = 1; n < teamArr.length; n++) {
            if (teamArr[n].p1.x > 0 && teamArr[n].p1.y < 0) {
                teamArr[n].p1.x = teamArr[n].p1.x * -1;
            }
            if (teamArr[n].p2.x > 0 && teamArr[n].p2.y < 0) {
                teamArr[n].p2.x = teamArr[n].p2.x * -1;
            }
        }
    }

    private static void threeToTwo() {
        for (int n = 1; n < teamArr.length; n++) {
            if (teamArr[n].p1.x < 0 && teamArr[n].p1.y < 0) {
                teamArr[n].p1.y = teamArr[n].p1.y * -1;
            }
            if (teamArr[n].p2.x < 0 && teamArr[n].p2.y < 0) {
                teamArr[n].p2.y = teamArr[n].p2.y * -1;
            }
        }
    }

    private static void twoToOne() {
        for (int n = 1; n < teamArr.length; n++) {
            if (teamArr[n].p1.x < 0 && teamArr[n].p1.y > 0) {
                teamArr[n].p1.x = teamArr[n].p1.x * -1;
            }
            if (teamArr[n].p2.x < 0 && teamArr[n].p2.y > 0) {
                teamArr[n].p2.x = teamArr[n].p2.x * -1;
            }
        }
    }

    private static void deadFlag(int checkN, int deadTime) {
        teamArr[checkN].isDead = true;
        teamArr[checkN].deadTime = deadTime;
    }
}