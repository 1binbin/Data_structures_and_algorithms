package Common_Algorithms;

/**
 * 动态规划--背包问题
 *
 * @Author hongxiaobin
 * @Time 2022/5/27-20:22
 */
public class KnapsackProblem {
    public static void main(String[] args) {
//        背包的重量
        int[] w = {1, 4, 3};
//        物品的价值
        int[] val = {1500, 3000, 2000};
//        背包的容量
        int m = 4;
//        物品的个数
        int n = val.length;
//        为了记录商品的存在情况。定义一个二维数组
        int[][] path = new int[n + 1][m + 1];
//        创建二维数组
//        v[i][j]表示在前i个能转入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
//        初始化第一行和第一列，在本程序中可以不处理，默认就是0
        for (int i = 0; i < v.length; i++) {
//            第一列设置为0
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
//            第一行为0
            v[0][i] = 0;
        }

//        根据得到的公式动态处理
//        不处理第一行，故i从1开始
        for (int i = 1; i < v.length; i++) {
//            不处理第一列
            for (int j = 1; j < v[0].length; j++) {
//                公式，注意公式推导i从0开始，这里是从1开始，需要做处理
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
//                    说明，因为i从1开始的，因此公式需要调整以下的情况
//                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
//                    为了记录商品存放在背包的情况，不能直接使用公式，需要使用if-else来处理公式
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }


//        遍历v
        for (int[] ints : v) {
            for (int anInt : ints) {
                System.out.printf("%4d\t", anInt);
            }
            System.out.println();
        }
//        输出最后放入的是哪些商品
        System.out.println("======================================");
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }
}
