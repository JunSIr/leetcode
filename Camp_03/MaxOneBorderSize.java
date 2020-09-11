package Camp_03;
/*
给定一个N×N的矩阵matrix，在这个矩阵中，只有0和1两种值，返回边框全是1的最大正方形的边长长度、
例如
0 1 1 1 1
0 1 0 0 1
0 1 0 0 1
0 1 1 1 1
0 1 0 1 1
其中，边框全是1的最大正方形的大小为4×4，所以返回4
* */
public class MaxOneBorderSize {

    //思路  枚举边长O(n^2) -- > 枚举左上角点 O(n) --> 枚举整个边是否都是1  优化到O（N）
    //整体 O（n^3）

    /*
    * 准备两个预处理矩阵 a[i]含义为 该点右边/下边包含自己连续几个1
    * */
    public static void setBorderMap(int[][] m ,int [][] right ,int [][] dowm){

        int r = m.length;
        int c = m[0].length ;

        //设置最右下角数
        if (m[r - 1][c - 1] == 1){
            right[r - 1][c - 1] = 1 ;
            dowm[r - 1][c - 1] = 1 ;
        }

        //设置最右一列数
        for (int i = r - 2; i != -1 ; i--) {
            if (m[i][c - 1] == 1){
                right[i][c - 1] = 1;
                dowm[i][c - 1]  =  dowm[i + 1][c - 1] + 1 ;
            }
        }

        //设置最后一行数
        for (int i = c - 2; i != -1 ; i--) {
            if (m[r - 1][i] == 1){
                right[r - 1][i] = right[r - 1][i + 1] + 1;
                dowm[r - 1][i] = 1  ;
            }
        }

        //根据最后一行最后一列，填充两个预处理数组
        for (int i = r - 2; i != -1 ; i--) {
            for (int j = c - 2; j != -1; j--) {
                if (m[i][j] == 1){
                    right[i][j] = right[i][j + 1] + 1 ;
                    dowm[i][j] = dowm[i + 1][j] + 1 ;
                }
            }
        }
    }


    public static int getMaxSize(int[][] m){
        //预处理数组
        int[][] right = new int[m.length][m[0].length] ;
        int[][] down = new int[m.length][m[0].length] ;
        setBorderMap(m,right,down);

        //枚举边长 由于是正方形 长与高选最短
        for (int size = Math.min(m.length,m[0].length); size != 0  ; size--) {
            if (hasSizeOfBorder(size,right,down)){
                return size ; //由于左上角的枚举是从大到小来考虑的，故有酒返回
            }
        }
        return 0;
    }

    public static boolean hasSizeOfBorder(int size, int[][] right, int[][] down){

        for (int i = 0; i != right.length - size + 1; i++) { //枚举左上角点的行

            for (int j = 0; j != right[0].length - size + 1 ; j++) { //列
                if (right[i][j] >= size && down[i][j] >= size
                && right[i + size -1][j] >= size && down[i][j + size - 1] >= size){
                    return  true ;
                }
            }
        }
        return false ;
    }

    //for test
    public static int[][] generateRandom01Matrix(int rowSize, int colSize) {
        int[][] res = new int[rowSize][colSize];
        for (int i = 0; i != rowSize; i++) {
            for (int j = 0; j != colSize; j++) {
                res[i][j] = (int) (Math.random() * 2);
            }
        }
        return res;
    }

    //for test
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = generateRandom01Matrix(7, 8);
        printMatrix(matrix);
        System.out.println(getMaxSize(matrix));
    }
}
