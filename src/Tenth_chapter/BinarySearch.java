package Tenth_chapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 * 注意：前提条件是该数组是有序的
 *
 * @Author hongxiaobin
 * @Time 2022/5/23-9:45
 */
public class BinarySearch {
    public static void main(String[] args) {
        //    保证数组为升序
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1234};
        System.out.println(binarySearch(arr, 8));
        List<Integer> list = binarySearchAll(arr, 1000);
        System.out.println(list);
        System.out.println(binarySearchNoRecursive(arr, 1234));
    }

    /**
     * 重载递归二分查找
     *
     * @Param: int[] arr 升序数组, int findVal 目标值
     * @Return: 目标值的下标，找不到返回-1
     */
    public static int binarySearch(int[] arr, int findVal) {
        return binarySearch(arr, 0, arr.length - 1, findVal);
    }

    /**
     * 二分查找算法
     * 递归，数组升序
     * 只查找第一个满足值的下标
     *
     * @Param: int[] arr 升序数组, int left 左索引, int right 右索引, int findVal 目标值
     * @Return: 目标值的下标，找不到返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
//        如果左指针大于右指针，说明找不到，返回-1
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
//        目标值大于中间值，向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
//            目标值小于中间值，向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    /**
     * 重载查找所有值
     *
     * @Param: int[] arr 升序数组, int findVal 目标值
     * @Return: 所有的目标值的下标的集合
     */
    public static List<Integer> binarySearchAll(int[] arr, int findVal) {
        return binarySearchAll(arr, 0, arr.length - 1, findVal);
    }

    /**
     * 递归二分查找
     * 查找所有的目标值的下标
     *
     * @Param: int[] arr 升序数组, int left 左索引, int right 右索引, int findVal 目标值
     * @Return: 返回所有目标值的下标
     */
    public static List<Integer> binarySearchAll(int[] arr, int left, int right, int findVal) {
//        如果左指针大于右指针，说明找不到，返回空
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
//        目标值大于中间值，向右递归
            return binarySearchAll(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
//            目标值小于中间值，向左递归
            return binarySearchAll(arr, left, mid - 1, findVal);
        } else {
//            1.在找到mid索引值，不要马上返回
//            2.向mid左边索引值扫描，将所有满足值的下标加入到集合
//            3.向mid右边索引值扫描，将所有满足值的下标加入到集合
            List<Integer> resIndexlist = new ArrayList<>();
            resIndexlist.add(mid);
//            向左扫描
            int temp = mid - 1;
            while (temp >= 0 && arr[temp] == findVal) {
                resIndexlist.add(temp--);
            }
//            向右扫描
            temp = mid + 1;
            while (temp <= arr.length - 1 && arr[temp] == findVal) {
                resIndexlist.add(temp++);
            }
            return resIndexlist;
        }
    }

    /**
     * 非递归实现二分查找
     * 升序
     *
     * @Param: int[] arr 升序数组, int findValue 目标值
     * @Return: 返回目标值的下标，找不到返回-1
     */
    public static int binarySearchNoRecursive(int[] arr, int findValue) {
        int left = 0;
        int right = arr.length - 1;
        int mid;
        if (left > right || arr.length == 0 || arr == null) {
//            找不到
            return -1;
        }
        while (left <= right) {
            mid = (left + right) / 2;
            if (findValue == arr[mid]) {
                return mid;
            } else if (findValue > arr[mid]) {
//                向右查找
                left = mid + 1;
            } else if (findValue < arr[mid]) {
//                向左查找
                right = mid - 1;
            }
        }
        return -1;
    }
}
