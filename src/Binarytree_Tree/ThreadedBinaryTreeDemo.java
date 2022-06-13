package Binarytree_Tree;

/**
 * 实现线索化二叉树功能
 *
 * @Author hongxiaobin
 * @Time 2022/4/27-20:50
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HedNode root = new HedNode(1, "Tom");
        HedNode hedNode2 = new HedNode(3, "Jack");
        HedNode hedNode3 = new HedNode(6, "Smith");
        HedNode hedNode4 = new HedNode(8, "Mary");
        HedNode hedNode5 = new HedNode(10, "King");
        HedNode hedNode6 = new HedNode(14, "Dim");
//        创建二叉树
        root.setLeft(hedNode2);
        root.setRight(hedNode3);
        hedNode2.setLeft(hedNode4);
        hedNode2.setRight(hedNode5);
        hedNode3.setLeft(hedNode6);
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(root);
        threadedBinaryTree.threadedNodes();
//        以10号结点测试
        HedNode left = hedNode5.getLeft();
        HedNode right = hedNode5.getRight();
        System.out.printf("10号结点的前驱结点是 %s \n", left.toString());
        System.out.printf("10号结点的后继结点是 %s \n", right.toString());
//        当线索化二叉树后，不能再用原来的遍历方式 8,3,10,1,14,6
        System.out.println("\n使用线索化的方式遍历 线索化二叉树");
        threadedBinaryTree.threadedList();
    }
}

/**
 * 创建二叉树
 */
class ThreadedBinaryTree {
    private HedNode root;
    //    为了实现线索化，需要创建一个指向当前结点的前驱结点的指针
    //    在递归进行线索化时，pre总是保留前一个结点
    private HedNode pre = null;

    public ThreadedBinaryTree(HedNode root) {
        this.root = root;
    }

    public void threadedNodes() {
        this.threadedNodes(root);
    }

    /**
     * 中序遍历线索化二叉树
     */
    public void threadedList(){
//        定义一个变量，用来存储当前遍历的结点，从root开始
        HedNode node = root;
        while(node != null){
//            循环的找到leftType == 1结点，第一个找到就是8结点
//            后面随着遍历而变化，因为当leftType==1时，说明该节点是按照线索化处理后的有效结点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
//            打印当前结点
            System.out.println(node);
//            如果当前结点的右指针，指向的事后继结点，就一直输出
            while (node.getRightType() == 1){
//                获取到当前结点的后继结点
                node = node.getRight();
                System.out.println(node);
            }
//            替换这个遍历的结点
            node = node.getRight();
        }
    }


    /**
     * 编写中序线索化的方法（中序遍历）
     * @param node the node当前需要线索化的结点
     */
    public void threadedNodes(HedNode node) {
//        如果node为空，不能线索化
        if (node == null) {
            return;
        }
//        1.先线索化左子树
        threadedNodes(node.getLeft());
//        2.线索化当前结点!!!
//        2.1处理当前结点的前驱结点
        if (node.getLeft() == null) {
//            让当前结点的左指针指向前驱结点
            node.setLeft(pre);
//            修改当前结点的左指针类型,指向前驱结点
            node.setLeftType(1);
        }
//        2.2处理后继结点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
//        ！！！每处理一个节点后，让当前结点时下一个结点的前驱结点
        pre = node;
//        3.线索化右子树
        threadedNodes(node.getRight());
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("当前二叉树为空，无法遍历！");
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("当前二叉树为空，无法遍历！");
        }
    }

    /**
     * 后续遍历
     */
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("当前二叉树为空，无法遍历！");
        }
    }

    /**
     * 前序查找
     *
     * @param no the no
     * @return the heo node
     */
    public HedNode preOrdrSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    /**
     * 中序查找
     *
     * @param no the no
     * @return the heo node
     */
    public HedNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    /**
     * 后序查找
     *
     * @param no the no
     * @return the heo node
     */
    public HedNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    /**
     * 删除指定结点
     *
     * @param no the no
     */
    public void delNode(int no) {
        if (root != null) {
//            如果只有一个root结点，这里立即判断root是否为要删除的结点
            if (root.getNo() == no) {
                root = null;
            } else {
                root.deleNode(no);
            }
        } else {
            System.out.println("空树，不能删除！");
        }
    }
}

/**
 * 创建节点类
 */
