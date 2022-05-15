package gdut.imis.ds.linearlist;

/**
 * @author hongxiaobin
 */
public interface LinearList<E> { //线性表定义的运算，以接口形式描述
	boolean isEmpty();                 // 判断是否空，若为空，则返回true
    int size();                        // 返回元素个数（长度）
    int search(E key);                   // 查找并返回首个与key相等元素；若查找不成功，则返回null
    int remove(E key);                   // 查找并删除首个与key相等元素，返回被删除元素的位置
    void clear();                      // 删除所有元素
    @Override
    String toString();                 // 返回所有元素的描述字符串
    @Override
    boolean equals(Object obj);        // 比较this与obj引用线性表的所有元素是否对应相等
    
    /**
     * @param i 元素序号（索引号），指定操作位置
     * @return E
     */
    E get(int i);                      // 返回第i个元素
    void set(int i, E x);              // 设置第i个元素为x   ////不要返回值
    boolean insert(int i, E x);            // 插入x作为第i个元素
    int insert(E x);                   // 在最后插入x元素
    E remove(int i);                   // 删除第i个元素，返回被删除元素
	boolean contains(E x);	//是否包含x元素
}
