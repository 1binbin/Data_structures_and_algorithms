package Common_Algorithms;

import java.util.Arrays;

/**
 * Prim算法-最小生成树（修路问题）
 *
 * @Author hongxiaobin
 * @Time 2022/6/10-22:20
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        /*创建图*/
//        顶点
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
//        领接矩阵，行分别为A-G，列分别为A-G,10000表示权值较大不会被选择
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };
//        创建MGraph对象
        MGraph mgraph = new MGraph(verxs);
//        创建一个MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(mgraph, verxs, data, weight);
//        输出
        minTree.showGraph(mgraph);
//        输出路径
        System.out.println("路径为");
        minTree.prim(mgraph, 0);
    }
}

/**
 * 创建最小生成树 ->村庄的路线图
 *
 * @Author: hongxiaobin
 * @Date: 2022/6/13 0:47
 */
class MinTree {
    /**
     * 创建图的邻接矩阵
     *
     * @Param: MGraph graph 图对象, int verrxs 图对应的顶点个数, char data 图各个顶点的值, int[][] weight 图的领接矩阵
     * @Return:
     */
    public void createGraph(MGraph graph, int verxs, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    /**
     * 显示图的邻接矩阵
     *
     * @Param: MGraph graph 图对象
     * @Return: void
     */
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 编写prime算法，得到最小生成树
     *
     * @Param: MGraph graph 图对象, int start 起始顶点,如'A'下标为0传入0
     * @Return: void
     */
    public void prim(MGraph graph, int v) {
//        visited数组用来记录顶点是否被访问过，初始化为 0 都没有被访问过
        int[] visited = new int[graph.verxs];
//        把当前这个结点设置为已访问过
        visited[v] = 1;
//        用h1,h2记录两个顶点的下标，h1是起始顶点，h2是当前顶点
        int h1 = -1, h2 = -1;
//        将minWeight初始化一个大数，后面遍历过程中会被替换
        int minWeight = 10000;
//        因为有graph.verxs个顶点，故生成的边数为graph.verxs-1，即查找的次数
        for (int k = 1; k < graph.verxs; k++) {
//            循环是确定每一次生成的子图和哪个结点的距离最近
//            i结点表示被访问过的结点
            for (int i = 0; i < graph.verxs; i++) {
//                j结点表示没有被访问过的结点
                for (int j = 0; j < graph.verxs; j++) {
//                    只要访问过和没访问过的结点间的权值小于minWeight，就更新minWeight和h1,h2，即找到最小的权值
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
//            找到一条边最小
            System.out.println(graph.data[h1] + "->" + graph.data[h2] + ":" + minWeight);
//            将当前结点标记为已访问过
            visited[h2] = 1;
//            重置minWeight
            minWeight = 10000;
        }
    }
}

/**
 * 创建图
 *
 * @Author: hongxiaobin
 * @Date: 2022/6/13 0:47
 */
class MGraph {
    //    表示图的结点个数
    int verxs;
    //    存放结点数据
    char[] data;
    //    存放边，就是邻接矩阵
    int[][] weight;

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
