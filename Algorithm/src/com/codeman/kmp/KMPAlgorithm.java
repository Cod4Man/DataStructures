package src.com.codeman.kmp;

import java.util.Arrays;

/**
 * KMP算法，是一种优化暴力匹配字符串的方法
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
//        System.out.println(Arrays.toString(next2("AACCDDAAACCDD")));
//        System.out.println(Arrays.toString(next("AACCDDAAACCDD")));
        // AACCDDAAACCDD    [0, 1, 0, 0, 0, 0, 1, 2, 2, 3, 4, 5, 6]
        String str1 = "ABCABDS";
        String str2 = "CAB";
        int[] next = next(str2);
        System.out.println(findStr(str1, str2, next));
    }

    /**
     * 查找出现下标
     * @param str1 父
     * @param str2 子
     * @param next 部分匹配表
     * @return 下标
     */
    public static int findStr(String str1, String str2, int[] next) {
        int index = -1;
        for (int i = 0, j = 0; i < str1.length(); i ++) {

            // 全场最佳
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j ++;
            }
            if (j == str2.length()) {
                index = i - j + 1;
                break;
            }
        }

        return index;
    }


    public static int[] next2(String str) {
        int[] result = new int[str.length()];
        result[0] = 0; // 如果只有一个，那么肯定是0
        for (int i = 1, j = 0; i < str.length(); i ++) {

            if (str.charAt(i) == str.charAt(j)) {
                j++;
            } else {
                j = 0; // 这里其实漏了多次比较
                /*if (str.charAt(i) == str.charAt(j)) {
                    j++;
                }*/
            }
            result[i] = j;
        }
        return result;
    }

    /**
     * 获取字符串的部分匹配表
     * 如：    ABCDABD    ACAAC
     * 匹配表：0000120    [0, 0, 1, 1, 2]
     * @param str
     * @return
     */
    public static int[] next(String str) {
        int[] result = new int[str.length()];
        result[0] = 0; // 如果只有一个，那么肯定是0
        for (int i = 1, j = 0; i < str.length(); i ++) {

            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = result[j - 1];
            }

            if (str.charAt(i) == str.charAt(j)) {
                j++;
            }
            result[i] = j;
        }
        return result;
    }
}
