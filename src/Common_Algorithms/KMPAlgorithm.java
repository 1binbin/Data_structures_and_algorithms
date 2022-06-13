package Common_Algorithms;

import java.util.Arrays;

/**
 * KMP算法
 *
 * @Author hongxiaobin
 * @Time 2022/5/28-14:49
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext(str2);
        System.out.println(Arrays.toString(next));
        System.out.println(kmpSearch(str1, str2, next));
    }

    /**
     * KMP搜索算法
     *
     * @Param: String str1 目标字符串  String str2 子串  int[] next 子串对应的部分匹配表
     * @Return: 如果找到返回第一个匹配的位置，否则返回-1
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
//            核心！需要处理不想等的情况,去调整j的大小
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 获取部分匹配表
     *
     * @Param: String dest 字符串
     * @Return: int[] 匹配表
     */
    public static int[] kmpNext(String dest) {
//        创建一个数组，保存部分匹配值
        int[] next = new int[dest.length()];
//        如果dest的长度为1，它的部分匹配值为0
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
//            当dest.charAt(i) != dest.charAt(j)需要从next[j-1]获取新的j
//            知道发现有dest.charAt(i) == dest.charAt(j)成立才退出
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
//            当dest.charAt(i) == dest.charAt(j)这个条件满足时，部分匹配值就是要加1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
