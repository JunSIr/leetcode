package Camp_02;
/*
*  还是打表找规律
* 给定一个正整数N，表示有N份青草统一堆放在仓库里
* 有一只牛和一只羊，牛先吃，羊后吃，它俩轮流吃草
* 不管是牛还是羊，每一轮能吃的草量必须是：1，4，16，64…(4的某次方)
* 谁最先把草吃完，谁获胜
* 假设牛和羊都绝顶聪明，都想赢，都会做出理性的决定
* 根据唯一的参数N，返回谁会赢
* */
public class EatGrass {
    /*
    * 暴力解
    * String 返回"先手”/“后手”  代表谁赢
    * */
    public static String winner(int N){
        // 0  1  2  3 4
        // 后 先 后 先 先
        if (N < 5){
            return (N == 0 || N == 2 )? "后手" : "先手" ;
        }

        int base = 1 ; //当前先手决定吃的草数

        while (base < N){
            //母过程的子递归  本轮先手就是下一轮的后手
            if (winner(N - base).equals("后手")){
                return "先手" ;
            }
            //防止内存溢出
            if (base > N/4){
                break;
            }
            base *= 4  ;
        }

        return "后手" ;
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 50; i++) {
            System.out.println(i + " : " + winner(i));
        }
    }

    /*
    * 规律解
    * */
    public static String winner2(int N ){
        if (N % 5 == 0 || N % 5 == 2) {
            return "后手";
        } else {
            return "先手";
        }
    }
}
