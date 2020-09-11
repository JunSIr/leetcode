package MyDataTest_11;
/*
*       N皇后问题
* N皇后问题是指在N*N的棋盘上要摆N个皇后，
* 要求任何两个皇后不同行、不同列， 也不在同一条斜线上
* 给定一个整数n，返回n皇后的摆法有多少种。n=1，返回1
* n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
* n=8，返回92
* */
public class NQueens {

    public static int nQueens(int n ){
        if (n < 1)
            return 0 ;

        int[] record = new int[n];
        return process(0,record,n) ;
    }

    /*
    * i 代表来到第几行，每一行都只能有一个皇后
    * recode本行之上的皇后位置 如 recode[0] =1 则代表第0行的皇后位置在第一列
    * n 代表是 n皇后问题
    * */
    public static int process(int i ,int[] record , int n){
        //base case
        if (i == n)
            return 1 ;  //越界，返回一个结果

        int res = 0 ;
        //循环列
        for (int j = 0 ; j < n; j++) {
            if (isValid(record,i,j)){
                //验证通过,位置锁定,到下一行去决策
                record[i] = j  ;
                res += process(i + 1, record, n);
            }
        }
        return res ;
    }


    public static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) { //列++
            /*
            * 同列同斜线
            * */
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(nQueens(8));
    }
}
