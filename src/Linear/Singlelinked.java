package Linear;

import java.util.Stack;

/**
 * @author hongxiaobin
 * @create 2022/3/7-10:54
 * @description 创建水浒传英雄排名链表
 */
public class Singlelinked {
    public static void main(String[] args) {
//        进行测试
//        先创建结点
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(4, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(2, "林冲", "豹子头");
//        创建一个链表
        SingleLinkedList singlelinkedlist = new SingleLinkedList();
//        加入
        singlelinkedlist.addByOrder(heroNode1);
        singlelinkedlist.addByOrder(heroNode2);
        singlelinkedlist.addByOrder(heroNode3);
        singlelinkedlist.addByOrder(heroNode4);
//        显示
        singlelinkedlist.list();
    }
}

/*定义一个头结点headNode*/
class HeroNode {
    public int no;
    public String name;
    //    昵称
    public String nickname;
    //    下一个结点
    public HeroNode next;

    //    定义HeroNode
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'';
    }

    //    定义单链表管理英雄任务
}

class SingleLinkedList {
    //        创建头结点
    private HeroNode head = new HeroNode(0, "", "");

    //        添加结点到单向链表
//        思路
//        1.找到当前链表的最后一个结点
//        2.将最后一个结点的next指向新的结点
    public void add(HeroNode heroNode) {
//            因为头结点不能动所以需要一个辅助变量
        HeroNode temp = head;
//            遍历链表，找到最后一个结点
        while (true) {
//                找到最后
            if (temp.next == null) {
                break;
            }
//                没有找到最后,将temp后移
            temp = temp.next;
        }
//            但退出循环时，temp指向链表的最后
//           将最后的结点指向新的结点
        temp.next = heroNode;
    }


    /**
     * 第二种添加结点的方式，按照顺序添加
     */
    public void addByOrder(HeroNode heroNode) {
//        因为头结点不能动，因此需要一个辅助变量来找到添加的位置
//        因为单链表，因为我们要找的是temp是位于添加位置的前一个结点，否则加不进去
        HeroNode temp = head;
//        用来判断添加的结点编号是否已经存在，默认值为false不存在
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
//            位置找到，temp的后面插入
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
//                说明编号存在
                flag = true;
                break;
            }
//            后移继续遍历
            temp = temp.next;
        }
//        判断可否添加
        if (flag) {
            System.out.printf("准备插入的英雄的编号 %d 已经存在\n", heroNode.no);
        } else {
//            插入到链表中
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 显示链表，遍历
     */
    public void list() {
//            先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
//            链表不为空
        HeroNode temp = head.next;
        while (true) {
//                判断是否为链表最后
            if (temp == null) {
                break;
            }
//                输出结点信息
            System.out.println(temp);
//                将temp后移
            temp = temp.next;
        }
    }

    /**
     * @Author: hongxiaobin
     * @Description: 进行逆序打印
     * @Date: 2022/3/22 20:15
     * @Param:
     * @Return:
     */
    public void reversePrint(HeroNode heroNode) {
        if (head.next ==null){
//            空链表不能打印
            return;
        }
//        创建一个栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
//        将链表中的所有结点压入栈中
        while (cur != null){
            stack.push(cur);
//            结点后移
            cur = cur.next;
        }
//        将栈中的结点进行打印，pop出栈
//        栈的特点，先进后出
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }
}

