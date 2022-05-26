package Tenth_chapter;

/**
 * 分治算法--汉若塔问题
 *
 * @Author hongxiaobin
 * @Time 2022/5/26-9:39
 */
public class Hanoitower {
    private static int count;

    public static void main(String[] args) {
        hanoiTower(23, 'A', 'B', 'C');
        System.out.println("移动次数为 " + count);
    }

    /**
     * 解决汉若塔问题
     *
     * @Param:
     * @Return:
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘从 " + a + " -> " + c);
            count++;
        } else {
            hanoiTower(num - 1, a, c, b);
            System.out.println("第" + num + "个盘从 " + a + " -> " + c);
            count++;
            hanoiTower(num - 1, b, a, c);
        }
    }
}
