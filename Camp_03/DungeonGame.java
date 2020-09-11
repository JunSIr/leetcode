package Camp_03;
/**
 * 给定一个二维数组map 含义是一张地图 例如 如下矩阵
 * -2 -3 4
 * -5 -10 1
 * 0 30 -5
 * 游戏的规则如下
 * 骑士从左上角出发 每次只能向右或向下走 最后到达右下角见到公主
 * 地图中每个位置的值代表要遭遇的事情
 * 如果是负数、说明此处有怪兽、要让骑士损失血量
 * 如果是非负数、代表此处有血瓶、能让骑士回血
 * 骑士从左上角到右下角的过程中，走到任何一个位置时，血量不能少于1
 * 为了保证骑士能见到公主、初始血量至少是多少？根据map返回初始血量
 *
 * */
public class DungeonGame {

    //暴力递归  有时间再改动态规划
    public static int needMin(int[][] matrix){
        return 1;
    }

    public static int process(int[][] matrix, int N, int M, int row, int col){
        // base case 来到右下角的位置
        if (row == N - 1 && col == M - 1){
            // 至少需要
            return matrix[N - 1][M - 1] < 0 ? (-matrix[N - 1][M - 1] + 1) : 1 ;
        }

        if (row == N - 1){ //到达最后一行
            int rightNeed = process(matrix,N,M,row,col + 1); //右边需要的最少生命值
            if (matrix[row][col] < 0){ //当前位置有怪兽
                return  -matrix[row][col] + rightNeed;
            }else if (matrix[row][col] >= rightNeed){ //当前位置有血瓶且大于右边所需最少血量
                return 1; //保证能登上就好
            }else {
                return rightNeed - matrix[row][col];
            }

        }
        if (col == M - 1){ //到达最后一列
            int downNeed = process(matrix,N,M,row + 1,col); //右边需要的最少生命值
            if (matrix[row][col] < 0){ //当前位置有怪兽
                return  -matrix[row][col] + downNeed;
            }else if (matrix[row][col] >= downNeed){ //当前位置有血瓶且大于右边所需最少血量
                return 1; //保证能登上就好
            }else {
                return downNeed - matrix[row][col];
            }


        }

        //普遍位置
        // 最少需要 右下抉择
        int minNextNeed = Math.min( process(matrix,N,M,row,col + 1), process(matrix,N,M,row + 1,col));
        if (matrix[row][col] < 0){ //如果当前是怪兽
            return (-matrix[row][col]) + minNextNeed;
        }else if (matrix[row][col] >= minNextNeed){ //跟上面一样
            return 1;
        }else {
            return minNextNeed - matrix[row][col];
        }

    }
}
