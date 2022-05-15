public class BinaryTree<T> {
    public BinaryNode<T> root;  //根节点

    public BinaryTree() {
        this.root = null;
    }

    //已知先序和中序构造完整二叉树
    public BinaryTree(SeqList1<T> preList, SeqList1<T> inList) {
        this.root = buildBitrees(preList, 0, preList.size() - 1,
                inList, 0, inList.size() - 1);
    }

    //先根次序遍历以p结点为根的子树
    public void preOrder(BinaryNode<T> p) {
        if (p != null) {
//            System.out.println(p.data.toString() + " ");   //访问根节点
//            preOrder(p.left);                           //遍历左子树
//            preOrder(p.right);                          //遍历右子树
            p.preOrder();
        }
    }

    //中根次序遍历以p结点为根的子树
    public void inOrder(BinaryNode<T> p) {
        if (p != null) {
            inOrder(p.left);                           //遍历左子树
            System.out.println(p.data.toString() + " ");   //访问根节点
            inOrder(p.right);                          //遍历右子树
        }
    }

    //后根次序遍历以p结点为根的子树
    public void postOrder(BinaryNode<T> p) {
        if (p != null) {
            postOrder(p.left);                           //遍历左子树
            postOrder(p.right);                          //遍历右子树
            System.out.println(p.data.toString() + " ");   //访问根节点
        }
    }

    //已知先序和中序得到二叉树结点顺序
    public BinaryNode<T> buildBitrees(SeqList1<T> preList, int preSNumber, int preENumber,
                                      SeqList1<T> inList, int inSNumber, int inENumber) {
        if (preSNumber <= preENumber && inSNumber <= inENumber) {
            //1.在先序中确定二叉树的根结点(序列中第一个结点)
            T first = preList.get(preSNumber);
            //2.创建该二叉树根结点，设定其值域
            BinaryNode root = new BinaryNode(first);
            //3.在中序中由根结点划分左右子树结点序列(在根结点左边的为左子树中序遍历序列，右边的为右子树)
            int index = inList.search(first);
            int leftLength = index - inSNumber;
            int rightLength = inENumber - index;
            //4.划分先根序列中的左右子树结点
            //5.根据得到的左右子树结点序列，重复1~3步，构造该结点的左右子树
            //如先根：abdefgc；中根：dbfegac
            //左子树dbfeg，结点为b，长度为5
            //右子树c，结点为c，长度为1
            root.left = buildBitrees(preList, preSNumber + 1, preSNumber + leftLength,
                    inList, inSNumber, index - 1);
            root.right = buildBitrees(preList, preSNumber + leftLength + 1, preSNumber + leftLength + rightLength,
                    inList, index + 1, index  + rightLength);
            return root;
        } else {
            return null;
        }
    }
}
