package gdut.imis.ds.linearlist;

/**
 * @author hongxiaobin
 */
public class SeqList<E extends Comparable<E>> implements LinearList<E> {
    private static final int MIN_CAPACITY = 16;
    protected Object[] element;
    protected int n;

    public SeqList() {
        this(MIN_CAPACITY);
    }

    public SeqList(int length) {
        if (length < MIN_CAPACITY) {
            length = MIN_CAPACITY;
        }
        this.element = new Object[length];
        this.n = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.n == 0;
    }

    @Override
    public int size() {
        return n;
    }


    @Override
    // 读取操作
    // 若0≤i<n，返回第i个元素；否则，返回null--- O(1)
    public E get(int i) {
        if (i >= 0 && i < this.n) {
            return (E) this.element[i];
        }
        return null;
    }

    @Override
    // 若0≤i<n且x≠null，设置第i个元素为x；否则抛出序号越界异常或空对象异常---O(1)
    public void set(int i, E x) {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException(i + "");
        }
        if (x == null) {
            throw new NullPointerException("x == null");
        }
        this.element[i] = x;
    }

    @Override
    // 顺序表的插入操作
    // 插入x为第i个元素，x!=null，返回插入成功true，失败false
    // x为null不能插入，插入失败
    // 对i容错，若i<0，则头插入；若i>长度，则尾插入
    // 对存储空间大小校验，若数组空间已满，则扩充顺序表的数组容量
    // 插入操作：将插入位置及之后的记录往后移动一个单元，待插入的数据放置到第i个元素的位置
    // 时间复杂度：？
    public boolean insert(int index, E key) {
        if (key == null) {
            throw new NullPointerException();
        }
//        如果下标越下界则头部插入
        if (index < 0) {
            index = 0;
        }
//        如果下标越上界则尾部插入
        if (index >= this.n) {
            index = this.n;
        }
//        如果原来的数组已满则扩容
        if (this.n == element.length) {
            Object[] source = this.element;
            this.element = new Object[source.length * 2];
            for (int i = 0; i < source.length; i++) {
                this.element[i] = source[i];
            }
        }
//        元素后移
        for (int i = this.n - 1; i >= index; i--) {
            element[i + 1] = element[i];
        }
//        插入下标index的元素
        this.n++;
        this.element[index] = key;
        return true;
    }


    @Override
    public int insert(E x) {
        this.insert(this.n, x);
        return n;
    }


    @Override
    //顺序表的删除操作
    public E remove(int i) {
        if (i >= 0 && i < this.n) {
            E x = (E) this.element[i];
            // 元素前移一个位置
            if (this.n - 1 - i >= 0) {
                System.arraycopy(this.element, i + 1, this.element, i, this.n - 1 - i);
            }
            this.element[this.n--] = null;
            return x;
        }
        return null;
    }

    @Override
    public void clear() {
        for (int i = 0; i < n; i++) {
            element[i] = null;
        }

        n = 0;
    }

    @Override
    // 顺序查找
    // 在this引用的顺序表中，顺序查找首个与key相等元素，返回元素序号i，0≤i<n；若查找不成功，则返回-1。
    // 顺序查找算法:O(n)
    public int search(E key) {
        return -1;
    }

    @Override
    public boolean contains(E x) {
        return this.search(x) != -1;
    }

    @Override
    public int remove(E key) {
        return -1;
    }

    // 返回所有元素的描述字符串，形式为“[,]”。覆盖Object类的toString()方法
    // O(n)
    @Override
    public String toString() {
        String str = "[";
        if (this.n > 0) {
            str += this.element[0].toString();
        }
        for (int i = 1; i < this.n; i++) {
            str += ", " + this.element[i].toString();
            if (i % 15 == 0) {
                str += "\n";
            }
        }
        return str + "] ";
    }

    public void partition() {
        int i = 0;
        int j = size() - 1;
        E base = this.get(i);
        while (j > i) {
            while (base.compareTo(this.get(j)) < 0) {
                j--;
            }
            if (j > i) {
                this.set(i, this.get(j));
                i++;
            }
            while (base.compareTo(this.get(i)) > 0) {
                i++;
            }
            if (j > i) {
                this.set(j, this.get(i));
                j--;
            }
        }
        this.set(i, base);
    }
}
