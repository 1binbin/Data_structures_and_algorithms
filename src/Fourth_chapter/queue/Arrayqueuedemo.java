package Fourth_chapter.queue;

import java.util.Scanner;

/**
 * @author hongxiaobin
 * @create 2022/2/28-21:49
 * @description 用数组模拟队列,存在问题，数组只能用一次造成假溢出，解决-使用环形队列
 */
public class Arrayqueuedemo {
    public static void main(String[] args) {
        Arrayqueuecycle arrayqueue = new Arrayqueuecycle(3);
        char key;
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
//        输出一个菜单
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            System.out.println("e(exit):退出程序");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    arrayqueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数据");
                    int value = scanner.nextInt();
                    arrayqueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayqueue.getQueue();
                        System.out.printf("取出数据为 %d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayqueue.headQueue();
                        System.out.printf("队列的头数据是 %d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    System.out.println("输入菜单错误，请重新输入");
            }
            System.out.println();
        }
        System.out.println("程序退出");
    }
}

class Arrayqueue {
    //    定义数组最大容量，队头指针，队尾指针，用于存放数据的数组
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    //    创建队列的构造器
    public Arrayqueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    //    判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //    判断对垒是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //   入队
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满不可以加入数据");
        } else {
            rear++;//让real后移
            arr[rear] = n;
        }
    }

    //    出队
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不可取值");
        }
        front++;
        return arr[front];
    }

    //    显示所有的数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }
//    显示队列的头数据，不是出列
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front+1];
    }
}
