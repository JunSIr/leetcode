package Camp_03;
import java.util.HashMap;


/**
 * 请实现如下结构
 * TopRecode{
 *     public TopRecode(int K) 构造时事先指定好K的大小，构造后酒固定不变了
 *     public void add(String str) 向该结构添加一字符串 可以重复加入
 *     public List<String> top() 返回之前加入的字符串钟，词频最大的K个 }
 *  要求 add() O(logK) top() O(K)
 * }
 */
public class TopKTimesRealTime {
    //入堆结构
    public static class Node {
        public String str;
        public int times;

        public Node(String s, int t) {
            str = s;
            times = t;
        }
    }

    //题目要求的结构
    public static class TopKRecord{
        //手动改写的堆结构
        private Node[] heap ;
        private int heapSize ;
        //词频表
        private HashMap<String,Node> strNodeMap ;
        //堆钟位置表
        private HashMap<Node,Integer> nodeIndexMap ;

        public TopKRecord(int K) {
            heap = new Node[K];
            heapSize = 0 ;
            strNodeMap = new HashMap<>() ;
            nodeIndexMap = new HashMap<>();
        }

        private void heapInsert(int index) {
            while (index != 0) {
                int parent = (index - 1) / 2;
                if (heap[index].times < heap[parent].times) {
                    swap(parent, index);
                    index = parent;
                } else {
                    break;
                }
            }
        }

        private void heapify(int index, int heapSize) {
            int l = index * 2 + 1;
            int r = index * 2 + 2;
            int smallest = index;
            while (l < heapSize) {
                if (heap[l].times < heap[index].times) {
                    smallest = l;
                }
                if (r < heapSize && heap[r].times < heap[smallest].times) {
                    smallest = r;
                }
                if (smallest != index) {
                    swap(smallest, index);
                } else {
                    break;
                }
                index = smallest;
                l = index * 2 + 1;
                r = index * 2 + 2;
            }
        }

        private void swap(int index1, int index2) {
            nodeIndexMap.put(heap[index1], index2);
            nodeIndexMap.put(heap[index2], index1);
            Node tmp = heap[index1];
            heap[index1] = heap[index2];
            heap[index2] = tmp;
        }

        public void add(String str) {

            Node curNode = null ;
            int preIndex = -1 ; //该str的Node之前在堆中位置

            /*
            *  更新词频表  不涉及堆操作
            * */
            if (!strNodeMap.containsKey(str)) { //之前没加过 建好词频节点
                curNode = new Node(str,1) ;
                strNodeMap.put(str,curNode) ;
                nodeIndexMap.put(curNode,-1);
            }else { //之前加过 更新词频 拿到堆中index
                curNode = strNodeMap.get(str) ;
                curNode.times ++  ;  //更新词频
                //如果之前加过 且在堆中 pre会拿到正确结果 如加过但未在堆中，pre还是-1
                preIndex = nodeIndexMap.get(curNode) ;
            }

            /*
            * 拿着上面的index 更新堆结构 不管堆最终有无变化
            * */
            if (preIndex == -1) { //之前不在堆上 尝试加入堆

                if (heapSize == heap.length) { //堆满，比较再加

                    if (heap[0].times < curNode.times) { //比得过，入堆，比不过拉倒
                        nodeIndexMap.put(heap[0],-1) ;
                        nodeIndexMap.put(curNode,0);
                        heap[0] = curNode ;
                        heapify(0,heapSize); //向下调整
                    }

                }else{                          //堆没满，直接加
                    nodeIndexMap.put(curNode,heapSize) ;
                    heap[heapSize] = curNode;
                    heapInsert(heapSize++);         //向上调整
                }


            }else { //之前在堆中 直接更新堆结构
                heapify(preIndex,heapSize);
            }
        }

        public void printTopK() {
            System.out.println("TOP: ");
            for (int i = 0; i != heap.length; i++) {
                if (heap[i] == null) {
                    break;
                }
                System.out.print("Str: " + heap[i].str);
                System.out.println(" Times: " + heap[i].times);
            }
        }

        }

    //for test
    public static String[] generateRandomArray(int len, int max) {
        String[] res = new String[len];
        for (int i = 0; i != len; i++) {
            res[i] = String.valueOf((int) (Math.random() * (max + 1)));
        }
        return res;
    }

    //for test
    public static void printArray(String[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TopKRecord record = new TopKRecord(2);
        record.add("zuo");
        record.printTopK();
        record.add("cheng");
        record.add("cheng");
        record.printTopK();
        record.add("Yun");
        record.add("Yun");
        record.printTopK();

    }
    }



