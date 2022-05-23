package Eleventh_chapter;

import java.util.Scanner;

/**
 * 实现哈希表
 *
 * @Author hongxiaobin
 * @Time 2022/5/23-16:52
 */
public class HashTabDemo {
    public static void main(String[] args) {
//        创建一个哈希表
        HashTab hashTab = new HashTab(7);
//        写一个简单的菜单
        String key;
        Scanner scanner = new Scanner(System.in);
        boolean is = true;
        while (is) {
            System.out.println("add：添加雇员");
            System.out.println("list：显示雇员");
            System.out.println("find：查找雇员");
            System.out.println("exit：退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    is = false;
                    break;
                default:
                    break;
            }
            System.out.println();
        }
    }
}

/**
 * 哈希表
 *
 * @Author: hongxiaobin
 * @Date: 2022/5/23 17:03
 */
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    public HashTab(int size) {
//        初始化
        empLinkedListArray = new EmpLinkedList[size];
        this.size = size;
//        !不要忘记分别初始化每一个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    /**
     * 添加雇员
     *
     * @Param:
     * @Return:
     */
    public void add(Emp emp) {
//        根据员工id得到该员工应当添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
//        将emp添加到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    /**
     * 遍历所有的链表
     *
     * @Param:
     * @Return:
     */
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    /**
     * 根据输入的id查找雇员
     *
     * @Param:
     * @Return:
     */
    public void findEmpById(int id) {
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpByID(id);
        if (emp != null) {
            System.out.printf("在第 %d 条链表中找到雇员 id = %d", empLinkedListNO, id);
        } else {
            System.out.println("在哈希表中没有找到雇员");
        }
    }

    /**
     * 编写一个散列函数（取模法）
     *
     * @Param: int id员工编号
     * @Return: int 应该存放的位置
     */
    public int hashFun(int id) {
        return id % size;
    }
}

/**
 * 表示一个员工
 *
 * @Author: hongxiaobin
 * @Date: 2022/5/23 16:54
 */
class Emp {
    public int id;
    public String name;
    //    默认为null
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

/**
 * 创建EmpLinkedList ,表示链表
 *
 * @Author: hongxiaobin
 * @Date: 2022/5/23 16:55
 */
class EmpLinkedList {
    //    头指针，指向第一个Emp，因此第一个结点指向第一个雇员
    private Emp head;

    /**
     * 添加雇员
     * 说明
     * 1.假定，当添加雇员时，id是自增长的，即id是自动分配的
     * 2.因此尾添加即可
     *
     * @Param:
     * @Return:
     */
    public void add(Emp emp) {
//        如果添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
//        如果不是第一个雇员，则使用一个辅助指针，帮助定位到最后
        Emp curEmp = head;
        while (curEmp.next != null) {
            curEmp = curEmp.next;
        }
//        退出循环时，直接将emp加入链表
        curEmp.next = emp;
    }

    /**
     * 遍历链表
     *
     * @Param:
     * @Return:
     */
    public void list(int no) {
        if (head == null) {
            System.out.println("第 " + no + " 条链表为空");
            return;
        }
        System.out.print("第 " + no + " 条链表信息为 ");
        Emp curEmp = head;
        while (true) {
            System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    /**
     * 根据id查找雇员
     *
     * @Param: int id 雇员编号
     * @Return: Emp 雇员结点 找不到返回null
     */
    public Emp findEmpByID(int id) {
//        判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
//        辅助指针
        Emp curEmp = head;
        //                找到了
        while (curEmp.id != id) {
            //            退出条件,遍历完了还没有找到
            if (curEmp.next == null) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}