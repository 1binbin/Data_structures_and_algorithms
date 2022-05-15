package Sixth_chapter;

import java.io.*;
import java.util.*;

/**
 * 实现赫夫曼编码，数据压缩过程
 * 左边为0右边为1
 * 包括字符串和文件的压缩和解压
 *
 * 文件压缩的注意事项：
 * 1.如果文件本身就是经过压缩处理的，那么使用赫夫曼编码再压缩的效率不会有明显的变化，比如视频，ppt，jpg等等文件
 * 2.赫夫曼编码是按直接来处理的，因此可以处理所有的文件（二进制文件、文本文件）
 * 3.如果一个文件中的内容，重复的数据不多，压缩效果也不会很明显
 *
 * 压缩总体思路
 * 1.将压缩对象转为字符数组，再根据字符数组构建一棵赫夫曼树，在生成赫夫曼编码表
 * 2.根据赫夫曼编码表生成对象对应的赫夫曼编码字符串（只有0,1）
 * 3.按照每8位1字节转为十进制数，保存在字节数组中，即为压缩后的形式，（再将字节数组写入即可）
 * 解压总体思路
 * 1.读取字节数组和赫夫曼编码表
 * 2.根据字节数组转为赫夫曼编码字符串，按照每8为1字节转为字符再去和赫夫曼编码表对应找到对应的字符，存入字符数组
 * 3.将字符数组转为对象即可（或直接将字符输入写入文件）
 *
 * @Author hongxiaobin
 * @Time 2022 /4/30-11:09
 */
