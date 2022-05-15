package Third_chapter;

/**
 * 匹配字符串
 * 暴力匹配（BF算法）
 * 假设现在str1匹配到i位置，子串str2匹配到j位置，则有:
 * 1.如果当前字符匹配成功（即str[i]==str2[j]），则i++,j++,继续匹配下一个字符
 * 2.如果匹配失败，令i = i-(j-1) ,j=0 相当于每次匹配失败时，i回溯,j设置为0
 *
 * @Author hongxiaobin
 * @Time 2022 /4/26-10:36
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "absccsadsddas";
        String str2 = "sccs";
        System.out.println(violenceMatch(str1, str2));
    }

    /**
     * 暴力匹配（BF算法）实现
     *
     * @param str1 the str 1
     * @param str2 the str 2
     * @return the int
     */
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int s1Len = s1.length;
        int s2Len = s2.length;
//        i索引指向s1,j索引指向s2
        int i = 0;
        int j = 0;
        while (i < s1Len && j < s2Len) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i -= (j - 1);
                j = 0;
            }
        }
        if (j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }
}
