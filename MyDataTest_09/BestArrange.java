package MyDataTest_09;
/*
* 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
* 给你每一个项目开始的时间和结束的时间
* 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
* 返回最多的宣讲场次
* */

import java.util.Arrays;
import java.util.Comparator;

public class BestArrange {

    /*
    * 会议对象
    * */
    public static class Program{
        public int start ; //会议开始时间
        public int end ; //结束时间
        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /*
    * 暴力枚举
    * 无非就是coding出所有可能性
    * */
    public static  int bestArrange(Program[] programs){
        if (programs==null || programs.length<1)
            return 0 ;
        return process(programs,0,0) ;
    }
    /*
    * done : 目前为止已安排的会议数目
    * timeLine ：目前时间
    * */
    public static int process(Program[] programs, int done, int timeLine){
        //已经没有会议，返回已安排的会议
        if (programs.length==0)
            return done ;
        /*
        *  决策本轮产生的最大会议数max
        * */
        int max = done ;
        for (int i = 0; i < programs.length; i++) {
            /*
            * 能安排的我就安排
            * */
            if (programs[i].start >= timeLine){
                Program[] next = copyButExcept(programs, i); //安排完第i个会议后的数组
                max = Math.max(max,process(next,done+1,programs[i].end));
            }

        }
        return max ;
    }

    /*
    * arranged out of programs
    * */
    public static Program[] copyButExcept(Program[] programs, int i) {
        Program[] ans = new Program[programs.length - 1];
        int index = 0;
        for (int k = 0; k < programs.length; k++) {
            if (k != i) {
                ans[index++] = programs[k];
            }
        }
        return ans;
    }

    /*
    * 贪心算法
    * 贪心策略 : 结束时间早的先安排
    * */


    public static  int bestArrange_1(Program[] programs){
        if (programs==null || programs.length<1)
            return 0 ;
        Arrays.sort(programs,new myComparator());
        /*
        * 初始化
        * */
        int timeLine = 0 ;
        int result = 0 ;

        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeLine){
                timeLine = programs[i].end ;
                result ++ ;
            }
        }
        return result ;
    }



            /*
            * 定义比较器
            * */
    public static class myComparator implements Comparator<Program>{
                @Override
                public int compare(Program o1, Program o2) {
                    return o1.end - o2.end;
                }
            }


    // for test
    public static Program[] generatePrograms(int programSize, int timeMax) {
        Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Program(r1, r1 + 1);
            } else {
                ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }


    public static void main(String[] args) {
/*        Program[] programs = new Program[5];

        programs[0] = new Program(1, 3);
        programs[1] = new Program(2, 4);
        programs[2] = new Program(5, 6);
        programs[3] = new Program(1, 3);
        programs[4] = new Program(6, 7);

        int i = bestArrange_1(programs);
        System.out.println(i);*/
        int programSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;
        for (int i = 0; i < timeTimes; i++) {
            Program[] programs = generatePrograms(programSize, timeMax);
            if (bestArrange(programs) != bestArrange_1(programs)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
