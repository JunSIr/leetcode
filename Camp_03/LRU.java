package Camp_03;

import java.util.HashMap;

/*
* 实现LRU内存替换算法
* */
public class LRU {
    //代表记录key value的链表节点
    public static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> last;
        public Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    // 双向链表
    public static class NodeDoubleLinkedList<K , V> {
        private Node<K,V> head; //指向头的节点指针
        private Node<K,V> tail; //指向尾的节点指针

        public NodeDoubleLinkedList() {
            head = null;
            tail = null;
        }
        //添加节点到双向链表
        public void addNode(Node<K,V> newNode) {
            if (newNode == null){
                return;
            }
            if (head == null){ //if本双向链表是空的
                head = newNode;
                tail = newNode;
            }else { //链表不为空，加到尾巴上
                tail.next = newNode;
                newNode.last = tail;
                tail = newNode;
            }
        }
        //将某个节点移动到尾巴上
        public void moveNodeToTail(Node<K,V> node) {
            //已经是尾巴了，不管
            if (this.tail == node){
                return;
            }
            if (this.head == node){ //如果是头部，要特殊处理一下
                //换头
                this.head = node.next;
                this.head.last = null;
            }else {  // 当前node是中间的一个节点
                node.last.next = node.next;
                node.next.last = node.last;
            }
            //前面处理好要移动节点的前后关联，以下处理被移动节点本身关系
            node.last = this.tail;
            node.next = null;
            this.tail.next = node;
            this.tail = node; //换尾巴
        }

        //删除头节点并返回，含义为LRU的“替换”
        public Node<K,V> removeHead(){
            if (this.head == null){
                return null;
            }
            Node<K,V> res = this.head;
            if (this.head == this.tail){ //链表中只有一个节点
                this.head = null;
                this.tail = null;
            }else { //不只有一个节点 常规删除
                this.head = res.next; //头往后换
                res.next = null;
                this.head.last = null;
            }
            return res;
        }
        //
    }
    //正主LRU
    public static class MyCache<K,V>{
        private HashMap<K,Node<K,V>> keyNodeMap; //记录节点内存地址
        private NodeDoubleLinkedList<K,V> nodeList; //数据存放区
        private final int capacity;

        public MyCache(int capacity) {
            if (capacity < 1) {
                throw new RuntimeException("should be more than 0.");
            }
            this.capacity = capacity;
            keyNodeMap = new HashMap<>();
            nodeList = new NodeDoubleLinkedList<>();
        }

        //要求O(1)的get方法
        public V get(K key){
            if (keyNodeMap.containsKey(key)){ //存在 返回
                Node<K, V> res = keyNodeMap.get(key);
                nodeList.moveNodeToTail(res);//最近使用
                return res.value;
            }
            return null;
        }
        //要求O(1)的set方法
        public void set(K key,V value){
            if (keyNodeMap.containsKey(key)){ //已经存在 修改值
                Node<K, V> node = keyNodeMap.get(key);
                node.value = value;
                nodeList.moveNodeToTail(node);//最近使用
            }else { //新加的记录
                if (keyNodeMap.size() == capacity){ //容量已满 执行替换算法
                    removeMostUsedCathe();
                }
                Node<K, V> newNode = new Node<K,V>(key, value);
                keyNodeMap.put(key,newNode);
                nodeList.addNode(newNode);
            }
        }
        //内部自动调用的替换算法
        private void removeMostUsedCathe(){
            Node<K, V> node = nodeList.removeHead();
            keyNodeMap.remove(node.key);
        }
    }

    public static void main(String[] args) {
        MyCache<String, Integer> testCache = new MyCache<String, Integer>(3);
        testCache.set("A", 1);
        testCache.set("B", 2);
        testCache.set("C", 3);
        System.out.println(testCache.get("B"));
        System.out.println(testCache.get("A"));
        testCache.set("D", 4);
        System.out.println(testCache.get("D"));
        System.out.println(testCache.get("C"));
    }
}
