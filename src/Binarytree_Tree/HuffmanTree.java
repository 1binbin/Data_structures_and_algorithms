package Binarytree_Tree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 创建一棵赫夫曼树
 *
 * @Author hongxiaobin
 * @Time 2022 /4/30-9:46
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
//        前序遍历
//        67,29,38,15,7,8,23,10,4,1,3,6,13
        preOrder(createHuffmanTree(arr));
    }

    /**
     * 构建一棵赫夫曼树
     * @param arr the arr
     * @return the node 处理后的赫夫曼树的root结点（根节点）
     */
    public static Node createHuffmanTree(int[] arr) {
//        第一步为了操作方便
//        1.遍历arr数组
//        2、将arr的每个元素构建一个Node
//        3.将Node放入到ArrayList中，便于排序
        ArrayList<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
//        以下的1-5为一次过程的执行，需要进行循环一直执行直到所有的结点进行处理，最后集合中剩余一个结点为止
        while (nodes.size() > 1) {
            //        先排序,从小到大
//        因为实现了Comparable故可以用集合类排序
            Collections.sort(nodes);
//        取出根节点权值最小的两棵二叉树
//        1.取出权值最小的结点（二叉树）
            Node leftNode = nodes.get(0);
//        2.取出次小的结点
            Node rightNode = nodes.get(1);
//        3.构建一棵新的二叉树，权值等于子节点的权值之和
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
//        4.从ArrayList中删除处理过的结点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
//        5.将新的结点加入到nodes中
            nodes.add(parent);
        }
//        返回赫夫曼树的root结点
        return nodes.get(0);
    }


    public static void preOrder(Node root){
        if (root!=null){
            root.preOrder();
        }else {
            throw new RuntimeException("是空树，不能遍历！");
        }
    }
}

/**
 * 节点类
 * 为了让Node对象支持排序Collections集合排序，需要实现Comparable接口
 */
class Node implements Comparable<Node> {
    //    节点的权值
    int value;
    //    指向左子节点
    Node left;
    //    指向右子节点
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
//        从小到大排序
        return this.value - o.value;
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        System.out.println(this);
        if (this.left !=null){
            this.left.preOrder();
        }
        if (this.right!=null){
            this.right.preOrder();
        }
    }
}
