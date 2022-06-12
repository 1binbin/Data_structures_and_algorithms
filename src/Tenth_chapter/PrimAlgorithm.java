package Tenth_chapter;

/**
 * @Author hongxiaobin
 * @Time 2022/6/10-22:20
 */
public class PrimAlgorithm {
    public static void main(String[] args) {

    }
}


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
