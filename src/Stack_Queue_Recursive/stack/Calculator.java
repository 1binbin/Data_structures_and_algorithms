package Stack_Queue_Recursive.stack;

/**
 * @Author hongxiaobin
 * @Time 2022/3/31-20:07
 * @Description 使用栈来完成计算一个表达式的结果
 */
public class Calculator {
    /**
     * 使用栈完成表达式计算思路
     * 1.通过一个index值（索引）,来遍历表达式
     * 2.如果发现是一个数字，就直接入数栈（保存数字部分）
     * 3.如果发现是一个符号
     * 如果当前的符号栈为空，就直接入栈
     * 如果符号栈有操作符，就进行比较
     * 如果当前的操作符的优先级小于或等于栈中的操作符,就需要从数栈中pop出两个数，在从符号栈中pop出一个符号，进行运算，将得到的结果，入数栈，然后将当前的操作符入符号栈
     * 如果当前的操作符的优先级大于栈中的操作符,就直接入符号栈
     * 4.当表达式扫描完毕，就顺序从数栈和符号栈中pop出相应的数和符号并运算
     * 5.最后在数栈中只会有一个数字即为结果
     */
    public static void main(String[] args) {
//        根据思路模拟计算器
        String expression = "70+2*6-2";
//        创建一个数栈和符号栈
        Arraystack2 numStack = new Arraystack2(10);
        Arraystack2 operStack = new Arraystack2(10);
//        定义需要的相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        int ch = ' ';
        String keepnum = "";
//        开始循环扫描表达式
        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOper(ch)) {
//                判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {
//                    处理
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
//                        把运算结果入数栈
                        numStack.push(res);
//                        把当前的操作符入符号栈
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                } else {
//                    如果为空，直接入栈
                    operStack.push(ch);
                }
            } else {
//                如果是数则直接入数栈
//                numStack.push(ch);  需要将 ’1‘转化为 1
//                当处理多位数字时，不能发现是一个数就立即入栈，因为可能是多位数，所以在处理数时，需要向exepression的表达式后再看一位，如果是数就继续扫描，否则就入栈
                keepnum += ch;
//                如果ch是最后一位则直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepnum));
                } else {
//                判断下一个字符是不是数字，如果是则继续扫描，否则就直接入栈
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepnum));
//                    重要！！！！！！清空keepnum
                        keepnum = "";
                    }
                }
            }
//            让index+1
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        while (true) {
//            如果符号栈为空则计算到最后的结果，数栈中只有一个数字，即为结果
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        System.out.printf("表达式 %s = %d", expression, numStack.pop());
    }
}

/**
 * @Author: hongxiaobin
 * @Date: 2022/3/31 22:10
 * @Description: 创建一个栈
 */
class Arraystack2 {
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

    public Arraystack2(int maxSize) {
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
     * @Date: 2022/3/31 22:40
     * @Description: 返回当前栈顶的值, 但是没有输出
     * @Param: []
     * @Return: int
     */
    public int peek() {
        return stack[top];
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/31 19:28
     * @Description: 遍历栈
     * @Param: []
     * @Return: void
     */
    public void list() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据");
        }
//        从栈顶开始取数据
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/31 22:12
     * @Description: 返回运算符的优先级，数字越大，优先级越高
     * @Param: [int]
     * @Return: int
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/31 22:17
     * @Description: 判断是否为操作符
     * @Param: [char]
     * @Return: boolean
     */
    public boolean isOper(int val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/31 22:19
     * @Description: 计算方法
     * @Param: [int, int, int]
     * @Return: int
     */
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}

