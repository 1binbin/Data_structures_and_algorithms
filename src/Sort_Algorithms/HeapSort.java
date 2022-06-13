package Sort_Algorithms;

import java.util.Arrays;

/**
 * 堆排序（树的应用）
 * 基本思路
 * 1.将无序序列构建成一个堆，根据升序（大顶堆）降序（小顶堆）需求选择大顶堆或者小顶堆
 * 2.将堆顶元素与末尾元素交换，将最大元素沉到数组末端
 * 3.重新调整结构，使其满足堆定义，然后继续交换堆顶元素和当前末尾元素，反复执行上述步骤，直到整个序列有序
 * 4.时间复杂度O(nlogn)
 *
 * @Author hongxiaobin
 * @Time 2022/4/28-11:13
 */
public class HeapSort {
    public static void main(String[] args) {
//        要求将数组进行升序排列
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
    }

    /**
     * 编写一个堆排序的方法
     *
     * @param arr the arr
     */
    public static void heapSort(int[] arr) {
        System.out.println("堆排序");
        int temp;
        adjustHeap(arr,1,arr.length);
//        完成最终代码
//        将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length/2-1; i >=0; i--) {
            adjustHeap(arr,i,arr.length);
        }
//        2.将堆顶元素与末尾袁旭交换，将最大元素沉到数组末端
//        3.重新调增结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调增+交换步骤，知道整个序列有序
        for (int i = arr.length-1; i >0 ; i--) {
//            交换
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,i);
        }
        System.out.println("数组"+Arrays.toString(arr));
    }

    /**
     * 将一个数组（二叉树）调整成一个大顶堆
     * 完成将以 i 对应的非叶子结点的树调整为大顶堆
     * 如{4,6,8,5,9} => i=1 => {4,9,8,5,6}
     * 如果再次调用方法，则 i=0 在 {4,9,8,5,6} 基础上调整 得到 {9,6,8,5,4}
     *
     * @param arr    待调整的数组
     * @param i      非叶子结点在数组中的索引
     * @param length 表示对多少个元素继续调整，length在逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
//        !!!!!!总体思路 1.找到 i 对应的那棵树 寻找它的子节点中是否有比 父节点大的值，有就交换给 父节点，保证 i 的位置是这棵树中最大的那个值
//        先去除当前元素的值，保存在临时变量中
        int temp = arr[i];
//        开始调整
//        1.int k = 2 * i + 1; k指向的是 i 结点的左子节点
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
//            说明左子节点的值 小于 右子节点的值
            if (k + 1 < length && arr[k] < arr[k + 1]) {
//                让 k 指向右子节点，因为需要找到最大值
                k++;
            }
//            如果现在所指的位置（左子节点或右子节点）大于temp（非叶子结点），就交换
            if (arr[k] > temp) {
//                把较大的值赋给当前这个结点
                arr[i] = arr[k];
//                让i指向k继续循环比较
                i = k;
            } else {
//                原因：当前结点没有其他的子树，因为是从下到上调整，所以在找到比 i 位置大的元素之后可以直接退出循环了
                break;
            }
        }
//        循环结束后，已经将以 i 为父节点（局部调整）的数的最大值，放在了最顶上
//        将temp值放到调整后的位置
        arr[i] = temp;
    }
}
