package Fourth_chapter.recursion;

import java.util.Scanner;

/**
 * @Author hongxiaobin
 * @Time 2022/4/5-11:47
 * @Description
 */
public class RecursionTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个大于2的整数");
        int i = scanner.nextInt();
        System.out.println("打印问题");
        test(i);
        System.out.println();
        System.out.println("阶乘问题");
        System.out.println(i+" 的阶乘为 "+factorial(i));
    }
//    打印问题
    private static void test(int n){
        if (n>2){
            test(n-1);
        }
            System.out.print("n = "+n+"\t");
    }
//    阶乘问题
    private static long factorial(int n){
        if (n==1){
            return 1;
        }else {
            return factorial(n-1)*n;
        }
    }
}
