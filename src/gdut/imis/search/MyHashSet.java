package gdut.imis.search;

import gdut.imis.ds.linearlist.Node;
import gdut.imis.ds.linearlist.SinglyList;

/**
 * 哈希表（散列表）构建
 *
 * @Author hongxiaobin
 * @Time 2022/6/16-10:27
 */
public class MyHashSet<E> {
    public static final float LOAD_FACTOR = 0.75f;
    private static final int CAPACITY = 16;
    private SinglyList<E>[] table;
    private int count = 0;

    public MyHashSet(int length) {
        if (length < 10) {
            length = 10;
        }
        this.table = new SinglyList[length];
        for (int i = 0; i < this.table.length; i++) {
            this.table[i] = new SinglyList<>();
        }
    }

    public MyHashSet() {
        this(CAPACITY);
    }

    public MyHashSet(E[] value) {
        this((int) (value.length / MyHashSet.LOAD_FACTOR));
        for (E e : value) {
            this.add(e);
        }
    }

    private int hash(E x) {
        int key = Math.abs(x.hashCode());
        return key % this.table.length;
    }

    private int search(E key) {
        return this.table[this.hash(key)].search(key);
    }

    private boolean add(E e) {
        if (e == null || this.search(e) != -1) {
            return false;
        }
        if (this.count >= this.table.length * LOAD_FACTOR) {
//            超过容量，扩容
            SinglyList<E>[] temp = this.table;
            this.table = new SinglyList[table.length * 2];
            for (int i = 0; i < this.table.length; i++) {
                this.table[i] = new SinglyList<>();
            }
            this.count = 0;
            for (SinglyList<E> eSinglyList : temp) {
                for (Node<E> p = eSinglyList.head.next; p != null; p = p.next) {
                    this.add(p.data);
                }
            }
        }
        this.count++;
        return this.table[this.hash(e)].insert(0, e);
    }

    public int remove(E key) {
        int n = this.table[this.hash(key)].remove(key);
        if (n != -1) {
            this.count--;
        }
        return n;
    }

}
