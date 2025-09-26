package com.java.leetcode;

/**
 * 1653. 使字符串平衡的最少删除次数
 * https://leetcode.cn/problems/minimum-deletions-to-make-string-balanced/
 */
public class MinimumDeletions {

    public int minimumDeletions(String s) {


        char[] chars = s.toCharArray();

        int del = 0;

        for (char c : chars) {
            del = del + 'b' - c;
        }

        int rtu = del;


        for (char c : chars) {
            del = del + (c - 'a') * 2 - 1;
            rtu = Math.max(rtu, del);
        }

        return rtu;
    }

}
