package com.java.leetcode;

import java.util.HashMap;
import java.util.Map;

public class NumberOfPairs {

    public int[] numberOfPairs(int[] nums) {
        // 2341. 数组能形成多少数对
        // https://leetcode.cn/problems/maximum-number-of-pairs-in-array/

        int[] rtu = new int[2];

        HashMap<Integer, Integer> h = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            h.put(nums[i], h.getOrDefault(nums[i], 0) + 1);
        }


        for (Integer value : h.values()) {
            rtu[0] = rtu[0] + value / 2;
            rtu[1] = rtu[1] + value % 2;
        }


        return rtu;
    }


    public int[] numberOfPairs2(int[] nums) {
        Map<Integer, Boolean> cnt = new HashMap<Integer, Boolean>();
        int res = 0;
        for (int num : nums) {
            cnt.put(num, !cnt.getOrDefault(num, false));
            if (!cnt.get(num)) {
                res++;
            }
        }
        return new int[]{res, nums.length - 2 * res};
    }


}
