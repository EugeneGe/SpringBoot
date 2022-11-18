package com.yicheng.common.core.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author EugeneGe
 * @date 2021-11-19 19:24
 */
public class DataUtil {

    /**
     * URL化。编写一种方法，将字符串中的空格全部替换为%20
     *
     * @param S
     * @param length
     * @return
     */
    public static String replaceSpaces(String S, int length) {
        return S.substring(0, length).replace(" ", "%20");
    }

    /**
     * 判定字符是否唯一
     *
     * @param astr
     * @return
     */
    public static boolean isUnique(String astr) {
        char[] strArr = astr.toCharArray();
        HashSet set = new HashSet();
        for (char c : strArr) {
            set.add(c);
        }
//        return astr.length() == set.size();
        return astr.chars().distinct().count() == astr.length();
    }

    /**
     * 判定是否互为字符重排
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        } else {
            char[] c1 = s1.toCharArray();
            char[] c2 = s2.toCharArray();
            Arrays.sort(c1);
            Arrays.sort(c2);
            for (int i = 0; i < c1.length; i++) {
                if (c1[i] != c2[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 回文排列
     * 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
     * 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
     * 回文串不一定是字典当中的单词。
     *
     * @param s
     * @return
     */
    public static boolean canPermutePalindrome(String s) {
        if (s == null) {
            return false;
        }
        char[] chars = s.toCharArray();
        Set<Character> set = new HashSet<>();
        for (char c : chars) {
            if (set.contains(c)) {
                set.remove(c);
            } else {
                set.add(c);
            }
        }
        return set.size() <= 1;
    }

}
