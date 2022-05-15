package Sixth_chapter;

/**
 * 顺序存储二叉树，用n表示第几个元素（从0开始，从上到下，从左到右数）
 * 每个元素的父节点为：（n-1)/2
 * 左子节点：2n+1
 * 右子节点：2n+2
 * @Author hongxiaobin
 * @Time 2022 /4/27-17:38
 */
public class ArrBinaryTreeDemo {
    /**
     * 测试方法
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
//        前序遍历 1,2,4,5,3,6,7
        System.out.println("前序遍历");
        arrBinaryTree.preOrder();
    }
}

/**
 * 实现二叉树顺序存储的功能类
 */
class ArrBinaryTree {
    //    存储数据节点的数组
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //    重载preOrder
    public void preOrder() {
        this.preOrder(0);
    }

    /**
     * 编写一个方法，完成顺序存储二叉树的前序遍历(中序，后序遍历类似链式存储)
     * @param index 开始的父节点索引
     */
    public void preOrder(int index) {
//        如果数组为空，或者长度为0，不能遍历
        if (arr == null || arr.length == 0) {
            throw new RuntimeException("数组为空，不能进行前序遍历！");
        }
//        输出当前元素
        System.out.println(arr[index]);
//        向左递归遍历
        if (2 * index + 1 < arr.length) {
            preOrder(2 * index + 1);
        }
//        向右递归遍历
        if (2 * index + 2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }
}
