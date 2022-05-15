package com.other;

public class Seq<T>{
    public static void main(String[] args) {
        Seq<String> list = new Seq<String>(1000);
        list.insert(1,"姚明");
        list.insert(2,"科比");
        list.insert(3,"麦迪");
        list.insert(4,"詹姆斯");
        list.insert(5,"哈哈哈");
    }
    private int n;
    private T[] elements;
    public Seq(int capacity){
        this.n = 0;
        this.elements =(T[]) new Object[capacity];
    }

    public void removeAll(T key){
        for(int i = 0;i<this.n-1;i++){
        int index = search(key);
        if(index == -1){
            System.out.println("此关键字不存在");
            if(i==index) {
                this.elements[i] = null;
                this.elements[i] = this.elements[i + 1];
                this.n--;
            }
            }
        }
    }
    public int search(T key) {
        for(int i=0;i<this.n;i++){
            if(key.equals(this.elements[i])){
                return i;
            }
        }
        return -1;
    }
    public void insert(int pos, T data){
//        先判断链表满了没有
        if(this.n == this.elements.length){
            System.out.println("没有位置了！");
            this.elements = (T[]) new Object[this.elements.length*2];
        }
//        再判断pos的合法性
        if(pos<0 ||pos>this.elements.length){
            System.out.println("输入数据不对！");
            return;
        }
//        移动
        for(int i = this.n-1;i>=pos;i--){
            this.elements[i+1] = this.elements[i];
        }
        this.elements[pos] = data;
        this.n++;
    }
    @Override
    public String toString() {
        String str = "[";
        if (this.n > 0) {
            str += this.elements[0].toString();
        }
        for (int i = 1; i < this.n; i++) {
            str += ", " + this.elements[i].toString();
            if (i % 15 == 0) {
                str += "\n";
            }
        }
        return str + "] ";
    }
}