public class HuffmanCode {
    //    赫夫曼编码表
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //    每一个结点的路径，左边为0右边为1
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
//        String content = "i like like like java do you like a java";
        /*String content = "hello world";
        byte[] contentBytes = content.getBytes();
        byte[] bytes = huffmanZip(contentBytes);
        System.out.println("压缩后");
        System.out.println(Arrays.toString(bytes));
        System.out.println("解压后");
        System.out.println(new String(decode(huffmanCodes, bytes)));*/

//        测试压缩文件
        String srcFile = "E:\\Java project\\Data_structures_and_algorithms\\src\\Sixth_chapter\\1.png";
        String dstFile = "E:\\Java project\\Data_structures_and_algorithms\\src\\Sixth_chapter\\dst.zip";
        String unzipdstFile = "E:\\Java project\\Data_structures_and_algorithms\\src\\Sixth_chapter\\11.png";
        zipFlie(srcFile, dstFile);
        System.out.println("压缩文件成功~");
        unZipFile(dstFile,unzipdstFile);
        System.out.println("解压文件成功~");
    }


    /*
    完成对文件的压缩
     */

    /**
     * 文件压缩
     *
     * @Param: String srcFile 需要压缩的文件的全路径 ; String dstFile 完成压缩后的文件需要放在哪个目录下
     * @Return: void
     */
    public static void zipFlie(String srcFile, String dstFile) {
//        创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
//        创建输入流，读取文件
        FileInputStream is = null;
        try {
            is = new FileInputStream(srcFile);
//            创建一个与源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
//            读取文件
            is.read(b);
//            对源文件进行压缩，并且拿到了赫夫曼编码表
            byte[] huffmanBytes = huffmanZip(b);
//            创建文件的输出流，存放压缩文件
            os = new FileOutputStream(dstFile);
//            创建一个和文件输出流相关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
//            这里以对象流的方式写入赫夫曼编码是为了以后解压时使用，解压时可以按照对象流写入的顺序依次读取，区分出赫夫曼编码表
//            写入赫夫曼编码字符数组
            oos.writeObject(huffmanBytes);
//            一定要把赫夫曼编码写入压缩文件，便于今后解压
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                assert is != null;
                is.close();
                assert oos != null;
                oos.close();
                os.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    /**
     * 文件解压，读取数据和赫夫曼编码表完成解压
     * @Param: String zipFile 准备解压的文件  ; String dstFile 文件解压后存放的位置
     * @Return: void
     */
    public static void unZipFile(String zipFile,String dstFile){
//        定义文件输入流
        InputStream is = null;
//        定义对象输入流
        ObjectInputStream ois = null;
//        定义文件输出流
        OutputStream os = null;
        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
//            按照写入对象流的顺序依次的读取对象流
//            读取byte数组（数据部分）
            byte[] huffmanBytes = (byte[]) ois.readObject();
//            读取赫夫曼编码表
            Map<Byte,String> codes = (Map<Byte, String>) ois.readObject();
//            解压
            byte[] bytes = decode(codes, huffmanBytes);
//            将获取的bytes写入到目标文件
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }finally {
            try {
                assert os != null;
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    /*
      完成对数据进行解压，还原原来的字符串
      思路
      1.将最后得到的赫夫曼编码字节数组转为赫夫曼编码对应的二进制字符串
      2.将二进制字符串去对照赫夫曼编码转为原始字符串
     */

    /**
     * 将最后得到的赫夫曼编码字节数组转为赫夫曼编码对应的二进制字符串
     *
     * @Param: boolean flag 标识是否需要补高位true表示需要补（不是最后一组的其他正数需要补）, byte b
     * @Return: b对应的二进制字符串（按照补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {
//        使用一个变量保存b，即将b转为int
        int temp = b;
//        如果temp是一个正数，还需要补高位0的问题，凑成 8 位数
//        | 按位或256 即一个数与 1 0000 0000 进行按位或
        if (flag) {
            temp |= 256;
        }
//        str返回的是temp对应的二进制的 补码
        String str = Integer.toBinaryString(temp);
//        如果压缩后生成的最后一位为负数，那么会多出24位1，也需要截取
        if (flag || temp < 0) {
//        只取最后的 8 为数字组成一个二进制
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * 完成赫夫曼编码解码过程
     *
     * @Param: Map<Byte, String> huffmanCodes 赫夫曼编码表 ; byte[] huffmanBytes 赫夫曼编码得到的字节数组
     * @Return: byte[] 原来的字符数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
//        1.先得到 huffmanBytes 对应的二进制字符串，形如1010100010111·····
        StringBuilder stringBuilder = new StringBuilder();
//        将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
//            如果是最后一个字节，无需补高位，flag传入false
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }
//        把字符串按照指定的赫夫曼编码表进行编码
//        把赫夫曼编码表进行调换，需要进行反向查询，即根据value查询key
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
//        创建一个集合存放byte
        List<Byte> list = new ArrayList<>();
//        逐位扫描匹配赫夫曼编码表
        for (int i = 0; i < stringBuilder.length(); ) {
//            计数器，计数每一组的字符个数
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
//                让i不变，移动count，直到匹配一个字符
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
//                    没有匹配到
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
//            让i移动到count的位置
            i += count;
        }
//        当for结束后，list中存放这所有的字符
//        把list中的数据放入到byte[]中并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }


    /**
     * 将压缩的方法封装起来，便于调用
     *
     * @Param: byte[] bytes 原始的字符串对应的字符数组
     * @Return: byte[] 返回赫夫曼编码处理后的字节数组，即压缩后的数组
     */
    private static byte[] huffmanZip(byte[] bytes) {
//        将每一个字符转为结点
        List<NodeCode> nodes = getNodes(bytes);
//        创建赫夫曼树
        NodeCode huffmanTreeRoot = createHuffmanTree(nodes);
//        对应的赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
//        根据生成的赫夫曼编码压缩得到压缩后的赫夫曼编码字节数组
        return zip(bytes, huffmanCodes);
    }

    /**
     * 讲字符数组转为集合
     *
     * @Param: byte[] bytes
     * @Return: List<NodeCode>
     */
    private static List<NodeCode> getNodes(byte[] bytes) {
//        1.创建一个ArrayList
        ArrayList<NodeCode> nodes = new ArrayList<>();
//        2.存储每一个byte出现的次数（使用map）
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
//                map中还没有字符数据
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
//        把每一个键值对转为Node对象，并加入到集合中
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new NodeCode(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    /**
     * 创建一棵赫夫曼树
     *
     * @Param: List<NodeCode> nodes
     * @Return: NodeCode
     */
    private static NodeCode createHuffmanTree(List<NodeCode> nodes) {
        while (nodes.size() > 1) {
//            从小到大排序
            Collections.sort(nodes);
            NodeCode leftNode = nodes.get(0);
            NodeCode rightNode = nodes.get(1);
//            新的二叉树只有权值没有数据
            NodeCode parent = new NodeCode(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
//            加入新的二叉树
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * 前序遍历
     *
     * @Param: NodeCode root
     * @Return: void
     */
    private static void preOrder(NodeCode root) {
        if (root != null) {
            root.preOrder();
        } else {
            throw new RuntimeException("赫夫曼树为空，不可遍历！");
        }
    }


    /**
     * 生成赫夫曼树对应的赫夫曼编码
     * 1.将赫夫曼编码存在Map中
     * 2.在生成的赫夫曼编码表示，需要取拼接路径，定义一个StringBuilder存储某个叶子结点的路径
     *
     * @Param: NodeCode node 传入结点,String code 路径，左子节点为0，右子节点为1 ,StringBuilder stringBuilder 用于拼接路径
     * @Return: void
     */
    private static void getCodes(NodeCode node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
//        将传入的code加入到stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {
//            判断node是否为叶子结点
            if (node.data == null) {
//                非叶子节点
//                递归处理
//                向左
                getCodes(node.left, "0", stringBuilder2);
//                向右
                getCodes(node.right, "1", stringBuilder2);
            } else {
//                叶子结点
//                表示找到了某个叶子结点，表示到了树的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    /**
     * 为了调用方便，重载getCodes
     *
     * @Param:
     * @Return:
     */
    private static Map<Byte, String> getCodes(NodeCode root) {
        if (root == null) {
            throw new RuntimeException("树为空！");
        } else {
            getCodes(root.left, "0", stringBuilder);
            getCodes(root.right, "1", stringBuilder);
            return huffmanCodes;
        }
    }

    /**
     * 将字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码，压缩后的byte[]
     *
     * @Param: byte[] bytes 原始的字符串对应的byte[] ,Map<Byte,String> huffmanCodes 生成的赫夫曼编码
     * @Return: byte[] 赫夫曼编码字符串对应的byte[]数组，即8位对应一个byte，放入到数组中  如为 10101000 （补码）转为原码为 11011000 （补码-1再取反）转为十进制为-88，故byte[0] == -88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
//        1.利用huffmanCodes将bytes转为赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
//        2.将生成的字符串转为字符数组
//        2.1统计返回的赫夫曼编码字符串的长度   或用int len = (stringBuilder.length() + 7 ) / 8
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
//        2.2创建储存压缩后的byte数组
        byte[] huffmanCodeByte = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
//            考虑最后一组可能不达到8位数
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
//            2.2.1将strByte转为一个byte ，放入到huffmanCodeByte中
            huffmanCodeByte[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeByte;
    }

}

/**
 * 创建结点，包含数据域和权值
 */
class NodeCode implements Comparable<NodeCode> {
    //    存放数组本身，比如'a'==97
    Byte data;
    //    权值，表示字符出现的次数
    int weight;
    //    左子节点
    NodeCode left;
    //    右子节点
    NodeCode right;

    public NodeCode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(NodeCode o) {
//        从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "NodeCode [" + "data=" + data + ", weight=" + weight + ']';
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}