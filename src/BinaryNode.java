public class BinaryNode<T> {
    public T data;  //数据域
    public BinaryNode<T> left,right;     //指针域

    public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public BinaryNode(T data) {
        this(data,null,null);
    }

    public BinaryNode() {
        this.data = null;
        this.left = null;
        this.right = null;
    }

    @Override
    public String toString() {
        return data.toString();
    }

    public void preOrder(){
        System.out.println(this);
        if (this.left!=null){
            this.left.preOrder();
        }
        if (this.right!=null){
            this.right.preOrder();
        }
    }
}
