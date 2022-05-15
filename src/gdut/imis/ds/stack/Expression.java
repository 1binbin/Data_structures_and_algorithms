package gdut.imis.ds.stack;

import java.util.Scanner;

/**
 * @Author hongxiaobin
 * @Time 2022/4/15-8:52
 * @Description 处理从键盘输入的表达式, 括号匹配
 */
public class Expression {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要处理的字符串");
        String s = scanner.nextLine();
        System.out.println(isMatch(s));
    }

    public static boolean isMatch(String expr) {
        Stack<String> stack = new SeqStack<String>();
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            switch (c) {
                case '(':
                    stack.push(")");
                    break;
                case '[':
                    stack.push("]");
                    break;
                case '{':
                    stack.push("}");
                    break;
                case ')':
                case ']':
                case '}':
                    if (stack.isEmpty()) {
                        return false;
                    } else if (stack.pop().equals(c + "")) {
                        return false;
                    }
                default:

            }
        }
        return stack.isEmpty();
    }
}
