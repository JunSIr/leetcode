package MyDataTest_07;

import java.util.LinkedList;
import java.util.Queue;

/*
* 二叉树的序列化和反序列化
* 1）可以用先序或者中序或者后序或者按层遍历，来实现二叉树的序列化
* 2）用了什么方式序列化，就用什么样的方式反序列化
 * */
public class SerializeAndReconstructTree {

    /*    先序序列化（其他方式只需改变递归的顺序即可）
    *  head就是要序列化的树 、 ans就是序列化的结果
    * */
    public static Queue<String> preSerialize(Node head){
        Queue<String> ans = new LinkedList<>();
        pres(head,ans);
        return ans ;
    }
    public static void pres(Node head , Queue<String> ans){
        if (head==null){
            ans.add(null) ;
        }else {
            ans.add(String.valueOf(head.value));
            pres(head.left,ans);
            pres(head.right,ans);
        }
    }

    /*
    * 反序列化
    * 将序列化的队列，建成一棵二叉树，返回
    * 用什么方式序列化的就用什么方式反序列化
    * */
    public static Node buildByQueue(Queue<String> ans){
        if (ans==null || ans.size()<1) return null ;
        return build(ans) ;
    }
    public static Node build(Queue<String> ans){
        String value = ans.poll();
        //递归返回条件
        if (value==null) return null ;
        //构建节点
        Node node = new Node(Integer.parseInt(value)) ;
        node.left = build(ans);
        node.right = build(ans) ;
        return node ;
    }

    /*
    * 层序序列化
    * */
    public static Queue<String> levelSerialize(Node head){
        Queue<String> ans = new LinkedList<>();
        level(head,ans);
        return ans ;
    }
    public static Queue<String> level(Node head,Queue<String> ans){
        if (head==null) {
            ans.add(null) ;
        } else {

            ans.add(String.valueOf(head.value)) ;
            //层序大多需要队列
            Queue<Node> queue = new LinkedList<>() ;
            queue.add(head) ;
            while (!queue.isEmpty()){

                head = queue.poll();
                //如果左节点不为空，ans和队列都入队
                if (head.left!=null){
                    queue.add(head.left) ;
                    ans.add(String.valueOf(head.left.value)) ;
                }else {
                    ans.add(null) ;
                }
                //如果右节点不为空，ans和队列都入队
                if (head.right!=null){
                    queue.add(head.right) ;
                    ans.add(String.valueOf(head.right.value)) ;
                }else {
                    ans.add(null) ;
                }
            }
        }
        return ans ;
    }

    /*
    * 层序反序列化
    * */
    public static Node buildByLevelQueue(Queue<String> ans){
        if (ans==null||ans.size() < 1) return null ;

        //层序所需队列
        Queue<Node> queue = new LinkedList<>();

        Node head = generateNode(ans.poll());
        if (head != null){
            queue.add(head) ;
        }
        Node node = null ;
        while (!queue.isEmpty()){
            node = queue.poll();

            node.left = generateNode(ans.poll());
            node.right = generateNode(ans.poll()) ;

            if (node.left!=null){
                queue.add(node.left);
            }
            if (node.right!=null){
                queue.add(node.right) ;
            }
        }
        return head ;
    }
        //由值生成节点
    public static Node generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new Node(Integer.parseInt(val));
    }



    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        Queue<String> serialize = levelSerialize(head);
//        serialize.forEach(System.out::println);

        Node head1 = buildByLevelQueue(serialize);
        System.out.println(head1.value);
        System.out.println(head1.left.value);
        System.out.println(head1.right.value);
        System.out.println(head1.right.left.value);

    }
}
