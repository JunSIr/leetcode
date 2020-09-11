package MyDataTest_11;
/*
* 找零问题
* 数组，每个位置都是一定面值的纸币，单位置纸币不限使用次数，返回所有找零方案
* */
public class CoinsWay {

    /*
    * 1.暴力递归尝试
    * */
    public static int ways1(int[] arr, int value){
        if (arr==null ||arr.length==0 ||value<0 )
            return 0 ;
        return process1(arr,0,value) ;
    }
    /*
    * arr 零钱
    * index 左->右模型
    * rest 还需要找零多少
    * */
    public static int process1(int[] arr, int index , int rest){

        //base case
        if (index == arr.length)
            return rest==0 ? 1 : 0  ;

        int ways = 0  ; //方案数
        /*
        * 枚举 - 当前位置的面额 用几张
        * 如 zhang == 0 表示一涨不用
        * */
        for (int zhang = 0; zhang * arr[index] <= rest  ; zhang++) {
            ways +=  process1(arr,index +1 ,rest - zhang * arr[index] ) ;
        }
        return ways ;
    }

    /*
    * 2.记忆化搜索
    * 确定之前暴力递归含有重复计算 --> 加一个缓存结构，不用精细
    * 用结构["2_30" ，方案] 代表2位置30面值产生的方案，可以用哈希表也可以用二维数组
    * dp[index][value]
    * */
    public static int ways2(int[] arr, int value ){
        if (arr==null ||arr.length==0 ||value<0 )
            return 0 ;
        //记忆缓存结构
        int[][] dp = new int[arr.length +1 ][value + 1 ] ;  //value 0 -> value 故value+1

        //人为将值置为-1 表示缓存中没有
        for (int i = 0; i < dp.length; i++) {  //行
            for (int j = 0; j < dp[0].length; j++) { //列
                 dp[i][j] = -1 ;
            }
        }
        return process2(arr,0, value, dp) ;
    }

    public static int process2(int[] arr, int index , int rest ,int[][] dp){

        //上来先判断 缓存中是否有结果
        if (dp[index][rest] != -1){ //有结果
            return dp[index][rest] ;
        }

        //base case
        if (index == arr.length){
            dp[index][rest] =  rest == 0 ? 1 : 0  ;
            return dp[index][rest] ;
        }

        int ways = 0  ; //方案数
        for (int zhang = 0; zhang * arr[index] <= rest  ; zhang++) {
            ways +=  process1(arr,index +1 ,rest - zhang * arr[index] ) ;
        }
        dp[index][rest] = ways;
        return dp[index][rest] ;
    }


    /*
    * 3.标准动态规划
    *  = 精细化缓存结构
    * */
    public static int ways3(int[] arr, int value){

        if (arr==null ||arr.length==0 ||value<0 )
            return 0 ;

        int N = arr.length;
        int[][] dp = new int[N + 1][value + 1] ;

        // 根据 return process1(arr,0,value) 确定目标位于最右上

        //根据 if (index == arr.length) return rest==0 ? 1 : 0  确定初始值位于最左下
        dp[N][0] = 1 ;

        /*
        * 由以上信息 可以确定 推进顺序 下到上  同行之间不影响 默认左到右
        * */
        for (int index = N-1; index >= 0  ; index--) {
            for (int rest = 0; rest <= value; rest++) {

                int ways = 0  ;
                for (int zhang = 0; zhang * arr[index] <= rest  ; zhang++) {
                    ways += dp[index + 1][rest - zhang * arr[index] ] ;
                }
                dp[index][rest] = ways ;

            }
        }
        return dp[0][value] ;
    }

    /*
    * 4.优化以上动态规划的枚举 第三个for循环 即 每个格子还要跑一遍循环
    * 此处需要具体分析
    * */
    public static int ways4(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0)
            return 0 ;

        int N = arr.length;

        int[][] dp = new int[N + 1][aim + 1] ;

        dp[N][0] = 1 ;

        // 根据 return process1(arr,0,value) 确定目标位于最右上

        //根据 if (index == arr.length) return rest==0 ? 1 : 0  确定初始值位于最左下

        /*
         * 由以上信息 可以确定 推进顺序 下到上  同行之间不影响 默认左到右
         * */
        for(int index = N - 1; index >= 0; index--) {
            for(int rest = 0; rest <= aim; rest++) {

                dp[index][rest] = dp[index +1][rest]  ; //rest > rest - xx
                if (rest - arr[index] >= 0 )
                    dp[index][rest] += dp[index][rest - arr[index] ] ;

            }
        }
        return dp[0][aim] ;
    }

    public static void main(String[] args) {
        int[] arr = { 5, 10,50,100 };
        int sum = 1000;
        System.out.println(ways1(arr, sum));
        System.out.println(ways2(arr, sum));
        System.out.println(ways3(arr, sum));
        System.out.println(ways4(arr, sum));
    }

}
