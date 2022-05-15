package com.other;

/**
 * @Author hongxiaobin
 * @Time 2022/3/29-9:35
 * @Description
 */
public class Fibonacci {
    public static void main(String[] args) {
        for (int i = 1;i<=50;i++){
            System.out.print(getdata(i)+"\t");
        }
    }
    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/29 9:43
     * @Description: 递归求斐波那契数列，时间复杂度O(2^n)
     * @Param: int
     * @Return: long
     */
    private static long getdata(int n){
        if (n <0){
            throw new IndexOutOfBoundsException(n);
        }
        if (n==1 || n==0){
            return 1;
        }else {
            return getdata(n-1)+getdata(n-2);
        }
    }
}
