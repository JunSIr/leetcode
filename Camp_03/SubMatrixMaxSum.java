package Camp_03;
/**
 * 返回矩阵最大累加和
 * 思路：枚举长方形 O(N^6)
 * 优化：长方形压缩成一行，讨论单行数组最大累加和 O(N^3)
 * */
public class SubMatrixMaxSum {

    public static int maxSum(int[][] m){
        if (m == null ||m.length == 0 || m[0].length == 0){
            return 0 ;
        }
        /*
        * 0-0 0-1 0-N-1 ... 1-1 1-N-1 ... 2-2 2-3 ..
        * */
        int [] s = null ;
        int cur = 0 ;
        int max = Integer.MIN_VALUE ;
        for (int i = 0; i < m.length ; i++) {
            s = new int[m[0].length] ; //一行有几个就几个

            for (int j = i; j < m.length; j++) {
                cur = 0 ;
                for (int k = 0; k < s.length; k++) {
                    s[k] += m[j][k] ; //两列累加
                    cur += s[k] ; //记住累加后的值
                    //列->更新max
                    max = Math.max(max, cur);
                    cur = Math.max(0,cur) ; //cur<0 重置为0
                }

            }
        }

        return max  ;
    }

    public static void main(String[] args) {
        int[][] matrix = { { -9, 4, 7 }, { 6, -4, 6 } };
        System.out.println(maxSum(matrix));

    }
}
