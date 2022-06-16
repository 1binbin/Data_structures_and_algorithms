package Common_Algorithms;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @Author hongxiaobin
 * @Time 2022/6/15-10:50
 */
public class HorseChessboard {
    //    棋盘的列数
    private static int X;
    //    棋盘的行数
    private static int Y;
    //    创建一个数组，标记棋盘中各个位置是否被访问过
    private static boolean visited[];
    //    标记是否棋盘所有位置被访问
    private static boolean finished;

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
//        测试骑士周游算法
        X = 8;
        Y = 8;
        int row = 1;
        int column = 1;
//        创建棋盘
        int[][] chessBoard = new int[X][Y];
        visited = new boolean[X * Y];
//        测试
        traversalChessBoard(chessBoard, row - 1, column - 1, 1);
        for (int[] rows : chessBoard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
        long end = System.currentTimeMillis();
        System.out.println("运行时间" + (end - begin) + "ms");
    }

    /**
     * 实现马踏棋盘算法
     *
     * @Param: int[][] chessboard 棋盘,int row 所在行(0开始),int column 所在列(0开始),int step 第几步(1开始)
     * @Return:
     */
    public static void traversalChessBoard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
//        标记已访问
        visited[row * X + column] = true;
//        获取当前位置下一步可以走的位置
        ArrayList<Point> ps = next(new Point(column, row));
        sort(ps);
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);
//            没有访问过
            if (!visited[p.y * X + p.x]) {
                traversalChessBoard(chessboard, p.y, p.x, step + 1);
            }
        }
        if (step < X * Y && !finished) {
//            棋盘置零
            chessboard[row][column] = 0;
//            回溯
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 可走的位置
     *
     * @Param: Point curPoint 当前位置
     * @Return:
     */
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    /**
     * 根据当前这一步的所有的下一步的选择位置进行非递减排序
     *
     * @Param:
     * @Return:
     */
    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                return Integer.compare(count1, count2);
            }
        });
    }
}

class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public Point() {
    }
}