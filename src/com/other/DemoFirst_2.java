package com.other;

/**
 * @author hongxiaobin
 * @create 2022/2/28-22:40
 * @description
 */
public class DemoFirst_2 {
    public static void main(String[] args) {
        SingleLinked singleLinked = new SingleLinked();
        Node node1 = new Node(1, 6666);
        Node node2 = new Node(2, 2222);
        Node node3 = new Node(3, 5555);
        Node node4 = new Node(4, 4444);
        Node node5 = new Node(5, 7777);
        singleLinked.insert(node1);
        singleLinked.insert(node2);
        singleLinked.insert(node3);
        singleLinked.insert(node4);
        singleLinked.insert(node5);
        singleLinked.list();
//        System.out.println("返回结点3的前结点");
//        System.out.println(singleLinked.prev(node3));
//        System.out.println("返回最后一个结点");
//        System.out.println(singleLinked.last());
//        System.out.println("返回子链表（1,3）");
//        singleLinked.subList(1, 3).list();
        System.out.println("第2个");
        singleLinked.partition(singleLinked);
        singleLinked.list();
    }
}

/*创建结点*/
class Node<T> {
    public int no;
    public T date;
    //    下一个结点
    public Node next;

    //    定义Node
    public Node(int no, T date) {
        this.no = no;
        this.date = date;
    }

    public Node() {
    }

    @Override
    public String toString() {
        return "com.other.Node{" +
                "no=" + no +
                ", name='" + date + '}';
    }
}

class SingleLinked<T extends Comparable<T>> {
    /*创建头结点*/
    private Node head = new Node(0, "");

    /*返回最后一个结点*/
    public Node last() {
        Node temp = this.head;
        while (temp.next != null) {
            temp = temp.next;
        }
        return temp;
    }

    /*返回p结点的前驱结点*/
    public Node prev(Node p) {
        Node temp = this.head;
        if (p == this.head) {
            return null;
        }
        while (true) {
            if (temp.next == p) {
                break;
            } else {
                temp = temp.next;
            }
        }
        return temp;
    }

    /*返回长度*/
    public int getLen() {
        int length = 0;
        Node temp = this.head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * @Author: hongxiaobin
     * @Description: 返回从第i个结点开始，长度为len的子表
     * @Date: 2022/3/18 14:47
     * @Param: int i,int len
     * @Return: com.other.SingleLinked
     */
    public SingleLinked subList(int i, int len) {
//        容错，如果开始小于1，则设置为1
        if (i < 1) {
            i = 1;
        }
//        如果最后的大于长度，则设置为最后一个
        if (i + len > this.getLen()) {
            len = this.getLen() - i;
        }
        SingleLinked singleLinked = new SingleLinked();
        Node temp, a;
        a = singleLinked.head;
        temp = this.head;
        for (int j = 0; j < i - 1; j++) {
            temp = temp.next;
        }
        for (int z = i - 1; z < i + len - 1; z++) {
            temp = temp.next;
            a.next = new Node(temp.no, temp.date);
            a = a.next;
        }
        return singleLinked;
    }

    /*添加结点*/
    public void insert(Node node) {
        Node temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
    }

    public void partition1() {
        Node temp = this.head;
        if (temp.next == null) {
            throw new NullPointerException();
        }
        Node p1 = temp.next;
        Node p = p1.next;
        Node pre = p1;
        Node q;
        while (p != null) {
            q = p.next;
            if ((int) p.date < (int) p1.date) {
                pre.next = p.next;
                p.next = temp.next;
                temp.next = p;
                p = q;
            } else {
                pre = p;
                p = p.next;
            }
        }
    }

    public void partition(SingleLinked<T> list) {
        Node<Integer> start = list.head.next;
        Node<Integer> end = this.last();
        System.out.println(start);
        int pivot = start.date;
//        int pivot = 2222;
        while (start != end) {
            while (start != end && end.date >= pivot) {
                end = list.prev(end);
            }
            start.date = end.date;
            while (start != end && start.date <= pivot) {
                start = start.next;
            }
            end.date = start.date;
        }
        start.date = pivot;
    }
    /*显示链表*/
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        Node temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
    public int get1(int i) {
        if (i < 0 || i > getLen()) {
            throw new NullPointerException();
        }
        Node node = head;
        if (node == null) {
            throw new NullPointerException();
        }
        for (int index = 0; index <= i; index++) {
            node = node.next;
        }
        return (int)node.date;
    }
    public void set1(int i, int x) {
        if (i >= 0 && i < this.getLen()) {
            Node p = head.next;
            for (int j = 0; j < i; j++) {
                p = p.next;
            }
            if (p != null) {
                p.date = x;
            } else {
                throw new NullPointerException();
            }
        } else {
            throw new IndexOutOfBoundsException(i);
        }
    }
}