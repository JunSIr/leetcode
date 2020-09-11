package Camp_03;


import java.util.HashMap;

/**
 * 给定一棵二叉树的头结点head 和一个数K
 * 路径的定义:
 * 可以从任何一个点开始 但是只能往下走 往下可以走到任何节点停止
 * 返回路径累加和为K的所有路径中 最长的路径最多有几个专业
 * */
public class LongestSumEqualK {
    // 算法原型是  之前的 数组累加和问题
    // 本题没用二叉树递归套路 是 DFS + 恢复现场

    public static class Node {
        int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static int ans = 0; //收集累加和为K的，最长路径有多少个节点

    public static int longest(Node head, int K){
        ans = 0;
        // key ： 前缀和
        // value : 该前缀和，最早出现在哪一层
        // sumMap：只维持，从头节点出发到当前节点，这条路径上的前缀和
        HashMap<Integer,Integer> sumMap = new HashMap<>();
        sumMap.put(0,-1);
        process(head, 0, 0, K, sumMap);
        return ans;
    }

    public static void process(Node X, int level, int preNum, int K, HashMap<Integer,Integer> sumMap){
        if (X == null){
            return;
        }

        // 从头节点出发，到当前X节点，总的前缀和是多少，allSum
        int allSum = preNum + X.value;
        if (sumMap.containsKey(allSum - K)){ //之前出现 allSum - K最早在哪一层
            // 更新最大路径
            ans = Math.max(ans,level - sumMap.get(allSum - K));
        }

        //如果这个累加和是第一次出现，放入set里
        if (!sumMap.containsKey(allSum)){
            sumMap.put(allSum,level);
        }
        // 当前节点作为左右孩子的共用父节点 sumMap共用
        process(X.left,level + 1, allSum, K, sumMap);
        process(X.right,level + 1, allSum, K, sumMap);

        //返回前恢复现场 清除本节点对sumMap的影响
        if (sumMap.get(allSum) == level){
            sumMap.remove(allSum);
        }
    }

    public static void main(String[] args) {
        //                   3
        //           -2             3
        //        1      4      5      -7
        //       3 5   2 -5  -5  -3   1   5
        int K = 0;
        Node head = new Node(3);
        head.left = new Node(-2);
        head.left.left = new Node(1);
        head.left.right = new Node(4);
        head.left.left.left = new Node(3);
        head.left.left.right = new Node(5);
        head.left.right.left = new Node(2);
        head.left.right.right = new Node(5);

        head.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(-7);
        head.right.left.left = new Node(-5);
        head.right.left.right = new Node(-3);
        head.right.right.left = new Node(1);
        head.right.right.right = new Node(5);

        System.out.println(longest(head, K));
    }
}
