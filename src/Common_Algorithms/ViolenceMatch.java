package Common_Algorithms;

/**
 * 暴力匹配算法
 *
 * @Author hongxiaobin
 * @Time 2022/5/28-14:10
 */
public class ViolenceMatch {
    public static void main(String[] args) {
//        测试
        String str1 = "adgetwe";
        String str2 = "getq";
        int index = violenceMatch(str1, str2);
        System.out.println(index);
    }

    /**
     * 暴力匹配算法实现
     *
     * @Param: String str1 目标串, String str2 子串
     * @Return:
     */
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int s1Len = s1.length;
        int s2Len = s2.length;
//        指向s1
        int i = 0;
//        指向s2
        int j = 0;
        while (i < s1Len && j < s2Len) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
//        判断是否匹配成功
        if (j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }
}
