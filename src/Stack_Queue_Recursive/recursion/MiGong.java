package Stack_Queue_Recursive.recursion;

/**
 * The type Mi gong.
 *
 * @Author hongxiaobin
 * @Time 2022 /4/11-22:58
 * @Description 用递归解决迷宫回溯问题
 */
public class MiGong {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
//        先创建一个二维数组，模拟迷宫
//        地图 8x7
        int[][] map = new int[8][7];
//        使用 1 表示墙
//        上下全部置为 1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
//        左右全部置为 1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
//        设置其他地方的墙
        map[3][1] = 1;
        map[3][2] = 1;
//        输出地图
        System.out.println("地图的情况");
        showMap(map);
        System.out.println("路线情况1");
        setWay1(map,1,1);
        showMap(map);
        System.out.println("路线情况2");
        setWay2(map,1,1);
        showMap(map);
    }

    private static void showMap(int[][] map) {
        for (int[] value : map) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(value[j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归回溯来给小球找路
     *
     * @param map the map
     * @param i   the
     * @param j   the j
     * @return the way 1
     */
    public static boolean setWay1(int[][] map, int i, int j) {
        /*
         * 使用递归来解决小球找路
         * 说明
         * 1.map为地图
         * 2.i，j为从哪个位置开始找如（1,1）
         * 3.如果小球能到map[6][5]位置，则说明通路找到
         * 4.约定：当map[i][j] 为0表示改点没有走过 ： 当为1时表示墙 ； 2表示通路可以走 ： 3表示该点已经走过但是走不通
         * 5.在走迷宫时需要确定一个策略（方法） 走的顺序，下->右->上->左 （结果与定义的策略相关） 如果该点走不通，再回溯
         * */

//        通路已经找到
        if (map[i][j] == 2) {
            return true;
        } else {
//            如果当前这个点还没有走过
            if (map[i][j] == 0) {
//                按照策略走
//                假定该点可以走通
                map[i][j] = 2;
//                向下走
                if (setWay1(map, i + 1, j)) {
                    return true;
//                    向右走
                } else if (setWay1(map, i, j + 1)) {
                    return true;
//                    向上走
                } else if (setWay1(map, i - 1, j)) {
                    return true;
//                    向左走
                } else if (setWay1(map, i, j - 1)) {
                    return true;
//                    都走不通也就是该点不通，一个死路，置成3
                } else {
                    map[i][j] = 3;
                    return false;
                }
            }else {
                return false;
            }
        }
    }

    /**
     * 修改走的策略，上右下左
     *
     * @param map the map
     * @param i   the i
     * @param j   the j
     * @return the way
     */
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[i][j] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (setWay2(map, i - 1, j)) {
                    return true;
                } else if (setWay2(map, i, j + 1)) {
                    return true;
                } else if (setWay2(map, i + 1, j)) {
                    return true;
                } else if (setWay2(map, i, j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            }else {
                return false;
            }
        }
    }
}
