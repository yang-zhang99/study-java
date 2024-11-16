package com.java.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/making-file-names-unique/
 * <p>
 * 1487. 保证文件名唯一
 */
public class GetFolderNames {

    public String[] getFolderNames2(String[] names) {
        Map<String, Integer> index = new HashMap<String, Integer>();
        int n = names.length;
        String[] res = new String[n];

        for (int i = 0; i < n; i++) {

            String name = names[i];

            if (!index.containsKey(name)) {
                res[i] = name;
                index.put(name, 1);
            } else {

                int k = index.get(name);
                while (index.containsKey(addSuffix(name, k))) {
                    k++;
                }
                res[i] = addSuffix(name, k);
                index.put(name, k + 1);
                index.put(addSuffix(name, k), 1);
            }
        }
        return res;
    }

    public String addSuffix(String name, int k) {
        return name + "(" + k + ")";
    }


    public String[] getFolderNames(String[] names) {
        String[] rtu = new String[names.length];

        // 创建一个哈希表来存储数据出现的频次
        HashMap<String, Integer> hashMap = new HashMap<>();


        // 大体框架肯定是循环
        for (int i = 0; i < names.length; i++) {
            // 拆分
            String[] tt = names[i].split("\\(");

            String t0 = tt[0];
            Integer t1 = null;

            if (tt.length > 1) {
                t1 = Integer.valueOf(tt[1].split("\\)")[0]);
            }

            // 要判断 字符串是否存在 ();
            if (hashMap.get(t0) == null) {

                rtu[i] = names[i];

                if (t1 == null) {
                    hashMap.put(rtu[i], 0);
                } else {
                    hashMap.put(rtu[i], t1);
                }
            } else {

                Integer value = hashMap.get(t0) + 1;

                rtu[i] = t0 + "(" + value + ")";

                hashMap.put(tt[0], value);

            }
        }
        return rtu;
    }

}


