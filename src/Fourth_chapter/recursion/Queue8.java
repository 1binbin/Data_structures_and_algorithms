package Fourth_chapter.recursion;

/**
 * 八皇后问题
 * 在 8*8 的国际象棋上摆放八个皇后，使其不能互相攻击
 * 即任意两个皇后都不能处于同一行，同一列或同一斜线上
 * <p>
 * 算法思路分析
 * 1.第一个皇后先放在第一行第一列
 * 2.第二个皇后放在第二行第一列、然后判断是否OK，如果不OK，继续放在第二行第二列、第三列、一次把所有的列都放完，找到一个合适的
 * 3.继续第三个皇后，还是第一列、第二列·····直到第8个皇后也能放在一个不冲突的位置，算是找到了一个正确解
 * 4.当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后，放到第一列的所有正确解，全部得到
 * 5.然后回头继续第一个皇后放第二列，后面继续循环执行1,2,3,4的步骤
 * <p>
 * 用一维数据解决问题 arr[8]={0,4,7,5,2,6,1,3} 对应的arr下标表示第几行，即第几个皇后
 * arr[i] = val,val表示第i+1个皇后，放在i+1行的第val+1列
 *
 * @Author hongxiaobin
 * @Time 2022 /4/24-22:00
 */
public class Queue8 {
    //    定于一个max表示共有多少个皇后
    int max = 8;
    //    定义一个数据，保存皇后放置的位置结果
    int[] array = new int[max];
    static int count = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有 %d 种解法",count);
    }

    /**
     * 放置第n个皇后
     * 特别注意check每一次递归时，每一个check中都有for循环，所以会产生回溯
     *
     * @Param: [int n]
     * @Return: void
     */
    private void check(int n) {
//        当n=8时表示八个皇后已经放好了
        if (n == max) {
            print();
            return;
        }
//        如果没有为最后一个，依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
//             先把当前这个皇后放到该行的第1列
            array[n] = i;
//            判断放置第n个皇后在第i列时是否有冲突
            if (judge(n)) {
//                接着放第n+1个皇后，即开始递归了
                check(n + 1);
            }
//            如果冲突，就继续执行arr[n] =  i;即将第n个皇后放置在本行的后移的一个位置

        }
    }

    /**
     * @Param: [int n]
     * @Return: void
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
//            判断是否同一列，同一斜线（斜率为1或-1）
//            不需判断同一行，因为 n 本身每次都在递增，代表着行
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Print().定义一个方法用来打印数组,将皇后摆放的位置输出
     */
    public void print() {
        count++;
        for (int j : array) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
}
