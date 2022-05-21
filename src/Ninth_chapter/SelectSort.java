package Ninth_chapter;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @Author hongxiaobin
 * @Time 2022/5/21-9:41
 */
public class SelectSort {
    public static void main(String[] args) {
        int n = 2000;
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }
        long start = System.currentTimeMillis();
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        selectSort(arr);
        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));
        long end = System.currentTimeMillis();
        System.out.println("执行时间为 " + (end - start) + " ms");
    }

    /**
     * 选择排序,升序
     * 时间复杂度O(n^2)
     *
     * @Param: int[] arr 待排序数组
     * @Return:
     */
    public static void selectSort(int[] arr) {
//        轮次数： n - 1
        for (int i = 0; i < arr.length - 1; i++) {
//            最小值的下标
            int minIndex = i;
//        保存最小值
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
//                存在新的最小值
                if (min > arr[j]) {
//                    重置最小值
                    min = arr[j];
                    minIndex = j;
                }
            }
//            交换最小值与第一个元素
//            优化：如果最小值的下标在找最小值的第一个元素时，不需要交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
