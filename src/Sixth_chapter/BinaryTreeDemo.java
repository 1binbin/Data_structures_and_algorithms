package Sixth_chapter;

/**
 * 实现二叉树的各种操作
 *
 * @Author hongxiaobin
 * @Time 2022 /4/26-20:39
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
//        先创建一棵二叉树
        HeoNode root = new HeoNode(1, "宋江");
        HeoNode node2 = new HeoNode(2, "吴用");
        HeoNode node3 = new HeoNode(3, "卢俊义");
        HeoNode node4 = new HeoNode(4, "林冲");
        HeoNode node5 = new HeoNode(5, "关胜");
//        说明：先手动创建该二叉树，后续再改进用递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        BinaryTree tree = new BinaryTree(root);
//        测试前序遍历 1,2,3,5,4
        System.out.println("前序遍历");
        tree.preOrder();
//        测试中序遍历 2,1,5,3,4
        System.out.println("中序遍历");
        tree.infixOrder();
//        测试后续遍历 2,5,4,3,1
        System.out.println("后续遍历");
        tree.postOrder();

//        测试前序查找
        System.out.println();
        System.out.println("前序遍历查找");
        HeoNode resNode = tree.preOrdrSearch(5);
        if (resNode != null) {
            System.out.printf("找到了，信息为 no=%d name=%s \n", resNode.getNo(), resNode.getName());
//            System.out.printf("遍历次数为 %d \n",resNode.precount);
        } else {
            System.out.println("找不到！");
        }
//        测试中序查找
        System.out.println("中序遍历查找");
        HeoNode resNode1 = tree.infixOrderSearch(5);
        if (resNode != null) {
            System.out.printf("找到了，信息为 no=%d name=%s \n", resNode1.getNo(), resNode1.getName());
//            System.out.printf("遍历次数为 %d \n",resNode.infixcount);
        } else {
            System.out.println("找不到！");
        }
//        测试后序查找
        System.out.println("后序遍历查找");
        HeoNode resNode2 = tree.postOrderSearch(5);
        if (resNode != null) {
            System.out.printf("找到了，信息为 no=%d name=%s \n", resNode2.getNo(), resNode2.getName());
//            System.out.printf("遍历次数为 %d \n",resNode.postcount);
        } else {
            System.out.println("找不到！");
        }

//        测试删除指定结点
        System.out.println();
        System.out.println("删除前");
        tree.preOrder();
        tree.delNode(3);
//        tree.delNode(5);
        System.out.println("删除 3 号结点之后");
        tree.preOrder();
    }
}

/**
 * 创建二叉树
 */
class BinaryTree {
    private HeoNode root;

    public BinaryTree(HeoNode root) {
        this.root = root;
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
    public HeoNode preOrdrSearch(int no) {
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
    public HeoNode infixOrderSearch(int no) {
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
    public HeoNode postOrderSearch(int no) {
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
 * 创建一个HeoNode结点类
 */
class HeoNode {
    private int no;
    private String name;
    /**
     * 指向左节点
     */
    private HeoNode left;
    /**
     * 指向右节点
     */
    private HeoNode right;

    public HeoNode(int no, String name) {
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

    public HeoNode getLeft() {
        return left;
    }

    public void setLeft(HeoNode left) {
        this.left = left;
    }

    public HeoNode getRight() {
        return right;
    }

    public void setRight(HeoNode right) {
        this.right = right;
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
    public HeoNode preOrderSearch(int no) {
//        比较当前结点是不是
        System.out.println("进入前序查找");
        if (this.no == no) {
            return this;
        }
//        则判断当前结点的左子节点是否为空，如果不为空，则递归前序查找
//        如果做地柜前序查找，找到了则返回
        HeoNode resNode = null;
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
    public HeoNode infixOrderSearch(int no) {
        HeoNode resNode = null;
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
    public HeoNode postOrderSearch(int no) {
        HeoNode resNode = null;
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