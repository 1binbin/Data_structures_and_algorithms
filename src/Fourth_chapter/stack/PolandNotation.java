package Fourth_chapter.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @Author hongxiaobin
 * @Time 2022/4/1-21:43
 * @Description 实现逆波兰计算器（使用后缀表达式计算）
 */
public class PolandNotation {

    public static void main(String[] args) {
//        完成将一个中缀表达式转为后缀表达式的功能，步骤
//        1.将一个中缀表达式的数字和非数字分开存入一个list中
//        2,。将得到的中缀表达式list转为后缀表达式list
//        如将1+（（2+3）*4）-5=转为1 2 3 + 4 * 5 -
        String expression = "1+2*(3-4*(5+6)/7+8)-(9+10)*11";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("对应的中缀表达式对应的list为 "+infixExpressionList);
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("对应的后缀表达式对应的list为 "+parseSuffixExpressionList);
        System.out.println(expression+" 即 "+parseSuffixExpressionList+" 的计算结果为 "+calculate(parseSuffixExpressionList));
//        创建一个逆波兰表达式，为了方便，数字和符号使用空格隔开
        String suffixExpression = "3 4 + 5 * 6 -";
//        1.先把表达式放到一个ArrayList中
        List<String> rpnList = getListString(suffixExpression);
        System.out.println("计算的结果是：" + calculate(rpnList));
//        2.将ArrayList传给一个方法，遍历ArrayList配合栈完成计算

    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/4/1 21:51
     * @Description: 将表达式分割放在集合中
     * @Param: [String]
     * @Return: List<String>
     */
    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        Collections.addAll(list, split);
        return list;
    }

    /*
     * 要求
     * 1.输入一个逆波兰表达式（后缀表达式）使用栈（Stack）计算其结果
     * 2.支持小数和多位数整数
     * 思路分析[3 4 + 5 * 6 - 即为（3+4）*5-6=29]
     * 1.从左向右扫描，将3和4压入栈中
     * 2.遇到 + 运算符，因此弹出4和3，计算3+4的值并将结果压入栈
     * 3.将5入栈，接下来是 * 运算符，因此弹出5和7，得到结果35压入栈
     * 4.将6入栈，接下来是 - 运算符，计算出35-6的值，得到结果29即为最后的计算结果*/

    /**
     * @Author: hongxiaobin
     * @Date: 2022/4/1 21:54
     * @Description: 完成计算
     * @Param: [List<String>]
     * @Return: int
     */
    public static int calculate(List<String> ls) {
//        只需要创建一个栈即可
        Stack<String> stack = new Stack<>();
        for (String item : ls) {
//            使用正则表达式来取出数
//            匹配多位数
            if (item.matches("\\d+")) {
//                入栈
                stack.push(item);
            } else {
//                pop出两个数进行计算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if ("+".equals(item)) {
                    res = num1 + num2;
                } else if ("-".equals(item)) {
                    res = num1 - num2;
                } else if ("*".equals(item)) {
                    res = num1 * num2;
                } else if ("/".equals(item)) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(res + "");
            }
        }
//        最后留在栈中的数据即为结果
        return Integer.parseInt(stack.pop());
    }

    /*
     * 思路分析
     * 1.初始化两个栈：运算符栈s1和中间结果栈s2
     * 2.从左到右扫描中缀表达式
     * 3.遇到操作数时，将其压入s2
     * 4.遇到运算符时，比较其余s1栈顶运算符的优先级
     *      1）如果s1为空，或栈顶运算符为 ( 时，则直接将此运算符入栈
     *      2）否则，若优先级比栈顶运算符的高，也将运算符压入s1
     *      3）否则，将s1栈顶的运算符弹出并压入栈s2中，再次转到（4.1）与s1新的栈顶元素运算符进行相比较
     * 5.遇到括号时
     *      1）如果是左括号"("，则直接压入s1
     *      2）如果是有括号")"，则依次弹出s1栈顶的运算符，并压入s2，知道遇到左括号为止，此时将这一对括号丢弃
     * 6.重复步骤2到5
     * 7.将s1中剩余的运算符依次弹出并压入s2
     * 8.依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式*/

    /**
     * @Author: hongxiaobin
     * @Date: 2022/4/4 22:00
     * @Description: 将中缀表达式转为对应的List
     * @Param: [String]
     * @Return: List<String>
     */
    public static List<String> toInfixExpressionList(String s) {
//        定义一个List，存放中缀表达式对应的内容
        List<String> ls = new ArrayList<>();
//        这是一个指针用于遍历中缀字符串
        int i = 0;
//        用于对多位数的拼接
        String str;
//        没遍历到一个字符就放入c中
        char c;
        do {
//            如果c是一个非数字，就需要加入到ls里面去，用ASCII码判断
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
//                指针后移
                i++;
//                如果是一个数字，需要考虑多位数的问题
            } else {
//                先将str置空
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
//                    拼接
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/4/4 22:11
     * @Description: 将中缀表达式转为后缀表达式对应的list
     * @Param: [List<String>]
     * @Return: List<String>
     */
    public static List<String> parseSuffixExpressionList(List<String> ls) {
//        定义两个栈
//        符号栈
        Stack<String> s1 = new Stack<>();
//        因为s2栈在整个转换过程中没有pop操作，而且后面还需要逆序输出，因此采用栈比较麻烦，不使用s2栈，直接使用List<String>用来替换s2
        List<String> s2 = new ArrayList<>();
//        遍历ls
        for (String item : ls) {
            if (item.matches("\\d+")) {
//            如果是一个数就加入到s2
                s2.add(item);
            } else if ("(".equals(item)) {
//                如果是左括号，放入s1
                s1.push(item);
            } else if (")".equals(item)) {
//                如果是有右括号，则依次弹出s1栈顶的运算符，并压入s2，知道遇到左括号为止，此时将这一对括号丢弃
//                peek()查看栈顶的内容但是不弹出
                while (!"(".equals(s1.peek())) {
                    s2.add(s1.pop());
                }
//                ！！！将左括号弹出栈，即消除小括号
                s1.pop();
            } else {
//                遇到别的符号，考虑优先级
//                当item的优先级小于等于栈顶运算符优先级，将s1栈顶的运算符弹出并压入栈s2中，再次转到（4.1）与s1新的栈顶元素运算符进行相比较
//                问题：缺少一个方法用来比较优先级高低
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
//                还需要将item压入栈中
                s1.push(item);
            }
        }
//        将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
//        因为是存放到一个list中的，所以按照顺序输出就是中缀表达式对应的后缀表达式
        return s2;
    }
}

/**
 * @Author: hongxiaobin
 * @Date: 2022/4/4 22:59
 * @Description: 返回运算符对应的优先级
 */
class Operation {
    /**
     * 加
     */
    private static int ADD = 1;
    /**
     * 减
     */
    private static int SUB = 1;
    /**
     * 乘
     */
    private static int MUL = 1;
    /**
     * 除
     */
    private static int DIV = 1;

    /**
     * @Author: hongxiaobin
     * @Date: 2022/4/4 23:01
     * @Description: 返回优先级对应的数字
     * @Param: [String]
     * @Return: int
     */
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
//                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}

