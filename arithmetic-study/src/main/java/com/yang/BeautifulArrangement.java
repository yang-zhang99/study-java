package com.yang;

import java.util.Arrays;

/**
 * 526. 优美的排列 *
 * <p>
 * https://leetcode.cn/problems/beautiful-arrangement/
 */
public class BeautifulArrangement {
    
    public int countArrangement(int n) {
        int[] memo = new int[1 << n];
        Arrays.fill(memo, -1); // -1 表示没有计算过
        return dfs(0, n, memo);
    }
    
    private int dfs(int s, int n, int[] memo) {
        if (s == (1 << n) - 1) {
            return 1;
        }
        if (memo[s] != -1) { // 之前计算过
            return memo[s];
        }
        int res = 0;
        int i = Integer.bitCount(s) + 1;
        for (int j = 1; j <= n; j++) {
            if ((s >> (j - 1) & 1) == 0 && (i % j == 0 || j % i == 0)) {
                res += dfs(s | (1 << (j - 1)), n, memo);
            }
        }
        memo[s] = res; // 记忆化
        return res;
    }
    
    
    public static void main(String[] args) {
        BeautifulArrangement beautifulArrangement = new BeautifulArrangement();
        System.out.println(beautifulArrangement.countArrangement(2));
        // System.out.println(beautifulArrangement.countArrangement(3));
        // System.out.println(beautifulArrangement.countArrangement(4));
    }
}