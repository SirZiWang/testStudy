package com.wangzi.test.algorithm;

import java.util.Random;

public class ShellSort2 {
	//直接定义一种增量枚举类型
    private static enum Type{
        SHELL, HIBBARD;
    }

    public static<T extends Comparable<? super T>> void shellsort(T[] target, Type type){//修改原来的方法，新增一个增量类型参数
        //但是要保证两者的增量数是一致的，所以用size标记增量个数
        int size = 0;
        for(int increment = target.length / 2; increment > 0; increment /=2){
            size++;
        }
        switch (type) {
        case SHELL:
            long startTimeShell = System.currentTimeMillis();
            for(int increment = target.length / 2; increment > 0; increment /=2){
                dataHanding(target, increment);
            }
            System.out.println("希尔增量排序耗时："+ (System.currentTimeMillis() - startTimeShell));
            break;
        case HIBBARD:
            long startTimeHibbard = System.currentTimeMillis();
            for(int increment = (int) (Math.pow(2, size)-1); size > 0; increment = (int) (Math.pow(2, --size)-1)){
                dataHanding(target, increment);
            }
            System.out.println("Hibbard增量排序耗时："+ (System.currentTimeMillis() - startTimeHibbard));
            break;

        default:
            break;
        }


    }

    private static <T extends Comparable<? super T>> void dataHanding(T[] target, int increment) {
        int j ;//标记当前数据应该要插入的位置
        //简单的插入排序
        //遍历目标数据，从第一个增量开始，第0个增量默认排序好的
        for(int i = increment; i < target.length; i++){
            //把带插入的数据用temp暂存起来
            T temp = target[i];
            //寻找准确的插入位置
            //其实核心是在增量的分组下寻找这个值在某一个分组下应该属于它的位置
            for(j = i; j >= increment&&temp.compareTo(target[j - increment]) < 0; j-=increment){//注意，因为是处理分组的，所以不能和简单插入排序一样j--
                //如果当前位置的数据比前一个数据小，那么就需要把前面数据往后移动一位
                target[j] = target[j - increment];//这里并不是冒泡的交换位置！！
            }
            target[j] = temp;//把数据插入到准确的地方，这个才是插入排序
/*          //打印每次排序后的结果
            String result = "";
            for (T t : target) {
                result += t+" ";
            }
            System.out.println(increment+"增量的排序结果：" + result);*/
        }
    }


    public static void main(String[] args) {

        //制造一个2000个数据的无序序列
        Integer[] target = new Integer[20000];
        for (int i = 0; i < 20000; i++) {
            target[i] = new Random().nextInt(2000);
        }

        shellsort(target, Type.SHELL);
        shellsort(target, Type.HIBBARD);
    }
}
