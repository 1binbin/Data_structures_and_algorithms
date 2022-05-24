package Seventh_chapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 图的创建与操作实现
 *
 * @Author hongxiaobin
 * @Time 2022/5/18-19:53
 */
public class Graph {
    //    存储顶点的集合
    private ArrayList<String> vertexList;
    //    存储对应的邻接矩阵
    private int[][] edges;
    //    表示变的数目
    private int numOfEdges;
    //    定义boolean[]记录每个结点是否被访问
    private boolean[] isVisted;
    private int n;

    /**
     * 构造器
     *
     * @Param: int n 为顶点的个数
     * @Return:
     */
    public Graph(int n) {
//        初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        this.n = n;

    }

    public static void main(String[] args) {
//        测试图是否创建正确
//        结点个数
        int n = 5;
//        结点值
        String[] vertexValue = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        for (String vertex : vertexValue) {
            graph.insertVertex(vertex);
        }
//        添加边
//        A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
//        显示邻接矩阵
        graph.showGraph();
//        测试DFS
        System.out.println("DFS深度优先遍历");
        graph.dfs();
        System.out.println();
//        测试BFS
        System.out.println("BFS广度优先遍历");
        graph.bfs();
    }

    /**
     * 返回节点的个数
     *
     * @Param:
     * @Return:
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 返回边的个数
     *
     * @Param:
     * @Return:
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 返回结点i对应的数据
     *
     * @Param: int i 为下标
     * @Return:
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    /**
     * 返回v1和v2的权值
     *
     * @Param: int v1 ,v2 为两个顶点
     * @Return:
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    /**
     * 插入顶点
     *
     * @Param: String vertex  表示顶点的值
     * @Return:
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @Param: int v1 ,v2为任意两个顶点的下标即第几个顶点
     * int weight 为权值，即0表示不直接相连，1表示直接相连
     * @Return:
     */
    public void insertEdge(int v1, int v2, int weight) {
//        v1 与 v2 的关系应该有两个，下标互换
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
//        边数增加
        numOfEdges++;
    }

    /**
     * 显示矩阵
     *
     * @Param:
     * @Return:
     */
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 得到当前结点的第一个邻接节点的下标
     *
     * @Param: int index 当前结点
     * @Return: int 第一个邻接节点的下标，如果存在就返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接节点的下标返回下一个邻接节点
     *
     * @Param: int v1 前一个邻接节点的行下标，int v2 前一个邻接节点的列下标
     * @Return:
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 深度遍历 DFS 不唯一
     * 思路分析
     * 1、访问初始结点，并标记结点v为已访问
     * 2、查找结点v的第一个邻接节点w
     * 3、若w存在，则继续执行步骤4，如果w不存在，则回到第一步，将从v的下一个结点继续
     * 4、若w未被访问，对w进行深度优先遍历递归（即把w当做另一个v，然后执行步骤123）
     * 5、查找结点v的w邻接节点的下一个邻接节点，转到步骤3
     *
     * @Param:
     * @Return:
     */
    private void dfs(boolean[] isVisted, int i) {
//        首先访问该节点，输出
        System.out.print(getValueByIndex(i) + "->");
//        将结点设置为已访问
        isVisted[i] = true;
//        得到这个结点的第一个邻接节点
        int w = getFirstNeighbor(i);
        while (w != -1) {
//            说明存在w
            if (!isVisted[w]) {
                dfs(isVisted, w);
            }
//            w已经被访问过
//            查找下一个邻接节点
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 对dfs进行重载，遍历所有的结点，并进行dfs
     *
     * @Param:
     * @Return:
     */
    public void dfs() {
        isVisted = new boolean[n];
//        遍历所有的结点，进行回溯
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisted[i]) {
                dfs(isVisted, i);
            }
        }
    }

    /** 广度遍历BFS 不唯一
     * 一层一层遍历
     * 步骤
     * 1.访问初始结点v并标记结点v已访问
     * 2.结点v入队列，当队列非空时，继续执行，否则算法结束
     * 3.出队列，取得队头结点u
     * 4.查找结点u的第一个邻接点w
     * 5.若结点u的邻接点w不存在，则转到步骤3，否则循环执行以下三个步骤
     * 5.1若结点w尚未访问，则访问结点w并标记为已访问
     * 5.2结点w入队列
     * 5.3查找结点u的继w邻接点后的下一个邻接点w，找到步骤5
     * @Param:
     * @Return:
     */
    public void bfs(){
        isVisted = new boolean[n];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisted[i]){
                bfs(isVisted,i);
            }
        }
    }
    /**
     * 对一个结点广度优先遍历
     *
     * @Param: boolean[] isVisted 记录是否被访问
     * @Return:
     */
    private void bfs(boolean[] isVisted, int i) {
//        表示队列的头结点对应下标
        int u;
//        邻接节点
        int w;
//        队列，记录结点访问的顺序
        LinkedList queue = new LinkedList();
//        访问结点，输出结点信息
        System.out.print(getValueByIndex(i) + "->");
//        标记已访问
        isVisted[i] = true;
//        将结点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
//            取出队列的头结点下标
            u = (Integer) queue.removeFirst();
//            得到第一个邻接节点的下标
            w = getFirstNeighbor(u);
            while (w != -1) {
//                找到了
//                是否访问过
                if (!isVisted[w]) {
                    System.out.print(getValueByIndex(w) + "->");
                    isVisted[w] = true;
                    queue.addLast(w);
                }
//                已经访问过，以u为前驱找w后面的下一个邻接点
//                体现出广度优先
                w = getNextNeighbor(u, w);
            }
        }
    }
}
