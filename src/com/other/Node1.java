package com.other;

public class Node1<T> {
    public T item;
    public Node1 next;

    public Node1(T item, Node1 next) {
        this.item = item;
        this.next = next;
    }

    public Node1() {
    }
}
