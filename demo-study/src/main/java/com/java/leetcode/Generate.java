package com.java.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Generate {
    // 118. 杨辉三角
    // https://leetcode.cn/problems/pascals-triangle/

    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> rtu = new ArrayList<>(numRows);


        for (int i = 0; i < numRows; i++) {

            List<Integer> row = new ArrayList<Integer>();

            for (int j = 0; j <= i; ++j) {

                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(rtu.get(i - 1).get(j - 1) + rtu.get(i - 1).get(j));
                }
            }
            rtu.add(row);
        }


        return rtu;
    }
}
