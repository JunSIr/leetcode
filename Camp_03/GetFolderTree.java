package Camp_03;

import java.util.TreeMap;

/**
 * 给定一个字符串类型的数组，譬如
 * String[] arr = {b\st, d\, a\d\e, a\b\c}
 * 把这些路径中蕴含的目录打印出来，子目录直接列在父目录下面，并比父目录向右进两格，就像这样
 * a
 *  b
 *   c
 *  d
 *   e
 * b
 *  st
 * d
 * 同一级的需要按字母排序不能乱
 * */
public class GetFolderTree {
    //前缀树节点
    public static class Node{
        public String path ; //这个点通过哪个str
        public TreeMap<String,Node> nextMap ; //通过str这条路到达哪个点

        public Node(String path) {
            this.path = path;
            nextMap = new TreeMap<>();
        }
    }

    public static void print(String[] folderPaths){
        if (folderPaths == null || folderPaths.length == 0){
            return;
        }
        //创建前缀树
        Node head = generateFolderTree(folderPaths);
        printProcess(head,0);

    }

    //生成前缀树
    public static Node generateFolderTree(String[] folderPaths){
        Node head = new Node(""); //根目录

        for (String folder : folderPaths){
            String[] paths = folder.split("\\\\");//java 特性，用一个"\"做分割的意思
            Node cur = head;  //每个str每次开始都来到head位置往下遍历找
            for (int i = 0; i < paths.length; i++) {
                if (!cur.nextMap.containsKey(paths[i])){ //cur的next没有，创建出来
                    cur.nextMap.put(paths[i],new Node(paths[i])) ;
                }
                cur = cur.nextMap.get(paths[i]) ;
            }
        }
        return head ;
    }

    //打印前缀树 先序遍历
    public static void printProcess(Node head, int level){
        if (level != 0){
            System.out.println(get4nSpace(level) + head.path);
        }

        for (Node next : head.nextMap.values()){
            printProcess(next,level + 1);
        }
    }
    //获取空格
    public static String get4nSpace(int n) {
        String res = "";
        for (int i = 1; i < n; i++) {
            res += "    ";
        }
        return res;
    }

    public static void main(String[] args) {
        //    "a\b\c" '\'  a,b,c
        String test = "a\\b\\cd";

        //  "a\b\c"    "\"    a,b,c
        String[] arr = test.split("\\\\"); //    \\\\    \\   \
        for(String str : arr) {
            System.out.println(str);
        }
    }
}
