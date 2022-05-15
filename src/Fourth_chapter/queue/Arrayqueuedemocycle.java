package Fourth_chapter.queue;

import java.util.Scanner;

/**
 * @author hongxiaobin
 * @create 2022/3/7-10:10
 * @description 用数组模拟循环队列
 */
public class Arrayqueuedemocycle {
    public static void main(String[] args) {
        Arrayqueuecycle arrayqueuecycle = new Arrayqueuecycle(4);
        char key;
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
//        输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            System.out.println("e(exit):退出程序");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayqueuecycle.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数据");
                    int value = scanner.nextInt();
                    arrayqueuecycle.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayqueuecycle.getQueue();
                        System.out.printf("取出数据为 %d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayqueuecycle.headQueue();
                        System.out.printf("队列的头数据是 %d\n", res);
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

class Arrayqueuecycle {
    //    定义数组最大容量，队头指针，队尾指针，用于存放数据的数组
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    //    创建队列的构造器
    public Arrayqueuecycle(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    //    判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //    判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //   入队
    public void addQueue(int x) {
//        if (isFull()) {
//            System.out.println("队列已满不可以加入数据");
//        } else {
//            arr[rear] = n;
//            rear = (rear + 1) % maxSize;
//        }
        if (this.front==(this.rear+1)%arr.length){
            int[] temp = this.arr;
            this.arr = new int[arr.length * 2];
            int j=0;
            for (int i=front;i!=front;i=(i+1)%temp.length) {
                this.arr[j++]=temp[i];
            }
            this.front=0;
            this.rear=j;
        }
        this.arr[rear]=x;
        rear=(rear+1)%arr.length;
    }

    //    出队
    public int getQueue() {
        int temp =  this.arr[front];
        front=(front+1)% arr.length;
        return temp;
    }

    //    显示所有的数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = 0; i < front + getSize(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    private int getSize() {
        return (rear + maxSize - front) % maxSize;
    }

    //    显示队列的头数据，不是出列
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front];
    }
}
