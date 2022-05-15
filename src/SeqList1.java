//顺序表实现线性表接口，重写相应方法
public class SeqList1<T> implements LinearList1<T> {

    protected Object[] element;//用来存放元素的数组充当顺序表
    protected int n;//顺序表的长度（元素个数）
    private static final int MIN_CAPACITY = 16; // 定义顺序存储空间的最小容量

    public SeqList1() {
        this(MIN_CAPACITY);//默认创建一个16长度的顺序表
    }

    public SeqList1(int length) {
        if (length < MIN_CAPACITY) {
            length = MIN_CAPACITY;
        }
        this.element = new Object[length];//创建指定长度的顺序表
        this.n = 0;//默认元素为0个
    }

    @Override
    public boolean isEmpty() {
        return this.n==0;//判断元素个数是否为0
    }

    @Override
    public int size() {
        return this.n;//获取表的长度
    }

    @Override
    //在顺序表中顺序查找首个与key相等的元素，找到则返回元素序号i（0<=i<n)，若找不到则返回-1
    public int search(T key) {
        //key==null时，则抛出空指针异常
        if(key==null){
            throw new NullPointerException();
        }
        for(int i=0;i<this.n;i++) {
            if(key.equals(this.element[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    //删除具有指定关键字key的元素
    public T remove(T key) {
        return this.remove(this.search(key));
    }

    @Override
    //删除所有元素
    public void clear() {
        this.n=0;
    }

    @Override
    //判断是否有包含关键字为key的元素
    public boolean contains(T key) {
        return this.search(key)!=-1;
    }

    @Override
    public T get(int index) {
        if(index>=0 && index<this.n) {
            return (T)this.element[index];//返回所指定位置引用的对象
        }
            return null;
    }

    @Override
    public void set(int index, T x) {
        if(x==null){
            throw new NullPointerException();
        }
        if(index>=0 && index<this.n){
            this.element[index]=x;
        }else{
            throw new IndexOutOfBoundsException(index+"");//抛出序号越界异常
        }
    }

    @Override
    public int insert(int index, T x) {
        if(x==null){
            throw new NullPointerException(); //空指针异常
        }
        if(index<0){
            index=0;    //插入位置小于0，则插在最前
        }
        if(index>this.n){
            index=this.n;   //插入位置大于表的长度，则插在最后
        }
        Object[] source=this.element;//新建一个对象引用element数组
        if(this.n==element.length){//数组已满，则进行扩容
            this.element=new Object[source.length*2];  //申请一个容量更大的数组
            for(int j=0;j<index;j++){//复制当前数组前index-1个元素
                this.element[j]=source[j];//复制数组元素，传递对象引用
            }
        }
        for(int j=this.n-1;j>=index;j--){ //从index开始至表尾的元素向后移动，次序从后往前
            this.element[j+1]=source[j];
        }
        this.element[index]=x;
        this.n++;
        return index;
    }

    @Override
    //在顺序表尾插入x元素
    public int insert(T x) {
        return this.insert(this.n,x);
    }

    @Override
    public T remove(int index) {
        if(index>=0 && index<this.n){
            T x=(T)this.element[index];//将要删除的元素储存在x
            for(int i=index;i<this.n-1;i++){
                this.element[i]=this.element[i+1];//把i之后的元素都向前移一位
            }
            this.element[this.n-1]=null;//最后一个置空
            this.n--;
            return x;
        }
        return null;
    }

    @Override
    public String toString() {
        String str=this.getClass().getName()+"(";//返回类名
        if(this.n>0){
            str +=this.element[0].toString();
        }
        for(int i=1;i<this.n;i++){
            str += ","+this.element[i].toString();
        }
        return str+")";
    }

    //删除所有关键字为key的数据元素，要求所有元素一次移动到位
    public void removeAll(T key){
        //统计元素中有多少个具有关键字key
        int number=0;//统计具有key元素的个数
        if(key==null){
            throw new NullPointerException();
        }
        /* 方式一：时间复杂度过高
        for(int i=0;i<this.n;i++) {
            if(key.equals(this.element[i])){
                number++;
            }
        }
        //System.out.println(number);

        //根据key的个数执行多少次删除
        for(int i=0;i<number;i++){
            this.remove(key);
        }
         */

        //方式二：力扣推荐
        int newNumber=0;
        for(int i=0;i<this.n;i++){  //类似于一个先遍历的指针
            //如果不需删除则覆盖保留在顺序表前端
            if(this.element[i]!=key){
                this.element[newNumber]=this.element[i];
                newNumber++;
            }
        }
        this.n=newNumber;//最后保留下来的就是新的顺序表长度


    }

    //将第一个关键字为key的元素替换为x
    public void replaceFirst(T key,T x){
        int number=-1;      //避免索引是0
        if(key==null){
            throw new NullPointerException();
        }
        for(int i=0;i<this.n;i++) {
            if(key.equals(this.element[i])){
                number=i;
                break;
            }
        }
        //如果找到该元素则替换
        if(number!=-1) {
            this.element[number] = x;
        }else{
            System.out.println("对不起！在顺序表中找不到具有该关键字的元素！");
        }
    }

    //查找最后一个关键字为key的元素，返回其索引号
    public int searchLast(T key){
        if(key==null){
            throw new NullPointerException();
        }
        for(int i=this.n;i>=0;i--) {
            if(key.equals(this.element[i])){
                return i;
            }
        }
        return -1;
    }
}
