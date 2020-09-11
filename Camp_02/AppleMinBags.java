package Camp_02;
/*
* 打表找规律
* 某个面试题，输入参数简单，并且只有一个实际参数
* 要求的返回值类型也简单，并且只有一个
* 用暴力方法，把输入参数对应的返回值，打印出来看看，进而优化code
* */


/* 问题一：
 * 小虎去买苹果，商店只提供两种类型的塑料袋，每种类型都有任意数量。
 * 1）能装下6个苹果的袋子
 * 2）能装下8个苹果的袋子
 * 小虎可以自由使用两种袋子来装苹果，但是小虎有强迫症，他要求自己使用的袋子数量必须最少，且使用的每个袋子必须装满。
 * 给定一个正整数N，返回至少使用多少袋子。如果N无法让使用的每个袋子必须装满，返回-1
 * */
public class AppleMinBags {


    /*
    * 暴力解
    * */
    public static int minBags(int apples){

        if (apples < 0 ){
            return  -1 ;
        }

        int bag6 = -1 ; //6号包
        int bag8 = apples / 8 ; //先试试最多能用几个8号袋
        int rest = apples - bag8 * 8 ;//剩余苹果数

        while (bag8 >= 0){
            int restUse6 = minBagBase6(rest);
            if (restUse6 != -1){
                bag6 = restUse6 ;
                break;
            }
            //  ==-1，余下袋子无法用6号装完
            bag8 -- ;
            rest = apples - bag8 * 8 ;
        }

        return bag6 == -1 ? -1 : bag6+bag8 ;
    }

    //余下苹果能否用6号袋子装完，能返回几个6号袋，不能返回-1
    public static int minBagBase6(int rest){
        return rest % 6 == 0 ? rest / 6 : -1 ;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println("apple:" +i + " " + minBags(i));
        }
    }

    /*
    * 根据结果 直接优化成O（1）
    * */
    public static int minBagAwesome(int apple) {
        if ((apple & 1) != 0) { // 如果是奇数，返回-1
            return -1;
        }
        if (apple < 18) {
            return apple == 0 ? 0 : (apple == 6 || apple == 8) ? 1
                    : (apple == 12 || apple == 14 || apple == 16) ? 2 : -1;
        }
        return (apple - 18) / 8 + 3;
    }
}
