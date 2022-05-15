package gdut.imis.ds.stack;

/**
 * @Author hongxiaobin
 * @Time 2022/4/15-8:40
 * @Description 实现顺序栈
 */
public class SeqStack<E>  implements Stack<E> {
    public static void main(String[] args) {
        SeqStack<Integer> stack = new SeqStack<>();
        stack.push(10);
        stack.push(12);
        stack.push(13);
        stack.push(14);
        System.out.println(stack.peek());

    }
    public  Object[] elements;
    /**
     * 栈顶指针
     */
    public int top;

    public SeqStack() {
//        默认长度为16
        this(16);
    }

    public SeqStack(int length) {
        this.elements = new Object[length];
        this.top = 0;
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    @Override
    public void push(E x) {
        if (top>=elements.length){
            throw new RuntimeException("栈已满");
        }
        this.elements[top++]=x;
    }

    @Override
    public E peek() {
        if (isEmpty()){
            throw new RuntimeException("栈为空");
        }
        return (E)this.elements[top-1];
    }

    @Override
    public E pop() {
        if (isEmpty()){
            throw new RuntimeException("栈为空");
        }
        return (E)this.elements[--top];
    }
}
