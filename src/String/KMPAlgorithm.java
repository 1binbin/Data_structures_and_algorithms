package String;

import java.util.Arrays;

/**
 * KMP算法
 * 解决模式串在文本串是否出现过，如果出现过，返回最早出现的位置
 * 需要先得到子串的部分匹配表（next数组），也就是特征数，即在当前位置，前缀和后缀的最大相同字符的个数
 * 步骤
 * 1.先得到部分匹配表
 * 2.使用部分匹配表完成KMP匹配
 *
 * @Author hongxiaobin
 * @Time 2022 /4/26-10:56
 */
public class KMPAlgorithm {
    /**
     * 测试方法
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext(str2);
        System.out.println(Arrays.toString(next));
        System.out.println(kmpSearch(str1, str2, next));
    }

    /**
     * KMP搜索算法
     *
     * @param str1 源字符串
     * @param str2 子串
     * @param next 子串对应的部分匹配表
     * @return the int -1位找不到，否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
//        遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {
//            需要处理当str1.charAt(i) != str2.charAt(j)，要去调整j的大小
//            KMP的核心点
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
     * 获取一个字符串的部分匹配表
     *
     * @param dest the dest
     * @return the int [ ]
     */
    public static int[] kmpNext(String dest) {
//        创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
//            当dest.charAt(i) != dest.charAt(j)时，我们需要从next[j-1]获取新的j
//            直到发现有dest.charAt(i) == dest.charAt(j)成立时才退出
//            这是KMP算法的核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
//            当dest.charAt(i) == dest.charAt(j)这个条件满足时，部分匹配值就需要加一
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
