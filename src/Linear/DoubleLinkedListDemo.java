package Linear;

/**
 * @Author hongxiaobin
 * @Time 2022/3/22-20:44
 * @Description 实现双向链表
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 heroNode1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 heroNode2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 heroNode3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 heroNode4 = new HeroNode2(4, "林冲", "豹子头");
//        创建一个双向链表对象
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.doubleadd(heroNode1);
        doubleLinkedList.doubleadd(heroNode2);
        doubleLinkedList.doubleadd(heroNode3);
        doubleLinkedList.doubleadd(heroNode4);
        doubleLinkedList.doublelist();
        System.out.println("修改结点");
        HeroNode2 heroNode5 = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.doubleupdate(heroNode5);
        doubleLinkedList.doublelist();
        System.out.println("删除结点");
        doubleLinkedList.doubledel(3);
        doubleLinkedList.doublelist();
    }
}

/**
 * @Author: hongxiaobin
 * @Date: 2022/3/22 20:55
 * @Description: 创建结点
 */
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    /**
     * 指向下一个结点
     */
    public HeroNode2 next;
    /**
     * 指向前一个结点
     */
    public HeroNode2 prev;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

/**
 * @Author: hongxiaobin
 * @Date: 2022/3/22 20:59
 * @Description: 创建双向链表类
 */
class DoubleLinkedList {
    //        创建头结点
    private HeroNode2 head = new HeroNode2(0, "", "");

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/22 21:03
     * @Description: 返回头结点
     * @Param: null
     * @Return: HerNode2
     */
    public HeroNode2 getHead() {
        return head;
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/22 21:03
     * @Description: 遍历双向链表
     * @Param: null
     * @Return: void
     */
    public void doublelist() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/22 21:05
     * @Description: 添加结点，默认添加到双向链表的最后
     * @Param: HeroNode2
     * @Return: void
     */
    public void doubleadd(HeroNode2 heroNode) {
//        因为head结点不能动，所以需要一个辅助遍历temp
        HeroNode2 temp = head;
//        遍历链表找到最后
        while (temp.next != null) {
            temp = temp.next;
        }
//        将新的结点插到最后
//        形成一个双向链表
        temp.next = heroNode;
        heroNode.prev = temp;
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/22 21:09
     * @Description: 修改一个结点
     * @Param: HeroNode2
     * @Return: void
     */
    public void doubleupdate(HeroNode2 heroNode2) {
//        判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
//        找到需要修改的结点，根据no编号
//        定义一个辅助变量
        HeroNode2 temp = head.next;
//        表示是否找到新的结点
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == heroNode2.no) {
//                找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
//        根据flag，判断是否找到要修改的结点
        if (flag) {
            temp.name = heroNode2.name;
            temp.nickname = heroNode2.nickname;
        }
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/22 21:25
     * @Description: 删除一个结点
     * @Param:
     * @Return:
     */
    public void doubledel(int no) {
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
//            可以删除
            temp.prev.next = temp.next;
//            这里的代码有问题，删除如果是最后一个结点
//            如果是最后一个结点就不需要执行下面的语句，否则出现空指针异常
            if (temp.next != null) {
                temp.next.prev = temp.prev;
            }
        } else {
            System.out.printf("要删除的结点 %d 找不到", no);
        }
    }
}
