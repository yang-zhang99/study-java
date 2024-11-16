package com.java.test;

import java.util.HashMap;
import java.util.Map;

public class ListTest {

    public static void main(String[] args) throws CloneNotSupportedException {
//        Solution s = new Solution();
//        s.setX(12);
////        int rut = s.specialPerm(new int[]{31, 93});
//        System.out.println(s.getX());
//
//        Solution s1 = (Solution) s.clone();
//
//        s1.setX(13);
//
//        System.out.println(s1.getX());
//        System.out.println(s.getX());
//        BigDecimal
//        InputStream inputStream;

//        inputStrea

        HashMap<String, String> hashMap = new HashMap<String, String>();

        String s = new String("xxx");

        hashMap.put(s, "xxx");

        System.out.println(s.hashCode());

        hashMap.put("xxxx", "xxxx");
        hashMap.put(s, "ssss");

        for (Map.Entry<String, String> i : hashMap.entrySet()) {
            System.out.println(i.getKey());
            System.out.println(i.getKey().hashCode());
//            System.out.println(i.getValue());
        }

    }


}

class Solution implements Cloneable {

    private int x;

    public int getX() {
        return x;
    }

    public Solution setX(int x) {
        this.x = x;
        return this;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    //    public int specialPerm(int[] nums) {
//        Arrays.sort(nums);
//        int t = 0;
//
//        for (int j = 0; j < nums.length; j++) {
//            for (int i = j + 1; i < nums.length; i++) {
//                if (nums[i] % nums[j] == 0) {
//                    t = t + 1;
//                }
//            }
//        }
//        return t;
//
//    }
}