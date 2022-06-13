package Search_Algorithms;

import java.util.Arrays;

/**
 * 斐波那契查找
 *
 * @Author hongxiaobin
 * @Time 2022/5/23-10:49
 */
public class FibonacciSearch {
    private static final int maxSize = 10;
    public static void main(String[] args) {
        int[] arr = {1, 1, 8, 10, 89, 1000, 1234};
        System.out.println(fibSearch(arr, 1234));
    }

    /**
     * 构建斐波那契数列
     * 非递归实现
     *
     * @Param:
     * @Return: 返回斐波那契数列
     */
    public static int[] fib(int maxSize) {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 斐波那契查找
     * 升序，非递归
     *
     * @Param: int[] arr 升序数组 ,int key 目标值
     * @Return: 目标值所在的下标，找不到返回-1
     */
    public static int fibSearch(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;
//        斐波那契分割数值的下标
        int k = 0;
        int mid;
        int[] f = fib(maxSize);
//        获取到斐波那契分割数值的下标
        while (right > f[k]) {
            k++;
        }
//        因为f[k]值可能大于a的长度，扩容，不足的部分使用arr最后的数填充
        int[] temp = Arrays.copyOf(arr, f[k]);
        for (int i = right + 1; i < f[k]; i++) {
            temp[i] = arr[right];
        }
//        使用while循环处理，找到目标值
        while (left <= right) {
            mid = left + f[k - 1] - 1;
            if (key < temp[mid]) {
                right = mid - 1;
//                1.全部元素 =  前面的元素 + 后边的元素
//                2.f[k] = f[k-1] + f[k-2]
//                3.因为前面有f[k-1]个元素，所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
//                4.即在f[k-1] 的前面继续查找，故k--，即下次循环mid = f[k-1-1]-1 + low
                k--;
            } else if (key > temp[mid]) {
                left = mid + 1;
//                1.全部元素 =  前面的元素 + 后边的元素
//                2.f[k] = f[k-1] + f[k-2]
//                3.后面有f[k-2]个元素，所以继续拆分 f[k-2] = f[k-3] + f[k-4]
//                4.即在f[k-2] 的前面继续查找，故k-=2，即下次循环mid = f[k-2-1]-1 + low
                k -= 2;
            } else {
//                返回mid与high中的较小值
                return Math.min(mid, right);
            }
        }
        return -1;
    }
}

