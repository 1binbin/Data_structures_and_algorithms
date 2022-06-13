package Search_Algorithms;

/**
 * 平衡二叉树（AVL）
 * 1.平衡二叉树也叫平衡二叉搜索树又被成为AVL树，可以保证查询效率较高
 * 2.具有以下特点，它是一棵空树或它的左右两个子树的高度差的绝对值不超过1.并且左右两个子树都是一棵平衡二叉树。平衡二叉树的实现方法有红黑树、AVL、替罪羊树，Treap树，伸展树等
 *
 * @Author hongxiaobin
 * @Time 2022/5/14-15:23
 */
public class AVLTreeDemo {

    public static void main(String[] args) {
//        测试左旋转
//        int[] arr = {4, 3, 6, 5, 7, 8};
//        测试右旋转
//        int[] arr = {10, 12, 8, 9, 7, 6};
//        测试双旋转
        int[] arr = {10,11,7,6,8,9};
//        创建一棵树
        AVlTree aVlTree = new AVlTree();
        for (int i = 0; i < arr.length; i++) {
            aVlTree.add(new AVLNode(arr[i]));
        }
//        中序遍历
        System.out.println("中序遍历");
        aVlTree.infixOrde();
        System.out.println("----------在平衡处理后----------");
        System.out.println("以该节点为根节点的树的高度");
        System.out.println(aVlTree.getRoot().height());//3
        System.out.println("左子树的高度");
        System.out.println(aVlTree.getRoot().leftHeight());//2
        System.out.println("右子树的高度");
        System.out.println(aVlTree.getRoot().rightHeight());//2
        System.out.println(aVlTree.getRoot());
    }
}

/**
 * 创建AVL树
 *
 * @Author: hongxiaobin
 * @Date: 2022/5/14 15:41
 */
class AVlTree {
    private AVLNode root;

    public AVLNode getRoot() {
        return root;
    }

    public void setRoot(AVLNode root) {
        this.root = root;
    }

    /**
     * 查找要删除的结点
     */
    public AVLNode search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    /**
     * 查找要删除的结点的父节点
     */
    public AVLNode searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    public void add(AVLNode node) {
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
    public int delRightTreeMin(AVLNode node) {
        AVLNode target = node;
//        循环查找左子节点，就会找到最小值
        while (target.left != null) {
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
            AVLNode targetNode = search(value);
            if (targetNode == null) {
                return;
            }
//            如果发现targetNode没有父节点，也就是二叉排序树只有一个结点，直接删除
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
//            去找到targetNode的父节点
            AVLNode parent = searchParent(value);
//            如果删除的结点是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
//                判断targetNode是parent的左子节点还是右子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
//                删除有两个子树的结点
            } else if (targetNode.left != null && targetNode.right != null) {
                targetNode.value = delRightTreeMin(targetNode.right);
//                删除只有一棵子树的结点
            } else {
//                如果要删除的结点只有左子节点
                if (targetNode.left != null) {
                    if (parent.left.value == value) {
                        parent.left = targetNode.left;
                    } else {
                        parent.right = targetNode.left;
                    }
//                    如果要删除的结点只有右子节点
                } else {
                    if (parent.left.value == value) {
                        parent.left = targetNode.right;
                    } else {
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
}

/**
 * 建立节点
 *
 * @Author: hongxiaobin
 * @Date: 2022 /5/9 21:34
 */
class AVLNode {
    int value;
    AVLNode left;
    AVLNode right;

    public AVLNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AVLNode{" + "value=" + value + '}';
    }

    /**
     * 返回以该结点为根节点的树的高度
     *
     * @Param:
     * @Return: int 树的高度
     */
    public int height() {
//        需要加上本身结点
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 返回左子树的高度
     *
     * @Param:
     * @Return:
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    /**
     * 返回右子树的高度
     *
     * @Param:
     * @Return:
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    /**
     * 处理左旋转,降低右子树的高度
     *
     * @Param:
     * @Return:
     */
    private void leftRotate() {
//        创建一个新的结点，以当前根节点的值
        AVLNode newNode = new AVLNode(value);
//        把新的结点的左子树设置为当前结点的左子树
        newNode.left = left;
//        把新的结点的右子树设置为当前结点的右子树的左子树
        newNode.right = right.left;
//        把当前结点的值替换为右子节点的值
        value = right.value;
//        把当前结点的右子树设置为右子树的右子树
        right = right.right;
//        把当前结点的左子树设置为新的结点
        left = newNode;
    }

    /**
     * 处理右旋转,降低左子树的高度
     *
     * @Param:
     * @Return:
     */
    private void rightRotate() {
        AVLNode newNode = new AVLNode(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }
    /**
     * 找到要删除的结点
     *
     * @Param: value 希望删除结点的值
     * @Return: AVLNode 如果找到了就返回结点，否则返回null
     */
    public AVLNode search(int value) {
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
    public AVLNode searchParent(int value) {
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
    public void add(AVLNode node) {
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
//        当添加完一个结点后，如果（右子树的高度 - 左子树的高度 ）> 1，左旋转
        if (rightHeight() - leftHeight() > 1) {
//            如果它的右子树的左子树的高度大于它的右子树的高度
//            先对右子树进行右旋转，再对当前结点进行左旋转
            if (right!=null && right.leftHeight() > right.rightHeight()){
                right.rightRotate();
                leftRotate();
            }else {
                leftRotate();
            }
//            加一个判断一个，所以只要这个条件满足了，以下的条件不再判断！！！
            return;
        }
//        当添加完一个结点后，如果（左子树的高度 - 右子树的高度 ） > 1 ,右旋转
        if (leftHeight() - rightHeight() > 1) {
//            如果它的左子树的右子树的高度大于它的左子树的高度
            if (left!=null && left.rightHeight() > left.leftHeight()){
//                先对当前结点的左节点（左子树）进行左旋转
                left.leftRotate();
//                再对当前结点进行右旋转
                rightRotate();
            }else {
//               直接进行右旋转即可
                rightRotate();
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
}