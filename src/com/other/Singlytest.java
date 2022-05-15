package com.other;

public class Singlytest<T> {
    public static void main(String[] args) {
        Singly<String> list = new Singly<String>();
        list.insert("姚明");
        list.insert("科比");
        list.insert("麦迪");
        list.insert("詹姆斯");
        list.insert("哈哈哈");
        list.insert("姚明");
        System.out.println("原来的链表");
        System.out.println(list);
        System.out.println();

        System.out.println("返回最后一个结点的数据");
        System.out.println(list.last().item);
        System.out.println();

        System.out.println("查找 麦迪 的前结点的数据");
        Node1 node1 = new Node1<>("麦迪", null);
        System.out.println(list.prev(node1).item);
        System.out.println();

        System.out.println("在 2 的位置插入一个数据");
        list.insert(2, "111");
        System.out.println(list);
        System.out.println();

        System.out.println("在末尾插入一个结点");
        list.insert("22222");
        System.out.println(list);
        System.out.println();

        System.out.println("删除 2 位置的结点");
        list.remove(2);
        System.out.println(list);
        System.out.println();

        System.out.println("查找 ”麦迪“ 的位置");
        System.out.println(list.indexof("麦迪"));
        System.out.println();

        System.out.println("获取 4 位置的值");
        System.out.println(list.get(4));
        System.out.println();

        System.out.println("判断链表是否为空");
        System.out.println(list.isEmpty());
        System.out.println();

        System.out.println("获取链表长度");
        System.out.println(list.length());
        System.out.println();

        System.out.println("清空链表");
        list.clear();
        System.out.println(list);
    }
}

class Singly<T> {
    private Node1 head;
    private int N;

    public Singly() {
        this.head = new Node1();
        this.N = 0;

    }

    //  清空链表
    public void clear() {
        head.next = null;
        this.N = 0;
    }

    //    获取链表的长度
    public int length() {
        return N;
    }

    //    判断链表是否为空
    public boolean isEmpty() {
        return N == 0;
    }

    //    获取指定位置i处的元素
    public Object get(int i) {
        if (i < 0 || i > N) {
            throw new NullPointerException();
        }
//        通过循环，从头结点开始往后找，依次找1次，可以找到对应的元素
        Node1 n = head;
        if (n == null) {
            throw new NullPointerException();
        }
        for (int index = 0; index < i; index++) {
            n = n.next;
        }
        return n.item;
    }

    public boolean listEmpty() {
        Node1 n = head;
        if (n.next == null) { //通过判断头结点的下一结点地址是否为空，即可判断单链表是否为空
            return true;
        }
        return false;
    }

    //    向链表中添加元素t
    public void insert(T t) {
        this.insert(N, t);
//        if (t == null) {
//            throw new NullPointerException();
//        }
//        com.other.Node1 n = head;
//        while (n.next != null) {
//            n = n.next;
//        }
//        n.next = n;
//        N++;
    }

    //    向指定位置i处，添加元素t
    public void insert(int i, T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        if (i < 0 || i > N) {
            throw new IndexOutOfBoundsException();
        }
        Node1 pre = head;
        Node1 newNode = new Node1(t, null);
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
        N++;
    }

    //    删除指定位置i处的元素，并返回被删除的元素
    public Object remove(int i) {
        if (i < 0 || i >= N) {
            throw new IndexOutOfBoundsException();
        }
//        找到i位置的前一个节点
        Node1 pre = head;
        for (int index = 0; index < i - 1; index++) {
            pre = pre.next;
        }
//        要找到i位置的结点
        Node1 curr = pre.next;
//        找到i位置的下一个结点
        if (i == N - 1) {
            N--;
            return curr.item;
        }
        Node1 nextNode = curr.next;
//        前一个结点指向下一个结点
        pre.next = nextNode;
//        元素个数-1
        N--;
        return curr.item;
    }

    //    查找元素t在链表中第一次出现的位置
    public int indexof(T t) {
//        从头结点开始，依次找到每一个结点，取出item，和t比较，如果相同，就找到了
        Node1 n = head;
        for (int i = 0; n.next != null; i++) {
            if (n.item.equals(t)) {
                return i;
            }
            n = n.next;
        }
        return -1;
    }


    @Override
    public String toString() {
        String s = "com.other.Node1[";
        for (int i = 0; i < this.N; i++) {
            s += this.get(i) + "\t";
        }
        return s + "]";
    }

    public Node1 last() {
        Node1 temp = new Node1(this.get(N - 1), null);
//        com.other.Node1 temp = this.head;
//        while (temp.next != null) {
//            temp = temp.next;
//        }
        return temp;
    }

    public Node1 prev(Node1 p) {
        Node1 temp = this.head;
        if (p == this.head) {
            return null;
        }
        while (true) {
            if (temp.next.item.equals(p.item)) {
                break;
            } else {
                temp = temp.next;
            }
        }
        return temp;
    }
}

/*       com.other.Node1 n = head;
        com.other.Node1 q = null;
        if(p==null||p.next==null){
            return null;
        }
      if(head.next==null){
          return null;//空链表
      }
//      创建要给一个栈，将各个节点压入栈
        Stack<com.other.Node1> stack = new Stack<>();
        com.other.Node1 cur = head.next;
//        将链表的左右结点压入栈
        while(cur!=null){
            stack.push(cur);
            cur = cur.next;//cur后移，这样就可以压入下一个结点
        }
          while(stack.size()>0){
              if(stack.pop().equals(p)){
                    q = p.next;
              }
              }
          return q;*/