class HedNode {
    private int no;
    private String name;
    //    指向左节点
    private HedNode left;
    //    指向右节点
    private HedNode right;
    //    如果leftType==0表示指向的是右子树，如果是1则表示指向前驱结点
    private int leftType;
    //    如果rightType==0表示指向的事右子树，如果是1表示指向后继结点
    private int rightType;

    public HedNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HedNode getLeft() {
        return left;
    }

    public void setLeft(HedNode left) {
        this.left = left;
    }

    public HedNode getRight() {
        return right;
    }

    public void setRight(HedNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeoNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历
     * 1.选输出当前结点（初始时为root结点）
     * 2.如果左节点不为空，则递归继续前序遍历
     * 3.如果右节点不为空，则递归继续前序遍历
     */
    public void preOrder() {
//        先输出父节点
        System.out.println(this);
//        递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
//        递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     * 1.如果当前结点的左子节点不为空，则继续中序遍历
     * 2.输出当前结点
     * 3.如果当前结点的右子节点不为空，则继续终须遍历
     */
    public void infixOrder() {
//        递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
//        输出父节点
        System.out.println(this);
//        递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 后序遍历
     * 1.如果当前结点的左子节点不为空，则继续递归后续遍历
     * 2.如果当前结点的右子节点不为空，则继续遍历后续遍历
     * 3.输出当前结点
     */
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序查找思路
     * 1.先判断当前结点的no是否等于要查找的
     * 2.如果相等则返回当前结点
     * 3.如果不等则判断当前结点的左子节点是否为空，不为空递归前序查找
     * 4.如果左递归前序查找，找到结点，则返回，否则继续判断，当前的右子节点是否为空，如果不空，则继续向右递归前序查找
     *
     * @param no the no
     * @return the heo node
     */
    public HedNode preOrderSearch(int no) {
//        比较当前结点是不是
        System.out.println("进入前序查找");
        if (this.no == no) {
            return this;
        }
//        则判断当前结点的左子节点是否为空，如果不为空，则递归前序查找
//        如果做地柜前序查找，找到了则返回
        HedNode resNode = null;
        if (this.left != null) {
//            左子节点去调用前序查找
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
//        右边
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
//        三个方向都找完了，必须返回，无论是否为空
        return resNode;
    }


    /**
     * 中序查找思路
     * 1.判断当前结点的左子节点是否为空，如果不为空，则递归中序查找
     * 2.如果找到，则返回，如果没有找到，就和当前结点比较，如果是则返回当前结点，否则继续进行有递归的中序查找
     * 3.如果有递归中序查找，找到则返回，否则返回null
     *
     * @param no the no
     * @return the heo node
     */
    public HedNode infixOrderSearch(int no) {
        HedNode resNode = null;
//        左边
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
//        判断当前
        System.out.println("进入中序查找");
        if (this.no == no) {
            return this;
        }
//        右边
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 后序查找思路
     * 1.判断当前结点的左子节点是否为空，如果不为空，则递归后续查找
     * 2.如果找到则返回，如果没有找到，就判断当前结点的右子节点是否为空，如果不为空，则右递归进行后续查找，如果找到，则返回
     * 3，就和当前结点进行比较，如果是则返回，否则返回null
     *
     * @param no the no
     * @return the heo node
     */
    public HedNode postOrderSearch(int no) {
        HedNode resNode = null;
//        左边
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
//        右边
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
//        当前
        System.out.println("进入后序查找");
        if (this.no == no) {
            return this;
        }
        return resNode;
    }

    /*
     * 删除结点
     * 1.如果是叶子结点，就直接删除该节点
     * 2.如果删除的不是叶子结点，就删除该子树*/

    /**
     * 思路
     * 1.因为目前的二叉树是单向的，所以是判断当前结点的 子节点 是否需要删除结点，而不能去判断当前这个结点是不是需要删除的结点
     * 2.如果当前结点的左子节点不为空，并且左子节点的编号就是需要删除的结点，就将this.left置空，并且结束删除
     * 3.如果当前结点的右子节点不为空，并且右子节点的编号就是需要删除的结点，就将this.right置空，并且结束删除
     * 4.如果第二三步没有删除结点，那么需要向左子树（非空）递归删除
     * 5.如果第四步没有删除结点，那么需要向右子树（非空）递归删除
     * 6.考虑本身root为空树，或只有一个root结点，则等价于二叉树置空
     *
     * @param no the no
     */
    public void deleNode(int no) {
//        2
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
//        3
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
//        4
        if (this.left != null) {
            this.left.deleNode(no);
        }
//        5
        if (this.right != null) {
            this.right.deleNode(no);
        }
    }
}
