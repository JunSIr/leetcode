package MyDataTest_10;
/*
*   并查集
* 有若干个样本a、b、c、d…类型假设是V
* 在并查集中一开始认为每个样本都在单独的集合里
* 用户可以在任何时候调用如下两个方法：
       boolean isSameSet(V x, V y) : 查询样本x和样本y是否属于一个集合
       void union(V x, V y) : 把x和y各自所在集合的所有样本合并成一个集合
       isSameSet和union方法的代价越低越好 O(1)

* */

/*
*  并查集结构
* 1）每个节点都有一条往上指的指针
* 2）节点a往上找到的头节点，叫做a所在集合的代表节点
* 3）查询x和y是否属于同一个集合，就是看看找到的代表节点是不是一个
* 4）把x和y各自所在集合的所有点合并成一个集合，只需要小集合的代表点挂在大集合的代表点的下方即可

* */

/*
*  并查集如何优化的
* 1）节点往上找代表点的过程，把沿途的链变成扁平的
* 2）小集合挂在大集合的下面
* 3）如果方法调用很频繁，那么单次调用的代价为O(1)，两个方法都如此

* */

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionSet<V> {
     /*
     * 代表集合的'点'
     * */
    public static class Node<V>{
        V value;
        public Node(V v) {
            value = v;
        }
    }


    /*
    * 属性
    * */
    HashMap<Node<V>, Node<V>> parents ;  // 对某个点，你爹是谁
    HashMap<V,Node<V>> froms  ;  //对某个V 谁代表你 或者 你属于哪个集合
    HashMap<Node<V>,Integer> sizeMap  ; //你这个集合 多大

    /*
    * 构造器
    * */
    public UnionSet(List<V> values) {
        /*
        * 初始化
        * */
        for (V value : values) {
            Node<V> node = new Node<>(value);
            froms.put(value,node) ; //根据定义，初始，每个值由代表自己的节点代表
            parents.put(node,node) ; //都以自己为爹
            sizeMap.put(node,1) ;
        }
    }

    /*
    * 是否同一个集合
    */

    public boolean isSameSet(V a,V b){
        //判空
        if (!froms.containsKey(a) || !froms.containsKey(b))
            return false ;
        // 爹同即同集合
        return findFather(froms.get(a)) == findFather(froms.get(b)) ;
    }

    /*
    * 你爹是谁
    * */
    public Node<V> findFather(Node<V> cur){

        Stack<Node<V>> stack = new Stack<>() ; //随意容器都行
        /*
        * 愉快的找爹过程，只要我不是我自己的爹..
        * */
        while (cur != parents.get(cur)){
            stack.push(cur) ;
            cur = parents.get(cur) ;
        }
        /*
        * 扁平化 降维打击
        * */
        while (!stack.isEmpty()){
            parents.put(stack.pop(),cur) ; //我cur是你们所有人的爹
        }

        return cur ;
    }


    /*
    * 两个值所属集合合并
    * */
    public void union(V a, V b){
        //判空
        if (!froms.containsKey(a) || !froms.containsKey(b))
            return;

        Node<V> aHead = findFather(froms.get(a));
        Node<V> bHead = findFather(froms.get(b));

        //不同才好合并
        if (aHead!=bHead){
            //得到两个头各有多大
            int aSize = sizeMap.get(aHead);
            int bSize = sizeMap.get(bHead);

            //小挂大
            if (aSize >= bSize){
                parents.put(bHead,aHead) ;
                sizeMap.put(aHead, aSize + bSize);
                sizeMap.remove(bHead) ;
            }else {
                parents.put(aHead,bHead) ;
                sizeMap.put(bHead,aSize + bSize);
                sizeMap.remove(aHead) ;
            }

        }


    }
}
