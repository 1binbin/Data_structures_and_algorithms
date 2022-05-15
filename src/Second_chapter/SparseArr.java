package Second_chapter;

/**
 * @author hongxiaobin
 * @create 2022/2/28-21:04
 * @description 稀疏数组和二维数据的互转
 */
public class SparseArr {
    public static void main(String[] args) {
//        二维数组转稀疏数组
//        创建一个11*11的二维数组，0表示没有棋子，1表示黑棋，2表示蓝棋
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
//        输出原始数组
        System.out.println("----------原始的二维数据----------");
        getArrData(chessArr1);
//        转化成稀疏数组
//        1.先遍历二维数组得到非0的数据的个数
        int sum = 0;
        for (int[] inits : chessArr1) {
            for (int anInt : inits) {
                if (anInt != 0) {
                    sum++;
                }
            }
        }
//        2.创建稀疏数组parseArr[sum+1][3]
        int[][] sparseArr = new int[sum + 1][3];
//        给稀疏数组第一个一维数组赋值
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;
//        记录其他非0的数据
//        使用计数器用于记录第几个非0的数据
        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
//        3.输出稀疏数组
        System.out.println("----------稀疏数组数据----------");
        getArrData(sparseArr);

//       稀疏数组转为二维数组
        int[][] sparseArr2 = new int[3][3];
        sparseArr2[0][0] = 11;
        sparseArr2[0][1] = 11;
        sparseArr2[0][2] = 2;
        sparseArr2[1][0] = 1;
        sparseArr2[1][1] = 2;
        sparseArr2[1][2] = 1;
        sparseArr2[2][0] = 2;
        sparseArr2[2][1] = 3;
        sparseArr2[2][2] = 2;
        System.out.println("----------原始稀疏数组为----------");
        getArrData(sparseArr2);
//        转化为二维数据
        int[][] chessArr2 = new int[sparseArr2[0][0]][sparseArr2[0][1]];
        for (int i = 1; i < sparseArr2.length; i++) {
            chessArr2[sparseArr2[i][0]][sparseArr2[i][1]] = sparseArr2[i][2];
        }
        System.out.println("----------转换为二维数组为----------");
        getArrData(chessArr2);
    }

    private static void getArrData(int[][] arr) {
        for (int[] row : arr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        System.out.println();
    }
}
