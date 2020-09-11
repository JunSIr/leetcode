package Camp_02;
/*
* 转圈打印矩阵
* 给定一个整型矩阵matrix，请按照转圈的方式打印它。
例如：
1 2 3 4
5 6 7 8
9 10 11 12
13 14 15 16
打印结果为：1，2，3，4，8，12，16，15，14，13，9，5，6，7，11，
10
* */
public class prIntMatrixSpiralOrder {

    public static void spiralOrderPrint(int[][] matrix){
        //准备两个点  左上角点 右下角点
        int upR = 0 ;
        int upC = 0 ;
        int lowR = matrix.length - 1 ;
        int lowC = matrix[0].length - 1 ;

        while (upR <= lowR && upC <= lowC){
            printEdge(matrix,upR++,upC++,lowR--,lowC--);
        }

    }

    public static void printEdge(int[][] matrix,int upR,int upC,int lowR,int lowC){
        //一横线 的特殊情况
        if (upR == lowR){
            for (int i = upC; i <= lowC; i++) {
                System.out.print(matrix[upR][i] + "");
            }
        }
        //一竖线 的特殊情况
        if (upC == lowC){
            for (int i = upR; i <= lowR ; i++) {
                System.out.print(matrix[i][upC] + "");
            }
        }

        int curR = upR ;
        int curC = upC ;

        while (curC != lowC){
            System.out.print(matrix[upR][curC] + " ");
            curC ++ ;
        }
        while (curR != lowR){
            System.out.print(matrix[curR][lowC] + " ");
            curR ++ ;
        }
        while (curC != upC){
            System.out.print(matrix[lowR][curC] + " ");
            curC -- ;
        }

        while (curR != upR){
            System.out.print(matrix[curR][upC] + " ");
            curR -- ;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        spiralOrderPrint(matrix);
    }
}
