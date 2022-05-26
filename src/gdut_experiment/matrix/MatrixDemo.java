package gdut_experiment.matrix;

/**
 * 图的实现
 *
 * @Author hongxiaobin
 * @Time 2022/5/26-10:14
 */
public class MatrixDemo {
    public static void main(String[] args) {
       /* int[][] value = {
                {1, 2, 3}, {4, 5, 6, 7, 8}, {9}
        };
        Matrix matrix = new Matrix(3, 5, value);
        System.out.println("矩阵为");
        System.out.println(matrix);
        matrix.set(2, 3, 10);
        System.out.println("修改完的矩阵为");
        System.out.println(matrix);
*/
        System.out.println("----------测试邻接矩阵-----------");
        String[] vertexes = {"A", "B", "C", "D"};
        Triple[] edges = {new Triple(0, 1, 10), new Triple(1, 0, 10),
                new Triple(0, 2, 8), new Triple(2, 0, 8),
                new Triple(2, 1, 20), new Triple(1, 2, 20),
                new Triple(1, 3, 9), new Triple(3, 1, 9)};
        MatrixGraph<String> graph = new MatrixGraph<>(vertexes, edges);
        System.out.println(graph);
    }
}

/**
 * 矩阵类
 *
 * @Author: hongxiaobin
 * @Date: 2022/5/26 10:42
 */
class Matrix {
    private static final int MIN_CAPACITY = 6;
    private int rows, columns;
    private int[][] element;

    public Matrix(int rows, int columns) {
        if (rows >= 0 && columns >= 0) {
            this.rows = rows;
            this.columns = columns;

            if (rows < MIN_CAPACITY) rows = MIN_CAPACITY;
            if (columns < MIN_CAPACITY) columns = MIN_CAPACITY;

            this.element = new int[rows][columns];
        } else {
            throw new IllegalArgumentException("矩阵行列数不能小于0");
        }
    }

    public Matrix(int n) {
        this(n, n);
    }

    public Matrix() {
        this(0);
    }

    public Matrix(int rows, int columns, int[][] values) {
        this(rows, columns);
        for (int i = 0; i < values.length && i < rows; i++) {
            for (int j = 0; j < values[i].length && i < columns; j++) {
                this.element[i][j] = values[i][j];
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int get(int x, int y) {
        if (x >= 0 && x < this.rows && y >= 0 && y < this.columns) {
            return this.element[x][y];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void set(int x, int y, int values) {
        if (x >= 0 && x < this.rows && y >= 0 && y < this.columns) {
            this.element[x][y] = values;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        String str = "矩阵" + this.getClass().getName() + "(" + this.rows + "x" + this.columns + ");\n";
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                str += String.format("%12d", this.element[i][j]);
            }
            str += "\n";
        }
        return str;
    }

    public void setRowsColumn(int m, int n) {
        if (m > 0 && n > 0) {
            if (m > this.element.length || n > this.element[0].length) {
                int[][] source = this.element;
                this.element = new int[m][n];
                for (int i = 0; i < this.rows; i++) {
                    for (int j = 0; j < this.columns; j++) {
                        this.element[i][j] = source[i][j];
                    }
                }
            }
            this.rows = m;
            this.columns = n;
        } else {
            throw new IllegalArgumentException("矩阵行列数不小于0");
        }
    }

}

/**
 * 边类
 *
 * @Author: hongxiaobin
 * @Date: 2022/5/26 10:43
 */
class Triple implements Comparable<Triple> {
    int row, column, value;

    public Triple() {
        this(-1, -1, -1);
    }

    public Triple(int row, int column, int value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }

    @Override
    public int compareTo(Triple tri) {
        if (this.row > tri.row) return 1;
        else if (this.row < tri.row) return -1;
        else if (this.column > tri.column) return 1;
        else if (this.column < tri.column) return -1;
        else if (this.value > tri.value) return 1;
        else if (this.value < tri.value) return -1;
        else return 0;
    }
}


/**
 * 邻接矩阵类
 *
 * @Author: hongxiaobin
 * @Date: 2022/5/26 10:52
 */
class MatrixGraph<E extends Comparable<E>> {
    private static final int MAX_WEIGHT = 0x0000ffff;  //最大权值
    private SeqList<E> vertexlist;  //顶点数组
    private Matrix matrix;  //邻接矩阵

    public MatrixGraph() {
        this.vertexlist = new SeqList<E>();
        this.matrix = new Matrix();
    }

    public MatrixGraph(E[] vertexlist) {
        this();
        for (int i = 0; i < vertexlist.length; i++) {
            this.insert(vertexlist[i]);
        }
    }

    public MatrixGraph(E[] vertexs, Triple[] edges) {
        this(vertexs);
        for (int i = 0; i < edges.length; i++) {
            Triple temp = edges[i];
            this.matrix.set(temp.row, temp.column, temp.value);
        }
    }

    public static void main(String[] args) {

    }

    public void insert(E e) {
        this.vertexlist.insert(e);
        int i = this.vertexlist.size() - 1;
        if (i >= this.matrix.getRows()) {
            this.matrix.setRowsColumn(i + 1, i + 1);
        }
        for (int j = 0; j < i; j++) {
            this.matrix.set(i, j, MAX_WEIGHT);
            this.matrix.set(j, i, MAX_WEIGHT);
        }
    }

    private void insert(Triple edge) {
        if (edge.row != edge.column) {
            if (edge.value <= 0 || edge.value > MAX_WEIGHT) {
                edge.value = MAX_WEIGHT;
            }
            this.matrix.set(edge.row, edge.column, edge.value);
        }
    }

    @Override
    public String toString() {
        String str = "顶点数组" + this.vertexlist.toString();
        str += this.matrix.toString();
        return str;
    }
}
