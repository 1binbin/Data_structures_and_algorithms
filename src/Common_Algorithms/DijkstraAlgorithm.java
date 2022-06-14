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
        graph.dijkstra(6);
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
    //    已经访问的顶点的集合
    private VisitedVertex vv;

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

    /**
     * 迪杰斯特拉算法实现
     *
     * @Param: index 出发顶点的下标
     * @Return:
     */
    public void dijkstra(int index) {
        vv = new VisitedVertex(matrix[0].length, index);

    }

    /**
     * 更新index下标的顶点到周围顶点的距离和周围顶点的前驱结点
     *
     * @Param: index 当前顶点的下标
     * @Return:
     */
    private void update(int index) {
        int len = 0;
//        根据遍历邻接矩阵的matrix[index]行
        for (int j = 0; j < matrix[index].length; j++) {
//            已有的距离加上当前顶点到邻接矩阵的第j个顶点的距离
            len = vv.getDis(index) + matrix[index][j];
//            如果j没有背范文国并且len小于j的距离，就更新j的距离和前驱结点
            if (!vv.in(j) && len < vv.getDis(j)) {
//                更新j顶点前驱结点为index顶点
                vv.updatePre(j, index);
//                更新出发点到j顶点的距离
                vv.updateDis(j, len);
            }
        }
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

    /**
     * 更新出发顶点到index对应的顶点的距离
     *
     * @Param: index 顶点的下标，len 出发顶点到index对应的顶点的距离
     * @Return: void
     */
    public void updateDis(int index, int len) {
        this.dis[index] = len;
    }

    /**
     * 更新pre这个顶点的前一个顶点的下标为index
     *
     * @Param: pre 顶点的下标，index 前一个顶点的下标
     * @Return:
     */
    public void updatePre(int pre, int index) {
        this.pre_visited[index] = pre;
    }

    /**
     * 返回出发顶点到index对应的顶点的距离
     *
     * @Param: index 顶点的下标
     * @Return: 出发顶点到index对应的顶点的距离
     */
    public int getDis(int index) {
        return dis[index];
    }
}

