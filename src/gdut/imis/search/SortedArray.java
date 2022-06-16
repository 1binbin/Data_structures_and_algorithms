package gdut.imis.search;

import java.util.Scanner;

/**
 * 二分法查找
 *
 * @Author hongxiaobin
 * @Time 2022/6/16-10:14
 */
public class SortedArray {
    public static void main(String[] args) {
        Integer[] values = new Integer[]{2, 7, 10, 23, 54, 77, 89, 100, 201, 290};
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要查找的值");
        int key = scanner.nextInt();
        System.out.println(key + " 在数组中的位置为 " + binarySearch(values, key));
    }

    public static <T extends Comparable<? super T>> int binarySearch(T[] value, T key) {
        return binarySearch(value, 0, value.length - 1, key);
    }

    private static <T extends Comparable<? super T>> int binarySearch(T[] values, int begin, int end, T key) {
        if (values.length < 0 || begin < 0 || end > values.length || begin > end) {
            return -1;
        }
//        查找
        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (key.compareTo(values[mid]) == 0) {
                return mid;
            } else if (key.compareTo(values[mid]) < 0) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return -1;
    }
}
