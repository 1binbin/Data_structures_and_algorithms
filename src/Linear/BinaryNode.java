package Linear;

/**
 * 节点类
 *
 * @Author: hongxiaobin
 * @Date: 2022/5/15 15:04
 */
public class BinaryNode<T extends Comparable<T>> {
    private T data;
    private BinaryNode<T> left;
    private BinaryNode<T> right;

    public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public BinaryNode(T data) {
        this(data, null, null);
    }

    public BinaryNode() {
        this(null, null, null);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryNode<T> left) {
        this.left = left;
    }

    public BinaryNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryNode<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BinaryNode{" +
                "data=" + data +
                '}';
    }
}
