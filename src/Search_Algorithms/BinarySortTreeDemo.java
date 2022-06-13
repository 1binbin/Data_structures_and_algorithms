package Search_Algorithms;

/**
 * 二叉排序树BST
 * 介绍
 * 1.对于二叉排序树的任何一个非叶子节点，要求左子节点的值比当前节点的值小，右子节点的值比当前节点的值大
 * 二叉树的删除
 * 包括删除叶子结点、删除只有一棵子树的结点、删除有两颗子树的结点
 * <p>
 * 一、删除叶子结点思路
 * 1.需要先去找到要删除的结点targetNode
 * 2.找到targetNode的父节点parent
 * 3.确定targetNode的parent的左子节点还是右子节点，再删除
 * 4.如果是左子节点，parent.left == null 如果是右子节点,parent.right == null
 * <p>
 * 二、删除只有一棵子树的结点
 * 1.需要先去找到要删除的结点targetNode
 * 2.找到targetNode的父节点parent
 * 3.确定这个targetNode的子节点是左子节点还是右子节点
 * 4.确定targetNode是parent的左子节点还是右子节点
 * 5.如果targetNode有左子节点
 * 5.1如果targetNode是parent的左子节点，parent.left = targetNode.left
 * 5.1如果targetNode是parent的右子节点，parent.right = targetNode.left
 * 6.如果targetNode有右子节点
 * 6.1如果targetNode是parent的左子节点，parent.left = targetNode.right
 * 6.1如果targetNode是parent的右子节点，parent.right = targetNode.right
 * <p>
 * 三、删除具有两颗子树的结点
 * 1.需要先去找到要删除的结点targetNode
 * 2.找到targetNode的父节点parent
 * 3.从targetNode的右子树找到最小的结点
 * 4.用一个临时变量将最小结点的值保存到temp
 * 5.删除该最小结点然后targetNode.value = temp
 *
 * @Author hongxiaobin
 * @Time 2022/5/9-21:30
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
//        循环插入结点
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
//        遍历二叉排序树
        System.out.println("中序遍历二叉排序树");
        binarySortTree.infixOrde();
        System.out.println();
        binarySortTree.preOrder();
        System.out.println();
        binarySortTree.postOrder();

//        删除叶子结点
        System.out.println("删除叶子结点 2");
        binarySortTree.delNode(10);
        binarySortTree.infixOrde();
        System.out.println();
    }
}

/**
 * 创建二叉排序树
 *
 * @Author: hongxiaobin
 * @Date: 2022/5/9 21:41
 */
class BinarySortTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * 查找要删除的结点
     */
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    /**
     * 查找要删除的结点的父节点
     */
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }
    /**
     * @Param: Node传入的结点，当做二叉排序树的根节点
     * @Return: 以Node为根节点的二叉排序树的最小结点值
     */
    public int delRightTreeMin(Node node){
        Node target = node;
//        循环查找左子节点，就会找到最小值
        while (target.left!=null){
            target = target.left;
        }
//        这时target就指向了最小结点
        delNode(target.value);
        return target.value;
    }

    /**
     * 删除结点
     *
     * @Param: value 要删除的结点的值
     * @Return: void
     */
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
//            1.需要先找到要删除的结点targetNode
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
//            如果发现targetNode没有父节点，也就是二叉排序树只有一个结点，直接删除
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
//            去找到targetNode的父节点
            Node parent = searchParent(value);
//            如果删除的结点是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
//                判断targetNode是parent的左子节点还是右子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
//                删除有两个子树的结点
            } else if (targetNode.left!=null && targetNode.right!= null) {
                targetNode.value = delRightTreeMin(targetNode.right);
//                删除只有一棵子树的结点
            }else {
//                如果要删除的结点只有左子节点
                if (targetNode.left!=null){
                    if (parent.left.value == value){
                        parent.left = targetNode.left;
                    }else {
                        parent.right = targetNode.left;
                    }
//                    如果要删除的结点只有右子节点
                }else {
                    if (parent.left.value==value){
                        parent.left = targetNode.right;
                    }else {
                        parent.right = targetNode.right;
                    }
                }
            }
        }
    }

    /**
     * 中序遍历
     *
     * @Param:
     * @Return:
     */
    public void infixOrde() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("当前二叉排序树为空，不能遍历");
        }
    }
    public void preOrder() {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("当前二叉排序树为空，不能遍历");
        }
    }
    public void postOrder() {
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("当前二叉排序树为空，不能遍历");
        }
    }
}

/**
 * 建立节点
 *
 * @Author: hongxiaobin
 * @Date: 2022 /5/9 21:34
 */
class   Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 找到要删除的结点
     *
     * @Param: value 希望删除结点的值
     * @Return: Node 如果找到了就返回结点，否则返回null
     */
    public Node search(int value) {
        if (value == this.value) {
//            找到了
            return this;
        } else if (value < this.value) {
//            查找的值小于当前结点，向左子树查找
//            如果左子节点为空就找不到了，返回null
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除结点的父节点
     *
     * @Param: value 要找得结点的值
     * @Return: 返回的是要删除的结点的父节点，如果没有返回null
     */
    public Node searchParent(int value) {
//        如果当前结点就是父节点则直接返回
//        子节点不为空的时候才可以去判断
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
//            如果查找的值小于当前结点的值，并且当前结点的左子节点不为空
            if (value < this.value && this.left != null) {
//                向左子节树递归查找
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
//                向右子树递归查找
                return this.right.searchParent(value);
            } else {
//                没有找到父节点
                return null;
            }
        }
    }

    /**
     * 添加节点
     * 递归的方式，按照二叉排序树的规则
     *
     * @param node the node
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
//        判断传入的结点的值，和当前子树的根节点的值关系
//        this等价于当前树的根节点
        if (node.value < this.value) {
//            如果当前结点左子节点为空
            if (this.left == null) {
                this.left = node;
            } else {
//                递归向左子树添加
                this.left.add(node);
            }
//            添加的结点的值大于当前结点值
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    /**
     * 中序遍历
     *
     * @Param:
     * @Return:
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }
}
