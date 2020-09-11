package Camp_03;



/**
 * 给定一个二维数组matrix,可以从任何位置出发，每一步可以走上、下、左、右、四个方向
 * 返回最大递增链的长度
 * 例子 matrix =
 * 5 4 3
 * 3 1 2
 * 2 1 3
 * 从最中心的1出发，是可以走1 2 3 4 5的链的，而且这是最长的递增链 所以返回长度5
 * */
public class LongestIncreasingPath {

    /*
    * 纯暴力解 枚举每个位置的最长递增链 总体返回最长的
    * */
    public static int maxPath(int[][] matrix) {
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                ans = Math.max(ans, process(matrix,i,j));
            }
        }
        return ans;
    }
    // 递归含义: 假设在matrix中，从i行，j列出发，能走出的最长递增路径，返回最长递增路径的长度
    public static int process(int[][] matrix,int i, int j) {
        if (i < 0 || i > matrix.length || j < 0 || j > matrix[0].length){
            return -1; //代表无有效长度
        }
        // [i,j]这个点的上、下、左、右分别的最长递增链长度
        int up = 0;
        int dowm = 0;
        int left = 0;
        int right = 0;

        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]){ //上边不越界并且比自身大
            up = process(matrix,i - 1,j);
        }
        if (i + 1 <= matrix.length && matrix[i + 1][j] > matrix[i][j]){
            dowm = process(matrix,i + 1,j);
        }
        if (j - 1 >=0 && matrix[i][j - 1] > matrix[i][j]){
            left = process(matrix,i,j - 1);
        }
        if (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]){
            right = process(matrix,i, j + 1);
        }
        return 1 + Math.max(Math.max(up,dowm),Math.max(left,right)); //1在这是base case了
    }

    /*
    *  由于dp表的联系太弱 看不出规律 直接使用记忆化搜索 时间复杂度O(row*col)
    * */

    public static int maxPath2(int[][] matrix) {
        int ans = Integer.MIN_VALUE;
        //与原矩阵一样大小就好
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                ans = Math.max(ans, process1(matrix,i,j,dp));
            }
        }
        return ans;
    }

    public static int process1(int[][] matrix,int i, int j, int[][] dp) {
        if (i < 0 || i > matrix.length || j < 0 || j > matrix[0].length){
            return -1; //代表无有效长度
        }

        if (dp[i][j] != 0){
            return dp[i][j];
        }

        // [i,j]这个点的上、下、左、右分别的最长递增链长度
        int up = 0;
        int dowm = 0;
        int left = 0;
        int right = 0;
        int ans = 0;

        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]){ //上边不越界并且比自身大
            up = process1(matrix,i - 1,j,dp);
        }
        if (i + 1 <= matrix.length && matrix[i + 1][j] > matrix[i][j]){
            dowm = process1(matrix,i + 1,j,dp);
        }
        if (j - 1 >=0 && matrix[i][j - 1] > matrix[i][j]){
            left = process1(matrix,i,j - 1,dp);
        }
        if (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]){
            right = process1(matrix,i, j + 1,dp);
        }

        dp[i][j] = 1 + Math.max(Math.max(up,dowm),Math.max(left,right));
        return ans;
    }

}
