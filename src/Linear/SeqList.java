package Linear;

/**
 * @author hongxiaobin
 */
@SuppressWarnings("ALL")
public class SeqList<T extends Comparable<T>> implements SeqLinearList<T> { // 线性表的顺序存储实现（顺序表）
    private static final int MIN_CAPACITY = 16; // 定义顺序存储空间的最小容量
    protected Object[] element; // 地址连续的顺序存储空间，Object数组提供
    protected int n; // 顺序表元素个数（长度）

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
    public T get(int i) {
        if (i >= 0 && i < this.n) {
            return (T) this.element[i];
        }
        return null;
    }

    @Override
    // 若0≤i<n且x≠null，设置第i个元素为x；否则抛出序号越界异常或空对象异常---O(1)
    public void set(int i, T x) {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException(i + "");
        }
     /*   if (x == null) {
            throw new NullPointerException("x == null");
        }*/
        this.element[i] = x;
    }

    @Override
    // 顺序表的插入操作
    // 插入x为第i个元素，x!=null，返回插入成功true，失败false
    // x为null不能插入，插入失败
    // 对i容错，若i<0，则头插入；若i>长度，则尾插入
    // 对存储空间大小校验，若数组空间已满，则扩充顺序表的数组容量
    // 插入操作：将插入位置及之后的记录往后移动一个单元，待插入的数据放置到第i个元素的位置
    // 线性表长度加1
    // 时间复杂度：？
    public boolean insert(int i, T x) {
        if (x == null) {
            throw new NullPointerException();
        }
//        如果下标越下界则头部插入
        if (i < 0) {
            i = 0;
        }
//        如果下标越上界则尾部插入
        if (i >= this.n) {
            i = this.n;
        }
//        如果原来的数组已满则扩容
        if (this.n == element.length) {
            Object[] source = this.element;
            this.element = new Object[source.length * 2];
            for (int z = 0; z < source.length; z++) {
                this.element[i] = source[i];
            }
        }
//        元素后移
        for (int j = this.n - 1; j >= i; j--) {
            element[i + 1] = element[i];
        }
//        插入下标index的元素
        this.n++;
        this.element[i] = x;
        return true;
    }


    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/30 8:47
     * @Description: 顺序表尾插入x元素，O(1)。成员方法重载
     * @Param: [x]
     * @Return: int
     */
    @Override
    public int insert(T x) {
        this.insert(this.n, x);
        return this.n;
    }


    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/29 21:44
     * @Description: 顺序表的删除操作 O(n)
     * @Param: [i]
     * @Return: T
     */
    @Override
    public T remove(int i) {
        if (i >= 0 && i < this.n) {
            T x = (T) this.element[i];
            for (int j = i; j < this.n - 1; j++) {
                this.element[j] = this.element[j + 1];
            }
            this.element[this.n--] = null;
            return x;
        }
        return null;
    }


    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/29 21:43
     * @Description: 删除所有元素
     * @Param: []
     * @Return: void
     */
    @Override
    public void clear() {
        // clear to let GC do its work
        for (int i = 0; i < n; i++) {
            element[i] = null;
        }
        n = 0;
    }


    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/29 21:43
     * @Description: 顺序查找, 在this引用的顺序表中，顺序查找首个与key相等元素，返回元素序号i，0≤i<n；若查找不成功，则返回-1。顺序查找算法:O(n)
     * @Param: [key]
     * @Return: int
     */
    @Override
    public int search(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.n; i++) {
            if (key.equals(this.element[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/29 21:43
     * @Description: 线性表中是否包含x元素
     * @Param: T
     * @Return: boolean
     */
    @Override
    public boolean contains(T x) {
        return this.search(x) != -1;
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/29 21:43
     * @Description: 删除第一个值为key的记录
     * @Param: T
     * @Return: int
     */
    @Override
    public int remove(T key) {
        int index = this.search(key);
        if (index != -1) {
            this.remove(index);
        } else {
            throw new NullPointerException((String) key);
        }
        return -1;
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/29 21:39
     * @Description: 删除所有关键字为key的数据元素，要求所有元素一次移动到位
     * @Param: T
     * @Return: void
     */
    @Override
    public void removeAll(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        int left = 0;
        for (int right = 0; right < this.n; right++) {
            if (!this.element[right].equals(key)) {
                this.element[left] = this.element[right];
                left++;
            }
        }
        this.n = left;
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/29 21:40
     * @Description: 将第一个关键字为key的元素替换为x
     * @Param: T, T
     * @Return: void
     */
    @Override
    public void replaceFirst(T key, T x) {
        if (key == null || !this.contains(key)) {
            throw new NullPointerException("key:" + key);
        }
        for (int i = 0; i < this.n; i++) {
            if (key.equals(this.element[i])) {
                this.element[i] = x;
                break;
            }
        }
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/29 21:41
     * @Description: 查找最后一个关键字为key的元素，返回其索引号
     * @Param: T
     * @Return: int
     */
    @Override
    public int searchLast(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        for (int i = this.n - 1; i >= 0; i--) {
            if (key.equals(this.element[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/31 21:09
     * @Description: 实现对线性表快速排序
     * @Param: []
     * @Return: void
     */
    @Override
    public void seqPartition() {
        int i = 0;
        int j = size() - 1;
        T base = this.get(i);
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

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/29 21:42
     * @Description: 返回所有元素的描述字符串，形式为“[,]”。覆盖Object类的toString()方法 O(n)
     * @Param: null
     * @Return: String
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        if (this.n > 0) {
            str.append(this.element[0].toString());
        }
        for (int i = 1; i < this.n; i++) {
            str.append(", ").append(this.element[i].toString());
            if (i % 15 == 0) {
                str.append("\n");
            }
        }
        return str + "] ";
    }
}
