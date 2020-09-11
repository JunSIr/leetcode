package Camp_01;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
* 单调栈
* 应用  找  左右最近最小（大）的位置
*          本题找 左右 最近最小
* */
public class MonotonousStack {

    //可能含有重复值的处理逻辑
    public static int[][] getNearLess(int[] arr) {
        //      [ [左最小，右最小] ... ]
        int[][] res = new int[arr.length][2];
        //单调栈  栈底->栈顶  小->大
        //放的是位置 同样值的压在一个位置
        Stack<List<Integer>> stack = new Stack<>();
        //左->右 进栈
        for (int i = 0; i < arr.length; i++) {
            // 不破坏 小->大的栈结构前提下，进栈
            //即 arr[i] > stack.peak（栈顶） 压栈
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> popIs = stack.pop(); //违背小->大，整个list弹出
                //如果栈压无元素，则 -1位置代表没有，否则压的list的最末尾位置 即 最晚加入的位置
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                //设置结果
                for (Integer popi : popIs) {
                    res[popi][0] = leftLessIndex; //左最小
                    res[popi][1] = i; //右最小
                }
            }
            //不同位置值相当，加入list
            if (!stack.isEmpty() && arr[i] == arr[stack.peek().get(0)]) {
                stack.peek().add(i);
            } else {
                /*
                 * 栈空或满足小->大的业务逻辑
                 * */
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }

        //栈中残留元素结算
        while (!stack.isEmpty()) {
            List<Integer> popIs = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer popi : popIs) {
                res[popi][0] = leftLessIndex;
                res[popi][1] = -1; //都是-1 ，因为没人逼它弹
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3} ;
        int[][] nearLess = getNearLess(arr);
        System.out.println(nearLess[0][0]);
    }
}
