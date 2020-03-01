package src.com.codeman.greedy;

import java.util.*;

/**
 * 贪心算法：通过比较，尽量得出解
 */
public class GreedyAlgorithm {
    private static Map<String, HashSet<String>> allChooses = new HashMap<>();
    private static List<String> bingJi = new ArrayList<>();
    private static Set<String> result = new HashSet<>();

    public static void main(String[] args) {
        init();
        setBingJi();
        getResult();
        System.out.println(result);
    }

    /**
     * 比较返回结果
     */
    private static void getResult() {
        // 思路：一个一个比较，看看谁能贡献最多的未有元素
        // 最大值指针
        String maxKey = null;
        int maxSize = 0;
        List<String> tempList = new ArrayList<>();
        // 包含几个就减几个
        while (bingJi.size() >  0) {
            maxKey = null;
            maxSize = 0;
            for (String s : allChooses.keySet()) {
                tempList.clear();
                tempList.addAll(allChooses.get(s));
                tempList.retainAll(bingJi);
                int size = tempList.size();
                if (maxKey == null || size > maxSize) {
                    maxKey = s;
                    maxSize = size;
                }
            }
            if (maxKey != null) {
                bingJi.removeAll(new ArrayList<>(allChooses.get(maxKey)));
                result.add(maxKey);
            }
        }
    }

    private static void setBingJi() {
        for (String s : allChooses.keySet()) {
            for (String s1 : allChooses.get(s)) {
                if (!bingJi.contains(s1)) {
                    bingJi.add(s1);
                }
            }
        }
    }

    /**
     * 准备数据
     */
    private static void init() {
        HashSet<String> k1 = new HashSet<>();
        k1.add("1");
        k1.add("2");
        k1.add("3");
        HashSet<String> k2 = new HashSet<>();
        k2.add("4");
        k2.add("2");
        k2.add("5");
        HashSet<String> k3 = new HashSet<>();
        k3.add("4");
        k3.add("5");
        HashSet<String> k4 = new HashSet<>();
        k4.add("6");
        k4.add("7");
        HashSet<String> k5 = new HashSet<>();
        k5.add("8");
        k5.add("1");
        allChooses.put("K1", k1);
        allChooses.put("K2", k2);
        allChooses.put("K3", k3);
        allChooses.put("K4", k4);
        allChooses.put("K5", k5);
    }

}
