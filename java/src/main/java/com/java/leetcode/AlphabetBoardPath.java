package com.java.leetcode;

public class AlphabetBoardPath {
    // 1138. 字母板上的路径
    // https://leetcode.cn/problems/alphabet-board-path/

    public String alphabetBoardPath(String target) {
        StringBuilder rtu = new StringBuilder();
        // 起点
        int cx = 0;
        int cy = 0;


        for (int i = 0; i < target.length(); i++) {
            char t = target.charAt(i);

            // 找目标

            int x = (t - 'a') / 5;
            int y = (t - 'a') % 5;

//            if (x < cx) {
//                rtu.append("U".repeat(cx - x));
//            }
//            if (y < cy) {
//                rtu.append("L".repeat(cy - y));
//            }
//            if (x > cx) {
//                rtu.append("D".repeat(x - cx));
//            }
//            if (y > cy) {
//                rtu.append("R".repeat(y - cy));
//            }
            rtu.append('!');
            cx = x;
            cy = y;

        }

        return rtu.toString();

    }

    public String alphabetBoardPath2(String target) {
        int cx = 0, cy = 0;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            int nx = (c - 'a') / 5;
            int ny = (c - 'a') % 5;
            if (nx < cx) {
                for (int j = 0; j < cx - nx; j++) {
                    res.append('U');
                }
            }
            if (ny < cy) {
                for (int j = 0; j < cy - ny; j++) {
                    res.append('L');
                }
            }
            if (nx > cx) {
                for (int j = 0; j < nx - cx; j++) {
                    res.append('D');
                }
            }
            if (ny > cy) {
                for (int j = 0; j < ny - cy; j++) {
                    res.append('R');
                }
            }
            res.append('!');
            cx = nx;
            cy = ny;
        }
        return res.toString();
    }


}

