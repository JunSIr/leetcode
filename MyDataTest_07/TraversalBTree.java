package MyDataTest_07;

/* 二叉树的三种遍历
* 先序:任何子树的处理顺序都是，先头节点、再左子树、然后右子树
* 中序：任何子树的处理顺序都是，先左子树、再头节点、然后右子树
* 后序：任何子树的处理顺序都是，先左子树、再右子树、然后头节点

* 关于后面树形递归到树形dp你必须知道 :
任何二叉树的子树都有到它左子树溜一圈处理结果，
再到右子树处理结果，
再到子树头节点处理左右子树返回结果 的能力

* 关于递归序
* 1）理解递归序
* 2）先序、中序、后序都可以在递归序的基础上加工出来
* 3）第一次到达一个节点就打印就是先序、第二次打印即中序、第三次即后序

* 无论何种遍历，，时空复杂度均为 O(n) 与堆做区分 堆通常自上而下是O(nlogN)
*/

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*二叉树的数据结构*/
 class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int v) {
        value = v;
    }
}

public class TraversalBTree {
     //递归版前序遍历
    public static void before(Node head){
        if (head==null) return;
        System.out.println(head.value);
        before(head.left);
        before(head.right);
    }

    //递归版中序遍历
    public static void mid(Node head){
        if (head==null) return;
        mid(head.left);
        System.out.println(head.value);
        mid(head.right);
    }

    //递归版中序遍历
    public static void after(Node head){
        if (head==null) return;
        after(head.left);
        after(head.right);
        System.out.println(head.value);
    }

    /*
    * 非递归版强记就行了，还有什么好说的呢？
    * */

    //栈版前序遍历
    public static void sBefore(Node head){
        /*
        * 准备一个栈，头节点先入栈，进入流程 -->
        * 1.弹栈并打印
        * 2.有右，右入栈 ; 有左，左入栈
        * */
        if (head!=null) {
            Stack<Node> stack = new Stack<>() ;
            stack.push(head) ;
            while (!stack.isEmpty()){
                //弹栈并打印
                head = stack.pop() ;
                System.out.println(head.value);
                if (head.right!=null){
                    stack.push(head.right);
                }
                if (head.left!=null){
                    stack.push(head.left);
                }
            }
        }
    }

    //栈版中序遍历
    public static void sMid(Node head){
        /* 宏观上可以大概理解一下，下面的流程与最终的结果
        * 1.先把“左边界“全部节点依次入栈
        * 2.为空了，则弹栈并打印，来到右孩子处，重复1过程
        * */
        if (head!=null){
            Stack<Node> stack = new Stack<>() ;
            while (!stack.isEmpty() || head!=null){
                if (head!=null){
                    stack.push(head);
                    head = head.left  ;
                }else {
                    head = stack.pop();
                    System.out.println(head.value);
                    head = head.right ;
                }
            }
        }


    }

    //栈版后序遍历
    public static void sAfter(Node head){
        /*整棵树按先序入辅助栈 辅助栈弹出为后序
        * 1.头结点入栈，进入流程
        * 2.与前序相反，先左后右
        * */
        if (head!=null){
            Stack<Node> stack = new Stack<>() ;//正规栈
            Stack<Node> help = new Stack<>(); //辅助栈，用来逆序

            //这个过程保证整棵树按先序入栈
            stack.push(head);
            while (!stack.isEmpty()){
                head = stack.pop() ;  //stack.isEmpty x
                help.push(head) ;
                if (head.left!=null){
                    stack.push(head.left) ;
                }
                if (head.right!=null){
                    stack.push(head.right) ;
                }
            }

            while (!help.isEmpty()){
                System.out.println(help.pop().value);
            }

      }

    }


    /*
    * 队列实现层序遍历
    * 1）其实就是宽度优先遍历，用队列
    * 2）可以通过设置flag变量的方式，来发现某一层的结束（看下一题--返回二叉树的最大宽度）
    * */
    public static void level(Node head){
        if (head==null) return;

        Queue<Node> queue = new LinkedList<>() ;
        queue.add(head) ;
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.value);
            if (cur.left!=null){
                queue.add(cur.left);
            }
            if (cur.right!=null){
                queue.add(cur.right) ;
            }
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        sBefore(head);
        System.out.println("========");
        sMid(head);
        System.out.println("========");
        sAfter(head);
        System.out.println("========");
        level(head);
        System.out.println("========");
    }
}
