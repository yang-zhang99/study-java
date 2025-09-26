package com.java.leetcode;

import java.util.HashMap;

public class LongestPalindrome {
    // 409. 最长回文串
    // https://leetcode.cn/problems/longest-palindrome/

    public int longestPalindrome(String s) {
        int rtu = 0;
        boolean r = true;

        HashMap<Character, Integer> c = new HashMap();

        for (int i = 0; i < s.length(); i++) {
            c.put(s.charAt(i), c.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (Integer i : c.values()) {
            if (i % 2 == 0) {
                rtu += i;
            } else {
                rtu += i - 1;
                if (r) {
                    rtu++;
                    r = false;
                }
            }
        }

        return rtu;
    }


}
