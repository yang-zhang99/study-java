package com.java.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestWPI {
    // 1124. 表现良好的最长时间段
    // https://leetcode.cn/problems/longest-well-performing-interval/


    // 超出时间限制了！
    public int longestWPI(int[] hours) {
        int rtu = 0, l = hours.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        for (int i = 0; i < l; i++) {
            int t = hours[i] > 8 ? 1 : -1;
            map.put(i + 1, map.get(i) + t);
        }
        for (int i = 0; i <= l; i++) {
            for (int j = 0; j <= i; j++) {
                if (map.get(i) > map.get(j)) {
                    rtu = Math.max(i - j, rtu);
                }
            }
        }
        return rtu;
    }

    public int longestWPI2(int[] hours) {
        int n = hours.length;
        Map<Integer, Integer> map = new HashMap<>();
        int s = 0, res = 0;
        for (int i = 0; i < n; i++) {
            s += hours[i] > 8 ? 1 : -1;
            if (s > 0) {
                res = Math.max(res, i + 1);
            } else {
                if (map.containsKey(s - 1)) {
                    res = Math.max(res, i - map.get(s - 1));
                }
            }
            if (!map.containsKey(s)) {
                map.put(s, i);
            }
        }
        return res;
    }


    public int longestWPI3(int[] hours) {
        int n = hours.length, max = 0;
        int[] arr = new int[n + 1];
        for (int i = 0; i < n; i++) arr[i + 1] = arr[i] + (hours[i] > 8 ? 1 : -1);
        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= i; j++)
                if (arr[i] > arr[j]) max = Math.max(max, i - j);
        return max;
    }

}
