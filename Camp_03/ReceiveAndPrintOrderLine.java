package Camp_03;

import java.util.HashMap;

/**
 * 一种消息接收并打印的结构设计
 * 已知一个消息流会不断吐出整数1~N 但不一定按照顺序吐出。
 * 如果上次打印的数为i，那么当i + 1出现时
 * 请打印i + 1及其之后接收过并且连续的所有数
 * 直到1 ~ N全部接收并打印完
 * 请设计这种接收并打印的结构 初始默认i == 0
* */

public class ReceiveAndPrintOrderLine {

    //单链表节点
    public static class Node {
        public String info;
        public Node next;

        public Node(String str) {
            info = str;
        }
    }
    //自定义消息接收结构
    public static class MessageBox{
        private HashMap<Integer,Node> headMap; //头表
        private HashMap<Integer,Node> tailMap; //尾表
        private int waitPoint; //等待哪个序号的节点

        public MessageBox() {
            headMap = new HashMap<>();
            tailMap = new HashMap<>();
            waitPoint = 1;
        }
        //接收方法
        public void receive(int num,String info){
            if (num < 1){
                return;
            }
            //num~num这个连续区间的头和尾
            Node cur = new Node(info);
            headMap.put(num,cur);
            tailMap.put(num,cur);

            // 查询有没有某个连续区间以num-1结尾
            if (tailMap.containsKey(num - 1)){
                tailMap.get(num - 1).next = cur; //串在一起
                tailMap.remove(num - 1); //不再已num - 1结尾 以num结尾
                headMap.remove(num) ; //不再以num的节点开头
            }

            //查询有无已num + 1开头的
            if (headMap.containsKey(num + 1)){
                cur.next = headMap.get(num + 1);
                tailMap.remove(num); //不再以cur节点结尾
                headMap.remove(num + 1); //不再以num + 1节点开头

            }
            //如果等到关键节点，执行内部print()
            if (num == waitPoint){
                print();
            }
        }
        //等到关键节点，执行打印（UDP接收等待、播放）
        private void print(){
            Node node = headMap.get(waitPoint);
            headMap.remove(waitPoint);
            //打印这个串
            while (node != null){
                System.out.print(node.info + "  ");
                node = node.next;
                waitPoint++;  //最终会来到后续正确的关键位置
            }
            tailMap.remove(waitPoint - 1); //细节 清理尾巴
            System.out.println();
        }

    }

    public static void main(String[] args) {
        // MessageBox only receive 1~N
        MessageBox box = new MessageBox();

        box.receive(2,"B"); // - 2"
        box.receive(1,"A"); // 1 2 -> print, trigger is 1

        box.receive(4,"D"); // - 4
        box.receive(5,"E"); // - 4 5
        box.receive(7,"G"); // - 4 5 - 7
        box.receive(8,"H"); // - 4 5 - 7 8
        box.receive(6,"F"); // - 4 5 6 7 8
        box.receive(3,"C"); // 3 4 5 6 7 8 -> print, trigger is 3

        box.receive(9,"I"); // 9 -> print, trigger is 9

        box.receive(10,"J"); // 10 -> print, trigger is 10

        box.receive(12,"L"); // - 12
        box.receive(13,"M"); // - 12 13
        box.receive(11,"K"); // 11 12 13 -> print, trigger is 11
    }
}
