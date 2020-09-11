package Camp_02;
/*
* 之字打印矩阵问题
* 核心方法: 宏观调度
* */

/*
* 【题目】 给定一个矩阵matrix，按照“之”字形的方式打印这个矩阵，例如：
1 2 3 4
5 6 7 8
9 10 11 12
“之”字形打印的结果为：1，2，5，9，6，3，4，7，10，11，8，12
* */
public class ZigZagPrintMatrix {

    public static void printMatrixZigZag(int[][] matrix){

        int AR = 0 ; //A指针row
        int AC = 0 ; //A指针col
        int BR = 0 ;
        int BC = 0 ;

        int endR = matrix.length - 1 ; //3行 第一行是第0行 最后一行是第2行
        int endC = matrix[0].length  - 1 ;

        boolean fromUp  = false ; //是否从头打印 true从上到下，false从下到上
        while (AR != endR + 1){ //行不越界
            printLevel(matrix,AR,AC,BR,BC,fromUp);

            //AB宏观调度,不懂就画图
            AR = AC == endC ? AR + 1 : AR ;
            AC = AC == endC ? AC : AC + 1 ;
            //以下顺序不能乱
            BC = BR == endR ? BC + 1 : BC ;
            BR = BR == endR ? BR : BR + 1 ;


            fromUp = !fromUp ;
        }
    }

    /*
    * 打印一条斜线，根据fromUp不同 实现从上到下或从下到上
    * */
    public static void printLevel(int[][] matrix ,int AR,int AC,int BR,int BC,boolean fromUp){

        if (fromUp){
            while (AR != BR + 1){
                System.out.print(matrix[AR++][AC--] + " ");
            }
        }else {
            while (BR != AR - 1){
                System.out.print(matrix[BR--][BC++] + " ");
            }
        }

    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        printMatrixZigZag(matrix);
    }
}
