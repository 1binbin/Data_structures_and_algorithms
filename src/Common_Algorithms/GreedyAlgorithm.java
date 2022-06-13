package Common_Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 贪心算法-最大覆盖集合
 *
 * @Author hongxiaobin
 * @Time 2022/5/29-17:22
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
//        创建广播电台
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

//        存放所有地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");
//        存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();
//        定义一个临时的集合，在遍历过程中存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
//        就是还有几个可以覆盖的
        HashSet<String> tempSet = new HashSet<>();
//        定义一个变量，保存在一次遍历过程中能够覆盖最多未覆盖的地区对应的电台的key
//        如果变量不为空，则加入到selects中
        String maxKey;
//        如果allAreas不为0，则表示还没有覆盖的地区
        while (allAreas.size() != 0) {
//            没进行一次while都要把maxKey置空
            maxKey = null;
            for (String key : broadcasts.keySet()) {
//                把tempSet清空
                tempSet.clear();
//                当前的key能覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
//                求和tempSet和allAreas集合的交集，交集覆盖tempSet
                tempSet.retainAll(allAreas);
//                如果当前的集合包含了未覆盖地区的数量，比maxKey指向的集合未覆盖的地区还多，就需要重置maxKey
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                selects.add(maxKey);
//                清除所有地区中已覆盖地区
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

//        输出结果
        System.out.println(selects);
    }
}
