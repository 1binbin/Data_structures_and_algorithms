package Linear;

/**
 * @Author hongxiaobin
 * @Time 2022/3/22-22:39
 * @Description 用单向环形链表实现约瑟夫问题
 */
public class Jpsephu {
    public static void main(String[] args) {
//        测试构建和遍历
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
//        加入5个小孩
        int startNo = 1, countNum = 10, nums = 28;
        circleSingleLinkedList.addBoy(nums);
        circleSingleLinkedList.countBoy(startNo, countNum, nums);
    }
}

/**
 * @Author: hongxiaobin
 * @Date: 2022/3/22 22:39
 * @Description: 定义约瑟夫环的结点
 */
class Boy {
    /**
     * 编号
     */
    private int no;
    /**
     * 下一个结点
     */
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}

/**
 * @Author: hongxiaobin
 * @Date: 2022/3/22 22:41
 * @Description: 创建一个环形的单向链表
 */
class CircleSingleLinkedList {
    /**
     * 创建一个first结点，但是目前还没有编号
     */
    private Boy first = new Boy(-1);

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/22 22:43
     * @Description: 添加小孩结点，构成一个环形链表
     * @Param: int 结点的个数
     * @Return: void
     */
    public void addBoy(int nums) {
//        对nums进行数据检验
        if (nums < 2) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;
//        使用一个for循环创建一个环形链表
        for (int i = 1; i <= nums; i++) {
//            根据编号，创建小孩结点
            Boy boy = new Boy(i);
//            如果是第一个小孩
            if (i == 1) {
                first = boy;
//                构成环形
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/22 23:22
     * @Description: 遍历当前环形链表
     * @Param: null
     * @Return: void
     */
    public void showBoy() {
//        判断链表是否为空
        if (first == null) {
            System.out.println("链表为空,没有任何小孩");
            return;
        }
//        因为first不能动，所以使用一个辅助指针遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
//            结点后移
            curBoy = curBoy.getNext();
        }
    }

    /**
     * @Author: hongxiaobin
     * @Date: 2022/3/31 9:17
     * @Description: 根据用户输入计算出圈的小孩
     * @Param: int[第几个小孩开始数] int[表示数几下] int[表示最初有多少个小孩在圈中]
     * @Return: void
     */
    /*
     * 出圈顺序
     * 1.需求创建一个辅助指针helper，事先应该指向环形链表的最后这个结点
     * 2.当报数前，先让first和helper移动k-1次，当小孩报数时，让first和helper指针同时移动n-1次
     * 3.这时就可以将first指向的小孩出圈
     * 4.first= first.next
     *   helper.next = first
     * 5.原来first指向的结点没有任何引用就会被垃圾回收*/
    public void countBoy(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            throw new NullPointerException("参数输入有误");
        }
//        创建辅助指针帮助小孩出圈
        Boy helper = first;
        //                说明helper指向最后一个结点
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }
//        报数前，先让first和helper移动k-1次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
//        当小孩报数时，让first和helper指针同时移动n-1次
//            剩下一个人
        while (helper != first) {
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩 %d 出圈\n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩为 %d", first.getNo());
    }
}