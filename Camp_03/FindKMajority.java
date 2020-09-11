package Camp_03;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 1.给定一个数组arr，如果有某个数出现次数超过数组长度的一半，打印这个数，如果没有 则不打印
 * 2.给定一个数组arr和整数K，arr长度为N，如果有某些数出现次数超过N/K，打印这些数，如果没有不打印
 *
 * */
public class FindKMajority {

    // 这是一个很骚 又不复杂的算法
    public static void printHalfMajor(int[] arr){
        int cand = 0;
        int Hp = 0;

        for (int i = 0; i < arr.length; i++) {
            if (Hp == 0){
                cand = arr[i];
                Hp = 1;
            }
            else if (arr[i] == cand){
                Hp++;
            }else {
                Hp--;
            }
        }

        if (Hp == 0){
            System.out.println("no such number");
        }

        Hp = 0; //复用Hp
        // 检查candidate的真实频率
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == cand){
                Hp++;
            }
        }

        if (Hp > arr.length / 2){
            System.out.println("the num is " + cand);
        }else {
            System.out.println("no such num");
        }
    }

    //第二题  K - 1个候选
    public static void printKMajor(int[] arr, int K){
        if (K < 2){
            System.out.println("invalid");
        }

        //准备K - 1 容量的map 记录它们的"HP"
        HashMap<Integer,Integer> cands = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (cands.containsKey(arr[i])){  //为候选 HP + 1
                cands.put(arr[i], cands.get(arr[i]) + 1);
            }else {
                if (cands.size() == K - 1){ //如果满了 当前数不要 所有候选HP - 1 表示删除四个不同数
                    allCandsMinusOne(cands);
                }else { //添加为候选人
                    cands.put(arr[i],1);
                }
            }
        }

        HashMap<Integer, Integer> reals = getReal(arr, cands);
        boolean hasPrint = false;
        for (Map.Entry<Integer,Integer> set : cands.entrySet()){
            Integer key = set.getKey();
            if (reals.get(key) > arr.length / K){
                System.out.println(key + "");
                hasPrint = true;
            }
        }
        System.out.println(hasPrint ? "" : "no such num");
    }



    // 所有候选HP - 1
    public static void allCandsMinusOne(HashMap<Integer,Integer> map){
        List<Integer> removeList = new LinkedList<>(); //最终要删除的候选 添加到此处
        for (Map.Entry<Integer,Integer> set : map.entrySet()){
            Integer key = set.getKey();
            Integer value = set.getValue();
            if (value == 1){
                removeList.add(key);
            }
            map.put(key,map.get(key) - 1);
        }

        for (Integer i : removeList){
            map.remove(i);
        }
    }
    // 获取  candidates中真正的词频
    public static HashMap<Integer,Integer> getReal(int[] arr, HashMap<Integer,Integer> cands){
        HashMap<Integer,Integer> reals = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int curNum = arr[i];
            if (cands.containsKey(curNum)){

                if (reals.containsKey(curNum)){
                    reals.put(curNum, reals.get(curNum) + 1);
                }else {
                    reals.put(curNum, 1);
                }
            }
        }
        return reals;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 1, 1, 2, 1 };
        printHalfMajor(arr);
        int K = 4;
        printKMajor(arr,K);
    }
}
