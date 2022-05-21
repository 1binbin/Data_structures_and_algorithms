package Ninth_chapter;

import java.util.Arrays;

/** 冒泡排序
 * @Author hongxiaobin
 * @Time 2022/5/20-22:40
 */
public class BubbleSort {
    public static void main(String[] args) {
        int n = 2000;
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }
        /*
//        冒泡排序的演变过程
//        第一趟排序，就是将最大的数排在最后
//        临时变量
        System.out.println("演示冒泡排序演变过程");
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
//            如果前面的数比后面的数大就交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第一趟排序后的数组");
        System.out.println(Arrays.toString(arr));

//        第二趟排序，将第二大的数排在倒数第二位
        for (int i = 0; i < arr.length - 1 - 1; i++) {
//            如果前面的数比后面的数大就交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第二趟排序后的数组");
        System.out.println(Arrays.toString(arr));
        System.out.println("以此类推·······");
        System.out.println();
        System.out.println("经过冒泡排序后······");
        System.out.println(Arrays.toString(arr));
         */
        long start = System.currentTimeMillis();
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));
        long end = System.currentTimeMillis();
        System.out.println("执行时间为 " + (end - start) + " ms");
    }

    /**
     * 冒牌排序,升序
     * 时间复杂度O(n^2)
     *
     * @Param: int[] arr 待排序数组序列
     * @Return:
     */
    public static void bubbleSort(int[] arr) {
//        辅助变量，用于交换
        int temp;
//        标识变量，标识是否进行过交换 ,true表示交换过
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
//                升序排序
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
//            说明在一趟排序中，一次交换都没有发生过
            if (!flag) {
                break;
            } else {
//                重置flag
                flag = false;
            }
        }
    }
}
