package Ninth_chapter;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @Author hongxiaobin
 * @Time 2022/5/21-10:23
 */
public class InsertSort {
    public static void main(String[] args) {
        int n = 20000000;
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
        long start = System.currentTimeMillis();
        System.out.println("排序前");
//        System.out.println(Arrays.toString(arr));
        insertSort(arr);
        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));
        long end = System.currentTimeMillis();
        System.out.println("执行时间为 " + (end - start) + " ms");
    }

    /**
     * 插入排序，升序
     * O(n^2)
     *
     * @Param:
     * @Return:
     */
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
//           待插入的数
            int insertVal = arr[i];
//           待插入的索引
            int insertIndex = i - 1;
//           从无序表的前一个元素开始找插入位置，在第一个比其小的后面插入
//           insertIndex >=0保证在给InsertVal找插入位置，不越界
//           insertVal < arr[insertIndex] 保证待插入的数还没有找到适当的位置
//           就需要将insertIndex前移
//            降序：将insertVal < arr[insertIndex] 改为 insertVal > arr[insertIndex]
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
//                前移
                insertIndex--;
            }
//            当退出while循环时，说明插入的位置找到，insertIndex + 1
//            优化：当需要插入的元素在有序表的最后，即不需要赋值插入
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
    }
}
