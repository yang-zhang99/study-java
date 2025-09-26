package com.java.leetcode;

public class MinTaps {

    public int minTaps(int n, int[] ranges) {


        int[] land = new int[n];

        for (int i = 0; i < ranges.length; i++) {
            int l = Math.max(i - ranges[i], 0);
            int r = Math.min(i + ranges[i], n);

            for (int j = l; j < r; j++) {
                land[j] = Math.max(land[j], r);
            }
        }

        int cnt = 0;
        int cur = 0;

        while (cur < n) {
            if (land[cur] == 0) {
                return -1;
            }
            cur = land[cur];
            cnt++;
        }

        return cnt;
    }


}
