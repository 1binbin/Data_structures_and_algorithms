//线性表接口，泛型
public interface LinearList1<T> {

    boolean isEmpty();              //判断是否为空，若为空则返回true
    int size();                     //返回线性表元素个数（长度）
    int search(T key);                //查找并返回首个与key相等的元素，若查找不成功则返回null
    T remove(T key);                 //查找并删除首个与key相等的元素，返回被删除元素
    void clear();                   //删除所有元素
    String toString();              //返回所有元素的描述字符串
    boolean equals(Object obj);     //比较this与obj引用线性表的所有元素是否相等
    boolean contains(T key);        //判断是否有包含关键字为key的元素

    T get(int index);               //返回第index个元素
    void set(int index,T x);        //设置第index个元素为x
    int insert(int index,T x);      //插入x作为第index个元素
    int insert(T x);                //在表尾插入x元素
    T remove(int index);            //删除第index个元素，返回被删除元素



}
