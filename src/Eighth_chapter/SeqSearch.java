package Eighth_chapter;

/**
 * 线性查找算法
 *
 * @Author hongxiaobin
 * @Time 2022/5/23-9:34
 */
public class SeqSearch {
    public static void main(String[] args) {
        int arr[] = {1,9,11,-1,34,89};
        System.out.println(seqSearch(arr,-1));
    }
    /**线性查找
     * 逐一比对，发现有相同值，就返回下标，否则返回-1
     * 查找第一个与目标值相同的值的下标
     * @Param: int[] arr 原数组 ,int value 目标值
     * @Return:
     */
    public static int seqSearch(int[] arr ,int value){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value){
                return i;
            }
        }
        return -1;
    }
}
