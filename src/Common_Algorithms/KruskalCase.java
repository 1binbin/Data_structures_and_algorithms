package Common_Algorithms;

import java.util.Arrays;

/**
 * 克鲁斯卡尔算法（Kruskal)-最小生成树（公交车站问题）
 *
 * @Author hongxiaobin
 * @Time 2022/6/13-17:22
 */
public class KruskalCase {
    //    使用INF常量表示无穷大，即两个顶点不能连通
    private static final int INF = Integer.MAX_VALUE;
    //    顶点数组
    private final char[] vertexs;
    //    邻接矩阵
    private final int[][] matrix;
    //    边的个数
    private int edgeNum;

    public KruskalCase(char[] vertexs, int[][] matrix) {
//        初始化顶点个数和边的个数
        int vlen = vertexs.length;
//        初始化顶点
        this.vertexs = new char[vlen];
//        使用深拷贝方式，为了不改变原数组
        System.arraycopy(vertexs, 0, this.vertexs, 0, vertexs.length);
//        初始化边
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, vlen);
        }
//        统计边，深度拷贝，为了不改变原数组
        for (int i = 0; i < vlen; i++) {
//            不统计自身
            for (int j = i + 1; j < vlen; j++) {
//                边有效，则统计边的个数
                if (this.matrix[i][j] != INF) {
                    this.edgeNum++;
                }
            }
        }
    }

    /**
     * 测试用例
     *
     * @Param:
     * @Return:
     */
    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
//        邻接矩阵 行列对应顶点分别为：A B C D E F G
//        自身为0，不能连接为INF
        int[][] matrix = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };
        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
//        输出构建的图
        kruskalCase.print();
        kruskalCase.kruskal();
    }

    /**
     * 克鲁斯卡尔算法（Kruskal)
     *
     * @Param:
     * @Return:
     */
    public void kruskal() {
//        表示最后结果数组的索引
        int index = 0;
//        保存已有最小生成树的每个顶点在最小生成树中的终点
        int[] ends = new int[edgeNum];
//        创建结果数组，保存最后的最小生成树
        EData[] result = new EData[edgeNum];
//        获取图中所有边的结合
        EData[] edges = getEdges();
        System.out.println("图的边的集合= " + Arrays.toString(edges) + "\n共 " + edges.length + " 条边");
//        排序
        sortEdges(edges);
//        遍历edges数组，将边添加到最小生成树中，判断是准备加入的边是否形成回路，如果不形成回路，则加入，否则不加入
        for (int i = 0; i < edgeNum; i++) {
//            获取第i条边的两端顶点
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);
//            获取p1这个顶点在已有最小生成树中的终点
            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);
//            如果p1和p2不形成回路，则加入到最小生成树中
            if (m != n) {
//                设置m在已有最小生成树中的终点
                ends[m] = n;
//                将第i条边添加到最小生成树中
                result[index++] = edges[i];
            }
        }
//        统计并打印最小生成树
        System.out.println("最小生成树为");
        for (int i = 0; i < index; i++) {
            System.out.println(result[i]);
        }
    }

    /**
     * 打印邻接矩阵
     *
     * @Param:
     * @Return:
     */
    public void print() {
        System.out.println("邻接矩阵为：\n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 对边进行排序，使用冒泡排序
     *
     * @Param: EData[] edges 边数组
     * @Return: void
     */
    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 返回顶点的下标
     *
     * @Param: char ch 顶点值
     * @Return: int 下标值
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取图中的边放入到EData数组中，后面需要遍历该数组
     * EData[] 形式为{['A','B',12]······}
     *
     * @Param:
     * @Return: EData[] 边数组
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点，用户判断是否形成回路
     *
     * @Param: int i 下标 ,int[] ends 记录各个顶点对应的终点，在遍历过程中逐步更新
     * @Return: int 返回的是终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }
}

/**
 * 创建一个类，它的对象实例表示一条边
 *
 * @Author: hongxiaobin
 * @Date: 2022/6/13 17:42
 */
class EData {
    //    边的一个点
    char start;
    //    边的另外一个点
    char end;
    //    边的权值
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    /**
     * 显示重写toString方法，输出边的信息
     *
     * @Param:
     * @Return:
     */
    @Override
    public String toString() {
        return "EDat[<" + start + "," + end + "> , weight=" + weight + ']';
    }
}