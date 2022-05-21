package Ninth_chapter;

import java.util.Arrays;

/**
 * 希尔排序
 *
 * @Author hongxiaobin
 * @Time 2022/5/21-10:58
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {2,4,6,1,2,-4,-6};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 希尔排序（交换式）,升序
     *
     * @Param:
     * @Return:
     */
    public static void shellSort(int[] arr) {
//        辅助元素，用于交换
        int temp;
//        分组
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //            遍历各组中的所有元素，组队的步长为组数，也就是 arr[0] 与 arr[0 + 组数]组队
                for (int j = i - gap; j >= 0; j -= gap) {
                    //                如果当前元素大于加上步长的那个元素，说明交换，即组内交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }
}
