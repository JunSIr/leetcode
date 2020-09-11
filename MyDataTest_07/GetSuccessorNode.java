package MyDataTest_07;
/*
*二叉树结构如下定义：
Class Node {
	V value;
	Node left;
	Node right;
	Node parent;
}
给你二叉树中的某个节点，返回该节点的后继节点
* */

/*
* 某叶子节点的后继节点是指的是 一个树节点在一颗二叉树中序遍历的序列 中的下一个 前驱节点同样
**/
public class GetSuccessorNode {
    /*带父节点指针的二叉树结构*/
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;
        public Node(int data) {
            this.value = data;
        }
    }

    /*
    * 以下方法阐述了 一个节点与其后继节点在结构上的关系，前驱反过来想就可以
    * */
    public static Node getSuccessorNode(Node node){
        if (node==null) return node ;

        //如果该节点有右树，优先返回右树最左节点
        if (node.right!=null){
            return findMostLeftNode(node.right)  ;
        }else {
            //无右子树 画个S型树就能看出来了
            Node parent = node.parent ;
            while (node.parent!=null && node == parent.right){
                node = parent;
                parent = node.parent ;
            }
            return parent ;
        }
    }
    /*
    * 返回右子树最左节点，如右子树无最左节点，返回自己
    * */
    public static Node findMostLeftNode(Node node){
        if (node==null) return null ;
        while (node.left!=null){
            node = node.left ;
        }
        return node ;
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));
    }
}
