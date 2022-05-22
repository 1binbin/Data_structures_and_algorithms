package Ninth_chapter;

/** 基数排序
 * @Author hongxiaobin
 * @Time 2022/5/22-20:22
 */
public class RadixSort {
    public static void main(String[] args) {
       int[] arr = {53,3,542,748,14,214};
    }
    /** 基数排序
     * @Param:
     * @Return:
     */
    public static void radixSort(int[] arr){
//        定义一个二维数组，表示10个桶，每个桶就是一个一维数组
//        说明1.二维数组包含10个一维数组 2.为了防止放入数组的时候溢出，则每个桶大小定位arr.length
        int[][] bucket = new int[10][arr.length];
    }
}
