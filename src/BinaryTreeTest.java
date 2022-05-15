public class BinaryTreeTest {
    public static void main(String[] args) {
        SeqList1<String> preList = new SeqList1<>();
        SeqList1<String> inList = new SeqList1<>();
//        String preStr = "abdefgc";
//        String inStr = "dbfegac"
        String preStr = "abc";
        String inStr = "bac";
        for (int i = 0; i < preStr.length(); i++) {
            preList.insert(preStr.charAt(i) + "");
            inList.insert(inStr.charAt(i) + "");
        }
        BinaryTree<String> binaryTree = new BinaryTree<>(preList, inList);
        System.out.println("目前构造的二叉树先序序列为：");
        binaryTree.preOrder(binaryTree.root);
        System.out.println("目前构造的二叉树中序序列为：");
        binaryTree.inOrder(binaryTree.root);
        System.out.println("目前构造的二叉树后序序列为：");
        binaryTree.postOrder(binaryTree.root);
    }
}
