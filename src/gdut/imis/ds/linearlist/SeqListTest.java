package gdut.imis.ds.linearlist;

/**
 * @Author hongxiaobin
 * @Time 2022/3/18-9:57
 * @Description
 */
public class SeqListTest {
    public static void main(String[] args) {
        SeqList<Integer> seqList = new SeqList<>();
        for (int i = 0; i < 10; i++) {
            seqList.insert(i,(int)(Math.random()*100+1));
        }
        System.out.println(seqList);
        seqList.partition();
        System.out.println(seqList);
    }
}
