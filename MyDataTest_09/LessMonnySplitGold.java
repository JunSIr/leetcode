package MyDataTest_09;

/*
*一块金条切成两半，是需要花费和长度数值一样的铜板的。
比如长度为20的金条，不管怎么切，都要花费20个铜板。 一群人想整分整块金条，怎么分最省铜板?
例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。
如果先把长度60的金条分成10和50，花费60; 再把长度50的金条分成20和30，花费50;一共花费110铜板。
但如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20， 花费30;一共花费90铜板。
输入一个数组，返回分割的最小代价。
* */

/*
* 堆结构与排序是处理贪心题目的重要利器
* */

import java.util.PriorityQueue;

public class LessMonnySplitGold {

    /*
    * 本题本质是哈夫曼树
    * */
    public static int minSplit(int[] arr){
        //小根堆
        PriorityQueue<Integer>  minHeap = new PriorityQueue<>();

        for (int i = 0; i < arr.length; i++) {
            minHeap.add(arr[i]);
        }
        int noleaf = 0  ;
        int res = 0  ;
        /*
        * 弹出两个，合成加入 ...
        * */
        while (minHeap.size() != 1){
            noleaf = minHeap.poll() + minHeap.poll() ; //noleaf必不是堆的叶子节点
            res += noleaf ;
            minHeap.add(noleaf) ;
        }

        return res ;
    }

    public static void main(String[] args) {
        int[] arr = new int[3] ;
        arr[0] = 10 ;
        arr[1] = 20 ;
        arr[2] = 30 ;
        int i = minSplit(arr);
        System.out.println(i);
    }
}
