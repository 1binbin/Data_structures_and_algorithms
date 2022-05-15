package gdut.imis.ds.stack;

/**
 * @Author hongxiaobin
 * @Time 2022/4/15-8:38
 * @Description 声明栈的相关方法
 */
public interface Stack<E> {
    /**
     * @Description: 判断栈是否为空
     * @Param: []
     * @Return: boolean
     */
    boolean isEmpty();
    /**
     * @Description: 入栈
     * @Param: [E]
     * @Return: void
     */
    void push(E x);
    /**
     * @Description: 查看栈顶，但不出栈
     * @Param: []
     * @Return: E
     */
    E peek();
    E pop();
}
