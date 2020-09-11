package Camp_03;

import com_4.class03.Code02_WorldBreak;

import java.util.HashSet;

/**
* 假设所有字符都是小写字母 长字符串是str
 * arr是去重的单词表 每个单词都不是空字符串且可以使用任意次
 * 使用arr中的单词有多少种拼接str的方式 返回方法数
* */
public class WorldBreak {

    /*
    * 暴力解 O(N^3)
    * arr中的元素拼成str
    * */
    public static int ways(String str, String[] arr){
        HashSet<String> set = new HashSet<>(); //arr中的字符放在set里去重
        for (String candidate : arr){
            set.add(candidate);
        }

        return process(str,0,set);
    }
    //含义 str[i....] 能够被set中的贴纸分解的话，返回分解的方法数
    public static int process(String str, int i, HashSet<String> set){
        if (i == str.length()) {
            return 1;
        }

        int ways = 0;
        for (int end = i; end < str.length(); end++) {
            String pre = str.substring(i, end + 1);// + 1是因为subString是左闭右开 这之前提到过
            if (set.contains(pre)){ //可以用arr中的字符匹配成pre  O(N)
                 ways += process(str, end + 1, set);
            }
        }
        return ways;
        // 枚举所有前缀
    }

    /*
    * 前缀树优化  arr建立前缀树 代替set O（N） -> O（1） 优化成O（N^2）
    * */
    public static class Node {
        public boolean end;
        public Node[] nexts;

        public Node() {
            end = false;
            nexts = new Node[26];
        }
    }
    public static int ways1(String str, String[] arr) {

        if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
            return 0;
        }
        // 以下建立前缀树
        Node root = new Node();
        for (String s : arr){
            char[] chars = s.toCharArray();
            Node node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null){
                    node.nexts[index] = new Node();
                }
                node = node.nexts[index];
            }
            node.end = true;
        }
       return g(str.toCharArray(),root,0);
    }
    // str[i...] 方法数 O(N^2)
    public static int g(char[] str, Node root, int i) {
        // base case
        if (i == str.length){
            return 1; //表示啥也不用 一种方法数
        }

        int ways = 0;
        Node cur = root;
        for (int end = i; end < str.length ; end++) {
            int path = str[end] - 'a';
            if (cur.nexts[path] == null){ //没有相关路径 此end开头的前缀不用枚举了
                break;
            }
            //存在
            cur = cur.nexts[path];
            if (cur.end){
                ways += g(str,root,end + 1);
            }
        }
        return ways;
    }


    // 以下的逻辑都是为了测试
    public static class RandomSample {
        public String str;
        public String[] arr;

        public RandomSample(String s, String[] a) {
            str = s;
            arr = a;
        }
    }

    // 随机样本产生器
    public static RandomSample generateRandomSample(char[] candidates, int num, int len, int joint) {
        String[] seeds = randomSeeds(candidates, num, len);
        HashSet<String> set = new HashSet<>();
        for (String str : seeds) {
            set.add(str);
        }
        String[] arr = new String[set.size()];
        int index = 0;
        for (String str : set) {
            arr[index++] = str;
        }
        StringBuilder all = new StringBuilder();
        for (int i = 0; i < joint; i++) {
            all.append(arr[(int) (Math.random() * arr.length)]);
        }
        return new RandomSample(all.toString(), arr);
    }

    public static String[] randomSeeds(char[] candidates, int num, int len) {
        String[] arr = new String[(int) (Math.random() * num) + 1];
        for (int i = 0; i < arr.length; i++) {
            char[] str = new char[(int) (Math.random() * len) + 1];
            for (int j = 0; j < str.length; j++) {
                str[j] = candidates[(int) (Math.random() * candidates.length)];
            }
            arr[i] = String.valueOf(str);
        }
        return arr;
    }

    public static void main(String[] args) {
        char[] candidates = { 'a', 'b' };
        int num = 20;
        int len = 4;
        int joint = 5;
        int testTimes = 30000;
        boolean testResult = true;
        for (int i = 0; i < testTimes; i++) {
            RandomSample sample = generateRandomSample(candidates, num, len, joint);
            int ans1 = ways1(sample.str, sample.arr);
            int ans2 = ways(sample.str, sample.arr);

            if (ans1 != ans2 ) {
                testResult = false;
            }
        }
        System.out.println(testTimes + "次随机测试是否通过：" + testResult);
    }
}
