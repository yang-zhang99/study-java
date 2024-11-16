package com.java.leetcode;

import java.util.HashMap;

public class BestHand {
    // 2347. 最好的扑克手牌
    // https://leetcode.cn/problems/best-poker-hand/

    public String bestHand(int[] ranks, char[] suits) {

        HashMap<Character, Integer> h = new HashMap<Character, Integer>();
        for (char suit : suits) {
            h.put(suit, h.getOrDefault(suit, 0) + 1);
        }
        if (h.size() == 1) {
            return "Flush";
        }

        HashMap<Integer, Integer> r = new HashMap<Integer, Integer>();

        for (int rank : ranks) {
            r.put(rank, r.getOrDefault(rank, 0) + 1);
        }

        if (r.size() == 5) {
            return "High Card";
        }


        Integer max = 0;
        for (Integer i : r.values()) {
            max = i > max ? i : max;
        }

        if (max >= 3) {
            return "Three of a Kind";
        } else {
            return "Pair";
        }

    }

}
