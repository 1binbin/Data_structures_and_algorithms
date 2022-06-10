package Ninth_chapter;

import java.util.Arrays;

/**
 * @Author hongxiaobin
 * @Time 2022/5/22-11:03
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {28, 7, 39, 10, 65, 14, 61, 17, 50, 21};
//        int[] arr = {25,84,21,47,15,27,68,35,20};
        quickSort(arr);
    }

    public static void quickSort(int[] arr) {
        quickSort1(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序，升序
     * 尚硅谷
     *
     * @Param: int[] arr 待排序数组, int left 数组最左边索引, int right 数组最右边索引
     * @Return:
     */
    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
//        找到中轴值
        int pivot = arr[(left + right) / 2];
//        只要左边索引小于右边索引则循环
        while (l < r) {
//            在中轴的左边一直找，直到找到一个左边值大于中轴值
            while (arr[l] < pivot) {
//                左索引往前移
                l++;
            }
//            在中轴的右边一直找，直到找到一个右边值小于中轴值
            while (arr[r] > pivot) {
//                右边索引后移
                r--;
            }
//            如果左索引大于等于右索引，说明pivot左边全部是小于等于pivot，右边全部大于等于pivot
            if (l >= r) {
                break;
            }
//            交换
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
//            如果交换完之后发现arr[l]==pivot,需要前移
            if (arr[l] == pivot) {
                r--;
            }
            if (arr[r] == pivot) {
                l++;
            }
        }
//        如果 l==r，需要让l++,r--，否则出现栈溢出
        if (l == r) {
            l++;
            r--;
        }
//        向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        if (right > l) {
            quickSort(arr, l, right);
        }
    }

    /**
     * 快速排序
     *
     * @Param:
     * @Return:
     */
    public static void quickSort1(int[] arr, int low, int high) {
        int i, j, temp, t;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        //temp就是基准位
        temp = arr[low];
        while (i < j) {
            //先看右边，依次往左递减
            while (temp <= arr[j] && i < j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp >= arr[i] && i < j) {
                i++;
            }
            //如果满足条件则交换
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
            System.out.println(Arrays.toString(arr));
        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSort1(arr, low, j - 1);
        //递归调用右半数组
        quickSort1(arr, j + 1, high);
    }

}
