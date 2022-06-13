package Sort_Algorithms;

/**
 * 基数排序
 *
 * @Author hongxiaobin
 * @Time 2022/5/22-20:22
 */
public class RadixSort {
    public static void main(String[] args) {
        int n = 80000000;
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
        long start = System.currentTimeMillis();
        System.out.println("排序前");
//        System.out.println(Arrays.toString(arr));
        radixSort(arr);
        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));
        long end = System.currentTimeMillis();
        System.out.println("执行时间为 " + (end - start) + " ms");
    }

    /**
     * 基数排序,升序，正整数
     *
     * @Param:
     * @Return:
     */
    public static void radixSort(int[] arr) {
//        定义一个二维数组，表示10个桶，每个桶就是一个一维数组
//        得到数组中最大的数的位数
        int max = arr[0];
        for (int j : arr) {
            if (j > max) {
                max = j;
            }
        }
//        得到最大数的位数
        int maxLength = (max + "").length();
//        说明1.二维数组包含10个一维数组 2.为了防止放入数组的时候溢出，则每个桶大小定位arr.length
        int[][] bucket = new int[10][arr.length];
//        为了记录每个桶中实际存放的数据，定义一个一维数组来记录各个桶每次放入的数据个数
        int[] bucketElementCounts = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int value : arr) {
//                取最低位
                int digitOfElement = value / n % 10;
//            放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]++] = value;
            }
//        按照桶的插入顺序，放到原来的数组
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
//            如果桶中有数组才放入到原数组
                if (bucketElementCounts[k] != 0) {
//                循环该桶，将数据放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
//            清空桶
                bucketElementCounts[k] = 0;
            }
        }
    }
}
