package gdut.imis.ds.linearlist;

public class SinglyList<E> implements LinearList<E> {
    public Node<E> head;
    public int size;

    public SinglyList() {
        head = new Node<E>();
        size = 0;
    }
    public Node last() {
        return new Node(this.get(size-1), null);
    }

    public SinglyList(E[] values) {
        this();
        Node<E> last = head;
        for (int i = 0; i < values.length; i++) {
            last.next = new Node<E>(values[i], null);
            last = last.next;
        }
        size = values.length;
    }

    @Override
    public boolean isEmpty() {
        return head.next == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int i) {
        //TODO
        if (i < 0 || i > size) {
            throw new NullPointerException();
        }
        Node n = head;
        if (n == null) {
            throw new NullPointerException();
        }
        for (int index = 0; index < i; index++) {
            n = n.next;
        }
        return (E) n.data;
    }

    @Override
    public void set(int i, E x) {
        //TODO
        if (x == null) {
            throw new NullPointerException();
        }
        Node<E> p = this.location(i);
        if (p != null) {
            p.data = x;
        }
    }

    @Override
    public boolean insert(int i, E x) {
        // TODO
        if (x == null) {
            throw new NullPointerException();
        }
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException();
        }
        Node pre = head;
        Node newNode = new Node(x, null);
        if (i == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            for (int j = 0; j < i - 1; j++) {
                pre = pre.next;
            }
            newNode.next = pre.next;
            pre.next = newNode;
        }
        size++;
        return true;
    }

    @Override
    public int insert(E x) {
        insert(size, x);
        return size;
    }

    @Override
    public E remove(int i) {
        // TODO
        return null;
    }

    @Override
    public void clear() {
        head.next = null;
    }

    @Override
    public int search(E key) {
        // TODO
        return -1;

    }

    @Override
    public boolean contains(E key) {
        int i = search(key);
        return i != -1;
    }

    @Override
    public int remove(E key) {
        int i = search(key);
        remove(i);
        return i;
    }


    @Override
    public String toString() {
        String str = "[";
//        Node<E> p = head.next;
//        while (p != null) {
//            str += "," + p.data.toString();
//            p = p.next;
//        }
//        return str + "] ";
        if (size == 0) {
            str += get(0);
            return str + "]";
        } else {
            str += get(0);
            for (int i = 1; i < this.size; i++) {
                str += "," + get(i);
            }
            return str + "]";
        }
    }

    private Node<E> location(int i) {
        if (i >= 0 && i < this.size) {
            Node<E> p = head.next;
            for (int j = 1; j <= i; j++) {
                p = p.next;
            }
            if (p == null) {
                return p;
            }
        }
        return null;
    }

    /**
     * @Author: hongxiaobin
     * @Description: 单链表就地反转
     * @Date: 2022/3/18 9:13
     * @Param:
     * @Return:
     */
    public void reverse() {
        Node<E> p = head.next;
        Node<E> q = null;
        head.next = null;
        while (p != null) {
            q = p;
            p = p.next;
            q.next = head.next;
            head.next = q;
        }
    }
}
