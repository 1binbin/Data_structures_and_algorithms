package com.other;

/**
 * @Author hongxiaobin
 * @Time 2022/3/14-20:18
 * @Description 顺序表
 */
public class DemoFirst_1 {
    public static void main(String[] args) {
        SeqList<Integer> list = new SeqList<>(5);
        System.out.println("初始表");
        list.insert(0, 555);
        list.insert(1, 222);
        list.insert(2, 805);
        list.insert(3, 444);
        list.insert(4, 666);
        System.out.println(list);
        System.out.println();
//
//        System.out.println("判断线性表为空");
//        System.out.println(list.isEmpty());
//        System.out.println();
//
//        System.out.println("获取线性表长度");
//        System.out.println(list.size());
//        System.out.println();
//
//        System.out.println("在线性表中顺序找到首个与key相等的元素，找到则返回元素的序号，否则返回-1");
//        System.out.println(list.search(444));
//        System.out.println();
//
//        System.out.println("删除具有指定key的元素");
//        list.remove(4);
//        System.out.println(list);
//        System.out.println();
//
//        System.out.println("判断是否存在某元素");
//        System.out.println(list.contains(444));
//        System.out.println(list.contains(666));
//        System.out.println();
//
//        System.out.println("返回指定位置的元素");
//        System.out.println(list.get(3));
//        System.out.println();
//
//        System.out.println("修改某元素");
//        list.set(3, 666);
//        System.out.println(list);
//        System.out.println();
//
//        System.out.println("在第index个插入某元素");
//        list.insert(2, 999);
//        System.out.println(list);
//        System.out.println();
//
//        System.out.println("在第index个插入某元素");
//        list.insert(4, 2222);
//        System.out.println(list);
//        System.out.println();
//
//        System.out.println("在顺序表尾部插入数据");
//        list.insert(1234);
//        System.out.println(list);
//        System.out.println();
//
//        System.out.println("删除指定元素");
//        list.remove(2);
//        System.out.println(list);
//        System.out.println();
//
//        System.out.println("删除所有的key");
//        list.insert(3, 222);
//        list.insert(4, 222);
//        System.out.println(list);
//        list.removeAll(222);
//        System.out.println(list);
//        System.out.println();
//
//        System.out.println("将第一个key替换为X");
//        list.insert(4, 111);
//        System.out.println(list);
//        list.replaceFirst(111, 1110);
//        System.out.println(list);
//        System.out.println();
//
//        System.out.println("查找最后一个为key，返回索引号");
//        list.insert(19, 111);
//        System.out.println(list);
//        System.out.println(list.searchLast(111));
//        System.out.println();

//        System.out.println("删除所有元素");
//        list.clear();
//        System.out.println(list);
        for (int i = 0 ;  i <5;i++) {
            list.partition();
            System.out.println(list);
        }
    }
}

class SeqList<T extends Comparable<T>> {
    private static final int MIN_CAPACITY = 16;
    private Object[] element;
    private int n;

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

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/28 23:03
     * @Description: 判断线性表是否为空
     * @Param: null
     * @Return: boolean
     */
    public boolean isEmpty() {
        return this.n == 0;
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/28 23:03
     * @Description: 获取线性表长度
     * @Param: null
     * @Return: int
     */
    public int size() {
        return this.n;
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/28 23:03
     * @Description: 在线性表中顺序找到首个与key相等的元素，找到则返回元素的序号，否则返回-1
     * @Param: T
     * @Return: int
     */
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
     * @Date: 2022/3/28 23:03
     * @Description: 删除具有指定key的元素
     * @Param: T
     * @Return: void
     */
    public void remove(T key) {
        if (this.search(key) != -1) {
            this.remove(this.search(key));
        } else {
            throw new NullPointerException((String) key);
        }
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/28 23:04
     * @Description: 删除所有元素
     * @Param: null
     * @Return: void
     */
    public void clear() {
        this.n = 0;
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/28 23:04
     * @Description: 判断是否包含key的元素
     * @Param: T
     * @Return: boolean
     */
    public boolean contains(T key) {
        if (key == null) {
            throw new NullPointerException((String) key);
        }
        for (int i = 0; i < this.n; i++) {
            if (key.equals(this.element[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/28 23:04
     * @Description: 返回指定位置的元素
     * @Param: int
     * @Return: T
     */
    public T get(int index) {
        if (index >= 0 && index < this.n) {
            return (T) this.element[index];
        } else {
            throw new IndexOutOfBoundsException("index:" + index);
        }
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/28 23:04
     * @Description: 修改某元素
     * @Param: int ,T
     * @Return: void
     */
    public void set(int index, T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (index >= 0 && index < this.n) {
            this.element[index] = key;
        } else {
            throw new IndexOutOfBoundsException("index:" + index);
        }
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/28 23:04
     * @Description: 在第index个插入某元素
     * @Param: int, T
     * @Return: int
     */
    public int insert(int index, T key) {
//        判断插入元素是否为空
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
        return index;
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/28 23:05
     * @Description: 在第index个插入某元素
     * @Param: int, T
     * @Return: int
     */
    public int insert1(int index, T key) {
//        判断插入元素是否为空
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
//        新建一个对象引用原来的数组
//        Object[] source = this.element;
        Object[] source = new Object[element.length];
        for (int i = 0; i < element.length; i++) {
            source[i] = element[i];
        }
//        如果原来的数组已满则扩容
        if (this.n == element.length) {
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
//        将source中剩下的元素复制到element中
        for (int i = index + 1; i < n; i++) {
            this.element[i] = source[i - 1];
        }
        return index;
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/28 23:05
     * @Description: 在顺序表尾部插入数据
     * @Param: T
     * @Return: int
     */
    public int insert(T key) {
        return this.insert(this.n, key);
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/28 23:05
     * @Description: 删除指定元素
     * @Param: int
     * @Return: T
     */
    public T remove(int index) {
        if (index >= 0 && index < this.n) {
            T t = (T) this.element[index];
            for (int i = index; i < this.n - 1; i++) {
                this.element[i] = this.element[i + 1];
            }
            this.element[this.n - 1] = null;
            this.n--;
            return t;
        } else {
            throw new IndexOutOfBoundsException("index:" + index);
        }
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/28 23:05
     * @Description: 删除指定元素的所有值
     * @Param: T
     * @Return: void
     */
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
     * @Date: 2022/3/28 23:06
     * @Description: 将第一个key的元素替换为x
     * @Param: T, T
     * @Return: void
     */
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
     * @Date: 2022/3/28 23:06
     * @Description: 查找最后一个为key的元素，返回索引号
     * @Param: T
     * @Return: int
     */
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
     * @Date: 2022/3/28 23:06
     * @Description: 遍历
     * @Param: null
     * @Return: String
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(this.getClass().getName() + "[");
        if (this.n > 0) {
            s.append(this.element[0].toString());
        }
        for (int i = 1; i < this.n; i++) {
            s.append(",").append(this.element[i].toString());
        }
        return s + "]";
    }

    /**
     * @Author: hongxiaobin
     * @Description: 实现以第一个为基准，比小的放前面，比大的放后面
     * @Date: 2022/3/18 9:40
     * @Param: null
     * @Return: void
     */
    public void partition() {
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
}
