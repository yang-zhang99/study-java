package com.java.leetcode;

/**
 * 思考：
 * 这道算法题最容易想到的是，按照十进制转二进制的方法，将这个数转换成二进制，如果超过 32 位，直接范围 ERROR 即可。
 */
public class PrintBin {
    // 面试题 05.02. 二进制数转字符串
    // https://leetcode.cn/problems/bianry-number-to-string-lcci/

    public String printBin(double num) {

        StringBuilder s = new StringBuilder("0.");

        for (int i = 0; i < 40; i++) {

            num = num * 2;

            int digit = (int) num;
            s.append(digit);

            num = num - digit;

            if (num == 0) {
                break;
            }

        }

        return s.length() <= 32 ? s.toString() : "ERROR";
    }


}
