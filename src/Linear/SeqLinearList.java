package Linear;

/**
 * @Author hongxiaobin
 * @Time 2022/3/29-21:48
 * @Description
 */
public interface SeqLinearList<T> {
    /**
     * 判断是否为空，若为空，则返回true
     */
    boolean isEmpty();

    /**
     * 返回元素个数（长度）
     */
    int size();

    /**
     * 查找并返回首个与key相等元素；若查找不成功，则返回null
     */
    int search(T key);

    /**
     * 查找并删除首个与key相等元素，返回被删除元素的位置
     */
    int remove(T key);

    /**
     * 删除所有元素
     */
    void clear();

    /**
     * 返回所有元素的描述字符串
     */
    @Override
    String toString();

    /**
     * 比较this与obj引用线性表的所有元素是否对应相等
     */
    @Override
    boolean equals(Object obj);

    /**
     * 返回第i个元素
     */
    T get(int i);

    /**
     * 设置第i个元素为x
     */
    void set(int i, T x);

    /**
     * 插入x作为第i个元素
     */
    boolean insert(int i, T x);

    /**
     * 在最后插入x元素
     */
    int insert(T x);

    /**
     * 删除第i个元素，返回被删除元素
     */
    T remove(int i);

    /**
     * 是否包含x元素
     */
    boolean contains(T key);

    /**
     * 删除所有关键字为key的数据元素，要求所有元素一次移动到位
     */
    void removeAll(T key);

    /**
     * 将第一个关键字为key的元素替换为x
     */
    void replaceFirst(T key, T x);

    /**
     * 查找最后一个关键字为key的元素，返回其索引号
     */
    int searchLast(T key);

    /**
     * 实现对线性表快速排序
     */
    void seqPartition();
}
