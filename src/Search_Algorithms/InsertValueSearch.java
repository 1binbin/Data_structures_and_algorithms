package Search_Algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * 插值查找
 * 适用于均匀分布的数组
 * 注意：前提条件是该数组是有序的
 *
 * @Author hongxiaobin
 * @Time 2022/5/23-9:45
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        //    保证数组为升序
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1234};
        System.out.println(insertValueSearch(arr, 8));
        List<Integer> list = insertValueSearchAll(arr, 1000);
        System.out.println(list);
        System.out.println(insertValueSearchNoRecursive(arr, 1234));
    }

    /**
     * 重载递归插值查找
     *
     * @Param: int[] arr 升序数组, int findVal 目标值
     * @Return: 目标值的下标，找不到返回-1
     */
    public static int insertValueSearch(int[] arr, int findVal) {
        return insertValueSearch(arr, 0, arr.length - 1, findVal);
    }

    /**
     * 插值查找算法
     * 递归，数组升序
     * 只查找第一个满足值的下标
     *
     * @Param: int[] arr 升序数组, int left 左索引, int right 右索引, int findVal 目标值
     * @Return: 目标值的下标，找不到返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
//        如果左指针大于右指针，说明找不到，条件必须需要，否则mid可能越界
        if (left > right || findVal > arr[arr.length - 1] || findVal < arr[0]) {
            return -1;
        }
//        求mid公式重点！！！
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {
//        目标值大于中间值，向右递归
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
//            目标值小于中间值，向左递归
            return insertValueSearch(arr, left, mid - 1, findVal);
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
    public static List<Integer> insertValueSearchAll(int[] arr, int findVal) {
        return insertValueSearchAll(arr, 0, arr.length - 1, findVal);
    }

    /**
     * 递归插值查找
     * 查找所有的目标值的下标
     *
     * @Param: int[] arr 升序数组, int left 左索引, int right 右索引, int findVal 目标值
     * @Return: 返回所有目标值的下标
     */
    public static List<Integer> insertValueSearchAll(int[] arr, int left, int right, int findVal) {
//        如果左指针大于右指针，说明找不到，返回空
        if (left > right || findVal > arr[arr.length - 1] || findVal < arr[0]) {
            return new ArrayList<>();
        }
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {
//        目标值大于中间值，向右递归
            return insertValueSearchAll(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
//            目标值小于中间值，向左递归
            return insertValueSearchAll(arr, left, mid - 1, findVal);
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
     * 非递归实现插值查找
     * 升序
     *
     * @Param: int[] arr 升序数组, int findValue 目标值
     * @Return: 返回目标值的下标，找不到返回-1
     */
    public static int insertValueSearchNoRecursive(int[] arr, int findValue) {
        int left = 0;
        int right = arr.length - 1;
        int mid;
        if (left > right || findValue < arr[left] || findValue > arr[right]) {
//            找不到
            return -1;
        }
        while (left <= right) {
            mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
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
