package Fourth_chapter.stack;

import java.util.Scanner;

/**
 * @Author hongxiaobin
 * @Time 2022/3/31-19:18
 * @Description 用数组模拟栈
 */
public class ArrayStackDemo {
    /**
     * 思路分析
     * 1.定义一个top来表示栈顶，初始化为-1
     * 2.入栈的操作，当有数据加入到栈时，top++;stack[top] = data;
     * 3.出栈的操作，int value = stack[top];top--;return value;
     */
    public static void main(String[] args) {
//        创建一个对象即为栈
        Arraystack arraystack = new Arraystack(4);
        String key;
//        控制是否退出菜单
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("show：表示显示栈");
            System.out.println("push：表示入栈");
            System.out.println("pop：表示出栈");
            System.out.println("exit：表示退出程序");
            System.out.println("请输入你的选择");
            key = scanner.next();
            try {
                switch (key){
                    case "show":
                        arraystack.list();
                        break;
                    case "push":
                        System.out.println("请输入一个数");
                        int nextInt = scanner.nextInt();
                        arraystack.push(nextInt);
                        break;
                    case "pop":
                        System.out.println("出栈的数据为 "+arraystack.pop());
                        break;
                    case "exit":
                        scanner.close();
                        loop = false;
                        break;
                    default:
                        System.out.println("菜单选择有误，请重新输入");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("---------------------");
            System.out.println();
        }
        System.out.println("程序已退出");
    }
}

/**
 * @Author: hongxiaobin
 * @Date: 2022/3/31 19:21
 * @Description: 定义一个类表示栈
 */
class Arraystack {
    /**
     * 栈的大小
     */
    private int maxSize;
    /**
     * 数组，数组模拟栈，数据就放在该栈中
     */
    private int[] stack;
    /**
     * top表示栈顶，初始化为-1
     */
    private int top = -1;

    public Arraystack(int maxSize) {
        this.maxSize = maxSize;
//        初始化栈，才可以存数据
        stack = new int[maxSize];
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/31 19:23
     * @Description: 判断栈满
     * @Param: []
     * @Return: boolean
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/31 19:24
     * @Description: 判断栈空
     * @Param: []
     * @Return: boolean
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/31 19:25
     * @Description: 入栈操作
     * @Param: [int]
     * @Return: void
     */
    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("栈满");
        }
        top++;
        stack[top] = value;
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/31 19:26
     * @Description: 出栈
     * @Param: []
     * @Return: int
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }
    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/31 19:28
     * @Description: 遍历栈
     * @Param: []
     * @Return: void
     */
    public void list(){
        if (isEmpty()){
            throw new RuntimeException("栈空，没有数据");
        }
//        从栈顶开始取数据
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d] = %d\n",i,stack[i]);
        }
    }
}