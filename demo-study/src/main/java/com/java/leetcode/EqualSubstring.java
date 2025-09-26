package com.java.leetcode;


public class EqualSubstring {
    // 1208. 尽可能使字符串相等
    // https://leetcode.cn/problems/get-equal-substrings-within-budget/

    public int equalSubstring(String s, String t, int maxCost) {
        int rtu = 0;
        int sum = 0;

        int l = s.length();
        int left = 0, right = 0;

        while (right < l) {
            sum = sum + Math.abs(s.charAt(right) - t.charAt(right));
            right++;

            while (sum > maxCost) {
                sum = sum - Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }

            rtu = Math.max(rtu, right - left);
        }

        return rtu;
    }

}
