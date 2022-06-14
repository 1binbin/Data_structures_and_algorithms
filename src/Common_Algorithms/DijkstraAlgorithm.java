package Common_Algorithms;

import java.util.Arrays;

/**
 * @Author hongxiaobin
 * @Time 2022/6/14-10:51
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        Graph graph = new Graph(vertex, matrix);
        System.out.println("显示图的信息");
        graph.showGraph();
    }
}

/**
 * 已访问顶点集合类
 *
 * @Author: hongxiaobin
 * @Date: 2022/6/14 10:59
 */
class VisitedVertex {
    //记录各个顶点是否访问过，1表示访问过，0表示未访问
    public int[] already_arr;
    // 每个下标对应的值为前一个顶点的下标，会动态更新
    public int[] dis;
    //记录出发顶点到其他所有顶点的距离，会动态更新
    public int[] pre_visited;

    /**
     * 构造器
     *
     * @Param: lenght 顶点个数 ， index 出发顶点的下标
     * @Return:
     */
    public VisitedVertex(int lenght, int index) {
        this.already_arr = new int[lenght];
        this.pre_visited = new int[lenght];
        this.dis = new int[lenght];
//        初始化dis数组
        Arrays.fill(this.dis, 65535);
//        设置到本身的距离为0
        this.dis[index] = 0;
    }

    /**
     * 判断index对应的顶点是否已经访问过
     *
     * @Param: index 顶点的下标
     * @Return: true表示已经访问过，false表示未访问过
     */
    public boolean in(int index) {
        return this.already_arr[index] == 1;
    }
}

/**
 * 创建图
 *
 * @Author: hongxiaobin
 * @Date: 2022/6/14 10:51
 */
class Graph {
    //    顶点数组
    private char[] vertex;
    //    邻接矩阵
    private int[][] matrix;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    /**
     * 显示图
     *
     * @Param:
     * @Return:
     */
    public void showGraph() {
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%12d", ints[j]);
            }
            System.out.println();
        }
    }
}