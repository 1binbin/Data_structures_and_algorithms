package Ninth_chapter;

import java.util.Arrays;

/**
 * @Author hongxiaobin
 * @Time 2022/5/22-11:44
 */
public class MergeSort {
    public static void main(String[] args) {
        int n = 200000000;
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
        int[] temp = new int[arr.length];
        long start = System.currentTimeMillis();
        System.out.println("排序前");
//        System.out.println(Arrays.toString(arr));
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));
        long end = System.currentTimeMillis();
        System.out.println("执行时间为 " + (end - start) + " ms");
    }

    /**
     * 分+合
     *
     * @Param:
     * @Return:
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
//            向左递归分解
            mergeSort(arr, left, mid, temp);
//            向右递归分解
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并的方法
     *
     * @Param: [arr 待排序数组, left 左边有序序列初始索引, mid 中间索引, right 右边索引, temp 暂存数组]
     * @Return:
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
//        初始化左右两边有序序列的索引
        int i = left;
        int j = mid + 1;
//        初始化临时数组的索引
        int t = 0;
//        1.先把左右两边（有序）的数据按照规则填充到暂存数组中
//        直到左右两边的有序序列的索引都到达了末尾
        while (i <= mid && j <= right) {
//            如果左边的数据小于右边的数据，则把左边的数据放入暂存数组中
//            arr[i] <= arr[j]?temp[t++] = arr[i++]:temp[t++] = arr[j++];
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                i++;
            } else {
                temp[t] = arr[j];
                j++;
            }
            t++;
        }
//        2.把有剩余数据的一端一次填充到暂存数组中
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }
//        3.将temp数组的元素拷贝到arr
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft++] = temp[t++];
        }
    }
}
