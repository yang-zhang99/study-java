package com.java.leetcode;

import java.util.Arrays;

public class FindLongestChain {

    public int findLongestChain(int[][] pairs) {
        // 646. 最长数对链
        // https://leetcode.cn/problems/maximum-length-of-pair-chain/

        int rtu = 0;
        int min = Integer.MIN_VALUE;

        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);

        for (int[] p : pairs) {
            if (min < p[0]) {
                min = p[1];
                rtu++;
            }
        }


        return rtu;

    }

}
