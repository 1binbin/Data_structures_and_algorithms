package gdut.imis.ds.linearlist;

/**
 * @author hongxiaobin
 */
public class Node<E> {
    public E data;
    public Node<E> next;

    public Node() {
        data = null;
        next = null;
    }

    public Node(E x, Node<E> next) {
        this.data = x;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node [data=" + data + ", next=" + next + "]";
    }
}
