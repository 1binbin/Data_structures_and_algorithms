package Common_Algorithms;

import java.util.Arrays;

/**
 * @Author hongxiaobin
 * @Time 2022/6/14-12:44
 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 25535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};
//        创建图对象
        FGraph graph = new FGraph(vertex.length, matrix, vertex);
        graph.floyd();
        graph.show(vertex);
    }
}

/**
 * 创建图
 *
 * @Param:
 * @Return:
 */
class FGraph {
    //    存放顶点的数组
    private char[] vertex;
    //    保存从各个顶点出发到其他顶点的最短距离，也是最后结果的数组
    private int[][] dis;
    //    保存到达目标结点的前驱顶点
    private int[][] pre;

    /**
     * 构造器
     *
     * @Param: int length 顶点个数,int[][] matrix 邻接矩阵,char[] vertex 顶点数组
     * @Return:
     */
    public FGraph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
//        对pre初始化，注意存放的事前驱顶点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    /**
     * 显示pre和dis
     *
     * @Param:
     * @Return:
     */
    public void show(char[] vertex) {
        for (int k = 0; k < dis.length; k++) {
//            输出pre
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[pre[k][i]] + "\t");
            }
            System.out.println();
//            输出dis
            for (int i = 0; i < dis.length; i++) {
                System.out.print("(" + vertex[k] + "到" + vertex[i] + "的最短路径为" + dis[k][i] + ")\t");
            }
            System.out.println();
            System.out.println();
        }
    }

    /**
     * 弗洛伊德算法实现
     *
     * @Param:
     * @Return:
     */
    public void floyd() {
//        变量保存距离
        int len;
//        遍历中间顶点
        for (int k = 0; k < dis.length; k++) {
//            遍历出发顶点
            for (int i = 0; i < dis.length; i++) {
//                遍历到达顶点
                for (int j = 0; j < dis.length; j++) {
//                    从i顶点出发经过k顶点到j顶点的距离
                    len = dis[i][k] + dis[k][j];
//                    如果len更小则更新
                    if (len < dis[i][j]) {
                        dis[i][j] = len;
//                        更新前驱顶点
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
}
