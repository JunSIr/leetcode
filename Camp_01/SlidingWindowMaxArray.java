package Camp_01;

import java.util.LinkedList;

/*
*  滑动窗口最大值
*  假设一个固定W的窗口，依次划过arr
*  返回每一次划出状况的最大值
*  例如 arr = [4,3,5,4,3,3,6,7] w = 3
*  返回 [5,5,5,4,6,7]
* */
public class SlidingWindowMaxArray {

    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        // 其中放的是位置，头代表 （大->小）尾
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        // L...R
        //     i
        for (int R = 0; R < arr.length; R++) { // 当前让 i -> [i] 进窗口 ， i 就是 r
            // R -> 值  可以放在比他大的数后，或者空
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                qmax.pollLast();
            }
            qmax.addLast(R);
            // 数进来了
            // 如果窗口没有形成W的长度之前，不弹出数字的
            if (qmax.peekFirst() == R - w) {
                qmax.pollFirst();
            }
            // 以上窗口更新做完了
            if (R >= w -1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {4,3,5,4,3,3,6,7}  ;
        int[] maxWindow = getMaxWindow(arr, 3);
        for (int i = 0; i < maxWindow.length; i++) {
            System.out.println(maxWindow[i]);
        }
    }
}
