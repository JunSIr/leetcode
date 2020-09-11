package MyDataTest_11;
/*
         范围模型上的尝试
* 给定一个整型数组arr，代表数值不同的纸牌排成一条线，
* 玩家A和玩家B依次拿走每张纸牌，
* 规定玩家A先拿，玩家B后拿，
* 但是每个玩家每次只能拿走最左或最右的纸牌，
* 玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。
* */
public class CardWhoWin {

    public static int m(int [] arr){
        if (arr==null || arr.length == 1)
            return 0 ;
        /*
        * 0-len-1张牌。先手与后手。谁点数大谁赢。
        * 假设A 和 B 选牌都是最佳方案  谁胜利取决于牌的顺序
        * */
        return Math.max(f(arr,0,arr.length-1),s(arr,0,arr.length-1)) ;
    }
    /*
     * 先手拿牌
     * arr 剩下的牌
     * L 数组左下标，右同理
     * */
    public static int f(int[] arr, int L ,int R){
        //base case : 先手，只剩一张牌了，直接拿走
        if (L==R)
            return arr[L] ;
        /*
         * 在后手那位选牌的可能性中，选择对我最有利的
         * */
        return Math.max(arr[L] +s(arr,L+1,R), arr[R] + s(arr,L,R -1)) ;

    }

    /*
     * 后手拿牌
     * arr 剩下的牌
     * L 数组左下标，右同理
     * */
    public static int s(int[] arr, int L ,int R){
        //base case : 先手，只剩一张牌了，直接拿走
        if (L==R)
            return 0 ;
        /*
        * 在先手那位选牌的可能性中，选择对我最不利的
        * */
        return Math.min(f(arr,L,R-1),f(arr,L +1 ,R)) ;
    }



    /*
    * 暴力递归改动态规划
    * f、s各对应一张正方形二维表
    * 边界控制 f 0->N || s 0->N  N算越界  L<R --> 结构左下部分无效
    * 顺序 :
    * */
    public static int dpWay(int[] arr){

        if (arr==null || arr.length == 1)
            return 0 ;

        int N = arr.length;
        int[][] f = new int[N][N];
        int[][] s = new int[N][N];

        /*
        * 初始化 f
        * if (L==R) return arr[L] ;
        * */
        for (int i = 0; i < N ; i++) {
            f[i][i] = arr[i] ;
        }

        /*
        * 初始化s
        * if (L==R) return 0 ;
        * 由于Java int类型 一开始初始化为0 所以不必处理
        * */


        /*
        * 按顺序推进
        * */

                /*   从此处可分析出推进顺序 向右上  对角线  推到角点
                return Math.max(arr[L] +s(arr,L+1,R), arr[R] + s(arr,L,R -1)) ;*/
        for (int i = 1; i < N; i++) { //按列推进
            int row = 0 ;//行
            int col = i; //列
            while (row < N && col < N){
                //Math.min(f(arr,L,R-1),f(arr,L +1 ,R)) ;
                s[row][col] = Math.min(f[row][col-1],f[row+1][col]);
                // Math.max(arr[L] +s(arr,L+1,R), arr[R] + s(arr,L,R -1))
                f[row][col] = Math.max(arr[row] + s[row+1][col],arr[col] + s[row][col-1]) ;
                row ++ ;
                col ++ ;
            }

        }
        //return Math.max(f(arr,0,arr.length-1),s(arr,0,arr.length-1)) ;
        return  Math.max(f[0][N-1],s[0][N-1]) ;
    }



    public static void main(String[] args) {
        int[] arr = {1,9,1} ; //先手必输
        System.out.println(m(arr));
        System.out.println(dpWay(arr));
    }
}
