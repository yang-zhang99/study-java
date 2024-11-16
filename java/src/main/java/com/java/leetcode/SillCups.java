package com.java.leetcode;

import java.util.Arrays;


public class SillCups {
    // 2335. 装满杯子需要的最短总时长
    // https://leetcode.cn/problems/minimum-amount-of-time-to-fill-cups/

    public int fillCups(int[] amount) {
        Arrays.sort(amount);
        if (amount[2] > amount[1] + amount[0]) {
            return amount[2];
        }
        return (amount[0] + amount[1] + amount[2] + 1) / 2;
    }

    public int fillCups2(int[] amount) {
        int rtu = 0;
        // 很明显，这个题目按照顺序排列，然后计算。

        while (true) {
            Arrays.sort(amount);

            if (amount[2] != 0) {
                if (amount[1] != 0) {
                    amount[2]--;
                    amount[1]--;
                    rtu++;
                } else {
                    rtu += amount[2];
                    return rtu;
                }
            } else {
                return rtu;
            }
        }
    }


}




